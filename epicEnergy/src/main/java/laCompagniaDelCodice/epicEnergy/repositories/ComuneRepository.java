package laCompagniaDelCodice.epicEnergy.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laCompagniaDelCodice.epicEnergy.entities.Comune;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, UUID> {

}
