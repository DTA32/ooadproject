package view.operator.pc;

import controller.PCBookController;
import controller.PCController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;
import model.PC;
import model.PCBook;
import view.operator.menu.OperatorMenu;

import java.util.ArrayList;

public class AssignUserAnotherPC {
    private static AssignUserAnotherPC assignuseranotherpc;
    public static AssignUserAnotherPC getInstance() {
        return assignuseranotherpc = assignuseranotherpc == null ? new AssignUserAnotherPC() : assignuseranotherpc;
    }
    public void show(){
        _repaint();
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    VBox assignUserAnotherPCVb, titleVb, backVb, assignVb, pcIdOldVb, pcIdNewvB;
    HBox hb;
    Label titleLbl, descriptionLbl, pcIdOldLbl, pcIdNewLbl, noteLbl;
    TextField pcIdField;
    Scene scene;
    Button back, assignToAnotherPCBtn;
    TableView<PCBook> table;
    TableColumn<PCBook, String> userIdCol, pcidCol, bookedTimeCol;
    TableColumn<PCBook, Integer> bookIDCol;
    ComboBox<Integer> pcList;


    public AssignUserAnotherPC() {
        assignUserAnotherPCVb = new VBox(10);
        backInit();
        titleInit();
        tableInit();
        addEventListener();
    }

    ArrayList<PC> getAllPcData(){
        return PCController.getAllPCData();
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
        backVb = new VBox(10);
        backVb.getChildren().addAll(back);
        backVb.setAlignment(Pos.CENTER_LEFT);
        assignUserAnotherPCVb.getChildren().add(backVb);
    }

    void tableInit() {
        table = new TableView<>();

        bookIDCol = new TableColumn<>("Book ID");
        bookIDCol.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        bookIDCol.setMinWidth(250);

        userIdCol = new TableColumn<>("User ID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        userIdCol.setMinWidth(250);

        pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pc_id"));
        pcidCol.setMinWidth(250);

        bookedTimeCol = new TableColumn<>("Booked Date");
        bookedTimeCol.setCellValueFactory(new PropertyValueFactory<>("booked_date"));
        bookedTimeCol.setMinWidth(250);

        table.getColumns().addAll(bookIDCol, userIdCol, pcidCol, bookedTimeCol);

        ObservableList<PCBook> bookedPC = PCBook.getAllBookedPCs();
        table.setItems(bookedPC);

        descriptionLbl = new Label("Select a PC to assign user to another PC");
        descriptionLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        pcIdOldLbl = new Label("Current PC ID : ");
        pcIdOldLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        pcIdField = new TextField();
        pcIdField.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        pcIdField.setEditable(false);
        pcIdField.setPromptText("Select a PC ID!");

        pcIdOldVb = new VBox(10);
        pcIdOldVb.getChildren().addAll(pcIdOldLbl, pcIdField);

        pcIdNewLbl = new Label("New PC ID : ");
        pcIdNewLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        pcList = new ComboBox<>();

        pcIdNewvB = new VBox(10);
        pcIdNewvB.getChildren().addAll(pcIdNewLbl, pcList);

        hb = new HBox(10);
        hb.getChildren().addAll(pcIdOldVb, pcIdNewvB);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setSpacing(16);

        noteLbl = new Label("Note : You cannot assign a pc that have been booked by another user!");
        noteLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        assignToAnotherPCBtn = new Button("Assign User to Another PC");
        assignToAnotherPCBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));

        assignVb = new VBox(10);
        assignVb.getChildren().addAll(table, descriptionLbl, hb,  noteLbl, assignToAnotherPCBtn);
        assignVb.setAlignment(Pos.CENTER_LEFT);

        assignUserAnotherPCVb.getChildren().add(assignVb);
        assignUserAnotherPCVb.setAlignment(Pos.CENTER);
        assignUserAnotherPCVb.setPadding(new Insets(64));
        assignUserAnotherPCVb.setSpacing(32);

        scene = new Scene(assignUserAnotherPCVb, 1200, 600);
    }

    void addEventListener(){
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;
            PCBook pcBook = table.getSelectionModel().getSelectedItem();
            pcIdField.setText(String.valueOf(pcBook.getPc_id()));
        });

        assignToAnotherPCBtn.setOnAction(e -> {
            PCBook pcBook = table.getSelectionModel().getSelectedItem();
            int book_id = pcBook.getBook_id();
            int newPc_id = pcList.getSelectionModel().getSelectedItem();
            boolean checked = PCBookController.assignUserToNewPc(book_id, newPc_id);
            if (checked){
                _repaint();
            }
        });
    }

    public void _repaint() {
        pcList.getItems().clear();
        ArrayList <PC> allPcData = getAllPcData();
        for (PC pc : allPcData) {
            pcList.getItems().add(pc.getPcid());
        }
        pcList.getSelectionModel().selectFirst();
        table.getItems().clear();
        ObservableList<PCBook> bookedPC = PCBook.getAllBookedPCs();
        table.setItems(bookedPC);
    }
}
