package ur.user.servlet;

import ur.user.bean.CrudUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		HttpSession session = request.getSession();
		String sessionID = session.getId();
		String sql = "select * from user where username = ?";
        String username = null;
        CrudUserDao crudUserDao = new CrudUserDao();

        try {
            username = crudUserDao.loginUser(sql, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("user", username);
        Cookie cookie = new Cookie(username, sessionID);
        cookie.setMaxAge(18000);
        response.addCookie(cookie);
        response.sendRedirect("loginSuccess.jsp");
		
	}

}
