package Model;

public class FresherCandidate {
	private int id;
	private int candidateId;
	private int universityId;
	private String graduationTime;
	private String graduationRank;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getGraduationTime() {
		return graduationTime;
	}
	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}
	public String getGraduationRank() {
		return graduationRank;
	}
	public void setGraduationRank(String graduationRank) {
		this.graduationRank = graduationRank;
	}
	
}
