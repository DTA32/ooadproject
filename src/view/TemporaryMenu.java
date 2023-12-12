package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.geometry.*;
import main.MainStage;


public class TemporaryMenu {
    Scene scene;
    FlowPane fp;

    Button bookPC;
    Button viewReport;
    Button register;

    Button allPc;

    Button bookedPC;

    Button cancelPC;

    Button finishPC;


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

        fp.getChildren().addAll(title, bookPC, viewReport, register, allPc, bookedPC, cancelPC, finishPC);

        scene = new Scene(fp, 1200, 600);
    }

    public Scene getScene(){
        return scene;
    }
}
