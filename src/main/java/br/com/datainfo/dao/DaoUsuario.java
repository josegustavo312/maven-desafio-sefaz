package br.com.datainfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.datainfo.bean.BeanUsuario;
import br.com.datainfo.connection.SingleConnection;

public class DaoUsuario {

private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanUsuario beanUsuario) {
		
		try {
			String sql = "insert into usuario(nome, email, senha) values (?,?,?)";
			
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, beanUsuario.getNome());
			insert.setString(2, beanUsuario.getEmail());
			insert.setString(3, beanUsuario.getSenha());
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
	
	public List<BeanUsuario> listar(String descricaoConsulta) throws SQLException{
		String sql = "select * from usuario where email <> 'admin@gmail.com' and nome like '%"+descricaoConsulta+"%'";
		return consultarUsuarios(sql);
	}
	
	public List<BeanUsuario> listar() throws SQLException{
		String sql = "select * from usuario where email <> 'admin@gmail.com'";
		return consultarUsuarios(sql);	
	}
	
	private List<BeanUsuario> consultarUsuarios(String sql) throws SQLException {
		List<BeanUsuario> listar = new ArrayList<BeanUsuario>();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			BeanUsuario beanUsuario = new BeanUsuario();
			beanUsuario.setId(resultSet.getLong("id"));
			beanUsuario.setNome(resultSet.getString("nome"));
			beanUsuario.setEmail(resultSet.getString("email"));
			beanUsuario.setSenha(resultSet.getString("senha"));
						
			listar.add(beanUsuario);
		}
		
		return listar;
		
	}
	
	public void delete(String id) {		
		try {
			String sql = "delete from usuario where id = '"+id+"' and email <> 'admin@gmail.com'";
			
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

	public BeanUsuario consultar(String id) throws SQLException {
		String sql = "select * from usuario where id='"+id+"' and email <> 'admin@gmail.com'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			BeanUsuario beanUsuario = new BeanUsuario();
			beanUsuario.setId(resultSet.getLong("id"));
			beanUsuario.setNome(resultSet.getString("nome"));
			beanUsuario.setSenha(resultSet.getString("senha"));
			beanUsuario.setEmail(resultSet.getString("email"));			
			
			return beanUsuario;
		}
		
		return null;
	}
	
	public void atualizar(BeanUsuario beanUsuario) {
		try {
			
			String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = "+ beanUsuario.getId();
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);			
			preparedStatement.setString(1, beanUsuario.getNome());
			preparedStatement.setString(2, beanUsuario.getEmail());
			preparedStatement.setString(3, beanUsuario.getSenha());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	public boolean validarEmail(String email) throws SQLException {
		String sql = "select count(1) as qtd from usuario where email='"+email+"'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {			
			return resultSet.getInt("qtd") <= 0; //Return true
		}
		
		return false;
	}
	
	public boolean validarEmailUpdate(String email, String id) throws SQLException {
		String sql = "select count(1) as qtd from usuario where email ='"+email+"' and id <> "+id;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {			
			return resultSet.getInt("qtd") <= 0; //Return true
		}
		
		return false;
	}
	
	public boolean validarLogin(String email, String senha) throws Exception{
		
		String sql = "select * from usuario where email = '"+email+"' and senha = '"+senha+"'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return true; // Encontrou o usuário.
		}else {
			return false; // Usuário inexistente no banco.
		}
		
	}
	
}
