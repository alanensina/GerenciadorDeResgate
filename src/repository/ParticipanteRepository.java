package repository;

import java.util.List;

import model.Participante;

public interface ParticipanteRepository<G> {

	public boolean register(G obj);
	
	public List<Participante> listarParticipantes();
	
	public Participante buscarParticipantePorId(int id);
	
	public List<Participante> listarParticipantesAptos();
	
	public void cancelarParticipante(int id);
	
}