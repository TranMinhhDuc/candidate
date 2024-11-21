package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.ExperienceCandidateDTO;

public class ExperienceCandidateDAO extends DAO {
    public ExperienceCandidateDAO() {
        super();
    }

    public List<ExperienceCandidateDTO> searchExperienceCandidate(int page, String firstName, String lastName, String skillName) {
        List<ExperienceCandidateDTO> candidates = new ArrayList<>();
        String sql = "SELECT " +
                     "cs.id AS candidateSkillId, " +
                     "c.id AS candidateId, " +
                     "s.id AS skillId, " +
                     "c.firstName, " +
                     "c.lastName, " +
                     "s.name AS skillName, " +
                     "c.birthDate AS birth, " +
                     "c.address, " +
                     "c.phone, " +
                     "c.email " +
                     "FROM CandidateSkill cs " +
                     "JOIN Candidate c ON cs.candidateId = c.id " +
                     "JOIN Skill s ON cs.skillId = s.id "
                     + "WHERE c.firstName LIKE ? AND c.lastName LIKE ? AND s.name LIKE ?"
                     + "ORDER BY c.lastName ASC "
                     + "LIMIT 10 OFFSET ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");
            ps.setString(3, "%" + skillName + "%");
            ps.setInt(4, page*10);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExperienceCandidateDTO candidate = new ExperienceCandidateDTO();
                candidate.setCandidateSkillId(rs.getInt("candidateSkillId"));
                candidate.setCandidateId(rs.getInt("candidateId"));
                candidate.setSkillId(rs.getInt("skillId"));
                candidate.setFirstName(rs.getString("firstName"));
                candidate.setLastName(rs.getString("lastName"));
                candidate.setSkillName(rs.getString("skillName"));
                candidate.setBirth(rs.getString("birth"));
                candidate.setAddress(rs.getString("address"));
                candidate.setPhone(rs.getString("phone"));
                candidate.setEmail(rs.getString("email"));
                candidates.add(candidate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }
    
    public boolean deleteExperienceCandidate(int candidateSkillId) {
        String sql = "DELETE FROM CandidateSkill WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, candidateSkillId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateExperienceCandidate(int id, int skillId) {
        String sql = "UPDATE CandidateSkill " +
                     "SET skillId = ? " +
                     "WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, skillId);
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addSkillCandidate(int candidateId, int skillId){
        String query = "INSERT INTO candidateskill (candidateId, skillId)"
                + "VALUES (?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, candidateId);
            ps.setInt(2, skillId);
            
            int rowAdd = ps.executeUpdate();
            return rowAdd > 0;
        } catch (Exception e) {
            return false;
        }
    }

}
