package repository;

import java.util.List;

import model.Endereco;

public interface EnderecoRepository<G> {

	public int register(G obj);
	
	public boolean delete(int id);
	
	public List<Endereco> listarTodos();
	
	public Endereco buscarPorId(int id);	
}