package com.Loja.GamerHaven.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo tipo não pode estar vazio, ele é obrigatorio!!")
	@Size(min = 3, max = 100, message = "O atributo tipo deve conter no mínimo 03 e no máximo 100 caracters")
	private String tipo;

	@NotBlank(message = "o atributo descrição não pode estar vazio, ele é obrigatorio!!")
	@Size(min = 10, max = 1000, message = "O atributo descrição deve conterno mínimo 10 e no máximo 1000 caracters")
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Game> game;

	public Long getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Game> getGame() {
		return game;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setGame(List<Game> game) {
		this.game = game;
	}
	
	
}
