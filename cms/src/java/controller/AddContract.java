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
public class AddContract extends HttpServlet {

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
            out.println("<title>Servlet AddContract</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddContract at " + request.getContextPath() + "</h1>");
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
        ArrayList<String> list = p.getAllCustomer();
        request.setAttribute("cus", list);
        ArrayList<String> list2 = p.getAllEmployee();
        request.setAttribute("emp", list2);
        request.getRequestDispatcher("addcontract.jsp").forward(request, response);
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
        try {
            CustomerDao cdao = new CustomerDao();
            UserDao udao = new UserDao();
            String id = cdao.getId();
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String product = request.getParameter("product");
            String customer = request.getParameter("customer");
            String employee = request.getParameter("employee");
            String start = request.getParameter("start");
            

            if (type.equals("Có thời hạn")) {
                String enddate = request.getParameter("end");

                // Kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
                if (isValidEndDate(start, enddate)) {
                    Contract c = new Contract(id, name, type, product, start, enddate, employee, customer);
                    cdao.addContract(c);
                    String employeeID = cdao.getEmployeeIDByName(employee);
                    String customerID = udao.getUserIDByName(customer);

                    if (employeeID != null && customerID != null) {
                        cdao.insertNhanVienHopDongUsers(id, employeeID, customerID);
                    } else {
                    }
                    ArrayList<Contract> list = cdao.getListContract();
                    request.setAttribute("listCt", list);
                    request.getRequestDispatcher("contract.jsp").forward(request, response);
                } else {
                    CustomerDao p = new CustomerDao();
                    ArrayList<String> list = p.getAllCustomer();
                    request.setAttribute("cus", list);
                    ArrayList<String> list2 = p.getAllEmployee();
                    request.setAttribute("emp", list2);
                    request.setAttribute("message", "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
                    request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                }
            } else {
                String enddate = null;
                Contract c = new Contract(id, name, type, product, start, enddate, employee, customer);
                cdao.addContract(c);
                // Lấy ID của nhân viên và khách hàng từ tên
                String employeeID = cdao.getEmployeeIDByName(employee);
                String customerID = udao.getUserIDByName(customer);
                System.out.println(employeeID);
                System.out.println(customerID);
                System.out.println(product);
                // Thêm thông tin vào bảng GiaoDich_NhanVien_HopDong_Users
                if (employeeID != null && customerID != null) {
                    cdao.insertNhanVienHopDongUsers(id, employeeID, customerID);
                } else {
                    // Xử lý khi không tìm thấy ID của nhân viên hoặc khách hàng
                    // (ví dụ: hiển thị thông báo lỗi)
                }
                ArrayList<Contract> list = cdao.getListContract();
                request.setAttribute("listCt", list);
                request.getRequestDispatcher("contract.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddContract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Validate end date against start date

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
