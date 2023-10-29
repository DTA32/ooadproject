import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Register extends Application {
    Scene scene;
    BorderPane bp;
    FlowPane fp;
    Label errorLbl;
    @Override
    public void start(Stage primaryStage) throws Exception {
        bp = new BorderPane();
        fp = new FlowPane();

        bp.setTop(titleInit());

        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);
        usernameInit();
        passwordInit();
        confirmPasswordInit();
        ageInit();
        errorInit();
        registerInit();
        bp.setCenter(fp);

        scene = new Scene(bp, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
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

    void usernameInit() {
        Label usernameLbl = new Label("Username");
        TextField usernameField = new TextField();
        VBox usernameContainer = new VBox();
        usernameContainer.getChildren().add(usernameLbl);
        usernameContainer.getChildren().add(usernameField);
        fp.getChildren().add(usernameContainer);
    }

    void passwordInit() {
        Label passwordLbl = new Label("Password");
        PasswordField passwordField = new PasswordField();
        VBox passwordContainer = new VBox();
        passwordContainer.getChildren().add(passwordLbl);
        passwordContainer.getChildren().add(passwordField);
        fp.getChildren().add(passwordContainer);
    }

    void confirmPasswordInit() {
        Label confirmPasswordLbl = new Label("Confirm Password");
        PasswordField confirmPasswordField = new PasswordField();
        VBox passwordContainer = new VBox();
        passwordContainer.getChildren().add(confirmPasswordLbl);
        passwordContainer.getChildren().add(confirmPasswordField);
        fp.getChildren().add(passwordContainer);
    }

    void ageInit(){
        Label ageLbl = new Label("Age");
        Spinner<Integer> ageSpinner = new Spinner<>(13, 65, 13);
        VBox ageContainer = new VBox();
        ageContainer.getChildren().add(ageLbl);
        ageContainer.getChildren().add(ageSpinner);
        fp.getChildren().add(ageContainer);
    }

    void errorInit(){
        errorLbl = new Label("");
        errorLbl.setTextFill(Color.RED);
        fp.getChildren().add(errorLbl);
    }

    void registerInit() {
        Button button = new Button("Register");
        button.setPrefWidth(500);
        fp.getChildren().add(button);
        button.setOnMouseClicked(registerClick());
    }

    private EventHandler<MouseEvent> registerClick() {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Register Clicked");
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
