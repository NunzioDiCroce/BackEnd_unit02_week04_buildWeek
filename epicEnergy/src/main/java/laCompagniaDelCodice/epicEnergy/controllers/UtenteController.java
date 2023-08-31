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

import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteSavePayload;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteUpdatePayload;
import laCompagniaDelCodice.epicEnergy.services.UtenteService;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	// POST UTENTE
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Utente saveUser(@RequestBody UtenteSavePayload body) {
		Utente created = utenteService.save(body);
		return created;
	}

	// GET UTENTI
	@GetMapping
	public List<Utente> getUsers() {
		return utenteService.findAll();
	}

	// GET UTENTE BY ID
	@GetMapping("/{userId}")
	public Utente getUserById(@PathVariable UUID userId) {
		return utenteService.findById(userId);

	}

	// PUT UTENTE
	@PutMapping("/{userId}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Utente updateUser(@PathVariable UUID userId, @RequestBody UtenteUpdatePayload body) {
		return utenteService.findByIdAndUpdate(userId, body);
	}

	// DELETE UTENTE
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) {
		utenteService.findByIdAndDelete(userId);
	}

}
