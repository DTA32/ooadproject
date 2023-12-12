package view;

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
import model.ReportModel;
import model.ViewPCModel;

public class ViewPC {

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

    public Scene getScene(){
        return scene;
    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = new TemporaryMenu();
            MainStage.stage.setScene(temp.getScene());
        });
        bp.setTop(back);
    }

    //kayaknya pindah scene bukan kek gini
    //deh hehe tpi ini langsung ngambil id pcnya juga jadi mungkin aja hehe
    void Bookinit(){
        Button book = new Button("Book");
        book.setOnMouseClicked(e -> {
            BookPC bookPC = new BookPC();
            MainStage.stage.setScene(book.getScene());
        });
        bp.setTop(book);
    }

    private void bookPC(ViewPCModel pcModel){
        BookPC bookPC = new BookPC();
        MainStage.stage.setScene(bookPC.getScene());
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
        TableColumn<ViewPCModel, Integer> pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pcid"));
        pcidCol.setPrefWidth(200);
        TableColumn<ViewPCModel, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(690);
        TableColumn<ViewPCModel, Void> bookCol = new TableColumn<>("Book");
        bookCol.setPrefWidth(100);

        bookCol.setCellFactory(col -> new TableCell<ViewPCModel, Void>() {
            private final Button bookButton = new Button("Book");

            {
                bookButton.setOnAction(e -> {
                    ViewPCModel currentRowData = getTableView().getItems().get(getIndex());
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
        table.getItems().add(new ViewPCModel(1, "Broken"));
        table.getItems().add(new ViewPCModel(2, "Usable"));
        table.getItems().add(new ViewPCModel(3, "Maintenance"));
        fp.getChildren().add(table);
    }


}
