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
import laCompagniaDelCodice.epicEnergy.entities.Fattura;
import laCompagniaDelCodice.epicEnergy.enums.StatoFattura;
import laCompagniaDelCodice.epicEnergy.exceptions.NotFoundException;
import laCompagniaDelCodice.epicEnergy.payloads.NewFatturaPayload;
import laCompagniaDelCodice.epicEnergy.repositories.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	private FatturaRepository fattRep;

	// ----------SALVATAGGIO FATTURE
	public Fattura saveFattura(Fattura fattura) {
		return fattRep.save(fattura);
	}

	// ----------RICERCA LISTA FATTURE
	public List<Fattura> getAllFatture() {
		return fattRep.findAll();
	}

	// -----------IMPAGINAZIONE GET FATTURE
	public Page<Fattura> find(int page, int size, String sort) {
		Pageable pag = PageRequest.of(page, size, Sort.by(sort));
		return fattRep.findAll(pag);
	}

	// ----------RICERCA FATTURA PER ID
	public Fattura getFatturaByID(UUID id) {
		Optional<Fattura> found = fattRep.findById(id);
		return found.orElseThrow(() -> new NotFoundException("Fattura non trovata con id " + id));
	}

	// ----------MODIFICA FATTURA PER ID
	public Fattura updateFattura(UUID id, NewFatturaPayload body) {
		Fattura found = this.getFatturaByID(id);
		found.setAnno(body.getAnno());
		found.setData(body.getData());
		found.setImporto(body.getImporto());
		found.setNumero(body.getNumero());
		found.setStatoFattura(body.getStatoFattura());
		return fattRep.save(found);
	}

	// -----------ELIMINA FATTURA PER ID
	public void delete(UUID id) {
		Fattura found = this.getFatturaByID(id);
		fattRep.delete(found);
	}

	// -----------FILTRA FATTURA PER CLIENTE
	public List<Fattura> filtraPerCliente(Cliente cliente) {
		return fattRep.findByCliente(cliente);
	}

	// -----------FILTRA FATTURA PER STATO
	public List<Fattura> filtraPerStato(StatoFattura stato) {
		return fattRep.findByStatoFattura(stato);
	}

	// -----------FILTRA FATTURA PER DATA
	public List<Fattura> filtraPerData(Date data) {
		return fattRep.findByData(data);
	}

	// -----------FILTRA FATTURA PER ANNO
	public List<Fattura> filtraPerAnno(int anno) {
		return fattRep.findByAnno(anno);
	}

	// -----------FILTRA FATTURA PER IMPORTO
	public List<Fattura> filtraPerImporto(BigDecimal minImporto, BigDecimal maxImporto) {
		return fattRep.findByImportoBetween(minImporto, maxImporto);
	}
}
