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

public class FinishPC {
    private static FinishPC finishPC;
    public static FinishPC getInstance() {
        return finishPC = finishPC == null ? new FinishPC() : finishPC;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
    }
    Scene scene;

    BorderPane bpOuter;

    FlowPane fp;

    VBox vbFields;

    DatePicker  datefield;

    Button finish;


    public FinishPC(){
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
        finishInit();


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

    void finishInit(){
        finish = new Button("Finish");
        finish.setPrefWidth(400);
        finish.setOnMouseClicked(e -> {
            isFinishable();
        });
        vbFields.getChildren().add(finish);
    }

    void titleInit(){
        Label title = new Label("Finish Booking");
        title.setFont(new Font("Arial", 24));
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
    }

    void dateInit(){
        Label dateLbl = new Label("Date");
        datefield = new DatePicker();
        datefield.setPrefWidth(400);
        VBox dateContainer = new VBox();
        dateContainer.getChildren().addAll(dateLbl, datefield);
        vbFields.getChildren().add(dateContainer);
    }

    void isFinishable(){
        if(datefield.getValue() != null){
            Date selectedDate = java.sql.Date.valueOf(datefield.getValue());

            Calendar calendarTDY = Calendar.getInstance();

            stripTime(calendarTDY);
            Date today = calendarTDY.getTime();

            Calendar daySelected = Calendar.getInstance();
            daySelected.setTime(selectedDate);
            stripTime(daySelected);
            selectedDate = daySelected.getTime();


            if(selectedDate.before(today)){
                //testi
                boolean bookingDataExists = true;
                if(bookingDataExists){
//                    addTransactionHeader(selectedDate);
//                    addTransactionDetail(selectedDate);
                    System.out.println("Bisa cuy");
                }else{
                    Helper.showAlert(AlertType.ERROR, "No Booking data found");
                }
            }else{
                Helper.showAlert(AlertType.ERROR, "Cannot finish book because the date is not today or before today");
            }
        }else{
            Helper.showAlert(AlertType.WARNING, "Date cannot be empty");
        }
    }


    void stripTime(Calendar calendar){
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
    }
}
