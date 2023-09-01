package laCompagniaDelCodice.epicEnergy.controllers;

import java.util.UUID;

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

import laCompagniaDelCodice.epicEnergy.entities.Comune;
import laCompagniaDelCodice.epicEnergy.payloads.ComuneRequestPayload;
import laCompagniaDelCodice.epicEnergy.services.ComuneService;

@RestController
@RequestMapping("/comuni")
public class ComuneController {
	@Autowired
	private ComuneService comuneSrv;

	// POST COMUNE
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Comune saveComune(@RequestBody Comune body) {
		Comune created = comuneSrv.create(body);
		return created;
	}

	// GET COMUNI
	@GetMapping
	public Page<Comune> geComuni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return comuneSrv.find(page, size, sortBy);
	}

	// GET COMUNE DA ID
	@GetMapping("/{id}")
	public Comune getComuneById(@PathVariable UUID id) {
		return comuneSrv.findById(id);
	}

	// PUT COMUNE
	@PutMapping("/{comuneId}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Comune updateComune(@PathVariable UUID comuneId, @RequestBody ComuneRequestPayload body) {
		return comuneSrv.findByIdAndUpdate(comuneId, body);
	}

	// DELETE COMUNE
	@DeleteMapping("/{comuneId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public void deleteComune(@PathVariable UUID comuneId) {
		comuneSrv.findByIdAndDelete(comuneId);
	}
}
