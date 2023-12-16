package view.computer_technician;

import controller.PCController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;
import model.PC;
import view.Admin.ChangeRoleStaff;
import view.TemporaryMenu;

import java.util.ArrayList;

public class ViewTechnicianJob {

    Scene scene;
    BorderPane bp = new BorderPane();
    VBox vb;
    Label titleLbl;
    TableView<PC> tableView;
    TableColumn<PC, String> pcIDColumn, pcStatusColumn;
//    TableColumn <PC, Void> actionColumn;
//    ComboBox <String> pcStatusList;

    public ViewTechnicianJob() {
        topInit();
        initialize();
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }


    public ArrayList<PC> getAllPCData(){
        return PCController.getAllPCData();
    }

    public Scene getScene() {
        return scene;
    }

    void topInit(){
        VBox topContainer = new VBox();
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = new TemporaryMenu();
            temp.show();
        });
        topContainer.getChildren().addAll(back);
        bp.setTop(topContainer);
    }

    private void initialize() {
        titleLbl = new Label("View Technician Job");
        titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        tableView = new TableView<>();

        pcIDColumn = new TableColumn<>("PC ID");
        pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcid"));
        pcIDColumn.setMinWidth(200);

        pcStatusColumn = new TableColumn<>("PC Status");
        pcStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        pcStatusColumn.setPrefWidth(200);

//        actionColumn = new TableColumn<>("Action");
//        actionColumn.setPrefWidth(350);
//        actionColumn.setCellFactory(param -> new TableCell<>() {
//            private final Button editBtn = new Button("Change Status");
//
//            {
//                editBtn.setOnAction(event -> {
//                    pcStatusList = new ComboBox<String>();
//                    pcStatusList.getItems().addAll("Admin", "Customer", "Operator", "Computer Technician"); // 0, 1, 2, 3
//                    pcStatusList.getSelectionModel().select(user.getRole());
////                    PC pc = getTableView().getItems().get(getIndex());
////                    ChangeRoleStaff changeRole = new ChangeRoleStaff(pc);;
////                    MainStage.stage.setScene(changeRole.getScene());
//                });
//            }
//
//            @Override
//            protected void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//                if (!empty){
//                    HBox hb = new HBox(editBtn);
//                    setGraphic(hb);
//                }
//                else{
//                    setGraphic(null);
//                }
//            }
//        });

        tableView.getColumns().addAll(pcIDColumn, pcStatusColumn);
        tableView.setPlaceholder(new Label("No Staffs Found"));

        ArrayList <PC> userList = getAllPCData();
        tableView.getItems().addAll(userList);

        vb = new VBox(10);
        vb.getChildren().addAll(titleLbl, tableView);
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setPadding(new Insets(50));

        scene = new Scene(vb, 1200, 600);
    }


}
