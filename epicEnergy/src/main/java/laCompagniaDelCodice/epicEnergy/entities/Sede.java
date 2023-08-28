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

	@Enumerated(EnumType.STRING)
	private TipoSede tipoSede;

}
