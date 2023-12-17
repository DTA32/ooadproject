package helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;

public class Helper {

    private static User user, tempUser;

    public static void showAlert(AlertType alertType, String contentText){
        Alert alert = new Alert(alertType);
        alert.setContentText(contentText);
        alert.show();
    }

    public static User getCurrentUser() {
        return user;
    }

    public static User getTempUser() {
        return tempUser;
    }

    public static void setTempUser(User user) {
        Helper.tempUser = user;
    }

    public static void setUser(User user) {
        Helper.user = user;
    }
}
