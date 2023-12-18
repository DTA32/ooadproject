package view.customer.menu;

import helper.Helper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;
import model.User;
import view.auth.Login;
import view.Admin.pc.ViewPC;
import view.customer.pc.BookPC;
import view.customer.report.MakeReport;
import view.customer.transaction.CustomerTransactionHistory;

public class CustomerMenu {

    private static CustomerMenu customerMenu;
    public static CustomerMenu getInstance() {
        return customerMenu = customerMenu == null ? new CustomerMenu() : customerMenu;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    Scene scene;
    VBox companyVb, titleDescVb, adminMenuVb;
    HBox mainMenuVb;
    Label companyLbl, titleLbl, descriptionLbl;
    Button makeReportBtn, viewCustomerTransactionHistoryBtn, viewAllPCBtn, logoutBtn;

    public CustomerMenu() {
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

        descriptionLbl = new Label("Main menu for customer role");
        descriptionLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        titleDescVb.setAlignment(Pos.CENTER);
        titleDescVb.getChildren().addAll(companyVb, titleLbl, descriptionLbl);
        titleDescVb.setMaxWidth(500);
        titleDescVb.setSpacing(16);

        makeReportBtn = new Button("Make Report");
        makeReportBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        viewCustomerTransactionHistoryBtn = new Button("View Transaction History");
        viewCustomerTransactionHistoryBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        viewAllPCBtn = new Button("View All PC");
        viewAllPCBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        logoutBtn = new Button("Logout");
        logoutBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        mainMenuVb.setAlignment(Pos.CENTER);
        mainMenuVb.getChildren().addAll(
                viewCustomerTransactionHistoryBtn, viewAllPCBtn
        );
        mainMenuVb.setSpacing(16);

        adminMenuVb = new VBox();
        adminMenuVb.setAlignment(Pos.CENTER);
        adminMenuVb.getChildren().addAll(titleDescVb, mainMenuVb, Helper.logoutRender());
        adminMenuVb.setPadding(new Insets(64));
        adminMenuVb.setSpacing(16);

        scene = new Scene(adminMenuVb, 1200, 600);
    }

    private void addEventListener() {
        makeReportBtn.setOnMouseClicked(e -> {
            MakeReport makeReportMenu = MakeReport.getInstance();
            makeReportMenu.show();
        });
        viewCustomerTransactionHistoryBtn.setOnMouseClicked(e -> {
            CustomerTransactionHistory customerTransactionHistory = CustomerTransactionHistory.getInstance();
            customerTransactionHistory.show();
        });

        viewAllPCBtn.setOnMouseClicked(e -> {
            ViewPC viewPC = new ViewPC();
            viewPC.show();
        });
    }

}
