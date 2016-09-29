package br.com.petshop.modelo;

import java.util.Calendar;

public class SerVivo {
	
	private Integer idSerVivo;
	private String nome;
	private Calendar dataNascimento;
	
	public Integer getIdSerVivo() {
		return idSerVivo;
	}
	public void setIdSerVivo(Integer idSerVivo) {
		this.idSerVivo = idSerVivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	

}
