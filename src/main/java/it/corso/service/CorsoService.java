package it.corso.service;

import java.util.List;

import it.corso.dto.CorsoDto;
import it.corso.dto.CorsoInsertDto;
import it.corso.dto.CorsoUpdateDto;
import it.corso.model.Categoria;
import it.corso.model.Corso;

public interface CorsoService {
	void updateCorso(CorsoUpdateDto corsoUpdateDto);
	
	void insertCorso(CorsoInsertDto corsoInsertDto);
	
	void corsoDelete(int id);
	
	CorsoDto getCorsoById(int id);
	
	
	List<CorsoDto> getCorsi();
	
	boolean existsCorsoById(int id);
	
	List<Corso> findCorsoByCategoria(int idCa);

	void deleteCorsoByCategory(int idCa);
	

}
