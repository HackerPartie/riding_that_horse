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

@WebServlet("/post/*")
public class ShowSinglePost extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String aidee = req.getParameter("id");
		int id1 = Integer.parseInt(aidee);
        String sql = "select post.id, post.title, post.body, user.username from post inner join user on post.userId = user.id where post.id = ?;";

        CrudPostDao crudPostDao = new CrudPostDao();
        PostByUser postByUser = null;
        try {
            postByUser = crudPostDao.readPost(sql, id1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

		if (postByUser == null) {
			extracted(resp);
        } else {
			req.setAttribute("post", postByUser);
			//req.setAttribute("pUsername", postingUsername);
			RequestDispatcher dispatcher = req.getRequestDispatcher("../post.jsp");
			dispatcher.forward(req, resp);
		}
	}

	private void extracted(HttpServletResponse resp) throws IOException {
		extracted(resp);
	}

}
