package laCompagniaDelCodice.epicEnergy.runners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import laCompagniaDelCodice.epicEnergy.entities.Cliente;
import laCompagniaDelCodice.epicEnergy.entities.Comune;
import laCompagniaDelCodice.epicEnergy.entities.Fattura;
import laCompagniaDelCodice.epicEnergy.entities.Sede;
import laCompagniaDelCodice.epicEnergy.enums.StatoFattura;
import laCompagniaDelCodice.epicEnergy.enums.TipoCliente;
import laCompagniaDelCodice.epicEnergy.enums.TipoSede;
import laCompagniaDelCodice.epicEnergy.payloads.ProvinciaRequestPayload;
import laCompagniaDelCodice.epicEnergy.repositories.ComuneRepository;
import laCompagniaDelCodice.epicEnergy.repositories.SedeRepository;
import laCompagniaDelCodice.epicEnergy.services.ClienteService;
import laCompagniaDelCodice.epicEnergy.services.FatturaService;
import laCompagniaDelCodice.epicEnergy.services.ProvinciaService;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private FatturaService fatturaService;

	@Autowired
	private SedeRepository sedeRepo;

	@Autowired
	ProvinciaService provinciaSrv;

	@Autowired
	ComuneRepository comuneRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();
		Cliente cliente = null;

		/* IMPORTAZIONE DATI DA province-italiane.csv */
		String filePathProvince = new File("province-italiane.csv").getPath();
		boolean isFirstLineProvincia = true;

		try (BufferedReader br = new BufferedReader(new FileReader(filePathProvince))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (isFirstLineProvincia) {
					isFirstLineProvincia = false;
					continue;
				}

				String[] columnsP = line.split(";");
				String sigla = columnsP[0];
				String nomeProvincia = columnsP[1];
				String regione = columnsP[2];
				ProvinciaRequestPayload provincia = new ProvinciaRequestPayload(sigla, nomeProvincia, regione);
				provinciaSrv.create(provincia);
				// System.out.println("SIGLA: " + sigla + ", PROVINCIA: " + nomeProvincia + ",
				// REGIONE: " + regione);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* IMPORTAZIONE DEI DATI DA comuni-italiani.csv */
		String filePathComuni = new File("comuni-italiani.csv").getPath();
		boolean isFirstLineComune = true;
		int progressivoComune = 1;
		try (BufferedReader br = new BufferedReader(new FileReader(filePathComuni))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (isFirstLineComune) {
					isFirstLineComune = false;
					continue;
				}

				String[] columnsC = line.split(";");
				String codiProvincia = columnsC[0];
				String progressivoComuneStringa = String.format("%03d", progressivoComune);
				String denominazioneItaliano = columnsC[2];
				String nomeProvincia = columnsC[3];
				Comune nuovocomune = new Comune(codiProvincia, progressivoComuneStringa, denominazioneItaliano,
						nomeProvincia);
				comuneRepo.save(nuovocomune);
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
					cliente.setTipoCliente(
							TipoCliente.values()[faker.number().numberBetween(0, TipoCliente.values().length)]);
					clienteService.saveCliente(cliente);
				}
				for (int i = 0; i < 30; i++) {
					Sede sede = new Sede();
					sede.setVia(faker.address().streetAddress(true));
					sede.setCivico(faker.address().hashCode());
					sede.setCap(faker.address().zipCode());
					sede.setLocalita(faker.address().country());
					sede.setTipoSede(TipoSede.values()[faker.number().numberBetween(0, TipoSede.values().length)]);
					sede.setCliente(cliente);
					sede.setComune(nuovocomune);
					sedeRepo.save(sede);
				}
//				System.out.println("Codice Provincia (Storico)(1): " + codiProvincia + ", Progressivo del Comune: "
//						+ progressivoComune + ",Denominazione in italiano: " + denominazioneItaliano
//						+ ", Nome Provincia: " + nomeProvincia);
				progressivoComune++;
			}
		} catch (IOException e) {
			e.printStackTrace();
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
			fatturaService.saveFattura(fattura);
		}

	}
}
