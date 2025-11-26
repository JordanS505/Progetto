package org.elis.demo.junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.elis.demo.controller.UtenteController;
import org.elis.demo.dto.request.UtenteDTO;
import org.elis.demo.dto.response.UtenteResponse;
import org.elis.demo.mapper.UtenteMapper;
import org.elis.demo.model.Utente;
import org.elis.demo.service.def.UtenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class UtenteControllerTest {

    @Mock
    private UtenteService service;

    @Mock
    private UtenteMapper mapper;

    @InjectMocks
    private UtenteController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCerca_TuttiVuoti() {
        UtenteDTO dto = new UtenteDTO();
        dto.setNome("");
        dto.setCognome("");
        dto.setCodiceFiscale("");
        dto.setDataDiNascita(null);

        Utente u = new Utente();
        List<Utente> listaUtenti = List.of(u);
        List<UtenteResponse> listaResp = List.of(new UtenteResponse());

        when(service.findAll()).thenReturn(listaUtenti);
        when(mapper.fromUtenteToResponse(listaUtenti)).thenReturn(listaResp);

        ResponseEntity<List<UtenteResponse>> response = controller.cerca(dto);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaResp, response.getBody());
        verify(service).findAll();
        verify(mapper).fromUtenteToResponse(listaUtenti);
    }

    @Test
    void testCerca_PerNome() {
        UtenteDTO dto = new UtenteDTO();
        dto.setNome("Mario");
        dto.setCognome("");
        dto.setCodiceFiscale("");
        dto.setDataDiNascita(null);

        Utente u = new Utente();
        List<Utente> listaUtenti = List.of(u);
        List<UtenteResponse> listaResp = List.of(new UtenteResponse());

        when(service.findByNome("Mario")).thenReturn(listaUtenti);
        when(mapper.fromUtenteToResponse(listaUtenti)).thenReturn(listaResp);

        ResponseEntity<List<UtenteResponse>> response = controller.cerca(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaResp, response.getBody());
        verify(service).findByNome("Mario");
        verify(mapper).fromUtenteToResponse(listaUtenti);
    }

    @Test
    void testAggiungi() {
        UtenteDTO dto = new UtenteDTO();
        dto.setNome("Mario");
        dto.setCognome("Rossi");
        dto.setCodiceFiscale("RSSMRA80A01H501U");
        dto.setDataDiNascita(LocalDate.of(1980,1,1));

        ResponseEntity<Void> response = controller.insert(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service).create(dto);
    }

    
    @Test
    void testRimuovi() {
        long id = 1L;

        ResponseEntity<Void> response = controller.rimuovi(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service).delete(id);
    }

    @Test
    void testUpdate() {
        long id = 1L;
        UtenteDTO dto = new UtenteDTO();
        dto.setNome("Mario");
        dto.setCognome("Rossi");
        dto.setCodiceFiscale("RSSMRA80A01H501U");
        dto.setDataDiNascita(LocalDate.of(1980,1,1));

        ResponseEntity<Void> response = controller.update(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service).update(id, dto);
    }
}
