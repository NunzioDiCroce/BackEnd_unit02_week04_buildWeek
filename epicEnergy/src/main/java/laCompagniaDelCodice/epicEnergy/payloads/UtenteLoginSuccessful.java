package laCompagniaDelCodice.epicEnergy.payloads;

import laCompagniaDelCodice.epicEnergy.entities.Utente;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UtenteLoginSuccessful {
	private String token;
	private Utente utente;

	public UtenteLoginSuccessful(String token, Utente utente) {

		this.token = token;
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "UtenteLoginSuccessful [token=" + token + "]";
	}

}
