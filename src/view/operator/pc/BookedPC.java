package view.operator.pc;

import controller.PCBookController;
import controller.TransactionController;
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
import model.PCBook;
import view.operator.menu.OperatorMenu;

import java.util.Date;
import java.util.List;

public class BookedPC {
    private static BookedPC bookedPC;
    public static BookedPC getInstance() {
        return bookedPC = bookedPC == null ? new BookedPC() : bookedPC;
    }
    public void show(){
        _repaint();
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
            List<PCBook> selectedBooks = PCBookController.FinishBook(table.getSelectionModel().getSelectedItems());
            Date now = new Date();
            for(PCBook book : selectedBooks){
                if(book.getBooked_date().before(now)){
                    PCBookController.DeleteBookData(book.getBook_id());
                }
                else {
                    Helper.showAlert(Alert.AlertType.ERROR, "Cannot finish booking before the booked date!");
                }
            }
            Helper.showAlert(Alert.AlertType.INFORMATION, "Booked PC finished!");
            _repaint();
        });

        cancelBtn = new Button("Cancel");
        cancelBtn.setOnMouseClicked(e -> {
            PCBook selectedBook = table.getSelectionModel().getSelectedItem();
            if(selectedBook == null){
                Helper.showAlert(Alert.AlertType.ERROR, "Please select a pc to cancel its booking!");
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
            OperatorMenu operatorMenu = OperatorMenu.getInstance();
            operatorMenu.show();
        });
        bp.setTop(back);
    }


    void tableInit(){
        table = new TableView();
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

        table.getColumns().addAll(bookIDCol,pcIdCol, dateCol);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        fp.getChildren().add(table);
    }

    void _repaint(){
        ObservableList<PCBook> bookedPCs = PCBook.getAllBookedPCs();
        table.setItems(bookedPCs);
    }
}
