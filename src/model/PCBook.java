package model;

import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PCBook
{
    private int book_id;
    private Integer pc_id;
    private Integer user_id;
    private Date booked_date;

    public PCBook(int book_id, Integer pc_id, Integer user_id, Date booked_date) {
        this.book_id = book_id;
        this.pc_id = pc_id;
        this.user_id = user_id;
        this.booked_date = booked_date;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Integer getPc_id() {
        return pc_id;
    }

    public void setPc_id(Integer pc_id) {
        this.pc_id = pc_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getBooked_date() {
        return booked_date;
    }

    public void setBooked_date(Date booked_date) {
        this.booked_date = booked_date;
    }

    //nanti tambahin 1 parameter baru user_id

    public static boolean addNewBook(Integer pcid, LocalDate booked_date, Integer user_id) {
        Connect connect = Connect.getConnection();

        Date date = Date.from(booked_date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String prepareSql = "INSERT INTO pcbooks (pc_id, user_id, booked_date) VALUES (?, ?, ?)";

        try(PreparedStatement ps = connect.prepareStatement(prepareSql)) {
            ps.setInt(1, pcid);
            ps.setInt(2, user_id);
            ps.setDate(3, new java.sql.Date(date.getTime()));

            int getRows = ps.executeUpdate();
            return getRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean isAvailablePC(Integer pcid, LocalDate booked_date) {
        Connect connect = Connect.getConnection();
        java.sql.Date checkDateAvailability = java.sql.Date.valueOf(booked_date);

        String prepareSql = "SELECT COUNT(*) FROM pcbooks WHERE pc_id = ? and DATE(booked_date) = ?";

        try(PreparedStatement ps = connect.prepareStatement(prepareSql)) {
           ps.setInt(1, pcid);
           ps.setDate(2, checkDateAvailability);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int count = rs.getInt(1);
                return count == 0; //cek kalo ada pc yg udah di book atau belum
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ObservableList<PCBook> getAllBookedPCs(){
        Connect connect = Connect.getConnection();
        String prepareSql = "SELECT * FROM pcbooks";

        try {
            ResultSet rs = connect.executeQuery(prepareSql);
            ObservableList<PCBook> bookedPCs = FXCollections.observableArrayList();
            while (rs.next()){
                int bookID = rs.getInt("book_id");
                int pcID = rs.getInt("pc_id");
                int userID = rs.getInt("user_id");
                Date bookedDate = rs.getDate("booked_date");
                bookedPCs.add(new PCBook(bookID, pcID, userID, bookedDate));
            }

            return bookedPCs;
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    public static boolean DeleteBookData(int book_id){
        Connect connect = Connect.getConnection();

        String prepareSql = "DELETE FROM pcbooks WHERE book_id = ?";

        try (PreparedStatement ps = connect.prepareStatement(prepareSql)){
            ps.setInt(1, book_id);
            int getRows = ps.executeUpdate();
            return getRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static LocalDate getBookingbyID(int book_id){
        Connect connect = Connect.getConnection();

        String prepareSql = "SELECT booked_date FROM pcbooks WHERE book_id = ?";

        try(PreparedStatement ps = connect.prepareStatement(prepareSql)) {
            ps.setInt(1, book_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               java.sql.Date bookedDate = rs.getDate("booked_date");
               return bookedDate.toLocalDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean checkforBookings(int pc_id){
        Connect connect = Connect.getConnection();

        String prepareSql = "SELECT COUNT(*) FROM pcbooks WHERE pc_id = ?";

        try (PreparedStatement ps = connect.prepareStatement(prepareSql)){
            ps.setInt(1, pc_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static String getPCStatus(int pcid){
//        Connect connect = Connect.getConnection();
//        String prepareSql = "SELECT pc_condition FROM pcs WHERE pc_id = ?";
//
//        try (PreparedStatement ps = connect.prepareStatement(prepareSql)){
//            ps.setInt(1, pcid);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                return rs.getString("pc_condition");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}



