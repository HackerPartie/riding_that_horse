package ur.post.servlet;

import ur.post.bean.CrudPostDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/*")
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aidee = request.getParameter("id");
		int id1 = Integer.parseInt(aidee);
        String sql = "delete from post where id = ?;";
		CrudPostDao crudPostDao = new CrudPostDao();
		crudPostDao.deletePost(sql, id1);
		response.sendRedirect("/posts");
	}

}
