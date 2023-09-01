package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Ruolo;
import laCompagniaDelCodice.epicEnergy.exceptions.ItemNotFoundException;
import laCompagniaDelCodice.epicEnergy.exceptions.RuoloNotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.RuoloSavePayload;
import laCompagniaDelCodice.epicEnergy.payloads.RuoloUpdatePayload;
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
		Ruolo nuovoRuolo = new Ruolo(body.getNome(), body.getRuoloOperatore(), body.getRuoloAmministratore());
		return ruoloRepository.save(nuovoRuolo);
	}

	// CERCA RUOLI
	public List<Ruolo> findAll() {
		return ruoloRepository.findAll();
	}

	// CERCA RUOLO PER ID
	public Ruolo findById(String sigla) throws RuoloNotFoundException {
		return ruoloRepository.findById(sigla).orElseThrow(() -> new RuoloNotFoundException(sigla));
	}

	// AGGIORNA RUOLO
	public Ruolo findByIdAndUpdate(String nome, RuoloUpdatePayload body) throws ItemNotFoundException {
		Ruolo found = this.findById(nome);

		found.setRuoloOperatore(body.getRuoloOperatore());
		found.setRuoloAmministratore(body.getRuoloAmministratore());

		return ruoloRepository.save(found);

	}

	// ELIMINA RUOLO
	public void findByIdAndDelete(String id) throws ItemNotFoundException {
		Ruolo found = this.findById(id);
		ruoloRepository.delete(found);
	}

}
