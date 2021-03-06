package ur.post.model;

public class Post {

	private int id;
	private String title;
	private String body;
	private int userId;

	public Post(int id, String title, String body, int userId) {
		this.setId(id);
		this.setTitle(title);
		this.setBody(body);
		this.setUserId(userId);
	}

	public Post() {
		// TODO Auto-generated constructor stub
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
