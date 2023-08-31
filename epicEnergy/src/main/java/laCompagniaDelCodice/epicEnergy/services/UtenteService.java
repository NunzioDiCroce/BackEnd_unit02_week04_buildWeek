package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Ruolo;
import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.exceptions.BadRequestException;
import laCompagniaDelCodice.epicEnergy.exceptions.ItemNotFoundException;
import laCompagniaDelCodice.epicEnergy.exceptions.NotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteSavePayload;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteUpdatePayload;
import laCompagniaDelCodice.epicEnergy.repositories.RuoloRepository;
import laCompagniaDelCodice.epicEnergy.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {

	private final UtenteRepository utenteRepository;
	private final RuoloRepository ruoloRepository;

	@Autowired
	public UtenteService(UtenteRepository utenteRepository, RuoloRepository ruoloRepository) {
		this.utenteRepository = utenteRepository;
		this.ruoloRepository = ruoloRepository;
	}

	// SALVA UTENTE
//	public Utente saveIniziale(UtenteRequestPayload body) {
//		UtenteRequestPayload nuovoUtente = new UtenteRequestPayload(body.getUsername(), body.getPassword(), body.getEmail(), body.getNome(),
//				body.getCognome());
//		return utenteRepository.save(nuovoUtente);
//	}

	public Utente create(UtenteSavePayload body) {
		utenteRepository.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException("L'email è stata già utilizzata");
		});
		Ruolo ruolo = ruoloRepository.findByNome(body.getRuoloNome());
		Utente newUser = new Utente(body.getUsername(), body.getPassword(), body.getEmail(), body.getNome(),
				body.getCognome(), ruolo);
		return utenteRepository.save(newUser);
	}

	public Utente findByEmail(String email) {
		return utenteRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email" + email + "non trovato"));
	}

	// SALVA UTENTE
	public Utente save(UtenteSavePayload body) {
		Utente nuovoUtente = new Utente(body.getUsername(), body.getPassword(), body.getEmail(), body.getNome(),
				body.getCognome(), body.getRuoloNome());
		return utenteRepository.save(nuovoUtente);
	}

	// CERCA UTENTI
	public List<Utente> findAll() {
		return utenteRepository.findAll();
	}

	// CERCA UTENTE PER ID
	public Utente findById(UUID id) throws ItemNotFoundException {
		return utenteRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

	}

	// AGGIORNA UTENTE
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

	// ELIMINA UTENTE
	public void findByIdAndDelete(UUID id) throws ItemNotFoundException {
		Utente found = this.findById(id);
		utenteRepository.delete(found);
	}

}
