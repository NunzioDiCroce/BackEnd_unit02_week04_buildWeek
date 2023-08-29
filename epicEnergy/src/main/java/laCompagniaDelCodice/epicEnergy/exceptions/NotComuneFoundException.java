package laCompagniaDelCodice.epicEnergy.exceptions;

import java.util.UUID;

public class NotComuneFoundException extends RuntimeException {
	public NotComuneFoundException(UUID idComune) {
		super("Comune con id: " + idComune + " non trovato");
	}

	public NotComuneFoundException(String message) {
		super(message);
	}
}
