package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UniversityDAO;
import Model.University;

@WebServlet("/university")
public class UniversityController extends HttpServlet {
    
    private UniversityDAO universityDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        String universityName = req.getParameter("universityName") != null
                ? req.getParameter("universityName")
                : "";
        universityDAO = new UniversityDAO();
        
        List<University> universities = universityDAO.getUniversity(universityName);
       req.setAttribute("universities", universities);
       req.getRequestDispatcher("/University.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
    	req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        String action =req.getParameter("action");
        String message = "";
        
        universityDAO = new UniversityDAO();
        
        try {
            if ("add".equalsIgnoreCase(action)) {
                String name =req.getParameter("universityName");
                message = universityDAO.addUniversity(name)?"thêm thành công":"thêm thất bại";
                
            } else if ("edit".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(req.getParameter("universityId"));
                String name =req.getParameter("universityName");
                message = universityDAO.updateUniversity(id, name)?"update thành công":"update thất bại";
            } else if ("delete".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(req.getParameter("universityId"));
                message = universityDAO.deleteUniversity(id) ? "xóa thành công":"xóa thất bại";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        req.getSession().setAttribute("message", message);
        resp.sendRedirect("university");
    }
}

