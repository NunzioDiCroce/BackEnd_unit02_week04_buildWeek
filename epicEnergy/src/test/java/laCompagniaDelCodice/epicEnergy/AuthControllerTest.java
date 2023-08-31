package laCompagniaDelCodice.epicEnergy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.exceptions.UnauthorizedException;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteLoginPayload;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteLoginSuccessful;
import laCompagniaDelCodice.epicEnergy.security.AuthController;
import laCompagniaDelCodice.epicEnergy.security.JWTTools;
import laCompagniaDelCodice.epicEnergy.services.UtenteService;

//* * * * CLASSE PER TEST AUTHCONTROLLER (LOGIN UTENTE) * * * *

@SpringBootTest
public class AuthControllerTest {

	@MockBean // ANNOTAZIONE PER CREARE UNA SIMULAZIONE DEL BEAN IN SPRING
	private UtenteService utenteService;

	@MockBean // ANNOTAZIONE PER CREARE UNA SIMULAZIONE DEL BEAN IN SPRING
	private JWTTools jwtTools;

	@Autowired
	private AuthController authController;

	@Test
	public void testLoginSuccess() throws UnauthorizedException {

		// CREAZIONE PAYLOAD DI TEST
		UtenteLoginPayload utenteLoginPayload = new UtenteLoginPayload("utenteTest@mail.com", "passwordTest");

		// CREAZIONE UTENTE DI TEST
		Utente utente = new Utente("utenteTest", "passwordTest", "utenteTest@mail.com", "nomeTest", "cognomeTest",
				"AMMINISTRATORE");

		// CREAZIONE TOKEN DI TEST
		String token = "tokenTest";

		// DEFINIZIONE COMPORTAMENTO MOCK UTENTESERVICE E JWTTOOLS
		when(utenteService.findByEmail("utenteTest@mail.com")).thenReturn(utente);
		when(jwtTools.createToken(utente)).thenReturn(token);

		// ESECUZIONE LOGIN DI TEST
		ResponseEntity<UtenteLoginSuccessful> response = authController.login(utenteLoginPayload);
		UtenteLoginSuccessful loginSuccessful = response.getBody();

		assertNotNull(loginSuccessful); // TEST RISPOSTA CON LOGINSUCCESSFUL NON NULLO
		assertEquals(token, loginSuccessful.getToken()); // TEST UGUAGLIANZA TOKEN
	}

}
