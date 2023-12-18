package view.Admin.staff;

import controller.UserController;
import helper.Helper;
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
import view.Admin.menu.AdminMenu;

import java.util.ArrayList;

public class ViewAllStaffs {

    Scene scene;
    BorderPane bp = new BorderPane();
    VBox viewAllStaffVb, titleVb;
    Label titleLbl;
    TableView <User> tableView;
    TableColumn <User, String> userNameColumn, roleColumn;
    TableColumn <User, Void> actionColumn;

    public ViewAllStaffs() {
        topInit();
        initialize();
    }
    private static ViewAllStaffs viewAllStaffs;
    public static ViewAllStaffs getInstance() {
        return viewAllStaffs = viewAllStaffs == null ? new ViewAllStaffs() : viewAllStaffs;
    }


    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    public ArrayList<User> getAllStaffs(){
        return UserController.getAllUserData();
    }

    public Scene getScene() {
        return scene;
    }

    void topInit(){
        VBox topContainer = new VBox();
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            AdminMenu adminMenu = AdminMenu.getInstance();
            adminMenu.show();
        });
        topContainer.getChildren().addAll(back);
        bp.setTop(topContainer);
    }

    private void initialize() {
        titleLbl = new Label("VIEW ALL STAFFS");
        titleLbl.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        titleVb = new VBox(10);
        titleVb.getChildren().addAll(titleLbl);
        titleVb.setAlignment(Pos.CENTER);
        titleVb.setMaxWidth(500);

        tableView = new TableView<>();

        userNameColumn = new TableColumn<>("Username");
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        userNameColumn.setMinWidth(350);

        roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("Role"));
        roleColumn.setMinWidth(350);

        actionColumn = new TableColumn<>("Action");
        actionColumn.setMinWidth(350);
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button("Change Role");

            {
                editBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    Helper.setTempUser(user);
                    ChangeRoleStaff changeRole = new ChangeRoleStaff();;
                   changeRole.show();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty){
                    HBox hb = new HBox(editBtn);
                    setGraphic(hb);
                }
                else{
                    setGraphic(null);
                }
            }
        });

        tableView.getColumns().addAll(userNameColumn, roleColumn, actionColumn);
        tableView.setPlaceholder(new Label("No Staffs Found"));

        ArrayList <User> userList = getAllStaffs();
        tableView.getItems().addAll(userList);

        viewAllStaffVb = new VBox(10);
        viewAllStaffVb.getChildren().addAll(titleLbl, tableView);
        viewAllStaffVb.setAlignment(Pos.CENTER);
        viewAllStaffVb.setPadding(new Insets(64));
        viewAllStaffVb.setSpacing(32);

        bp.setCenter(viewAllStaffVb);

        scene = new Scene(bp, 1200, 600);
    }

}
