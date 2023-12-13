import java.sql.Date;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.CustomerTransactionHistoryModel;

public class CustomerTransactionHistory extends Application {
    Scene scene;
    BorderPane bp;
    FlowPane fp;

    @Override
    public void start(Stage primaryStage) {
        bp = new BorderPane();
        fp = new FlowPane();

        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.TOP_CENTER);
        fp.setVgap(16);
        bp.setCenter(fp);
        titleInit();
        tableInit();

        scene = new Scene(bp, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void titleInit() {
        Label title = new Label("Customer Transaction History");
        title.setFont(new Font("Arial", 24));
        title.setAlignment(Pos.CENTER);
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
    }

    void tableInit() {
        TableView table = new TableView<>();
        table.setEditable(true);
        table.setPrefWidth(1000);
        table.setPrefHeight(500);
        TableColumn<CustomerTransactionHistoryModel, Integer> noCol = new TableColumn<>("NO");
        noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
        noCol.setPrefWidth(200);
        TableColumn<CustomerTransactionHistoryModel, String> userCol = new TableColumn<>("User ID");
        userCol.setCellValueFactory(new PropertyValueFactory<>("userid"));
        userCol.setPrefWidth(400);
        TableColumn<CustomerTransactionHistoryModel, String> pcidCol = new TableColumn<>("PC ID");
        pcidCol.setCellValueFactory(new PropertyValueFactory<>("pcid"));
        pcidCol.setPrefWidth(200);
        TableColumn<CustomerTransactionHistoryModel, Date> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setPrefWidth(200);
        table.getColumns().addAll(noCol, userCol, pcidCol, dateCol);
        table.getItems()
                .add(new CustomerTransactionHistoryModel(1, 1, 121, new Date(System.currentTimeMillis())));
        table.getItems()
                .add(new CustomerTransactionHistoryModel(2, 2, 122, new Date(System.currentTimeMillis())));
        table.getItems()
                .add(new CustomerTransactionHistoryModel(3, 3, 123, new Date(System.currentTimeMillis())));
        fp.getChildren().add(table);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
