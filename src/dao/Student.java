package dao;

public class Student {

	/**
	 * @param roll_NO
	 * @param fullname
	 * @param mothername
	 * @param prn
	 * @param collegeName
	 */
	public Student(String roll_NO, String fullname, String mothername, String prn, String collegeName) {
		super();
		this.roll_NO = roll_NO;
		this.fullname = fullname;
		this.mothername = mothername;
		this.prn = prn;
		this.collegeName = collegeName;
	}
	private String roll_NO;
	private String fullname;
	private String mothername;
	private String prn;
	private String collegeName;
	public String getRoll_NO() {
		return roll_NO;
	}
	public void setRoll_NO(String roll_NO) {
		this.roll_NO = roll_NO;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getMothername() {
		return mothername;
	}
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	public String getPrn() {
		return prn;
	}
	public void setPrn(String prn) {
		this.prn = prn;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	
}
