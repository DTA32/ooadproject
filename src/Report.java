import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import model.ReportModel;

public class Report extends Application{
    Scene scene;
    BorderPane bp;
    FlowPane fp;
    @Override
    public void start(Stage primaryStage) {
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
        TableColumn<ReportModel, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(100);
        TableColumn<ReportModel, Integer> pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pcid"));
        pcidCol.setPrefWidth(200);
        TableColumn<ReportModel, String> reportCol = new TableColumn<>("Report Notes");
        reportCol.setCellValueFactory(new PropertyValueFactory<>("reportNotes"));
        reportCol.setPrefWidth(690);
        table.getColumns().addAll(idCol, pcidCol, reportCol);
        table.getItems().add(new ReportModel(1,13,"Jelek."));
        table.getItems().add(new ReportModel(2,22,"Yutub ngelag"));
        table.getItems().add(new ReportModel(3,16,"Gabisa buka palo"));
        fp.getChildren().add(table);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
