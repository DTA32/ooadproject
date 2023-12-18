package helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.TransactionHistoryHeaderModel;
import model.User;
import view.auth.Login;

public class Helper {

    private static User user, tempUser;
    private static TransactionHistoryHeaderModel th;

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

    public static TransactionHistoryHeaderModel getTransactionHistoryHeaderModel() {
        return th;
    }

    public static void setTransactionHistoryHeaderModel(TransactionHistoryHeaderModel th) {
        Helper.th = th;
    }

    public static void setTempUser(User user) {
        Helper.tempUser = user;
    }

    public static void setUser(User user) {
        Helper.user = user;
    }

    public static Button logoutRender() {
        Button logout = new Button("Logout");
        logout.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        logout.setOnMouseClicked(e -> {
            Helper.setUser(null);
            Helper.setTempUser(null);
            Login login = Login.getInstance();
            login.show();
            Helper.showAlert(AlertType.INFORMATION, "Successfully logged out!");
        });
        return logout;
    }
}
