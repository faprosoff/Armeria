package edu.ort.t3.tpfinal.vistas;

public interface Vista {
	
	public String leerCadena(String msj);

	public String leerCadena();

	public int leerEntero(String msj);

	public int leerEntero();

	public void mostrar(String mensaje);

	public void mostrar(String mensaje, boolean conEnter);

}
