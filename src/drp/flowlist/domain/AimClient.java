package drp.flowlist.domain;


public class AimClient {
    private int id;
    private String name;
    private String clientID;
    private String clientLevelName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientLevelName() {
        return clientLevelName;
    }

    public void setClientLevelName(String clientLevelName) {
        this.clientLevelName = clientLevelName;
    }

    @Override
    public String toString() {
        return "AimClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clientID='" + clientID + '\'' +
                ", clientLevelName='" + clientLevelName + '\'' +
                '}';
    }
}
