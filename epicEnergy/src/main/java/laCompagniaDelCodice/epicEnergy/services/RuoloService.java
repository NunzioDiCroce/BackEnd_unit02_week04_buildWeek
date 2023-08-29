package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Ruolo;
import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.exceptions.ItemNotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.RuoloSavePayload;
import laCompagniaDelCodice.epicEnergy.repositories.RuoloRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RuoloService {

	private final RuoloRepository ruoloRepository;

	@Autowired
	public RuoloService(RuoloRepository ruoloRepository) {
		this.ruoloRepository = ruoloRepository;
	}

	// SALVA RUOLO
	public Ruolo save(RuoloSavePayload body) {
		Ruolo nuovoRuolo = new Ruolo(body.getRuoloOperatore(), body.getRuoloAmministratore());
		return ruoloRepository.save(nuovoRuolo);
	}

	// CERCA RUOLI
	public List<Ruolo> findAll() {
		return ruoloRepository.findAll();
	}

	// CERCA RUOLO PER ID
	public Ruolo findById(UUID id) throws ItemNotFoundException {
		return ruoloRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

	}

	// AGGIORNA RUOLO
	public Ruolo findByIdAndUpdate(UUID id, RuoloUpdatePayload body) throws ItemNotFoundException {
		Utente found = this.findById(id);

		found.setUsername(body.getUsername());
		found.setPassword(body.getPassword());
		found.setEmail(body.getEmail());
		found.setNome(body.getNome());
		found.setCognome(body.getCognome());
		found.setRuolo(body.getRuolo());

		return utenteRepository.save(found);

	}

	// ELIMNA RUOLO
	public void findByIdAndDelete(UUID id) throws ItemNotFoundException {
		Utente found = this.findById(id);
		utenteRepository.delete(found);
	}

}
