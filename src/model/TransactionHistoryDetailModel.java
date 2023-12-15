package model;

public class TransactionHistoryDetailModel {
    private int no;
    private int transactionDetailID;
    private int transactionID;
    private int userID;
    private int pcID;
    
	public TransactionHistoryDetailModel(int no, int transactionDetailID, int transactionID , int userID, int pcID) {
        this.no = no;
        this.transactionDetailID = transactionDetailID;
        this.transactionID = transactionID;
        this.userID = userID;
        this.pcID = pcID; 
    }

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getTransactionDetailID() {
		return transactionDetailID;
	}

	public void setTransactionDetailID(int transactionDetailID) {
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
