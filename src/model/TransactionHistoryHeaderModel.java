package model;

import java.sql.Date;

public class TransactionHistoryHeaderModel {
    private int no;
    private int transactionID;
    private int userID;
    private Date date;
    private int total;
    
	public TransactionHistoryHeaderModel(int no, int transactionID, int userID , Date date, int total) {
        this.no = no;
        this.transactionID = transactionID;
        this.userID = userID;
        this.date = date;
        this.total = total; 
    }

    public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
