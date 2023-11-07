package model;

public class ReportModel {
    private int id;
    private int pcid;
    private String reportNotes;

    public ReportModel(int id, int pcid, String reportNotes) {
        this.id = id;
        this.pcid = pcid;
        this.reportNotes = reportNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public String getReportNotes() {
        return reportNotes;
    }

    public void setReportNotes(String reportNotes) {
        this.reportNotes = reportNotes;
    }
}
