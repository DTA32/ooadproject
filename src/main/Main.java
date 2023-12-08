package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainStage.stage = primaryStage;
        TemporaryMenu temp = new TemporaryMenu();
        MainStage.stage.setScene(temp.getScene());
        MainStage.stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
