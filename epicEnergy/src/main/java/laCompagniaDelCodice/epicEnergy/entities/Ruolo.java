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
@Table(name = "Ruoli")
public class Ruolo {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private Boolean ruoloOperatore;
	private Boolean ruoloAmministratore;

	@ManyToMany
	@JoinTable(name = "Utente_Ruolo")
	private List<Utente> utente;

	public Ruolo(Boolean ruoloOperatore, Boolean ruoloAmmnistratore, String nome) {
		this.ruoloOperatore = ruoloOperatore;
		this.ruoloAmministratore = ruoloAmmnistratore;
		this.nome = nome;

	}

}
