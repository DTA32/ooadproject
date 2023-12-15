package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import database.Connect;

public class TechnicianListJobModel {
    private int no;
    private String technician;
    private String status;
    private int pcid;

    public TechnicianListJobModel(int no, String technician, String status, int pcid) {
        this.no = no;
        this.technician = technician;
        this.status = status;
        this.pcid = pcid;
    }
    
//    public static List getListJob() {
//    	List ll = new List;
//    
//        Connect conn = Connect.getConnection();
//        String prepareSql = "SELECT 1 as no, 'Yudi' as name, 'Complete' as state, 13 as numb FROM users;";
//        
//        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
//            ResultSet rs = ps.executeQuery();
//            System.out.println(rs);
//            while(rs.next())
//              {
//                  int no = rs.getInt("no");
//                  String name = rs.getString("name");
//                  String state = rs.getString("state");
//                  int numb = rs.getInt("numb");
//                  ll.add(e)
//              }
//            return rs;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    
    public static boolean updateStatus(int techID, String status) {
        Connect conn = Connect.getConnection();
        String prepareSql = "UPDATE users set user_name= ? where user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
        	ps.setString(1, status);
			ps.setInt(2, techID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }
}
