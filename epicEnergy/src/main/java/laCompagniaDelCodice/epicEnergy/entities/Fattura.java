package laCompagniaDelCodice.epicEnergy.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import laCompagniaDelCodice.epicEnergy.enums.StatoFattura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Fatture")
public class Fattura {

	@Id
	@GeneratedValue
	private UUID id;

	private int anno;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	private Date data;
	private BigDecimal importo;
	private int numero;

	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;

	@ManyToOne
	private Cliente cliente;

}
