package br.com.petshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection conn;
    
    public static Connection getConnection(){
             
        try {
 
            String driverName = "com.mysql.jdbc.Driver";
//            String url = "jdbc:mysql://codeshouse.com.br:3306/codes475_petshop";
//            String usuario = "codes475_petshop";
//            String senha = "p3t3sh0p2016#";
            String url = "jdbc:mysql://localhost:3306/restaurante";
            String usuario = "root";
            String senha = "123mudar";
         
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão Estabelecida");
        } catch (ClassNotFoundException e) {
        	System.out.println("Erro na conexão com a base de dados"
        			+ " ClassNotFoundException Exception");
            e.printStackTrace();
        } catch (SQLException f) {
        	System.out.println("Erro na conexão com a base de dados - SQL Exception");
            f.printStackTrace();
        }
        return conn;
    }
    
    public static void close(Connection conn, PreparedStatement pstm, ResultSet rs){
            try{if (rs != null) rs.close( );}catch (Exception e) {e.printStackTrace();}
            try{if (pstm != null)pstm.close( );}catch (Exception e) {e.printStackTrace();}
            try{if (conn != null)conn.close( );}catch (Exception e) {e.printStackTrace(); }       
         
    }
}