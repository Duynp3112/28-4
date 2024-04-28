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
public class AddCampaign extends HttpServlet {

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
            out.println("<title>Servlet AddCampaign</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCampaign at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("addcampaign.jsp").forward(request, response);
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
        CustomerDao cdao = new CustomerDao();
        String chiendichID = cdao.getCDId();
        String tenChiendich = request.getParameter("name");
        String motaChitiet = request.getParameter("mota");
        String loaiChiendich = request.getParameter("type");
        String mucTieu = request.getParameter("muctieu");
        double chiPhi = Double.parseDouble(request.getParameter("chiphi"));
        String ngayBatDau = request.getParameter("start");
        String ngayKetThuc = request.getParameter("end");
        String employee = request.getParameter("employee");
        CustomerDao customerDao = new CustomerDao();
        String nhanVienID = customerDao.getNhanVienIDByFullName(employee);
        System.out.println(employee);
        System.out.println(chiendichID);
        System.out.println(nhanVienID);
        System.out.println(ngayBatDau);
        System.out.println(ngayKetThuc);
        if (nhanVienID != null) {
            ChienDich chiendich = new ChienDich(chiendichID, tenChiendich, motaChitiet, loaiChiendich, mucTieu, chiPhi, ngayBatDau, ngayKetThuc, nhanVienID);
            customerDao.addChienDich(chiendich);
            ArrayList<ChienDich> list = cdao.getListChienDich();
            request.setAttribute("listCp", list);
            request.getRequestDispatcher("chiendich.jsp").forward(request, response);
        } else {
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
