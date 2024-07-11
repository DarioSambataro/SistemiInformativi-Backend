package it.corso.dto;

import java.util.List;

import it.corso.model.Corso;
import it.corso.model.Ruolo;

public class UtenteDto {
	private int id;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private List<CorsoDto> listaCorsi;
	
	private List<Ruolo> listaRuoli;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CorsoDto> getListaCorsi() {
		return listaCorsi;
	}

	public void setListaCorsi(List<CorsoDto> listaCorsi) {
		this.listaCorsi = listaCorsi;
	}

	public List<Ruolo> getListaRuoli() {
		return listaRuoli;
	}

	public void setListaRuoli(List<Ruolo> listaRuoli) {
		this.listaRuoli = listaRuoli;
	}
	
	
}
