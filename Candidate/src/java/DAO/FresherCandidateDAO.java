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
    
    public static int totalPage;
    private List<FresherCandidateDTO> candidates;
    
    public List<FresherCandidateDTO> searchFresherCandidate(String firstName, String lastName, String graduationTime, String graduationRank, String universityName, int page, 
            String sortBy, String direction) {
        
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
                        "ORDER BY " + sortBy + " " + direction +
                        " LIMIT 10 OFFSET ?;";
        
        String totalPageQuery = "SELECT CEIL(COUNT(*) / 10) AS totalPage "
                + "FROM freshercandidate fc " +
                        "JOIN Candidate c ON fc.candidateId = c.id " +
                        "JOIN university u ON fc.universityId = u.id " +
                        "WHERE c.firstName LIKE ? "
                        + "AND c.lastName LIKE ? "
                        + "AND fc.graduationTime LIKE ? "
                        + "AND fc.graduationRank LIKE ? "
                        + "AND u.name LIKE ?";
        
        candidates = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement psTotalPage = con.prepareStatement(totalPageQuery);
            
            ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");
            ps.setString(3, "%" + graduationTime + "%");
            ps.setString(4, "%" + graduationRank + "%");
            ps.setString(5, "%" + universityName + "%");
            ps.setInt(6, page * 10);
            
            psTotalPage.setString(1, "%" + firstName + "%");
            psTotalPage.setString(2, "%" + lastName + "%");
            psTotalPage.setString(3, "%" + graduationTime + "%");
            psTotalPage.setString(4, "%" + graduationRank + "%");
            psTotalPage.setString(5, "%" + universityName + "%");
            
            ResultSet rs = ps.executeQuery();
            ResultSet rsTotalPage = psTotalPage.executeQuery();
            
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
            
            if(rsTotalPage.next()) {
                totalPage = rsTotalPage.getInt("totalPage");
            }
        } catch (Exception e) {
        }
        
        System.out.println(totalPage);
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
