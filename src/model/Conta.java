package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Conta implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private Participante participante;
	private double saldoNormal;
	private double saldoAdicional;
	private double saldoPortabilidade;
	private LocalDate ultimoResgate;
	
	public Conta() {}

	public Conta(Participante participante, double saldoNormal, double saldoAdicional, double saldoPortabilidade,
			LocalDate ultimoResgate) {
		this.participante = participante;
		this.saldoNormal = saldoNormal;
		this.saldoAdicional = saldoAdicional;
		this.saldoPortabilidade = saldoPortabilidade;
		this.ultimoResgate = ultimoResgate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public double getSaldoNormal() {
		return saldoNormal;
	}

	public void setSaldoNormal(double saldoNormal) {
		this.saldoNormal = saldoNormal;
	}

	public double getSaldoAdicional() {
		return saldoAdicional;
	}

	public void setSaldoAdicional(double saldoAdicional) {
		this.saldoAdicional = saldoAdicional;
	}

	public double getSaldoPortabilidade() {
		return saldoPortabilidade;
	}

	public void setSaldoPortabilidade(double saldoPortabilidade) {
		this.saldoPortabilidade = saldoPortabilidade;
	}

	public LocalDate getUltimoResgate() {
		return ultimoResgate;
	}

	public void setUltimoResgate(LocalDate ultimoResgate) {
		this.ultimoResgate = ultimoResgate;
	}

	@Override
	public String toString() {
		return getParticipante().getNome();
	}
	
	
}