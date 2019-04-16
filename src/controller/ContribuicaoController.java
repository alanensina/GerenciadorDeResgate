package controller;

import model.Conta;
import service.ContribuicaoService;

public class ContribuicaoController {
	private ContribuicaoService service = new ContribuicaoService();

	public ContribuicaoController() {}
	
	public void receberContribuicao(Conta c1) {
		enviarParaOService(c1);
	}
	
	public void enviarParaOService(Conta c1) {
		service.adicionarContribuicao(c1);
	}
}