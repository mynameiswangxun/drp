package drp.basedata.domain;

import drp.util.datadict.domain.TemiClientLevel;

public class TemiClient {
    private int id;
    private int pid;
    private String name;
    private String temiClientId;
    private String bankAccount;
    private String contactTel;
    private String contactor;
    private String address;
    private String zipCode;
    private String isLeaf;
    private String isTemiClient;
    private TemiClientLevel temiClientLevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemiClientId() {
        return temiClientId;
    }

    public void setTemiClientId(String temiClientId) {
        this.temiClientId = temiClientId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIsTemiClient() {
        return isTemiClient;
    }

    public void setIsTemiClient(String isClient) {
        this.isTemiClient = isClient;
    }

    public TemiClientLevel getTemiClientLevel() {
        return temiClientLevel;
    }

    public void setTemiClientLevel(TemiClientLevel temiClientLevel) {
        this.temiClientLevel = temiClientLevel;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    @Override
    public String toString() {
        return "TemiClient{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", temiClientId='" + temiClientId + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", isLeaf='" + isLeaf + '\'' +
                ", isClient='" + isTemiClient + '\'' +
                ", clientLevel=" + temiClientLevel +
                '}';
    }
}
