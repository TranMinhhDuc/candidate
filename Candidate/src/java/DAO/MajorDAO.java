package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Major;

public class MajorDAO extends DAO {

    private List<Major> majors;

    public MajorDAO() {
        super();
    }

    public List<Major> getMajors(String nameMajor) {
        majors = new ArrayList<>();
        String sql = "SELECT id, majorName FROM Majors " +
                     "WHERE majorName LIKE ? " +
                     "ORDER BY majorName ASC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nameMajor + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Major major = new Major();
                major.setId(rs.getInt("id"));
                major.setMajorName(rs.getString("majorName"));
                majors.add(major);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return majors;
    }

    public boolean addMajor(String name) {
        String sql = "INSERT INTO Majors (majorName) VALUES (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMajor(int id, String name) {
        String sql = "UPDATE Majors SET majorName = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMajor(int id) {
        String sql = "DELETE FROM Majors WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
