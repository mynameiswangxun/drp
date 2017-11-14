package drp.basedata.manager;

import drp.basedata.domain.Item;
import drp.util.pagemodel.PageModel;


public interface ItemManager {
    void addItem(Item item);

    void deleteItem(String[] itemIds);

    void modifyItem(Item item);

    Item findItemById(String itemId);

    PageModel<Item> findItemList(int pageNo, int pageSize, String condition);

    void UploadFile(String itemId,String fileName);

}
