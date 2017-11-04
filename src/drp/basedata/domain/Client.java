package drp.basedata.domain;

import drp.util.domian.datadict.ClientLevel;

public class Client {
    private int id;
    private int pid;
    private String name;
    private String clientId;
    private String bankAccount;
    private String contactTel;
    private String address;
    private String zipCode;
    private String isLeaf;
    private String isClient;
    private ClientLevel clientLevel;

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public String getIsClient() {
        return isClient;
    }

    public void setIsClient(String isClient) {
        this.isClient = isClient;
    }

    public ClientLevel getClientLevel() {
        return clientLevel;
    }

    public void setClientLevel(ClientLevel clientLevel) {
        this.clientLevel = clientLevel;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", clientId='" + clientId + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", isLeaf='" + isLeaf + '\'' +
                ", isClient='" + isClient + '\'' +
                ", clientLevel=" + clientLevel +
                '}';
    }
}
