package drp.util.database;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sys-conf.xml读取
 */
public class XmlConfigReader {

    private static XmlConfigReader xmlConfigReader = new XmlConfigReader();
    private JdbcConfig jdbcConfig = new JdbcConfig();

    //保存dao工厂名称，key=名称，value=类路径
    private Map<String,String> daoFactoryMap = new HashMap<String,String>();

    private XmlConfigReader(){
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("sys-conf.xml");
        try {
            Document document = saxReader.read(inputStream);
            //配置jdbc基本信息
            Element dnElement = (Element)document.selectObject("/config/db-info/driver-name");
            Element urlElement = (Element)document.selectObject("/config/db-info/url");
            Element usernameElement = (Element)document.selectObject("/config/db-info/username");
            Element passwordElement  = (Element)document.selectObject("/config/db-info/password");
            jdbcConfig.setDrivername(dnElement.getStringValue());
            jdbcConfig.setUrl(urlElement.getStringValue());
            jdbcConfig.setUsername(usernameElement.getStringValue());
            jdbcConfig.setPassword(passwordElement.getStringValue());

            //取得DaoFactory信息
            List<Element> daoFactoryList = document.selectNodes("/config/dao-factory/*");
            for (Element element:
                    daoFactoryList) {
                String tagName = element.getName();
                String tagText = element.getText();
                daoFactoryMap.put(tagName,tagText);

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static XmlConfigReader getInstance() {
        return xmlConfigReader;
    }

    public JdbcConfig getJdbcConfig() {
        return jdbcConfig;
    }

    /**
     * 通过数据库名取得具体的daoFactory
     * @param name
     * @return
     */
    public String getDaoFactoryClassPathByName(String name){

        return daoFactoryMap.get(name.toLowerCase());
    }
}
