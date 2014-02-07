package ur.post.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ur.post.model.CrudPost;

@WebServlet("/delete/*")
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aidee = request.getParameter("id");
		int id1 = Integer.parseInt(aidee);
		CrudPost crudPost = new CrudPost();
		crudPost.deletePost(id1);
		response.sendRedirect("/posts");
	}

}
