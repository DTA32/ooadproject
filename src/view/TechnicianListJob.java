package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;
import helper.Helper;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import main.MainStage;
import model.TechnicianListJobModel;
import controller.TechnicianListJobController;

public class TechnicianListJob{
    private static TechnicianListJob technicianListJob;
    public static TechnicianListJob getInstance() {
        return technicianListJob = technicianListJob == null ? new TechnicianListJob() : technicianListJob;
    }

    ObservableList<TechnicianListJobModel> data = FXCollections.observableArrayList();
    
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
        technicianCol.setCellFactory(TextFieldTableCell.<TechnicianListJobModel>forTableColumn());
        technicianCol.setPrefWidth(400);
        TableColumn<TechnicianListJobModel, String> statusCol = new TableColumn<>("Job Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        //update data
        statusCol.setOnEditCommit(
            new EventHandler<CellEditEvent<TechnicianListJobModel, String>>() {
                @Override
                public void handle(CellEditEvent<TechnicianListJobModel, String> t) {
                	// get id
                	int user_id = ((TechnicianListJobModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getNo();
                	// close get id
                	if (TechnicianListJobController.updateStatus(user_id, t.getNewValue())){
                		Helper.showAlert(Alert.AlertType.INFORMATION, "Successfully updated!");
                    }

                }
            }
        );
        // close update data
        statusCol.setPrefWidth(200);
        TableColumn<TechnicianListJobModel, Integer> pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pcid"));
        pcidCol.setPrefWidth(200);
        table.getColumns().addAll(noCol, technicianCol, statusCol, pcidCol);
      //ambil data dari database
        Connect conn = Connect.getConnection();
        String prepareSql = "SELECT 1 as no, 'Yudi' as name, 'Complete' as state, 13 as numb FROM users;";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int no = rs.getInt("no");
                String name = rs.getString("name");
                String state = rs.getString("state");
                int numb = rs.getInt("numb");
                table.getItems().add(new TechnicianListJobModel(no, name, state, numb));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        close
//        table.getItems().add(new TechnicianListJobModel(1, "Yudi", "Complete", 13));
//        table.getItems().add(new TechnicianListJobModel(2, "Andi", "Uncomplete", 17));
//        table.getItems().add(new TechnicianListJobModel(3, "Budi", "Complete", 20));
        fp.getChildren().add(table);
    }

}
