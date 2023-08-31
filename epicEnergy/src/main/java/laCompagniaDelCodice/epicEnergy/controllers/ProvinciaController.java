package laCompagniaDelCodice.epicEnergy.controllers;

import laCompagniaDelCodice.epicEnergy.exceptions.InvalidInputException;
import laCompagniaDelCodice.epicEnergy.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import laCompagniaDelCodice.epicEnergy.entities.Provincia;
import laCompagniaDelCodice.epicEnergy.payloads.ProvinciaRequestPayload;
import laCompagniaDelCodice.epicEnergy.services.ProvinciaService;

@RestController
@RequestMapping("/province")
public class ProvinciaController {
	@Autowired
	private ProvinciaService provinciaSrv;

	// POST PROVINCIA
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Provincia saveProvincia(@RequestBody ProvinciaRequestPayload body) {
		if(body == null || body.getProvincia() == null){
			throw new InvalidInputException("Nome Provincia non valido");
		}else{
			Provincia created = provinciaSrv.create(body);
			return created;
		}
	}

	// GET PROVINCE
	@GetMapping
	public Page<Provincia> getProvince(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return provinciaSrv.find(page, size, sortBy);
	}

	// GET PROVINCIA DA ID
	@GetMapping("/{id}")
	public Provincia getProvinciaById(@PathVariable String sigla) {
		if (sigla == null){
			throw new NotFoundException("Provincia non trovata");
		}else {
			return provinciaSrv.findById(sigla);
		}
	}

	// PUT PROVINCIA
	@PutMapping("/{provinciaId}")
	public Provincia updateProvincia(@PathVariable String sigla, @RequestBody ProvinciaRequestPayload body) {
		if(sigla == null && body == null){
			throw new InvalidInputException("Input non valido");
		}else{
			return provinciaSrv.findByIdAndUpdate(sigla, body);
		}
	}

	// DELETE PROVINCIA
	@DeleteMapping("/{provinciaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProvincia(@PathVariable String sigla) {
		if (sigla == null){
			throw new InvalidInputException("Input non valido");
		}else {
			provinciaSrv.findByIdAndDelete(sigla);
		}
	}
}
