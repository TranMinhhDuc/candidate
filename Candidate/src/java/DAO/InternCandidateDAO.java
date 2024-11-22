package DAO;

import static DAO.FresherCandidateDAO.totalPage;
import DTO.InternCandidateDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InternCandidateDAO extends DAO{

    public InternCandidateDAO() {
        super();
    }
    
    public static int totalPage;
    
    public List<InternCandidateDTO> searchInternCandidate(String firstName, String lastName, String major, String universityName, int page, String sortBy, String direction) {
        List<InternCandidateDTO> candidates = new ArrayList<>();
        String sql = "SELECT ic.id, c.firstName, c.lastName, c.birthDate, c.address, c.phone, c.email, " +
                    "ic.universityId, u.name AS universityName, m.majorName AS major, ic.semester " +
                    "FROM InternCandidate ic " +
                    "INNER JOIN Candidate c ON ic.candidateId = c.id " +
                    "INNER JOIN University u ON ic.universityId = u.id " +
                    "INNER JOIN Majors m ON ic.majorId = m.id " +
                    "WHERE c.firstName LIKE ? " +
                    "AND c.lastName LIKE ? " +
                    "AND m.majorName LIKE ? " + 
                    "AND u.name LIKE ? " +
                    "ORDER BY " + sortBy + " " + direction +
                    " LIMIT 10 OFFSET ?";

        String totalPageQuery = "SELECT CEIL(COUNT(*) / 10) AS totalPage " +
                    "FROM InternCandidate ic " +
                    "INNER JOIN Candidate c ON ic.candidateId = c.id " +
                    "INNER JOIN University u ON ic.universityId = u.id " +
                    "INNER JOIN Majors m ON ic.majorId = m.id " +
                    "WHERE c.firstName LIKE ? " +
                    "AND c.lastName LIKE ? " +
                    "AND m.majorName LIKE ? " + 
                    "AND u.name LIKE ? ";
                
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            PreparedStatement psTotalPage = con.prepareStatement(totalPageQuery);
            
            ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");
            ps.setString(3, "%" + major + "%");
            ps.setString(4, "%" + universityName + "%");
            ps.setInt(5, page * 10);
            
            psTotalPage.setString(1, "%" + firstName + "%");
            psTotalPage.setString(2, "%" + lastName + "%");
            psTotalPage.setString(3, "%" + major + "%");
            psTotalPage.setString(4, "%" + universityName + "%");

            ResultSet resultSet = ps.executeQuery();
            ResultSet rsTotalPage = psTotalPage.executeQuery();

            while (resultSet.next()) {
                InternCandidateDTO candidate = new InternCandidateDTO();
                candidate.setFresherCandidateId(resultSet.getInt("id"));
                candidate.setFirstName(resultSet.getString("firstName"));
                candidate.setLastName(resultSet.getString("lastName"));
                candidate.setBirth(resultSet.getString("birthDate"));
                candidate.setAddress(resultSet.getString("address"));
                candidate.setPhone(resultSet.getString("phone"));
                candidate.setEmail(resultSet.getString("email"));
                candidate.setUniversityId(resultSet.getInt("universityId"));
                candidate.setUniversityName(resultSet.getString("universityName"));
                candidate.setMajor(resultSet.getString("major"));
                candidate.setSemester(resultSet.getString("semester"));
                candidates.add(candidate);
            }
            if(rsTotalPage.next()) {
                totalPage = rsTotalPage.getInt("totalPage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    public boolean updateInternCandidate(int id, int universityId, String majorId, String semester) {
        String sql = "UPDATE InternCandidate " +
                     "SET universityId = ?, majorId = ?, semester = ? " +
                     "WHERE id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, universityId);
            preparedStatement.setString(2, majorId);
            preparedStatement.setString(3, semester);
            preparedStatement.setInt(4, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteInternCandidate(int id) {
        String sql = "DELETE FROM InternCandidate WHERE id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addInternCandidate(int candidateId, int universityId, int majorId, String semester) {
        String sql = "INSERT INTO InternCandidate (candidateId, universityId, majorId, semester) " +
                     "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, candidateId);
            preparedStatement.setInt(2, universityId);
            preparedStatement.setInt(3, majorId);
            preparedStatement.setString(4, semester);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
