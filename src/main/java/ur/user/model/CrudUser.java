package ur.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import ur.bean.DAO;

public class CrudUser {
	
	public String getUsername(int userId) {
		DAO dao = new DAO();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String username = null;
		String getUserSql = "select * from user where id = ?;";
		
		try {
			connection = dao.connectDb();
			preparedStatement = connection.prepareStatement(getUserSql);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				username = resultSet.getString("username");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		return username;
	}
	
	
	
	public String getUser(HttpSession session) {
		String user = null;
		if (session.getAttribute("user") == null) {
		} else {
			user = (String) session.getAttribute("user");
		}
		return user;
	}
	
	

}
