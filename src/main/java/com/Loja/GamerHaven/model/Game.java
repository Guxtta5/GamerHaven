package com.Loja.GamerHaven.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_games")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo nome do game não pode estar vazio, ele é obrigatorio!!")
	@Size(min = 5, max = 150, message = "O atributo nome do game deve conter no mínimo 05 e no máximo 150 caracters")
	private String nomeDoGame;

	@NotBlank(message = "O atributo desenvolvedora não pode estar vazio, ele é obrigatorio!")
	@Size(min = 5, max = 100, message = "O atributo desenvolvedora deve conter no mínomo 05 e no máximo 100 caracters")
	private String desenvolvedora;

	@NotBlank(message = "O atributo publicadora não pode esar vazio, ele é obrigatorio!!")
	@Size(min = 5, max = 100, message = "O atributo publicadora deve conter no mínimo 05 e no máximo 100 caracters")
	private String publicadora;

	@PositiveOrZero(message = "O valor não deve ser negativo")
	@NotNull(message = "O atributo Valor não pode estar vazio, ele é obrigatorio!")
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 6, fraction = 2)
	private BigDecimal valor;

	@ManyToOne
	@JsonIgnoreProperties("game")
	private Categoria categoria;
	
	

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public String getNomeDoGame() {
		return nomeDoGame;
	}

	public String getDesenvolvedora() {
		return desenvolvedora;
	}

	public String getPublicadora() {
		return publicadora;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomeDoGame(String nomeDoGame) {
		this.nomeDoGame = nomeDoGame;
	}

	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}

	public void setPublicadora(String publicadora) {
		this.publicadora = publicadora;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
