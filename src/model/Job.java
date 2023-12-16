package model;

import database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Job {

    private int job_id, userID, pc_id;
    private String jobStatus;

    public static ArrayList <Job> getAllJobData(){
        ArrayList<Job> jobList = new ArrayList<>();
        Connect conn = Connect.getConnection();
        String query = "SELECT * FROM jobs";
        try (ResultSet rs = conn.executeQuery(query)) {
            while (rs.next()) {
                int job_id = Integer.parseInt(rs.getString("job_id"));
                int user_id = Integer.parseInt(rs.getString("user_id"));
                int pc_id = Integer.parseInt(rs.getString("pc_id"));
                String job_status = rs.getString("job_status");
                Job job = new Job(job_id, user_id, pc_id, job_status);
                jobList.add(job);
            }
            return jobList;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean addNewJob(int user_id, int pc_id)
    {
        Connect conn = Connect.getConnection();
        String query = "INSERT INTO jobs VALUES (?, ?, 'UnComplete' )";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, user_id);
            ps.setInt(2, pc_id);
            ps.executeUpdate();;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updateJobStatus(int job_id, String job_status)
    {
        Connect conn = Connect.getConnection();
        String query = "UPDATE jobs SET job_status = ? WHERE job_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, job_status);
            ps.setInt(2, job_id);
            ps.executeUpdate();;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Job(int job_id, int userID, int pc_id, String jobStatus) {
        this.job_id = job_id;
        this.userID = userID;
        this.pc_id = pc_id;
        this.jobStatus = jobStatus;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPc_id() {
        return pc_id;
    }

    public void setPc_id(int pc_id) {
        this.pc_id = pc_id;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}
