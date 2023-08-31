package laCompagniaDelCodice.epicEnergy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Provincia;
import laCompagniaDelCodice.epicEnergy.exceptions.NotProvinciaFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.ProvinciaRequestPayload;
import laCompagniaDelCodice.epicEnergy.repositories.ProvinciaRepository;

@Service
public class ProvinciaService {
	private final ProvinciaRepository provinciaRepo;

	@Autowired
	public ProvinciaService(ProvinciaRepository provinciaRepo) {

		this.provinciaRepo = provinciaRepo;
	}

	public Provincia create(ProvinciaRequestPayload body) {
		// check if email already in use
//		utenteRepo.findByEmail(body.getEmail()).ifPresent(user -> {
//			throw new BadRequestException("L'email è già stata utilizzata");
//		});
		Provincia nuovaProvincia = new Provincia(body.getSigla(), body.getProvincia(), body.getRegione());
		return provinciaRepo.save(nuovaProvincia);
	}

	public Page<Provincia> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return provinciaRepo.findAll(pageable);
	}

	public List<Provincia> findNoPage() {
		// (numero di pagina, numero di elementi per
		// pagina, nome del campo per cui sortare)
		return provinciaRepo.findAll();
	}

	public Provincia findById(String sigla) throws NotProvinciaFoundException {
		return provinciaRepo.findById(sigla).orElseThrow(() -> new NotProvinciaFoundException(sigla));
	}

	public Provincia findByIdAndUpdate(String sigla, ProvinciaRequestPayload body) throws NotProvinciaFoundException {
		Provincia found = this.findById(sigla);
		found.setProvincia(body.getProvincia());
		found.setRegione(body.getRegione());
		found.setSigla(body.getSigla());

		return provinciaRepo.save(found);
	}

	public void findByIdAndDelete(String sigla) throws NotProvinciaFoundException {
		Provincia found = this.findById(sigla);
		provinciaRepo.delete(found);
	}
}
