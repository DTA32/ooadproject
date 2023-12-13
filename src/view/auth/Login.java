package view.auth;

import controller.UserController;
import helper.Helper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;

public class Login {

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

    public Scene getScene() {
        return scene;
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
        vb.getChildren().addAll(loginTitle, usernameTitle, usernameInput, passwordTitle, passwordInput, loginButton,
                registerHyperlink);
        loginTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setPadding(new Insets(50));
        scene = new Scene(vb, 1200, 600);
    }

    private void addEventListener() {
        loginButton.setOnMouseClicked(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            if (UserController.getUserData(username, password)) {
                Helper.showAlert(Alert.AlertType.INFORMATION, "Login success!");
            }
        });

//        registerHyperlink.setOnAction(e -> {
//            RegisterPage registerPage = RegisterPage.getInstance();
//            registerPage.show();
//        });
    }

}
