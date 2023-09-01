package laCompagniaDelCodice.epicEnergy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Provincia saveProvincia(@RequestBody ProvinciaRequestPayload body) {
		Provincia created = provinciaSrv.create(body);
		return created;
	}

	// GET PROVINCE
	@GetMapping
	public Page<Provincia> getProvince(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "sigla") String sortBy) {
		return provinciaSrv.find(page, size, sortBy);
	}

	// GET PROVINCIA DA ID
	@GetMapping("/{sigla}")
	public Provincia getProvinciaById(@PathVariable String sigla) {
		return provinciaSrv.findById(sigla);
	}

	// PUT PROVINCIA
	@PutMapping("/{provinciaId}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Provincia updateProvincia(@PathVariable String sigla, @RequestBody ProvinciaRequestPayload body) {
		return provinciaSrv.findByIdAndUpdate(sigla, body);
	}

	// DELETE PROVINCIA
	@DeleteMapping("/{provinciaId}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProvincia(@PathVariable String sigla) {
		provinciaSrv.findByIdAndDelete(sigla);
	}
}
