package drp.flowlist.dao;

import drp.basedata.manager.AimClientManager;
import drp.basedata.manager.ClientManager;
import drp.basedata.manager.FiscalTimeManagerImpl;
import drp.basedata.manager.ItemManagerImpl;
import drp.flowlist.domain.FlowDetail;
import drp.flowlist.domain.FlowList;
import drp.flowlist.manager.FlowListManagerImpl;
import drp.systemmgr.domain.User;
import drp.systemmgr.manager.UserManager;
import drp.util.database.ConnectionManager;
import drp.util.database.DBUtil;
import drp.util.exception.DaoException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlowListDaoForMysql implements FlowListDao{

    @Override
    public synchronized String generateFlowListNum() throws DaoException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String sql = "SELECT MAX(flow_num) FROM flow_list WHERE SUBSTRING(flow_num,1,8)=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String flowListNum = simpleDateFormat.format(new Date()) + "0001";

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,simpleDateFormat.format(new Date()));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if(resultSet.getLong(1)!=0){
                flowListNum = String.valueOf(resultSet.getLong(1)+1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("生成流向单序号失败!");
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return flowListNum;
    }

    @Override
    public void addFlowList(String flowListNum, FlowList flowList) throws DaoException {
        String sql = "INSERT INTO flow_list (flow_num, fiscal_year_period_id, client_id, op_date, recorder_id" +
                ", vou_sts, op_type)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,flowListNum);
            preparedStatement.setInt(2,flowList.getFiscalTime().getId());
            preparedStatement.setInt(3,flowList.getClient().getId());
            preparedStatement.setTimestamp(4,new Timestamp(flowList.getOpDate().getTime()));
            preparedStatement.setString(5,flowList.getRecorder().getId());
            preparedStatement.setString(6,flowList.getVouSts());
            preparedStatement.setString(7,flowList.getOpType());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("添加流向单失败！");
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }

    @Override
    public void addFlowDetail(String flowListNum, List<FlowDetail> flowDetailList) throws DaoException {
        String sql = "INSERT INTO flow_detail (flow_list_num, items_id, aim_client_id, op_num,adjust_flag) VALUES (?, ?, ?, ?,?);";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (FlowDetail flowDetail:
                 flowDetailList) {
                preparedStatement.setString(1,flowListNum);
                preparedStatement.setString(2,flowDetail.getItem().getItemId());
                preparedStatement.setInt(3,flowDetail.getAimClient().getId());
                preparedStatement.setBigDecimal(4,flowDetail.getOpNum());
                preparedStatement.setString(5,flowDetail.getAdjustFlag());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }
    }

    @Override
    public void delFlowList(String[] flowListNum) throws DaoException {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<flowListNum.length;i++){
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        String sql = "DELETE FROM flow_list WHERE flow_num IN ("+stringBuffer.toString()+")";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<flowListNum.length;i++){
                preparedStatement.setString(i+1,flowListNum[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }

    @Override
    public void delFlowDetail(String[] flowListNum) throws DaoException {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<flowListNum.length;i++){
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        String sql = "DELETE FROM flow_detail WHERE flow_list_num IN ("+stringBuffer.toString()+")";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<flowListNum.length;i++){
                preparedStatement.setString(i+1,flowListNum[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }

    @Override
    public void modifyFlowList(String flowListNum, FlowList flowList) throws DaoException {

    }

    @Override
    public void modifyFlowDetail(String flowListNum, List<FlowDetail> flowDetailList) throws DaoException {

    }

    @Override
    public List<FlowList> findFlowLists(int pageNo, int pageSize, String clientId, Date beginDate, Date endDate) throws DaoException {
        String sql = "SELECT * FROM flow_list WHERE(client_id LIKE ? AND op_date BETWEEN ? AND ? AND vou_sts='N') limit ?,? ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlowList> flowLists = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+clientId+"%");
            preparedStatement.setTimestamp(2,new Timestamp(beginDate.getTime()));
            preparedStatement.setTimestamp(3,new Timestamp(endDate.getTime()));
            preparedStatement.setInt(4,(pageNo-1)*pageSize);
            preparedStatement.setInt(5,pageSize);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                FlowList flowList = new FlowList();
                flowList.setFlowNum(resultSet.getString("flow_num"));
                flowList.setFiscalTime(new FiscalTimeManagerImpl().findFiscalTimeById(resultSet.getInt("fiscal_year_period_id")));
                flowList.setClient(ClientManager.getInstance().findClientOrAreaById(resultSet.getInt("client_id")));
                flowList.setOpDate(resultSet.getDate("op_date"));
                flowList.setRecorder(UserManager.getInstance().findUserById(resultSet.getString("recorder_id")));
                flowList.setVouSts(resultSet.getString("vou_sts"));
                flowList.setAdjuster(UserManager.getInstance().findUserById(resultSet.getString("adjuster_id")));
                flowList.setAdjustDate(resultSet.getDate("adjust_date"));
                flowList.setSpotter(UserManager.getInstance().findUserById(resultSet.getString("spotter_id")));
                flowList.setSpotDate(resultSet.getDate("spot_date"));
                flowList.setSpotDesc(resultSet.getString("spot_desc"));
                flowList.setConfirmer(UserManager.getInstance().findUserById("confirmer_id"));
                flowList.setConfirmDate(resultSet.getDate("confirm_date"));
                flowList.setOpType(resultSet.getString("op_type"));
                flowLists.add(flowList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return flowLists;
    }


    @Override
    public void auditFlowCard(String[] flowListNum) throws DaoException {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<flowListNum.length;i++){
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        String sql = "UPDATE flow_list SET vou_sts='S' WHERE flow_num IN ("+stringBuffer.toString()+")";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<flowListNum.length;i++){
                preparedStatement.setString(i+1,flowListNum[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }


    }

    @Override
    public FlowList findFlowListById(String flowListNum) throws DaoException {
        FlowList flowList =null;
        String sql = "SELECT * FROM flow_list WHERE flow_num=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,flowListNum);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                flowList = new FlowList();
                flowList.setFlowNum(resultSet.getString("flow_num"));
                flowList.setFiscalTime(new FiscalTimeManagerImpl().findFiscalTimeById(resultSet.getInt("fiscal_year_period_id")));
                flowList.setClient(ClientManager.getInstance().findClientOrAreaById(resultSet.getInt("client_id")));
                flowList.setOpDate(resultSet.getDate("op_date"));
                flowList.setRecorder(UserManager.getInstance().findUserById(resultSet.getString("recorder_id")));
                flowList.setVouSts(resultSet.getString("vou_sts"));
                flowList.setAdjuster(UserManager.getInstance().findUserById(resultSet.getString("adjuster_id")));
                flowList.setAdjustDate(resultSet.getDate("adjust_date"));
                flowList.setSpotter(UserManager.getInstance().findUserById(resultSet.getString("spotter_id")));
                flowList.setSpotDate(resultSet.getDate("spot_date"));
                flowList.setSpotDesc(resultSet.getString("spot_desc"));
                flowList.setConfirmer(UserManager.getInstance().findUserById("confirmer_id"));
                flowList.setConfirmDate(resultSet.getDate("confirm_date"));
                flowList.setOpType(resultSet.getString("op_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }


        return flowList;
    }

    @Override
    public List<FlowDetail> findFlowDetailById(String flowListNum) throws DaoException {
        String sql = "SELECT * FROM flow_detail WHERE flow_list_num=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<FlowDetail> flowDetails = new ArrayList<>();

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,flowListNum);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                FlowDetail flowDetail = new FlowDetail();
                flowDetail.setItem(new ItemManagerImpl().findItemById(resultSet.getString("items_id")));
                flowDetail.setAimClient(AimClientManager.getInstance().findAimClientbyId(resultSet.getInt("aim_client_id")));
                flowDetail.setOpNum(resultSet.getBigDecimal("op_num"));
                flowDetail.setAdjustNum(resultSet.getBigDecimal("adjust_num"));
                flowDetail.setAdjustReason(resultSet.getString("adjust_reason"));
                flowDetail.setAdjustFlag(resultSet.getString("adjust_flag"));
                flowDetails.add(flowDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }

        return flowDetails;
    }

    @Override
    public int getRecordCount(String clientId, Date beginDate, Date endDate) {
        String sql = "SELECT COUNT(*) FROM flow_list WHERE(client_id LIKE ? AND op_date BETWEEN ? AND ?) ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FlowList> flowLists = new ArrayList<>();
        Connection connection = null;
        int result = 0;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+clientId+"%");
            preparedStatement.setTimestamp(2,new Timestamp(beginDate.getTime()));
            preparedStatement.setTimestamp(3,new Timestamp(endDate.getTime()));

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
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
