package br.com.petshop.util;

abstract class SQL {
	
	 static final String INSERT_ENDERECO = "INSERT INTO endereco (logradouro, bairro, cidade, estado) VALUES (?,?,?,?)";
	 static final String INSERT_PESSOA = "INSERT INTO pessoa (nome, data_nascimento, email, telefone_fixo, telefone_celular, telefone_outro, cpf, cargo, tipo, id_endereco) VALUES (?,?,?,?,?,?,?,?,?,(SELECT MAX(id_endereco) FROM endereco))";
	
	
	

}
