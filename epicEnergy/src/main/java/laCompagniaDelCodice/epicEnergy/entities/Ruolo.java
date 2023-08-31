package laCompagniaDelCodice.epicEnergy.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Ruoli")
public class Ruolo {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private Boolean ruoloOperatore;
	private Boolean ruoloAmministratore;

	public Ruolo(String nome, Boolean ruoloAmmnistratore, Boolean ruoloOperatore) {
		this.nome = nome;
		this.ruoloOperatore = ruoloOperatore;
		this.ruoloAmministratore = ruoloAmmnistratore;

	}

}
