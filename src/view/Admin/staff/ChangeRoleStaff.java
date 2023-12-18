package view.Admin.staff;

import controller.UserController;
import helper.Helper;
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
    VBox changeRoleVb, titleVb, usernameVb, ageVb, currentRoleVb, formVb;
    HBox hb, hb2;
    Label titleLbl, userNameLbl, ageLbl, currentRoleLbl, changeRoleLbl;
    Button saveBtn, cancelBtn;
    ComboBox <String> roleList;
    User user;

    public ChangeRoleStaff() {
//        this.user = user;
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
        user = Helper.getTempUser();

        changeRoleLbl = new Label("Change Role");
        changeRoleLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        roleList = new ComboBox<String>();
        roleList.getItems().addAll(  "admin", "operator", "technician");
        roleList.getSelectionModel().select(user.getRole());

        hb2 = new HBox();
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(changeRoleLbl, roleList);
        hb2.setPadding(new Insets(16));
        hb2.setSpacing(16);

        hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);

        cancelBtn = new Button("Cancel");
        saveBtn = new Button("Save");
        hb.getChildren().addAll(cancelBtn, saveBtn);

        titleLbl = new Label("CHANGE ROLE");
        titleLbl.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        titleVb = new VBox();
        titleVb.getChildren().addAll(titleLbl);
        titleVb.setAlignment(Pos.CENTER);
        titleVb.setMaxWidth(500);

        userNameLbl = new Label("User Name : " + user.getUserName());
        userNameLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        usernameVb = new VBox();
        usernameVb.getChildren().addAll(userNameLbl);
        usernameVb.setAlignment(Pos.CENTER);
        usernameVb.setSpacing(16);
        usernameVb.setMaxWidth(500);

        ageLbl = new Label("Age : " + user.getAge());
        ageLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        ageVb = new VBox();
        ageVb.setAlignment(Pos.CENTER);
        ageVb.getChildren().addAll(ageLbl);
        ageVb.setSpacing(16);
        ageVb.setMaxWidth(500);

        currentRoleLbl = new Label("Current Role : " + user.getRole());
        currentRoleLbl.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

        currentRoleVb = new VBox();
        currentRoleVb.setAlignment(Pos.CENTER);
        currentRoleVb.getChildren().addAll(currentRoleLbl);
        currentRoleVb.setSpacing(16);
        currentRoleVb.setMaxWidth(500);

        formVb = new VBox();
        formVb.getChildren().addAll(usernameVb, ageVb, currentRoleVb, hb2);
        formVb.setAlignment(Pos.CENTER);
        formVb.setSpacing(16);
        formVb.setMaxWidth(500);

        changeRoleVb = new VBox();
        changeRoleVb.getChildren().addAll(titleVb,formVb, hb);
        changeRoleVb.setAlignment(Pos.CENTER);
        changeRoleVb.setPadding(new Insets(64));
        changeRoleVb.setSpacing(32);


        scene = new Scene(changeRoleVb, 1200, 600);
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
