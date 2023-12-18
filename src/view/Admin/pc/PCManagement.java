package view.Admin.pc;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.MainStage;
import model.PC;
import controller.PCController;
import view.Admin.menu.AdminMenu;

public class PCManagement {

    private static PCManagement pcManagement;

    public static PCManagement getInstance() {
        return pcManagement = pcManagement == null ? new PCManagement() : pcManagement;
    }

    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }


    Scene scene;
    BorderPane bp;
    FlowPane fp;
    TableView<PC> table;
    TableColumn<PC, String> pcId, Status;
    TextField pcIdInput;
    ComboBox<String> pcConditionInput;

    Button addButton, updateButton, deleteButton;

    public PCManagement(){
        initialize();
        setupEventHandling();
    }

    private void initialize() {
        bp = new BorderPane();
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);

        titleInit();
        inputInit();
        buttonInit();
        tableInit();
        backInit();

        VBox leftContainer = new VBox(10, pcIdInput, pcConditionInput, addButton, updateButton, deleteButton);
        leftContainer.setAlignment(Pos.CENTER);
        leftContainer.setPadding(new Insets(10));
        bp.setLeft(leftContainer);
        bp.setCenter(table);
        scene = new Scene(bp, 1200, 600);
    }

    void _repaint(){
        table.getItems().clear();
        table.getItems().addAll(PCController.getAllPc());
    }

    void titleInit() {
        Label title = new Label("PC Management");
        title.setFont(new Font("Arial", 24));
        title.setAlignment(Pos.CENTER);
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
    }

    void backInit() {
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            AdminMenu adminMenu = AdminMenu.getInstance();
            adminMenu.show();
        });
        bp.setTop(back);
    }

    void buttonInit(){
        addButton = new Button("Add Pc");
        updateButton = new Button("Update Pc");
        deleteButton = new Button("Delete Pc");
        HBox buttonContainer = new HBox(10, addButton, updateButton, deleteButton);
        buttonContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(buttonContainer);
    }

    void inputInit() {
        pcIdInput = new TextField();
        pcIdInput.setPromptText("Enter PC ID");
        pcIdInput.setMaxWidth(200);

        pcConditionInput = new ComboBox<>();
        pcConditionInput.getItems().addAll("Usable", "Maintenance", "Broken");
        pcConditionInput.setPromptText("Select PC Condition");
        pcConditionInput.setMaxWidth(200);

        VBox inputContainer = new VBox(10, pcIdInput, pcConditionInput);
        inputContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(inputContainer);
    }

    void tableInit() {
        table = new TableView<>();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);

        pcId = new TableColumn("PC ID");
        pcId.setCellValueFactory(new PropertyValueFactory<>("pcid"));
        pcId.setPrefWidth(200);

        Status = new TableColumn("Status");
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Status.setPrefWidth(690);

        table.getColumns().addAll(pcId, Status);
        _repaint();

    }


    void setupEventHandling(){
            addButton.setOnMouseClicked(e -> {
                String pcidText = pcIdInput.getText();
                PCController.addNewPc(pcidText);
                _repaint();
                pcIdInput.clear();
            });

            updateButton.setOnMouseClicked(e -> {
                String pcidText = pcIdInput.getText();
                String statusText = pcConditionInput.getValue();
                PCController.updatePC(pcidText, statusText);
                _repaint();
                pcIdInput.clear();
            });

            deleteButton.setOnMouseClicked(e -> {
                String pcidText = pcIdInput.getText();
                PCController.deletePC(pcidText);
                _repaint();
                pcIdInput.clear();
            });

    }
}
