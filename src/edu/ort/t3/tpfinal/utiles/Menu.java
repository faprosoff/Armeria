package edu.ort.t3.tpfinal.utiles;

import edu.ort.t3.tpfinal.vistas.Vista;

public class Menu {

	private String titulo;
	private String opciones[];
	private int numOpciones;
	private Vista vista;

	public Menu(String titulo, String[] opciones, Vista vista) {
		this.titulo = titulo;
		this.opciones = opciones;
		this.numOpciones = this.opciones.length;
		this.vista = vista;
	}

	/**
	 * GESTIONA EL MENU.
	 * MUESTRA LAS OPCIONES Y RECOGE LA SELECCIONADA POR EL USUARIO.
	 */
	public int gestionar() {
		mostrar();
		return recogerOpcion();
	}

	private void mostrar() {
		int i = 1;
		vista.mostrar(titulo);
		for (String elemento : opciones)
			vista.mostrar("(" + (i++) + ") " + elemento);
	}

	private int recogerOpcion() {
		int opcion;
		opcion = vista.leerEntero();
		while (opcion < 1 || opcion > numOpciones) {
			vista.mostrar("ERROR: Ingrese una opción entre 1 y " + numOpciones);
			opcion = vista.leerEntero();
		}
		return opcion;
	}
	
}