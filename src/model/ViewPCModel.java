package model;

public class ViewPCModel {
    private int pcid;
    private String status;

    public ViewPCModel(int pcid, String status){
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
