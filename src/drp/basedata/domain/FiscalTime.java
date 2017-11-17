package drp.basedata.domain;

import java.util.Date;

public class FiscalTime {
    private int id;
    private int fiscalYear;
    private int fiscalMonth;
    private Date beginDate;
    private Date endDate;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(int fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public int getFiscalMonth() {
        return fiscalMonth;
    }

    public void setFiscalMonth(int fiscalMonth) {
        this.fiscalMonth = fiscalMonth;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FiscalTime{" +
                "id=" + id +
                ", fiscalYear=" + fiscalYear +
                ", fiscalMonth=" + fiscalMonth +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
