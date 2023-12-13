package model;

import database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

    private String UserID, UserNae, UserPassword, UserAge, UserRole;

    public static boolean getUserData(String username, String password){
        Connect conn = Connect.getConnection();
        String prepareSql = "SELECT * FROM users WHERE UserName = ? AND Password = ?";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

        prepareSql = "INSERT INTO users (UserName, Password, Age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, age);
            ps.executeUpdate();;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public User(String userID, String userNae, String userPassword, String userAge, String userRole) {
        UserID = userID;
        UserNae = userNae;
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
        return UserNae;
    }

    public void setUserNae(String userNae) {
        UserNae = userNae;
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
