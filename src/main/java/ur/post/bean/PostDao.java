package ur.post.bean;

import ur.post.model.Post;
import ur.post.model.PostByUser;

import java.sql.SQLException;
import java.util.List;

public interface PostDao {

    public void createPost(String sql, String title, String body, int userId);
    public Post readPost(String sql, int id1) throws SQLException;
    public void updatePost(String sql, int id, String title, String body);
    public void deletePost(String sql, int id);
    public List<PostByUser> getPosts(String sql);

}
