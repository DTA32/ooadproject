package view.Admin.job;

import controller.JobController;
import controller.PCController;
import controller.ReportController;
import controller.UserController;
import helper.Helper;
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
import model.Report;
import model.User;
import view.Admin.menu.AdminMenu;

import java.util.ArrayList;
import java.util.List;

public class JobManagement {

    private static JobManagement jobManagement;
    public static JobManagement getInstance() {
        return jobManagement = jobManagement == null ? new JobManagement() : jobManagement;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    Scene scene;
    VBox titleVb, pc_idVb, technician_idVb, job_statusVb, jobManagementVb, assignVb, backVb, updateVb, pc_idVb2, technician_idVb2;
    HBox hb, hb2;
    TextField pc_idField, technicianField;
    Label titleLbl, pc_idLbl, technician_idLbl, job_statusLbl, updateLbl, reportedPcList, assignJobLbl, technician_idLbl2;
    TableView<Job> tableView;
    Button  updateJobStatusBtn, backBtn, assignBtn;
    TableColumn<Job, String> jobIDColumn, pcIDColumn, userIDColumn, jobStatusColumn;
    ComboBox<String> statusPCList, reportedPCCb, technicianList2;
    BorderPane bp;


    public JobManagement() {
        initialize();
        addEventListener();
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
        backBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        backVb = new VBox(10);
        backVb.getChildren().addAll(backBtn);
        backVb.setAlignment(Pos.TOP_LEFT);

        titleLbl = new Label("JOB MANAGEMENT");
        titleLbl.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        titleVb.getChildren().addAll(titleLbl);
        titleVb.setAlignment(Pos.CENTER);
        titleVb.setMaxWidth(500);

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

        ReportController reportController = new ReportController();
        List <Report> reportList = reportController.GetAllReportData();

        reportedPCCb = new ComboBox<>();
        if (reportList.isEmpty()){
            reportedPCCb.setPromptText("No PC Reported");
        } else {
            reportedPCCb.setPromptText("Select PC ID");
            for (Report report : reportList){
                System.out.println(report.getPc_id());
                reportedPCCb.getItems().add(Integer.toString(report.getPc_id()));
            }
        }

        pc_idVb2 = new VBox(10);
        pc_idVb2.getChildren().addAll(reportedPcList, reportedPCCb);
        pc_idVb2.setAlignment(Pos.CENTER_LEFT);

        technician_idLbl2 = new Label("Technician ID");
        technician_idLbl2.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        ArrayList <User> technicianList = UserController.getAllTechnician();
        technicianList2 = new ComboBox<>();
        for (User user : technicianList){
            technicianList2.getItems().add(user.getUserID());
        }

        technician_idVb2 = new VBox(10);
        technician_idVb2.getChildren().addAll(technician_idLbl2, technicianList2);
        technician_idVb2.setAlignment(Pos.CENTER_LEFT);

        technicianList2.setValue(technicianList.get(0).getUserID());

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

        jobManagementVb.getChildren().addAll(backVb, titleLbl, tableView, bp);
        jobManagementVb.setAlignment(Pos.CENTER);
        jobManagementVb.setPadding(new Insets(64));
        jobManagementVb.setSpacing(32);

        scene = new Scene(jobManagementVb, 1200, 600);
    }

    void addEventListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;
            Job job = tableView.getSelectionModel().getSelectedItem();
            technicianField.setText(Integer.toString(job.getUserID()));
            pc_idField.setText(Integer.toString(job.getPc_id()));
            statusPCList.getSelectionModel().select(job.getJobStatus());
        });

        backBtn.setOnMouseClicked(e -> {
            AdminMenu adminMenu = AdminMenu.getInstance();
            adminMenu.show();
        });

        updateJobStatusBtn.setOnAction(e -> {
            Job job = tableView.getSelectionModel().getSelectedItem();
            boolean success = JobController.updateJobStatus(job.getJob_id(), statusPCList.getSelectionModel().getSelectedItem());
            if (success) {
                PCController.updatePCCondition(Integer.toString(job.getPc_id()), statusPCList.getSelectionModel().getSelectedItem());
                Helper.showAlert(Alert.AlertType.INFORMATION, "Job Status Updated Successfully!");
                _repaint();
            }
        });

        assignBtn.setOnAction(e -> {
            if (reportedPCCb.getSelectionModel().isEmpty() || technicianList2.getSelectionModel().isEmpty()){
                Helper.showAlert(Alert.AlertType.ERROR, "Please select PC ID and Technician ID");
            } else {
                boolean success = JobController.addNewJob(Integer.parseInt(technicianList2.getSelectionModel().getSelectedItem()), Integer.parseInt(reportedPCCb.getSelectionModel().getSelectedItem()));
                System.out.println("Success : " + success);
                if (success) {
                    Helper.showAlert(Alert.AlertType.INFORMATION, "Job Assigned Successfully!");
                    _repaint();
                }
            }
        });

    }

    public void _repaint() {
        tableView.getItems().clear();

        ArrayList <Job> jobList = getAllJobData();

        for(Job job : jobList) {
            tableView.getItems().add(job);
        }
    }

}
