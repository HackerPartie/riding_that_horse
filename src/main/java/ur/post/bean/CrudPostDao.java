package ur.post.bean;

import ur.bean.ConnSource;
import ur.post.model.PostByUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudPostDao extends ConnSource implements PostDao {
	
	public void createPost(String postSql, String title, String body, int userId) {

		PreparedStatement preparedStatement;

		try {
			preparedStatement = getConnection(postSql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, body);
			preparedStatement.setInt(3, userId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    closeConn();
		}
		
	}

    @Override
    public PostByUser readPost(String sql, int id1) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        PostByUser postByUser = null;
        try {
            preparedStatement = getConnection(sql);
            preparedStatement.setInt(1,id1);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String body = resultSet.getString("body");
            String username = resultSet.getString("username");
            postByUser = new PostByUser(id, title, body, username);
        }
        try {
            closeConn();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return postByUser;
    }

    @Override
    public void updatePost(String sql, int id1, String title, String body) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = getConnection(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, body);
			preparedStatement.setInt(3, id1);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeConn();
        }
	}

    @Override
	public void deletePost(String sql, int id1) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = getConnection(sql);
			preparedStatement.setInt(1, id1);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
	}

    @Override
    public List<PostByUser> getPosts(String sql) {

        List<PostByUser> posts = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        preparedStatement = getConnection(sql);
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
                String username = resultSet.getString("username");
                PostByUser postings = new PostByUser(id, title, body, username);
                posts.add(postings);
                for (PostByUser postByUser: posts) {
                    System.out.println(postByUser.getUsername());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        for (PostByUser postByUser: posts) {
            System.out.println(postByUser.getUsername());
        }
        return posts;
    }


}
