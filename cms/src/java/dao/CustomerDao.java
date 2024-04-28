/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.ChienDich;
import model.Contract;
import model.Customer;
import model.Interaction;
import model.Transaction;
import model.User;

/**
 *
 * @author ADMIN
 */
public class CustomerDao extends BaseDao {

    PreparedStatement pstm;
    ResultSet rs;

    public ArrayList<Customer> getListCustomer() {
        ArrayList<Customer> list = new ArrayList<>();
        try {
            String strSelect = "SELECT Users.UserID, Users.FullName,Users.Email,Users.Diachi,Users.Gioitinh,Users.Ngaysinh,Users.SĐT, Quyen.RoleName\n"
                    + "FROM Users\n"
                    + "FULL JOIN Quyen_Users ON Users.UserID = Quyen_Users.UserID\n"
                    + "FULL JOIN Quyen ON Quyen_Users.RoleID = Quyen.RoleID \n"
                    + "Where Quyen.RoleName='Customer';";
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();

            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                String uid = rs.getString(1);
                String fname = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                String gender = rs.getString(5);
                Date dob = dbDateFormat.parse(rs.getString(6));
                String phone = rs.getString(7);
                String formatted_dob = displayDateFormat.format(dob);
                list.add(new Customer(uid, fname, email, formatted_dob, address, gender, phone));
            }
        } catch (SQLException | ParseException e) {
            System.out.println("getListCustomer:" + e.getMessage());
        }
        return list;
    }

    public String getId() {
        String id = "";
        try {
            String query = "SELECT MAX(CAST(SUBSTRING(HD_ID, 3, LEN(HD_ID) - 2) AS INT)) AS MaxID FROM HopDong";
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int maxID = rs.getInt("MaxID");
                int nextID = maxID + 1;
                id = "HD" + String.format("%03d", nextID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public Customer getUserById(String u_id) {
        Customer customer = null;
        try {
            String strSelect = "SELECT Users.UserID, Users.FullName, Users.Email, Users.Diachi, Users.Gioitinh, Users.Ngaysinh, Users.SĐT, Quyen.RoleName\n"
                    + "FROM Users\n"
                    + "FULL JOIN Quyen_Users ON Users.UserID = Quyen_Users.UserID\n"
                    + "FULL JOIN Quyen ON Quyen_Users.RoleID = Quyen.RoleID \n"
                    + "WHERE Users.UserID = ? AND Quyen.RoleName='Customer';";

            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, u_id);
            rs = pstm.executeQuery();
            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if (rs.next()) {
                String uid = rs.getString(1);
                String fname = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                String gender = rs.getString(5);
                Date dob = dbDateFormat.parse(rs.getString(6));
                String phone = rs.getString(7);
                String formatted_dob = displayDateFormat.format(dob);
                customer = new Customer(uid, fname, email, address, gender, formatted_dob, phone);
            }
        } catch (SQLException | ParseException e) {
            System.out.println("getUserById: " + e.getMessage());
        }
        return customer;
    }

    public void updateCustomer(Customer c) {
        try {
            String strUpdate = "UPDATE Users SET FullName=?, Email=?, Diachi=?, Gioitinh=?, Ngaysinh=?, SĐT=? WHERE UserID=?;";
            pstm = connection.prepareStatement(strUpdate);
            pstm.setString(1, c.getName());
            pstm.setString(2, c.getEmail());
            pstm.setString(3, c.getAddress());
            pstm.setString(4, c.getGender());
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = inputDateFormat.parse(c.getDob());
            String dbDate = dbDateFormat.format(dob);
            pstm.setString(5, dbDate);
            pstm.setString(6, c.getPhone());
            pstm.setString(7, c.getUserId());
            pstm.executeUpdate();
        } catch (SQLException | ParseException e) {
            System.out.println("updateCustomer: " + e.getMessage());
        }
    }

    public void addCustomerWithRole(Customer c, String roleName) {
        try {
            String strInsertUser = "INSERT INTO Users (UserID, FullName, Email, Diachi, Gioitinh, Ngaysinh, SĐT) VALUES (?, ?, ?, ?, ?, ?, ?);";
            pstm = connection.prepareStatement(strInsertUser);
            pstm.setString(1, c.getUserId());
            pstm.setString(2, c.getName());
            pstm.setString(3, c.getEmail());
            pstm.setString(4, c.getAddress());
            pstm.setString(5, c.getGender());
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = inputDateFormat.parse(c.getDob());
            String dbDate = dbDateFormat.format(dob);
            pstm.setString(6, dbDate);
            pstm.setString(7, c.getPhone());
            pstm.executeUpdate();
            String strInsertRole = "INSERT INTO Quyen_Users (UserID, RoleID) VALUES (?, (SELECT RoleID FROM Quyen WHERE RoleName = ?));";
            pstm = connection.prepareStatement(strInsertRole);
            pstm.setString(1, c.getUserId());
            pstm.setString(2, roleName);
            pstm.executeUpdate();
        } catch (SQLException | ParseException e) {
            System.out.println("addCustomerWithRole: " + e.getMessage());

        }
    }

    public ArrayList<String> getAllGender() {
        ArrayList<String> list = new ArrayList<>();
        try {
            String strSelect = "SELECT distinct gender FROM Users";
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String gender = rs.getString(1);
                if (gender != null) {
                    list.add(gender);
                }
            }
        } catch (SQLException e) {
            System.out.println("getAllGender:" + e.getMessage());
        }
        return list;
    }

    public ArrayList<ChienDich> getListChienDich() {
        ArrayList<ChienDich> listChienDich = new ArrayList<>();
        try {
            String query = "SELECT ChienDich.Chiendich_ID, ChienDich.Tenchiendich, ChienDich.Motachitiet, "
                    + "ChienDich.Loaichiendich, ChienDich.Muctieu, ChienDich.Chiphi, ChienDich.Ngaybatdau, "
                    + "ChienDich.Ngayketthuc, Users.FullName AS TenNhanVien "
                    + "FROM ChienDich "
                    + "INNER JOIN NhanVien ON ChienDich.NhanVienID = NhanVien.NhanVienID "
                    + "INNER JOIN Users ON NhanVien.UserID = Users.UserID";

            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();

            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                String chiendichID = rs.getString("Chiendich_ID");
                String tenChiendich = rs.getString("Tenchiendich");
                String motaChitiet = rs.getString("Motachitiet");
                String loaiChiendich = rs.getString("Loaichiendich");
                String mucTieu = rs.getString("Muctieu");
                double chiPhi = rs.getDouble("Chiphi");
                Date start_date = dbDateFormat.parse(rs.getString("Ngaybatdau"));
                Date end_date = dbDateFormat.parse(rs.getString("Ngayketthuc"));
                String nhanVienTen = rs.getString("TenNhanVien");
                String Ngaybatdau = displayDateFormat.format(start_date);
                String Ngayketthuc = displayDateFormat.format(end_date);

                ChienDich chiendich = new ChienDich(chiendichID, tenChiendich, motaChitiet, loaiChiendich,
                        mucTieu, chiPhi, Ngaybatdau, Ngayketthuc, nhanVienTen);
                listChienDich.add(chiendich);
            }
        } catch (SQLException | ParseException e) {
        }
        return listChienDich;
    }

    public void addChienDich(ChienDich chiendich) {
        try {
            String query = "INSERT INTO ChienDich (Chiendich_ID, Tenchiendich, Motachitiet, Loaichiendich, Muctieu, Chiphi, Ngaybatdau, Ngayketthuc, NhanVienID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            pstm = connection.prepareStatement(query);

            pstm.setString(1, chiendich.getChiendichID());
            pstm.setString(2, chiendich.getTenChiendich());
            pstm.setString(3, chiendich.getMotaChitiet());
            pstm.setString(4, chiendich.getLoaiChiendich());
            pstm.setString(5, chiendich.getMucTieu());
            pstm.setDouble(6, chiendich.getChiPhi());
            pstm.setString(7, chiendich.getNgayBatDau());
            pstm.setString(8, chiendich.getNgayKetThuc());
            pstm.setString(9, chiendich.getNhanVienID());
            pstm.executeUpdate();
            System.out.println("Chien Dich added successfully.");
        } catch (SQLException e) {
            System.out.println("addChienDich: " + e.getMessage());
        }
    }

    public void updateChienDich(ChienDich chiendich) {
        try {
            String query = "UPDATE ChienDich SET Tenchiendich=?, Motachitiet=?, Loaichiendich=?, Muctieu=?, Chiphi=?, Ngaybatdau=?, Ngayketthuc=?, NhanVienID=? WHERE Chiendich_ID=?;";
            pstm = connection.prepareStatement(query);

            pstm.setString(1, chiendich.getTenChiendich());
            pstm.setString(2, chiendich.getMotaChitiet());
            pstm.setString(3, chiendich.getLoaiChiendich());
            pstm.setString(4, chiendich.getMucTieu());
            pstm.setDouble(5, chiendich.getChiPhi());
            pstm.setString(6, chiendich.getNgayBatDau());
            pstm.setString(7, chiendich.getNgayKetThuc());
            pstm.setString(8, chiendich.getNhanVienID());
            pstm.setString(9, chiendich.getChiendichID());
            pstm.executeUpdate();
            System.out.println("Chien Dich updated successfully.");
        } catch (Exception e) {
            System.out.println("updateChienDich: " + e.getMessage());
        }
    }

    public String getNhanVienIDByFullName(String fullName) {
        String nhanVienID = null;
        try {
            String query = "SELECT NhanVienID FROM Users u join NhanVien n on u.UserID=n.UserID WHERE FullName = ?;";

            pstm = connection.prepareStatement(query);
            pstm.setString(1, fullName);

            rs = pstm.executeQuery();

            if (rs.next()) {
                nhanVienID = rs.getString("NhanVienID");
            }
        } catch (SQLException e) {
            System.out.println("getNhanVienIDByFullName: " + e.getMessage());
        }
        return nhanVienID;
    }

    public ArrayList<Contract> getListContract() {
        ArrayList<Contract> contracts = new ArrayList<>();
        try {
            String query = "SELECT *, u1.FullName AS tennv, u2.FullName AS tenkh FROM HopDong h "
                    + "JOIN NhanVien n ON h.NhanVienID = n.NhanVienID "
                    + "JOIN NhanVien_HopDong_Users gn ON gn.HD_ID = h.HD_ID "
                    + "JOIN Users u1 ON n.UserID = u1.UserID "
                    + "JOIN Users u2 ON gn.UserID = u2.UserID";

            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();

            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                String contractID = rs.getString("HD_ID");
                String contractName = rs.getString("TenHD");
                String type = rs.getString("loaihopdong");
                String product = rs.getString("sanpham");
                Date start_date = dbDateFormat.parse(rs.getString("Ngaybatdau"));
                Date end_date = rs.getString("Ngayketthuc") != null
                        ? dbDateFormat.parse(rs.getString("Ngayketthuc"))
                        : null;
                String employeeName = rs.getString("tennv");
                String customerName = rs.getString("tenkh");
                String startDateStr = displayDateFormat.format(start_date);
                String endDateStr = end_date != null ? displayDateFormat.format(end_date) : "Vĩnh viễn";

                Contract contract = new Contract(
                        contractID, contractName, type, product,
                        startDateStr, endDateStr, employeeName, customerName);
                contracts.add(contract);
            }
        } catch (SQLException | ParseException e) {
            // Handle exceptions appropriately
        }
        return contracts;
    }

    public ArrayList<Transaction> getListTransaction() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT g.Giaodich_ID, g.TenGD, g.Sotien, u2.FullName AS UserName, u.FullName AS EmployeeName "
                    + "FROM GiaoDich g "
                    + "JOIN GiaoDich_NhanVien_HopDong_Users gn ON g.Giaodich_ID = gn.Giaodich_ID "
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

    public ArrayList<Interaction> getListInteraction() {
        ArrayList<Interaction> interactions = new ArrayList<>();
        try {
            String query = "SELECT t.TuongTac_ID, t.TinhHuong, t.Phanhoi, t.Lichlamviec, u.FullName AS UserName, u2.FullName AS EmployeeName "
                    + "FROM TuongTac t "
                    + "JOIN Users u ON t.UserID = u.UserID "
                    + "JOIN NhanVien n ON t.NhanVienID = n.NhanVienID "
                    + "JOIN Users u2 ON n.UserID = u2.UserID";
            PreparedStatement stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String interactionId = rs.getString("TuongTac_ID");
                String scenario = rs.getString("TinhHuong");
                String feedback = rs.getString("Phanhoi");
                Time schedule = rs.getTime("Lichlamviec");
                String userId = rs.getString("UserName");
                String employeeId = rs.getString("EmployeeName");
                interactions.add(new Interaction(interactionId, scenario, feedback, schedule, userId, employeeId));
            }
        } catch (SQLException e) {
            System.err.println("Error accessing the database: " + e.getMessage());
        }

        return interactions;
    }

    public ArrayList<String> getAllCustomer() {
        ArrayList<String> list = new ArrayList<>();
        try {
            String strSelect = "SELECT distinct FullName FROM Users INNER JOIN Quyen_Users ON Users.UserID = Quyen_Users.UserID \n"
                    + "  INNER JOIN Quyen ON Quyen_Users.RoleID = Quyen.RoleID WHERE RoleName = 'CUSTOMER'";
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String FullName = rs.getString(1);
                if (FullName != null) {
                    list.add(FullName);
                }
            }
        } catch (Exception e) {
            System.out.println("getAllCustomer:" + e.getMessage());
        }
        return list;
    }

    public ArrayList<String> getAllEmployee() {
        ArrayList<String> list = new ArrayList<>();
        try {
            String strSelect = "SELECT distinct FullName FROM Users INNER JOIN Quyen_Users ON Users.UserID = Quyen_Users.UserID \n"
                    + "  INNER JOIN Quyen ON Quyen_Users.RoleID = Quyen.RoleID WHERE RoleName = 'EMPLOYEE'";
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String FullName = rs.getString(1);
                if (FullName != null) {
                    list.add(FullName);
                }
            }
        } catch (Exception e) {
            System.out.println("getAllEmployee:" + e.getMessage());
        }
        return list;
    }

    public void delete(String HDID) {
        String sql = "DELETE FROM NhanVien_HopDong_Users WHERE HD_ID = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, HDID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete2(String HDID) {
        String sql = "DELETE FROM HopDong WHERE HD_ID = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, HDID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMaxContractID() {
        String maxcontractID = "";
        try {

            String query = "SELECT MAX(HD_ID) AS maxcontractID FROM HopDong";
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();

            if (rs.next()) {
                maxcontractID = rs.getString("maxcontractID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxcontractID;
    }

    public void addContract(Contract c) {
        try {

            // Lấy ID của nhân viên từ tên nhân viên
            String employeeID = getEmployeeIDByName(c.getEmployeeName());
            String maxContractID = getMaxContractID();
            int number = Integer.parseInt(maxContractID.trim().substring(2)) + 1;
            String newcontractID = "HD" + String.format("%03d", number);
            String query = "INSERT INTO HopDong (HD_ID, TenHD, Loaihopdong, Sanpham, Ngaybatdau, Ngayketthuc, NhanVienID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstm = connection.prepareStatement(query);

            pstm.setString(1, newcontractID);
            pstm.setString(2, c.getContractName());
            pstm.setString(3, c.getType());
            pstm.setString(4, c.getProduct());
            pstm.setString(5, c.getStartDate());
            pstm.setString(6, c.getEndDate());
            pstm.setString(7, employeeID);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getEmployeeIDByName(String employeeName) throws SQLException {

        String employeeID = "";
        try {
            String query = "select n.NhanVienID from NhanVien n join users u on n.UserID=u.UserID where u.FullName = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, employeeName);
            rs = pstm.executeQuery();
            if (rs.next()) {
                employeeID = rs.getString("NhanVienID");
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
        return employeeID;
    }

    public void deleteC(String userID) {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteC2(String userID) {
        String sql = "DELETE FROM Quyen_Users WHERE UserID = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNhanVienHopDongUsers(String id, String employeeID, String customerID) {
        try {
            String query = "INSERT INTO NhanVien_HopDong_Users (NhanVienID, UserID, HD_ID) VALUES (?, ?, ?)";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, employeeID);
            pstm.setString(2, customerID);
            pstm.setString(3, id);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contract getContractByID(String contractID) {
        Contract contract = null;
        try {
            String query = "SELECT *, u1.FullName AS tennv, u2.FullName AS tenkh FROM HopDong h \n"
                    + "JOIN NhanVien n ON h.NhanVienID = n.NhanVienID \n"
                    + "JOIN NhanVien_HopDong_Users gn ON gn.HD_ID = h.HD_ID \n"
                    + "JOIN Users u1 ON n.UserID = u1.UserID \n"
                    + "JOIN Users u2 ON gn.UserID = u2.UserID "
                    + "WHERE h.HD_ID = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, contractID);
            rs = pstm.executeQuery();
            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if (rs.next()) {
                // Extract data from result set
                String id = rs.getString("HD_ID");
                String name = rs.getString("TenHD");
                String type = rs.getString("Loaihopdong");
                String product = rs.getString("Sanpham");
                Date startDate = dbDateFormat.parse(rs.getString("Ngaybatdau"));
                String employeeName = rs.getString("tennv");
                String customerName = rs.getString("tenkh");

                // Xử lý trường hợp Ngayketthuc có giá trị null
                Date endDate = null;
                String formatted_end_date = null;
                String ngayketthuc = rs.getString("Ngayketthuc");
                if (ngayketthuc != null) {
                    endDate = dbDateFormat.parse(ngayketthuc);
                    formatted_end_date = displayDateFormat.format(endDate);
                }
                String formatted_start_date = displayDateFormat.format(startDate);

                contract = new Contract(id, name, type, product, formatted_start_date, formatted_end_date, employeeName, customerName);
            }
        } catch (SQLException | ParseException e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return contract;
    }

    public static void main(String[] args) {
        // Lấy thông tin của hợp đồng với ID là "HD001"
        CustomerDao cdao = new CustomerDao();
        Contract contract = cdao.getContractByID("HD001");
        System.out.println(contract);
    }

    public void updateContract(Contract c) {
        try {

            // Lấy ID của nhân viên từ tên nhân viên
            String employeeID = getEmployeeIDByName(c.getEmployeeName());
            String query = "UPDATE HopDong "
                    + "SET TenHD = ?, Loaihopdong = ?, Sanpham = ?, Ngaybatdau = ?, Ngayketthuc = ?, NhanVienID = ? "
                    + "WHERE HD_ID = ?";
            pstm = connection.prepareStatement(query);

            pstm.setString(1, c.getContractName());
            pstm.setString(2, c.getType());
            pstm.setString(3, c.getProduct());
            pstm.setString(4, c.getStartDate());
            pstm.setString(5, c.getEndDate());
            pstm.setString(6, employeeID);
            pstm.setString(7, c.getContractID());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateNhanVienHopDongUsers(String userID, String employeeID, String customerID) {
        try {
            String query = "Update NhanVien_HopDong_Users Set NhanVienID=? where HD_ID=?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, employeeID);
            pstm.setString(2, userID);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getCDId() {
        String id = "";
        try {
            String query = "SELECT MAX(CAST(SUBSTRING(ChienDich_ID, 3, LEN(ChienDich_ID) - 2) AS INT)) AS MaxID FROM ChienDich";
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int maxID = rs.getInt("MaxID");
                int nextID = maxID + 1;
                id = "CD" + String.format("%03d", nextID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public ChienDich getChienDichById(String id) {
        ChienDich chiendich = null;
        try {
            String query = "SELECT * FROM ChienDich WHERE Chiendich_ID = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String chiendichID = rs.getString("Chiendich_ID");
                String tenChiendich = rs.getString("Tenchiendich");
                String motaChitiet = rs.getString("Motachitiet");
                String loaiChiendich = rs.getString("Loaichiendich");
                String mucTieu = rs.getString("Muctieu");
                double chiPhi = rs.getDouble("Chiphi");
                String ngayBatDau = rs.getString("Ngaybatdau");
                String ngayKetThuc = rs.getString("Ngayketthuc");
                String nhanVienID = rs.getString("NhanVienID");
                chiendich = new ChienDich(chiendichID, tenChiendich, motaChitiet, loaiChiendich, mucTieu, chiPhi, ngayBatDau, ngayKetThuc, nhanVienID);
            }
        } catch (SQLException e) {
            System.out.println("getChienDichById: " + e.getMessage());
        }
        return chiendich;
    }

}
