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
import laCompagniaDelCodice.epicEnergy.repositories.FatturaRepository;

@Component
public class LucaRunner implements CommandLineRunner {

	@Autowired
	private FatturaRepository fatturaRepository;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();

		for (int i = 0; i < 30; i++) {
			Fattura fattura = new Fattura();
			fattura.setAnno(faker.number().numberBetween(2020, 2023));
			fattura.setData(faker.date().past(30, TimeUnit.DAYS));
			fattura.setImporto(new BigDecimal(faker.number().randomDouble(2, 10, 1000)));
			fattura.setNumero(faker.number().numberBetween(100, 999));
			fattura.setStatoFattura(
					StatoFattura.values()[faker.number().numberBetween(0, StatoFattura.values().length - 1)]);
			fattura.setCliente(new Cliente(faker.name().fullName(), faker.address().fullAddress()));
			fatturaRepository.save(fattura);
		}
	}
}
