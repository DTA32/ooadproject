package view.Admin.job;

import controller.JobController;
import controller.PCController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;
import model.Job;
import model.PC;
import view.TemporaryMenu;

import java.util.ArrayList;

public class JobManagement {

    Scene scene;
    BorderPane bp = new BorderPane();
    VBox vb, pc_idVb, technician_idVb, job_statusVb;
    HBox hb;
    TextField pc_idField;
    Label titleLbl, pc_idLbl, technician_idLbl, job_statusLbl, assignLbl;
    TableView<Job> tableView;

    Button assignBtn;
    TableColumn<Job, String> jobIDColumn, pcIDColumn, userIDColumn, jobStatusColumn;
    ComboBox<String> statusPCList, technicianList;

    public JobManagement() {
        topInit();
        initialize();
    }

    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    public ArrayList<Job> getAllJobData(){
        return JobController.getAllJobData();
    }

    public Scene getScene() {
        return scene;
    }

    void topInit(){
        VBox topContainer = new VBox();
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = new TemporaryMenu();
            temp.show();
        });
        topContainer.getChildren().addAll(back);
        bp.setTop(topContainer);
    }

    private void initialize() {
        titleLbl = new Label("JOB MANAGEMENT");
        titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLbl.setAlignment(Pos.CENTER);

        tableView = new TableView<>();

        jobIDColumn = new TableColumn<>("Job ID");
        jobIDColumn.setCellValueFactory(new PropertyValueFactory<>("job_id"));
        jobIDColumn.setPrefWidth(200);

        pcIDColumn = new TableColumn<>("PC ID");
        pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pc_id"));
        pcIDColumn.setMinWidth(200);

        userIDColumn = new TableColumn<>("User ID");
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userIDColumn.setMinWidth(200);

        jobStatusColumn = new TableColumn<>("Job Status");
        jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        jobStatusColumn.setPrefWidth(200);

        ArrayList <Job> jobList = getAllJobData();
        tableView.getItems().addAll(jobList);

        assignLbl = new Label("Assign Broken PC to Technician");
        assignLbl.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        assignBtn = new Button("Assign");

        pc_idVb = new VBox(10);
        pc_idLbl = new Label("PC ID");
        pc_idLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        pc_idField = new TextField();
        pc_idField.setPromptText("Select PC ID From The Table");
        pc_idField.setEditable(false);
        pc_idVb.getChildren().addAll(pc_idLbl, pc_idField);

        technician_idVb = new VBox(10);
        technician_idLbl = new Label("Technician ID");
        technician_idLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        technicianList = new ComboBox<>();
        technicianList.setPromptText("Select Technician ID");
        for (Job job : jobList){
            technicianList.getItems().add(Integer.toString(job.getUserID()));
        }
        technician_idVb.getChildren().addAll(technician_idLbl, technicianList);


        job_statusVb = new VBox(10);
        job_statusLbl = new Label("Job Status");
        job_statusLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        statusPCList = new ComboBox<String>();
        statusPCList.getItems().addAll("Complete", "UnComplete");
        statusPCList.setPromptText("Select Job Status From THe Table");
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            Job job = tableView.getSelectionModel().getSelectedItem();
            technicianList.getSelectionModel().select(job.getUserID());
            pc_idField.setText(Integer.toString(job.getPc_id()));
            statusPCList.getSelectionModel().select(job.getJobStatus());
        });
        job_statusVb.getChildren().addAll(job_statusLbl, statusPCList);


        tableView.getColumns().addAll(jobIDColumn, pcIDColumn, userIDColumn, jobStatusColumn);
        tableView.setPlaceholder(new Label("No Staffs Found"));

        vb = new VBox(10);
        vb.setPadding(new Insets(16));

        hb = new HBox(10);
        hb.setPadding(new Insets(16));
        hb.setSpacing(32);

        hb.getChildren().addAll(pc_idVb, technician_idVb, job_statusVb);



        vb.getChildren().addAll(titleLbl, tableView, assignLbl, hb, assignBtn);
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setPadding(new Insets(50));

        scene = new Scene(vb, 1200, 600);
    }


}
