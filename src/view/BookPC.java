package view;

import javafx.geometry.Orientation;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import main.MainStage;

public class BookPC {
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
        dateInit();
        pcidInit();
        fp.getChildren().add(vbFields);
        bookInit();

        bpOuter.setCenter(fp);

        scene = new Scene(bpOuter, 1200, 600);
        setupEventHandling();
    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = new TemporaryMenu();
            MainStage.stage.setScene(temp.getScene());
        });
        bpOuter.setTop(back);
    }

    public Scene getScene(){
        return scene;
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

    void setupEventHandling(){
        book.setOnMouseClicked((e) ->  {
            String date = "";
            String pcid = pcidField.getText();
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Error");
            if(dateField.getValue() == null){
                err.setHeaderText("Date cannot be empty!");
                err.showAndWait();
            } else{
                date = dateField.getValue().toString();
                // ini harusnya di controller
                if(pcid.isBlank()) {
                    err.setHeaderText("PC ID cannot be empty!");
                    err.showAndWait();
                }
                else{
                    // lagi lagi, ini cuma iseng
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Book PC");
                    alert.setHeaderText(null);
                    alert.setContentText("PC Booked!");
                    alert.showAndWait();
                }
            }
        });
    }
}
