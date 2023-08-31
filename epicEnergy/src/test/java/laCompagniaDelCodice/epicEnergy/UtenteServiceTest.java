package laCompagniaDelCodice.epicEnergy;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import laCompagniaDelCodice.epicEnergy.entities.Utente;
import laCompagniaDelCodice.epicEnergy.payloads.UtenteSavePayload;
import laCompagniaDelCodice.epicEnergy.services.UtenteService;

// * * * * CLASSE PER TEST METODO CREATE DI UTENTESERVICE (CREAZIONE UTENTE) * * * *

@SpringBootTest
public class UtenteServiceTest {

	@MockBean // ANNOTAZIONE PER CREARE UNA SIMULAZIONE DEL BEAN IN SPRING
	private UtenteService utenteService;

	@Test
	public void testCreaUtente() {

		// DATI NECESSARI PER CREARE UN UTENTE
		UtenteSavePayload payload = new UtenteSavePayload("utenteTest", "passwordTest", "utenteTest@mail.com",
				"nomeTest", "cognomeTest", "AMMINISTRATORE");

		// DATI ATTESI UTENTE
		Utente nuovoUtente = new Utente("utenteTest", "passwordTest", "utenteTest@mail.com", "nomeTest", "cognomeTest",
				"AMMINISTRATORE");

		// DEFINIZIONE COMPORTAMENTO MOCK UTENTESERVICE
		when(utenteService.create(payload)).thenReturn(nuovoUtente);

		Utente utenteCreato = utenteService.create(payload);

	}

}
