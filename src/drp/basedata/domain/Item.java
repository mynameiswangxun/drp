package drp.basedata.domain;

import drp.util.datadict.domain.ItemCategory;
import drp.util.datadict.domain.ItemUnit;

/**
 * 物料实体
 */
public class Item {
    private String itemId;
    private String itemName;
    private String itemPattern;
    private String spec;
    private ItemCategory itemCategory;
    private ItemUnit itemUnit;
    private String fileName;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPattern() {
        return itemPattern;
    }

    public void setItemPattern(String itemPattern) {
        this.itemPattern = itemPattern;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ItemUnit getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(ItemUnit itemUnit) {
        this.itemUnit = itemUnit;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPattern='" + itemPattern + '\'' +
                ", spec='" + spec + '\'' +
                ", itemCategory=" + itemCategory +
                ", itemUnit=" + itemUnit +
                '}';
    }
}
