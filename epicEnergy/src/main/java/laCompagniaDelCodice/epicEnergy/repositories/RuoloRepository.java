package laCompagniaDelCodice.epicEnergy.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import laCompagniaDelCodice.epicEnergy.entities.Ruolo;

public interface RuoloRepository extends JpaRepository<Ruolo, UUID> {

}
