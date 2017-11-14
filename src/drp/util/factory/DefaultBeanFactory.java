package drp.util.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.Map;

public class DefaultBeanFactory implements BeanFactory{
    //key=配置文件中的id，value为对应的对象
    private Map<String,Object> beansMap = new HashMap();

    //系统缺省配置文件名称
    private String beanConfFile = "beans-conf.xml";
    private Document document;

    public DefaultBeanFactory(){
        loadConfFile();
    }

    @Override
    public synchronized Object getBean(String beanId) {
        if (beansMap.get(beanId)!=null){
            return beansMap.get(beanId);
        }else {
            Element element = (Element) document.selectSingleNode("//bean[@id="+'"'+beanId+'"'+"]");
            String className =  element.attributeValue("class");
            Object result = null;
            try {
                result = Class.forName(className).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("加载类失败");
            }
            beansMap.put(beanId,result);
            return result;
        }
    }

    private void loadConfFile(){
        try {
            document = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream(beanConfFile));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("读取XML文件出错");
        }
    }

}
