package DAO;

import DTO.FresherCandidateDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FresherCandidateDAO extends DAO{

    public FresherCandidateDAO() {
        super();
    }
    
    private List<FresherCandidateDTO> candidates;
    
    public List<FresherCandidateDTO> searchFresherCandidate(String firstName, String lastName, String graduationTime, String graduationRank, String universityName, int page) {
        
        String query = "SELECT fc.id AS fresherCandidateId, c.id AS candidateId, u.id AS universityId, c.firstName, c.lastName, " +
                        "fc.graduationTime, fc.graduationRank, u.name AS universityName, " +
                        "c.birthDate AS birth, c.address, c.phone, c.email " +
                        "FROM freshercandidate fc " +
                        "JOIN Candidate c ON fc.candidateId = c.id " +
                        "JOIN university u ON fc.universityId = u.id " +
                        "WHERE c.firstName LIKE ? "
                        + "AND c.lastName LIKE ? "
                        + "AND fc.graduationTime LIKE ? "
                        + "AND fc.graduationRank LIKE ? "
                        + "AND u.name LIKE ?" +
                        "ORDER BY c.lastName ASC " +
                        "LIMIT 10 OFFSET ?;";
        
        candidates = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");
            ps.setString(3, "%" + graduationTime + "%");
            ps.setString(4, "%" + graduationRank + "%");
            ps.setString(5, "%" + universityName + "%");
            ps.setInt(6, page * 10);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                
                FresherCandidateDTO candidate = new FresherCandidateDTO();
                candidate.setFresherCandidateId(rs.getInt("fresherCandidateId"));
                candidate.setCandidateId(rs.getInt("candidateId"));
                candidate.setUniversityId(rs.getInt("universityId"));
                candidate.setFirstName(rs.getString("firstName"));
                candidate.setLastName(rs.getString("lastName"));
                candidate.setGrauduationtime(rs.getString("graduationTime"));
                candidate.setRank(rs.getString("graduationRank"));
                candidate.setUniversityName(rs.getString("universityName"));
                candidate.setBirth(rs.getString("birth"));
                candidate.setAddress(rs.getString("address"));
                candidate.setPhone(rs.getString("Phone"));
                candidate.setEmail(rs.getString("email"));
                
                candidates.add(candidate);
            }
        } catch (Exception e) {
        }
        return candidates;
    }
    
    public boolean addFresherCandidate(int candidateId, int universityId, String GraduationTime, String rank ) {
        String query = "INSERT INTO freshercandidate (candidateId, universityId, graduationTime, graduationRank) " +
                       "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, candidateId);
            ps.setInt(2, universityId);
            ps.setString(3, GraduationTime);
            ps.setString(4, rank);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateFresherCandidate(int id, int universityId, String graduationTime, String rank ) {
        String query = "UPDATE freshercandidate SET universityId = ?, graduationTime = ?, graduationRank = ? " +
                       "WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, universityId);
            ps.setString(2, graduationTime);
            ps.setString(3, rank);
            ps.setInt(4, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteFresherCandidate(int id) {
        String query = "DELETE FROM freshercandidate WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
