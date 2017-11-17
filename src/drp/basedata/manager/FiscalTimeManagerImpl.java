package drp.basedata.manager;

import drp.basedata.dao.FiscalTimeDao;
import drp.basedata.domain.FiscalTime;
import drp.util.database.DBUtil;
import drp.util.exception.ApplicationException;
import drp.util.factory.BeanFactory;
import drp.util.pagemodel.PageModel;

import java.sql.Connection;

public class FiscalTimeManagerImpl implements FiscalTimeManager {
    FiscalTimeDao fiscalTimeDao = null;
    public FiscalTimeManagerImpl(){
        BeanFactory beanFactory = BeanFactory.getInstance();
        fiscalTimeDao = (FiscalTimeDao) beanFactory.getDaoObject(FiscalTimeDao.class);
    }
    @Override
    public PageModel<FiscalTime> findFiscalList(int pageNo, int pageSize) {
        Connection connection = DBUtil.getConnection();
        PageModel<FiscalTime> pageModel = null;
        try{
            pageModel = fiscalTimeDao.findFiscalList(connection,pageNo,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("查询会计核算期失败!");
        }finally {
            DBUtil.closeConnection(connection);
        }
        return pageModel;
    }
}
