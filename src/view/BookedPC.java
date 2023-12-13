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
    private static BookedPC bookedPC;
    public static BookedPC getInstance() {
        return bookedPC = bookedPC == null ? new BookedPC() : bookedPC;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

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
            TemporaryMenu temp = TemporaryMenu.getInstance();
            temp.show();
        });
        bp.setTop(back);
    }

    private void cancelBook(){
        Button cancel = new Button("Cancel");
        cancel.setOnMouseClicked(e -> {
//            CancelPC cancelPC = CancelPC.getInstance();
//            cancelPC.show();
        });
        fp.getChildren().add(cancel);

    }

    private void finishBook(){
        Button finish = new Button("Finish");
        finish.setOnMouseClicked(e -> {
//            FinishPC finishPC = FinishPC.getInstance();
//            finishPC.show();
        });

        fp.getChildren().add(finish);
    }

    void tableInit(){
        TableView table = new TableView();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);


        TableColumn bookIDCol = new TableColumn("Book ID");
        bookIDCol.setCellValueFactory(new PropertyValueFactory<PC, String> ("bookid"));
        bookIDCol.setPrefWidth(200);

        TableColumn<PC, Integer> pcIdCol= new TableColumn("PC ID");
        pcIdCol.setCellValueFactory(new PropertyValueFactory("pcid"));
        pcIdCol.setPrefWidth(200);

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<PC, String> ("status"));
        statusCol.setPrefWidth(200);

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<PC, String> ("date"));
        dateCol.setPrefWidth(200);

        TableColumn finishCol = new TableColumn("Finish");
        finishCol.setCellFactory(col -> new TableCell<PC, Void>(){
            private final Button finishButton = new Button("Finish");

            {
                finishButton.setOnAction((e) -> {
                    PC curr_PC = getTableView().getItems().get(getIndex());
                    FinishPC finishPC = FinishPC.getInstance();
                    finishPC.show();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty){
                super.updateItem(item, empty);
                setGraphic(empty ? null : finishButton);
            }
        });
        finishCol.setPrefWidth(200);


        TableColumn<PC, Void> cancelCol = new TableColumn("Cancel");
        cancelCol.setCellFactory(col -> new TableCell<PC, Void>() {
            private final Button cancelButton = new Button("Cancel");

            {
                cancelButton.setOnAction((e) -> {
                    PC curr_PC = getTableView().getItems().get(getIndex());
                    CancelPC cancelPC = CancelPC.getInstance();
                    cancelPC.show();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : cancelButton);
            }
        });
        cancelCol.setPrefWidth(200);



        table.getColumns().addAll(bookIDCol,pcIdCol, statusCol, finishCol, cancelCol);
        table.getItems().add(new PC(1, "Finished Booked"));
        table.getItems().add(new PC(2, "Not Booked"));
        table.getItems().add(new PC(3, "Finished Booked"));
        fp.getChildren().add(table);
    }
}
