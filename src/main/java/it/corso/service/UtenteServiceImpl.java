package it.corso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.CorsoDao;

import it.corso.dao.RuoloDao;
import it.corso.dao.UtenteDao;
import it.corso.dto.UtenteDto;
import it.corso.dto.CorsoDto;
import it.corso.dto.UtenteLoginRequestDto;
import it.corso.dto.UtenteSignupDto;
import it.corso.dto.UtenteUpdateDto;
import it.corso.model.Ruolo;
import it.corso.model.Utente;
import it.corso.model.Corso;

@Service
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	public UtenteDao utenteDao;
	
	@Autowired
	public RuoloDao ruoloDao;

	@Autowired
	public CorsoDao corsoDao;
	
	
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public void userSignup(UtenteSignupDto utenteSignupDto) {
		Utente user = new Utente();

		user.setFirstname(utenteSignupDto.getFirstname());
		user.setLastname(utenteSignupDto.getLastname());
		user.setEmail(utenteSignupDto.getEmail());		
		user.setPassword(DigestUtils.sha256Hex(utenteSignupDto.getPassword()));
		
		utenteDao.save(user);	
		
	}

	@Override
	public void userUpdate(UtenteUpdateDto utenteUpdateDto) {
		try {
			Optional<Utente> optionalUtente = utenteDao.findByEmail(utenteUpdateDto.getEmail());
			
			if(optionalUtente.isPresent()) {
				 Utente utente = optionalUtente.get();
				 utente.setFirstname(utenteUpdateDto.getFirstname());
				 utente.setLastname(utenteUpdateDto.getLastname());
				 
				 List<Ruolo> ruoliUtente = new ArrayList<>();
				 Optional<Ruolo> ruoloDb = ruoloDao.findById(utenteUpdateDto.getId());
				 
				 if(ruoloDb.isPresent()) {
					 Ruolo ruolo = ruoloDb.get();
					 ruolo.setId(utenteUpdateDto.getId());
			          
			         ruoliUtente.add(ruolo);
			         utente.setLista_ruoli(ruoliUtente);
				 }
			}
		}
		catch (Exception ex) {
			
		}
		
	}

	@Override
	public void userDelete(String email) {
		Optional<Utente> optional = utenteDao.findByEmail(email);
		
		if(optional.isPresent()) {
			utenteDao.delete(optional.get());
			
		}
		
	}

	@Override
	public Utente getUserByEmail(String email) {
		Optional<Utente> optional = utenteDao.findByEmail(email);
		
		if(optional.isPresent()) {
	        return optional.get();
		}
		
		return new Utente();
	}

	@Override
	public List<UtenteDto> getAllUser() {
		try {
			List<Utente> utente = (List<Utente>) utenteDao.findAll();
			List<UtenteDto> utentiDto = new ArrayList<>();
			
			utente.forEach(u -> utentiDto.add(modelMapper.map(u, UtenteDto.class)));
			return utentiDto;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
		
	}

	@Override
	public boolean existsUtenteByEmail(String email) {
		return utenteDao.existsByEmail(email);
	}

	@Override
	public boolean login(UtenteLoginRequestDto utenteLoginRequest) {
		Optional<Utente> optional = utenteDao.findByEmail(utenteLoginRequest.getEmail());
		
		String encrypted = DigestUtils.sha256Hex(utenteLoginRequest.getPassword());
		
		boolean pres = optional.isPresent();
		String s1 = optional.get().getPassword();
		String s2 = encrypted;
		boolean isEqual = s1.equals(s2);
		
		if(pres && isEqual) {
			return true;
		}
			
		else {
			return false;
		}
	}



	
	@Override
	public void addCourse(int userId, int courseId) {
		try {
			Optional<Utente> optionalUtente = utenteDao.findById(userId);
			Optional<Corso> optionalCorso = corsoDao.findById(courseId);
			
			if(optionalUtente.isPresent() && optionalCorso.isPresent()) {
				Corso corsoDb = optionalCorso.get();
				System.out.println("ciao");
				Utente utenteDb = optionalUtente.get();
				utenteDb.getListaCorsi().add(corsoDb);
				utenteDao.save(utenteDb);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
