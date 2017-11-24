package drp.basedata.manager;

import drp.basedata.domain.AimClient;
import drp.util.database.DBUtil;
import drp.util.datadict.manager.DataDictManager;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AimClientManager {
    private AimClientManager(){}

    private static AimClientManager aimClientManager = new AimClientManager();

    public static AimClientManager getInstance(){
        return aimClientManager;
    }

    /**
     * 条件查询
     * @param pageNo
     * @param pageSize
     * @param condition
     * @return
     */
    public PageModel<AimClient> findAimClientManagerListByCondition(int pageNo,int pageSize, String condition){
        String sql = "SELECT * FROM aim_view WHERE client_temi_id LIKE ? OR name LIKE ? LIMIT ?,? ";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        PageModel<AimClient> pageModel = new PageModel<AimClient>();
        List<AimClient> aimClients = new ArrayList<AimClient>();
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+condition+"%");
            preparedStatement.setString(2,"%"+condition+"%");
            preparedStatement.setInt(3,(pageNo-1)*pageSize);
            preparedStatement.setInt(4,pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                AimClient aimClient = new AimClient();
                aimClient.setId(resultSet.getInt("id"));
                aimClient.setClientID(resultSet.getString("client_temi_id"));
                aimClient.setLevelName(DataDictManager.getInstance().
                        findAbstractDataDictById(resultSet.getString("client_temi_level_id")).getName());
                aimClient.setName(resultSet.getString("name"));
                aimClients.add(aimClient);
            }
            pageModel.setTotalRecords(findTotalNumByCondition(connection,condition));
            pageModel.setPageNo(pageNo);
            pageModel.setPageSize(pageSize);
            pageModel.setList(aimClients);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return pageModel;
    }
    public int findTotalNumByCondition(Connection connection,String condition){
        String sql = "SELECT COUNT(*) FROM aim_view WHERE client_temi_id LIKE ? OR name LIKE ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+condition+"%");
            preparedStatement.setString(2,"%"+condition+"%");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return result;
    }
}
