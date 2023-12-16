package model;

import database.Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PC {
    private int pcid;
    private String status;

    public static ArrayList<PC> getAllPCData(){
        ArrayList<PC> pcList = new ArrayList<>();
        Connect conn = Connect.getConnection();
        String query = "SELECT * FROM pcs";
        try (ResultSet rs = conn.executeQuery(query)) {
            while (rs.next()) {
                int pc_id = Integer.parseInt(rs.getString("pc_id"));
                String pc_condition = rs.getString("pc_condition");
                PC pc = new PC(pc_id, pc_condition);
                pcList.add(pc);
            }
            return pcList;
        } catch (SQLException e) {
            return null;
        }
    }

    public PC(int pcid, String status){
        this.pcid = pcid;
        this.status = status;
    }

    public int getPcid(){
        return pcid;
    }

    public void setPcid(int pcid){
        this.pcid = pcid;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }


}
