package drp.util.datadict.domain;

/**
 * 数据字典的抽象
 */
public abstract class AbstractDataDict {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AbstractDataDict{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
