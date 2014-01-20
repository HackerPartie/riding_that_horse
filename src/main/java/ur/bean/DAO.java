package ur.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	private Connection connection = null;
	
	public Connection connectDb () throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String dbUrl = "jdbc:mysql://localhost:3306/riding";
		String user = "horse";
		String password = "horse";
		connection = DriverManager.getConnection(dbUrl, user, password);
		System.out.println(connection);
		return connection;
	}
	
	public void disconnectDb() throws Exception {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
	
	

}