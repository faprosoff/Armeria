package edu.ort.t3.tpfinal;

import edu.ort.t3.tpfinal.vistas.VistaFactory.VistaType;

public class Main {
	public static void main(String[] args) {

		Aplicacion app = new Aplicacion(VistaType.CONSOLA);

		app.IniciarMenu();

	}
}