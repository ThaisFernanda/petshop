package br.com.petshop.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import br.com.petshop.modelo.Funcionario;
import br.com.petshop.util.ConnectionFactory;

public class FuncionarioRepositorioImpl implements IRepositorio<Funcionario> {

	private static Connection connection = null;
	private static final String TABELA = "endereco";
	private static final String TABELA2 = "pessoa";
	
	@Override
	public void salvar(Funcionario entidade) {
		connection = ConnectionFactory.getConnection();
		String sql = "INSERT INTO "+TABELA+" (logradouro, bairro, cidade, estado)"
				+ "VALUES (?,?,?,?)";
		String sql2 = "INSERT INTO "+TABELA2+" (nome, data_nascimento, email, telefone_fixo, telefone_celular, telefone_outro, cpf, cargo, tipo, id_endereco) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,(SELECT MAX(id_endereco) FROM endereco))";
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			
			//INSERT ENDEREÇO
			ps = connection.prepareStatement(sql);
			ps.setString(1, entidade.getLogradouro());
			ps.setString(2, entidade.getBairro());
			ps.setString(3, entidade.getCidade());
			ps.setString(4, entidade.getEstado());
			
			ps.execute();
			//INSERT PESSOA	
			ps2 = connection.prepareStatement(sql2);
			ps2.setString(1, entidade.getNome());
			
			ps2.setDate(2, entidade.getDataNascimento());
			
			ps2.setString(3, entidade.getEmail());
			ps2.setString(4, entidade.getTelefoneFixo());
			ps2.setString(5, entidade.getTelefoneCelular());
			ps2.setString(6, entidade.getTelefoneOutro());
			ps2.setString(7, entidade.getCPF());
			ps2.setString(8, entidade.getCargo());
			ps2.setString(9, entidade.getTipo());
			
			
			ps2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, ps, null);
		}
		
	}

	@Override
	public void atualizar(Funcionario entidade) {
		connection = ConnectionFactory.getConnection();
		String sql = "UPDATE "+TABELA+"SET nome=?, data_nascimento=?, email=?, telefone_fixo=?, telefone_celular=?, telefone_outro=?, cpf=?, cargo=?, tipo=?, id_endereco=?"
				+ "WHERE id_pessoa = ? AND id_endereco = ?";
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
