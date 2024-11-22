package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Candidate;

public class CandidateDAO extends DAO{
	
	private Candidate candidate;
	public static int totalPage;
	
	public CandidateDAO() {
		super();
	}
	
	public List<Candidate> getCandidate(int page) {
	    List<Candidate> candidates = new ArrayList<>();
	    String query = "SELECT * FROM candidate ORDER BY lastName ASC LIMIT 10 OFFSET ?;";
	    String queryTotalPage = "SELECT CEIL(COUNT(*) / 10) AS totalPage FROM candidate;";

        try {
        	PreparedStatement ps = con.prepareStatement(query);
        	PreparedStatement psTotalPage = con.prepareStatement(queryTotalPage);
        	ps.setInt(1, page * 10);
        	
        	ResultSet rs = ps.executeQuery();
        	ResultSet rsTotalPage = psTotalPage.executeQuery();
            while (rs.next()) {
                candidate.setId(rs.getInt("id"));
                candidate.setFirstName(rs.getString("FirstName"));
                candidate.setLastName(rs.getString("LastName"));
                candidate.setBirthDate(rs.getInt("BirthDate"));
                candidate.setAddress(rs.getString("Address"));
                candidate.setPhone(rs.getString("Phone"));
                candidate.setEmail(rs.getString("Email"));
                candidate.setCandidateType(rs.getString("CandidateType"));
                candidates.add(candidate);
            }
            if (rsTotalPage.next()) {
            	totalPage = rsTotalPage.getInt("totalPage");
			}
        

        } catch (Exception e) {
        e.printStackTrace();
    }
	    return candidates;
	}
	
    public List<Candidate> searchCandidate(int page, String firstName, String lastName,String birthDate, String address, String candiateType, String sortBy, String direction) {
        List<Candidate> candidates = new ArrayList<>();

        String query = "SELECT * FROM candidate "
            + "WHERE firstName LIKE ? "
            + "AND lastName LIKE ? "
            + "AND birthDate LIKE ? "
            + "AND address LIKE ? "
            + "AND candidateType LIKE ? "
            + "ORDER BY " + sortBy + " " + direction 
            + " LIMIT 10 OFFSET ?;";
        String queryTotalPage = "SELECT CEIL(COUNT(*) / 10) AS totalPage FROM candidate "
            + "WHERE firstName LIKE ? "
            + "AND lastName LIKE ? "
            + "AND birthDate LIKE ? "
            + "AND address LIKE ? "
            + "AND candidateType LIKE ? "
            + "ORDER BY lastName ASC;";

        try{
            PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement psTotalPage = con.prepareStatement(queryTotalPage);

            ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");
            ps.setString(3, "%" + birthDate + "%");
            ps.setString(4, "%" + address + "%");
            ps.setString(5, "%" + candiateType + "%");
            ps.setInt(6, page*10);

            psTotalPage.setString(1, "%" + firstName + "%");
            psTotalPage.setString(2, "%" + lastName + "%");
            psTotalPage.setString(3, "%" + birthDate + "%");
            psTotalPage.setString(4, "%" + address + "%");
            psTotalPage.setString(5, "%" + candiateType + "%");


            ResultSet rs = ps.executeQuery();
            ResultSet rsTotalPage = psTotalPage.executeQuery();

            while(rs.next()) {
                candidate = new Candidate();
                candidate.setId(rs.getInt("id"));
                candidate.setFirstName(rs.getString("FirstName"));
                candidate.setLastName(rs.getString("LastName"));
                candidate.setBirthDate(rs.getInt("BirthDate"));
                candidate.setAddress(rs.getString("Address"));
                candidate.setPhone(rs.getString("Phone"));
                candidate.setEmail(rs.getString("Email"));
                candidate.setCandidateType(rs.getString("CandidateType"));
                candidates.add(candidate);
            }
            if(rsTotalPage.next()) {
                    totalPage = rsTotalPage.getInt("totalPage");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(totalPage);
        return candidates;
    }

    public boolean addCandidate(Candidate candidate) {
        String query = "INSERT INTO candidate (FirstName, LastName, BirthDate, Address, Phone, Email, CandidateType) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, candidate.getFirstName());
            ps.setString(2, candidate.getLastName());
            ps.setInt(3, candidate.getBirthDate());
            ps.setString(4, candidate.getAddress());
            ps.setString(5, candidate.getPhone());
            ps.setString(6, candidate.getEmail());
            ps.setString(7, candidate.getCandidateType());

            int insertRow = ps.executeUpdate();

            return insertRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCandidate(int candidateId) {
            String query = "DELETE FROM candidate WHERE id = ?";

            try {

                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, candidateId);

                    int deleteRow = ps.executeUpdate();

                    return deleteRow > 0;
            } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    return false;
            }
    }

    public boolean updateCandidate(Candidate candidate) {
        String query = "UPDATE candidate SET FirstName = ?, LastName = ?, BirthDate = ?,Address = ?, Phone = ?, Email = ?,  CandidateType = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, candidate.getFirstName());
            ps.setString(2, candidate.getLastName());
            ps.setInt(3, candidate.getBirthDate());
            ps.setString(4, candidate.getAddress());
            ps.setString(5, candidate.getPhone());
            ps.setString(6, candidate.getEmail());
            ps.setString(7, candidate.getCandidateType());
            ps.setInt(8, candidate.getId());

            int updateRow = ps.executeUpdate();

            return updateRow > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}