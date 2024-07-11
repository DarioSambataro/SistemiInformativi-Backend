package it.corso.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.corso.dto.UtenteDto;
import it.corso.model.Utente;

public interface UtenteDao extends CrudRepository<Utente, Integer>{
	Optional<Utente> findByEmail(String email);
	//Optional<UtenteDto> findById(int id);
	boolean existsByEmail(String email);
	void save(UtenteDto utenteDb);
}
