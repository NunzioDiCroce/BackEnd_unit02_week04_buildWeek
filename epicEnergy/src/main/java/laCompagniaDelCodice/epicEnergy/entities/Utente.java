package laCompagniaDelCodice.epicEnergy.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	private List<Ruolo> ruolo;

}
