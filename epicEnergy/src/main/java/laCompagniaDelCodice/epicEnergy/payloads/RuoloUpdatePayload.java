package laCompagniaDelCodice.epicEnergy.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RuoloUpdatePayload {

	private Boolean ruoloOperatore;
	private Boolean ruoloAmministratore;

}
