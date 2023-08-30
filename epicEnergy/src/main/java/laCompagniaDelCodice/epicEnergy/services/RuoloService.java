package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Ruolo;
import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.exceptions.ItemNotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.RuoloRequestModify;
import laCompagniaDelCodice.epicEnergy.payloads.RuoloSavePayload;
import laCompagniaDelCodice.epicEnergy.payloads.RuoloUpdatePayload;
import laCompagniaDelCodice.epicEnergy.repositories.RuoloRepository;
import laCompagniaDelCodice.epicEnergy.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RuoloService {
	@Autowired
	UtenteService utenteSrv;

	@Autowired
	UtenteRepository utenteRepo;
	private final RuoloRepository ruoloRepository;

	@Autowired
	public RuoloService(RuoloRepository ruoloRepository) {
		this.ruoloRepository = ruoloRepository;
	}

	// ASSEGNAZIONE DI UN RUOLO AD UN UTENTE TRAMITE ID UTENTE

	public Utente assegnaRuolo(UUID utenteId, RuoloRequestModify body)
			throws ItemNotFoundException, IllegalStateException {
		Utente utenteAssegnoRuolo = utenteSrv.findById(utenteId);

		if (utenteAssegnoRuolo == null) {
			throw new IllegalStateException("Utente non trovato");
		} else {
			Ruolo ruolo = this.findById(body.getId());
			utenteAssegnoRuolo.setRuolo(ruolo);
			// dispositivo.setStatoDispositivo(StatoDispositivo.ASSEGNATO);

			return utenteRepo.save(utenteAssegnoRuolo);
		}

	}
	// SALVA RUOLO
	public Ruolo save(RuoloSavePayload body) {
		Ruolo nuovoRuolo = new Ruolo(body.getRuoloOperatore(), body.getRuoloAmministratore(), body.getNome());
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
		Ruolo found = this.findById(id);

		found.setRuoloOperatore(body.getRuoloOperatore());
		found.setRuoloAmministratore(body.getRuoloAmministratore());

		return ruoloRepository.save(found);

	}

	// ELIMINA RUOLO
	public void findByIdAndDelete(UUID id) throws ItemNotFoundException {
		Ruolo found = this.findById(id);
		ruoloRepository.delete(found);
	}

}
