package Controller;

import DAO.InternCandidateDAO;
import DAO.UniversityDAO;
import DTO.InternCandidateDTO;
import Model.University;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/intern")
public class InternCandidateController extends HttpServlet {

    private InternCandidateDAO internDAO;
    private UniversityDAO universityDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        internDAO = new InternCandidateDAO();
        universityDAO = new UniversityDAO();

        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 0;
        String firstName = req.getParameter("firstName") != null ? req.getParameter("firstName") : "";
        String lastName = req.getParameter("lastName") != null ? req.getParameter("lastName") : "";
        String major = req.getParameter("major") != null ? req.getParameter("major") : "";
        String universityName = req.getParameter("universityName") != null ? req.getParameter("universityName") : "";

        List<University> universities = universityDAO.getUniversity("");
        List<InternCandidateDTO> interns = internDAO.searchInternCandidate(firstName, lastName, major, universityName, page);

        req.setAttribute("universities", universities);
        req.setAttribute("interns", interns);
        RequestDispatcher dispatcher = req.getRequestDispatcher("InternCandidate.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        internDAO = new InternCandidateDAO();
        String message ="";

        if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            int universityId = Integer.parseInt(req.getParameter("universityId"));
            String major = req.getParameter("major");
            String semester = req.getParameter("semester");

            boolean success = internDAO.updateInternCandidate(id, universityId, major, semester);
            message = success ? "Cập nhật thành công" : "Cập nhật thất bại";
        } else if ("delete".equals(action)) {
            
            int id = Integer.parseInt(req.getParameter("id"));
            boolean success = internDAO.deleteInternCandidate(id);
            message = success ? "Xóa thành công" : "Xóa thất bại";
        }
        
        req.getSession().setAttribute("message", message);
        resp.sendRedirect("intern");
    }
}
