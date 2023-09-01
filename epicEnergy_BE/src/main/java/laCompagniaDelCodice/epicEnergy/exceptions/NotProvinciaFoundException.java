package laCompagniaDelCodice.epicEnergy.exceptions;

public class NotProvinciaFoundException extends RuntimeException {
	public NotProvinciaFoundException(String sigla) {
		super("Provincia con sigla: " + sigla + " non trovato");
	}

//	public NotProvinciaFoundException1(String message) {
//		super(message);
//	}
}
