package edu.ort.t3.tpfinal.entidades;

import java.util.ArrayList;

import edu.ort.t3.tpfinal.entidades.arma.Arma;
import edu.ort.t3.tpfinal.entidades.arma.ArmaFactory;
import edu.ort.t3.tpfinal.entidades.arma.ArmaFactory.ArmaType;
import edu.ort.t3.tpfinal.excepciones.*;
import edu.ort.t3.tpfinal.manejadores.ArmaManager;

public class Armeria {
	private ArrayList<Arma> armas;
	private ArmaFactory armaFactory;
	private ArmaManager armaManager;
	private static final String NOMBRE = "AMMUNATION";

	public Armeria() throws ArmaManagerException {
		this.armaFactory = new ArmaFactory();
		this.armaManager = new ArmaManager();
		this.armas = armaManager.obtenerArmas(armaFactory);
	}

	// AÑADE UN ARMA A LA ARMERIA

	public boolean anadir(String numeroSerie, ArmaType tipo)
			throws NumeroSerieNoValidoException, TipoNoValidoException, ArmaYaExisteException, ArmaManagerException {
		int i = 0;
		boolean pudo = false;
		while (i < armas.size() && !pudo) {
			if (armas.get(i).getNumeroSerie().equals(numeroSerie)) {
				throw new ArmaYaExisteException();
			}
			i++;
		}
		if (!pudo) {
			Arma arma = armaFactory.crearArma(numeroSerie, tipo);
			armas.add(arma);
			armaManager.agregarArmas(arma);
			pudo = true;
		}
		return pudo;
	}

	// ELIMINA UN ARMA DE LA ARMERIA

	public boolean eliminar(String numeroSerie)
			throws NumeroSerieNoValidoException, ArmaNoExisteException, ArmaManagerException {
		int i = 0;
		boolean encontre = false;
		while (i < armas.size() && !encontre) {
			if (armas.get(i).getNumeroSerie().equals(numeroSerie)) {
				armas.remove(i);
				encontre = true;
			}
			i++;
		}
		if (!encontre) {
			throw new ArmaNoExisteException();
		}
		return encontre;
	}

	// DEVUELVE UN ARMA SEGUN EL NUMERO DE SERIE ESPECIFICADO

	public Arma getArmaPorNumSerie(String numeroSerie) throws NumeroSerieNoValidoException, ArmaNoExisteException {
		Arma arma = null;
		int i = 0;
		boolean encontre = false;
		while (i < armas.size() && !encontre) {
			if (armas.get(i).getNumeroSerie().equals(numeroSerie)) {
				arma = armas.get(i);
				encontre = true;
			}
			i++;
		}
		if (arma == null) {
			throw new ArmaNoExisteException();
		}
		return arma;
	}

	// GENERA UNA LISTA DE ARMAS DE UN TIPO ESPECIFICADO

	public ArrayList<Arma> getArmasTipo(ArmaType tipo) {
		ArrayList<Arma> armasTipo = new ArrayList<Arma>();
		for (Arma arma : armas) {
			if (arma.getTipo() == tipo) {
				armasTipo.add(arma);
			}
		}
		return armasTipo;
	}

	// GETTERS

	public String getNombre() {
		return NOMBRE;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Arma> getArmas() {
		return (ArrayList<Arma>) armas.clone();
	}

	// TO STRING

	public String toString() {
		return "ARMERIA " + NOMBRE + "\n" + armas;
	}

}
