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
import javafx.scene.text.FontWeight;
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
    VBox titleVb, usernamevB, passwordVb, confirmPasswordVb, ageVBb, formVb, registerVb;
    TextField usernameField;
    PasswordField passwordField;
    PasswordField confirmPasswordField;
    Spinner<Integer> ageSpinner;
    Button regButton;
    Hyperlink loginHyperlink;
    Label usernameLbl, passwordLbl, confirmPasswordLbl, ageLbl;

    public Register() {
        initialize();
        setupEventHandling();
    }

    private void initialize() {
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.CENTER);
        fp.setVgap(16);

        titleInit();
        usernameInit();
        passwordInit();
        confirmPasswordInit();
        ageInit();
        loginHyperlinkInit();
        registerInit();

        bp = new BorderPane();
        bp.setPadding(new Insets(64));
        bp.setCenter(fp);

        scene = new Scene(bp, 1200, 600);
    }

    void titleInit(){
        Label title = new Label("REGISTER");
        title.setFont(Font.font("Arial", FontWeight.BOLD,  20));

        titleVb = new VBox();
        titleVb.setAlignment(Pos.CENTER);
        titleVb.getChildren().add(title);
        titleVb.setMaxWidth(500);
        fp.getChildren().add(titleVb);
    }

//    void topInit(){
//        VBox topContainer = new VBox();
//        Button back = new Button("< Back");
//        back.setOnMouseClicked(e -> {
//            TemporaryMenu temp = TemporaryMenu.getInstance();
//            temp.show();
//        });
//        topContainer.getChildren().addAll(back, titleInit());
//        bp.setTop(topContainer);
//    }

    void usernameInit() {
        usernameLbl = new Label("Username");
        usernameLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        usernameField = new TextField();
        usernameField.setPromptText("Input your username here");

        usernamevB = new VBox();
        usernamevB.setAlignment(Pos.CENTER_LEFT);
        usernamevB.getChildren().addAll(usernameLbl, usernameField);
        usernamevB.setSpacing(16);
        usernamevB.setMaxWidth(500);
        fp.getChildren().add(usernamevB);
    }

    void passwordInit() {
        passwordLbl = new Label("Password");
        passwordLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        passwordField = new PasswordField();
        passwordField.setPromptText("Input your password here");

        passwordVb = new VBox();
        passwordVb.setAlignment(Pos.CENTER_LEFT);
        passwordVb.getChildren().addAll(passwordLbl, passwordField);
        passwordVb.setSpacing(16);
        passwordVb.setMaxWidth(500);
        fp.getChildren().add(passwordVb);
    }

    void confirmPasswordInit() {
        confirmPasswordLbl = new Label("Confirm Password");
        confirmPasswordLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Input your password here");

        confirmPasswordVb = new VBox();
        confirmPasswordVb.setAlignment(Pos.CENTER_LEFT);
        confirmPasswordVb.getChildren().addAll(confirmPasswordLbl, confirmPasswordField);
        confirmPasswordVb.setSpacing(16);
        confirmPasswordVb.setMaxWidth(500);
        fp.getChildren().add(confirmPasswordVb);
    }

    void ageInit(){
        ageLbl = new Label("Age");
        ageLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        ageSpinner = new Spinner<>(13, 65, 13);

        ageVBb = new VBox();
        ageVBb.setAlignment(Pos.CENTER_LEFT);
        ageVBb.getChildren().addAll(ageLbl, ageSpinner);
        ageVBb.setSpacing(16);
        ageVBb.setMaxWidth(500);
        fp.getChildren().add(ageVBb);
    }

    void registerInit() {
        regButton = new Button("Register");
        regButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        regButton.setPrefWidth(500);
        fp.getChildren().add(regButton);
    }

    void loginHyperlinkInit() {
        loginHyperlink = new Hyperlink("Already have an account? Login here");

        fp.getChildren().add(loginHyperlink);
    }

    void setupEventHandling() {
        Login login = Login.getInstance();

        regButton.setOnMouseClicked((e) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            int age = ageSpinner.getValue();

            if (UserController.addNewUser(username, password, confirmPassword, age)){
                login.show();
            }
        });
        loginHyperlink.setOnMouseClicked(e -> {
            login.show();
        });
    }

}
