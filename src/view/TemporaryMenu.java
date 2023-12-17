package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.geometry.*;
import main.MainStage;
import view.Admin.job.JobManagement;
import view.Admin.pc.CancelPC;
import view.Admin.pc.ViewPC;
import view.Admin.report.ViewAllReport;
import view.Admin.staff.ViewAllStaffs;
import view.Admin.transaction.TransactionHistory;
import view.auth.Login;
import view.auth.Register;
import view.computer_technician.ViewTechnicianJob;
import view.customer.pc.BookPC;
import view.customer.transaction.CustomerTransactionHistory;
import view.operator.pc.AssignUserAnotherPC;

public class TemporaryMenu {
    private static TemporaryMenu temporaryMenu;

    public static TemporaryMenu getInstance() {
        return temporaryMenu = temporaryMenu == null ? new TemporaryMenu() : temporaryMenu;
    }

    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    Scene scene;
    FlowPane fp;

    Button bookPC;
    Button viewReport;
    Button register;
    Button login;

    Button allPc;

    Button bookedPC;

    Button cancelPC;

    Button finishPC;

    Button history;

    Button TechJob;

    Button Transactions;

    Button AssignUser;

    Button Management;

    Button viewAllStaffs;
    Button viewtechnicianJob;
    Button jobManagementBtn;
    Button makeReportBtn;


    public TemporaryMenu() {
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.CENTER);
        fp.setVgap(48);

        Label title = new Label("TEMPORARY MENU, ONLY FOR FUNCTIONAL TESTS");

        bookPC = new Button("Book PC");
        bookPC.setOnMouseClicked(e -> {
            BookPC bookPC = BookPC.getInstance();
            bookPC.show();
        });
        viewReport = new Button("View Report");
        viewReport.setOnMouseClicked(e -> {
            ViewAllReport viewAllReport = ViewAllReport.getInstance();
            viewAllReport.show();
        });
        register = new Button("Register");
        register.setOnMouseClicked(e -> {
            Register register = Register.getInstance();
            register.show();
        });
        allPc = new Button("All PC");
        allPc.setOnMouseClicked(e -> {
            ViewPC allPc = ViewPC.getInstance();
            allPc.show();
        });

        bookedPC = new Button("Booked PC");
        bookedPC.setOnMouseClicked(e -> {
            BookedPC bookedPC = BookedPC.getInstance();
            bookedPC.show();
        });

        cancelPC = new Button("Cancel PC");
        cancelPC.setOnMouseClicked(e -> {
            CancelPC cancelPC = CancelPC.getInstance();
            cancelPC.show();
        });

        finishPC = new Button("Finish PC");
        finishPC.setOnMouseClicked(e -> {
            FinishPC finishPC = FinishPC.getInstance();
            finishPC.show();
        });

        login = new Button("Login");
        login.setOnMouseClicked(e -> {
            Login login = Login.getInstance();
            login.show();
        });

        history = new Button("History");
        history.setOnMouseClicked(e -> {
            CustomerTransactionHistory customerTransactionHistory = CustomerTransactionHistory.getInstance();
            customerTransactionHistory.show();
        });

        TechJob = new Button("Technician Job List");
        TechJob.setOnMouseClicked(e -> {
            TechnicianListJob techJob = TechnicianListJob.getInstance();
            techJob.show();
        });

        Transactions = new Button("Transaction History");
        Transactions.setOnMouseClicked(e -> {
        	TransactionHistory transHistory = TransactionHistory.getInstance();
        	transHistory.show();
        });

        AssignUser = new Button("Assign User to Another PC");
        AssignUser.setOnMouseClicked(e -> {
            AssignUserAnotherPC assignUser = AssignUserAnotherPC.getInstance();
            assignUser.show();
        });

        viewAllStaffs = new Button("View All Staffs");
        viewAllStaffs.setOnMouseClicked(e -> {
            ViewAllStaffs viewAllStaffs = new ViewAllStaffs();
            viewAllStaffs.show();
        });

        viewtechnicianJob = new Button("View Technician Job");
        viewtechnicianJob.setOnMouseClicked(e -> {
            ViewTechnicianJob viewtechnicianJob = new ViewTechnicianJob();
            viewtechnicianJob.show();
        });

        jobManagementBtn = new Button("Job Management");
        jobManagementBtn.setOnMouseClicked(e -> {
            JobManagement jobManagement = new JobManagement();
            jobManagement.show();
        });

        makeReportBtn = new Button("Make Report");
        makeReportBtn.setOnMouseClicked(e -> {
            MakeReport makeReport = MakeReport.getInstance();
            makeReport.show();
        });

        Management = new Button("PC Management");
        Management.setOnMouseClicked(e -> {
            PCManagement pcManagement = PCManagement.getInstance();
            pcManagement.show();
        });


        fp.getChildren().addAll(title, bookPC, viewReport,
                register, allPc, bookedPC,
                cancelPC, finishPC, login,
                history, TechJob, viewAllStaffs,
                viewtechnicianJob, jobManagementBtn, Transactions,
                AssignUser, makeReportBtn,
                history, TechJob, Management);

        scene = new Scene(fp, 1200, 600);
    }
}
