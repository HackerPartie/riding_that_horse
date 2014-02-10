package ur.post.servlet;

import ur.post.bean.CrudPostDao;
import ur.post.model.Post;

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
		String sql = "select * from post where id = ?;";
		
		CrudPostDao getPost = new CrudPostDao();
		Post post = null;
        try {
            post = getPost.readPost(sql, id1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(post);
		if (post == null) {
			System.out.println("response.sendError(400, 'Gibts nicht')");
		} else {
			request.setAttribute("post", post);
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
