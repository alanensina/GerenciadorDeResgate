package service;

import DAO.ParticipanteDAO;
import model.Participante;

public class CadParticipanteService {
	private ParticipanteDAO pdao = new ParticipanteDAO();

	public CadParticipanteService() {
	}

	public void salvarParticipante(Participante p1) {
		pdao.register(p1);
	}
}