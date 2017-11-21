package drp.flowlist.manager;

import drp.flowlist.dao.FlowListDao;
import drp.flowlist.domain.FlowList;
import drp.util.exception.ApplicationException;
import drp.util.exception.DaoException;
import drp.util.factory.BeanFactory;
import drp.util.pagemodel.PageModel;

import java.util.Date;

public class FlowListManagerImpl implements FlowListManager {
    FlowListDao flowListDao = null;

    public FlowListManagerImpl() {
        flowListDao = (FlowListDao) BeanFactory.getInstance().getDaoObject(FlowListDao.class);
    }

    @Override
    public void addFlowList(FlowList flowList) throws ApplicationException {
        //生成流向单单号
        String flowListNum = flowListDao.generateFlowListNum();
        try {
            //添加流向单主信息
            flowListDao.addFlowList(flowListNum, flowList);
            //添加流向单明细信息
            flowListDao.addFlowDetail(flowListNum, flowList.getFlowDetails());
        } catch (DaoException e){
            throw new ApplicationException("添加流向单失败!");
        }
    }

    @Override
    public void delFlowList(String[] flowCardNum) throws ApplicationException {

    }

    @Override
    public void modifyFlowList(FlowList flowList) throws ApplicationException {

    }

    @Override
    public PageModel<FlowList> findFlowLists(int pageNo, int pageSize, String clientId, Date beginDate, Date endDate) throws ApplicationException {
        return null;
    }

    @Override
    public void auditFlowList(String[] flowCardNum) throws ApplicationException {

    }

    @Override
    public FlowList findFlowList(String flowCardNum) throws ApplicationException {
        return null;
    }
}
