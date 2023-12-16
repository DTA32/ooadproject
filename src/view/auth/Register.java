package view.auth;

import controller.UserController;
import helper.Helper;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import main.MainStage;
import view.TemporaryMenu;

public class Register {
    private static Register register;
    public static Register getInstance() {
        return register = register == null ? new Register() : register;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }
    Scene scene;
    BorderPane bp;
    FlowPane fp;
    TextField usernameField;
    PasswordField passwordField;
    PasswordField confirmPasswordField;
    Spinner<Integer> ageSpinner;
    Button regButton;
    Hyperlink loginHyperlink;

    public Register() {
        initialize();
        setupEventHandling();
    }

    private void initialize() {
        bp = new BorderPane();
        fp = new FlowPane();

        topInit();

        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);
        usernameInit();
        passwordInit();
        confirmPasswordInit();
        ageInit();
        registerInit();
        loginHyperlinkInit();
        bp.setCenter(fp);

        scene = new Scene(bp, 1200, 600);
    }

    VBox titleInit(){
        Label application = new Label("Internet CLafes");
        application.setFont(new Font("Arial", 36));
        Label title = new Label("Register");
        title.setFont(new Font("Arial", 24));
        VBox titleContainer = new VBox();
        titleContainer.setSpacing(16);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getChildren().add(application);
        titleContainer.getChildren().add(title);
        titleContainer.setPadding(new Insets(16, 0, 48, 0));
        return titleContainer;
    }

    void topInit(){
        VBox topContainer = new VBox();
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = TemporaryMenu.getInstance();
            temp.show();
        });
        topContainer.getChildren().addAll(back, titleInit());
        bp.setTop(topContainer);
    }

    void usernameInit() {
        Label usernameLbl = new Label("Username");
        usernameField = new TextField();
        VBox usernameContainer = new VBox();
        usernameContainer.getChildren().add(usernameLbl);
        usernameContainer.getChildren().add(usernameField);
        fp.getChildren().add(usernameContainer);
    }

    void passwordInit() {
        Label passwordLbl = new Label("Password");
        passwordField = new PasswordField();
        VBox passwordContainer = new VBox();
        passwordContainer.getChildren().add(passwordLbl);
        passwordContainer.getChildren().add(passwordField);
        fp.getChildren().add(passwordContainer);
    }

    void confirmPasswordInit() {
        Label confirmPasswordLbl = new Label("Confirm Password");
        confirmPasswordField = new PasswordField();
        VBox passwordContainer = new VBox();
        passwordContainer.getChildren().add(confirmPasswordLbl);
        passwordContainer.getChildren().add(confirmPasswordField);
        fp.getChildren().add(passwordContainer);
    }

    void ageInit(){
        Label ageLbl = new Label("Age");
        ageSpinner = new Spinner<>(13, 65, 13);
        VBox ageContainer = new VBox();
        ageContainer.getChildren().add(ageLbl);
        ageContainer.getChildren().add(ageSpinner);
        fp.getChildren().add(ageContainer);
    }

    void registerInit() {
        regButton = new Button("view.auth.Register");
        regButton.setPrefWidth(500);
        fp.getChildren().add(regButton);
    }

    void loginHyperlinkInit() {
        loginHyperlink = new Hyperlink("Already have an account? Login here");
        loginHyperlink.setOnMouseClicked(e -> {
            Login login = Login.getInstance();
            login.show();
        });
        VBox loginContainer = new VBox();
        loginContainer.getChildren().add(loginHyperlink);
        loginContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(loginContainer);
    }

    void setupEventHandling() {
        regButton.setOnMouseClicked((e) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            int age = ageSpinner.getValue();

            if (UserController.addNewUser(username, password, confirmPassword, age)){
                Helper.showAlert(Alert.AlertType.INFORMATION, "Successfully registered!");
            }
        });
    }


}
