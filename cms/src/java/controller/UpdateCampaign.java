/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CustomerDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.ChienDich;

/**
 *
 * @author ADMIN
 */
public class UpdateCampaign extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateCampaign</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCampaign at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerDao cdao = new CustomerDao();
        String id = request.getParameter("chiendich_id");
        ChienDich c = cdao.getChienDichById(id);
        request.setAttribute("c", c);
        request.getRequestDispatcher("updatecampaign.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("update")) {
            CustomerDao cdao = new CustomerDao();
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String mota = request.getParameter("mota");
            String muctieu = request.getParameter("muctieu");
            String type = request.getParameter("loai");
            String chiphi = request.getParameter("chiphi");
            String employee = request.getParameter("employee");
            String start = request.getParameter("start");
            String end = request.getParameter("end");

            // Lấy mã nhân viên từ tên nhân viên
            String nhanVienID = cdao.getNhanVienIDByFullName(employee);

            // Tạo đối tượng ChienDich mới với thông tin cập nhật
            ChienDich cd = new ChienDich(id, name, mota, type, muctieu, Double.parseDouble(chiphi), start, end, nhanVienID);
            // Thực hiện cập nhật thông tin chiến dịch
            cdao.updateChienDich(cd);
            ArrayList<ChienDich> list = cdao.getListChienDich();
            request.setAttribute("listCp", list);
            request.getRequestDispatcher("customer.jsp").forward(request, response);
        }
        if (action.equals("delete")) {
            CustomerDao cdao = new CustomerDao();
            String userID = request.getParameter("project_id");
//            cdao.deleteC2(userID);
//            cdao.deleteC(userID);
            request.setAttribute("message", "Xóa thành công!");
            CustomerDao p = new CustomerDao();
            ArrayList<ChienDich> list = p.getListChienDich();
            request.setAttribute("listC", list);
            request.getRequestDispatcher("customer.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
