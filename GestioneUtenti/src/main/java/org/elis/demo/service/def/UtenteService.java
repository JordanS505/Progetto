package org.elis.demo.service.def;

import java.util.List;

import org.elis.demo.dto.request.UtenteDTO;
import org.elis.demo.model.Utente;

public interface UtenteService {
	List<Utente> findAll();
	List<Utente> findByNome(String nome);
	List<Utente> findByCognome(String cognome);
	List<Utente> findByNomeAndCognome(String nome, String cognome);
	List<Utente> findByCodiceFiscale(String cf);
	List<Utente> findByAll(UtenteDTO u);
	Utente findById(long id);
	void create(UtenteDTO u);
	void update(long id,UtenteDTO u);
	void delete(long id);
}
