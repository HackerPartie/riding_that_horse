package ur.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ur.bean.DAO;

@WebServlet("/savepost")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PreparedStatement preparedStatement = null;
	private Connection connection = null;
	private ResultSet resultSet = null;
    private int userId = 0; 
    private String user = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		HttpSession session = request.getSession();		
		DAO dao = new DAO();
		
		user = getUser(session);
		String userSql = "select * from user where username = ?;";
		String postSql = "insert into post(title, body, userId) value (?,?,?);";
		
		try {
			connection = dao.connectDb();
			preparedStatement = connection.prepareStatement(userSql);
			preparedStatement.setString(1, user);
			resultSet = preparedStatement.executeQuery();
			userId = getUserId(resultSet);
			System.out.println(userId);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			closeThatShit();
		}
			
		try {
			connection = dao.connectDb();
			preparedStatement = connection.prepareStatement(postSql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, body);
			System.out.println(userId);
			preparedStatement.setInt(3, userId);
			preparedStatement.executeUpdate();
			response.sendRedirect("/posts");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeThatShit();
		}
		
	}
	
	private String getUser(HttpSession session) {
		if (session.getAttribute("user") == null) {
		} else {
			user = (String) session.getAttribute("user");
		}
		return user;
	}
	
	private int getUserId (ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			userId = resultSet.getInt("id");
		}
		return userId;
	}
	
	private void closeThatShit() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

}
