package drp.flowlist.manager;

import drp.flowlist.domain.FlowDetail;
import drp.flowlist.domain.FlowList;
import drp.util.exception.ApplicationException;
import drp.util.pagemodel.PageModel;

import java.util.Date;
import java.util.List;

public interface FlowListManager {
    /**
     * 增加流向单
     * @param flowList
     * @throws ApplicationException
     */
    void addFlowList(FlowList flowList) throws ApplicationException;

    /**
     * 删除流向单
     * @param flowCardNum
     * @throws ApplicationException
     */
    void delFlowList(String[] flowCardNum) throws ApplicationException;

    /**
     * 修改流向单
     * @param flowNum
     * @param flowDetails
     * @throws ApplicationException
     */
    void modifyFlowList(String flowNum,List<FlowDetail> flowDetails) throws ApplicationException;

    /**
     * 条件分页查询流向单
     * @param pageNo
     * @param pageSize
     * @param clientId
     * @param beginDate
     * @param endDate
     * @return
     * @throws ApplicationException
     */
    PageModel<FlowList> findFlowLists(int pageNo, int pageSize, String clientId, Date beginDate, Date endDate) throws ApplicationException;

    /**
     * 送审流向单
     * @param flowCardNum
     * @throws ApplicationException
     */
    void auditFlowList(String[] flowCardNum) throws ApplicationException;

    /**
     * 查询流向单
     * @param flowCardNum
     * @return
     * @throws ApplicationException
     */
    FlowList findFlowList(String flowCardNum) throws ApplicationException;

}
