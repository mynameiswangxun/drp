package drp.flowlist.manager;

import drp.flowlist.dao.FlowListDao;
import drp.flowlist.domain.FlowDetail;
import drp.flowlist.domain.FlowList;
import drp.util.database.ConnectionManager;
import drp.util.exception.ApplicationException;
import drp.util.exception.DaoException;
import drp.util.factory.BeanFactory;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class FlowListManagerImpl implements FlowListManager {
    FlowListDao flowListDao = null;

    public FlowListManagerImpl() {
        flowListDao = (FlowListDao) BeanFactory.getInstance().getDaoObject(FlowListDao.class);
    }

    @Override
    public void addFlowList(FlowList flowList) throws ApplicationException {
        //取得connection
        Connection connection = ConnectionManager.getConnection();
        //开始事务
        ConnectionManager.beginTransaction(connection);
        //生成流向单单号
        String flowListNum = flowListDao.generateFlowListNum();
        try {
            //添加流向单主信息
            flowListDao.addFlowList(flowListNum, flowList);
            //添加流向单明细信息
            flowListDao.addFlowDetail(flowListNum, flowList.getFlowDetails());
            //提交事务
            ConnectionManager.commitTransaction(connection);
        } catch (DaoException e){
            e.printStackTrace();
            //回滚事务
            ConnectionManager.rollbackTransaction(connection);
            throw new ApplicationException("添加流向单失败!");
        } finally {
            ConnectionManager.closeConnection();
        }

    }

    @Override
    public void delFlowList(String[] flowCardNum) throws ApplicationException {
        Connection connection = null;
        FlowList flowList = null;
        try{
            connection = ConnectionManager.getConnection();
            flowListDao.delFlowDetail(flowCardNum);
            flowListDao.delFlowList(flowCardNum);

        }catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("删除失败!");
        } finally {
            ConnectionManager.closeConnection();
        }
    }

    @Override
    public void modifyFlowList(String flowNum,List<FlowDetail> flowDetails) throws ApplicationException {
        Connection connection = null;
        String[] flowNumTemp = new String[1];
        flowNumTemp[0] = flowNum;
        try{
            connection = ConnectionManager.getConnection();
           flowListDao.delFlowDetail(flowNumTemp);
           flowListDao.addFlowDetail(flowNum,flowDetails);

        }catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("修改失败!");
        } finally {
            ConnectionManager.closeConnection();
        }
    }

    @Override
    public PageModel<FlowList> findFlowLists(int pageNo, int pageSize, String clientId, Date beginDate, Date endDate) throws ApplicationException {
        Connection connection = null;
        PageModel<FlowList> pageModel = new PageModel<>();
        try{
            connection = ConnectionManager.getConnection();
            List<FlowList> list = flowListDao.findFlowLists(pageNo,pageSize,clientId,beginDate,endDate);
            int totalNum = flowListDao.getRecordCount(clientId,beginDate,endDate);

            pageModel.setPageNo(pageNo);
            pageModel.setPageSize(pageSize);
            pageModel.setTotalRecords(totalNum);
            pageModel.setList(list);

        }catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("查找失败!");
        } finally {
            ConnectionManager.closeConnection();
        }

        return pageModel;
    }

    @Override
    public void auditFlowList(String[] flowCardNum) throws ApplicationException {
        Connection connection = null;
        FlowList flowList = null;
        try{
            connection = ConnectionManager.getConnection();
            flowListDao.auditFlowCard(flowCardNum);

        }catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("送审失败!");
        } finally {
            ConnectionManager.closeConnection();
        }
    }


    @Override
    public FlowList findFlowList(String flowCardNum) throws ApplicationException {
        Connection connection = null;
        FlowList flowList = null;
        try{
            connection = ConnectionManager.getConnection();
            flowList = flowListDao.findFlowListById(flowCardNum);
            flowList.setFlowDetails(flowListDao.findFlowDetailById(flowCardNum));

        }catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("查找失败!");
        } finally {
            ConnectionManager.closeConnection();
        }
        return flowList;
    }
}
