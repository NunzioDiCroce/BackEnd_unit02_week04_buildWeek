package laCompagniaDelCodice.epicEnergy;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import laCompagniaDelCodice.epicEnergy.entities.Cliente;
import laCompagniaDelCodice.epicEnergy.entities.Fattura;
import laCompagniaDelCodice.epicEnergy.enums.StatoFattura;
import laCompagniaDelCodice.epicEnergy.enums.TipoCliente;
import laCompagniaDelCodice.epicEnergy.repositories.ClienteRepository;
import laCompagniaDelCodice.epicEnergy.repositories.FatturaRepository;

@Component
public class LucaRunner implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private FatturaRepository fatturaRepository;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();
		Cliente cliente = null;

		for (int i = 0; i < 30; i++) {
			cliente = new Cliente();
			cliente.setRagioneSociale(faker.company().name());
			cliente.setPartitaIva(faker.number().digits(11));
			cliente.setEmail(faker.internet().emailAddress());
			cliente.setDataInserimento(faker.date().past(365, TimeUnit.DAYS));
			cliente.setDataUltimoContatto(faker.date().past(30, TimeUnit.DAYS));
			cliente.setFatturaAnnuale(new BigDecimal(faker.number().randomDouble(2, 1000, 100000)));
			cliente.setPec(faker.internet().emailAddress());
			cliente.setTelefono(faker.phoneNumber().phoneNumber());
			cliente.setEmailContatto(faker.internet().emailAddress());
			cliente.setNomeContatto(faker.name().firstName());
			cliente.setCognomeContatto(faker.name().lastName());
			cliente.setTelefonoContatto(faker.phoneNumber().phoneNumber());
			cliente.setTipoCliente(TipoCliente.values()[faker.number().numberBetween(0, TipoCliente.values().length)]);
			clienteRepository.save(cliente);
		}

		for (int i = 0; i < 30; i++) {
			Fattura fattura = new Fattura();
			fattura.setAnno(faker.number().numberBetween(2020, 2023));
			fattura.setData(faker.date().past(30, TimeUnit.DAYS));
			fattura.setImporto(new BigDecimal(faker.number().randomDouble(2, 10, 1000)));
			fattura.setNumero(faker.number().numberBetween(100, 999));
			fattura.setStatoFattura(
					StatoFattura.values()[faker.number().numberBetween(0, StatoFattura.values().length - 1)]);
			fattura.setCliente(cliente);
			fatturaRepository.save(fattura);
		}
	}
}
