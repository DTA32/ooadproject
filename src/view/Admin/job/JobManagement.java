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
    VBox titleVb, pc_idVb, technician_idVb, job_statusVb, jobManagementVb, assignVb, backVb, updateVb, pc_idVb2, technician_idVb2;
    HBox hb, hb2;
    TextField pc_idField, technicianField;
    Label titleLbl, pc_idLbl, technician_idLbl, job_statusLbl, updateLbl, jobListLbl, reportedPcList, assignJobLbl, technician_idLbl2;
    TableView<Job> tableView;
    Button  updateJobStatusBtn, backBtn, assignBtn;
    TableColumn<Job, String> jobIDColumn, pcIDColumn, userIDColumn, jobStatusColumn;
    ComboBox<String> statusPCList, reportedPCList, technicianList2;
    BorderPane bp;


    public JobManagement() {
        initialize();
        addEventListener();
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

    private void initialize() {
        titleVb = new VBox(10);

        backBtn = new Button("< Back");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        backVb = new VBox(10);
        backVb.getChildren().addAll(backBtn);
        backVb.setAlignment(Pos.TOP_LEFT);

        titleLbl = new Label("JOB MANAGEMENT");
        titleLbl.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        titleVb.getChildren().addAll(titleLbl);
        titleVb.setAlignment(Pos.CENTER);
        titleVb.setMaxWidth(500);

//        addNewJobBtn = new Button("Add New Job");
//        addNewJobBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        jobListLbl = new Label("Job List");
        jobListLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        tableView = new TableView<>();

        jobIDColumn = new TableColumn<>("Job ID");
        jobIDColumn.setCellValueFactory(new PropertyValueFactory<>("job_id"));
        jobIDColumn.setMinWidth(250);

        pcIDColumn = new TableColumn<>("PC ID");
        pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pc_id"));
        pcIDColumn.setMinWidth(250);

        userIDColumn = new TableColumn<>("User ID");
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userIDColumn.setMinWidth(250);

        jobStatusColumn = new TableColumn<>("Job Status");
        jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
        jobStatusColumn.setMinWidth(250);

        ArrayList <Job> jobList = getAllJobData();
        tableView.getItems().addAll(jobList);

        assignJobLbl = new Label("Assign PC to Technician");
        assignJobLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        reportedPcList = new Label("Reported PC List");
        reportedPcList.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        reportedPCList = new ComboBox<>();
        reportedPCList.setPromptText("Select PC ID");
        // reportedPCList data is frm report data

        pc_idVb2 = new VBox(10);
        pc_idVb2.getChildren().addAll(reportedPcList, reportedPCList);
        pc_idVb2.setAlignment(Pos.CENTER_LEFT);

        technician_idLbl2 = new Label("Technician ID");
        technician_idLbl2.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        technicianList2 = new ComboBox<>();
        for (Job job : jobList){
            technicianList2.getItems().add(Integer.toString(job.getUserID()));
        }

        technician_idVb2 = new VBox(10);
        technician_idVb2.getChildren().addAll(technician_idLbl2, technicianList2);
        technician_idVb2.setAlignment(Pos.CENTER_LEFT);

        technicianList2.setPromptText(Integer.toString(jobList.get(0).getUserID()));

        hb2 = new HBox(10);
        hb2.getChildren().addAll(pc_idVb2, technician_idVb2);
        hb2.setAlignment(Pos.CENTER_LEFT);
        hb2.setSpacing(32);

        assignBtn = new Button("Assign");
        assignBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        assignVb = new VBox(10);
        assignVb.getChildren().addAll(assignJobLbl, hb2, assignBtn);
        assignVb.setAlignment(Pos.CENTER_LEFT);
        assignVb.setSpacing(32);

        updateLbl = new Label("Update Job Status");
        updateLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        updateJobStatusBtn = new Button("Update Status");
        updateJobStatusBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        updateJobStatusBtn.setPrefWidth(125);

        pc_idVb = new VBox(10);
        pc_idLbl = new Label("PC ID");
        pc_idLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        pc_idField = new TextField();
        pc_idField.setPromptText("Select PC ID From");
        pc_idField.setEditable(false);
        pc_idVb.getChildren().addAll(pc_idLbl, pc_idField);

        technician_idVb = new VBox(10);
        technician_idLbl = new Label("Technician ID");
        technician_idLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        technicianField = new TextField();
        technicianField.setPromptText("Select Technician ID");
        technicianField.setEditable(false);
        technician_idVb.getChildren().addAll(technician_idLbl, technicianField);

        job_statusVb = new VBox(10);
        job_statusLbl = new Label("Job Status");
        job_statusLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        statusPCList = new ComboBox<String>();
        statusPCList.getItems().addAll("Complete", "UnComplete");
        statusPCList.setPromptText("Select Job Status");
        job_statusVb.getChildren().addAll(job_statusLbl, statusPCList);

        tableView.getColumns().addAll(jobIDColumn, pcIDColumn, userIDColumn, jobStatusColumn);
        tableView.setPlaceholder(new Label("No Staffs Found"));

        jobManagementVb = new VBox(10);
        jobManagementVb.setPadding(new Insets(16));

        hb = new HBox(10);
        hb.getChildren().addAll(pc_idVb, technician_idVb, job_statusVb);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setSpacing(32);

        updateVb = new VBox(10);
        updateVb.getChildren().addAll(updateLbl, hb, updateJobStatusBtn);
        updateVb.setAlignment(Pos.CENTER_LEFT);
        updateVb.setSpacing(32);

        bp = new BorderPane();
        bp.setLeft(assignVb);
        bp.setRight(updateVb);

        jobManagementVb.getChildren().addAll(backVb, titleLbl, jobListLbl, tableView, bp);
        jobManagementVb.setAlignment(Pos.CENTER);
        jobManagementVb.setPadding(new Insets(64));
        jobManagementVb.setSpacing(32);

        scene = new Scene(jobManagementVb, 1200, 600);
    }

    void addEventListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            Job job = tableView.getSelectionModel().getSelectedItem();
            technicianField.setText(Integer.toString(job.getUserID()));
            pc_idField.setText(Integer.toString(job.getPc_id()));
            statusPCList.getSelectionModel().select(job.getJobStatus());
        });

        backBtn.setOnMouseClicked(e -> {
            TemporaryMenu temp = new TemporaryMenu();
            temp.show();
        });

//        addNewJobBtn.setOnAction(e -> {
//            AddNewJob addNewJob = new AddNewJob();
//            addNewJob.show();
//        });
    }

}
