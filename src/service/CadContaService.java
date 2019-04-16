package service;

import DAO.ContaDAO;
import model.Conta;

public class CadContaService {
	private ContaDAO dao = new ContaDAO();

	public CadContaService() {
	}

	public void salvarConta(Conta c1) {
		dao.register(c1);
	}
}
