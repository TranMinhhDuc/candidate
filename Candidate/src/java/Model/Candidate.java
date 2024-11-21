package Model;

public class Candidate {
	private int id;
	private String firstName;
	private String lastName;
	private int birthDate;
	private String address;
	private String phone;
	private String email;
	private String candidateType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(int i) {
		this.birthDate = i;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCandidateType() {
		return candidateType;
	}
	public void setCandidateType(String candidateType) {
		this.candidateType = candidateType;
	}
	
}