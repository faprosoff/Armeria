package edu.ort.t3.tpfinal.entidades.arma;

import edu.ort.t3.tpfinal.entidades.arma.ArmaFactory.ArmaType;
import edu.ort.t3.tpfinal.excepciones.NumeroSerieNoValidoException;
import edu.ort.t3.tpfinal.excepciones.TipoNoValidoException;

public class Rifle extends Arma {
	
	public Rifle(String numeroSerie, ArmaType tipo) throws NumeroSerieNoValidoException, TipoNoValidoException {
		super(numeroSerie, tipo);
	}

}