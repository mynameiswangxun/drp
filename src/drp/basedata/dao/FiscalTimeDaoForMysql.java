package drp.basedata.dao;

import drp.basedata.domain.FiscalTime;
import drp.util.database.DBUtil;
import drp.util.exception.ApplicationException;
import drp.util.pagemodel.PageModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FiscalTimeDaoForMysql implements FiscalTimeDao{
    @Override
    public void addFiscalTimeDao(Connection connection, FiscalTime fiscalTime) {
        String sql = "INSERT INTO fiscal_year_period(id,fiscal_year,fiscal_month,begin_date,end_date,status) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,fiscalTime.getId());
            preparedStatement.setInt(2,fiscalTime.getFiscalYear());
            preparedStatement.setInt(3,fiscalTime.getFiscalMonth());
            preparedStatement.setTimestamp(4,new Timestamp(fiscalTime.getBeginDate().getTime()));
            preparedStatement.setTimestamp(5,new Timestamp(fiscalTime.getEndDate().getTime()));
            preparedStatement.setString(6,fiscalTime.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("添加会计核算期失败!");
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }
    }

    @Override
    public void delFiscalTimeDao(Connection connection, int[] ids) {
        StringBuilder sql = new StringBuilder("DELETE FROM fiscal_year_period WHERE id in(");
        for(int i = 0; i<ids.length; i++){
            sql.append(ids[i]);
            sql.append(",");
        }
        sql.setCharAt(sql.length()-1,')');
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("删除会计核算期失败!");
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }

    @Override
    public void modifyFiscalTimeDao(Connection connection, FiscalTime fiscalTime) {
        String sql = "UPDATE fiscal_year_period SET fiscal_year=?,fiscal_month=?,begin_date=?,end_date=?,status=? WHERE id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,fiscalTime.getFiscalYear());
            preparedStatement.setInt(2,fiscalTime.getFiscalMonth());
            preparedStatement.setTimestamp(3,new Timestamp(fiscalTime.getBeginDate().getTime()));
            preparedStatement.setTimestamp(4,new Timestamp(fiscalTime.getEndDate().getTime()));
            preparedStatement.setString(5,fiscalTime.getStatus());
            preparedStatement.setInt(6,fiscalTime.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("修改会计核算期失败!");
        } finally {
            DBUtil.closeStatement(preparedStatement);
        }
    }

    @Override
    public FiscalTime findFiscalTimeById(Connection connection,int id) {
        String sql = "SELECT * FROM fiscal_year_period WHERE id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        FiscalTime fiscalTime = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                fiscalTime = new FiscalTime();
                fiscalTime.setId(resultSet.getInt("id"));
                fiscalTime.setFiscalYear(resultSet.getInt("fiscal_year"));
                fiscalTime.setFiscalMonth(resultSet.getInt("fiscal_month"));
                fiscalTime.setBeginDate(resultSet.getDate("begin_date"));
                fiscalTime.setEndDate(resultSet.getDate("end_date"));
                fiscalTime.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("查询会计核算期失败!");
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return fiscalTime;
    }

    @Override
    public PageModel<FiscalTime> findFiscalList(Connection connection, int pageNo, int pageSize) {
        String sql = "SELECT * FROM fiscal_year_period limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PageModel<FiscalTime> pageModel = new PageModel();
        List<FiscalTime> list = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,(pageNo-1)*pageSize);
            preparedStatement.setInt(2,pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                FiscalTime fiscalTime = new FiscalTime();
                fiscalTime.setId(resultSet.getInt("id"));
                fiscalTime.setFiscalYear(resultSet.getInt("fiscal_year"));
                fiscalTime.setFiscalMonth(resultSet.getInt("fiscal_month"));
                fiscalTime.setBeginDate(resultSet.getDate("begin_date"));
                fiscalTime.setEndDate(resultSet.getDate("end_date"));
                fiscalTime.setStatus(resultSet.getString("status"));
                list.add(fiscalTime);
            }
            pageModel.setList(list);
            pageModel.setPageSize(pageSize);
            pageModel.setPageNo(pageNo);
            pageModel.setTotalRecords(findTotalRecord(connection));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("查询会计核算期失败!");
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return pageModel;
    }

    private int findTotalRecord(Connection connection){
        String sql = "SELECT COUNT(*) FROM fiscal_year_period";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeResultSet(resultSet);
        }
        return result;
    }
}
