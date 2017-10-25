package drp.test;

import drp.systemmgr.manager.UserManager;
import drp.util.DBUtil;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        System.out.println(UserManager.getInstance().findUserById("a1235"));
    }
}
