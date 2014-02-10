package ur.post.servlet;

import ur.post.bean.CrudPostDao;
import ur.post.model.Post;
import ur.user.bean.CrudUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/post/*")
public class ShowSinglePost extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String aidee = req.getParameter("id");
		int id1 = Integer.parseInt(aidee);
        String sql = "select * from post where id = ?;";

        CrudPostDao crudPostDao = new CrudPostDao();
        Post post = new Post();
        try {
            post = crudPostDao.readPost(sql, id1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String postingUsername = null;
		CrudUserDao user = new CrudUserDao();
		int userId = post.getUserId();
		postingUsername = user.getUsername(userId);
		
		if (post == null) {
			extracted(resp);
        } else {
			req.setAttribute("post", post);
			req.setAttribute("pUsername", postingUsername);
			RequestDispatcher dispatcher = req.getRequestDispatcher("../post.jsp");
			dispatcher.forward(req, resp);
		}
	}

	private void extracted(HttpServletResponse resp) throws IOException {
		extracted(resp);
	}

}
