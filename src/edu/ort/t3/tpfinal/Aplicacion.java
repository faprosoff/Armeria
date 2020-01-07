package edu.ort.t3.tpfinal;

import edu.ort.t3.tpfinal.utiles.Menu;
import edu.ort.t3.tpfinal.vistas.Vista;
import edu.ort.t3.tpfinal.vistas.VistaFactory;
import edu.ort.t3.tpfinal.vistas.VistaFactory.VistaType;

import java.util.ArrayList;

import edu.ort.t3.tpfinal.entidades.*;
import edu.ort.t3.tpfinal.entidades.arma.Arma;
import edu.ort.t3.tpfinal.entidades.arma.ArmaFactory.ArmaType;
import edu.ort.t3.tpfinal.excepciones.*;

public class Aplicacion {

	private Armeria armeria;
	private Vista vista;
	private VistaFactory vistaFactory;
	private String[] opciones;
	private Menu menu;
	private Menu menuTipos;

	public Aplicacion(VistaType vistaType) {
		try {
			this.armeria = new Armeria();
			this.vistaFactory = new VistaFactory();
			this.vista = vistaFactory.crearVista(vistaType);
			this.opciones = new String[] { "ALTA DE ARMA", "BAJA DE ARMA", "MOSTRAR ARMA", "MOSTRAR TODAS LAS ARMAS",
					"CANTIDAD DE ARMAS EN LA ARMERIA", "MOSTRAR ARMAS DE UN TIPO", "SALIR" };
			this.menu = new Menu(armeria.getNombre(), opciones, vista);
			this.menuTipos = new Menu("¿Qué arma desea ingresar?", generarOpcionesMenu(), vista);
		} catch (ArmaManagerException ame) {
			vista.mostrar(ame.getMessage());
		}
	}

	// MENU GENERAL DEL PROGRAMA
	public void IniciarMenu() {
		int opcion;
		do {
			switch (opcion = menu.gestionar()) {
			case 1: // AÑADE UN ARMA
				anadirArma();
				break;
			case 2: // ELIMINA UN ARMA
				eliminarArma();
				break;
			case 3: // DEVUELVE UN ARMA
				getArma();
				break;
			case 4: // MUESTRA TODAS LAS ARMAS DE LA ARMERIA
				mostrarArmeria();
				break;
			case 5: // DEVUELVE EL TOTAL DE ARMAS EN LA ARMERIA
				mostrarTotalArmas();
				break;
			case 6: // MUESTRA LAS ARMAS DE UN TIPO ESPECIFICADO
				mostrarArmasSegunTipo();
				break;
			default: // SALIR
				vista.mostrar("Gracias. Vuelva prontos.");
			}
		} while (opcion != opciones.length);
	}

	// AÑADE UN ARMA SEGUN EL NUMERO DE SERIE
	private void anadirArma() {
		String numeroSerie;
		try {
			numeroSerie = vista.leerCadena("Introduzca el número de serie (formato A-123):");
			armeria.anadir(numeroSerie, pedirTipo());
			vista.mostrar("El arma ha sido añadida correctamente.");
		} catch (NumeroSerieNoValidoException nsnve) {
			vista.mostrar("El número de serie introducido no es válido.");
		} catch (TipoNoValidoException tnve) {
			vista.mostrar("El tipo introducido no es válido.");
		} catch (ArmaYaExisteException ayee) {
			vista.mostrar("El arma ya existe.");
		} catch (ArmaManagerException ame) {
			vista.mostrar(ame.getMessage());
		}
	}

	// ELIMINA UN ARMA SEGUN EL NUMERO DE SERIE
	private void eliminarArma() {
		String numeroSerie;
		try {
			numeroSerie = vista.leerCadena("Introduzca el número de serie (formato A-123):");
			armeria.eliminar(numeroSerie);
			vista.mostrar("Arma eliminada");
		} catch (NumeroSerieNoValidoException nsnve) {
			vista.mostrar("El número de serie introducido no es válido.");
		} catch (ArmaNoExisteException anee) {
			vista.mostrar("El arma no existe.");
		} catch (ArmaManagerException ame) {
			vista.mostrar(ame.getMessage());
		}
	}

	// MUESTRA UN ARMA SEGUN EL NUMERO DE SERIE
	private void getArma() {
		Arma arma;
		String numeroSerie;
		try {
			numeroSerie = vista.leerCadena("Introduzca el número de serie (formato A-123):");
			arma = armeria.getArmaPorNumSerie(numeroSerie);
			vista.mostrar(arma.toString());
			vista.mostrar("");
		} catch (NumeroSerieNoValidoException nsnve) {
			vista.mostrar("El número de serie introducido no es válido.");
		} catch (ArmaNoExisteException anee) {
			vista.mostrar("El arma no existe.");
		}
	}

	// MUESTRA TODAS LAS ARMAS DE LA ARMERIA
	private void mostrarArmeria() {
		if (armeria.getArmas().isEmpty()) {
			vista.mostrar("La armeria se encuentra vacía.");
		} else {
			for (Arma arma : armeria.getArmas()) {
				vista.mostrar(arma.toString());
			}
			vista.mostrar("");
		}
	}

	// DEVUELVE EL TOTAL DE ARMAS EN LA ARMERIA
	private void mostrarTotalArmas() {
		vista.mostrar("Total de armas almacenadas: " + armeria.getArmas().size() + "\n");
	}

	public void mostrarArmasSegunTipo() {
		ArmaType tipo = pedirTipo();
		ArrayList<Arma> armasTipo = armeria.getArmasTipo(tipo);
		for (Arma arma : armasTipo) {
			vista.mostrar(arma.toString());
		}
		vista.mostrar("");
	}

	// PIDE EL TIPO DEL ARMA
	private ArmaType pedirTipo() {
		ArmaType tipo;
		int opcion = menuTipos.gestionar();
		ArmaType[] tipos = getValues();
		if (opcion == tipos.length + 1) {
			tipo = null;
		} else {
			tipo = tipos[opcion - 1];
		}
		return tipo;
	}

	private static final ArmaType[] VALUES = ArmaType.values();


	private String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[VALUES.length];
		int i = 0;
		for (ArmaType tipo : VALUES) {
			opcionesMenu[i++] = tipo.name();
		}
		return opcionesMenu;
	}
	public ArmaType[] getValues() {
		return VALUES;
	}
}
