package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ExperienceCandidateDAO;
import DAO.SkillDAO;
import DTO.ExperienceCandidateDTO;
import Model.Skill;

@WebServlet(urlPatterns = "/experience-candidate")
public class ExperienceCandidateController extends HttpServlet {
	
    private ExperienceCandidateDAO exCandidateDAO;
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        exCandidateDAO = new ExperienceCandidateDAO();
        SkillDAO skillDAO = new SkillDAO();
        
        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")):0;
        String firstName = req.getParameter("firstName") != null ? req.getParameter("firstName"):"";
        String lastName = req.getParameter("lastName") != null ? req.getParameter("lastName"):"";
        String skillName = req.getParameter("skillName") != null ? req.getParameter("skillName"):"";

        List<Skill> skills = skillDAO.getSkills("");
        req.setAttribute("skills", skills);

        List<ExperienceCandidateDTO> experiences = exCandidateDAO.searchExperienceCandidate(page, firstName, lastName, skillName);

        req.setAttribute("currentPage", page);
        req.setAttribute("candidates", experiences);
        RequestDispatcher dispatcher = req.getRequestDispatcher("ExperienceCandidate.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        exCandidateDAO = new ExperienceCandidateDAO();
        
        
        String action = req.getParameter("action");
        
        int candidateSkillId;
        String message = "";
        switch (action) {
            case "delete":
                candidateSkillId = Integer.parseInt(req.getParameter("candidateSkillId"));
                message = exCandidateDAO.deleteExperienceCandidate(candidateSkillId) ? "xóa thành công":"xóa thất bại";
                
                resp.sendRedirect("/candidate/experience-candidate");
                break;
                
            case "update":
                candidateSkillId = Integer.parseInt(req.getParameter("id"));
                int skillId = Integer.parseInt(req.getParameter("skillId"));
                
                message = exCandidateDAO.updateExperienceCandidate(candidateSkillId, skillId) ? "update thành công":"update thất bại";
                
                resp.sendRedirect("/candidate/experience-candidate");
                break;
            default:
                throw new AssertionError();
        }
        
        
        req.getSession().setAttribute("message", message);
    }
}
