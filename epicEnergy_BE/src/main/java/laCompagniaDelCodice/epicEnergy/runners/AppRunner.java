package laCompagniaDelCodice.epicEnergy.runners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import laCompagniaDelCodice.epicEnergy.entities.Cliente;
import laCompagniaDelCodice.epicEnergy.entities.Comune;
import laCompagniaDelCodice.epicEnergy.entities.Fattura;
import laCompagniaDelCodice.epicEnergy.entities.Provincia;
import laCompagniaDelCodice.epicEnergy.entities.Ruolo;
import laCompagniaDelCodice.epicEnergy.entities.Sede;
import laCompagniaDelCodice.epicEnergy.enums.StatoFattura;
import laCompagniaDelCodice.epicEnergy.enums.TipoCliente;
import laCompagniaDelCodice.epicEnergy.enums.TipoSede;
import laCompagniaDelCodice.epicEnergy.payloads.ProvinciaRequestPayload;
import laCompagniaDelCodice.epicEnergy.payloads.RuoloSavePayload;
import laCompagniaDelCodice.epicEnergy.repositories.RuoloRepository;
import laCompagniaDelCodice.epicEnergy.services.ClienteService;
import laCompagniaDelCodice.epicEnergy.services.ComuneService;
import laCompagniaDelCodice.epicEnergy.services.FatturaService;
import laCompagniaDelCodice.epicEnergy.services.ProvinciaService;
import laCompagniaDelCodice.epicEnergy.services.RuoloService;
import laCompagniaDelCodice.epicEnergy.services.SedeService;

@Component
public class AppRunner implements CommandLineRunner {
	@Autowired
	ProvinciaService provinciaSrv;
	@Autowired
	ComuneService comuneSrv;
	@Autowired
	ClienteService clienteService;
	@Autowired
	SedeService sedeSrv;
	@Autowired
	FatturaService fatturaSrv;
	@Autowired
	RuoloService ruoloSrv;
	@Autowired
	RuoloRepository ruoloRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(Locale.ITALIAN);
		/* ESTRAGGO LE PROVINCE DAL FILE .csv */
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* PRENDO DALLA TABELLA DEL DB TUTTE LE PROVINCE E LE SALVO IN UNA LISTA */

		List<Provincia> provinceDalDb = new ArrayList<Provincia>();
		provinceDalDb = provinciaSrv.findNoPage();
		// provinceDalDb.forEach(pr -> System.err.println(pr));
		/* ESTRAGGO TUTTI I COMUNI DAL .csv */
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
				// System.err.println(nomeProvincia);
				/*
				 * PER OGNI COMUNE CHE LEGGO DAL .csv AD OGNI GIRO DEL WHILE, CONFRONTO LA SUA
				 * PROVINCIA CON TUTTE LE PROVINCE DELLA LISTA provinceDalDB
				 */
				provinceDalDb.forEach(pr -> {
					if (pr.getProvincia().equalsIgnoreCase(nomeProvincia)) {
						// System.err.println(pr);
						Comune nuovocomune = new Comune(codiProvincia, progressivoComuneStringa, denominazioneItaliano,
								nomeProvincia);
						nuovocomune.setProvincia(pr);
						comuneSrv.create(nuovocomune);
						return;
					}
				});

				progressivoComune++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* MI PRENDO TUTTI I COMUNI DAL DB */
		List<Comune> comuniDalDB = new ArrayList<Comune>();
		comuniDalDB = comuneSrv.findNoPage();
		// comuniDalDB.forEach(comune -> System.err.println(comune));
		for (int i = 0; i < 5; i++) {
			Cliente cliente = new Cliente();
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
			clienteService.saveCliente(cliente);

		}

		/* MI PRENDO TUTTI I CLIENTI DAL DB E LI SALVO IN UNA LIST */
		List<Cliente> clientiDalDB = new ArrayList<Cliente>();
		clientiDalDB = clienteService.findNoPage();
		// clientiDalDB.forEach(cliente -> System.err.println(cliente));

		System.err.println(clientiDalDB.size());
		/* ISTANZIO 20 SEDI */
		for (int i = 0; i < 5; i++) {
			Sede sede = new Sede();
			sede.setVia(faker.address().streetAddress(true));
			sede.setCivico(faker.number().numberBetween(1, 100));
			sede.setCap(faker.address().zipCode());
			sede.setLocalita("Italia");
			sede.setTipoSede(TipoSede.values()[faker.number().numberBetween(0, TipoSede.values().length)]);

			sede.setCliente(clientiDalDB.get(faker.number().numberBetween(0, clientiDalDB.size() - 1)));
			sede.setComune(comuniDalDB.get(faker.number().numberBetween(0, comuniDalDB.size() - 1)));
			sedeSrv.save(sede);
		}

		/* ISTANZIO 25 FATTURE */
		for (int i = 0; i < 25; i++) {
			Fattura fattura = new Fattura();
			/**/
			Date dataRandom = faker.date().past(30, TimeUnit.DAYS);
			// System.err.println(dataRandom);
			fattura.setData(dataRandom);
			int annoRandom = dataRandom.getYear() + 1900;
			// System.err.println(annoRandom);
			fattura.setAnno(annoRandom);

			/**/
			fattura.setAnno(faker.number().numberBetween(2020, 2023));
			fattura.setData(faker.date().past(30, TimeUnit.DAYS));
			fattura.setImporto(new BigDecimal(faker.number().randomDouble(2, 10, 1000)));
			fattura.setNumero(faker.number().numberBetween(100, 999));
			fattura.setStatoFattura(
					StatoFattura.values()[faker.number().numberBetween(0, StatoFattura.values().length)]);
			fattura.setCliente(clientiDalDB.get(faker.number().numberBetween(0, clientiDalDB.size() - 1)));
			fatturaSrv.saveFattura(fattura);
		}

		RuoloSavePayload ruolo1 = new RuoloSavePayload(true, false, "AMMINISTRATORE");
		RuoloSavePayload ruolo2 = new RuoloSavePayload(false, true, "OPERATORE");
		ruoloSrv.save(ruolo1);
		ruoloSrv.save(ruolo2);
		Ruolo ricerca = ruoloRepo.findByNome("AMMINISTRATORE");
		// System.err.println(ricerca);
	}

}
