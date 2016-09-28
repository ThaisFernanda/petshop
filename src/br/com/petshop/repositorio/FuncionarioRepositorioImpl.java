package br.com.petshop.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.petshop.modelo.Funcionario;
import br.com.petshop.util.ConnectionFactory;

public class FuncionarioRepositorioImpl implements IRepositorio<Funcionario> {

	private static Connection connection = null;
	private static final String TABELA = "FUNCIONARIO";
	
	@Override
	public void salvar(Funcionario entidade) {
		connection = ConnectionFactory.getConnection();
		String sql = "INSERT INTO "+TABELA+" (NOME, CPF) VALUES (?,?)";
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, entidade.getNome());
			ps.setString(2, entidade.getCPF());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, ps, null);
		}
		
	}

	@Override
	public void atualizar(Funcionario entidade) {
		
	}

	@Override
	public void deletar(Long id) {
		connection = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		String sql = "DELETE FROM "+TABELA+" WHERE ID = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			ConnectionFactory.close(connection, ps, null);
		}
	}

	@Override
	public Collection<Funcionario> consultarTodos() {
		connection = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM "+TABELA);
			result = ps.executeQuery();
			Collection<Funcionario> funcionarios = new ArrayList<>();
			while(result.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(result.getString("NOME"));
				funcionario.setCPF(result.getString("CPF"));
				funcionarios.add(funcionario);
			}
			return funcionarios;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			ConnectionFactory.close(connection, ps, result);
		}
	}

	@Override
	public Funcionario consultarPorId(Long id) {
		return null;
	}

}
