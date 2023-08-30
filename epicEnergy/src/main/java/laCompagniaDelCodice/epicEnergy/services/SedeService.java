package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Sede;
import laCompagniaDelCodice.epicEnergy.exceptions.ItemNotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.SedeUpdatePayload;
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
	public Sede save(Sede body) {
		Sede nuovaSede = new Sede(body.getVia(), body.getCivico(), body.getLocalita(), body.getCap(), body.getCliente(),
				body.getComune(), body.getTipoSede());
		return sedeRepository.save(nuovaSede);

	}

	// CERCA SEDI
	public List<Sede> findAll() {
		return sedeRepository.findAll();
	}

	// CERCA SEDE PER ID
	public Sede findById(UUID id) throws ItemNotFoundException {
		return sedeRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

	}

	// AGGIORNA SEDE
	public Sede findByIdAndUpdate(UUID id, SedeUpdatePayload body) throws ItemNotFoundException {
		Sede found = this.findById(id);

		found.setVia(body.getVia());
		found.setCivico(body.getCivico());
		found.setLocalita(body.getLocalita());
		found.setCap(body.getCap());
		found.setCliente(body.getCliente());
		found.setComune(body.getComune());
		found.setTipoSede(body.getTipoSede());

		return sedeRepository.save(found);

	}

	// ELIMINA SEDE
	public void findByIdAndDelete(UUID id) throws ItemNotFoundException {
		Sede found = this.findById(id);
		sedeRepository.delete(found);
	}

}
