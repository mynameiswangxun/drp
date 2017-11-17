package drp.basedata.dao;

import drp.basedata.domain.FiscalTime;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;

public interface FiscalTimeDao {
    /**
     * 添加会计核算期
     * @param connection
     * @param fiscalTime
     */
    void addFiscalTimeDao(Connection connection, FiscalTime fiscalTime);

    /**
     * 批量删除会计核算期
     * @param connection
     * @param ids
     */
    void delFiscalTimeDao(Connection connection,String[] ids);

    /**
     * 修改会计核算期
     * @param connection
     * @param fiscalTime
     */
    void modifyFiscalTimeDao(Connection connection,FiscalTime fiscalTime);

    /**
     * 通过id查找会计核算期
     * @param id
     * @return
     */
    FiscalTime findFiscalTimeById(Connection connection,String id);

    /**
     * 分页查询
     * @param connection
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageModel<FiscalTime> findFiscalList(Connection connection, int pageNo,int pageSize);
}
