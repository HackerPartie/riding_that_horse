package ur.user.bean;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import ur.bean.ConnSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUserDao extends ConnSource implements UserDao {
	
//	public String getUsername(int userId) {
//		DataSource dataSource = new DataSource();
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		String username = null;
//		String getUserSql = "select * from user where id = ?;";
//
//		try {
//			connection = dataSource.connectDb();
//			preparedStatement = connection.prepareStatement(getUserSql);
//			preparedStatement.setInt(1, userId);
//			resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//				username = resultSet.getString("username");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				connection.close();
//				preparedStatement.close();
//				resultSet.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return username;
//	}
//
//
//
//	public String getUser(HttpSession session) {
//		String user = null;
//		if (session.getAttribute("user") == null) {
//		} else {
//			user = (String) session.getAttribute("user");
//		}
//		return user;
//	}

    @Override
    public void createUser(String sql, String username, String password) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
    }

    @Override
    public String loginUser(String sql, String user, String pwd) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PasswordService passwordService = new DefaultPasswordService();
        String username = null;
        String password = null;
        preparedStatement = getConnection(sql);
        try {
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                username = resultSet.getString("username");
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        if (passwordService.passwordsMatch(pwd, password) == true) {
            return username;
        } else {
            return null;
        }
    }

    @Override
    public int getUserId(String userSql, String user) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int userId = 0;

        try {
            preparedStatement = getConnection(userSql);
            preparedStatement.setString(1, user);
            System.out.println("user in getUserId: ");
            System.out.println(user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                userId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        System.out.println("userId: ");
        System.out.println(userId);
        return userId;
    }


    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }

}
