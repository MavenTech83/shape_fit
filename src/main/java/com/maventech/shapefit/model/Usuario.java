package com.maventech.shapefit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "tb_usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 200)
	@NotBlank(message = "O Atributo Nome é Obrigatório!")
	private String nome;
	
	@Column(length = 250)
	@NotBlank(message = "O Atributo Email é Obrigatório!")
	private String email;
	
	@Column(length = 100)
	@NotBlank(message = "O Atributo Senha é Obrigatório!")
	@Size(min = 6, max = 25, message = "A Senha deve ter no mínimo 6 e no máximo 25 caracteres")
	private String senha;
	
	@Column(length = 5000)
	@Size(max = 5000, message = "O link da foto não pode ser maior que 5000 caracteres")
	private String foto;
	
	@Column(columnDefinition = "DECIMAL(5, 2)")
	@NotNull(message = "O Atributo Peso é Obrigatório!")
	private Double peso;
	
	@Column(columnDefinition = "DECIMAL(5, 2)")
	@NotNull(message = "O Atributo Altura é Obrigatório!")
	private Double altura;

	@Column(columnDefinition = "DECIMAL(5, 2)")
	private Double imc;

	
	
	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	
}
