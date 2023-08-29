package laCompagniaDelCodice.epicEnergy.runners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import laCompagniaDelCodice.epicEnergy.payloads.ComuneRequestPayload;
import laCompagniaDelCodice.epicEnergy.payloads.ProvinciaRequestPayload;
import laCompagniaDelCodice.epicEnergy.services.ComuneService;
import laCompagniaDelCodice.epicEnergy.services.ProvinciaService;

@Component
public class SanteRunner implements CommandLineRunner {
	@Autowired
	ProvinciaService provinciaSrv;
	@Autowired
	ComuneService comuneSrv;
	@Override
	public void run(String... args) throws Exception {


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
				ComuneRequestPayload nuovocomune = new ComuneRequestPayload(codiProvincia, progressivoComuneStringa,
						denominazioneItaliano, nomeProvincia);
				comuneSrv.create(nuovocomune);
				System.out.println("Codice Provincia (Storico)(1): " + codiProvincia + ", Progressivo del Comune: "
						+ progressivoComune + ",Denominazione in italiano: " + denominazioneItaliano
						+ ", Nome Provincia: " + nomeProvincia);
				progressivoComune++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
