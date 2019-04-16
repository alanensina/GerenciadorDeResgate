package service;

import DAO.ContaDAO;
import model.Conta;

public class ContribuicaoService {
	private ContaDAO dao = new ContaDAO();

	public ContribuicaoService() {}
	
	public void adicionarContribuicao(Conta c1) {
		Conta contaAnterior = dao.buscarContaPorId(c1.getId());
		c1.setSaldoNormal(contaAnterior.getSaldoNormal() + c1.getSaldoNormal());
		c1.setSaldoAdicional(contaAnterior.getSaldoAdicional() + c1.getSaldoAdicional());
		c1.setSaldoPortabilidade(contaAnterior.getSaldoPortabilidade() + c1.getSaldoPortabilidade());
		dao.atualizarSaldo(c1);
	}
}
