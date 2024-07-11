package it.corso.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.corso.dto.CorsoDto;
import it.corso.model.Categoria;
import it.corso.model.Corso;
import it.corso.model.Utente;

public interface CorsoDao extends CrudRepository<Corso, Integer>{
	Optional<Corso> findById(CorsoDto corsoDto);
	//Optional<CorsoDto> findById(int id);
	
	Optional<Corso> findById(Integer id);
	List<Corso> findByCategoria(Categoria categoria);
}
