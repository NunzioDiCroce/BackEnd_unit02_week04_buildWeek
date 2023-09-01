package laCompagniaDelCodice.epicEnergy.payloads;

import laCompagniaDelCodice.epicEnergy.entities.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UtenteUpdatePayload {

	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;

	private Ruolo ruolo;

}
