package dao;

public class Subject {

	/**
	 * @param name
	 * @param type
	 * @param max
	 * @param min
	 */
	public Subject(String name, String type, String max, String min) {
		this.name = name;
		this.type = type;
		this.max = max;
		this.min = min;
	}
	private String name;
	private String type;
	private String max;
	private String min;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	
}
