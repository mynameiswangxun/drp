package drp.basedata.manager;

import drp.basedata.domain.Client;
import drp.util.database.DBUtil;
import drp.util.datadict.domain.ClientLevel;
import drp.util.datadict.manager.DataDictManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientManager {
    private static ClientManager instance =new ClientManager();
    private ClientManager(){}
    public static ClientManager getInstance(){
        return instance;
    }

    /**
     *返回HTML字符串
     * @return
     */
    public String getClientTreeHtmlString() {
        return new ClientTreeReader().getClientTreeHtmlString();
    }

    /**
     * 查询分销商或者区域
     * @param id
     * @return 若不存在返回空
     */
    public Client findClientOrAreaById(int id){
        String sql = "SELECT * FROM client left join data_dic on client.client_level_id=data_dic.id WHERE client.id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setPid(resultSet.getInt("pid"));
                client.setName(resultSet.getString("name"));
                client.setClientId(resultSet.getString("client_id"));
                client.setBankAccount(resultSet.getString("bank_account"));
                client.setContactTel(resultSet.getString("contact_tel"));
                client.setAddress(resultSet.getString("address"));
                client.setZipCode(resultSet.getString("zip_code"));
                client.setIsLeaf(resultSet.getString("is_leaf"));
                client.setIsClient(resultSet.getString("is_client"));
                ClientLevel clientLevel = (ClientLevel) DataDictManager.getInstance().
                        findAbstractDataDictById(resultSet.getString("client_level_id"));
                client.setClientLevel(clientLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }

        return client;
    }

    /**
     * 修改分销商或者区域
     * @param clientOrArea
     */
    public void modifyClientOrArea(Client clientOrArea){
        String sql="UPDATE client SET name=?,client_level_id=?,bank_account=?,contact_tel=?,address=?,zip_code=? WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,clientOrArea.getName());
            preparedStatement.setString(2,clientOrArea.getClientLevel().getId());
            preparedStatement.setString(3,clientOrArea.getBankAccount());
            preparedStatement.setString(4,clientOrArea.getContactTel());
            preparedStatement.setString(5,clientOrArea.getAddress());
            preparedStatement.setString(6,clientOrArea.getZipCode());
            preparedStatement.setInt(7,clientOrArea.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }
    }

    /**
     * 添加分销商或者区域
     * @param clientOrArea
     */
    public void addClientOrArea(Client clientOrArea){
        String sql = "INSERT INTO client(id,pid,client_level_id,name,client_id,bank_accout,contact_tel,address,zip_code,is_leaf,is_client) " +
                "VALUE (?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,clientOrArea.getId());
            preparedStatement.setInt(2,clientOrArea.getPid());
            preparedStatement.setString(3,clientOrArea.getClientLevel()==null?"":clientOrArea.getClientLevel().getId());
            preparedStatement.setString(4,clientOrArea.getName());
            preparedStatement.setString(5,clientOrArea.getClientId());
            preparedStatement.setString(6,clientOrArea.getBankAccount());
            preparedStatement.setString(7,clientOrArea.getContactTel());
            preparedStatement.setString(8,clientOrArea.getAddress());
            preparedStatement.setString(9,clientOrArea.getZipCode());
            preparedStatement.setString(10,clientOrArea.getIsLeaf());
            preparedStatement.setString(11,clientOrArea.getIsClient());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }

    /**
     * 根据分销商代码判断分销商是否存在
     * @param clientId
     * @return
     */
    public boolean isExistClientByClientId(String clientId){
        String sql = "SELECT * FROM client WHERE client_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,clientId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 区域名是已经存在
     * @param areaName
     * @return
     */
    public boolean isExistAreaName(String areaName){
        String sql = "SELECT * FROM client WHERE is_client='N',name=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,areaName);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 删除分销商或区域
     * @param id
     */
    public void delClientOrRegin(int id){

    }
}
