package drp.basedata.dao;

import drp.basedata.domain.Item;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;

public interface ItemDao {
    /**
     * 添加物料
     * @param item
     */
    void addItem(Connection connection, Item item);

    /**
     * 根据ID批量删除物料
     * @param itemIds
     */
    void deleteItem(Connection connection, String[] itemIds);

    /**
     * 修改物料
     * @param item
     */
    void modifyItem(Connection connection, Item item);

    /**
     * 根据id查询物料
     * @param itemId
     * @return
     */
    Item findItemById(Connection connection, String itemId);

    /**
     * 根据条件分页查询
     * @return
     */
    PageModel<Item> findItemList(Connection connection, int pageNo,int pageSize,String condition);
}
