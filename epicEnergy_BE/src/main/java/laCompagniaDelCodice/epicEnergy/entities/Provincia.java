package laCompagniaDelCodice.epicEnergy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Province")
public class Provincia {

//	private UUID id;
	@Id
//	@GeneratedValue
	private String sigla;
	private String provincia;
	private String Regione;

	public Provincia(String sigla, String provincia, String regione) {

		this.sigla = sigla;
		this.provincia = provincia;
		Regione = regione;
	}

	@Override
	public String toString() {
		return "Provincia [id=" /* + id + */ + ", sigla=" + sigla + ", provincia=" + provincia + ", Regione=" + Regione
				+ "]";
	}

}
