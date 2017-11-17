package drp.basedata.dao;

import drp.basedata.domain.Item;
import drp.util.database.DBUtil;
import drp.util.datadict.domain.ItemCategory;
import drp.util.datadict.domain.ItemUnit;
import drp.util.datadict.manager.DataDictManager;
import drp.util.exception.ApplicationException;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoForMysql implements ItemDao {
    @Override
    public void addItem(Connection connection, Item item) {
        String sql = "INSERT INTO items(items_id,item_category_id,item_unit_id,name,spec,pattern,file_name) VALUES(?,?,?,?,?,?,?) ";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getItemId());
            preparedStatement.setString(2, item.getItemCategory().getId());
            preparedStatement.setString(3, item.getItemUnit().getId());
            preparedStatement.setString(4, item.getItemName());
            preparedStatement.setString(5, item.getSpec());
            preparedStatement.setString(6, item.getItemPattern());
            preparedStatement.setString(7,item.getFileName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 1062) {
                throw new ApplicationException("添加物料失败!物料代码" + item.getItemId() + "已经存在");
            } else {
                throw new ApplicationException("添加物料失败!");
            }
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteItem(Connection connection, String[] itemIds) {
        StringBuilder sql = new StringBuilder("DELETE FROM items WHERE items_id in(");
        for(int i = 0; i<itemIds.length; i++){
            sql.append("'");
            sql.append(itemIds[i]);
            sql.append("',");
        }
        sql.setCharAt(sql.length()-1,')');
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("删除物料失败!");
        }
    }

    @Override
    public void modifyItem(Connection connection, Item item) {
        String sql = "UPDATE items SET item_category_id=?,item_unit_id=?,name=?,spec=?,pattern=?,file_name=? WHERE items_id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getItemCategory().getId());
            preparedStatement.setString(2, item.getItemUnit().getId());
            preparedStatement.setString(3, item.getItemName());
            preparedStatement.setString(4, item.getSpec());
            preparedStatement.setString(5, item.getItemPattern());
            preparedStatement.setString(6, item.getFileName());
            preparedStatement.setString(7,item.getItemId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("修改物料失败!");
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }
    }


    @Override
    public Item findItemById(Connection connection, String itemId) {
        String sql = "SELECT * FROM items WHERE items_id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Item item = new Item();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, itemId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item.setItemId(resultSet.getString("items_id"));
                item.setItemPattern(resultSet.getString("pattern"));
                item.setSpec(resultSet.getString("spec"));
                item.setItemName(resultSet.getString("name"));
                item.setFileName(resultSet.getString("file_name"));

                DataDictManager dataDictManager = DataDictManager.getInstance();
                ItemCategory itemCategory = (ItemCategory) dataDictManager.findAbstractDataDictById(resultSet.getString("item_category_id"));
                ItemUnit itemUnit = (ItemUnit) dataDictManager.findAbstractDataDictById(resultSet.getString("item_unit_id"));
                item.setItemCategory(itemCategory);
                item.setItemUnit(itemUnit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("查询出错！");
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return item;
    }

    @Override
    public PageModel<Item> findItemList(Connection connection, int pageNo, int pageSize, String condition) {
        String sql = "SELECT * FROM items where (items_id like ? or name like ?) limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PageModel<Item> pageModel = new PageModel<Item>();
        List<Item> items = new ArrayList<Item>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + condition + "%");
            preparedStatement.setString(2, "%" + condition + "%");
            preparedStatement.setInt(3, (pageNo - 1)*pageSize);
            preparedStatement.setInt(4, pageSize);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getString("items_id"));
                item.setItemName(resultSet.getString("name"));
                item.setSpec(resultSet.getString("spec"));
                item.setItemPattern(resultSet.getString("pattern"));
                item.setFileName(resultSet.getString("file_name"));

                DataDictManager dataDictManager = DataDictManager.getInstance();
                ItemCategory itemCategory = (ItemCategory) dataDictManager.
                        findAbstractDataDictById(resultSet.getString("item_category_id"));
                ItemUnit unit = (ItemUnit) dataDictManager.findAbstractDataDictById(resultSet.getString("item_unit_id"));
                item.setItemCategory(itemCategory);
                item.setItemUnit(unit);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("查询出错！");
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setTotalRecords(findItemsTotal(connection, condition));
        pageModel.setList(items);
        return pageModel;
    }

    /**
     * 获得记录数总数
     *
     * @return
     */
    private int findItemsTotal(Connection connection, String condition) {
        String sql = "SELECT COUNT(*) FROM items WHERE items_id like ? or name like ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + condition + "%");
            preparedStatement.setString(2, "%" + condition + "%");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
