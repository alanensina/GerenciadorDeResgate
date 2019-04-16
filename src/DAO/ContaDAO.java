package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Conta;
import model.Participante;
import repository.ContaRepository;

@SuppressWarnings("rawtypes")
public class ContaDAO implements ContaRepository {
	private ParticipanteDAO pdao = new ParticipanteDAO();

	@Override
	public boolean register(Object obj) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into conta (fk_participante, saldoNormal, saldoAdicional, saldoPortabilidade) values (?,?,?,?)";
		Conta c1 = new Conta();

		if (obj instanceof Conta) {
			c1 = (Conta) obj;
		} else {
			JOptionPane.showMessageDialog(null, "Objeto não é uma instância de Conta. Método: ContaDAO.register()");
			return false;
		}

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, c1.getParticipante().getId());
			stmt.setDouble(2, c1.getSaldoNormal());
			stmt.setDouble(3, c1.getSaldoAdicional());
			stmt.setDouble(4, c1.getSaldoPortabilidade());

			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso.");
		} catch (SQLException ex) {
			// service.emptyFields(end);
			return false;
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao salvar a CONTA no banco de dados. Método: ContaDAO.register()" + e);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;

	}

	@Override
	public List<Conta> listarContas() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from conta";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Conta> contas = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Conta c1 = new Conta();
				c1.setId(rs.getInt("id"));
				c1.setParticipante(pdao.buscarParticipantePorId(rs.getInt("fk_participante")));
				c1.setSaldoNormal(rs.getDouble("saldoNormal"));
				c1.setSaldoAdicional(rs.getDouble("saldoAdicional"));
				c1.setSaldoPortabilidade(rs.getDouble("saldoPortabilidade"));
				if (rs.getDate("ultimoResgate") == null) {
					c1.setUltimoResgate(null);
				} else {
					c1.setUltimoResgate(rs.getDate("ultimoResgate").toLocalDate());
				}
				contas.add(c1);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao buscar todos os participantes no banco de dados. (ParticipanteDAO.listarParticipantes)"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return contas;
	}

	@Override
	public Conta buscarContaPorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from conta where id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Conta c1 = new Conta();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {

				c1.setId(rs.getInt("id"));
				c1.setParticipante(pdao.buscarParticipantePorId(rs.getInt("fk_participante")));
				c1.setSaldoNormal(rs.getDouble("saldoNormal"));
				c1.setSaldoAdicional(rs.getDouble("saldoAdicional"));
				c1.setSaldoPortabilidade(rs.getDouble("saldoPortabilidade"));
				if (rs.getDate("ultimoResgate") == null) {
					c1.setUltimoResgate(null);
				} else {
					c1.setUltimoResgate(rs.getDate("ultimoResgate").toLocalDate());
				}
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao buscar o endereço pelo ID no banco de dados. (EnderecoDAO.buscarPorId)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return c1;
	}

	@Override
	public boolean atualizarSaldo(Object obj) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "update conta set saldoNormal = ?, saldoAdicional = ?, saldoPortabilidade = ? where fk_participante = ?";
		PreparedStatement stmt = null;
		
		Conta c1 = new Conta();

		if (obj instanceof Conta) {
			c1 = (Conta) obj;
		} else {
			JOptionPane.showMessageDialog(null, "Objeto não é uma instância de Conta. Método: ContaDAO.atualizarSaldo()");
			return false;
		}
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, c1.getSaldoNormal());
			stmt.setDouble(2, c1.getSaldoAdicional());
			stmt.setDouble(3, c1.getSaldoPortabilidade());
			stmt.setInt(4, c1.getParticipante().getId());

			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Conta atualizada com sucesso.");
		} catch (SQLException ex) {
			// service.emptyFields(end);
			return false;
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao atualizar os saldos no banco de dados. Método: ContaDAO.atualizarSaldo()" + e);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

	@Override
	public List listarContasAptas() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from conta c inner join participante p on c.fk_participante = p.id where p.status <> 'CANCELADO';";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Conta> contas = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Conta c1 = new Conta();
				c1.setId(rs.getInt("id"));
				c1.setParticipante(pdao.buscarParticipantePorId(rs.getInt("fk_participante")));
				c1.setSaldoNormal(rs.getDouble("saldoNormal"));
				c1.setSaldoAdicional(rs.getDouble("saldoAdicional"));
				c1.setSaldoPortabilidade(rs.getDouble("saldoPortabilidade"));
				if (rs.getDate("ultimoResgate") == null) {
					c1.setUltimoResgate(null);
				} else {
					c1.setUltimoResgate(rs.getDate("ultimoResgate").toLocalDate());
				}
				contas.add(c1);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao buscar todos os participantes no banco de dados. (ParticipanteDAO.listarParticipantes)"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return contas;
	}

	@Override
	public void atualizaUltimoResgate(LocalDate data, Object obj) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "update conta set ultimoResgate = ? where fk_participante = ?";
		PreparedStatement stmt = null;
		Conta c1 = new Conta();
		
		if (obj instanceof Conta) {
			c1 = (Conta) obj;
		} else {
			JOptionPane.showMessageDialog(null, "Objeto não é uma instância de Conta. Método: ContaDAO.atualizaUltimoResgate()");
		}
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(data));
			stmt.setInt(2, c1.getParticipante().getId());
			stmt.executeUpdate();
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao atualizar o último resgate do participante no banco de dados. (ParticipanteDAO.atualizaUltimoResgate)"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}

	@Override
	public void atualizaSaldoNormal(Object obj, double valor) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "update conta set saldoNormal = ? where fk_participante = ?";
		PreparedStatement stmt = null;
		Conta c1 = new Conta();
		
		if (obj instanceof Conta) {
			c1 = (Conta) obj;
		} else {
			JOptionPane.showMessageDialog(null, "Objeto não é uma instância de Conta. Método: ContaDAO.atualizaSaldoNormal()");
		}
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, valor);
			stmt.setInt(2, c1.getParticipante().getId());
			stmt.executeUpdate();
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao atualizar o saldo normal do participante no banco de dados. (ParticipanteDAO.atualizaSaldoNormal())"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}

	@Override
	public void atualizaSaldoAdicional(Object obj, double valor) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "update conta set saldoAdicional = ? where fk_participante = ?";
		PreparedStatement stmt = null;
		Conta c1 = new Conta();
		
		if (obj instanceof Conta) {
			c1 = (Conta) obj;
		} else {
			JOptionPane.showMessageDialog(null, "Objeto não é uma instância de Conta. Método: ContaDAO.atualizaSaldoAdicional()");
		}
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, valor);
			stmt.setInt(2, c1.getParticipante().getId());
			stmt.executeUpdate();
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao atualizar o saldo adicional do participante no banco de dados. (ParticipanteDAO.atualizaSaldoAdicional)"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}

	@Override
	public void atualizaSaldoPortabilidade(Object obj, double valor) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "update conta set saldoPortabilidade = ? where fk_participante = ?";
		PreparedStatement stmt = null;
		Conta c1 = new Conta();
		
		if (obj instanceof Conta) {
			c1 = (Conta) obj;
		} else {
			JOptionPane.showMessageDialog(null, "Objeto não é uma instância de Conta. Método: ContaDAO.atualizaSaldoPortabilidade()");
		}
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, valor);
			stmt.setInt(2, c1.getParticipante().getId());
			stmt.executeUpdate();
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao atualizar o saldo de portabilidade do participante no banco de dados. (ParticipanteDAO.atualizaSaldoPortabilidade())"
							+ ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
	}
}