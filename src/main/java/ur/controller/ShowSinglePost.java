package ur.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ur.bean.DAO;
import ur.model.Post;

@WebServlet("/showsinglepost/*")
public class ShowSinglePost extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(req.getRequestURL());
		System.out.println(req.getPathInfo());
		System.out.println(req.getParameter("id"));
		String aidee = req.getParameter("id");
		int id1 = Integer.parseInt(aidee);
		System.out.println(id1);
		String sql = "select * from post where id = ?;";
		
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
			System.out.println(resultSet);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		req.setAttribute("post", post);
		RequestDispatcher dispatcher = req.getRequestDispatcher("../post.jsp");
		dispatcher.forward(req, resp);
		
	}

	
}
