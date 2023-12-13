package model;

import java.sql.Date;

public class CustomerTransactionHistoryModel {
    private int no;
    private int userid;
    private int pcid;
    private Date date;

    public CustomerTransactionHistoryModel(int no, int userid, int pcid, Date date) {
        this.no = no;
        this.userid = userid;
        this.pcid = pcid;
        this.date = date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
