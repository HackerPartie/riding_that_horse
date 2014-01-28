package ur.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ur.bean.DAO;
import ur.model.Post;

@WebServlet("/posts")
public class ShowPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ResultSet resultSet = null;
		String sql = "select * from post;";
		List<Post> posts = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		DAO dao = new DAO();
		
		try {
			connection = dao.connectDb();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
				int userId = resultSet.getInt("userId");
				System.out.println(id);
				System.out.println(title);
				System.out.println(body);
				System.out.println(userId);
				Post getThePosts = new Post(id, title, body, userId);
				posts.add(getThePosts);

			}			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(posts);
		request.setAttribute("posts", posts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
		dispatcher.forward(request, response);
	}

	

	

}
