package drp.util.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象工厂，主要创建两个系列产品
 * 1.Manager
 * 2.Dao
 */
public class BeanFactory {

    private static BeanFactory beanFactory = new BeanFactory();
    private final String beanConfFile = "beans-conf.xml";
    private Document document;

    private Map<String,Object> serviceMap = new HashMap();
    private Map<String,Object> daoMap = new HashMap();

    private BeanFactory() {
        try {
            document = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream(beanConfFile));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("读取XML文件出错");
        }
    }

    public static BeanFactory getInstance() {
        return beanFactory;
    }

    /**
     * 根据ID，获得Manager
     * @param serviceClassType
     * @return
     */
    public synchronized Object getServiceObject(Class serviceClassType) {
        if (serviceMap.get(serviceClassType.getName())!=null){
            return serviceMap.get(serviceClassType.getName());
        }else {
            Element element = (Element) document.selectSingleNode("//service[@id="+'"'+serviceClassType.getName()+'"'+"]");
            String className =  element.attributeValue("class");
            Object result = null;
            try {
                result = Class.forName(className).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("加载类失败");
            }
            serviceMap.put(serviceClassType.getName(),result);
            return result;
        }
    }

    /**
     * 根据ID，获得dao
     * @param daoClassType
     * @return
     */
    public synchronized Object getDaoObject(Class daoClassType) {
        if (daoMap.get(daoClassType.getName())!=null){
            return daoMap.get(daoClassType.getName());
        }else {
            Element element = (Element) document.selectSingleNode("//dao[@id="+'"'+daoClassType.getName()+'"'+"]");
            String className =  element.attributeValue("class");
            Object result = null;
            try {
                result = Class.forName(className).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("加载类失败");
            }
            daoMap.put(daoClassType.getName(),result);
            return result;
        }
    }
}
