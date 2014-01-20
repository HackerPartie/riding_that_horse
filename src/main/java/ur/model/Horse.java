package ur.model;

public class Horse {
	
	private int id;
	private String horseName;
	
	public Horse(String horseName) {
		this.setHorseName(horseName);
	}
	
	public String getHorseName() {
		return horseName;
	}
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	
	
}
