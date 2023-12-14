package view.computer_technician;

import controller.UserController;
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
import model.User;
import view.Admin.ChangeRoleStaff;
import view.TemporaryMenu;

import java.util.ArrayList;

public class viewTechnicianJob {

    Scene scene;
    BorderPane bp = new BorderPane();
    VBox vb;
    Label titleLbl;
    TableView<User> tableView;
    TableColumn<User, String> userNameColumn, roleColumn;
    TableColumn <User, Void> actionColumn;

//    public viewTechnicianJob() {
//        getAllPC();
//        topInit();
//        initialize();
//    }
//
//    public ArrayList<User> getAllPC(){
//        return UserController.getAllPCData();
//    }
//
//    public Scene getScene() {
//        return scene;
//    }
//
//    void topInit(){
//        VBox topContainer = new VBox();
//        Button back = new Button("< Back");
//        back.setOnMouseClicked(e -> {
//            TemporaryMenu temp = new TemporaryMenu();
//            MainStage.stage.setScene(temp.getScene());
//        });
//        topContainer.getChildren().addAll(back);
//        bp.setTop(topContainer);
//    }
//
//    private void initialize() {
//        titleLbl = new Label("View Technician Job");
//        titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        tableView = new TableView<>();
//
//        userNameColumn = new TableColumn<>("PC ID");
//        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));
//        userNameColumn.setPrefWidth(200);
//
//        roleColumn = new TableColumn<>("PC Status");
//        roleColumn.setCellValueFactory(new PropertyValueFactory<>("Role"));
//        roleColumn.setPrefWidth(200);
//
//        actionColumn = new TableColumn<>("Action");
//        actionColumn.setPrefWidth(350);
//        actionColumn.setCellFactory(param -> new TableCell<>() {
//            private final Button editBtn = new Button("Change Status");
//
//            {
//                editBtn.setOnAction(event -> {
//                    User user = getTableView().getItems().get(getIndex());
//                    ChangeRoleStaff changeRole = new ChangeRoleStaff(user);;
//                    MainStage.stage.setScene(changeRole.getScene());
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
//
//        tableView.getColumns().addAll(userNameColumn, roleColumn, actionColumn);
//        tableView.setPlaceholder(new Label("No Staffs Found"));
//
//        ArrayList <User> userList = getAllStaffs();
//        tableView.getItems().addAll(userList);
//
//        vb = new VBox(10);
//        vb.getChildren().addAll(titleLbl, tableView);
//        vb.setAlignment(Pos.CENTER_LEFT);
//        vb.setPadding(new Insets(50));
//
//        scene = new Scene(vb, 1200, 600);
//    }


}
