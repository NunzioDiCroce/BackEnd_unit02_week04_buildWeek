package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {

	private final UtenteRepository utenteRepository;

	@Autowired
	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}

	// SALVA UTENTE
	public void save(Utente utente) {
		utenteRepository.save(utente);
		log.info("Utente con ID " + utente.getId() + " salvato con successo");

	}

	// CERCA UTENTI
	public List<Utente> findAll() {
		return utenteRepository.findAll();
	}

	// CERCA UTENTE PER ID
	public Utente findById(UUID id) throws ItemNotFoundException {
		return utenteRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

	}

}
