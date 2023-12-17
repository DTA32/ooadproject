package controller;

import javafx.collections.ObservableList;
import model.PC;
import helper.Helper;
import javafx.scene.control.Alert.AlertType;
import model.PCBook;

import java.util.ArrayList;


public class PCController {

    public static ObservableList getAllPc(){
        return PC.getallPC();
    }

    public static void addNewPc(String pcidText) {
        if (pcidText == null || pcidText.isEmpty()) {
            Helper.showAlert(AlertType.ERROR, "PC ID Cannot be empty!");
            return;
        }

        int pcid;
        try {
            pcid = Integer.parseInt(pcidText);
        } catch (NumberFormatException ex) {
            Helper.showAlert(AlertType.ERROR, "PC ID must be a number");
            return;
        }

        ObservableList<PC> observableList = PC.getallPC();

        boolean isUnique = observableList.stream().noneMatch(pc -> pc.getPcid() == pcid);

        if (isUnique) {
            PC.addNewPC(pcid);
            Helper.showAlert(AlertType.INFORMATION, "PC Added Successfully!");
        } else {
            Helper.showAlert(AlertType.ERROR, "PC ID Already Exists!");
        }
    }

    public static void updatePC(String pcidText, String status){
        if (pcidText == null || pcidText.isEmpty()) {
            Helper.showAlert(AlertType.ERROR, "PC ID Cannot be empty!");
            return;
        }

        int pcid;
        try {
            pcid = Integer.parseInt(pcidText);
        } catch (NumberFormatException ex) {
            Helper.showAlert(AlertType.ERROR, "PC ID must be a number");
            return;
        }

        ObservableList<PC> observableList = PC.getallPC();

        boolean isUnique = observableList.stream().anyMatch(pc -> pc.getPcid().equals(pcid));

        if (isUnique) {
            PC.updatePC(pcid, status);
            Helper.showAlert(AlertType.INFORMATION, "PC Updated Successfully!");
        } else {
            Helper.showAlert(AlertType.ERROR, "PC ID Does Not Exist!");
        }
    }

    public static void deletePC(String pcidText){
        if(pcidText == null || pcidText.isEmpty()){
            Helper.showAlert(AlertType.ERROR, "PC ID Cannot be empty!");
            return;
        }

        //parse bang
        int pcid;

        try {
            pcid = Integer.parseInt(pcidText);
        } catch (NumberFormatException e) {
            Helper.showAlert(AlertType.ERROR, "PC ID must be a number!");
            return;
        }

        if(PCBook.checkforBookings(pcid)){
            Helper.showAlert(AlertType.ERROR, "PC is currently booked!");
            return;
        }

        PC.deletePC(pcid);
        Helper.showAlert(AlertType.CONFIRMATION, "PC has been deleted!");

    }

    public static ArrayList<PC> getAllPCData() {
        return PC.getAllPCData();
    }
}
