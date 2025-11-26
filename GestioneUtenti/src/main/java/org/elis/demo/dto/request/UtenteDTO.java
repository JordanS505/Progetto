package org.elis.demo.dto.request;

import java.time.LocalDate;

import lombok.Data;
@Data
public class UtenteDTO {
	private String nome;
	
	private String cognome;
	
	private String codiceFiscale;
	
	private LocalDate dataDiNascita;
}
