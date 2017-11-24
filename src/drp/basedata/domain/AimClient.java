package drp.basedata.domain;


import drp.util.datadict.domain.ClientLevel;

public class AimClient {
    private int id;
    private String name;
    private String clientID;
    private String levelName;

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

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public String toString() {
        return "AimClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clientID='" + clientID + '\'' +
                ", clientLevelName='" + levelName + '\'' +
                '}';
    }
}
