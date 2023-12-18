package view.operator.pc;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;
import model.Job;
import model.PCBook;
import view.TemporaryMenu;
import view.operator.menu.OperatorMenu;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class AssignUserAnotherPC {
    private static AssignUserAnotherPC assignuseranotherpc;
    public static AssignUserAnotherPC getInstance() {
        return assignuseranotherpc = assignuseranotherpc == null ? new AssignUserAnotherPC() : assignuseranotherpc;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    VBox assignUserAnotherPCVb, titleVb;
    Label titleLbl;
    Scene scene;
    Button back;
    TableView<PCBook> table;
    TableColumn<PCBook, String> userIdCol, pcidCol, bookedTimeCol;

    TableColumn<PCBook, Integer> operatorCol;


    public AssignUserAnotherPC() {
        assignUserAnotherPCVb = new VBox(10);
        titleInit();
        tableInit();
        backInit();
    }

    void titleInit() {
        titleLbl = new Label("ASSIGN USER TO ANOTHER PC");
        titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        titleVb = new VBox(10);
        titleVb.getChildren().addAll(titleLbl);
        titleVb.setAlignment(Pos.CENTER);

        assignUserAnotherPCVb.getChildren().add(titleVb);
    }

    void backInit() {
        back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            OperatorMenu operatorMenu = OperatorMenu.getInstance();
            operatorMenu.show();
        });
        assignUserAnotherPCVb.getChildren().add(back);
    }

    void tableInit() {
        table = new TableView<>();

        operatorCol = new TableColumn<>("Book ID");
        operatorCol.setCellValueFactory(new PropertyValueFactory<>("book_id"));

        userIdCol = new TableColumn<>("User ID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pc_id"));

        bookedTimeCol = new TableColumn<>("Booked Date");
        bookedTimeCol.setCellValueFactory(new PropertyValueFactory<>("booked_date"));

//        ArrayList<Job> jobList =
//        table.getItems().addAll(

//        scene = new Scene(bp, 1000, 600);
    }
}
