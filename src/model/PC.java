package model;

import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PC {
    private Integer pcid;
    private String status;

    public PC(Integer pcid, String status){
        this.pcid = pcid;
        this.status = status;
    }

    public Integer getPcid() {
        return pcid;
    }

    public void setPcid(Integer pcid) {
        this.pcid = pcid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PC(){

    }

    public static void updatePCCondition(String pc_id, String pc_condition)
    {
        Connect connect = Connect.getConnection();
        String prepareSql = "UPDATE pcs SET pc_condition = ? WHERE pc_id = ?";

        try (PreparedStatement ps = connect.prepareStatement(prepareSql)) {
            ps.setString(1, pc_condition);
            ps.setString(2, pc_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Check pc's existence
    public static ObservableList<PC> getallPC(){
        Connect connect = Connect.getConnection();
        String prepareSql = "SELECT * FROM pcs";
        ResultSet rs = connect.executeQuery(prepareSql);

        try {
            ObservableList<PC> ListOfPc = FXCollections.observableArrayList();
            while(rs.next()){
                PC pc = new PC(rs.getInt("pc_id"), rs.getString("pc_condition"));
                ListOfPc.add(pc);
            }

            return ListOfPc;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void addNewPC(int pcid){
        Connect connect = Connect.getConnection();
        String prepareSql = "INSERT INTO pcs (pc_id, pc_condition) VALUES (?, 'Usable')";

        try (PreparedStatement ps = connect.prepareStatement(prepareSql)) {
            ps.setInt(1, pcid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePC(int pcid, String status){
        Connect connect = Connect.getConnection();
        String prepareSql = "UPDATE pcs SET pc_condition = ? WHERE pc_id = ?";

        try (PreparedStatement ps = connect.prepareStatement(prepareSql)) {
            ps.setString(1, status);
            ps.setInt(2, pcid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //for delete make book logic first
    public static void deletePC(int pcid){
        Connect connect = Connect.getConnection();
        String prepareSql = "DELETE FROM pcs WHERE pc_id = ?";

        try (PreparedStatement ps = connect.prepareStatement(prepareSql)) {
            ps.setInt(1, pcid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

}
