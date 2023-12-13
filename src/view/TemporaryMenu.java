package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.geometry.*;
import main.MainStage;
import model.CustomerTransactionHistoryModel;
import view.auth.Login;


public class TemporaryMenu {
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


    public TemporaryMenu(){
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.CENTER);
        fp.setVgap(48);

        Label title = new Label("TEMPORARY MENU, ONLY FOR FUNCTIONAL TESTS");

        bookPC = new Button("Book PC");
        bookPC.setOnMouseClicked(e -> {
            BookPC bookPC = new BookPC();
            MainStage.stage.setScene(bookPC.getScene());
        });
        viewReport = new Button("View Report");
        viewReport.setOnMouseClicked(e -> {
            Report report = new Report();
            MainStage.stage.setScene(report.getScene());
        });
        register = new Button("Register");
        register.setOnMouseClicked(e -> {
            Register register = new Register();
            MainStage.stage.setScene(register.getScene());
        });
        allPc = new Button("All PC");
        allPc.setOnMouseClicked(e -> {
            ViewPC allPc = new ViewPC();
            MainStage.stage.setScene(allPc.getScene());
        });

        bookedPC = new Button("Booked PC");
        bookedPC.setOnMouseClicked(e -> {
            BookedPC bookedPC = new BookedPC();
            MainStage.stage.setScene(bookedPC.getScene());
        });

        cancelPC = new Button("Cancel PC");
        cancelPC.setOnMouseClicked(e -> {
            CancelPC cancelPC = new CancelPC();
            MainStage.stage.setScene(cancelPC.getScene());
        });

        finishPC = new Button("Finish PC");
        finishPC.setOnMouseClicked(e -> {
            FinishPC finishPC = new FinishPC();
            MainStage.stage.setScene(finishPC.getScene());
        });

        login = new Button("Login");
        login.setOnMouseClicked(e -> {
            Login login = new Login();
            MainStage.stage.setScene(login.getScene());
        });

        history = new Button("History");
        history.setOnMouseClicked(e -> {
            CustomerTransactionHistory history = new CustomerTransactionHistory();
            MainStage.stage.setScene(history.getScene());
        });

        TechJob = new Button("Technician Job List");
        TechJob.setOnMouseClicked(e -> {
            TechnicianListJob techJob = new TechnicianListJob();
            MainStage.stage.setScene(techJob.getScene());
        });

        fp.getChildren().addAll(title, bookPC, viewReport,
                register, allPc, bookedPC,
                cancelPC, finishPC, login,
                history, TechJob);

        scene = new Scene(fp, 1200, 600);
    }

    public Scene getScene(){
        return scene;
    }
}
