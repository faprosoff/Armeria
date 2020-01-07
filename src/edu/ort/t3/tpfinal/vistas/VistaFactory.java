package edu.ort.t3.tpfinal.vistas;

public class VistaFactory {

	public enum VistaType {
		CONSOLA;
	}

	public Vista crearVista(VistaType vistaType) {
		Vista instancia = null;
		switch (vistaType) {
		case CONSOLA:
			instancia = new VistaConsola();
			break;
		}
		return instancia;
	}

}
