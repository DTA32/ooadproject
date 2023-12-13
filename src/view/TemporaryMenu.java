package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.geometry.*;
import main.MainStage;
import view.auth.Login;
import view.auth.Register;


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
            Report report = Report.getInstance();
            report.show();
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

        fp.getChildren().addAll(title, bookPC, viewReport,
                register, allPc, bookedPC,
                cancelPC, finishPC, login,
                history, TechJob);

        scene = new Scene(fp, 1200, 600);
    }
}
