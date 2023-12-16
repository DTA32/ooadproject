package controller;

import helper.Helper;
import javafx.scene.control.Alert;
import model.User;

import java.util.ArrayList;

public class UserController {

    public static boolean getUserData(String username, String password){
        // Validate username
        if (username.isEmpty()){
            Helper.showAlert(Alert.AlertType.ERROR, "Username cannot be empty!");
            return false;
        }
        // Validate password
        if (password.isEmpty()){
            Helper.showAlert(Alert.AlertType.ERROR, "Password cannot be empty!");
            return false;
        }
        // Validate username and password
        boolean isValidUser = User.getUserData(username, password);
        if (!isValidUser){
            Helper.showAlert(Alert.AlertType.ERROR, "Invalid username or password!");
            return false;
        }
        return true;
    }

    public static boolean addNewUser(String username, String password, String confirmPassword, int age)
    {
        // Empty username or less than 7 characters
        if(username.isBlank() || username.length() < 7){
            Helper.showAlert(Alert.AlertType.ERROR, "Username must be at least 7 characters");
            return false;
        }

        // empty passssword or less than 6 characters
        if(password.isBlank() || password.length() < 6 ){
            Helper.showAlert(Alert.AlertType.ERROR, "Password must be at least 6 characters");
            return false;
        }

        // Contains alpha numeric characters
        boolean hasLetter = false, hasDigit = false;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isLetter(ch)) {
                hasLetter = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }

        }
        if (!hasLetter || !hasDigit){
            Helper.showAlert(Alert.AlertType.ERROR, "Password must contains alpha numeric characters");
            return false;
        }

        // Confirm password equals to password
        if(!password.equals(confirmPassword)){
            Helper.showAlert(Alert.AlertType.ERROR, "Password and Confirm Password must be the same");
            return false;
        }

        // Check age range
        if(age < 13 || age > 65){
            Helper.showAlert(Alert.AlertType.ERROR, "Age must be at least 13 years old");
            return false;
        }

        // Username is not unique
        if (!User.addNewUser(username, password, age)){
            Helper.showAlert(Alert.AlertType.ERROR, "Username is not unique!");
            return false;
        }
        return true;
    }

    public static boolean changeRoleUser(String UserID, String Role)
    {
        if (User.changeRoleUser(UserID, Role)) {
            Helper.showAlert(Alert.AlertType.INFORMATION, "Successfully Updated Role");
            return true;
        }

        Helper.showAlert(Alert.AlertType.ERROR, "Error updating");
        return false;
    }

    public static ArrayList<User> getAllUserData()
    {
        return User.getAlluserData();
    }

}
