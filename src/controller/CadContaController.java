package controller;

import model.Conta;
import service.CadContaService;

public class CadContaController {
	private CadContaService service = new CadContaService();

	public CadContaController(){}
	
	public void receberConta(Conta c1) {
		enviarParaService(c1);
	}
	
	public void enviarParaService(Conta c1) {
		service.salvarConta(c1);
	}
	
}
