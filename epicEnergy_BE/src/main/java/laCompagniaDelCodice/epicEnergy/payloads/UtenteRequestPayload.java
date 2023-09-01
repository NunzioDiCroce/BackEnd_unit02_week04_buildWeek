package laCompagniaDelCodice.epicEnergy.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UtenteRequestPayload {
	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;
}
