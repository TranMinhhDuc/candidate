package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MajorDAO;
import Model.Major;

@WebServlet(urlPatterns = "/major")
public class MajorController extends HttpServlet {

    private MajorDAO majorDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        majorDAO = new MajorDAO();

        String majorName = req.getParameter("majorName") != null ? req.getParameter("majorName") : "";
        List<Major> majors = majorDAO.getMajors(majorName);

        req.setAttribute("majors", majors);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Major.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String message = "";

        majorDAO = new MajorDAO();
        try {
            if ("add".equals(action)) {
                String majorName = request.getParameter("majorName");
                message = majorDAO.addMajor(majorName)?"add thành công":"add thất bại";
            } else if ("edit".equals(action)) {
                int majorId = Integer.parseInt(request.getParameter("majorId"));
                String majorName = request.getParameter("majorName");
                message = majorDAO.updateMajor(majorId, majorName)?"update thành công":"update thất bại";
            } else if ("delete".equals(action)) {
                int majorId = Integer.parseInt(request.getParameter("majorId"));
                message = majorDAO.deleteMajor(majorId)?"xóa thành công":"xóa thất bại";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        request.getSession().setAttribute("message", message);
        response.sendRedirect("major");
    }
}
