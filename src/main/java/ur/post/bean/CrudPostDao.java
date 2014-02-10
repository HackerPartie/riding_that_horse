package ur.post.bean;

import ur.bean.ConnSource;
import ur.post.model.Post;
import ur.post.model.PostByUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudPostDao extends ConnSource implements PostDao {
	
	public void createPost(String sql, String title, String body, int userId) {

		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = getConnection(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, body);
			System.out.println(userId);
			preparedStatement.setInt(3, userId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    closeConn();
		}
		
	}

    @Override
    public Post readPost(String sql, int id1) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Post post = null;
        preparedStatement = getConnection(sql);
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
            int userId = resultSet.getInt("userId");
            post = new Post(id, title, body, userId);
        }
        closeConn();
        return post;
    }

    @Override
    public void updatePost(String sql, int id1, String title, String body) {
		PreparedStatement preparedStatement = null;
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
		PreparedStatement preparedStatement = null;
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        preparedStatement = getConnection(sql);
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
                String username = resultSet.getString("username");
                //System.out.println(username);
                //System.out.println(title);
				//int userId = resultSet.getInt("userId");
                PostByUser postings = new PostByUser(id, title, body, username);
                System.out.println(postings.getClass());
                System.out.println(postings.getUsername());
                System.out.println(postings.getTitle());
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
