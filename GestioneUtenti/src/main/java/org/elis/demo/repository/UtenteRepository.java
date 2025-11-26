package org.elis.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.elis.demo.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtenteRepository extends JpaRepository<Utente, Long>{
Optional<List<Utente>> findByNome(String nome);
Optional<List<Utente>> findByNomeAndCognome(String nome,String cognome);
Optional<List<Utente>> findByCodiceFiscale(String codiceFiscale);
Optional<List<Utente>> findByCognome(String cognome);
@Query("SELECT u FROM Utente u WHERE u.nome=:nome AND u.cognome=:cognome AND u.dataDiNascita=:dataDiNascita "
		+ "AND u.codiceFiscale=:cf")
Optional<List<Utente>> findByAll(@Param("nome") String nome,@Param("cognome") String cognome, 
		@Param("dataDiNascita") LocalDate data, @Param("cf") String cf);
}
