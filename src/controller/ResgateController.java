package controller;

import java.time.LocalDate;

import model.Conta;
import service.ResgateService;

public class ResgateController {

	private ResgateService service = new ResgateService();
	
	public ResgateController() {}
	
	
	public void receberConta(Conta c1, LocalDate dataResgate, String pagamento) {
		enviarParaOService(c1, dataResgate, pagamento);
	}
	
	public void enviarParaOService(Conta c1, LocalDate dataResgate, String pagamento) {
		service.validarResgateTotal(c1, dataResgate, pagamento);
	}
	
	public void receberResgateParcial(Conta c1, LocalDate dataSolicitacao, double valor, String tipo) {
		service.validarResgateParcial(c1, dataSolicitacao, valor, tipo);
	}
	
	
}
