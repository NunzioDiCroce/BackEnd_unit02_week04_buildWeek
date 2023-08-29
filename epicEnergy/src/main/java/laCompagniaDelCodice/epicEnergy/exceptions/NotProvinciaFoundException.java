package laCompagniaDelCodice.epicEnergy.exceptions;

import java.util.UUID;

public class NotProvinciaFoundException extends RuntimeException {
	public NotProvinciaFoundException(UUID idComune) {
		super("Comune con id: " + idComune + " non trovato");
	}

	public NotProvinciaFoundException(String message) {
		super(message);
	}
}
