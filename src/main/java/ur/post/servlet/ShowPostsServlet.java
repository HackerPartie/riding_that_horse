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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/posts")
public class ShowPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        List<PostByUser> posts = new ArrayList<>();
		String sql = "select post.id, post.title, post.body, user.username from post inner join user on post.userId = user.id;";
        CrudPostDao crudPostDao = new CrudPostDao();
        posts = crudPostDao.getPosts(sql);

		System.out.println(posts.isEmpty());
        for (PostByUser postByUser: posts) {
            System.out.println(postByUser.getUsername());
        }
		request.setAttribute("posts", posts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
		dispatcher.forward(request, response);
	}

}
