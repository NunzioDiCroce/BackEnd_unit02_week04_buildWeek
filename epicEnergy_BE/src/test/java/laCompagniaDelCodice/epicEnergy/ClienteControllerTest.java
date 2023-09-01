package laCompagniaDelCodice.epicEnergy;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import laCompagniaDelCodice.epicEnergy.controllers.ClienteController;
import laCompagniaDelCodice.epicEnergy.entities.Cliente;
import laCompagniaDelCodice.epicEnergy.enums.TipoCliente;
import laCompagniaDelCodice.epicEnergy.services.ClienteService;

//* * * * CLASSE PER TEST CREAZIONE CLIENTE * * * *

@SpringBootTest
public class ClienteControllerTest {

	@MockBean // ANNOTAZIONE PER CREARE UNA SIMULAZIONE DEL BEAN IN SPRING
	private ClienteService clienteService;

	@Test
	public void testCreateCliente() {

		ClienteController clienteController = new ClienteController();

		// CREAZIONE NUOVO CLIENTE
		UUID clienteId = UUID.randomUUID();
		Cliente nuovoCliente = new Cliente();
		nuovoCliente.setId(clienteId);
		nuovoCliente.setRagioneSociale("ragioneSocialeTest");
		nuovoCliente.setPartitaIva("partitaIvaTest");
		nuovoCliente.setEmail("testMail@mail.com");
		nuovoCliente.setDataInserimento(new Date());
		nuovoCliente.setDataUltimoContatto(new Date());
		nuovoCliente.setFatturaAnnuale(BigDecimal.valueOf(50000));
		nuovoCliente.setPec("testPec@pec.com");
		nuovoCliente.setTelefono("testTelefono");
		nuovoCliente.setEmailContatto("emailContattoTest@email.com");
		nuovoCliente.setNomeContatto("nomeContattoTest");
		nuovoCliente.setCognomeContatto("cognomeContattoTest");
		nuovoCliente.setTelefonoContatto("telefonoContattoTest");
		nuovoCliente.setTipoCliente(TipoCliente.SPA);
//		nuovoCliente.setSedi(null);

//		// DEFINIZIONE COMPORTAMENTO MOCK CLIENTESERVICE
		when(clienteService.saveCliente(any(Cliente.class))).thenReturn(nuovoCliente);

//		assertNotNull(clienteService.saveCliente(nuovoCliente));

		// CREAZIONE NUOVO CLIENTE PAYLOAD
//		NewClientePayload nuovoClientePayload = new NewClientePayload();
//		nuovoClientePayload.setRagioneSociale("ragioneSocialeTest");
//		nuovoClientePayload.setPartitaIva("partitaIvaTest");
//		nuovoClientePayload.setEmail("testMail@mail.com");
//		nuovoClientePayload.setDataInserimento(new Date());
//		nuovoClientePayload.setDataUltimoContatto(new Date());
//		nuovoClientePayload.setFatturaAnnuale(BigDecimal.valueOf(50000));
//		nuovoClientePayload.setPec("testPec@pec.com");
//		nuovoClientePayload.setTelefono("testTelefono");
//		nuovoClientePayload.setEmailContatto("emailContattoTest@email.com");
//		nuovoClientePayload.setNomeContatto("nomeContattoTest");
//		nuovoClientePayload.setCognomeContatto("cognomeContattoTest");
//		nuovoClientePayload.setTelefonoContatto("telefonoContattoTest");
//		nuovoClientePayload.setTipoCliente(TipoCliente.SPA);

		Cliente response = clienteController.createCliente(nuovoCliente);

//		assertEquals(HttpStatus.CREATED, response.getStatusCode());
//		assertEquals(clienteId, response.getId());
		assertNotNull(clienteController.createCliente(nuovoCliente));

	}

}
