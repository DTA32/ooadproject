package controller;

import database.Connect;
import model.Job;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class JobController {

    public static boolean addNewJob(int user_id, int pc_id)
    {
        return Job.addNewJob(user_id, pc_id);
    }

    public static boolean updateJobStatus(int job_id, String job_status)
    {
        return Job.updateJobStatus(job_id, job_status);
    }

    public static ArrayList<Job> getAllJobData(){
        return Job.getAllJobData();
    }

}
