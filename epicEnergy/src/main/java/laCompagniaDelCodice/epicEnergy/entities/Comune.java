package laCompagniaDelCodice.epicEnergy.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Comuni")
public class Comune {

	@Id
	@GeneratedValue
	private UUID id;

	private String codiceProvincia;
	private int progressivoComune;
	private String denominazione;
	private String nomeProvincia;

	@ManyToOne
	private Provincia provincia;

}