package laCompagniaDelCodice.epicEnergy.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public Ruolo saveRole(@RequestBody RuoloSavePayload body) {
		Ruolo created = ruoloService.save(body);
		return created;
	}

	// GET RUOLO
	@GetMapping("")
	public List<Ruolo> getRoles() {
		return ruoloService.findAll();
	}

	// GET RUOLO BY ID
	@GetMapping("/{roleId}")
	public Ruolo getRoleById(@PathVariable UUID roleId) {
		return ruoloService.findById(roleId);

	}

	// PUT RUOLO
	@PutMapping("/{roleId}")
	public Ruolo updateRole(@PathVariable UUID roleId, @RequestBody RuoloUpdatePayload body) {
		return ruoloService.findByIdAndUpdate(roleId, body);
	}

	// DELETE RUOLO
	@DeleteMapping("/{roleId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRole(@PathVariable UUID roleId) {
		ruoloService.findByIdAndDelete(roleId);
	}

}
