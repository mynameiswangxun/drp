package drp.basedata.dao;

import drp.basedata.domain.Item;
import drp.util.exception.ApplicationException;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            throw new ApplicationException("添加物料失败!");
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
        return null;
    }

    @Override
    public PageModel<Item> findItemList(Connection connection, int pageNo, int pageSize, String condition) {
        return null;
    }
}
