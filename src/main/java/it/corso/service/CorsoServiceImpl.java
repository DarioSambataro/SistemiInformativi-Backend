package it.corso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.CategoriaDao;
import it.corso.dao.CorsoDao;
import it.corso.dto.CorsoDto;
import it.corso.dto.CorsoInsertDto;
import it.corso.dto.CorsoUpdateDto;
import it.corso.dto.UtenteDto;
import it.corso.model.Categoria;
import it.corso.model.Corso;
import it.corso.model.Utente;

@Service
public class CorsoServiceImpl implements CorsoService{

	@Autowired
	public CorsoDao corsoDao;
	
	@Autowired
	public CategoriaDao categoriaDao;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public void updateCorso(CorsoUpdateDto corsoUpdateDto) {
		
		
	}

	@Override
	public void insertCorso(CorsoInsertDto corsoInsertDto) {
		Corso corso  = new Corso();

		corso.setName(corsoInsertDto.getName());
		corso.setShortDescription(corsoInsertDto.getShortDescription());
		//corso.setCategoria(corsoInsertDto.getCategoria());
		corsoDao.save(corso);
		
	}

	@Override
	public void corsoDelete(int id) {
		Optional<Corso> optional = corsoDao.findById(Integer.valueOf(id));
		
		if(optional.isPresent()) {
			corsoDao.delete(optional.get());
			
		}
		
	}

	@Override
	public CorsoDto getCorsoById(int id) {
		Optional<Corso> optional = corsoDao.findById(Integer.valueOf(id));
		
		if(optional.isPresent()) {
	       CorsoDto corsoDto = modelMapper.map(optional.get(), CorsoDto.class);
	       return corsoDto;
		}
		
		return new CorsoDto();
	}

	@Override
	public List<CorsoDto> getCorsi() {
		List<Corso> corso = (List<Corso>) corsoDao.findAll();
		List<CorsoDto> corsiDto = new ArrayList<>();
		
		corso.forEach(u -> corsiDto.add(modelMapper.map(u, CorsoDto.class)));
		
		return corsiDto;
	}

	@Override
	public boolean existsCorsoById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Corso> findCorsoByCategoria(int idCa){
	    Optional<Categoria> categoriaEntity = categoriaDao.findById(idCa);
	    if(categoriaEntity.isPresent()) {      
	      List<Corso> listaCorsi = corsoDao.findByCategoria(categoriaEntity.get());
	      return listaCorsi;
	    }
	    else {
	      return new ArrayList<>();
	    }
	    
	  }
	
	@Override
	public void deleteCorsoByCategory(int idCa) {
	    List<Corso> listaCorsi = findCorsoByCategoria(idCa);
	    for (Corso corso : listaCorsi) {
	      corsoDao.delete(corso);
	    }
	}

	
}
