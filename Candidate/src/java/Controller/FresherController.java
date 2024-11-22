package Controller;

import DAO.FresherCandidateDAO;
import DAO.UniversityDAO;
import DTO.FresherCandidateDTO;
import Model.University;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/fresher")
public class FresherController extends HttpServlet{

    private FresherCandidateDAO fresherDAO;
    private UniversityDAO universityDAO;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        
        fresherDAO = new FresherCandidateDAO();
        universityDAO = new UniversityDAO();
        
        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")): 0;
        String firstName = req.getParameter("firstName") != null ? req.getParameter("firstName"):"";
        String lastName = req.getParameter("lastName") != null ? req.getParameter("lastName"):"";
        String graduationTime = req.getParameter("graduationTime") != null ? req.getParameter("graduationTime"):"";
        String graduationRank = req.getParameter("rank") != null ? req.getParameter("rank"):"";
        String universityName = req.getParameter("universityName") != null ? req.getParameter("universityName"):"";
        String sortBy = req.getParameter("sortBy") != null ? req.getParameter("sortBy"):"c.lastName";
        String direction = req.getParameter("direction") != null ? req.getParameter("direction"):"ASC";
        
        List<University> universities = universityDAO.getUniversity("");
        List<FresherCandidateDTO> freshers = fresherDAO.searchFresherCandidate(firstName, lastName, graduationTime, graduationRank, universityName, page, sortBy, direction);
        
       req.setAttribute("universities", universities);
       req.setAttribute("freshers", freshers);
       req.setAttribute("currentPage", page);
       req.setAttribute("firstName", firstName);
       req.setAttribute("lastName", lastName);
       req.setAttribute("graduationTime", graduationTime);
       req.setAttribute("graduationRank", graduationRank);
       req.setAttribute("universityName", universityName);
       req.setAttribute("sortBy", sortBy);
       req.setAttribute("direction", direction);
       req.setAttribute("totalPage", fresherDAO.totalPage);
        RequestDispatcher dispatcher = req.getRequestDispatcher("FresherCandidate.jsp");
        dispatcher.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        System.out.println(action);
        fresherDAO = new FresherCandidateDAO();
        String message = "";

        if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            int universityId = Integer.parseInt(req.getParameter("universityId"));
            String graduationTime = req.getParameter("graduationTime");
            String rank = req.getParameter("rank");

            boolean success = fresherDAO.updateFresherCandidate(id, universityId, graduationTime, rank);
            System.out.println(success);
            message = success ? "Cập nhật thành công" : "Cập nhật thất bại";
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean success = fresherDAO.deleteFresherCandidate(id);
            message =  success ? "Xóa thành công" : "Xóa thất bại";
        }
        req.getSession().setAttribute("message", message);
        resp.sendRedirect("fresher");
    }
}
