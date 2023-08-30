package laCompagniaDelCodice.epicEnergy.payloads;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RuoloRequestModify {
	private UUID id;



	@Override
	public String toString() {
		return "RuoloRequestModify [id=" + id + "]";
	}

}
