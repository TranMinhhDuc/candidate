package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Skill;

public class SkillDAO extends DAO{
	
	private List<Skill> skills;
	
	public SkillDAO() {
		super();
	}

	public List<Skill> getSkills(String nameSkill) {
	    skills = new ArrayList<>();
	    String sql = "SELECT id, name FROM Skill " +
	                 "WHERE name LIKE ? " +
	                 "ORDER BY name ASC";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, "%" + nameSkill + "%");
	        ResultSet rs = ps.executeQuery();
	        
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getInt("id"));
                skill.setName(rs.getString("name"));
                skills.add(skill);
            }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return skills;
	}

    public boolean addSkill(String name) {
        String sql = "INSERT INTO Skill (name) VALUES (?)";
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

    public boolean updateSkill(int id, String name) {
        String sql = "UPDATE Skill SET name = ? WHERE id = ?";
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

    public boolean deleteSkill(int id) {
        String sql = "DELETE FROM Skill WHERE id = ?";
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
