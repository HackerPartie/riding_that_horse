package ur.user.servlet;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import ur.user.bean.CrudUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("user");
		String password = request.getParameter("pwd");
		
		PasswordService passwordService = new DefaultPasswordService();
		password = passwordService.encryptPassword(password);
		String sql = "insert into user(username, password) values(?,?)";

        CrudUserDao crudUserDao = new CrudUserDao();
        crudUserDao.createUser(sql, username, password);
		response.sendRedirect("loginSuccess.jsp");

		
	}

}
