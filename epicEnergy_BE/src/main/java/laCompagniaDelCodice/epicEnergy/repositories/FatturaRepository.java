package laCompagniaDelCodice.epicEnergy.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laCompagniaDelCodice.epicEnergy.entities.Cliente;
import laCompagniaDelCodice.epicEnergy.entities.Fattura;
import laCompagniaDelCodice.epicEnergy.enums.StatoFattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
	List<Fattura> findByCliente(Cliente cliente);

	List<Fattura> findByStatoFattura(StatoFattura stato);

	List<Fattura> findByData(Date data);

	List<Fattura> findByAnno(int anno);

	List<Fattura> findByImportoBetween(BigDecimal minImporto, BigDecimal maxImporto);
}
