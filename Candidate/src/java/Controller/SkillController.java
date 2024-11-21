package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SkillDAO;
import Model.Skill;

@WebServlet(urlPatterns = "/skill")
public class SkillController extends HttpServlet{
	
    private SkillDAO skillDAO;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

            skillDAO = new SkillDAO();

            String skillName = req.getParameter("skillName") != null ? req.getParameter("skillName"):"";
            List<Skill> skills = skillDAO.getSkills(skillName);

            req.setAttribute("skills", skills);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Skill.jsp");
            dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String message = "";
        
        String action = request.getParameter("action");

        skillDAO = new SkillDAO();
        try {
            if ("add".equals(action)) {
                String skillName = request.getParameter("skillName");
                message = skillDAO.addSkill(skillName)? "add thành công":"add thất bại";
            } else if ("edit".equals(action)) {
                int skillId = Integer.parseInt(request.getParameter("skillId"));
                String skillName = request.getParameter("skillName");
                message = skillDAO.updateSkill(skillId, skillName)?"update thành công":"update thất bại";
            } else if ("delete".equals(action)) {
                int skillId = Integer.parseInt(request.getParameter("skillId"));
                message = skillDAO.deleteSkill(skillId)?"xóa thành công":"xóa thất bại";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        request.getSession().setAttribute("message", message);
        response.sendRedirect("skill");
    }
}
