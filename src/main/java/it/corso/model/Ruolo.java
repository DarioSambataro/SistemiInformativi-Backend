package it.corso.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ruolo")
public class Ruolo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_G")
	private int id;
	
	@Column(name = "TIPOLOGIA")
	@Enumerated(EnumType.STRING)
	private NomeTipologia tipologia;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NomeTipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(NomeTipologia tipologia) {
		this.tipologia = tipologia;
	}
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable
	(  
	   name = "utente_ruolo", joinColumns = @JoinColumn(name = "FK_R", referencedColumnName = "ID_G"),
	   inverseJoinColumns = @JoinColumn(name = "FK_U", referencedColumnName = "ID_U")
	)
	private List<Utente> listaUtenti = new ArrayList<>();
}