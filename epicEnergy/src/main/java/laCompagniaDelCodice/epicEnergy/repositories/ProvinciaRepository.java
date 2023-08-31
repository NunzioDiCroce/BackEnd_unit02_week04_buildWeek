package laCompagniaDelCodice.epicEnergy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laCompagniaDelCodice.epicEnergy.entities.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String> {

}
