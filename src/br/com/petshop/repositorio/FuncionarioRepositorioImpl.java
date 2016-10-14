package br.com.petshop.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


import br.com.petshop.modelo.Funcionario;
import br.com.petshop.util.ConnectionFactory;

public class FuncionarioRepositorioImpl implements IRepositorio<Funcionario> {

	private static Connection connection = null;
	private static final String ENDERECO = "endereco";
	private static final String PESSOA = "pessoa";
	
	@Override
	public void salvar(Funcionario entidade) {
		connection = ConnectionFactory.getConnection();
		String sql = "INSERT INTO "+ENDERECO+" (logradouro, bairro, cidade, estado)"
				+ "VALUES (?,?,?,?)";
		String sql2 = "INSERT INTO "+PESSOA+" (nome, data_nascimento, email, telefone_fixo, telefone_celular, telefone_outro, cpf, cargo, tipo, id_endereco) "
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
			
			ps.executeUpdate();
			//INSERT PESSOA	
			ps2 = connection.prepareStatement(sql2);
			ps2.setString(1, entidade.getNome());
				
				Date data = new Date(entidade.getDataNascimento().getTimeInMillis());
				ps2.setDate(2, data);
			
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
		String sql = "UPDATE "+PESSOA+"SET nome=?, data_nascimento=?, email=?, telefone_fixo=?, telefone_celular=?, telefone_outro=?, cpf=?, cargo=?, tipo=?, id_endereco=?"
				+ "WHERE id_pessoa = ?";
		String sql2 = "UPDATE "+ENDERECO+" SET logradouro=?, bairro=?, cidade=?, estado=?"
				+ "WHERE id_endereco-?";
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		
		try {
			//INSERT PESSOA
			ps = connection.prepareStatement(sql);
			ps.setString(1, entidade.getNome());
				Date data = new Date(entidade.getDataNascimento().getTimeInMillis());
				ps2.setDate(2, data);
			ps.setString(3, entidade.getEmail());
			ps.setString(4, entidade.getTelefoneFixo());
			ps.setString(5, entidade.getTelefoneCelular());
			ps.setString(6, entidade.getTelefoneOutro());
			ps.setString(7, entidade.getCPF());
			ps.setString(8, entidade.getCargo());
			ps.setString(9, entidade.getTipo());
						
			ps2.executeUpdate();
			
			//INSERT ENDERECO	
			ps2 = connection.prepareStatement(sql2);
			
			ps2.setString(1, entidade.getLogradouro());
			ps2.setString(2, entidade.getBairro());
			ps2.setString(3, entidade.getCidade());
			ps2.setString(4, entidade.getEstado());
			
			ps2.executeUpdate();
			
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
		PreparedStatement ps2 = null;
		String sql = "DELETE FROM "+PESSOA+" WHERE id_pessoa = ?";
		String sql2 = "DELETE FROM "+ENDERECO+" WHERE id_endereco = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps2 = connection.prepareStatement(sql2);
			
				ps.setLong(1, id);
				ps2.setLong(1, id);
			
			ps.executeUpdate();
			ps2.executeUpdate();
			
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
			ps = connection.prepareStatement("SELECT * FROM"+ PESSOA +"INNER JOIN"+ ENDERECO +"ON pessoa.id_endereco = endereco.id_endereco");
			result = ps.executeQuery();
			Collection<Funcionario> funcionarios = new ArrayList<>();
			while(result.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(result.getString("nome"));
				funcionario.setCPF(result.getString("cpf"));
				
					SimpleDateFormat dataformatada = new SimpleDateFormat("dd/MM/yyyy");
					Calendar data = dataformatada.getCalendar();
					data.setTime(result.getDate("dataNascimento"));
					funcionario.setDataNascimento(data);
				
				funcionario.setEmail(result.getString("email"));
				funcionario.setTelefoneFixo(result.getString("telefone_fixo"));
				funcionario.setTelefoneCelular(result.getString("telefone_celular"));
				funcionario.setTelefoneOutro(result.getString("telefone_outro"));
				funcionario.setCargo(result.getString("cargo"));
				funcionario.setTipo(result.getString("tipo"));
				funcionario.set(result.getString("id_endereco"));
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
