package drp.basedata.manager;

import drp.basedata.domain.TemiClient;
import drp.util.database.DBUtil;
import drp.util.datadict.domain.TemiClientLevel;
import drp.util.datadict.manager.DataDictManager;
import drp.util.exception.ApplicationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemiClientManager {
    /**
     * 返回HTML字符串
     *
     * @return
     */
    public String getTemiClientTreeHtmlString() {
        return new TemiClientTreeReader().getClientTreeHtmlString();
    }

    /**
     * 根据ID查找终端客户或者区域
     *
     * @param id
     * @return
     */
    public TemiClient findTemiClientOrAreaById(int id) {
        String sql = "SELECT * FROM temi_client WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TemiClient temiClient = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                temiClient = new TemiClient();
                temiClient.setId(resultSet.getInt("id"));
                temiClient.setPid(resultSet.getInt("pid"));
                temiClient.setTemiClientId(resultSet.getString("temi_client_id"));
                temiClient.setName(resultSet.getString("name"));
                temiClient.setContactTel(resultSet.getString("contact_tel"));
                temiClient.setContactor(resultSet.getString("contactor"));
                temiClient.setAddress(resultSet.getString("address"));
                temiClient.setZipCode(resultSet.getString("zip_code"));
                temiClient.setIsLeaf(resultSet.getString("is_leaf"));
                temiClient.setIsTemiClient(resultSet.getString("is_temi_client"));
                DataDictManager dataDictManager = DataDictManager.getInstance();
                TemiClientLevel temiClientLevel =
                        (TemiClientLevel) dataDictManager.findAbstractDataDictById(resultSet.getString("temi_client_level_id"));
                temiClient.setTemiClientLevel(temiClientLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return temiClient;
    }

    /**
     * 添加终端客户或者地区
     *
     * @param temiClient
     */
    public void addTemiClientOrArea(TemiClient temiClient) {
        String sql = "INSERT INTO temi_client(id,pid,temi_client_level_id,name,temi_client_id,contact_tel,contactor,address,zip_code,is_leaf,is_temi_client)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, temiClient.getId());
            preparedStatement.setInt(2, temiClient.getPid());
            preparedStatement.setString(3, temiClient.getTemiClientLevel() == null ? null : temiClient.getTemiClientLevel().getId());
            preparedStatement.setString(4, temiClient.getName());
            preparedStatement.setString(5, temiClient.getTemiClientId());
            preparedStatement.setString(6, temiClient.getContactTel());
            preparedStatement.setString(7, temiClient.getContactor());
            preparedStatement.setString(8, temiClient.getAddress());
            preparedStatement.setString(9, temiClient.getZipCode());
            preparedStatement.setString(10, temiClient.getIsLeaf());
            preparedStatement.setString(11, temiClient.getIsTemiClient());
            preparedStatement.executeUpdate();

            TemiClient parents = findTemiClientOrAreaById(temiClient.getPid());
            if ("Y".equals(parents.getIsLeaf())) {
                parents.setIsLeaf("N");
                modifyTemiClientOrArea(parents);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("添加失败！");
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    /**
     * 通过TemiClientID判断TemIcLINET是否存在
     *
     * @return
     */
    public boolean isExistTemiClientByTemiClientId(String temiClientId) {
        String sql = "SELECT * FROM temi_client WHERE temi_client_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, temiClientId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return false;
    }

    /**
     * 修改终端客户或区域
     * @param temiClient
     */
    public void modifyTemiClientOrArea(TemiClient temiClient) {
        String sql = "UPDATE temi_client SET temi_client_level_id=?, name=?, temi_client_id=?," +
                " contact_tel=?, contactor=?, address=?, zip_code=?, is_leaf=?, is_temi_client=? WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, temiClient.getTemiClientLevel() == null ? null : temiClient.getTemiClientLevel().getId());
            preparedStatement.setString(2, temiClient.getName());
            preparedStatement.setString(3, temiClient.getTemiClientId());
            preparedStatement.setString(4, temiClient.getContactTel());
            preparedStatement.setString(5, temiClient.getContactor());
            preparedStatement.setString(6, temiClient.getAddress());
            preparedStatement.setString(7, temiClient.getZipCode());
            preparedStatement.setString(8, temiClient.getIsLeaf());
            preparedStatement.setString(9, temiClient.getIsTemiClient());
            preparedStatement.setInt(10, temiClient.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("修改失败!");
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    /**
     * 删除单个结点
     * @param id
     */
    private void deleteTemiClientOrAreaNodeById(Connection connection,int id) {
        String sql = "DELETE FROM temi_client WHERE id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }
    }

    /**
     * 寻找子节点的id
     * @param connection
     * @param pid
     */
    private List<Integer> findSonNode(Connection connection, int pid){
        String sql = "SELECT id FROM temi_client WHERE pid = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> result = new ArrayList<Integer>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pid);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return result;
    }

    /**
     * 删除整个树
     * @param connection
     * @param id
     */
    private void deleteTemiClientOrAreaTreeById(Connection connection, int id){
        for (int sid:
                findSonNode(connection,id)) {
            deleteTemiClientOrAreaTreeById(connection,sid);
        }
        deleteTemiClientOrAreaNodeById(connection,id);
    }

    /**
     * 删除终端客户或者区域
     * @param id
     */
    public void deleteTemiClientOrAreaById(int id){
        Connection connection = DBUtil.getConnection();
        try {
            TemiClient parents = findTemiClientOrAreaById(findTemiClientOrAreaById(id).getPid());
            deleteTemiClientOrAreaTreeById(connection,id);
            //如果父结点没有子结点，设置为叶子结点
            if(findSonNode(connection,parents.getId()).size()==0){
                parents.setIsLeaf("Y");
                modifyTemiClientOrArea(parents);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("删除失败");
        } finally {
            DBUtil.closeConnection(connection);
        }
    }
}

