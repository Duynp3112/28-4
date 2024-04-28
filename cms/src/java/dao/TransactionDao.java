/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Transaction;

/**
 *
 * @author ADMIN
 */
public class TransactionDao extends BaseDao {

    PreparedStatement pstm;
    ResultSet rs;

    public ArrayList<Transaction> getListTransaction() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT g.Giaodich_ID, g.TenGD, g.Sotien, u2.FullName AS UserName, u.FullName AS EmployeeName "
                    + "FROM GiaoDich g "
                    + "JOIN NhanVien_GiaoDich_Users gn ON g.Giaodich_ID = gn.Giaodich_ID "
                    + "JOIN NhanVien n ON gn.NhanVienID = n.NhanVienID "
                    + "JOIN Users u ON n.UserID = u.UserID "
                    + "JOIN Users u2 ON gn.UserID = u2.UserID";

            PreparedStatement stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Giaodich_ID");
                String name = rs.getString("TenGD");
                double amount = rs.getDouble("Sotien");
                String userName = rs.getString("UserName");
                String employeeName = rs.getString("EmployeeName");
                transactions.add(new Transaction(id, name, amount, employeeName, userName));
            }
        } catch (SQLException e) {
            System.err.println("Error accessing the database: " + e.getMessage());
        }
        return transactions;
    }


  
}
