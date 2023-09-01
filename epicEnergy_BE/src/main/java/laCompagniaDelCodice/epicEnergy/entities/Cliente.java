package laCompagniaDelCodice.epicEnergy.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	private Date dataInserimento;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
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

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Sede> sedi;

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", ragioneSociale=" + ragioneSociale + ", partitaIva=" + partitaIva + ", email="
				+ email + ", dataInserimento=" + dataInserimento + ", dataUltimoContatto=" + dataUltimoContatto
				+ ", fatturaAnnuale=" + fatturaAnnuale + ", pec=" + pec + ", telefono=" + telefono + ", emailContatto="
				+ emailContatto + ", nomeContatto=" + nomeContatto + ", cognomeContatto=" + cognomeContatto
				+ ", telefonoContatto=" + telefonoContatto + ", tipoCliente=" + tipoCliente + "]";
	}


}
