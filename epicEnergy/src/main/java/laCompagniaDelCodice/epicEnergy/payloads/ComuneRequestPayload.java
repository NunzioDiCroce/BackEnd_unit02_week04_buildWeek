package laCompagniaDelCodice.epicEnergy.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ComuneRequestPayload {
	private String codiceProvincia;
	private String progressivoComune;
	private String denominazione;
	private String nomeProvincia;

	// @ManyToOne
	// private Provincia provincia;

	public ComuneRequestPayload(String codiceProvincia, String progressivoComune, String denominazione
			, String nomeProvincia
	// , Provincia provincia
	) {

		this.codiceProvincia = codiceProvincia;
		this.progressivoComune = progressivoComune;
		this.denominazione = denominazione;
		this.nomeProvincia = nomeProvincia;
		// this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "ComuneRequestPayload [codiceProvincia=" + codiceProvincia + ", progressivoComune=" + progressivoComune
				+ ", denominazione=" + denominazione + ", nomeProvincia="
				// + nomeProvincia + ", provincia=" + provincia
				+ "]";
	}

}
