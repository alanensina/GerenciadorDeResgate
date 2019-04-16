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
import model.Plano;
import repository.PlanoRepository;

@SuppressWarnings("rawtypes")
public class PlanoDAO implements PlanoRepository {

	private EnderecoDAO edao = new EnderecoDAO();

	public PlanoDAO() {
	}

	@Override
	public int register(Object obj) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into plano (nome, cnpj, fk_endereco, data_fundacao) values (?,?,?,?)";
		Plano plano = new Plano();
		int id_endereco = 0;
		int id = 0;

		if (obj instanceof Plano) {
			plano = (Plano) obj;
		} else {
			JOptionPane.showMessageDialog(null, "Objeto não é uma instância de Plano. Método: PlanoDAO.register()");
			return id;
		}

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, plano.getNome());
			stmt.setString(2, plano.getCnpj());
			id_endereco = edao.register(plano.getEndereco());
			stmt.setInt(3, id_endereco);
			stmt.setDate(4, Date.valueOf(plano.getDtFundacao()));

			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
			JOptionPane.showMessageDialog(null, "Plano cadastrado com sucesso.");

		} catch (SQLException ex) {
			// service.emptyFields(end);
			edao.delete(id_endereco);
			return id;
		} catch (Throwable e) {
			edao.delete(id_endereco);
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao salvar o plano no banco de dados. Método: PlanoDAO.register()" + e);
			return id;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return id;
	}

	@Override
	public List<Plano> listarPlanos() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from plano";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Plano> planos = new LinkedList<>();
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Plano plan = new Plano();
				plan.setId(rs.getInt("id"));
				plan.setNome(rs.getString("nome"));
				plan.setCnpj(rs.getString("cnpj"));
				plan.setEndereco(edao.buscarPorId(rs.getInt("fk_endereco")));
				plan.setDtFundacao(rs.getDate("data_fundacao").toLocalDate());

				planos.add(plan);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao buscar todos os planos no banco de dados. (PlanoDAO.listarPlanos)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return planos;		
	}

	@Override
	public Plano buscarPlanoPorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from plano where id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Plano plan = new Plano();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {

				plan.setId(rs.getInt("id"));
				plan.setNome(rs.getString("nome"));
				plan.setCnpj(rs.getString("cnpj"));
				plan.setEndereco(edao.buscarPorId(rs.getInt("fk_endereco")));
				plan.setDtFundacao(rs.getDate("data_fundacao").toLocalDate());

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao buscar o endereço pelo ID no banco de dados. (EnderecoDAO.buscarPorId)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return plan;
	}
}