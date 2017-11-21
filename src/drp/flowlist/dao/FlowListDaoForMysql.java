package drp.flowlist.dao;

import drp.flowlist.domain.FlowDetail;
import drp.flowlist.domain.FlowList;
import drp.util.exception.DaoException;

import java.util.Date;
import java.util.List;

public class FlowListDaoForMysql implements FlowListDao{

    @Override
    public String generateFlowListNum() throws DaoException {
        return null;
    }

    @Override
    public void addFlowList(String flowListNum, FlowList flowList) throws DaoException {

    }

    @Override
    public void addFlowDetail(String flowListNum, List<FlowDetail> flowDetailList) throws DaoException {

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
