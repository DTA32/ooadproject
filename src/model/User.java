package model;

import database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

    private String UserID, UserName, UserPassword, UserAge, UserRole;

    public static Integer getUserData(String username, String password){
        Connect conn = Connect.getConnection();
        String prepareSql = "SELECT * FROM users WHERE UserName = ? AND UserPassword = ?";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
//        Connect conn = Connect.getConnection();
//        String prepareSql = "SELECT * FROM users WHERE UserName = ? AND UserPassword = ?";
//        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
    }

    public static boolean addNewUser(String username, String password, int age) {
        Connect conn = Connect.getConnection();

        // Check if username is not unique
        String prepareSql = "SELECT * FROM users WHERE UserName = ?";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        prepareSql = "INSERT INTO users (UserName, UserPassword, UserAge, UserRole) VALUES (?, ?, ?, 'Customer')";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, age);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public User(String userID, String userNae, String userPassword, String userAge, String userRole) {
        UserID = userID;
        UserName = userNae;
        UserPassword = userPassword;
        UserAge = userAge;
        UserRole = userRole;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserNae() {
        return UserName;
    }

    public void setUserNae(String userNae) {
        UserName = userNae;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserAge() {
        return UserAge;
    }

    public void setUserAge(String userAge) {
        UserAge = userAge;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }
}
