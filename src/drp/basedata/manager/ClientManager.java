package drp.basedata.manager;

import drp.basedata.domain.Client;
import drp.util.database.DBUtil;
import drp.util.datadict.domain.ClientLevel;
import drp.util.datadict.manager.DataDictManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                ClientLevel clientLevel = null;
                if(!(resultSet.getString("client_level_id")==null||"".equals(resultSet.getString("client_level_id")))){
                    DataDictManager dataDictManager = DataDictManager.getInstance();
                    clientLevel =(ClientLevel)dataDictManager.findAbstractDataDictById(resultSet.getString("client_level_id"));
                }
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
        String sql="UPDATE client SET name=?,client_level_id=?,bank_account=?,contact_tel=?,address=?,zip_code=?,is_leaf=?,is_client=? WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,clientOrArea.getName());
            preparedStatement.setString(2,clientOrArea.getClientLevel()==null?null:clientOrArea.getClientLevel().getId());
            preparedStatement.setString(3,clientOrArea.getBankAccount());
            preparedStatement.setString(4,clientOrArea.getContactTel());
            preparedStatement.setString(5,clientOrArea.getAddress());
            preparedStatement.setString(6,clientOrArea.getZipCode());
            preparedStatement.setString(7,clientOrArea.getIsLeaf());
            preparedStatement.setString(8,clientOrArea.getIsClient());
            preparedStatement.setInt(9,clientOrArea.getId());
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
        String sql = "INSERT INTO client(id, pid, client_level_id, name, client_id, bank_account, contact_tel, address, zip_code, is_leaf, is_client)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,clientOrArea.getId());
            preparedStatement.setInt(2,clientOrArea.getPid());
            preparedStatement.setString(3,clientOrArea.getClientLevel()==null?null:clientOrArea.getClientLevel().getId());
            preparedStatement.setString(4,clientOrArea.getName());
            preparedStatement.setString(5,clientOrArea.getClientId());
            preparedStatement.setString(6,clientOrArea.getBankAccount());
            preparedStatement.setString(7,clientOrArea.getContactTel());
            preparedStatement.setString(8,clientOrArea.getAddress());
            preparedStatement.setString(9,clientOrArea.getZipCode());
            preparedStatement.setString(10,clientOrArea.getIsLeaf());
            preparedStatement.setString(11,clientOrArea.getIsClient());
            preparedStatement.executeUpdate();

            //把是叶子的父节点转为非叶子
            Client parent = findClientOrAreaById(clientOrArea.getPid());
            if("Y".equals(parent.getIsLeaf())){
                parent.setIsLeaf("N");
                modifyClientOrArea(parent);
            }

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
        String sql = "SELECT * FROM client WHERE is_client='N' and name=?";
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
     * 根据id删除单个分销商或区域
     * @param id
     */
    private void delClientOrReginNode(int id){
        String sql = "DELETE FROM client WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }
    }

    /**
     * 根据id删除整个树
     * @param id
     */
    private void delClientTree(int id){
        if(findClientOrAreaById(id)!=null){
            List<Integer> childrenId = findIdListByPid(id);
            for (int kid:
                    childrenId) {
                delClientTree(kid);
            }
            delClientOrReginNode(id);
        }
    }

    /**
     * 根据pid找到子结点的集合
     * @param pid
     * @return
     */
    public List<Integer> findIdListByPid(int pid){
        String sql = "SELECT id FROM client WHERE pid=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> list = new ArrayList<Integer>();
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 删除整个区域
     */
    public void delClientOrArea(int id) {
        Client client = findClientOrAreaById(id);
        int pid = client.getPid();
        delClientTree(id);
        List<Integer> parentsChildrenList = findIdListByPid(pid);
        if (parentsChildrenList.size() == 0) {
            Client parent = findClientOrAreaById(pid);
            parent.setIsLeaf("Y");
            modifyClientOrArea(parent);
        }
    }
}
