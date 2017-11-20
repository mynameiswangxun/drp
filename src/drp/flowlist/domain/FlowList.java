package drp.flowlist.domain;

import drp.basedata.domain.FiscalTime;
import drp.systemmgr.domain.User;
import drp.basedata.domain.*;

import java.util.Date;
import java.util.List;

public class FlowList {
    private String flowNum;
    private FiscalTime fiscalTime;
    //供方分销商ID
    private Client client;
    private User recorder;
    private Date opDate;
    private String opType;
    //单据状态
    private String vouSts;
    private User adjuster;
    private Date adjustDate;
    private User spotter;
    //抽查日期
    private Date spotDate;
    //抽查描述
    private String spotDesc;
    private User confirmer;
    //确认日期
    private Date confirmDate;

    private List<FlowDetail> flowDetails;

    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
    }

    public FiscalTime getFiscalTime() {
        return fiscalTime;
    }

    public void setFiscalTime(FiscalTime fiscalTime) {
        this.fiscalTime = fiscalTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getRecorder() {
        return recorder;
    }

    public void setRecorder(User recorder) {
        this.recorder = recorder;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getVouSts() {
        return vouSts;
    }

    public void setVouSts(String vouSts) {
        this.vouSts = vouSts;
    }

    public User getAdjuster() {
        return adjuster;
    }

    public void setAdjuster(User adjuster) {
        this.adjuster = adjuster;
    }

    public Date getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    public User getSpotter() {
        return spotter;
    }

    public void setSpotter(User spotter) {
        this.spotter = spotter;
    }

    public Date getSpotDate() {
        return spotDate;
    }

    public void setSpotDate(Date spotDate) {
        this.spotDate = spotDate;
    }

    public String getSpotDesc() {
        return spotDesc;
    }

    public void setSpotDesc(String spotDesc) {
        this.spotDesc = spotDesc;
    }

    public User getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(User confirmer) {
        this.confirmer = confirmer;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public List<FlowDetail> getFlowDetails() {
        return flowDetails;
    }

    public void setFlowDetails(List<FlowDetail> flowDetails) {
        this.flowDetails = flowDetails;
    }

    @Override
    public String toString() {
        return "FlowList{" +
                "flowNum='" + flowNum + '\'' +
                ", fiscalTime=" + fiscalTime +
                ", client=" + client +
                ", recorder=" + recorder +
                ", opDate=" + opDate +
                ", opType='" + opType + '\'' +
                ", vouSts='" + vouSts + '\'' +
                ", adjuster=" + adjuster +
                ", adjustDate=" + adjustDate +
                ", spotter=" + spotter +
                ", spotDate=" + spotDate +
                ", spotDesc='" + spotDesc + '\'' +
                ", confirmer=" + confirmer +
                ", confirmDate=" + confirmDate +
                ", flowDetails=" + flowDetails +
                '}';
    }
}
