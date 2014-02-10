package ur.post.model;

public class PostByUser {

    private int id;
    private String title;
    private String body;
    private int userId;
    private String username;

    public PostByUser(int id, String title, String body, String username) {
        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setUsername(username);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
