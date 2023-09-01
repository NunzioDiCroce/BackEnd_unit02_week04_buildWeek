package laCompagniaDelCodice.epicEnergy.exceptions;

public class RuoloNotFoundException extends RuntimeException {
	public RuoloNotFoundException(String sigla) {
		super("Ruolo come: " + sigla + " non trovato");
	}
}
