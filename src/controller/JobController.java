package controller;

import database.Connect;
import helper.Helper;
import javafx.scene.control.Alert;
import model.Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobController {

    public static boolean addNewJob(int user_id, int pc_id)
    {
        return Job.addNewJob(user_id, pc_id);
    }

    public static boolean updateJobStatus(int job_id, String job_status)
    {
        Connect connect = Connect.getConnection();
        String query = "SELECT job_status FROM jobs WHERE job_id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        String status = "";
        try {
            preparedStatement.setInt(1, job_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                status = resultSet.getString("job_status");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (status.equals("Complete")) {
            Helper.showAlert(Alert.AlertType.ERROR, "Job is already completed!");
            return false;
        }
        else if (job_status.equals("UnComplete")) {
            Helper.showAlert(Alert.AlertType.ERROR, "Job must be completed!");
            return false;
        }
        Job.updateJobStatus(job_id, job_status);
        return true;
    }

    public static ArrayList<Job> getTechnicianJob(String user_id){
        return Job.getTechnicianJob(user_id);
    }

    public static ArrayList<Job> getAllJobData(){
        return Job.getAllJobData();
    }

}
