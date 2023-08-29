package laCompagniaDelCodice.epicEnergy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteSavePayload;
import laCompagniaDelCodice.epicEnergy.services.UtenteService;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUser(@RequestBody UtenteSavePayload body) {
		Utente created = utenteService.save(body);
		return created;
	}

}
