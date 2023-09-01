package laCompagniaDelCodice.epicEnergy.payloads;

import laCompagniaDelCodice.epicEnergy.entities.Cliente;
import laCompagniaDelCodice.epicEnergy.entities.Comune;
import laCompagniaDelCodice.epicEnergy.enums.TipoSede;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SedeSavePayload {

	private String via;
	private int civico;
	private String localita;
	private String cap;

	private Cliente cliente;

	private Comune comune;

	private TipoSede tipoSede;

}
