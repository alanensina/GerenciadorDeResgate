package model;

public enum StatusParticipante {
	
	ATIVO("AT", "Ativo"), 
	CANCELADO("CAN", "Cancelado"),
	BENEFICIO("BEN", "Beneficio"),
	VINCULADO("VIN", "Vinculado"),
	SUSPENSO("SUS", "Suspenso");
	
	private String sigla;
	private String descricao;
	
	private StatusParticipante(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}