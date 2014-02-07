package ur.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import ur.bean.DAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		DAO dao = new DAO();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PasswordService service = new DefaultPasswordService();
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		String sessionID = session.getId();
		String sqlString = "select * from user where username = ?";
		
		try {
			connection = dao.connectDb();
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				if (service.passwordsMatch(pwd, password) == true) {
					pw.println(true);
					session.setAttribute("user", username);
					Cookie cookie = new Cookie(username, sessionID);
					cookie.setMaxAge(18000);
					response.addCookie(cookie);
					response.sendRedirect("loginSuccess.jsp");
				} else {
					pw.println(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
