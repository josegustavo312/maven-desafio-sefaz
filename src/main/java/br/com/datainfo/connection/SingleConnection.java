package br.com.datainfo.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String host = "ec2-3-233-7-12.compute-1.amazonaws.com";
	private static String database = "d49etmdrv7vj3i";	
	private static String banco = "jdbc:postgresql://"+host+":5432/"+database+"?sslmode=require";
	private static String password = "2520477fefdde4e8c2573a4c30e7e8b75504959848a3f2dea8715e0f7b599206";
	private static String user = "tkorneayhqrtdu";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false); // Não salva automaticamento os dados no banco.				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
