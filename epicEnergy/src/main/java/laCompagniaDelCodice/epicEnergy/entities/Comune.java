package laCompagniaDelCodice.epicEnergy.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private String progressivoComune;
	private String denominazione;
	private String nomeProvincia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sigla", referencedColumnName = "sigla")
	private Provincia provincia;

	@Override
	public String toString() {
		return "Comune [id=" + id + ", codiceProvincia=" + codiceProvincia + ", progressivoComune=" + progressivoComune
				+ ", denominazione=" + denominazione + ", nomeProvincia=" + nomeProvincia + ", provincia=" + provincia
				+ "]";
	}

//	public Comune(String codiceProvincia, String progressivoComune, String denominazione, String nomeProvincia,
//			Provincia provincia) {
//
//		this.codiceProvincia = codiceProvincia;
//		this.progressivoComune = progressivoComune;
//		this.denominazione = denominazione;
//		this.nomeProvincia = nomeProvincia;
//		this.provincia = provincia;
//	}

	public Comune(String codiceProvincia, String progressivoComune, String denominazione, String nomeProvincia) {
		super();
		this.codiceProvincia = codiceProvincia;
		this.progressivoComune = progressivoComune;
		this.denominazione = denominazione;
		this.nomeProvincia = nomeProvincia;
	}

}
