package laCompagniaDelCodice.epicEnergy.payloads;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtenteSavePayload {
	@NotNull(message = "Username obbligatorio")
	private String username;
	@NotNull(message = "Password obbligatoria")
	private String password;
	@NotNull(message = "Email è obbligatoria")
	@Email(message = "Formato e-mail errato")
	private String email;
	@NotNull(message = "Nome obbligatorio")
	private String nome;
	@NotNull(message = "Cognome obbligatorio")
	private String cognome;
	@NotNull(message = "Ruolo obbligatorio")
	private String ruoloNome;

}
