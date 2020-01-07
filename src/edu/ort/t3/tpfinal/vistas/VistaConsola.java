package edu.ort.t3.tpfinal.vistas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class VistaConsola implements Vista {

	@Override
	public String leerCadena(String msj) {
		System.out.println(msj);
		return leerCadena().toUpperCase();
	}

	@Override
	public String leerCadena() {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String cadena;
		try {
			cadena = bReader.readLine();
		} catch (Exception e) {
			cadena = "";
		}
		return cadena;
	}

	@Override
	public int leerEntero(String msj) {
		System.out.println(msj);
		return leerEntero();
	}

	@Override
	public int leerEntero() {
		int x;
		try {
			x = Integer.parseInt(leerCadena().trim());
		} catch (Exception e) {
			x = 0;
		}
		return x;
	}

	@Override
	public void mostrar(String mensaje) {
		mostrar(mensaje, true);
	}

	@Override
	public void mostrar(String mensaje, boolean conEnter) {
		if (conEnter) {
			System.out.println(mensaje);
		} else {
			System.out.print(mensaje);
		}
	}

}