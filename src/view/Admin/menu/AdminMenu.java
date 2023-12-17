package view.Admin.menu;

import helper.Helper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;
import model.User;
import view.Admin.job.JobManagement;
import view.Admin.pc.PCManagement;
import view.Admin.pc.ViewPC;
import view.Admin.report.ViewAllReport;
import view.Admin.staff.ViewAllStaffs;
import view.Admin.transaction.TransactionHistory;

public class AdminMenu {

    private static AdminMenu adminMenu;
    public static AdminMenu getInstance() {
        return adminMenu = adminMenu == null ? new AdminMenu() : adminMenu;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    Scene scene;
    VBox companyVb, titleDescVb, adminMenuVb;
    HBox mainMenuVb;
    Label companyLbl, titleLbl, descriptionLbl;
    Button crud_pcBtn, viewAllStaffJobBtn, viewAllReportBtn, viewAllStaffBtn, viewAllTransactionHistoryBtn, viewAllPCBtn;

    public AdminMenu() {
        initialize();
        addEventListener();
    }

    private void initialize() {
        titleDescVb = new VBox(10);
        mainMenuVb = new HBox(10);
        adminMenuVb = new VBox(10);
        companyVb = new VBox();

        companyLbl = new Label("Internet CLafes");
        companyLbl.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 48));

        companyVb.setAlignment(Pos.CENTER);
        companyVb.getChildren().add(companyLbl);
        companyVb.maxWidth(500);
        companyVb.setPadding(new Insets(0, 0, 48, 0));

        User user = Helper.getCurrentUser();
        titleLbl = new Label("Welcome " + user.getUserName() + "!");
        titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        descriptionLbl = new Label("Main menu for admin role");
        descriptionLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        titleDescVb.setAlignment(Pos.CENTER);
        titleDescVb.getChildren().addAll(companyVb, titleLbl, descriptionLbl);
        titleDescVb.setMaxWidth(500);
        titleDescVb.setSpacing(16);

        crud_pcBtn = new Button("CRUD PC");
        crud_pcBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        viewAllStaffJobBtn = new Button("View All Staff Job");
        viewAllStaffJobBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        viewAllPCBtn = new Button("View All PC");
        viewAllPCBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        viewAllReportBtn = new Button("View All Report");
        viewAllReportBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        viewAllStaffBtn = new Button("View All Staff");
        viewAllStaffBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        viewAllTransactionHistoryBtn = new Button("View All Transaction History");
        viewAllTransactionHistoryBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        mainMenuVb.setAlignment(Pos.CENTER);
        mainMenuVb.getChildren().addAll(
                crud_pcBtn, viewAllStaffJobBtn, viewAllReportBtn,
                viewAllStaffBtn, viewAllTransactionHistoryBtn
        );
        mainMenuVb.setSpacing(16);

        adminMenuVb = new VBox();
        adminMenuVb.setAlignment(Pos.CENTER);
        adminMenuVb.getChildren().addAll(titleDescVb, mainMenuVb);
        adminMenuVb.setPadding(new Insets(64));
        adminMenuVb.setSpacing(16);

        scene = new Scene(adminMenuVb, 1200, 600);
    }

    private void addEventListener() {
        crud_pcBtn.setOnMouseClicked(e->{
            PCManagement pcManagement = PCManagement.getInstance();
            pcManagement.show();
        });
        viewAllStaffBtn.setOnMouseClicked(e -> {
            ViewAllStaffs viewAllStaffs = new ViewAllStaffs();
            viewAllStaffs.show();
        });
        viewAllStaffJobBtn.setOnMouseClicked(e -> {
            JobManagement jobManagement = new JobManagement();
            jobManagement.show();
        });
        viewAllReportBtn.setOnMouseClicked(e -> {
            ViewAllReport report = new ViewAllReport();
            report.show();
        });
        viewAllPCBtn.setOnMouseClicked(e -> {
            ViewPC viewPC = new ViewPC();
            viewPC.show();
        });
        viewAllTransactionHistoryBtn.setOnMouseClicked(e -> {
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.show();
        });
    }
}
