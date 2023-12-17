package view;

import controller.PCBookController;
import helper.Helper;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.MainStage;
import model.PC;
import model.PCBook;
import view.Admin.pc.CancelPC;

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

    private TableView<PCBook> table;

    Button finishBtn, cancelBtn;

    public BookedPC(){
        bp = new BorderPane();
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);

        bp.setCenter(fp);

        titleInit();
        tableInit();
        setupButtons();
        backInit();

        scene = new Scene(bp, 1200, 600);
    }


    void setupButtons() {
        finishBtn = new Button("Finish");
        finishBtn.setOnMouseClicked(e -> {
            // Finish event handling
        });

        cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(e -> {
            PCBook selectedBook = table.getSelectionModel().getSelectedItem();
            if(selectedBook == null){
                Helper.showAlert(AlertType.ERROR, "Please select a pc to cancel its booking!");
                return;
            }

            PCBookController.cancelBook(selectedBook.getBook_id());
            _repaint();

        });

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(finishBtn, cancelBtn);
        buttonBox.setAlignment(Pos.CENTER);

        bp.setLeft(buttonBox);
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


    void tableInit(){
        TableView table = new TableView();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);


        TableColumn<PCBook, Integer> bookIDCol = new TableColumn("Book ID");
        bookIDCol.setCellValueFactory(new PropertyValueFactory<> ("book_id"));
        bookIDCol.setPrefWidth(200);

        TableColumn<PCBook, Integer> pcIdCol= new TableColumn("PC ID");
        pcIdCol.setCellValueFactory(new PropertyValueFactory("pc_id"));
        pcIdCol.setPrefWidth(200);

        TableColumn<PCBook, String> dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<> ("booked_date"));
        dateCol.setPrefWidth(200);

        //kok ada 5 column coy
        table.getColumns().addAll(bookIDCol,pcIdCol, dateCol);
        ObservableList<PCBook> bookedPC = PCBook.getAllBookedPCs();
        table.setItems(bookedPC);
        fp.getChildren().add(table);
    }

    void _repaint(){
        ObservableList<PCBook> bookedPCs = PCBook.getAllBookedPCs();
        table.setItems(bookedPCs);
    }
}
