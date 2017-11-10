package drp.basedata.dao;

import drp.basedata.domain.Item;
import drp.util.datadict.domain.ItemCategory;
import drp.util.datadict.domain.ItemUnit;
import drp.util.datadict.manager.DataDictManager;
import drp.util.exception.ApplicationException;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDaoForMysql implements ItemDao{
    @Override
    public void addItem(Connection connection, Item item) {
        String sql = "INSERT INTO items(items_id,item_category_id,item_unit_id,name,spec,pattern) VALUES(?,?,?,?,?,?) ";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,item.getItemId());
            preparedStatement.setString(2,item.getItemCategory().getId());
            preparedStatement.setString(3,item.getItemUnit().getId());
            preparedStatement.setString(4,item.getItemName());
            preparedStatement.setString(5,item.getSpec());
            preparedStatement.setString(6,item.getItemPattern());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getErrorCode()==1062){
                throw new ApplicationException("添加物料失败!物料代码"+item.getItemId()+"已经存在");
            }else{
                throw new ApplicationException("添加物料失败!");
            }
        }
    }

    @Override
    public void deleteItem(Connection connection, String[] itemIds) {

    }

    @Override
    public void modifyItem(Connection connection, Item item) {

    }

    @Override
    public Item findItemById(Connection connection, String itemId) {
        String sql = "SELECT * FROM items WHERE items_id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Item item = new Item();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,itemId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                item.setItemId(resultSet.getString("items_id"));
                item.setItemPattern(resultSet.getString("pattern"));
                item.setSpec(resultSet.getString("spec"));
                item.setItemName(resultSet.getString("name"));

                DataDictManager dataDictManager = DataDictManager.getInstance();
                ItemCategory itemCategory = (ItemCategory) dataDictManager.findAbstractDataDictById(resultSet.getString("item_category_id"));
                ItemUnit itemUnit = (ItemUnit) dataDictManager.findAbstractDataDictById(resultSet.getString("item_unit_id"));
                item.setItemCategory(itemCategory);
                item.setItemUnit(itemUnit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public PageModel<Item> findItemList(Connection connection, int pageNo, int pageSize, String condition) {
        return null;
    }
}
