package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CandidateDAO;
import DAO.ExperienceCandidateDAO;
import DAO.FresherCandidateDAO;
import DAO.InternCandidateDAO;
import DAO.MajorDAO;
import DAO.SkillDAO;
import DAO.UniversityDAO;
import Model.Candidate;
import Model.Major;
import Model.Skill;
import Model.University;

@WebServlet(urlPatterns = "/candidate")
public class CandidateController extends HttpServlet{
	
    private CandidateDAO candidateDAO;
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

            candidateDAO = new CandidateDAO();
            SkillDAO skillDAO = new SkillDAO();
            UniversityDAO universityDAO = new UniversityDAO();
            MajorDAO majorDAO = new MajorDAO();

            int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")):0;
            String firstName = req.getParameter("firstName") != null ? req.getParameter("firstName"):"";
            String lastName = req.getParameter("lastName") != null ? req.getParameter("lastName"):"";
            String birthDate = req.getParameter("birth") != null ? req.getParameter("birth"):"";
            String Address = req.getParameter("address") != null ? req.getParameter("address"):"";
            String candidateType = req.getParameter("candidateType") != null ? req.getParameter("candidateType"):"";
            
            List<Skill> skills = skillDAO.getSkills("");
            List<University> universities = universityDAO.getUniversity("");
            List<Major> majors = majorDAO.getMajors("");

            List<Candidate> candidates =  candidateDAO.searchCandidate(page, firstName, lastName, birthDate, Address, candidateType);
            req.setAttribute("candidates", candidates);
            req.setAttribute("currentPage", page);
            req.setAttribute("skills", skills);
            req.setAttribute("universities", universities);
            req.setAttribute("majors", majors);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Candidate.jsp");
            dispatcher.forward(req, resp);
    }
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        candidateDAO = new CandidateDAO();
        Candidate candidate = new Candidate();
        InternCandidateDAO internDAO = new InternCandidateDAO();
        ExperienceCandidateDAO exDAO = new ExperienceCandidateDAO();
        FresherCandidateDAO fresherDAO = new FresherCandidateDAO();
        
        String message = "";

        switch (action) {
            case "add":

            candidate.setFirstName(req.getParameter("firstName"));
            candidate.setLastName(req.getParameter("lastName"));
            candidate.setBirthDate(Integer.parseInt(req.getParameter("birthDate")));
            candidate.setAddress(req.getParameter("address"));
            candidate.setPhone(req.getParameter("phone"));
            candidate.setEmail(req.getParameter("email"));
            candidate.setCandidateType(req.getParameter("candidateType"));

            message = candidateDAO.addCandidate(candidate)?"thêm thành công":"thêm không thành công";

            resp.sendRedirect("candidate");
            break;

            case "delete":
                String candidateId = req.getParameter("candidateid");
                System.out.println(candidateId);
                message = candidateDAO.deleteCandidate(Integer.parseInt(candidateId)) ? "xóa thành công":"xóa không thành công";
                resp.sendRedirect("candidate");
                break;

            case "update":

                candidate.setId(Integer.parseInt(req.getParameter("id")));
                candidate.setFirstName(req.getParameter("firstName"));
                candidate.setLastName(req.getParameter("lastName"));
                candidate.setBirthDate(Integer.parseInt(req.getParameter("birthDate")));
                candidate.setAddress(req.getParameter("address"));
                candidate.setPhone(req.getParameter("phone"));
                candidate.setEmail(req.getParameter("email"));
                candidate.setCandidateType(req.getParameter("candidateType"));
                message = candidateDAO.updateCandidate(candidate) ? "update thành công":"update Thất bại";


                resp.sendRedirect("candidate");
                break;
            
            case "addSkill":
                System.out.println(action);
                boolean addskill = false ;
                candidateId = req.getParameter("candidateId");
                String skillId = req.getParameter("skillId");
                if(candidateId != null && skillId != null){
                   addskill = exDAO.addSkillCandidate(Integer.parseInt(candidateId), Integer.parseInt(skillId));
                }
                message = addskill ? "add skill thành công": "add skill thất bại";
                resp.sendRedirect("candidate");
                break;
                
            case "addIntern":
                System.out.println(action);
                
                candidateId = req.getParameter("candidateId");
                int universityId = Integer.parseInt(req.getParameter("universityId"));
                String semester = req.getParameter("semester");
                int majorId = Integer.parseInt(req.getParameter("majorId"));
                message = internDAO.addInternCandidate(Integer.parseInt(candidateId), universityId, majorId, semester) ? "add intern thành công":"add intern thất bại";
                resp.sendRedirect("candidate");
                break;
                
            case "addFresher":
                candidateId = req.getParameter("candidateId");
                universityId = Integer.parseInt(req.getParameter("universityId"));
                String graduationYear = req.getParameter("graduationYear");
                String rank = req.getParameter("rank");
                message = fresherDAO.addFresherCandidate(Integer.parseInt(candidateId), universityId, graduationYear, rank) ? "add fresher thành công":"add fresher thất bại";
                
                resp.sendRedirect("candidate");
                break;
        }   
        
        req.getSession().setAttribute("message", message);
    }
}
