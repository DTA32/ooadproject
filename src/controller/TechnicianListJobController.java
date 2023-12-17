package controller;

import helper.Helper;
import javafx.scene.control.Alert;
import model.TechnicianListJobModel;

public class TechnicianListJobController {

	public static boolean updateStatus(int techID, String status) {
		// TODO Auto-generated method stub
		if (status.isEmpty()){
            Helper.showAlert(Alert.AlertType.ERROR, "Status cannot be empty!");
            return false;
        }
		TechnicianListJobModel.updateStatus(techID, status);
		return true;
	}
	
}
