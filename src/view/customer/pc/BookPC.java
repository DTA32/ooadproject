package view.customer.pc;

import controller.PCBookController;
import javafx.geometry.Orientation;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import main.MainStage;
import model.PC;

import java.time.LocalDate;

import model.PCBook;
import view.TemporaryMenu;
import view.customer.menu.CustomerMenu;

public class BookPC {
    private static BookPC bookPC;
    public static BookPC getInstance() {
        return bookPC = bookPC == null ? new BookPC() : bookPC;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }
    Scene scene;
    BorderPane bpOuter;
    FlowPane fp;
    VBox vbFields;
    DatePicker dateField;
    TextField pcidField;
    Button book;

    public BookPC() {
        bpOuter = new BorderPane();
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.CENTER);
        fp.setVgap(48);
        vbFields = new VBox();
        vbFields.setAlignment(Pos.CENTER);
        vbFields.setSpacing(16);

        backInit();

        titleInit();
        pcidInit();
        dateInit();
        fp.getChildren().add(vbFields);
        bookInit();

        bpOuter.setCenter(fp);

        scene = new Scene(bpOuter, 1200, 600);
        setupEventHandling();
    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            CustomerMenu customerMenu = CustomerMenu.getInstance();
            customerMenu.show();
        });
        bpOuter.setTop(back);
    }



    void titleInit(){
        Label title = new Label("Book PC");
        title.setFont(new Font("Arial", 24));
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
    }

    void dateInit(){
        Label dateLbl = new Label("Date");
        dateField = new DatePicker();
        dateField.setPrefWidth(400);
        VBox dateContainer = new VBox();
        dateContainer.getChildren().add(dateLbl);
        dateContainer.getChildren().add(dateField);
        vbFields.getChildren().add(dateContainer);
    }

    void pcidInit(){
        Label pcidLbl = new Label("PC ID");
        pcidField = new TextField();
        VBox pcidContainer = new VBox();
        pcidContainer.getChildren().add(pcidLbl);
        pcidContainer.getChildren().add(pcidField);
        vbFields.getChildren().add(pcidContainer);
    }

    void bookInit(){
        book = new Button("Book");
        book.setPrefWidth(400);
        fp.getChildren().add(book);
    }

    public void setPcID(Integer pcID){
        if(pcidField != null && pcID != null){
            pcidField.setText(pcID.toString());
            pcidField.setDisable(true);
        }
    }

    void _repaint(){
        //Nanti perlu logic buat ambil user yang lagi login
        dateField.setValue(null);

    }

    void setupEventHandling(){
        //Nanti perlu logic buat ambil user yang lagi login
        book.setOnMouseClicked(e -> {
            LocalDate booked_date = dateField.getValue();
            String pcidText = pcidField.getText();
            try {
                Integer pcid = Integer.parseInt(pcidText);
                PCBookController.addNewBook(pcid, booked_date);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            _repaint();
        });

    }
}
