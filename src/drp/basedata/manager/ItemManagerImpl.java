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

    private ItemDao itemDao = null;

    public ItemManagerImpl(){
        ItemDaoFactory itemDaoFactory = null;
        //获得daoFactory的类名
        String factoryClassName = XmlConfigReader.getInstance().getDaoFactoryClassPathByName("mysql");
        //动态加载
        try {
            itemDaoFactory = (ItemDaoFactory) Class.forName(factoryClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        itemDao = itemDaoFactory.createItemDao();
    }
    @Override
    public void addItem(Item item) {

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
        Connection connection = DBUtil.getConnection();
        itemDao.deleteItem(connection,itemIds);
        DBUtil.closeConnection(connection);
    }

    @Override
    public void modifyItem(Item item) {
        Connection connection = DBUtil.getConnection();
        itemDao.modifyItem(connection,item);
        DBUtil.closeConnection(connection);
    }

    @Override
    public Item findItemById(String itemId) {
        Connection connection = DBUtil.getConnection();
        Item item = itemDao.findItemById(connection,itemId);
        DBUtil.closeConnection(connection);
        return item;
    }

    @Override
    public PageModel<Item> findItemList(int pageNo, int pageSize, String condition) {
        Connection connection = DBUtil.getConnection();
        PageModel<Item> pageModel = itemDao.findItemList(connection,pageNo,pageSize,condition);
        DBUtil.closeConnection(connection);
        return pageModel;
    }

    @Override
    public void UploadFile(String itemId, String fileName) {
        Connection connection = DBUtil.getConnection();
        try{
            Item item = itemDao.findItemById(connection,itemId);
            item.setFileName(fileName);
            itemDao.modifyItem(connection,item);
        } catch (ApplicationException ape){
            ape.printStackTrace();
            throw new ApplicationException("上传图片失败");
        } finally {
            DBUtil.closeConnection(connection);
        }
    }
}
