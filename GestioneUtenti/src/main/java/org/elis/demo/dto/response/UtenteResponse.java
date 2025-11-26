package org.elis.demo.dto.response;

import java.time.LocalDate;

import lombok.Data;
@Data
public class UtenteResponse {
	private long id;
	
	private String nome;
	
	private String cognome;
	
	private String codiceFiscale;
	
	private LocalDate dataDiNascita;
}
