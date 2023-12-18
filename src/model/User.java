package model;

import database.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private String UserID, UserName, Passwod, Role;
    private  int Age;

    public static User getUserData(String username, String password){
        Connect conn = Connect.getConnection();
        String prepareSql = "SELECT * FROM users WHERE UserName = ? AND Password = ?";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String UserID = rs.getString("UserID");
                String UserNae = rs.getString("UserName");
                String Password = rs.getString("Password");
                int Age = Integer.parseInt(rs.getString("Age"));
                String Role = rs.getString("Role");
                return new User(UserID, UserNae, Password, Role, Age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<User> getAllTechnician(){
        ArrayList<User> userList = new ArrayList<>();
        Connect conn = Connect.getConnection();
        String query = "SELECT * FROM users WHERE Role = 'technician'";
        try (ResultSet rs = conn.executeQuery(query)) {
            while (rs.next()) {
                String UserID = rs.getString("UserID");
                String UserNae = rs.getString("UserName");
                String Password = rs.getString("Password");
                int Age = Integer.parseInt(rs.getString("Age"));
                String Role = rs.getString("Role");
                User user = new User(UserID, UserNae, Password, Role, Age);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            return null;
        }
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

        prepareSql = "INSERT INTO users (UserName, Password, Age, Role) VALUES (?, ?, ?, 'customer' )";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, age);
            ps.executeUpdate();;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<User> getAlluserData(){
        ArrayList<User> userList = new ArrayList<>();
        Connect conn = Connect.getConnection();
        String query = "SELECT * FROM users WHERE NOT Role = 'customer'";
        try (ResultSet rs = conn.executeQuery(query)) {
            while (rs.next()) {
                String UserID = rs.getString("UserID");
                String UserNae = rs.getString("UserName");
                String Password = rs.getString("Password");
                int Age = Integer.parseInt(rs.getString("Age"));
                String Role = rs.getString("Role");
                User user = new User(UserID, UserNae, Password, Role, Age);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean changeRoleUser(String UserID, String Role)
    {
        Connect conn = Connect.getConnection();
        String prepareSql = "UPDATE users SET Role = ? WHERE UserID = ?";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
            ps.setString(1, Role);
            ps.setString(2, UserID);
            ps.executeUpdate();;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User(String userID, String userName, String passwod, String role, int age) {
        UserID = userID;
        UserName = userName;
        Passwod = passwod;
        Role = role;
        Age = age;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPasswod() {
        return Passwod;
    }

    public void setPasswod(String passwod) {
        Passwod = passwod;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
