package edu.ort.t3.tpfinal.manejadores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import edu.ort.t3.tpfinal.entidades.arma.Arma;
import edu.ort.t3.tpfinal.entidades.arma.ArmaFactory;
import edu.ort.t3.tpfinal.entidades.arma.ArmaFactory.ArmaType;
import edu.ort.t3.tpfinal.excepciones.ArmaManagerException;

public class ArmaManager {

	private File file;

	private static final String SEPARATOR = ",";
	private static final String ARMERIA_TXT = "Armeria.txt";
	private static final String MSJ_FNFE = "El archivo no existe.";
	private static final String MSJ_NFE = "Error en la conversión de datos.";
	private static final String MSJ_IOE = "Error al leer el archivo ";
	private static final String MSJ_EXCEPTION = "Se ha producido un error.";
	private static final int CANT_CAMPOS = 2;

	public ArmaManager() throws ArmaManagerException {
		this.file = new File(ARMERIA_TXT);
		verificarExistenciaArchivo();
	}

	// ABRE EL ARCHIVO QUE CONTIENE LA LISTA DE ARMAS

	public ArrayList<Arma> obtenerArmas(ArmaFactory armaFactory) throws ArmaManagerException {
		ArrayList<Arma> armasList = new ArrayList<Arma>();
		try {
			Reader reader = new FileReader(file);
			BufferedReader bufferedreader = new BufferedReader(reader);
			String string;
			while ((string = bufferedreader.readLine()) != null) {
				String[] armas = string.split(SEPARATOR);
				if (armas.length == CANT_CAMPOS) {
					Arma arma = armaFactory.crearArma(armas[0], ArmaType.valueOf(armas[1]));
					armasList.add(arma);
				}
			}
			bufferedreader.close();
		} catch (FileNotFoundException fnfe) {
			throw new ArmaManagerException(MSJ_FNFE);
		} catch (NumberFormatException nfe) {
			throw new ArmaManagerException(MSJ_NFE);
		} catch (IOException ioe) {
			throw new ArmaManagerException(MSJ_IOE + file.getAbsolutePath());
		} catch (Exception e) {
			throw new ArmaManagerException(MSJ_EXCEPTION);
		}
		return armasList;
	}

	// GUARDA EL ARCHIVO QUE CONTIENE LA LISTA DE ARMAS SOBREESCRIBIENDO EL
	// ARCHIVO ANTERIOR

	public void guardarArmas(ArrayList<Arma> armas) throws ArmaManagerException {
		try {
			Writer writer = new FileWriter(file);
			BufferedWriter bufferedrwriter = new BufferedWriter(writer);
			PrintWriter printwriter = new PrintWriter(bufferedrwriter);
			int indice = 0;
			String s;
			for (Arma a : armas) {
				if (indice == (armas.size() - 1)) {
					s = "";
				} else {
					s = "\n";
				}
				printwriter.print(a.getNumeroSerie() + SEPARATOR + a.getTipo() + s);
				printwriter.checkError();
				indice++;
			}
			printwriter.close();
		} catch (IOException ioe) {
			throw new ArmaManagerException(MSJ_IOE + file.getAbsolutePath());
		} catch (Exception e) {
			throw new ArmaManagerException(MSJ_EXCEPTION);
		}
	}

	// GUARDA UN ARMA AL FINAL DEL ARCHIVO

	public void agregarArmas(Arma arma) throws ArmaManagerException {
		try {
			Writer writer = new FileWriter(file, true);
			BufferedWriter bufferedrwriter = new BufferedWriter(writer);
			PrintWriter printwriter = new PrintWriter(bufferedrwriter);
			printwriter.print("\n" + arma.getNumeroSerie() + SEPARATOR + arma.getTipo());
			printwriter.checkError();
			printwriter.close();
		} catch (IOException ioe) {
			throw new ArmaManagerException(MSJ_IOE + file.getAbsolutePath());
		} catch (Exception e) {
			throw new ArmaManagerException(MSJ_EXCEPTION);
		}
	}

	// CREA EL ARCHIVO SI EL MISMO NO EXISTE

	private void verificarExistenciaArchivo() throws ArmaManagerException {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				throw new ArmaManagerException(MSJ_IOE + file.getAbsolutePath());
			} catch (Exception e) {
				throw new ArmaManagerException(MSJ_EXCEPTION);
			}
		}
	}

}