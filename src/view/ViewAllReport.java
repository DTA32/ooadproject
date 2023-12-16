package view;

import controller.ReportController;
import javafx.geometry.Orientation;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import main.MainStage;
import model.Report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ViewAllReport {
    private static ViewAllReport viewAllReport;
    public static ViewAllReport getInstance() {
        return viewAllReport = viewAllReport == null ? new ViewAllReport() : viewAllReport;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
        _repaint();
    }
    public void _repaint(){
        table.getItems().clear();
        ReportController controller = new ReportController();
        List<Report> reports = controller.GetAllReportData();
        for(Report report: reports){
            table.getItems().add(report);
        }
    }
    Scene scene;
    BorderPane bp;
    FlowPane fp;
    TableView<Report> table;

    public ViewAllReport() {
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

    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = TemporaryMenu.getInstance();
            temp.show();
        });
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

    void tableInit() {
        table = new TableView<>();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);
        TableColumn<Report, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("report_id"));
        idCol.setPrefWidth(100);
        TableColumn<Report, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("user_role"));
        roleCol.setPrefWidth(100);
        TableColumn<Report, Integer> pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pc_id"));
        pcidCol.setPrefWidth(100);
        TableColumn<Report, String> reportCol = new TableColumn<>("Report Notes");
        reportCol.setCellValueFactory(new PropertyValueFactory<>("report_note"));
        reportCol.setPrefWidth(500);
        TableColumn<Report, String> dateCol = new TableColumn<>("Report Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("report_date"));
        dateCol.setPrefWidth(198);
        table.getColumns().addAll(idCol, roleCol, pcidCol, reportCol, dateCol);
        _repaint();
        fp.getChildren().add(table);
    }
}
