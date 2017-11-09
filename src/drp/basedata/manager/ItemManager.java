package drp.basedata.manager;

import drp.basedata.domain.Item;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;

public interface ItemManager {
    void addItem(Item item);

    void deleteItem(String[] itemIds);

    void modifyItem(Item item);

    Item findItemById(Connection connection, String itemId);

    PageModel<Item> findItemList(int pageNo, int pageSize, String condition);
}
