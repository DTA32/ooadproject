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
        _repaint();
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    public void _repaint(){
        usernameInput.setText("");
        passwordInput.setText("");
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
            handleLogin();
        });

        registerHyperlink.setOnAction(e -> {
            Register register = Register.getInstance();
            register.show();
        });

        passwordInput.setOnKeyPressed(e -> {
            if(e.getCode().toString().equalsIgnoreCase("ENTER"))
                handleLogin();
        });
    }

    private void handleLogin(){
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        User user = UserController.getUserData(username, password);
        if (user != null) {
            Helper.setUser(user);
            if (user.getRole().equalsIgnoreCase("admin")){
                AdminMenu adminMenu = AdminMenu.getInstance();
                adminMenu.show();
            }
            else if (user.getRole().equalsIgnoreCase("customer")){
                CustomerMenu customerMenu = CustomerMenu.getInstance();
                customerMenu.show();
            }
            else if (user.getRole().equalsIgnoreCase("operator")){
                OperatorMenu operatorMenu = OperatorMenu.getInstance();
                operatorMenu.show();
            }
            else if (user.getRole().equalsIgnoreCase("technician")){
                ComputerTechnician computerTechnician = ComputerTechnician.getInstance();
                computerTechnician.show();
            }
            else{
                Helper.showAlert(Alert.AlertType.ERROR, "Role error!");
            }
        }
    }

}
