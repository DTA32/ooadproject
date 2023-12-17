package view.Admin.staff;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.MainStage;
import model.User;

public class ChangeRoleStaff {

    Scene scene;
    VBox vb;
    HBox hb;
    Label titleLbl, userNameLbl, ageLbl, currentRoleLbl;
    Button saveBtn, cancelBtn;
    ComboBox <String> roleList;
    User user;

    public ChangeRoleStaff(User user) {
        this.user = user;
        initialize();
        addEventListener();
    }

    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }

    private void initialize() {
        roleList = new ComboBox<String>();
        roleList.getItems().addAll("Admin", "Customer", "Operator", "Computer Technician"); // 0, 1, 2, 3
        roleList.getSelectionModel().select(user.getRole());

        hb = new HBox();
        hb.setSpacing(10);
        hb.setPadding(new Insets(10, 0, 0, 0));

        cancelBtn = new Button("Cancel");
        saveBtn = new Button("Save");
        hb.getChildren().addAll(cancelBtn, saveBtn);

        titleLbl = new Label("Change Role");
        titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        userNameLbl = new Label("User Name : " + user.getUserName());
        ageLbl = new Label("Age : " + user.getAge());
        currentRoleLbl = new Label("Current Role : " + user.getRole());

        vb = new VBox();
        vb.getChildren().addAll(titleLbl, userNameLbl, ageLbl, currentRoleLbl, roleList, hb);
        vb.setSpacing(10);


        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setPadding(new Insets(50));
        scene = new Scene(vb, 1200, 600);
    }

    private void addEventListener() {
        roleList.setOnAction(event -> {
            user.setRole(roleList.getSelectionModel().getSelectedItem());
        });

        saveBtn.setOnAction(event -> {
            if (UserController.changeRoleUser(user.getUserID(), user.getRole())){
                ViewAllStaffs viewAllStaffs = new  ViewAllStaffs();
                viewAllStaffs.show();
            }
        });

        cancelBtn.setOnAction(event -> {
            ViewAllStaffs viewAllStaffs = new  ViewAllStaffs();
            viewAllStaffs.show();
        });


    }

}
