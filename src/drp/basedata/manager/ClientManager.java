package drp.basedata.manager;

public class ClientManager {
    /**
     * 返回HTML字符串
     * @return
     */
    private static ClientManager instance =new ClientManager();
    private ClientManager(){}
    public static ClientManager getInstance(){
        return instance;
    }
    public String getClientTreeHtmlString() {
        return new ClientTreeReader().getClientTreeHtmlString();
    }
}
