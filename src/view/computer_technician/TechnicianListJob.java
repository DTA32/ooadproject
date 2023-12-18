package view.computer_technician;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.JobController;
import database.Connect;
import helper.Helper;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import main.MainStage;
import model.Job;
import model.TechnicianListJobModel;
import controller.TechnicianListJobController;
import view.TemporaryMenu;
import view.computer_technician.menu.ComputerTechnician;

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
    VBox technicianListJobVb, titleVb;
    HBox changeStatusHb;
    Label changeStatusLbl, titleLbl;
    Button changeStatusBtn;
    ComboBox<String> changeStatusCb;
    TableView<Job> table;


    public TechnicianListJob(){
        technicianListJobVb = new VBox(10);
        backInit();
        titleInit();
        tableInit();
        addEventListener();
    }

    public ArrayList<Job> getTechnicianJob(String user_id) {
        return JobController.getTechnicianJob(user_id);
    }

    void titleInit() {
        titleLbl = new Label("VIEW ALL JOB JOB");
        titleLbl.setAlignment(Pos.CENTER);
        titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        titleVb = new VBox(10);
        titleVb.getChildren().add(titleLbl);
        titleVb.setAlignment(Pos.CENTER);
        technicianListJobVb.getChildren().add(titleVb);
    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            ComputerTechnician computerTechnician = ComputerTechnician.getInstance();
            computerTechnician.show();
        });
        technicianListJobVb.getChildren().add(back);
    }
    
    void tableInit() {
        table = new TableView();

        TableColumn<Job, Integer> technicianCol = new TableColumn<>("User ID");
        technicianCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        technicianCol.setMinWidth(350);

        TableColumn<Job, String> statusCol = new TableColumn<>("Job Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        statusCol.setMinWidth(350);

        TableColumn<Job, Integer> pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pc_id"));
        pcidCol.setMinWidth(350);

        table.getColumns().addAll(technicianCol, pcidCol, statusCol);
        table.setPlaceholder(new Label("No Job Found"));

        int user_id = Integer.parseInt(Helper.getCurrentUser().getUserID());
        ArrayList<Job> jobList = getTechnicianJob(Integer.toString(user_id));
        table.getItems().addAll(jobList);

        changeStatusLbl = new Label("Change Status");
        changeStatusLbl.setFont(new Font("Arial", 16));

        changeStatusCb = new ComboBox();
        changeStatusCb.getItems().addAll("UnComplete", "Complete");
        changeStatusCb.setPromptText("Select Status");

        changeStatusBtn = new Button("Change Status");
        changeStatusBtn.setFont(new Font("Arial", 16));

        changeStatusHb = new HBox(10);
        changeStatusHb.getChildren().addAll(changeStatusCb, changeStatusBtn);
        changeStatusHb.setAlignment(Pos.CENTER_LEFT);
        changeStatusHb.setMaxWidth(500);

        technicianListJobVb.getChildren().addAll(table, changeStatusLbl, changeStatusHb);
        technicianListJobVb.setPadding(new Insets(64));
        technicianListJobVb.setSpacing(32);
        scene = new Scene(technicianListJobVb, 1200, 600);
    }

    void addEventListener(){
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;
            Job job = table.getSelectionModel().getSelectedItem();
            changeStatusCb.getSelectionModel().select(job.getJobStatus());
        });

        changeStatusBtn.setOnMouseClicked(e -> {
            boolean check = JobController.updateJobStatus(table.getSelectionModel().getSelectedItem().getJob_id(), changeStatusCb.getSelectionModel().getSelectedItem());
            if (check) {
                Helper.showAlert(Alert.AlertType.INFORMATION, "Job Status Updated Successfully!");
                _repaint();
            }
        });
    }

    public void _repaint() {
        table.getItems().clear();

        ArrayList <Job> jobList = getTechnicianJob(Helper.getCurrentUser().getUserID());

        for(Job job : jobList) {
            table.getItems().add(job);
        }
    }

}
