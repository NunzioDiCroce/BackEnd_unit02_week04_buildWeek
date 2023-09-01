package laCompagniaDelCodice.epicEnergy.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RuoloSavePayload {

	private Boolean ruoloOperatore;
	private Boolean ruoloAmministratore;
	private String nome;

}
