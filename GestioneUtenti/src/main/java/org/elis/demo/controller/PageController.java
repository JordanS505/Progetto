package org.elis.demo.controller;

import org.elis.demo.model.Utente;
import org.elis.demo.service.def.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
	@Autowired
	private UtenteService service;
	
	@GetMapping("/nuovo")
	public String aggiungi(Model model) {
		return "aggiungi";
	}
	@GetMapping("/modifica/{id}")
	public String modifica(@PathVariable long id,Model model) {
		Utente u = service.findById(id);
		model.addAttribute("utente", u);
		return "modifica";
	}
	@GetMapping("/gestione")
	public String gestione(Model model) {
		return "gestioneUtenti";
	}
}
