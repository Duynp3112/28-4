/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CustomerDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Contract;

/**
 *
 * @author ADMIN
 */
public class UpdateContract extends HttpServlet {

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
            out.println("<title>Servlet UpdateContract</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateContract at " + request.getContextPath() + "</h1>");
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
        CustomerDao p = new CustomerDao();
        String contractid = request.getParameter("contract_id");
        Contract contract = p.getContractByID(contractid);
        ArrayList<String> list2 = p.getAllEmployee();
        request.setAttribute("emp", list2);
        request.setAttribute("contract", contract);
        request.getRequestDispatcher("updatecontract.jsp").forward(request, response);
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
            try {
                CustomerDao cdao = new CustomerDao();
                UserDao udao = new UserDao();
                String userID = request.getParameter("userid");
                String name = request.getParameter("name");
                String type = request.getParameter("type");
                String product = request.getParameter("product");
                String customer = request.getParameter("customer");
                String employee = request.getParameter("employee");
                String start = request.getParameter("start");
                System.out.println(userID);
                if (type.equals("Có thời hạn")) {
                    String enddate = request.getParameter("end");
                    // Kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
                    if (isValidEndDate(start, enddate)) {
                        Contract c = new Contract(userID, name, type, product, start, enddate, employee, customer);
                        cdao.updateContract(c);
                        String employeeID = cdao.getEmployeeIDByName(employee);
                        String customerID = udao.getUserIDByName(customer);
                        System.out.println(customerID);

                        if (employeeID != null && customerID != null) {
                            cdao.updateNhanVienHopDongUsers(userID, employeeID, customerID);
                        } else {
                        }
                        request.setAttribute("message", "Sửa Thành Công!");
                        ArrayList<Contract> list = cdao.getListContract();
                        request.setAttribute("listCt", list);
                        request.getRequestDispatcher("contract.jsp").forward(request, response);
                    } else {
                        CustomerDao p = new CustomerDao();
                        Contract contract = p.getContractByID(userID);
                        ArrayList<String> list2 = p.getAllEmployee();
                        request.setAttribute("emp", list2);
                        request.setAttribute("contract", contract);
                        request.setAttribute("message", "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
                        request.getRequestDispatcher("updatecontract.jsp").forward(request, response);
                    }
                } else {
                    String enddate = null;
                    Contract c = new Contract(userID, name, type, product, start, enddate, employee, customer);
                    cdao.updateContract(c);
                    // Lấy ID của nhân viên và khách hàng từ tên
                    String employeeID = cdao.getEmployeeIDByName(employee);
                    String customerID = udao.getUserIDByName(customer);
                    System.out.println(employeeID);
                    System.out.println(customerID);
                    System.out.println(product);
                    if (employeeID != null && customerID != null) {
                        cdao.updateNhanVienHopDongUsers(userID, employeeID, customerID);
                    } else {
                    }
                    request.setAttribute("message", "Sửa Thành Công!");
                    ArrayList<Contract> list = cdao.getListContract();
                    request.setAttribute("listCt", list);
                    request.getRequestDispatcher("contract.jsp").forward(request, response);
                }

            } catch (SQLException ex) {
                Logger.getLogger(AddContract.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equals("delete")) {
            String id = request.getParameter("project_id");
            CustomerDao cdao = new CustomerDao();
            cdao.delete(id);
            cdao.delete2(id);
            request.setAttribute("message", "Xóa thành công!");
            ArrayList<Contract> list = cdao.getListContract();
            request.setAttribute("listCt", list);
            request.getRequestDispatcher("contract.jsp").forward(request, response);
        }
    }

    private boolean isValidEndDate(String start, String end) {
        try {
            // Parse start and end dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);
            return endDate.after(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
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
