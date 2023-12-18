package view.customer.transaction;

import java.util.ArrayList;

import controller.TransactionController;
import helper.Helper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;
import model.TransactionHistoryDetailModel;
import view.customer.menu.CustomerMenu;

public class CustomerTransactionHistory {
    private static CustomerTransactionHistory customerTransactionHistory;
    public static CustomerTransactionHistory getInstance() {
        return customerTransactionHistory = customerTransactionHistory == null ? new CustomerTransactionHistory() : customerTransactionHistory;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }
    Scene scene;
    VBox customerTransactionHistoryVb, titleVb, backVb;
    Label title;
    TableView <TransactionHistoryDetailModel> table;
    TableColumn<TransactionHistoryDetailModel, String> dateCol;
    TableColumn<TransactionHistoryDetailModel, Integer> transactionIdCol, pcidCol, userCol;

    public CustomerTransactionHistory(){
        customerTransactionHistoryVb = new VBox();
        backInit();
        titleInit();
        tableInit();
    }

    ArrayList <TransactionHistoryDetailModel> getUserTransactionDetail(int user_id){
    	return TransactionController.getUserTransactionDetail(user_id);
    }


    void titleInit() {
        title = new Label("CUSTOMER TRANSACTION HISTORY");
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        titleVb = new VBox();
        titleVb.getChildren().add(title);
        titleVb.setAlignment(Pos.CENTER);
        customerTransactionHistoryVb.getChildren().add(titleVb);
    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            CustomerMenu customerMenu = CustomerMenu.getInstance();
            customerMenu.show();
        });
        backVb = new VBox();
        backVb.getChildren().add(back);
        backVb.setAlignment(Pos.CENTER_LEFT);
        customerTransactionHistoryVb.getChildren().add(backVb);
    }

    void tableInit() {
        table = new TableView<>();

        transactionIdCol = new TableColumn<>("Transaction ID");
        transactionIdCol.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        transactionIdCol.setMinWidth(250);

        userCol = new TableColumn<>("User ID");
        userCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userCol.setMinWidth(250);

        pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pcID"));
        pcidCol.setMinWidth(250);

        dateCol = new TableColumn<>("Booked Time");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("transactionDetailID"));
        dateCol.setMinWidth(250);

        table.getColumns().addAll(transactionIdCol, userCol, pcidCol, dateCol);

        int uuser_id = Integer.parseInt(Helper.getCurrentUser().getUserID());
        ArrayList <TransactionHistoryDetailModel> transactionHistoryDetailModels = getUserTransactionDetail(uuser_id);
        table.getItems().addAll(transactionHistoryDetailModels);
        table.setPlaceholder(new Label("No Transaction Found"));

        customerTransactionHistoryVb.getChildren().add(table);
        customerTransactionHistoryVb.setAlignment(Pos.CENTER);
        customerTransactionHistoryVb.setPadding(new Insets(64));
        customerTransactionHistoryVb.setSpacing(32);

        scene = new Scene(customerTransactionHistoryVb, 1200, 600);
    }


}
