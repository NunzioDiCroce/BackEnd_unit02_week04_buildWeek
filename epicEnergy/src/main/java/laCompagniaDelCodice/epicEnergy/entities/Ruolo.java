package laCompagniaDelCodice.epicEnergy.entities;

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
@Table(name = "Ruoli")
public class Ruolo {

	@Id
	@GeneratedValue
	private UUID id;

	private Boolean ruoloOperatore;
	private Boolean ruoloAmministratore;

	@ManyToMany
	private Utente utente;

}
