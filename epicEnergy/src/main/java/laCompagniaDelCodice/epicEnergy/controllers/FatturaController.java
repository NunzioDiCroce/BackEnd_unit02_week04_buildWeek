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
import laCompagniaDelCodice.epicEnergy.entities.Fattura;
import laCompagniaDelCodice.epicEnergy.enums.StatoFattura;
import laCompagniaDelCodice.epicEnergy.exceptions.NotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.NewFatturaPayload;
import laCompagniaDelCodice.epicEnergy.services.FatturaService;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

	@Autowired
	private FatturaService fatturaService;

//	@GetMapping
//	public List<Fattura> getAllFatture() {
//		return fatturaService.getAllFatture();
//	}

	@GetMapping("/{id}")
	public Fattura getFatturaById(@PathVariable UUID id) throws NotFoundException {
		return fatturaService.getFatturaByID(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Fattura createFattura(@RequestBody Fattura fattura) {
		return fatturaService.saveFattura(fattura);
	}

	@GetMapping
	public Page<Fattura> getFatture(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return fatturaService.find(page, size, sortBy);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public Fattura updateFattura(@PathVariable UUID id, @RequestBody NewFatturaPayload body) {
		return fatturaService.updateFattura(id, body);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFattura(@PathVariable UUID id) {
		fatturaService.delete(id);
	}

	@GetMapping("/filtro/cliente")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Fattura> filtraPerCliente(@RequestParam Cliente cliente) {
		return fatturaService.filtraPerCliente(cliente);
	}

	@GetMapping("/filtro/stato")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Fattura> filtraPerStato(@RequestParam StatoFattura stato) {
		return fatturaService.filtraPerStato(stato);
	}

	@GetMapping("/filtro/data")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Fattura> filtraPerData(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date data) {
		return fatturaService.filtraPerData(data);
	}

	@GetMapping("/filtro/anno")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Fattura> filtraPerAnno(@RequestParam int anno) {
		return fatturaService.filtraPerAnno(anno);
	}

	@GetMapping("/filtro/importo")
	@PreAuthorize("hasAuthority('AMMINISTRATORE')")
	public List<Fattura> filtraPerImporto(@RequestParam BigDecimal minImporto, @RequestParam BigDecimal maxImporto) {
		return fatturaService.filtraPerImporto(minImporto, maxImporto);
	}

}
