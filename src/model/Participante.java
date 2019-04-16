package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Participante implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String cpf;
	private String celular;
	private String email;
	private LocalDate dtNascimento;
	private Plano plano;
	private LocalDate dtAssociacao;
	private String status;
	private Endereco endereco;
	
	public Participante() {}

	public Participante(String nome, String cpf, String celular, String email, LocalDate dtNascimento, Plano plano,
			LocalDate dtAssociacao, String status, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.celular = celular;
		this.email = email;
		this.dtNascimento = dtNascimento;
		this.plano = plano;
		this.dtAssociacao = dtAssociacao;
		this.status = status;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCelular() {
		return celular;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public LocalDate getDtAssociacao() {
		return dtAssociacao;
	}

	public void setDtAssociacao(LocalDate dtAssociacao) {
		this.dtAssociacao = dtAssociacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return getNome();
	}
}