package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.auth.Login;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainStage mainStage = MainStage.getInstance();
        mainStage.setStage(primaryStage);
        Login login = Login.getInstance();
        login.show();
        mainStage.getStage().setTitle("Internet CLafes");
        mainStage.getStage().show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}