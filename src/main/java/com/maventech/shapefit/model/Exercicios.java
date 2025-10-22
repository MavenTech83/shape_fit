package com.maventech.shapefit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

	@Entity
	@Table(name = "tb_exercicios")
	public class Exercicios {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@NotBlank(message = "O nome do exercício é obrigatório")
		@Size(min=3 , max = 100, message = "O nome deve conter entre 3 e 100 caracteres!")
		private String nome;
		
		@NotBlank(message = "A descrição é obrigatória!")
	    @Size(min = 10, max = 1000, message = "A descrição deve conter entre 10 e 1000 caracteres!")
	    private String descricao;
		
		//RELACIONAMENTO: Muitos Exercícios pertencem a uma Categoria//
	    @ManyToOne
	    @JsonIgnoreProperties("exercicios")
	    private Categoria categoria;
		
		// Getters e Setters//

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		
		
		
		
		
}
