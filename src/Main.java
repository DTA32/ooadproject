import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    Scene scene;
    BorderPane bp;
    FlowPane fp;
    ScrollPane sp;
    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
    	bp = new BorderPane();
        fp = new FlowPane();
        sp = new ScrollPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(10);
        sp.setContent(fp);
        sp.setMaxHeight(450);

        Label title = new Label("Register");
        bp.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        usernameInit();
        passwordInit();
        addressInit();
        genderInit();
        countryInit();
        kidsInit();
        birthInit();
        agreementInit();
        registerInit();
//        userInit();

        bp.setCenter(fp);
        scene = new Scene(bp, 1000, 500);
        stage.setScene(scene);
        stage.show();
    }

    // TextField
    void usernameInit() {
        Label usernameLbl = new Label("Username");
        TextField usernameField = new TextField();
        VBox usernameContainer = new VBox();
        usernameContainer.getChildren().add(usernameLbl);
        usernameContainer.getChildren().add(usernameField);
        fp.getChildren().add(usernameContainer);
    }

    // PasswordField
    void passwordInit() {
        Label passwordLbl = new Label("Password");
        PasswordField passwordField = new PasswordField();
        VBox passwordContainer = new VBox();
        passwordContainer.getChildren().add(passwordLbl);
        passwordContainer.getChildren().add(passwordField);
        fp.getChildren().add(passwordContainer);
    }

    // TextArea
    void addressInit() {
        Label addressLbl = new Label("Address");
        TextArea addressField = new TextArea();
        VBox addressContainer = new VBox();
        addressField.setPrefHeight(100);
        addressContainer.getChildren().add(addressLbl);
        addressContainer.getChildren().add(addressField);
        fp.getChildren().add(addressContainer);
    }

    // RadioButton with GridPane
    void genderInit() {
        Label genderLbl = new Label("Gender");
        ToggleGroup group = new ToggleGroup();

        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        RadioButton quantum = new RadioButton("Quantum (Non-binary)");

        male.setToggleGroup(group);
        female.setToggleGroup(group);
        quantum.setToggleGroup(group);

//        HBox buttonContainer = new HBox();
        VBox genderContainer = new VBox();
        GridPane buttonContainer = new GridPane();

//        buttonContainer.getChildren().add(male);
//        buttonContainer.getChildren().add(female);
//        buttonContainer.getChildren().add(quantum);
        buttonContainer.add(male, 0, 0);
        buttonContainer.add(female, 1, 0);
        buttonContainer.add(quantum, 2, 0);
        buttonContainer.setHgap(20);

        genderContainer.getChildren().add(genderLbl);
        genderContainer.getChildren().add(buttonContainer);

        fp.getChildren().add(genderContainer);
    }

    // ComboBox
    void countryInit() {
        Label countryLbl = new Label("Country ROAADDSSS");
        ComboBox<String> countryCombo = new ComboBox<String>();
        String[] countries = {
                "TAKE ME HOOMEEEE",
                "TO THE PLACEEE",
                "I BELONGGG",
                "WEST VIRGINIAAA"
        };
        countryCombo.setItems(FXCollections.observableArrayList(countries));
        countryCombo.getSelectionModel().selectFirst();
        VBox countryContainer = new VBox();
        countryContainer.getChildren().add(countryLbl);
        countryContainer.getChildren().add(countryCombo);
        fp.getChildren().add(countryContainer);
    }

    // Spinner
    void kidsInit() {
        Label kidsLbl = new Label("Kids Count");
        Spinner<Integer> kidsSpinner = new Spinner<Integer>(0,5,0);
        VBox kidsContainer = new VBox();
        kidsContainer.getChildren().add(kidsLbl);
        kidsContainer.getChildren().add(kidsSpinner);
        fp.getChildren().add(kidsContainer);
    }

    // DatePicker
    void birthInit() {
        Label birthLbl = new Label("Birthdate");
        DatePicker birthPicker = new DatePicker();
        VBox birthCont = new VBox();
        birthCont.getChildren().add(birthLbl);
        birthCont.getChildren().add(birthPicker);
        fp.getChildren().add(birthCont);
    }

    // List
    void userInit() {
        String[] users = {
                "Andi",
                "Budi",
                "Cinta"
        };
        ListView<String> userList = new ListView<String>();
        userList.setItems(FXCollections.observableArrayList(users));
        fp.getChildren().add(userList);
    }

    // CheckBox
    void agreementInit() {
        CheckBox check = new CheckBox("I have read and agree with terms of use");
        fp.getChildren().add(check);
    }

    // Button
    void registerInit() {
        Button button = new Button("Register");

        button.setPrefWidth(500);

        fp.getChildren().add(button);
    }

    public static void main(String args[]) {
        Register.main(args);
//        BookPC.main(args);
//        Report.main(args);
//    	launch(args);
    }
}
