package model;

import database.Connect;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionHistoryHeaderModel {
    private int transactionID;
    private int staff_id;
	private String staff_name;
    private String date;

	public static ArrayList<TransactionHistoryDetailModel> getAllTransactionDetail(int transactionID){
		Connect conn = Connect.getConnection();
		String prepareSql = "SELECT * FROM transactiondetails WHERE transaction_id = ?;";
		ArrayList<TransactionHistoryDetailModel> transactionHistoryDetailModels = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
			ps.setInt(1, transactionID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				transactionHistoryDetailModels.add(new TransactionHistoryDetailModel(
						rs.getString("booked_time"),
						rs.getInt("transaction_id"),
						rs.getInt("customer_name"),
						rs.getInt("pc_id")
				));
			}
			return transactionHistoryDetailModels;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<TransactionHistoryHeaderModel> getAllTransactionHeaderData() {
		ArrayList<TransactionHistoryHeaderModel> result = new ArrayList<TransactionHistoryHeaderModel>();
		Connect conn = Connect.getConnection();
		String query = "SELECT * FROM transactionheaders";
		try (ResultSet rs = conn.executeQuery(query)) {
			while (rs.next()) {
				int transactionID = rs.getInt("transaction_id");
				int staff_id = rs.getInt("staff_id");
				String staff_name = rs.getString("staff_name");
				String date = rs.getString("transaction_date");
				TransactionHistoryHeaderModel transactionHistoryHeaderModel = new TransactionHistoryHeaderModel(transactionID, staff_id, staff_name, date);
				result.add(transactionHistoryHeaderModel);
			}
			return result;
		} catch (SQLException e) {
			return null;
		}
	}

	public static int addNewTransactionHeader(User StaffID, String transactionDate){
		Connect conn = Connect.getConnection();
		String prepareSql = "INSERT INTO transactionheaders (staff_id, staff_name, transaction_date) VALUES (?, ?, ?);";
		try (PreparedStatement ps = conn.prepareStatement(prepareSql)) {
			ps.setInt(1, Integer.parseInt(StaffID.getUserID()));
			ps.setString(2, StaffID.getUserName());
			ps.setString(3, transactionDate);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String lastId = "SELECT LAST_INSERT_ID() AS last_id FROM transactionheaders;";
		try (ResultSet rs = conn.executeQuery(lastId)) {
			if (rs.next()) {
				return rs.getInt("last_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return 0;
    }

	public TransactionHistoryHeaderModel(int transactionID, int staff_id, String staff_name, String date) {
		this.transactionID = transactionID;
		this.staff_id = staff_id;
		this.staff_name = staff_name;
		this.date = date;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	public String getStaff_name() {
		return staff_name;
	}

	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
