package model;

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
