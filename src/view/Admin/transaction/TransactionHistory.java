package view.Admin.transaction;

import java.sql.Date;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import main.MainStage;
import model.TransactionHistoryHeaderModel;
import model.TransactionHistoryDetailModel;
import view.TemporaryMenu;

public class TransactionHistory{
    private static TransactionHistory transactionHistory;
    public static TransactionHistory getInstance() {
        return transactionHistory = transactionHistory == null ? new TransactionHistory() : transactionHistory;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }
    Scene scene;
    BorderPane bp;
    FlowPane fp;


    public TransactionHistory(){
        bp = new BorderPane();
        fp = new FlowPane();

        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);

        bp.setCenter(fp);
        titleInit();
        tableInit();
        tableInit2();
        backInit();

        scene = new Scene(bp, 1500, 600);

    }

    void titleInit() {
        Label title = new Label("Transaction History");
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
    void tableInit() {
        Label title = new Label("Transaction Header");
        title.setFont(new Font("Arial", 16));
        title.setAlignment(Pos.TOP_LEFT);
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
        TableView table = new TableView();
        table.setEditable(true);
        table.setPrefWidth(750);
        table.setPrefHeight(200);
        TableColumn<TransactionHistoryHeaderModel, Integer> noCol = new TableColumn<>("NO");
        noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
        noCol.setPrefWidth(150);
        TableColumn<TransactionHistoryHeaderModel, Integer> transactionCol = new TableColumn<>("Transaction ID");
        transactionCol.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        transactionCol.setPrefWidth(150);
        TableColumn<TransactionHistoryHeaderModel, Integer> userCol = new TableColumn<>("User ID");
        userCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userCol.setPrefWidth(150);
        TableColumn<TransactionHistoryHeaderModel, Date> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setPrefWidth(150);
        TableColumn<TransactionHistoryHeaderModel, Integer> totalCol = new TableColumn<>("Total amount");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        totalCol.setPrefWidth(150);
        table.getColumns().addAll(noCol, transactionCol, userCol, dateCol, totalCol);
        table.getItems().add(new TransactionHistoryHeaderModel(1, 1, 13, new Date(System.currentTimeMillis()), 50000));
        table.getItems().add(new TransactionHistoryHeaderModel(2, 2, 27, new Date(System.currentTimeMillis()), 80000));
        table.getItems().add(new TransactionHistoryHeaderModel(3, 3, 43, new Date(System.currentTimeMillis()), 25000));
        fp.getChildren().add(table);
    }
    
    void tableInit2() {
        Label title = new Label("Transaction Detail");
        title.setFont(new Font("Arial", 16));
        title.setAlignment(Pos.TOP_LEFT);
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
        TableView table2 = new TableView();
        table2.setEditable(true);
        table2.setPrefWidth(750);
        table2.setPrefHeight(200);
        TableColumn<TransactionHistoryDetailModel, Integer> noCol = new TableColumn<>("No");
        noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
        noCol.setPrefWidth(150);
        TableColumn<TransactionHistoryDetailModel, Integer> transactionDetailCol = new TableColumn<>("Transaction Detail ID");
        transactionDetailCol.setCellValueFactory(new PropertyValueFactory<>("transactionDetailID"));
        transactionDetailCol.setPrefWidth(150);
        TableColumn<TransactionHistoryDetailModel, Integer> transactionCol = new TableColumn<>("Transaction ID");
        transactionCol.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        transactionCol.setPrefWidth(150);
        TableColumn<TransactionHistoryDetailModel, Integer	> userCol = new TableColumn<>("User ID");
        userCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userCol.setPrefWidth(150);
        TableColumn<TransactionHistoryDetailModel, Integer> pcCol = new TableColumn<>("PC Booked");
        pcCol.setCellValueFactory(new PropertyValueFactory<>("pcID"));
        pcCol.setPrefWidth(150);
        table2.getColumns().addAll(noCol, transactionDetailCol, transactionCol, userCol, pcCol);
        table2.getItems().add(new TransactionHistoryDetailModel(1, 1, 1, 1, 5));
        table2.getItems().add(new TransactionHistoryDetailModel(2, 2, 2, 5, 7));
        table2.getItems().add(new TransactionHistoryDetailModel(3, 3, 3, 7, 10));
        fp.getChildren().add(table2);
    }

}
