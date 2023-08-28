package laCompagniaDelCodice.epicEnergy.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import laCompagniaDelCodice.epicEnergy.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Clienti")
public class Cliente {

	@Id
	@GeneratedValue
	private UUID id;

	private String ragioneSociale;
	private String partitaIva;
	private String email;
	private Date dataInserimento;
	private Date dataUltimoContatto;
	private BigDecimal fatturaAnnuale;
	private String pec;
	private String telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;

	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

}