package laCompagniaDelCodice.epicEnergy.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.exceptions.BadRequestException;
import laCompagniaDelCodice.epicEnergy.exceptions.UnauthorizedException;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteLoginPayload;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteLoginSuccessful;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteSavePayload;
import laCompagniaDelCodice.epicEnergy.services.UtenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UtenteService usersService;

	@Autowired
	JWTTools jwtTools;

	@Autowired
	PasswordEncoder bcrypt;
	@PostMapping("/registrazione")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUser(@RequestBody @Validated UtenteSavePayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		Utente created = usersService.create(body);

		return created;
	}

	@PostMapping("/login")
	public ResponseEntity<UtenteLoginSuccessful> login(@RequestBody UtenteLoginPayload body)
			throws UnauthorizedException {

		Utente user = usersService.findByEmail(body.getEmail());

		if (bcrypt.matches(body.getPassword(), user.getPassword())) {

			String token = jwtTools.createToken(user);

			UtenteLoginSuccessful loginAvvenuto = new UtenteLoginSuccessful(token);
			return new ResponseEntity<>(loginAvvenuto, HttpStatus.OK);

		} else {

			throw new UnauthorizedException("Credenziali non valide!");
		}
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUtente(@PathVariable UUID id) throws BadRequestException {
		usersService.findByIdAndDelete(id);

	}

}
