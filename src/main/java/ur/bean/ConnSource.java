package ur.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnSource extends DataSource {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public PreparedStatement getConnection(String sql){
        try {
            connection = connectDb();
            preparedStatement = connection.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(preparedStatement);
        return preparedStatement;
    }

    public void closeConn(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
