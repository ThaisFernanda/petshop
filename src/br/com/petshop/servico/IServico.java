package br.com.petshop.servico;

import java.io.Serializable;
import java.util.Collection;

public interface IServico<E extends Serializable> {

	public void salvar(E entidade);
	public void atualizar(E entidade);
	public void deletar(Long id);
	public Collection<E> consultarTodos();
	public E consultarPorId(Long id);
	
}
