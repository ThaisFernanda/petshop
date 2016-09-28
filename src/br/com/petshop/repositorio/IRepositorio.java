package br.com.petshop.repositorio;

import java.io.Serializable;
import java.util.Collection;

public interface IRepositorio<E extends Serializable> {

	public void salvar(E entidade);
	public void atualizar(E entidade);
	public void deletar(Long id);
	public Collection<E> consultarTodos();
	public E consultarPorId(Long id);
	
}
