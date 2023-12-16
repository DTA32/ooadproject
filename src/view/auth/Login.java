package view.auth;

import controller.UserController;
import helper.Helper;
import helper.UserSessionCookie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;
import view.TemporaryMenu;

public class Login {
    private static Login login;
    public static Login getInstance() {
        return login = login == null ? new Login() : login;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    Scene scene;
    VBox vb;
    Label loginTitle, usernameTitle, passwordTitle;
    TextField usernameInput;
    PasswordField passwordInput;
    Button loginButton;
    Hyperlink registerHyperlink;

    public Login() {
        initialize();
        addEventListener();
    }



    private void initialize() {
        vb = new VBox(10);
        loginTitle = new Label("Login");
        usernameTitle = new Label("Username");
        passwordTitle = new Label("Password");
        usernameInput = new TextField();
        usernameInput.setPromptText("Input your username here");
        passwordInput = new PasswordField();
        passwordInput.setPromptText("Input your password here");
        loginButton = new Button("Login");
        registerHyperlink = new Hyperlink("Don't have an account? Register Here!");
        registerHyperlink.setOnAction(e -> {
            Register register = Register.getInstance();
            register.show();
        });
        vb.getChildren().addAll(loginTitle, usernameTitle, usernameInput, passwordTitle, passwordInput, loginButton,
                registerHyperlink);
        loginTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setPadding(new Insets(50));
        backInit();
        scene = new Scene(vb, 1200, 600);
    }

    //Test buat booking
    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = TemporaryMenu.getInstance();
            temp.show();
        });
        vb.getChildren().add(back);
    }

    private void addEventListener() {

        //UDAH PASTI BAKAL CONFLICT YA GOODLUCK
        loginButton.setOnMouseClicked(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            Integer userId = UserController.getUserData(username, password);
            if (userId != null) {
                UserSessionCookie.getInstance().setUser_id(userId);
                Helper.showAlert(Alert.AlertType.INFORMATION, "Login success!");
                TemporaryMenu temp = TemporaryMenu.getInstance();
            } else {
                Helper.showAlert(Alert.AlertType.ERROR, "Invalid username or password.");
            }
        });
//        loginButton.setOnMouseClicked(e -> {
//            String username = usernameInput.getText();
//            String password = passwordInput.getText();
//
//            Integer user_id = UserController.getUserData(username, password);
//            if (UserController.getUserData(username, password)) {
//                Helper.showAlert(Alert.AlertType.INFORMATION, "Login success!");
//            }
//        });

//        registerHyperlink.setOnAction(e -> {
//            RegisterPage registerPage = RegisterPage.getInstance();
//            registerPage.show();
//        });
    }

}
