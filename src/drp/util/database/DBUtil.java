package drp.util.database;


import drp.util.exception.ApplicationException;

import java.sql.*;

/**
 * 封装数据常用操作
 */
public class DBUtil {
    /**
     * 取得Connection
     *
     * @return 数据库连接
     */
    public static Connection getConnection() {
        Connection connection = null;
        XmlConfigReader xmlConfigReader = XmlConfigReader.getInstance();
        JdbcConfig jdbcConfig = xmlConfigReader.getJdbcConfig();
        try {
            Class.forName(jdbcConfig.getDriverName());
            connection = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername()
                    , jdbcConfig.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ApplicationException("系统错误");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("系统错误");
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement Statement) {
        if (Statement != null) {
            try {
                Statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
