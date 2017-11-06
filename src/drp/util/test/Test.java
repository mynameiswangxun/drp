package drp.util.test;

import drp.basedata.manager.ClientManager;
import drp.util.database.IdGenerator;
import drp.util.datadict.manager.DataDictManager;
import drp.basedata.domain.*;

public class Test {
    public static void main(String[] args) {
//        ClientManager clientManager = ClientManager.getInstance();
//        Client client = clientManager.findClientOrAreaById(10005);
//        System.out.println(client.getClientLevel().getId());

        System.out.println(ClientManager.getInstance().isExistClientByClientId("A0001"));
    }
}
