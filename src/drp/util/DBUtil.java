package drp.util;


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
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null){
            XmlConfigReader xmlConfigReader = XmlConfigReader.getInstance();
            JdbcConfig jdbcConfig = xmlConfigReader.getJdbcConfig();
            try {
                Class.forName(jdbcConfig.getDriverName());
                connection = DriverManager.getConnection(jdbcConfig.getUrl(),jdbcConfig.getUsername()
                            ,jdbcConfig.getPassword());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement){
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
