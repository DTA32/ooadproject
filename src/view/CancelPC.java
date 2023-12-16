package view;

import helper.Helper;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Alert.AlertType;
import main.MainStage;

import java.util.Calendar;
import java.util.Date;

public class CancelPC {
    private static CancelPC cancelPC;
    public static CancelPC getInstance() {
        return cancelPC = cancelPC == null ? new CancelPC() : cancelPC;
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

    Button cancel;


    public CancelPC(){
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
        cancelInit();
        fp.getChildren().add(vbFields);

        bpOuter.setCenter(fp);

        scene = new Scene(bpOuter, 1200, 600);
    }

    void backInit(){
        Button back = new Button("< Back");
        back.setOnMouseClicked(e -> {
            TemporaryMenu temp = TemporaryMenu.getInstance();
            temp.show();
        });
        bpOuter.setTop(back);
    }

    void cancelInit(){
        cancel = new Button("Cancel");
        cancel.setPrefWidth(400);
        cancel.setOnMouseClicked(e -> ifCancellable());
        vbFields.getChildren().add(cancel);
    }


    void titleInit(){
        Label title = new Label("Cancel PC");
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
        dateContainer.getChildren().addAll(dateLbl, dateField);
        vbFields.getChildren().add(dateContainer);

    }

    void ifCancellable(){
        //Karena dari soal gaboleh pake library dari luar kelas jadi kita hardcode ya
        if(dateField.getValue() != null){
            Date selectedDate = java.sql.Date.valueOf(dateField.getValue());

            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();

            calendar.setTime(selectedDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            selectedDate = calendar.getTime();

            //Check if selected date is today or after today
            if(selectedDate.before(today)){
                Helper.showAlert(AlertType.INFORMATION, "Successfully cancelled");
            }else{
                Helper.showAlert(AlertType.ERROR, "Cannot cancel PC on the same day or before today");
            }
        }else{
            Helper.showAlert(AlertType.ERROR, "Date cannot be empty");
        }
    }
}
