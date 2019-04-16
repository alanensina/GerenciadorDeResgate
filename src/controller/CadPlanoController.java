package controller;

import model.Plano;
import service.CadPlanoService;

public class CadPlanoController {

	private static CadPlanoService service = new CadPlanoService();
	
	public CadPlanoController() {}
	
	public void receberPlano(Plano plano) {
		enviarProService(plano);
	}
	
	public void enviarProService(Plano plano) {
		service.salvarPlano(plano);
	}
}