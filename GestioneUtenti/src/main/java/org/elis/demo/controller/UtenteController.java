package org.elis.demo.controller;

import java.util.List;

import org.elis.demo.dto.request.UtenteDTO;
import org.elis.demo.dto.response.UtenteResponse;
import org.elis.demo.mapper.UtenteMapper;
import org.elis.demo.model.Utente;
import org.elis.demo.service.def.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
public class UtenteController {
	
	private final UtenteService service;
	private final UtenteMapper mapper;
	@PostMapping("/cerca")
	public ResponseEntity<List<UtenteResponse>> cerca(@RequestBody UtenteDTO dto){
		if(dto.getNome().isBlank() && dto.getCognome().isEmpty() && dto.getCodiceFiscale().isBlank()) {
			List<Utente> u = service.findAll();
			List<UtenteResponse> utenti = mapper.fromUtenteToResponse(u);
			return ResponseEntity.ok(utenti);
		}
		if(!dto.getNome().isBlank() && dto.getCognome().isBlank() && dto.getCodiceFiscale().isBlank()) {
			List<Utente> u = service.findByNome(dto.getNome());
			List<UtenteResponse> utenti = mapper.fromUtenteToResponse(u);
			return ResponseEntity.ok(utenti);
		}
		if(dto.getNome().isBlank() && !dto.getCognome().isBlank() && dto.getCodiceFiscale().isBlank()) {
			List<Utente> u = service.findByCognome(dto.getCognome());
			List<UtenteResponse> utenti = mapper.fromUtenteToResponse(u);
			return ResponseEntity.ok(utenti);
		}
		if(!dto.getNome().isBlank() && !dto.getCognome().isBlank() && dto.getCodiceFiscale().isBlank()) {
			List<Utente> u = service.findByNomeAndCognome(dto.getNome(),dto.getCognome());
			List<UtenteResponse> utenti = mapper.fromUtenteToResponse(u);
			return ResponseEntity.ok(utenti);
		}
		if(dto.getNome().isBlank() && dto.getCognome().isBlank() && !dto.getCodiceFiscale().isBlank()) {
			List<Utente> u = service.findByCodiceFiscale(dto.getCodiceFiscale());
			List<UtenteResponse> utenti = mapper.fromUtenteToResponse(u);
			return ResponseEntity.ok(utenti);
		}
		if(!dto.getNome().isBlank() && !dto.getCognome().isBlank() && !dto.getCodiceFiscale().isBlank() && dto.getDataDiNascita()!=null) {
			List<Utente> u = service.findByAll(dto);
			List<UtenteResponse> utenti = mapper.fromUtenteToResponse(u);
			return ResponseEntity.ok(utenti);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/rimuovi/{id}")
	public ResponseEntity<Void> rimuovi(@PathVariable long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<Void> update(@PathVariable long id,@RequestBody UtenteDTO u){
		service.update(id,u);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/aggiungi")
	public ResponseEntity<Void> insert(@RequestBody UtenteDTO u){
		service.create(u);
		return ResponseEntity.ok().build();
	}
}
