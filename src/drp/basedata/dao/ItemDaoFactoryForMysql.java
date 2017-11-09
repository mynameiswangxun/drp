package drp.basedata.dao;

public class ItemDaoFactoryForMysql implements ItemDaoFactory{
    @Override
    public ItemDao createItemDao() {
        return new ItemDaoForMysql();
    }
}
