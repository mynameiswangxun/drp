package drp.util.datadict.manager;

import drp.util.database.DBUtil;
import drp.util.datadict.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataDictManager {
    private static DataDictManager dataDictManager = new DataDictManager();
    private DataDictManager(){}
    public static DataDictManager getInstance(){
        return dataDictManager;
    }
    /**
     * 查找分销商级别列表
     * @return
     */
    public List<ClientLevel> findClientLevelList(){
        String sql = "SELECT * FROM data_dic WHERE category='A'";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ClientLevel> levels= new ArrayList<ClientLevel>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ClientLevel clientLevel = new ClientLevel();
                clientLevel.setId(resultSet.getString("id"));
                clientLevel.setName(resultSet.getString("name"));
                levels.add(clientLevel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return levels;
    }

    /**
     * 通过id查找数据字典中的数据
     * @return
     */
    public AbstractDataDict findAbstractDataDictById(String id){
        String sql = "SELECT * FROM data_dic WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AbstractDataDict abstractDataDict = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                switch (resultSet.getString("category")){
                    case "A":
                        abstractDataDict = new ClientLevel();
                        abstractDataDict.setId(resultSet.getString("id"));
                        abstractDataDict.setName(resultSet.getString("name"));
                        break;
                    case "B":
                        abstractDataDict = new TemiClientLevel();
                        abstractDataDict.setId(resultSet.getString("id"));
                        abstractDataDict.setName(resultSet.getString("name"));
                        break;
                    case "C":
                        abstractDataDict = new ItemCategory();
                        abstractDataDict.setId(resultSet.getString("id"));
                        abstractDataDict.setName(resultSet.getString("name"));
                        break;
                    case "D":
                        abstractDataDict = new ItemUnit();
                        abstractDataDict.setId(resultSet.getString("id"));
                        abstractDataDict.setName(resultSet.getString("name"));
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return abstractDataDict;
    }
}
