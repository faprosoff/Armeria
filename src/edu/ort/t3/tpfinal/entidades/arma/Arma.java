package edu.ort.t3.tpfinal.entidades.arma;

import java.util.regex.Pattern;

import edu.ort.t3.tpfinal.entidades.arma.ArmaFactory.ArmaType;
import edu.ort.t3.tpfinal.excepciones.NumeroSerieNoValidoException;
import edu.ort.t3.tpfinal.excepciones.TipoNoValidoException;

/*
 * EL NUM SERIE ES UNICO POR ARMA
 * FORMATO: A-123
 */

public abstract class Arma {

	private String numeroSerie;
	private ArmaType tipo;

	// PATRON PARA UN NUMERO DE SERIE VALIDO

	private static final Pattern patternNumeroSerie = Pattern.compile("^[A-Z][-]?\\d{3}$");

	// CONSTRUCTORES

	public Arma(String numeroSerie, ArmaType tipo) throws NumeroSerieNoValidoException, TipoNoValidoException {
		setNumeroSerie(numeroSerie);
		setTipo(tipo);
	}

	// COMPRUEBA QUE EL NUMERO DE SERIE SEA VALIDO

	public boolean esValido(String numeroSerie) {
		return patternNumeroSerie.matcher(numeroSerie).matches();
	}

	// GETTERS

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public ArmaType getTipo() {
		return tipo;
	}
	
	// SETTERS

	public void setNumeroSerie(String numeroSerie) throws NumeroSerieNoValidoException {
		if (!esValido(numeroSerie)) {
			throw new NumeroSerieNoValidoException();
		} else {
			this.numeroSerie = numeroSerie;
		}
	}

	public void setTipo(ArmaType tipo) throws TipoNoValidoException {
		if (tipo == null) {
			throw new TipoNoValidoException();
		} else {
			this.tipo = tipo;
		}
	}

	// TO STRING

	public String toString() {
		return "Arma [Número de serie: " + numeroSerie + ",  Tipo:" + tipo + "]";
	}


}
