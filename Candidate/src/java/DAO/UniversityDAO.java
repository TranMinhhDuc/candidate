package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.University;

public class UniversityDAO extends DAO{
private List<University> universities;
	
	public UniversityDAO() {
		super();
	}

	public List<University> getUniversity(String universityName) {
		universities = new ArrayList<>();
		System.out.println(universityName);
	    String sql = "SELECT id, name FROM university " +
	                 "WHERE name LIKE ? " +
	                 "ORDER BY name ASC";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, "%" + universityName + "%");
	        ResultSet rs = ps.executeQuery();
	        
            while (rs.next()) {
                University university = new University();
                university.setId(rs.getInt("id"));
                university.setUniversityName(rs.getString("name"));
                universities.add(university);
            }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return universities;
	}

    public boolean addUniversity(String name) {
        String sql = "INSERT INTO university (name) VALUES (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            int rowAdd = ps.executeUpdate();
            return rowAdd > 0;
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return false;
		}
    }

    public boolean updateUniversity(int id, String name) {
        String sql = "UPDATE university SET name = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            int rowUpdate = ps.executeUpdate();
            
            return rowUpdate > 0;
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return false;
		}
    }

    public boolean deleteUniversity(int id) {
        String sql = "DELETE FROM university WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowDelete = ps.executeUpdate();
            return rowDelete > 0;
        }catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
}
