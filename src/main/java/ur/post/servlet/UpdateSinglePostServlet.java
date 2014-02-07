package ur.post.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ur.post.model.CrudPost;
import ur.post.model.Post;

@WebServlet("/updatesinglepost/*")
public class UpdateSinglePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String aidee = request.getParameter("id");
		int id1 = Integer.parseInt(aidee);
		String sql = "select * from post where id = ?;";
		
		CrudPost getPost = new CrudPost();
		Post post = null;
		post = getPost.getThePost(sql, id1);
		
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
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		CrudPost updatePost = new CrudPost();
		try {
			updatePost.updatePost(id1, title, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("posts");
	}

}
