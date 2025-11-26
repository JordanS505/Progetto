package org.elis.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.elis.demo.dto.request.UtenteDTO;
import org.elis.demo.dto.response.UtenteResponse;
import org.elis.demo.model.Utente;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapper {
	public List<UtenteResponse> fromUtenteToResponse(List<Utente> utenti){
		List<UtenteResponse> uResponse = new ArrayList<>();
		if(utenti!=null) {
			for(Utente u : utenti) {
				UtenteResponse uR = new UtenteResponse();
				uR.setId(u.getId());
				uR.setNome(u.getNome());
				uR.setCognome(u.getCognome());
				uR.setCodiceFiscale(u.getCodiceFiscale());
				uR.setDataDiNascita(u.getDataDiNascita());
				uResponse.add(uR);
			}
		}
		return uResponse;
	}
	public Utente fromDTOToUtente(UtenteDTO u) {
		Utente utente = new Utente();
		utente.setNome(u.getNome());
		utente.setCognome(u.getCognome());
		utente.setCodiceFiscale(u.getCodiceFiscale());
		utente.setDataDiNascita(u.getDataDiNascita());
		return utente;
	}
}
