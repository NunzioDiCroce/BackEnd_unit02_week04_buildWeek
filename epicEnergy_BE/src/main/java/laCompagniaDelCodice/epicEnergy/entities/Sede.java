package laCompagniaDelCodice.epicEnergy.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import laCompagniaDelCodice.epicEnergy.enums.TipoSede;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sedi")
public class Sede {

	@Id
	@GeneratedValue
	private UUID id;

	private String via;
	private int civico;
	private String localita;
	private String cap;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Comune comune;

	@Enumerated(EnumType.STRING)
	private TipoSede tipoSede;

	public Sede(String via, int civico, String localita, String cap, Cliente cliente, Comune comune,
			TipoSede tipoSede) {
		this.via = via;
		this.civico = civico;
		this.localita = localita;
		this.cap = cap;
		this.cliente = cliente;
		this.comune = comune;
		this.tipoSede = tipoSede;
	}

}
