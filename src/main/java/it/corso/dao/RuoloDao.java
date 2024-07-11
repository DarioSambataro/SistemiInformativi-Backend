package it.corso.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Ruolo;
import it.corso.model.Utente;

public interface RuoloDao extends CrudRepository<Utente, Integer>{
	Optional<Ruolo> findById(int id);

}
