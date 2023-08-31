package laCompagniaDelCodice.epicEnergy.controllers;

import java.util.List;

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

import laCompagniaDelCodice.epicEnergy.entities.Ruolo;
import laCompagniaDelCodice.epicEnergy.payloads.RuoloSavePayload;
import laCompagniaDelCodice.epicEnergy.payloads.RuoloUpdatePayload;
import laCompagniaDelCodice.epicEnergy.services.RuoloService;

@RestController
@RequestMapping("/ruoli")
public class RuoloController {

	@Autowired
	private RuoloService ruoloService;

	// POST RUOLO
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Ruolo saveRole(@RequestBody RuoloSavePayload body) {
		Ruolo created = ruoloService.save(body);
		return created;
	}

	// GET RUOLO
	@GetMapping
	public List<Ruolo> getRoles() {
		return ruoloService.findAll();
	}

	// GET RUOLO BY ID
	@GetMapping("/{ruolo}")
	public Ruolo getRoleById(@PathVariable String ruolo) {
		return ruoloService.findById(ruolo);

	}

	// PUT RUOLO
	@PutMapping("/{roleId}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Ruolo updateRole(@PathVariable String roleId, @RequestBody RuoloUpdatePayload body) {
		return ruoloService.findByIdAndUpdate(roleId, body);
	}

	// DELETE RUOLO
	@DeleteMapping("/{roleId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public void deleteRole(@PathVariable String roleId) {
		ruoloService.findByIdAndDelete(roleId);
	}

}
