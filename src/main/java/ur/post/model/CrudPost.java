package ur.post.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ur.bean.DAO;

public class CrudPost {
	
	public void createPost(String postSql, String title, String body, int userId) {
		DAO dao = new DAO();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dao.connectDb();
			preparedStatement = connection.prepareStatement(postSql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, body);
			System.out.println(userId);
			preparedStatement.setInt(3, userId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dao.disconnectDb();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public Post getThePost(String sql, int id1) {
	
		DAO dao = new DAO();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Post post = null;
	
		try {
			connection = dao.connectDb();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id1);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
				int userId = resultSet.getInt("userId");
				post = new Post(id, title, body, userId);
				//posting.add(post);
			} 
			System.out.println(post);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return post;
	}
	
	public void updatePost(int id1, String title, String body) throws Exception {
		DAO dao = new DAO();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		String updateSql = "update post set title = ?, body = ? where id = ?";
		try {
			connection = dao.connectDb();
			preparedStatement = connection.prepareStatement(updateSql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, body);
			preparedStatement.setInt(3, id1);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    
	}
	
	public void deletePost(int id1) {
		DAO dao = new DAO();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		String deleteSql = "delete from post where id = ?;";
		try {
			connection = dao.connectDb();
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, id1);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

}
