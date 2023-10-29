import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class BookPC extends Application {
    Scene scene;
    BorderPane bpOuter;
    FlowPane fp;
    VBox vbFields;
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
        DatePicker dateField = new DatePicker();
        dateField.setPrefWidth(400);
        VBox dateContainer = new VBox();
        dateContainer.getChildren().add(dateLbl);
        dateContainer.getChildren().add(dateField);
        vbFields.getChildren().add(dateContainer);
    }

    void pcidInit(){
        Label pcidLbl = new Label("PC ID");
        TextField pcidField = new TextField();
        VBox pcidContainer = new VBox();
        pcidContainer.getChildren().add(pcidLbl);
        pcidContainer.getChildren().add(pcidField);
        vbFields.getChildren().add(pcidContainer);
    }

    void bookInit(){
        Button book = new Button("Book");
        book.setPrefWidth(400);
        fp.getChildren().add(book);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
