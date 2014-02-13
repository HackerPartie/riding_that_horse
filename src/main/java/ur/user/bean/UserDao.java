package ur.user.bean;

import java.sql.SQLException;

public interface UserDao {

    public void createUser(String sql, String username, String password);
    public String loginUser(String sql, String user, String pwd) throws SQLException;
    public int getUserId(String userSql, String user);
    public void updateUser();
    public void deleteUser();

}
