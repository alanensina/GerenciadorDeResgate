package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Participante;
import repository.ParticipanteRepository;

@SuppressWarnings("rawtypes")
public class ParticipanteDAO implements ParticipanteRepository {
	private EnderecoDAO edao = new EnderecoDAO();
	private PlanoDAO pdao = new PlanoDAO();

	@Override
	public boolean register(Object obj) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into participante (nome, cpf, celular, email, data_nascimento, fk_plano, data_associacao, status, fk_endereco) values (?,?,?,?,?,?,?,?,?)";
		Participante participante = new Participante();
		int id_endereco = 0;
		int id_plano = 0;

		if (obj instanceof Participante) {
			participante = (Participante) obj;
		} else {
			JOptionPane.showMessageDialog(null,
					"Objeto não é uma instância de Participante. Método: ParticipanteDAO.register()");
			return false;
		}

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, participante.getNome());
			stmt.setString(2, participante.getCpf());
			stmt.setString(3, participante.getCelular());
			stmt.setString(4, participante.getEmail());
			stmt.setDate(5, Date.valueOf(participante.getDtNascimento()));
			id_plano = edao.register(participante.getEndereco());
			stmt.setInt(6, participante.getPlano().getId());
			stmt.setDate(7, Date.valueOf(participante.getDtAssociacao()));
			stmt.setString(8, participante.getStatus());
			id_endereco = edao.register(participante.getEndereco());
			stmt.setInt(9, id_endereco);

			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Participante cadastrado com sucesso.");
		} catch (SQLException ex) {
			// service.emptyFields(end);
			edao.delete(id_endereco);
			return false;
		} catch (Throwable e) {
			edao.delete(id_endereco);
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao salvar o participante no banco de dados. Método: ParticipanteDAO.register()" + e);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

	@Override
	public List listarParticipantes() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from participante";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Participante> participantes = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Participante p1 = new Participante();
				p1.setId(rs.getInt("id"));
				p1.setNome(rs.getString("nome"));
				p1.setCpf(rs.getString("cpf"));
				p1.setCelular(rs.getString("celular"));
				p1.setEmail(rs.getString("email"));
				p1.setDtNascimento(rs.getDate("data_nascimento").toLocalDate());
				p1.setPlano(pdao.buscarPlanoPorId(rs.getInt("fk_plano")));
				p1.setDtAssociacao(rs.getDate("data_associacao").toLocalDate());
				p1.setStatus(rs.getString("status"));
				p1.setEndereco(edao.buscarPorId(rs.getInt("fk_endereco")));

				participantes.add(p1);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao buscar todos os participantes no banco de dados. (ParticipanteDAO.listarParticipantes)"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return participantes;
	}

	@Override
	public Participante buscarParticipantePorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from participante where id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Participante p1 = new Participante();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {

				p1.setId(rs.getInt("id"));
				p1.setNome(rs.getString("nome"));
				p1.setCpf(rs.getString("cpf"));
				p1.setCelular(rs.getString("celular"));
				p1.setEmail(rs.getString("email"));
				p1.setDtNascimento(rs.getDate("data_nascimento").toLocalDate());
				p1.setPlano(pdao.buscarPlanoPorId(rs.getInt("fk_plano")));
				p1.setDtAssociacao(rs.getDate("data_associacao").toLocalDate());
				p1.setStatus(rs.getString("status"));
				p1.setEndereco(edao.buscarPorId(rs.getInt("fk_endereco")));

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao buscar o participante pelo ID no banco de dados. (ParticipanteDAO.buscarParticipantePorId)"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return p1;
	}

	@Override
	public List listarParticipantesAptos() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from participante p left join conta c on p.id = c.fk_participante where c.fk_participante is null and p.status <> 'CANCELADO'";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Participante> participantes = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Participante p1 = new Participante();
				p1.setId(rs.getInt("id"));
				p1.setNome(rs.getString("nome"));
				p1.setCpf(rs.getString("cpf"));
				p1.setCelular(rs.getString("celular"));
				p1.setEmail(rs.getString("email"));
				p1.setDtNascimento(rs.getDate("data_nascimento").toLocalDate());
				p1.setPlano(pdao.buscarPlanoPorId(rs.getInt("fk_plano")));
				p1.setDtAssociacao(rs.getDate("data_associacao").toLocalDate());
				p1.setStatus(rs.getString("status"));
				p1.setEndereco(edao.buscarPorId(rs.getInt("fk_endereco")));

				participantes.add(p1);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao buscar todos os participantes no banco de dados. (ParticipanteDAO.listarParticipantesAptos)"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return participantes;
	}

	@Override
	public void cancelarParticipante(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "update participante set status = 'CANCELADO' where id = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao atualizar o status do participante no banco de dados. (ParticipanteDAO.cancelarParticipante)"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}