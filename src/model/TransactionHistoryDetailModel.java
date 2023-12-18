package model;

import database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionHistoryDetailModel {
    private String transactionDetailID;
    private int transactionID;
    private int userID;
    private int pcID;

	public static ArrayList<TransactionHistoryDetailModel> getUserTransactionDetail(int userID){
		Connect conn = Connect.getConnection();
		String prepareSql = "SELECT * FROM transactiondetails WHERE user_id = ?;";
		ArrayList<TransactionHistoryDetailModel> transactionHistoryDetailModels = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				transactionHistoryDetailModels.add(new TransactionHistoryDetailModel(
						rs.getString("booked_time"),
						rs.getInt("transaction_id"),
						rs.getInt("user_id"),
						rs.getInt("pc_id")
				));
			}
			return transactionHistoryDetailModels;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void addTransactionDetail(int transactionID, List<PCBook> PCBooks){
		Connect conn = Connect.getConnection();
		String prepareSql = "INSERT INTO transactiondetails (transaction_id, pc_id, user_id, booked_time) VALUES (?, ?, ?, ?);";
		try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
			for(PCBook pcBook : PCBooks){
				ps.setInt(1, transactionID);
				ps.setInt(2, pcBook.getPc_id());
				ps.setInt(3, pcBook.getUser_id());
				ps.setString(4, String.format("%tF", pcBook.getBooked_date()));
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TransactionHistoryDetailModel(String transactionDetailID, int transactionID , int userID, int pcID) {
        this.transactionDetailID = transactionDetailID;
        this.transactionID = transactionID;
        this.userID = userID;
        this.pcID = pcID;
    }

	public String getTransactionDetailID() {
		return transactionDetailID;
	}

	public void setTransactionDetailID(String transactionDetailID) {
		this.transactionDetailID = transactionDetailID;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getPcID() {
		return pcID;
	}

	public void setPcID(int pcID) {
		this.pcID = pcID;
	}
}
