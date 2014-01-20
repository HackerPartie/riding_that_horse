package ur.model;

public class GetTheDamnRide {
	private String horseName;
	private String horsemanName;
	private String day;
	private String place;
	
	public GetTheDamnRide (String horseName, String horsemanName, String day, String place) {
		this.setHorseName(horseName);
		this.setHorsemanName(horsemanName);
		this.setDay(day);
		this.setPlace(place);
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public String getHorsemanName() {
		return horsemanName;
	}

	public void setHorsemanName(String horsemanName) {
		this.horsemanName = horsemanName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
}
