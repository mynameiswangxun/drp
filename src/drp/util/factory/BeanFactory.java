package drp.util.factory;

public interface BeanFactory {
    /**
     * 根据产品编号取得具体产品
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
