package drp.util.test;

import drp.basedata.dao.ItemDaoForMysql;
import drp.basedata.manager.AimClientManager;
import drp.basedata.manager.ClientManager;
import drp.basedata.manager.ItemManagerImpl;
import drp.basedata.manager.TemiClientManager;
import drp.flowlist.dao.FlowListDaoForMysql;
import drp.flowlist.domain.FlowList;
import drp.util.database.DBUtil;
import drp.util.database.IdGenerator;
import drp.util.database.XmlConfigReader;
import drp.util.datadict.manager.DataDictManager;
import drp.basedata.domain.*;
import drp.util.datadict.domain.ClientLevel;
import drp.util.pagemodel.PageModel;

import java.util.List;

public class Test {
    public static void main(String[] args) {
//        ClientManager clientManager = ClientManager.getInstance();
//        Client client = clientManager.findClientOrAreaById(10005);
//        System.out.println(client.getClientLevel().getId());
//        ClientManager clientManager = ClientManager.getInstance();
//        Client client = new Client();
//        client.setId(IdGenerator.generate("client"));
//        client.setPid(10013);
//        client.setClientId("A0002");
//        ClientLevel clientLevel= (ClientLevel) DataDictManager.getInstance().findAbstractDataDictById("102");
//        client.setClientLevel(clientLevel);
//        client.setBankAccount("123");
//        client.setContactTel("123");
//        client.setAddress("123");
//        client.setZipCode("123");
//
//        clientManager.addClientOrArea(client);
////        System.out.println(ClientManager.getInstance().isExistAreaName("北京市"));
//        ClientManager clientManager = ClientManager.getInstance();
//        clientManager.delClientOrArea(10021);
//        new TemiClientManager().deleteTemiClientOrAreaById(20003);
        new FlowListDaoForMysql().addFlowList("1",new FlowList());
    }
}
