package view.Admin.transaction;

import java.sql.Date;
import java.util.ArrayList;

import helper.Helper;
import javafx.geometry.Insets;
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
import model.User;
import view.Admin.menu.AdminMenu;
import view.Admin.staff.ChangeRoleStaff;
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
    Label title;
    TableView <TransactionHistoryHeaderModel> table;
    TableColumn<TransactionHistoryHeaderModel, Integer>  transactionIDCol, userIDCol;
    TableColumn<TransactionHistoryHeaderModel, Integer> userNameCol, transactionDetailCol;
    TableColumn<TransactionHistoryHeaderModel, Void> actionColumn;
    VBox transactionHistoryVb, titleContainer, backVb;


    public TransactionHistory(){
        transactionHistoryVb = new VBox(10);
        backInit();
        titleInit();
        tableInit();
    }

    void titleInit() {
        title = new Label("TRANSACTION HEADER");
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
            AdminMenu adminMenu = AdminMenu.getInstance();
            adminMenu.show();
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
        transactionIDCol.setMinWidth(200);

        userIDCol = new TableColumn<>("User ID");
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("staff_id"));
        userIDCol.setMinWidth(200);

        userNameCol = new TableColumn<>("User Name");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("staff_name"));
        userNameCol.setMinWidth(200);

        transactionDetailCol = new TableColumn<>("Transaction Date");
        transactionDetailCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        transactionDetailCol.setMinWidth(200);

        actionColumn = new TableColumn<>("Action");
        actionColumn.setMinWidth(200);
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button seeDetailBtn = new Button("See Detail");

            {
                seeDetailBtn.setOnAction(event -> {
                    TransactionHistoryHeaderModel th = getTableView().getItems().get(getIndex());
                    Helper.setTransactionHistoryHeaderModel(th);
                    TransactionDetail transactionDetail = TransactionDetail.getInstance();
                    transactionDetail.show();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty){
                    HBox hb = new HBox(seeDetailBtn);
                    setGraphic(hb);
                }
                else{
                    setGraphic(null);
                }
            }
        });

        ArrayList<TransactionHistoryHeaderModel> transactionHistoryHeaderModels = TransactionHistoryHeaderModel.getAllTransactionHeaderData();

        table.getColumns().addAll(transactionIDCol, userIDCol, userNameCol, transactionDetailCol, actionColumn);

        table.getItems().addAll(transactionHistoryHeaderModels);

        transactionHistoryVb.getChildren().add(table);
        transactionHistoryVb.setAlignment(Pos.CENTER);
        transactionHistoryVb.setPadding(new Insets(64));
        transactionHistoryVb.setSpacing(32);

        scene = new Scene(transactionHistoryVb, 1200, 600);
    }

}
