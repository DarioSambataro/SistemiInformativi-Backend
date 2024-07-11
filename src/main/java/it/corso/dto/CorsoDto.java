package it.corso.dto;

import it.corso.model.Categoria;

public class CorsoDto {
	private int id;
	private String name;
	private String shortDescription;
	private String completeDescription;
	
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
	
	
}
