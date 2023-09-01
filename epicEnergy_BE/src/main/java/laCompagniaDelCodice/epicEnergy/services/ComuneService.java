package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Comune;
import laCompagniaDelCodice.epicEnergy.exceptions.NotComuneFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.ComuneRequestPayload;
import laCompagniaDelCodice.epicEnergy.repositories.ComuneRepository;

@Service
public class ComuneService {
	private final ComuneRepository comuneRepo;

	@Autowired
	public ComuneService(ComuneRepository comuneRepo) {

		this.comuneRepo = comuneRepo;
	}

	public Comune create(Comune nuovocomune2) {
		// check if email already in use
//		utenteRepo.findByEmail(body.getEmail()).ifPresent(user -> {
//			throw new BadRequestException("L'email è già stata utilizzata");
//		});
		Comune nuovoComune = new Comune(nuovocomune2.getCodiceProvincia(), nuovocomune2.getProgressivoComune(),
				nuovocomune2.getDenominazione(), nuovocomune2.getNomeProvincia());
		nuovoComune.setProvincia(nuovocomune2.getProvincia());
		return comuneRepo.save(nuovoComune);
	}

	public Page<Comune> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort)); // (numero di pagina, numero di elementi per
																		// pagina, nome del campo per cui sortare)
		return comuneRepo.findAll(pageable);
	}

	public List<Comune> findNoPage() {
		// (numero di pagina, numero di elementi per
		// pagina, nome del campo per cui sortare)
		return comuneRepo.findAll();
	}

	public Comune findById(UUID id) throws NotComuneFoundException {
		return comuneRepo.findById(id).orElseThrow(() -> new NotComuneFoundException(id));
	}

	public Comune findByIdAndUpdate(UUID id, ComuneRequestPayload body) throws NotComuneFoundException {
		Comune found = this.findById(id);
		found.setCodiceProvincia(body.getCodiceProvincia());
		found.setDenominazione(body.getDenominazione());
		// found.setNomeProvincia(body.getNomeProvincia());
		found.setProgressivoComune(body.getProgressivoComune());
		// found.setProvincia(body.getProvincia());

		return comuneRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotComuneFoundException {
		Comune found = this.findById(id);
		comuneRepo.delete(found);
	}

}
