package laCompagniaDelCodice.epicEnergy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import laCompagniaDelCodice.epicEnergy.services.ClienteService;

//* * * * CLASSE PER TEST CREAZIONE CLIENTE * * * *

@SpringBootTest
public class ClienteControllerTest {

	@MockBean // ANNOTAZIONE PER CREARE UNA SIMULAZIONE DEL BEAN IN SPRING
	private ClienteService clienteService;

	@Test
	public void testCreateCliente() {

	}

}
