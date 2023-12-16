package view.Admin.pc;

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
import view.customer.pc.BookPC;
import view.TemporaryMenu;

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

    public ViewPC(){
        bp = new BorderPane();
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);

        titleInit();
        tableInit();
        Bookinit();
        backInit();

        bp.setCenter(fp);

        scene = new Scene(bp, 1200, 600);

    }
    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = TemporaryMenu.getInstance();
            temp.show();
        });
        bp.setTop(back);
    }

    //kayaknya pindah scene bukan kek gini
    //deh hehe tpi ini langsung ngambil id pcnya juga jadi mungkin aja hehe
    void Bookinit(){
        Button book = new Button("Book");
        book.setOnMouseClicked(e -> {
            BookPC bookPC = BookPC.getInstance();
            bookPC.show();
        });
        bp.setTop(book);
    }

    private void bookPC(PC pcModel){
        BookPC bookPC = BookPC.getInstance();
        bookPC.show();
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
        TableView table = new TableView();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);
        TableColumn<PC, Integer> pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pcid"));
        pcidCol.setPrefWidth(200);
        TableColumn<PC, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(690);
        TableColumn<PC, Void> bookCol = new TableColumn<>("Book");
        bookCol.setPrefWidth(100);

        bookCol.setCellFactory(col -> new TableCell<PC, Void>() {
            private final Button bookButton = new Button("Book");

            {
                bookButton.setOnAction(e -> {
                    PC currentRowData = getTableView().getItems().get(getIndex());
                    bookPC(currentRowData);

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(bookButton);
                }
            }
        });


        //Fill data
        table.getColumns().addAll(pcidCol, statusCol, bookCol);
        table.getItems().add(new PC(1, "Broken"));
        table.getItems().add(new PC(2, "Usable"));
        table.getItems().add(new PC(3, "Maintenance"));
        fp.getChildren().add(table);
    }


}
