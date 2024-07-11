package it.corso.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinTable;
import jakarta.persistence.*;

@Entity
@Table(name = "utente")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_U")
	private int id;
	
	@Column(name = "Nome")
	private String firstname;
	
	@Column(name = "Cognome")
	private String lastname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	//esiste una relazione many to many tra utente e corso
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable
	(  
	   name = "utenti_corsi", joinColumns = @JoinColumn(name = "FK_UC", referencedColumnName = "ID_U"),
	   inverseJoinColumns = @JoinColumn(name = "FK_CU", referencedColumnName = "ID_C")
	)
	private List<Corso> listaCorsi = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable
	(  
	   name = "utente_ruolo", joinColumns = @JoinColumn(name = "FK_U", referencedColumnName = "ID_U"),
	   inverseJoinColumns = @JoinColumn(name = "FK_R", referencedColumnName = "ID_G")
	)
	private List<Ruolo> listaRuoli;
	
	public List<Corso> getListaCorsi() {
		return listaCorsi;
	}

	public void setListaCorsi(List<Corso> listaCorsi) {
		this.listaCorsi = listaCorsi;
	}

	public List<Ruolo> getListaRuoli() {
		return listaRuoli;
	}

	public void setLista_ruoli(List<Ruolo> listaRuoli) {
		this.listaRuoli = listaRuoli;
	}

	
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
