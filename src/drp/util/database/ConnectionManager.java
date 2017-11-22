package drp.util.database;

import drp.util.exception.ApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 用TreadLocal封装Connection
 * 放在TreadLocal的对象的声明周期是整个线程
 */
public class ConnectionManager {
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal();
    /**
     * 得到Connection
     * @return
     */
    public static Connection getConnection(){
        Connection connection = connectionThreadLocal.get();
        if(connection==null){
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
        }
        return connection;
    }
    public static void closeConnection(){
        Connection connection = connectionThreadLocal.get();
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connectionThreadLocal.remove();
    }
    public static void beginTransaction(Connection conn) {
        try {
            if (conn != null) {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false); //手动提交
                }
            }
        }catch(SQLException e) {}
    }

    public static void commitTransaction(Connection conn) {
        try {
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.commit();
                }
            }
        }catch(SQLException e) {}
    }

    public static void rollbackTransaction(Connection conn) {
        try {
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.rollback();
                }
            }
        }catch(SQLException e) {}
    }

    public static void resetConnection(Connection conn) {
        try {
            if (conn != null) {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }else {
                    conn.setAutoCommit(true);
                }
            }
        }catch(SQLException e) {}
    }
}
