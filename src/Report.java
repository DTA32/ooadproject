import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class Report extends Application{
    Scene scene;
    BorderPane bp;
    FlowPane fp;
    @Override
    public void start(Stage primaryStage) throws Exception {
        bp = new BorderPane();
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);

        backInit();
        titleInit();
        tableInit();

        bp.setCenter(fp);

        scene = new Scene(bp, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void backInit(){
        Button back = new Button("< Back");
        bp.setTop(back);
    }

    void titleInit(){
        Label title = new Label("View All Report");
        title.setFont(new Font("Arial", 24));
        title.setAlignment(Pos.CENTER);
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
    }

    void tableInit(){
        TableView table = new TableView();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);
        TableColumn no = new TableColumn("No");
        TableColumn pcid = new TableColumn("PC ID");
        TableColumn report = new TableColumn("Report Notes");
        table.getColumns().addAll(no, pcid, report);
        fp.getChildren().add(table);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
