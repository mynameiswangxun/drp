package drp.util.database;


import drp.util.datadict.manager.DataDictManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * id生成器
 */
public class IdGenerator {
    public static synchronized int generate(String tableName){
        String sql = "SELECT * FROM table_id WHERE table_name=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,tableName);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                throw new RuntimeException();
            }
            result = resultSet.getInt("value");
            result++;
            modifyValue(tableName,result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return result;
    }
    private static void modifyValue(String tableName,int newValue){
        String sql = "UPDATE table_id SET value=? WHERE table_name=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,newValue);
            preparedStatement.setString(2,tableName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }
}
