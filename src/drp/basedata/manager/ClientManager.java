package drp.basedata.manager;

public class ClientManager {
    private static ClientManager instance =new ClientManager();
    private ClientManager(){}
    public static ClientManager getInstance(){
        return instance;
    }

    /**
     *返回HTML字符串
     * @return
     */
    public String getClientTreeHtmlString() {
        return new ClientTreeReader().getClientTreeHtmlString();
    }
}
