package view.operator.menu;

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
import view.customer.report.MakeReport;
import view.auth.Login;
import view.operator.pc.*;

public class OperatorMenu {


    private static OperatorMenu operatorMenu;
    public static OperatorMenu getInstance() {
        return operatorMenu = operatorMenu == null ? new OperatorMenu() : operatorMenu;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    Scene scene;
    VBox companyVb, titleDescVb, adminMenuVb;
    HBox mainMenuVb;
    Label companyLbl, titleLbl, descriptionLbl;
    Button assignUserToAnotherPCBtn, makeReportBtn, bookedPcBtn, finishPcBtn;

    public OperatorMenu() {
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

        descriptionLbl = new Label("Main menu for operator role");
        descriptionLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        titleDescVb.setAlignment(Pos.CENTER);
        titleDescVb.getChildren().addAll(companyVb, titleLbl, descriptionLbl);
        titleDescVb.setMaxWidth(500);
        titleDescVb.setSpacing(16);

        assignUserToAnotherPCBtn = new Button("Assign User to Another PC");
        assignUserToAnotherPCBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        makeReportBtn = new Button("Make Report");
        makeReportBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        bookedPcBtn = new Button("Booked PC");
        bookedPcBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        finishPcBtn = new Button("Finish PC");
        finishPcBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));


        mainMenuVb.setAlignment(Pos.CENTER);
        mainMenuVb.getChildren().addAll(
                assignUserToAnotherPCBtn,
                makeReportBtn,
                bookedPcBtn,
                finishPcBtn
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
        assignUserToAnotherPCBtn.setOnMouseClicked(e -> {
            AssignUserAnotherPC assignUserAnotherPC = AssignUserAnotherPC.getInstance();
            assignUserAnotherPC.show();
        });
        makeReportBtn.setOnMouseClicked(e -> {
            MakeReport makeReport = MakeReport.getInstance();
            makeReport.show();
        });
        bookedPcBtn.setOnMouseClicked(e -> {
            BookedPC bookedPC = BookedPC.getInstance();
            bookedPC.show();
        });
        finishPcBtn.setOnMouseClicked(e -> {
            FinishPC finishPC = FinishPC.getInstance();
            finishPC.show();
        });
    }

}
