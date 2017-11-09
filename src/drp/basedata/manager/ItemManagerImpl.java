package drp.basedata.manager;

import drp.basedata.dao.ItemDao;
import drp.basedata.dao.ItemDaoFactory;
import drp.basedata.domain.Item;
import drp.util.database.DBUtil;
import drp.util.database.XmlConfigReader;
import drp.util.exception.ApplicationException;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;

public class ItemManagerImpl implements ItemManager{
    @Override
    public void addItem(Item item) {
        ItemDaoFactory itemDaoFactory = null;
        ItemDao itemDao = null;
        //获得daoFactory的类名
        String factoryClassName = XmlConfigReader.getInstance().getDaoFactoryClassPathByName("mysql");
        //动态加载
        try {
            itemDaoFactory = (ItemDaoFactory) Class.forName(factoryClassName).newInstance();
            itemDao = itemDaoFactory.createItemDao();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try{
            connection = DBUtil.getConnection();
            itemDao.addItem(connection,item);
        }catch (ApplicationException apex){
            throw apex;
        }finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteItem(String[] itemIds) {

    }

    @Override
    public void modifyItem(Item item) {

    }

    @Override
    public Item findItemById(Connection connection, String itemId) {
        return null;
    }

    @Override
    public PageModel<Item> findItemList(int pageNo, int pageSize, String condition) {
        return null;
    }
}
