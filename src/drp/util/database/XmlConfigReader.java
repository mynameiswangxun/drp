package drp.util.database;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * sys-conf.xml读取
 */
public class XmlConfigReader {

    private static XmlConfigReader xmlConfigReader = new XmlConfigReader();
    private JdbcConfig jdbcConfig = new JdbcConfig();

    private XmlConfigReader(){
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("sys-conf.xml");
        try {
            Document document = saxReader.read(inputStream);
            Element dnElement = (Element)document.selectObject("/config/db-info/driver-name");
            Element urlElement = (Element)document.selectObject("/config/db-info/url");
            Element usernameElement = (Element)document.selectObject("/config/db-info/username");
            Element passwordElement  = (Element)document.selectObject("/config/db-info/password");
            jdbcConfig.setDrivername(dnElement.getStringValue());
            jdbcConfig.setUrl(urlElement.getStringValue());
            jdbcConfig.setUsername(usernameElement.getStringValue());
            jdbcConfig.setPassword(passwordElement.getStringValue());
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
}
