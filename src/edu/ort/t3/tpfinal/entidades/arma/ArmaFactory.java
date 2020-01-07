package edu.ort.t3.tpfinal.entidades.arma;

import edu.ort.t3.tpfinal.entidades.*;
import edu.ort.t3.tpfinal.excepciones.NumeroSerieNoValidoException;
import edu.ort.t3.tpfinal.excepciones.TipoNoValidoException;

public class ArmaFactory {

	public enum ArmaType {
		PISTOLA, ESCOPETA, CARABINA, RIFLE;
	}

	public Arma crearArma(String numeroSerie, ArmaType armaType)
			throws NumeroSerieNoValidoException, TipoNoValidoException {
		Arma instancia = null;
		switch (armaType) {
		case PISTOLA:
			instancia = new Pistola(numeroSerie, armaType);
			break;
		case ESCOPETA:
			instancia = new Escopeta(numeroSerie, armaType);
			break;
		case CARABINA:
			instancia = new Carabina(numeroSerie, armaType);
			break;
		case RIFLE:
			instancia = new Rifle(numeroSerie, armaType);
			break;
		}
		return instancia;
	}

}
