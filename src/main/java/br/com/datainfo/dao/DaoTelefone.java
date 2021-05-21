package br.com.datainfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.datainfo.bean.BeanTelefone;
import br.com.datainfo.connection.SingleConnection;

public class DaoTelefone {
	
	private Connection connection;
	
	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanTelefone beanTelefone) {
		
		try {
			String sql = "insert into telefone(ddd, numero, tipo, usuario) "
									+ "values (?,?,?,?)";
			
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, beanTelefone.getDdd());
			insert.setString(2, beanTelefone.getNumero());
			insert.setString(3, beanTelefone.getTipo());
			insert.setLong(4, beanTelefone.getUsuario());
			insert.execute();
			
			connection.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}				
			
	}
	
	public List<BeanTelefone> listar(Long user) throws SQLException{
		List<BeanTelefone> listar = new ArrayList<BeanTelefone>();
		
		String sql = "select * from telefone where usuario = " + user;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			BeanTelefone beanTelefone = new BeanTelefone();
			beanTelefone.setId(resultSet.getLong("id"));
			beanTelefone.setDdd(resultSet.getLong("ddd"));
			beanTelefone.setNumero(resultSet.getString("numero"));
			beanTelefone.setTipo(resultSet.getString("tipo"));
			beanTelefone.setUsuario(resultSet.getLong("usuario"));
			
			listar.add(beanTelefone);
		}
		
		return listar;
	}
	
	public void delete(String id) {		
		try {
			String sql = "delete from telefone where id = '"+id+"'";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public void deleteFoneUsuario(String id) {		
		try {
			String sql = "delete from telefone where usuario = '"+id+"'";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
