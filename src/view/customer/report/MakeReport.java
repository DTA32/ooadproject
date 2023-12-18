package view.customer.report;

import controller.ReportController;
import helper.Helper;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.MainStage;
import view.TemporaryMenu;
import view.customer.menu.CustomerMenu;
import view.operator.menu.OperatorMenu;

public class MakeReport {
    private static MakeReport makeReport;
    public static MakeReport getInstance() {
        return makeReport = makeReport == null ? new MakeReport() : makeReport;
    }
    public void show(){
        MainStage stage = MainStage.getInstance();
        stage.getStage().setScene(scene);
        _repaint();
    }
    public void _repaint(){
        pcidField.setText("");
        reportNoteField.setText("");
    }
    Scene scene;
    BorderPane bpOuter;
    FlowPane fp;
    VBox vbFields;
    TextField pcidField;
    TextArea reportNoteField;
    Button reportBtn;
    Button back;

    public MakeReport() {
        initialize();
        setupEventHandling();
    }

    private void initialize() {
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
        pcidInit();
        reportNoteInit();

        fp.getChildren().add(vbFields);
        reportBtnInit();

        bpOuter.setCenter(fp);

        scene = new Scene(bpOuter, 1200, 600);
    }

    void backInit(){
        back = new Button("< Back");
        bpOuter.setTop(back);
    }

    void titleInit(){
        Label title = new Label("Make Report");
        title.setFont(new Font("Arial", 24));
        VBox titleContainer = new VBox();
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        fp.getChildren().add(titleContainer);
    }

    void pcidInit(){
        Label pcidLbl = new Label("PC ID");
        pcidField = new TextField();
        VBox pcidContainer = new VBox();
        pcidContainer.getChildren().addAll(pcidLbl, pcidField);
        vbFields.getChildren().add(pcidContainer);
    }

    void reportNoteInit(){
        Label reportNoteLbl = new Label("Report Note");
        reportNoteField = new TextArea();
        VBox reportNoteContainer = new VBox();
        reportNoteContainer.getChildren().addAll(reportNoteLbl, reportNoteField);
        vbFields.getChildren().add(reportNoteContainer);
    }

    void reportBtnInit(){
        reportBtn = new Button("Report");
        reportBtn.setPrefWidth(400);
        reportBtn.setAlignment(Pos.CENTER);
        fp.getChildren().add(reportBtn);
    }

    void setupEventHandling(){
        reportBtn.setOnMouseClicked((e) ->  {
            ReportController controller = new ReportController();
            String userRole = Helper.getCurrentUser().getRole();
            int pcid = Integer.parseInt(pcidField.getText());
            String reportNote = reportNoteField.getText();
            if(controller.AddNewReport(userRole, pcid, reportNote)){
                CustomerMenu customerMenu = CustomerMenu.getInstance();
                customerMenu.show();
            } else {
                Helper.showAlert(Alert.AlertType.ERROR, "Failed to make report!");
            }
        });
        back.setOnMouseClicked(e -> {
            OperatorMenu operatorMenu = OperatorMenu.getInstance();
            operatorMenu.show();
        });
    }
}
