package controller;

import helper.Helper;
import javafx.scene.control.Alert;
import model.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportController {
    public List<Report> GetAllReportData(){
        try{
            return Report.GetAllReportData();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
    public boolean AddNewReport(String user_role, int pc_id, String report_note){
        if(report_note.isBlank()){
            Helper.showAlert(Alert.AlertType.ERROR, "Report note cannot be blank");
            return false;
        }
        return Report.AddNewReport(user_role, pc_id, report_note);
    }
}
