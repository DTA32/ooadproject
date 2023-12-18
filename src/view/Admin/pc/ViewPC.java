package view.Admin.pc;

import helper.Helper;
import javafx.collections.ObservableList;
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
import model.PC;
import view.Admin.menu.AdminMenu;
import view.computer_technician.menu.ComputerTechnician;
import view.customer.menu.CustomerMenu;
import view.customer.pc.BookPC;
import view.TemporaryMenu;
import view.operator.menu.OperatorMenu;

public class ViewPC {
    private static ViewPC viewPC;
    public static ViewPC getInstance() {
        return viewPC = viewPC == null ? new ViewPC() : viewPC;
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

    public ViewPC(){
        bp = new BorderPane();
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);

        titleInit();
        tableInit();
//        Bookinit();
        backInit();

        bp.setCenter(fp);

        scene = new Scene(bp, 1200, 600);

    }
    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            if(Helper.getCurrentUser().getRole().equalsIgnoreCase("admin")){
                AdminMenu.getInstance().show();
            } else if (Helper.getCurrentUser().getRole().equalsIgnoreCase("customer")){
                CustomerMenu.getInstance().show();
            } else if (Helper.getCurrentUser().getRole().equalsIgnoreCase("operator")){
                OperatorMenu.getInstance().show();
            } else if (Helper.getCurrentUser().getRole().equalsIgnoreCase("technician")){
                ComputerTechnician.getInstance().show();
            } else {
                TemporaryMenu.getInstance().show();
            }
        });
        bp.setTop(back);
    }



    //kayaknya pindah scene bukan kek gini
    //deh hehe tpi ini langsung ngambil id pcnya juga jadi mungkin aja hehe

    private void bookPC(Integer pcId){
        BookPC bookPC = BookPC.getInstance();
        bookPC.setPcID(pcId);
        bookPC.show();
    }

    void _repaint(){
        table.getItems().clear();
//        table.getItems().addAll(PC.getAllPC());
    }

    void titleInit(){
        Label title = new Label("List of All PCs");
        title.setFont(new Font("Arial", 24));
        title.setAlignment(Pos.CENTER);
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
    }

    void tableInit(){
        TableView<PC> table = new TableView<>();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);

        pcId = new TableColumn("PC ID");
        pcId.setCellValueFactory(new PropertyValueFactory<>("pcid"));
        pcId.setPrefWidth(200);

        Status = new TableColumn<>("Status");
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Status.setPrefWidth(690);

        TableColumn<PC, Void> bookCol = new TableColumn<>("Book");
        bookCol.setPrefWidth(100);
        bookCol.setCellFactory(col -> new TableCell<PC, Void>(){
                private final Button bookBtn = new Button("Book");
                {
                    bookBtn.setOnAction(e -> {
                        PC currentRowData = getTableView().getItems().get(getIndex());
                        bookPC(currentRowData.getPcid());
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                    }else{
                        setGraphic(bookBtn);
                    }
                }
            });

        if(!Helper.getCurrentUser().getRole().equalsIgnoreCase("customer")){
            bookCol.setVisible(false);
        }

        //Fill data
        table.getColumns().addAll(pcId, Status, bookCol);
        refreshTable(table);
        fp.getChildren().add(table);
    }



    private void refreshTable(TableView<PC> table){
        ObservableList<PC> pcsList = PC.getallPC();
        table.setItems(pcsList);
    }

}
