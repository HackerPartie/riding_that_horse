package ur.post.servlet;

import ur.post.bean.CrudPostDao;
import ur.post.model.PostByUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updatesinglepost/*")
public class UpdateSinglePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String aidee = request.getParameter("id");
		int id1 = Integer.parseInt(aidee);
		String sql = "select post.id, post.title, post.body, user.username from post inner join user on post.userId = user.id where post.id = ?;";
		
		CrudPostDao getPost = new CrudPostDao();
		PostByUser postByUser = null;
        try {
            postByUser = getPost.readPost(sql, id1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(postByUser);
		if (postByUser == null) {
			System.out.println("response.sendError(400, 'Gibts nicht')");
		} else {
			request.setAttribute("post", postByUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../update_post.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		String aidee = request.getParameter("id");
		int id1 = Integer.parseInt(aidee);
        String sql = "update post set title = ?, body = ? where id = ?";
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		CrudPostDao updatePost = new CrudPostDao();
		try {
			updatePost.updatePost(sql, id1, title, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("posts");
	}

}
