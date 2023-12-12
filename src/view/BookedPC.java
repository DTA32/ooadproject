package view;

import helper.Helper;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.MainStage;
import javafx.scene.control.Alert.AlertType;
import model.PC;
import model.ReportModel;

public class BookedPC {

    Scene scene;

    BorderPane bp;

    FlowPane fp;

    public BookedPC(){
        bp = new BorderPane();
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);

        bp.setCenter(fp);

        titleInit();
        tableInit();
        backInit();

        scene = new Scene(bp, 1200, 600);
    }

    public Scene getScene(){
        return scene;
    }

    void titleInit(){
        Label title = new Label("Booked PC");
        title.setFont(new Font("Arial", 24));
        title.setAlignment(Pos.CENTER);
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = new TemporaryMenu();
            MainStage.stage.setScene(temp.getScene());
        });
        bp.setTop(back);
    }

    private void cancelBook(){
        Button cancel = new Button("Cancel");
        cancel.setOnMouseClicked(e -> {
            //Ini nanti juga di Controller
            Helper.showAlert(AlertType.INFORMATION, "Canceled Booking");
        });
        fp.getChildren().add(cancel);

    }

    private void finishBook(){
        Button finish = new Button("Finish");
        finish.setOnMouseClicked(e -> {
            //Ini nanti di Controller
            Helper.showAlert(AlertType.INFORMATION, "Finished Booking");
        });

        fp.getChildren().add(finish);
    }

    void tableInit(){
        TableView table = new TableView();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);

        TableColumn<PC, Integer> pcIdCol= new TableColumn("PC ID");
        pcIdCol.setCellValueFactory(new PropertyValueFactory("pcid"));
        pcIdCol.setPrefWidth(200);

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<PC, String> ("status"));
        statusCol.setPrefWidth(200);

        TableColumn<PC, String> optionCol = new TableColumn("Options");
        optionCol.setPrefWidth(200);

        optionCol.setCellFactory(col -> new TableCell<PC, String>() {
            private final ChoiceBox<String> choiceBox = new ChoiceBox<>();

            {
                choiceBox.getItems().addAll("Cancel", "Finish");
                choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                    if (!isEmpty()) {
                        PC currentPc = getTableView().getItems().get(getIndex());
                        if ("Cancel".equals(newVal)) {
                            currentPc.setStatus("Cancelled");
                        } else if ("Finish".equals(newVal)) {
                            currentPc.setStatus("Finished");
                        }
                        getTableView().refresh();
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(choiceBox);
                }
            }
        });





        table.getColumns().addAll(pcIdCol, statusCol, optionCol);
        table.getItems().add(new PC(1, "Booked"));
        table.getItems().add(new PC(2, "Not Booked"));
        table.getItems().add(new PC(3, "Booked"));
        fp.getChildren().add(table);
    }
}
