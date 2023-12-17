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
import model.User;
import view.Admin.menu.AdminMenu;
import view.computer_technician.menu.ComputerTechnician;
import view.customer.menu.CustomerMenu;
import view.operator.menu.OperatorMenu;

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
    VBox titleVb, usernameVb, passwordVb, formVB, loginVB;
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
        titleVb = new VBox(10);
        usernameVb = new VBox(10);
        passwordVb  = new VBox(10);
        formVB = new VBox(10);
        loginVB = new VBox(10);

        loginTitle = new Label("LOGIN");
        loginTitle.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        usernameTitle = new Label("Username");
        usernameTitle.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        passwordTitle = new Label("Password");
        passwordTitle.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        usernameInput = new TextField();
        usernameInput.setPromptText("Input your username here");

        passwordInput = new PasswordField();
        passwordInput.setPromptText("Input your password here");

        loginButton = new Button("Login");
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        loginButton.setPrefWidth(500);

        registerHyperlink = new Hyperlink("Don't have an account? Register Here!");

        titleVb.setAlignment(Pos.CENTER);
        titleVb.getChildren().addAll(loginTitle);
        titleVb.setMaxWidth(500);

        usernameVb.setAlignment(Pos.CENTER_LEFT);
        usernameVb.getChildren().addAll(usernameTitle, usernameInput);
        usernameVb.setSpacing(16);
        usernameVb.setMaxWidth(500);

        passwordVb.setAlignment(Pos.CENTER_LEFT);
        passwordVb.getChildren().addAll(passwordTitle, passwordInput);
        passwordVb.setSpacing(16);
        passwordVb.setMaxWidth(500);

        formVB.setAlignment(Pos.CENTER_LEFT);
        formVB.getChildren().addAll(usernameVb, passwordVb, registerHyperlink, loginButton);
        formVB.setSpacing(16);
        formVB.setMaxWidth(500);

        loginVB.setAlignment(Pos.CENTER);
        loginVB.getChildren().addAll(titleVb, formVB);
        loginVB.setPadding(new Insets(64));
        loginVB.setSpacing(16);

        scene = new Scene(loginVB, 1200, 600);
    }

    private void addEventListener() {
        loginButton.setOnMouseClicked(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            // ini harus dimerge sama yang maul (cookies)
            User user = UserController.getUserData(username, password);
            if (user != null) {
                if (user.getRole().equals("admin")){
                    AdminMenu adminMenu = AdminMenu.getInstance();
                    adminMenu.show();
                }
                else if (user.getRole().equals("customer")){
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.show();
                }
                else if (user.getRole().equals("operator")){
                    OperatorMenu operatorMenu = new OperatorMenu();
                    operatorMenu.show();
                }
                else if (user.getRole().equals("technician")){
                    ComputerTechnician computerTechnician = new ComputerTechnician();
                    computerTechnician.show();
                }
                else{
                    Helper.showAlert(Alert.AlertType.ERROR, "Role not found");
                }
            }
        });

        registerHyperlink.setOnAction(e -> {
            Register register = Register.getInstance();
            register.show();
        });
    }

}
