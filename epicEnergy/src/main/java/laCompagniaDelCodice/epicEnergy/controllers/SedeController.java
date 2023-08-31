package laCompagniaDelCodice.epicEnergy.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import laCompagniaDelCodice.epicEnergy.entities.Sede;
import laCompagniaDelCodice.epicEnergy.payloads.SedeUpdatePayload;
import laCompagniaDelCodice.epicEnergy.services.SedeService;

@RestController
@RequestMapping("/sedi")
public class SedeController {

	@Autowired
	private SedeService sedeService;

	// POST SEDE
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Sede saveSite(@RequestBody Sede body) {
		Sede created = sedeService.save(body);
		return created;
	}

	// GET SEDE
	@GetMapping
	public List<Sede> getSites() {
		return sedeService.findAll();
	}

	// GET SEDE BY ID
	@GetMapping("/{siteId}")
	public Sede getSiteById(@PathVariable UUID siteId) {
		return sedeService.findById(siteId);

	}

	// PUT SEDE
	@PutMapping("/{siteId}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Sede updateSite(@PathVariable UUID siteId, @RequestBody SedeUpdatePayload body) {
		return sedeService.findByIdAndUpdate(siteId, body);
	}

	// DELETE SEDE
	@DeleteMapping("/{siteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public void deleteSite(@PathVariable UUID siteId) {
		sedeService.findByIdAndDelete(siteId);
	}

}
