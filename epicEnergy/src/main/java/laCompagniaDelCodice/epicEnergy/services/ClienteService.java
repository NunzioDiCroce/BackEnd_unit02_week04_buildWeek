package laCompagniaDelCodice.epicEnergy.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import laCompagniaDelCodice.epicEnergy.entities.Cliente;
import laCompagniaDelCodice.epicEnergy.enums.TipoSede;
import laCompagniaDelCodice.epicEnergy.exceptions.NotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.NewClientePayload;
import laCompagniaDelCodice.epicEnergy.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	// -----------SALVATAGGIO CLIENTI
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	// -----------RICERCA LISTA COMPLETA CLIENTI
	public List<Cliente> getAllClienti() {
		return clienteRepository.findAll();
	}

	// -----------IMPAGINAZIONE GET CLIENTI
	public Page<Cliente> find(int page, int size, String sort) {
		Pageable pag = PageRequest.of(page, size, Sort.by(sort));
		return clienteRepository.findAll(pag);
	}

	// ------------RICERCA CLIENTE PER ID
	public Cliente getClienteByID(UUID id) {
		Optional<Cliente> found = clienteRepository.findById(id);
		return found.orElseThrow(() -> new NotFoundException("Cliente non trovato con ID " + id));
	}

	// ------------MODIFICA CLIENTE PER ID
	public Cliente updateCliente(UUID id, NewClientePayload body) {
		Cliente found = getClienteByID(id);
		found.setRagioneSociale(body.getRagioneSociale());
		found.setPartitaIva(body.getPartitaIva());
		found.setEmail(body.getEmail());
		found.setDataInserimento(body.getDataInserimento());
		found.setDataUltimoContatto(body.getDataUltimoContatto());
		found.setFatturaAnnuale(body.getFatturaAnnuale());
		found.setPec(body.getPec());
		found.setEmailContatto(body.getEmailContatto());
		found.setNomeContatto(body.getNomeContatto());
		found.setCognomeContatto(body.getCognomeContatto());
		found.setTelefonoContatto(body.getTelefonoContatto());

		return clienteRepository.save(found);
	}

	// ------------CANCELLAZIONE CLIENTE PER ID
	public void deleteCliente(UUID id) {
		Cliente found = getClienteByID(id);
		clienteRepository.delete(found);
	}

	// ------------ORDINA CLIENTE PER NOME
	public List<Cliente> ordinaPerNome() {
		return clienteRepository.findAllByOrderByRagioneSocialeAsc();
	}

	// ------------ORDINA CLIENTE PER FATTURATO ANNUALE
	public List<Cliente> ordinaPerFatturatoAnnuale() {
		return clienteRepository.findAllByOrderByFatturaAnnualeDesc();
	}

	public List<Cliente> ordinaPerDataInserimento() {
		return clienteRepository.findAllByOrderByDataInserimentoAsc();
	}

	// ------------ORDINA CLIENTE PER DATA DI ULTIMO CONTATTO
	public List<Cliente> ordinaPerDataUltimoContatto() {
		return clienteRepository.findAllByOrderByDataUltimoContattoDesc();
	}

	// ------------ORDINA CLIENTE PER PROVINCIA SEDE LEGALE
	public List<Cliente> findAllBySediLegalOrderBySediComuneProvinciaAsc() {
		return clienteRepository.findAllBySedi_TipoSedeOrderBySedi_Comune_ProvinciaAsc(TipoSede.LEGALE);
	}

	// ------------FILTRA CLIENTE PER FATTURATO ANNUALE
	public List<Cliente> filtraPerFatturatoAnnuale(BigDecimal fatturaAnnuale) {
		return clienteRepository.findByFatturaAnnuale(fatturaAnnuale);
	}

	// ------------FILTRA CLIENTE PER DATA D'INSERIMENTO
	public List<Cliente> filtraPerDataInserimento(Date dataInserimento) {
		return clienteRepository.findByDataInserimento(dataInserimento);
	}

	// ------------FILTRA CLIENTE PER DATA DI ULTIMO CONTATTO
	public List<Cliente> filtraPerDataUltimoContatto(Date dataUltimoContatto) {
		return clienteRepository.findByDataUltimoContatto(dataUltimoContatto);
	}

	// ------------FILTRA CLIENTE PER PARTE DEL NOME
	public List<Cliente> filtraPerParteDelNome(String ragioneSociale) {
		return clienteRepository.findByRagioneSocialeContainingIgnoreCase(ragioneSociale);
	}

	// ------------FIND TUTTI I CLIENTI SENZA PAGE
	public List<Cliente> findNoPage() {
		// (numero di pagina, numero di elementi per
		// pagina, nome del campo per cui sortare)
		return clienteRepository.findAll();
	}

}
