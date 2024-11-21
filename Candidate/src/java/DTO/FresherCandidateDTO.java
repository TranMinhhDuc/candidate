package DTO;

public class FresherCandidateDTO {
        private int FresherCandidateId;
	private int candidateId;
        private int universityId;
	private String firstName;
	private String lastName;
        private String graduationtime;
        private String rank;
        private String universityName;
	private String birth;
	private String address;
	private String phone;
	private String email;

    public int getFresherCandidateId() {
        return FresherCandidateId;
    }

    public void setFresherCandidateId(int FresherCandidateId) {
        this.FresherCandidateId = FresherCandidateId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
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

    public String getGrauduationtime() {
        return graduationtime;
    }

    public void setGrauduationtime(String grauduationtime) {
        this.graduationtime = grauduationtime;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
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
        
}
