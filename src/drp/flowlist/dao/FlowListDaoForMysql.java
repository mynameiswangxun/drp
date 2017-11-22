package drp.flowlist.dao;

import drp.flowlist.domain.FlowDetail;
import drp.flowlist.domain.FlowList;
import drp.util.database.ConnectionManager;
import drp.util.database.DBUtil;
import drp.util.exception.DaoException;

import java.sql.*;
import java.text.SimpleDateFormat;
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
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
        String sql = "INSERT INTO flow_detail (flow_list_num, items_id, aim_client_id, op_num,adjust_flag) VALUES (?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (FlowDetail flowDetail:
                 flowDetailList) {
                preparedStatement.setString(1,flowDetail.getFlowList().getFlowNum());
                preparedStatement.setString(2,flowDetail.getItem().getItemId());
                preparedStatement.setInt(3,flowDetail.getAimClient().getId());
                preparedStatement.setBigDecimal(4,flowDetail.getOpNum());
                preparedStatement.setString(5,flowDetail.getAdjustFlag());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delFlowList(String[] flowListNum) throws DaoException {

    }

    @Override
    public void delFlowDetail(String[] flowListNum) throws DaoException {

    }

    @Override
    public void modifyFlowList(String flowListNum, FlowList flowList) throws DaoException {

    }

    @Override
    public void modifyFlowDetail(String flowListNum, List<FlowDetail> flowDetailList) throws DaoException {

    }

    @Override
    public List<FlowList> findFlowLists(int pageNo, int pageSize, String clientId, Date beginDate, Date endDate) throws DaoException {
        return null;
    }

    @Override
    public int findCountOfFlowList(String clientId, Date beginDate, Date endDate) throws DaoException {
        return 0;
    }

    @Override
    public void auditFlowCard(String[] flowListNum) throws DaoException {

    }

    @Override
    public FlowList findFlowListById(String flowListNum) throws DaoException {
        return null;
    }

    @Override
    public List<FlowDetail> findFlowDetailById(String flowListNum) throws DaoException {
        return null;
    }
}
