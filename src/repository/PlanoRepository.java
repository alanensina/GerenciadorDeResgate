package repository;

import java.util.List;

import model.Plano;

public interface PlanoRepository<G> {

	public int register(G obj);
	
	public List<Plano> listarPlanos();
	
	public Plano buscarPlanoPorId(int id);
	
}
