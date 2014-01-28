package ur.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import ur.bean.DAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO daO = new DAO();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		PasswordService passwordService = new DefaultPasswordService();
		pwd = passwordService.encryptPassword(pwd);
		
		String sqlString = "insert into user(username, password) values(?,?)";
		
		try {
			connection = daO.connectDb();
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pwd);
			preparedStatement.executeUpdate();
			response.sendRedirect("loginSuccess.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				daO.disconnectDb();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
