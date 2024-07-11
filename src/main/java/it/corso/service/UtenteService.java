package it.corso.service;

import java.util.List;

import it.corso.dto.UtenteDto;
import it.corso.dto.UtenteLoginRequestDto;
import it.corso.dto.UtenteSignupDto;
import it.corso.dto.UtenteUpdateDto;
import it.corso.model.Utente;

public interface UtenteService {

	void userSignup(UtenteSignupDto userdDto);

	void userUpdate(UtenteUpdateDto userUpdateDto);

	void userDelete(String email);

	Utente getUserByEmail(String email);

	List<UtenteDto> getAllUser();

	boolean existsUtenteByEmail(String email);
	
	boolean login(UtenteLoginRequestDto userLoginRequestDto);
	
	void addCourse(int id1, int id2);
}
