package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Ruolo;
import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.exceptions.ItemNotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteSavePayload;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteUpdatePayload;
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
	public Ruolo save(UtenteSavePayload body) {
		Utente nuovoUtente = new Utente(body.getUsername(), body.getPassword(), body.getEmail(), body.getNome(),
				body.getCognome(), body.getRuolo());
		return utenteRepository.save(nuovoUtente);
	}

	// CERCA RUOLI
	public List<Utente> findAll() {
		return utenteRepository.findAll();
	}

	// CERCA RUOLO PER ID
	public Utente findById(UUID id) throws ItemNotFoundException {
		return utenteRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

	}

	// AGGIORNA RUOLO
	public Utente findByIdAndUpdate(UUID id, UtenteUpdatePayload body) throws ItemNotFoundException {
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
