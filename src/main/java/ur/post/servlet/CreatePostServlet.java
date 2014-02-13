package ur.post.servlet;

import ur.post.bean.CrudPostDao;
import ur.user.bean.CrudUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createpost")
public class CreatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
        System.out.println("title: ");
        System.out.println(title);
		HttpSession session = request.getSession();

        String user = getUser(session);
        System.out.println("user: ");
        System.out.println(user);
        int userId;
		String userSql = "select * from user where username = ?;";
		String postSql = "insert into post(title, body, userId) value (?,?,?);";

        CrudUserDao crudUserDao = new CrudUserDao();
        userId = crudUserDao.getUserId(userSql, user);
        System.out.println(userId);

        CrudPostDao crudPostDao = new CrudPostDao();
        try {
            crudPostDao.createPost(postSql, title, body, userId);
        } catch (Exception e) {
            System.out.println("something worked not well: " + e.toString());
        }

        response.sendRedirect("/posts");

	}
	
	private String getUser(HttpSession session) {
        String user = null;
		if (session.getAttribute("user") == null) {
		} else {
			user = (String) session.getAttribute("user");
		}
		return user;
	}

}
