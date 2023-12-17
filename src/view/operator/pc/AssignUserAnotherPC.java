package view.operator.pc;

import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import main.MainStage;
import view.TemporaryMenu;

public class AssignUserAnotherPC {
    private static AssignUserAnotherPC assignuseranotherpc;
    public static AssignUserAnotherPC getInstance() {
        return assignuseranotherpc = assignuseranotherpc == null ? new AssignUserAnotherPC() : assignuseranotherpc;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    Scene scene;
    BorderPane bp;
    FlowPane fp;

    public AssignUserAnotherPC() {
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

    void titleInit() {
        Label title = new Label("Assign User to Another PC");
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
            TemporaryMenu temp = TemporaryMenu.getInstance();
            temp.show();
        });
        bp.setTop(back);
    }

    void tableInit() {
        TableView<AssignUserAnotherPC> table = new TableView<>();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);

        TableColumn<AssignUserAnotherPC, Integer> noCol = new TableColumn<>("NO");
        noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
        noCol.setPrefWidth(200);

        TableColumn<AssignUserAnotherPC, String> operatorCol = new TableColumn<>("Operator");
        operatorCol.setCellValueFactory(new PropertyValueFactory<>("operator"));
        operatorCol.setPrefWidth(400);

        TableColumn<AssignUserAnotherPC, Integer> userIdCol = new TableColumn<>("User ID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userIdCol.setPrefWidth(200);

        TableColumn<AssignUserAnotherPC, Integer> pcidCol = new TableColumn<>("PC ID");
        pcidCol.setPrefWidth(200);

        ChoiceBox<String> operatorChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList("Operator 1", "Operator 2", "Operator 3"));
        operatorCol.setCellFactory(column -> new TableCell<AssignUserAnotherPC, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(operatorChoiceBox);
                    operatorChoiceBox.setValue(item);
                }
            }
        });

        ChoiceBox<Integer> userIdChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(1, 2, 3));
        userIdCol.setCellFactory(column -> new TableCell<AssignUserAnotherPC, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(userIdChoiceBox);
                    userIdChoiceBox.setValue(item);
                }
            }
        });

        ChoiceBox<Integer> pcidChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        pcidCol.setCellFactory(column -> new TableCell<AssignUserAnotherPC, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pcidChoiceBox);
                    pcidChoiceBox.setValue(item);
                }
            }
        });

//        table.getColumns().addAll(noCol, operatorCol, userIdCol, pcidCol);
//        table.getItems().add(new AssignUserAnotherPC(1, "Operator 1", 1, 13));
//        table.getItems().add(new AssignUserAnotherPC(2, "Operator 1", 2, 17));
//        table.getItems().add(new AssignUserAnotherPC(3, "Operator 3", 3, 20));

        fp.getChildren().add(table);
    }
}
