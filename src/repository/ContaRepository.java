package repository;

import java.time.LocalDate;
import java.util.List;

import model.Conta;

public interface ContaRepository<G> {

	public boolean register(G obj);

	public List<Conta> listarContas();

	public Conta buscarContaPorId(int id);
	
	public boolean atualizarSaldo(G obj);
	
	public List<Conta> listarContasAptas();

	public void atualizaUltimoResgate(LocalDate data, G obj);
	
	public void atualizaSaldoNormal(G obj, double valor);
	
	public void atualizaSaldoAdicional(G obj, double valor);
	
	public void atualizaSaldoPortabilidade(G obj, double valor);
}
