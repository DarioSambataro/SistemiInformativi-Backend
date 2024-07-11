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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "corso")
public class Corso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_C")
	private int id;
	
	@Column(name = "Nome_Corso")
	private String name;
	
	@Column(name = "Descrizione_breve")
	private String shortDescription;
	
	@Column(name = "Descrizione_completa")
	private String completeDescription;
	
	@Column(name = "Durata")
	private Integer duration;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "FK_CA", referencedColumnName = "ID_CA")
	private Categoria categoria;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable
	(  
	   name = "utenti_corsi", joinColumns = @JoinColumn(name = "FK_CU", referencedColumnName = "ID_C"),
	   inverseJoinColumns = @JoinColumn(name = "FK_UC", referencedColumnName = "ID_U")
	)
	private List<Utente> listaUtenti = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getCompleteDescription() {
		return completeDescription;
	}

	public void setCompleteDescription(String completeDescription) {
		this.completeDescription = completeDescription;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Utente> getListaUtenti() {
		return listaUtenti;
	}

	public void setListaUtenti(List<Utente> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}
	
	public void setNomeCategoria(NomeCategoria nomeCategoria) {
		this.categoria.setNomeCategoria(nomeCategoria);
	}
	
}
