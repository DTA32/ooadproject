import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

import java.util.Date;

public class BookPC extends Application {
    Scene scene;
    BorderPane bpOuter;
    FlowPane fp;
    VBox vbFields;
    Label errorLbl;
    DatePicker dateField;
    TextField pcidField;
    @Override
    public void start(Stage primaryStage) throws Exception {
        bpOuter = new BorderPane();
        fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.CENTER);
        fp.setVgap(48);
        vbFields = new VBox();
        vbFields.setAlignment(Pos.CENTER);
        vbFields.setSpacing(16);

        titleInit();
        dateInit();
        pcidInit();
        errorInit();
        fp.getChildren().add(vbFields);
        bookInit();

        bpOuter.setCenter(fp);

        scene = new Scene(bpOuter, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
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
    void errorInit(){
        errorLbl = new Label("");
        errorLbl.setTextFill(Color.RED);
        vbFields.getChildren().add(errorLbl);
    }

    void bookInit(){
        Button book = new Button("Book");
        book.setPrefWidth(400);
        fp.getChildren().add(book);
        book.setOnMouseClicked(bookClick());
    }

    private EventHandler<MouseEvent> bookClick(){
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                String date = "";
                String pcid = pcidField.getText();
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setTitle("Error");
                if(dateField.getValue() == null){
//                    errorLbl.setText("Date cannot be empty!");
                    err.setHeaderText("Date cannot be empty!");
                    err.showAndWait();
                } else{
                    date = dateField.getValue().toString();
                }
                // ini harusnya di controller
                if(pcid.isBlank()) {
//                    errorLbl.setText("PC ID cannot be empty!");
                    err.setHeaderText("PC ID cannot be empty!");
                    err.showAndWait();
                }
                else{
                    errorLbl.setText("");
                    // lagi lagi, ini cuma iseng
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Book PC");
                    alert.setHeaderText(null);
                    alert.setContentText("PC Booked!");
                    alert.showAndWait();
                }
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
