package service;

import DAO.PlanoDAO;
import model.Plano;

public class CadPlanoService {
	private PlanoDAO pdao = new PlanoDAO();

	public CadPlanoService() {}
	
	public void salvarPlano(Plano p1) {
			pdao.register(p1);
	}	
}