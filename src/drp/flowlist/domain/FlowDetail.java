package drp.flowlist.domain;

import drp.basedata.domain.Item;

import java.math.BigDecimal;

/**
 * 流向单明细
 */
public class FlowDetail {
    FlowList flowList;
    Item item;
    AimClient aimClient;
    private BigDecimal opNum;
    private BigDecimal adjustNum;
    private String adjustReason;
    private String adjustFlag;

    public FlowList getFlowList() {
        return flowList;
    }

    public void setFlowList(FlowList flowList) {
        this.flowList = flowList;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public AimClient getAimClient() {
        return aimClient;
    }

    public void setAimClient(AimClient aimClient) {
        this.aimClient = aimClient;
    }

    public BigDecimal getOpNum() {
        return opNum;
    }

    public void setOpNum(BigDecimal opNum) {
        this.opNum = opNum;
    }

    public BigDecimal getAdjustNum() {
        return adjustNum;
    }

    public void setAdjustNum(BigDecimal adjustNum) {
        this.adjustNum = adjustNum;
    }

    public String getAdjustReason() {
        return adjustReason;
    }

    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason;
    }

    public String getAdjustFlag() {
        return adjustFlag;
    }

    public void setAdjustFlag(String adjustFlag) {
        this.adjustFlag = adjustFlag;
    }

    @Override
    public String toString() {
        return "FlowDetail{" +
                "flowList=" + flowList +
                ", item=" + item +
                ", aimClient=" + aimClient +
                ", opNum=" + opNum +
                ", adjustNum=" + adjustNum +
                ", adjustReason='" + adjustReason + '\'' +
                ", adjustFlag='" + adjustFlag + '\'' +
                '}';
    }
}
