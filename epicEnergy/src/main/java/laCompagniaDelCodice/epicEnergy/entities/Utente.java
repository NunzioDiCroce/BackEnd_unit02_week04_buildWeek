package laCompagniaDelCodice.epicEnergy.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Utenti")
public class Utente {

	@Id
	@GeneratedValue
	private UUID id;

	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;

	@ManyToMany
	@JoinTable(name = "Utente_Ruolo")
	private List<Ruolo> ruolo;

	public Utente(String username, String password, String email, String nome, String cognome, List<Ruolo> ruolo) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.ruolo = ruolo;
	}

}
