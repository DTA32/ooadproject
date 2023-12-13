package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.MainStage;
import model.TechnicianListJobModel;

public class TechnicianListJob{
    private static TechnicianListJob technicianListJob;
    public static TechnicianListJob getInstance() {
        return technicianListJob = technicianListJob == null ? new TechnicianListJob() : technicianListJob;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }
    Scene scene;
    BorderPane bp;
    FlowPane fp;


    public TechnicianListJob(){
        bp = new BorderPane();
        fp = new FlowPane();

        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);
        bp.setCenter(fp);
        titleInit();
        tableInit();
        backInit();

        scene = new Scene(bp, 1200, 600);

    }

    void titleInit() {
        Label title = new Label("View All Job Report");
        title.setFont(new Font("Arial", 24));
        title.setAlignment(Pos.CENTER);
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = TemporaryMenu.getInstance();
            temp.show();
        });
        bp.setTop(back);
    }
    void tableInit() {
        TableView table = new TableView();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);
        TableColumn<TechnicianListJobModel, Integer> noCol = new TableColumn<>("NO");
        noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
        noCol.setPrefWidth(200);
        TableColumn<TechnicianListJobModel, String> technicianCol = new TableColumn<>("Computer technician");
        technicianCol.setCellValueFactory(new PropertyValueFactory<>("technician"));
        technicianCol.setPrefWidth(400);
        TableColumn<TechnicianListJobModel, String> statusCol = new TableColumn<>("Job Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(200);
        TableColumn<TechnicianListJobModel, Integer> pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pcid"));
        pcidCol.setPrefWidth(200);
        table.getColumns().addAll(noCol, technicianCol, statusCol, pcidCol);
        table.getItems().add(new TechnicianListJobModel(1, "Yudi", "Complete", 13));
        table.getItems().add(new TechnicianListJobModel(2, "Andi", "Uncomplete", 17));
        table.getItems().add(new TechnicianListJobModel(3, "Budi", "Complete", 20));
        fp.getChildren().add(table);
    }

}
