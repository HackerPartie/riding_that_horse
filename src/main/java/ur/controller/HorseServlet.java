package ur.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ur.bean.DAO;
import ur.model.GetTheDamnRide;
import ur.model.Horse;

@WebServlet("/horse")
public class HorseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public HorseServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dbO = new DAO();
		ResultSet rs = null;
		Connection c = null;
		PreparedStatement preparedStatement = null;
		List<Horse> horses = new ArrayList<Horse>();
		String sqlHorsenameString = "select * from horse";
			
		try {
			c = dbO.connectDb();
			preparedStatement = c.prepareStatement(sqlHorsenameString);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String hN = rs.getString("horseName");
				Horse getTheHorses = new Horse(hN);
				horses.add(getTheHorses);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				dbO.disconnectDb();
				preparedStatement.close();
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		request.setAttribute("horses", horses);
		RequestDispatcher rdp = request.getRequestDispatcher("index.jsp");
		rdp.forward(request, response);
	}

}
