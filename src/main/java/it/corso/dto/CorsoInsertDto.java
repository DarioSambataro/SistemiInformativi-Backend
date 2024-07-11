package it.corso.dto;

import it.corso.model.Categoria;

public class CorsoInsertDto {
	private int id;
	private String name;
	private String shortDescription;
	private Categoria categoria;
	
//	public CategoriaDto getCategoriaDto() {
//		return categoriaDto;
//	}
//	public void setCategoriaDto(CategoriaDto categoriaDto) {
//		this.categoriaDto = categoriaDto;
//	}
	public int getCategoriaId() {
		return categoria.getId();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void setCategoriaId(int id) {
		this.categoria.setId(id);
	}
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
	
	
}
