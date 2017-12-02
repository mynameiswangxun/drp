package drp.flowlist.dao;

import drp.flowlist.domain.FlowDetail;
import drp.flowlist.domain.FlowList;
import drp.util.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface FlowListDao {
    /**
     * 生成流向单号
     * @return
     */
     String generateFlowListNum() throws DaoException;

    /**
     * 增加流向单
     * @param flowListNum
     * @param flowList
     */
    void addFlowList(String flowListNum, FlowList flowList) throws DaoException;

    /**
     * 增加流向单明细
     * @param flowListNum
     * @param flowDetailList
     * @throws DaoException
     */
    void addFlowDetail(String flowListNum, List<FlowDetail> flowDetailList) throws DaoException;

    /**
     * 删除流向单
     * @param flowListNum
     * @throws DaoException
     */
    void delFlowList(String[] flowListNum) throws DaoException;

    /**
     * 删除流向单明细
     * @param flowListNum
     * @throws DaoException
     */
    void delFlowDetail(String[] flowListNum) throws DaoException;

    /**
     *修改流向单
     * @param flowListNum
     * @param flowList
     * @throws DaoException
     */
    void modifyFlowList(String flowListNum, FlowList flowList) throws DaoException;

    /**
     * 修改流向单明细
     * @param flowListNum
     * @param flowDetailList
     * @throws DaoException
     */
    void modifyFlowDetail(String flowListNum,List<FlowDetail> flowDetailList) throws DaoException;

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param clientId
     * @param beginDate
     * @param endDate
     * @return
     * @throws DaoException
     */
    List<FlowList> findFlowLists(int pageNo, int pageSize, String clientId, Date beginDate,Date endDate) throws DaoException;

    /**
     * 查询记录数
     * @param clientId
     * @param beginDate
     * @param endDate
     * @return
     * @throws DaoException
     */
    int findCountOfFlowList(String clientId, Date beginDate,Date endDate) throws DaoException;

    /**
     * 送审流向单
     * @param flowListNum
     * @throws DaoException
     */
    void auditFlowCard(String[] flowListNum) throws DaoException;

    /**
     * 查询流向单
     * @param flowListNum
     * @return
     * @throws DaoException
     */
    FlowList findFlowListById(String flowListNum) throws DaoException;

    /**
     * 查询流向单明细
     * @param flowListNum
     * @return
     * @throws DaoException
     */
    List<FlowDetail> findFlowDetailById(String flowListNum) throws DaoException;

    /**
     * 获得满足条件的总数量
     * @param clientId
     * @param beginDate
     * @param date
     * @return
     */
    int getRecordCount(String clientId,Date beginDate,Date date);

}
