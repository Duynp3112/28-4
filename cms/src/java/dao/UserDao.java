/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserDao extends BaseDao {

    PreparedStatement pstm;
    ResultSet rs;

    public boolean checkUser(String username, String password) {
        try {
            String strSelect = "select username,password from Users "
                    + "where username=? "
                    + "and password=?";
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkUser:" + e.getMessage());
        }
        return false;
    }

    public String getRoleNameByUsername(String username) {
        String roleName = null;
        try {
            String sql = "SELECT Quyen.RoleName\n"
                    + "FROM Users\n"
                    + "JOIN Quyen_Users ON Users.UserID = Quyen_Users.UserID\n"
                    + "JOIN Quyen ON Quyen_Users.RoleID = Quyen.RoleID\n"
                    + "WHERE Users.Username = ?;";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, username);
            rs = pstm.executeQuery();
            if (rs.next()) {
                roleName = rs.getString("RoleName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleName;
    }

    public static void main(String[] args) {
        UserDao u = new UserDao();
        u.checkUser("admin", "123");

    }

    public String getPosisionByUsername(String username) {
        String sql = "SELECT NhanVien.Chucvu FROM Users "
                + "JOIN NhanVien ON Users.UserID = NhanVien.UserID "
                + "WHERE Users.Username = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Chucvu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFullNameByUsername(String username) {
        String sql = "SELECT fullName FROM users WHERE username = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("fullName");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMaxUserID() {
        String maxUserID = "";
        try {

            String query = "SELECT MAX(userID) AS maxUserID FROM users";
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();

            if (rs.next()) {
                maxUserID = rs.getString("maxUserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxUserID;
    }

    public User getUser(String userID) {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE UserID = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, userID);
            rs = pstm.executeQuery();

            if (rs.next()) {
                String userId = rs.getString("UserID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String fullName = rs.getString("FullName");
                String email = rs.getString("Email");
                String dob = rs.getString("Ngaysinh");
                String address = rs.getString("Diachi");
                String gender = rs.getString("Gioitinh");
                String phone = rs.getString("SĐT");
                user = new User(userId, username, password, fullName, email, dob, address, gender, phone);
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
        return user;
    }

    public boolean addUser(String username, String password) {
        try {
            String maxUserID = getMaxUserID();
            int number = Integer.parseInt(maxUserID.trim().substring(1)) + 1;
            String newUserID = "U" + String.format("%04d", number);

            // Thêm người dùng mới vào bảng users
            String userInsertQuery = "INSERT INTO users (UserID, username, password) VALUES (?, ?, ?)";
            pstm = connection.prepareStatement(userInsertQuery);
            pstm.setString(1, newUserID);
            pstm.setString(2, username);
            pstm.setString(3, password);
            pstm.executeUpdate();

            // Lấy RoleID từ bảng Quyen cho role "Employee"
            String roleQuery = "SELECT RoleID FROM Quyen WHERE RoleName = 'ADMIN'";
            pstm = connection.prepareStatement(roleQuery);
            rs = pstm.executeQuery();

            // Nếu tìm thấy RoleID, thêm người dùng vào bảng Quyen_Users
            if (rs.next()) {
                String roleID = rs.getString("RoleID");
                String quyenUsersInsertQuery = "INSERT INTO Quyen_Users (UserID, RoleID) VALUES (?, ?)";
                pstm = connection.prepareStatement(quyenUsersInsertQuery);
                pstm.setString(1, newUserID);
                pstm.setString(2, roleID);
                pstm.executeUpdate();
            } else {
                // Xử lý nếu không tìm thấy RoleID
                System.err.println("Không tìm thấy RoleID cho role 'ADMIN'.");
                return false;
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserIDByName(String userName) {
        String userID = null;
        try {
            String query = "SELECT UserID FROM Users WHERE FullName = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, userName);
            rs = pstm.executeQuery();
            if (rs.next()) {
                userID = rs.getString("UserID");
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userID;
    }

    public String getUserIdbyUserName(String username) {
        String userId = null;
        try {
            String query = "SELECT UserID FROM Users WHERE Username = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, username);
            rs = pstm.executeQuery();

            if (rs.next()) {
                userId = rs.getString("UserID");
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
        return userId;
    }

    public void updateProfile(User user) {
        try {

            String query = "UPDATE Users SET FullName = ?, Email = ?, Ngaysinh = ?, Diachi = ?, Gioitinh = ?, SĐT = ? WHERE UserID = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getDob());
            pstm.setString(4, user.getAddress());
            pstm.setString(5, user.getGender());
            pstm.setString(6, user.getPhone());
            pstm.setString(7, user.getUserId());
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

    public void updatePassword(User user) {
        try {
            String query = "UPDATE Users SET Password = ? WHERE UserID = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, user.getPassword());
            pstm.setString(2, user.getUserId());

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

    public void addCustomer(User user) throws SQLException, ParseException {
        try {
            String maxUserID = getMaxUserID();
            int number = Integer.parseInt(maxUserID.trim().substring(1)) + 1;
            String newUserID = "U" + String.format("%04d", number);
            String userInsertQuery = "INSERT INTO users (UserID, username, password, FullName, Email, Ngaysinh, Diachi, Gioitinh, SĐT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstm = connection.prepareStatement(userInsertQuery);
            pstm.setString(1, newUserID);
            pstm.setString(2, user.getUsername());
            pstm.setString(3, user.getPassword());
            pstm.setString(4, user.getName());
            pstm.setString(5, user.getEmail());
            pstm.setString(6, user.getDob());
            pstm.setString(7, user.getAddress());
            pstm.setString(8, user.getGender());
            pstm.setString(9, user.getPhone());
            // Execute the user insertion query
            pstm.executeUpdate();

            // Get the RoleID for CUSTOMER role
            String roleQuery = "SELECT RoleID FROM Quyen WHERE RoleName = 'CUSTOMER'";
            pstm = connection.prepareStatement(roleQuery);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String roleID = rs.getString("RoleID");
                String quyenUsersInsertQuery = "INSERT INTO Quyen_Users (UserID, RoleID) VALUES (?, ?)";
                pstm = connection.prepareStatement(quyenUsersInsertQuery);
                pstm.setString(1, newUserID);
                pstm.setString(2, roleID);
                pstm.executeUpdate();
            }
        } finally {
            // Close resources in a finally block to ensure they're always closed
            if (pstm != null) {
                pstm.close();
            }
        }
    }
}
