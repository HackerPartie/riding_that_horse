package ur.post.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ur.post.model.CrudPost;
import ur.post.model.Post;
import ur.user.model.CrudUser;

@WebServlet("/post/*")
public class ShowSinglePost extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String aidee = req.getParameter("id");
		int id1 = Integer.parseInt(aidee);
		String sql = "select * from post where id = ?;";
		
		System.out.println(id1);
		
		String postingUsername = null;
		
		CrudPost getPost = new CrudPost();
		Post posting = new Post();
		CrudUser user = new CrudUser();
		
		posting = getPost.getThePost(sql, id1);
		int userId = posting.getUserId();
		postingUsername = user.getUsername(userId);
		
		System.out.println(postingUsername);
		
		System.out.println("post: ");
		System.out.println(userId);
		System.out.println(posting);
		System.out.println(postingUsername);
		
		
		if (posting == null)
			extracted(resp);
		else {
			req.setAttribute("post", posting);
			req.setAttribute("pUsername", postingUsername);
			RequestDispatcher dispatcher = req.getRequestDispatcher("../post.jsp");
			dispatcher.forward(req, resp);
		}
	}

	private void extracted(HttpServletResponse resp) throws IOException {
		extracted(resp);
	}
	
	
	
}
