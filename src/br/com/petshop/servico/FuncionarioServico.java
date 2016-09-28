package br.com.petshop.servico;

import java.util.Collection;

import br.com.petshop.modelo.Funcionario;
import br.com.petshop.repositorio.FuncionarioRepositorioImpl;

public class FuncionarioServico implements IServico<Funcionario> {

	FuncionarioRepositorioImpl repositorio = new FuncionarioRepositorioImpl();
	
	@Override
	public void salvar(Funcionario entidade) {
		repositorio.salvar(entidade);
	}

	@Override
	public void atualizar(Funcionario entidade) {

	}

	@Override
	public void deletar(Long id) {
		repositorio.deletar(id);
	}

	@Override
	public Collection<Funcionario> consultarTodos() {
		return repositorio.consultarTodos();
	}

	@Override
	public Funcionario consultarPorId(Long id) {
		return null;
	}

}
