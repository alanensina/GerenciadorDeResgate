package controller;

import model.Participante;
import service.CadParticipanteService;

public class CadParticipanteController {
	private CadParticipanteService service = new CadParticipanteService();
	
	public CadParticipanteController() {}
	
	public void receberParticipante(Participante p1) {
		enviarParaService(p1);
	}
	
	public void enviarParaService(Participante p1) {
		service.salvarParticipante(p1);
	}

}
