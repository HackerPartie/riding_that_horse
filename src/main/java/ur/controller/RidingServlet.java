package ur.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

@WebServlet("/riding")
public class RidingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dbO = new DAO();
		ResultSet rs = null;
		Connection c = null;
		PreparedStatement preparedStatement = null;
		List<GetTheDamnRide> rides = new ArrayList<GetTheDamnRide>();
		String sqlString = "select h.*, hm.*, hhm.* "
				+ "from horse h, horseman hm, horse_horseman hhm "
				+ "where hhm.horseId = h.id and hhm.horsemanId = hm.id and h.horseName = ?;";
		String hName = request.getParameter("horsename");
		try { 
			c = dbO.connectDb();
			preparedStatement = c.prepareStatement(sqlString);
			preparedStatement.setString(1, hName);
			
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String horseName = rs.getString("horseName");
				String horsemanName = rs.getString("horsemanName");
				String day = rs.getString("day");
				String place = rs.getString("place");
				GetTheDamnRide getTheDamnRide = new GetTheDamnRide(horseName, horsemanName, day, place);
				rides.add(getTheDamnRide);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbO.disconnectDb();
				preparedStatement.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("rides", rides);
		RequestDispatcher rdp = request.getRequestDispatcher("index.jsp");
		rdp.forward(request, response);
	}


}
