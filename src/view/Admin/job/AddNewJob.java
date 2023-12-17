//package view.Admin.job;
//
//import controller.JobController;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import main.MainStage;
//import model.Job;
//import view.TemporaryMenu;
//
//import java.util.ArrayList;
//
//public class AddNewJob {
//    Scene scene;
//    VBox titleVb, pc_idVb, technician_idVb, job_statusVb, jobManagementVb, assignRoleVb;
//    HBox hb;
//    TextField pc_idField;
//    Label titleLbl, pc_idLbl, technician_idLbl, job_statusLbl, descriptionLbl;
//    Button assignBtn;
//    ComboBox<String> technicianList, pcIdList;
//
//    public addNewJob() {
//        topInit();
//        initialize();
//    }
//
//    public void show(){
//        MainStage stage = MainStage.getInstance();
//        stage.getStage().setScene(scene);
//    }
//
//    public ArrayList<Job> getAllJobData(){
//        return JobController.getAllJobData();
//    }
//
//    public Scene getScene() {
//        return scene;
//    }
//
//    void topInit(){
//        VBox topContainer = new VBox();
//        Button back = new Button("< Back");
//        back.setOnMouseClicked(e -> {
//            TemporaryMenu temp = new TemporaryMenu();
//            temp.show();
//        });
//        topContainer.getChildren().addAll(back);
////        bp.setTop(topContainer);
//    }
//
//    private void initialize() {
//        titleVb = new VBox(10);
//
//        titleLbl = new Label("CREATE NEW JOB");
//        titleLbl.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));
//
//        titleVb.getChildren().addAll(titleLbl);
//        titleVb.setAlignment(Pos.CENTER);
//        titleVb.setMaxWidth(500);
//
//        descriptionLbl = new Label("Select Technician ID and PC ID");
//        descriptionLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
//
//        technician_idVb = new VBox(10);
//        technician_idLbl = new Label("Technician ID");
//        technician_idLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
//
//        pc_idVb = new VBox(10);
//        pc_idLbl = new Label("PC ID");
//        pc_idLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
//
//        pc_idField = new TextField();
//        pc_idField.setPromptText("Select PC ID From");
//        pc_idField.setEditable(false);
//        pc_idVb.getChildren().addAll(pc_idLbl, pc_idField);
//
//        technicianList = new ComboBox<>();
//        technicianList.setPromptText("Select Technician ID");
//        for (Job job : jobList) {
//            technicianList.getItems().add(Integer.toString(job.getUserID()));
//        }
//        technician_idVb.getChildren().addAll(technician_idLbl, technicianList);
//
//        assignBtn = new Button("Assign");
//        assignBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
//        assignBtn.setPrefWidth(125);
//
//        job_statusVb = new VBox(10);
//        job_statusLbl = new Label("Job Status");
//        job_statusLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
//
//        statusPCList = new ComboBox<String>();
//        statusPCList.getItems().addAll("Complete", "UnComplete");
//        statusPCList.setPromptText("Select Job Status");
//        job_statusVb.getChildren().addAll(job_statusLbl, statusPCList);
//
//        tableView.getColumns().addAll(jobIDColumn, pcIDColumn, userIDColumn, jobStatusColumn);
//        tableView.setPlaceholder(new Label("No Staffs Found"));
//
//        jobManagementVb = new VBox(10);
//        jobManagementVb.setPadding(new Insets(16));
//
//        hb = new HBox(10);
//        hb.getChildren().addAll(pc_idVb, technician_idVb, job_statusVb);
//        hb.setAlignment(Pos.CENTER);
//        hb.setSpacing(32);
//
//        assignRoleVb = new VBox(10);
//        assignRoleVb.getChildren().addAll(titleLbl, hb);
//        assignRoleVb.setSpacing(16);
//
//        jobManagementVb.getChildren().addAll(titleLbl, addNewJobBtn, tableView, assignLbl, assignRoleVb, assignBtn);
//        jobManagementVb.setAlignment(Pos.CENTER);
//        jobManagementVb.setPadding(new Insets(64));
//        jobManagementVb.setSpacing(32);
//
//        scene = new Scene(jobManagementVb, 1200, 600);
//    }
//}
