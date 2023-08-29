package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Sede;
import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.exceptions.ItemNotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.SedeSavePayload;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteUpdatePayload;
import laCompagniaDelCodice.epicEnergy.repositories.SedeRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SedeService {

	private final SedeRepository sedeRepository;

	@Autowired
	public SedeService(SedeRepository sedeRepository) {
		this.sedeRepository = sedeRepository;
	}

	// SALVA SEDE
	public Sede save(SedeSavePayload body) {
		Sede nuovaSede = new Sede(body.getVia(), body.getCivico(), body.getLocalita(), body.getCap(), body.getCliente(),
				body.getComune(), body.getTipoSede());
		return sedeRepository.save(nuovaSede);

	}

	// CERCA SEDI
	public List<Utente> findAll() {
		return utenteRepository.findAll();
	}

	// CERCA SEDE PER ID
	public Utente findById(UUID id) throws ItemNotFoundException {
		return utenteRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

	}

	// AGGIORNA SEDE
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

	// ELIMINA SEDE
	public void findByIdAndDelete(UUID id) throws ItemNotFoundException {
		Utente found = this.findById(id);
		utenteRepository.delete(found);
	}

}
