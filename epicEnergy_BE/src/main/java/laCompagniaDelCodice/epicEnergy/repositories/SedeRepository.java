package laCompagniaDelCodice.epicEnergy.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import laCompagniaDelCodice.epicEnergy.entities.Sede;

public interface SedeRepository extends JpaRepository<Sede, UUID> {

}
