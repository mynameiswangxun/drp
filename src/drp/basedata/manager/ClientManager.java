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
}
