package org.elis.demo.service.impl;

import java.util.List;

import org.elis.demo.dto.request.UtenteDTO;
import org.elis.demo.mapper.UtenteMapper;
import org.elis.demo.model.Utente;
import org.elis.demo.repository.UtenteRepository;
import org.elis.demo.service.def.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService{
	private final UtenteMapper mapper;
	@Autowired
	private UtenteRepository repo;
	
	@Override
	public List<Utente> findAll() {
		List<Utente> utenti= repo.findAll();
		return utenti;
	}

	@Override
	public List<Utente> findByNome(String nome) {
		List<Utente> utenti= repo.findByNome(nome).orElseThrow();
		return utenti;
	}

	@Override
	public List<Utente> findByCognome(String cognome) {
		List<Utente> utenti= repo.findByCognome(cognome).orElseThrow();
		return utenti;
	}

	@Override
	public List<Utente> findByNomeAndCognome(String nome, String cognome) {
		List<Utente> utenti= repo.findByNomeAndCognome(nome,cognome).orElseThrow();
		return utenti;
	}

	@Override
	public List<Utente> findByCodiceFiscale(String cf) {
		List<Utente> utente= repo.findByCodiceFiscale(cf).orElseThrow();
		return utente;
	}

	@Override
	public void create(UtenteDTO u) {
		repo.save(mapper.fromDTOToUtente(u));
		
	}

	@Override
	public void update(long id,UtenteDTO u) {
		Utente utente = repo.findById(id).orElseThrow();
		utente.setNome(u.getNome());
		utente.setCognome(u.getCognome());
		utente.setCodiceFiscale(u.getCodiceFiscale());
		utente.setDataDiNascita(u.getDataDiNascita());
		repo.save(utente);
	}

	@Override
	public void delete(long id) {
		Utente u = repo.findById(id).orElseThrow();
		repo.delete(u);
	}

	@Override
	public List<Utente> findByAll(UtenteDTO u) {
		List<Utente> utenti = repo.findByAll(u.getNome(), u.getCognome(), u.getDataDiNascita(), u.getCodiceFiscale()).orElseThrow();
		return utenti;
	}

	@Override
	public Utente findById(long id) {
		return repo.findById(id).orElseThrow();
	}
	
}
