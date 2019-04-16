package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Endereco;
import repository.EnderecoRepository;

@SuppressWarnings("rawtypes")
public class EnderecoDAO implements EnderecoRepository {

	public EnderecoDAO() {
	}

	@Override
	public int register(Object obj) {

		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into endereco (logradouro, numero, bairro, complemento, cep, cidade, uf) values (?,?,?,?,?,?,?)";
		Endereco end = new Endereco();
		int id = 0;

		if (obj instanceof Endereco) {
			end = (Endereco) obj;
		} else {
			JOptionPane.showMessageDialog(null,
					"Objeto não é uma instância de endereço. Método: EnderecoDAO.register()");
			return -1;
		}

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, end.getLogradouro());
			stmt.setString(2, end.getNumero());
			stmt.setString(3, end.getBairro());
			stmt.setString(4, end.getComplemento());
			stmt.setString(5, end.getCep());
			stmt.setString(6, end.getCidade());
			stmt.setString(7, end.getUf());

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException ex) {
			// service.emptyFields(end);
			return -1;
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao salvar o endereço no banco de dados. Método: EnderecoDAO.register()" + e);
			return -1;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
			System.out.println("Endereço cadastrado.");
		}
		return id;
	}

	@Override
	public boolean delete(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from endereco where id = ?";
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			return false;
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao deletar o endereço no banco de dados. Método: EnderecoDAO.delete()" + e);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
			System.out.println("Endereço deletado.");
		}
		return true;
	}

	@Override
	public List listarTodos() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from endereco";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Endereco> enderecos = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Endereco end = new Endereco();
				end.setId(rs.getInt("id"));
				end.setLogradouro(rs.getString("logradouro"));
				end.setNumero(rs.getString("numero"));
				end.setBairro(rs.getString("bairro"));
				end.setComplemento(rs.getString("complemento"));
				end.setCep(rs.getString("cep"));
				end.setCidade(rs.getString("cidade"));
				end.setUf(rs.getString("uf"));

				enderecos.add(end);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao buscar todos os endereços no banco de dados. (EnderecoDAO.listarTodos)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return enderecos;
	}

	@Override
	public Endereco buscarPorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from endereco where id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Endereco end = new Endereco();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {

				end.setId(id);
				end.setLogradouro(rs.getString("logradouro"));
				end.setNumero(rs.getString("numero"));
				end.setBairro(rs.getString("bairro"));
				end.setComplemento(rs.getString("complemento"));
				end.setCep(rs.getString("cep"));
				end.setCidade(rs.getString("cidade"));
				end.setUf(rs.getString("uf"));

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao buscar o endereço pelo ID no banco de dados. (EnderecoDAO.buscarPorId)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return end;
	}
}