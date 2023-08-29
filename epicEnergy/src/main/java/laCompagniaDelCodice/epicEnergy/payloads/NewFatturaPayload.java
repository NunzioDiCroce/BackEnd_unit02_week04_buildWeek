package laCompagniaDelCodice.epicEnergy.payloads;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import laCompagniaDelCodice.epicEnergy.enums.StatoFattura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewFatturaPayload {

	private int anno;
	private Date data;
	private BigDecimal importo;
	private int numero;

	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;
}
