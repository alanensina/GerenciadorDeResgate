package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Plano implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String cnpj;
	private Endereco endereco;
	private LocalDate dtFundacao;
	
	public Plano() {}

	public Plano(String nome, String cnpj, Endereco endereco, LocalDate dtFundacao) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.dtFundacao = dtFundacao;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LocalDate getDtFundacao() {
		return dtFundacao;
	}

	public void setDtFundacao(LocalDate dtFundacao) {
		this.dtFundacao = dtFundacao;
	}

	@Override
	public String toString() {
		return getNome();
	}
	
	
}