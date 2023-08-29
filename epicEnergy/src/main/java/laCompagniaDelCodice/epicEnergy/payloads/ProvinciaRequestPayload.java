package laCompagniaDelCodice.epicEnergy.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProvinciaRequestPayload {
	private String sigla;
	private String provincia;
	private String Regione;

	public ProvinciaRequestPayload(String sigla, String provincia, String regione) {
		super();
		this.sigla = sigla;
		this.provincia = provincia;
		Regione = regione;
	}

	@Override
	public String toString() {
		return "ProvinciaRequestPayload [sigla=" + sigla + ", provincia=" + provincia + ", Regione=" + Regione + "]";
	}

}
