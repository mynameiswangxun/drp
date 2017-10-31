package drp.test;

import drp.basedata.manager.ClientManager;
import drp.systemmgr.manager.UserManager;
import drp.util.DBUtil;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        System.out.println(ClientManager.getInstance().getClientTreeHtmlString());
    }
}
