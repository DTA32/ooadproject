package model;

import database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report {
    private int report_id;
    private String user_role;
    private int pc_id;
    private String report_note;
    private Date report_date;

    public Report(int report_id, String user_role, int pc_id, String report_note, Date report_date) {
        this.report_id = report_id;
        this.user_role = user_role;
        this.pc_id = pc_id;
        this.report_note = report_note;
        this.report_date = report_date;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public int getPc_id() {
        return pc_id;
    }

    public void setPc_id(int pc_id) {
        this.pc_id = pc_id;
    }

    public String getReport_note() {
        return report_note;
    }

    public void setReport_note(String report_note) {
        this.report_note = report_note;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public static List<Report> GetAllReportData(){
        List<Report> reports = new ArrayList<>();
        Connect db = Connect.getConnection();
        String query = "SELECT * FROM Reports";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                int report_id = Integer.parseInt(rs.getString("report_id"));
                String user_role = rs.getString("user_role");
                int pc_id = Integer.parseInt(rs.getString("pc_id"));
                String report_note = rs.getString("report_note");
                Date report_date = rs.getDate("report_date");
                Report report = new Report(report_id, user_role, pc_id, report_note, report_date);
                reports.add(report);
            }
            return reports;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean AddNewReport(String user_role, int pc_id, String report_note){
        String dateNow = String.format("%tF", new Date());
        Connect db = Connect.getConnection();
        String query = "INSERT INTO Reports (user_role, pc_id, report_note, report_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = db.prepareStatement(query)) {
            ps.setString(1, user_role);
            ps.setInt(2, pc_id);
            ps.setString(3, report_note);
            ps.setString(4, dateNow);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
