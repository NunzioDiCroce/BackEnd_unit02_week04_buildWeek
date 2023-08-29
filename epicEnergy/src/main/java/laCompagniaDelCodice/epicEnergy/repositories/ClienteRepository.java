package laCompagniaDelCodice.epicEnergy.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laCompagniaDelCodice.epicEnergy.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	// METODI DI ORDINAMENTO
	List<Cliente> findAllByOrderByRagioneSocialeAsc();

	List<Cliente> findAllByOrderByFatturaAnnualeDesc();

	List<Cliente> findAllByOrderByDataInserimentoAsc();

	List<Cliente> findAllByOrderByDataUltimoContattoDesc();

	// List<Cliente> findAllByOrderByProvinciaSedeLegaleAsc();

	// METODI DI FILTRAGGIO
	List<Cliente> findByFatturaAnnuale(BigDecimal fatturaAnnuale);

	List<Cliente> findByDataInserimento(Date dataInserimento);

	List<Cliente> findByDataUltimoContatto(Date dataUltimoContatto);

	List<Cliente> findByRagioneSocialeContainingIgnoreCase(String ragioneSociale);
}
