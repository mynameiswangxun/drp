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

    @Override
    public FiscalTime findFiscalTimeById(int id) {
        Connection connection = DBUtil.getConnection();
        FiscalTime fiscalTime = null;
        try{
            fiscalTime = fiscalTimeDao.findFiscalTimeById(connection,id);
        }catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("查找会计核算期失败!");
        } finally {
            DBUtil.closeConnection(connection);
        }

        return fiscalTime;
    }

    @Override
    public void modifyFiscalTime(FiscalTime fiscalTime) {
        Connection connection = DBUtil.getConnection();
        try{
            fiscalTimeDao.modifyFiscalTimeDao(connection,fiscalTime);
        } catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("修改会计核算期失败!");
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void addFiscalTime(FiscalTime fiscalTime) {
        Connection connection = DBUtil.getConnection();
        try {
            fiscalTimeDao.addFiscalTimeDao(connection,fiscalTime);
        } catch (Exception e){
            e.printStackTrace();
            throw new ApplicationException("增加会计核算期失败!");
        } finally {
            DBUtil.closeConnection(connection);
        }
    }
}
