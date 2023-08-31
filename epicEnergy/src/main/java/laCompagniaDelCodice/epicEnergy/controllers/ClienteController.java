package laCompagniaDelCodice.epicEnergy.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import laCompagniaDelCodice.epicEnergy.entities.Cliente;
import laCompagniaDelCodice.epicEnergy.payloads.NewClientePayload;
import laCompagniaDelCodice.epicEnergy.services.ClienteService;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

//	@GetMapping
//	public List<Cliente> getAllClienti() {
//		return clienteService.getAllClienti();
//	}

	@GetMapping("/{id}")
	public Cliente getClienteById(@PathVariable UUID id) {
		return clienteService.getClienteByID(id);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteService.saveCliente(cliente);
	}

	@GetMapping
	public Page<Cliente> getClienti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return clienteService.find(page, size, sortBy);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Cliente updateCliente(@PathVariable UUID id, @RequestBody NewClientePayload nuovoCliente) {
		return clienteService.updateCliente(id, nuovoCliente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public void deleteCliente(@PathVariable UUID id) {
		clienteService.deleteCliente(id);
	}

	@GetMapping("/ordinato/nome")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Cliente> ordinaClientiPerNome() {
		return clienteService.ordinaPerNome();
	}

	@GetMapping("/ordinato/fatturato")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Cliente> ordinaClientiPerFatturatoAnnuale() {
		return clienteService.ordinaPerFatturatoAnnuale();
	}

	@GetMapping("/ordinato/data-inserimento")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Cliente> ordinaClientiPerDataInserimento() {
		return clienteService.ordinaPerDataInserimento();
	}

	@GetMapping("/ordinato/data-ultimo-contatto")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Cliente> ordinaClientiPerDataUltimoContatto() {
		return clienteService.ordinaPerDataUltimoContatto();
	}

	@GetMapping("/ordinato/sedi-legali")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Cliente> getClientiWithSediLegal() {
		return clienteService.findAllBySediLegalOrderBySediComuneProvinciaAsc();
	}

	@GetMapping("/filtro/fatturato")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Cliente> filtraClientiPerFatturatoAnnuale(@RequestParam BigDecimal fatturaAnnuale) {
		return clienteService.filtraPerFatturatoAnnuale(fatturaAnnuale);
	}

	@GetMapping("/filtro/data-inserimento")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Cliente> filtraClientiPerDataInserimento(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date dataInserimento) {
		return clienteService.filtraPerDataInserimento(dataInserimento);
	}

	@GetMapping("/filtro/data-ultimo-contatto")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Cliente> filtraClientiPerDataUltimoContatto(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date dataUltimoContatto) {
		return clienteService.filtraPerDataUltimoContatto(dataUltimoContatto);
	}

	@GetMapping("/filtro/nome")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Cliente> filtraClientiPerNome(@RequestParam String ragioneSociale) {
		return clienteService.filtraPerParteDelNome(ragioneSociale);
	}

}
