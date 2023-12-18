package view.Admin.transaction;

import controller.TransactionController;
import helper.Helper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.MainStage;
import model.TransactionHistoryDetailModel;
import model.TransactionHistoryHeaderModel;
import view.Admin.menu.AdminMenu;

import java.util.ArrayList;

public class TransactionDetail {

    private static TransactionDetail transactionDetail;
    public static TransactionDetail getInstance() {
        return transactionDetail = transactionDetail == null ? new TransactionDetail() : transactionDetail;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }
    Scene scene;
    Label title;
    TableView<TransactionHistoryDetailModel> table;
    TableColumn<TransactionHistoryDetailModel, Integer> transactionIDCol, userIDCol, pcIDCol;
    TableColumn<TransactionHistoryDetailModel, Integer> bookedTimeCol;
    VBox transactionHistoryVb, titleContainer, backVb;


    public TransactionDetail(){
        transactionHistoryVb = new VBox(10);
        backInit();
        titleInit();
        tableInit();
    }

    void titleInit() {
        title = new Label("TRANSACTION DETAIL");
        title.setFont(new Font("Arial", 24));
        title.setAlignment(Pos.CENTER);

        titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);

        transactionHistoryVb.getChildren().add(titleContainer);
    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TransactionHistory history = TransactionHistory.getInstance();
            history.show();
        });

        backVb = new VBox();
        backVb.getChildren().add(back);
        backVb.setAlignment(Pos.CENTER_LEFT);
        transactionHistoryVb.getChildren().add(backVb);
    }

    void tableInit() {
        table = new TableView();

        transactionIDCol = new TableColumn<>("Transaction ID");
        transactionIDCol.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        transactionIDCol.setMinWidth(250);

        userIDCol = new TableColumn<>("User ID");
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userIDCol.setMinWidth(250);

        pcIDCol = new TableColumn<>("PC ID");
        pcIDCol.setCellValueFactory(new PropertyValueFactory<>("pcID"));
        pcIDCol.setMinWidth(250);

        bookedTimeCol = new TableColumn<>("Transaction Date");
        bookedTimeCol.setCellValueFactory(new PropertyValueFactory<>("transactionDetailID"));
        bookedTimeCol.setMinWidth(250);

        ArrayList<TransactionHistoryDetailModel> transactions = TransactionController.getAllTransactionDetail(Helper.getTransactionHistoryHeaderModel().getTransactionID());

        table.getColumns().addAll(transactionIDCol, userIDCol, pcIDCol, bookedTimeCol);
        table.getItems().addAll(transactions);

        transactionHistoryVb.getChildren().add(table);
        transactionHistoryVb.setAlignment(Pos.CENTER);
        transactionHistoryVb.setPadding(new Insets(64));
        transactionHistoryVb.setSpacing(32);

        scene = new Scene(transactionHistoryVb, 1200, 600);
    }

}
