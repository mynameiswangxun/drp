package drp.basedata.manager;

import drp.basedata.domain.FiscalTime;
import drp.util.pagemodel.PageModel;

public interface FiscalTimeManager {
    PageModel<FiscalTime> findFiscalList(int pageNo, int pageSize);
    FiscalTime findFiscalTimeById(int id);
    void modifyFiscalTime(FiscalTime fiscalTime);
    void addFiscalTime(FiscalTime fiscalTime);
}
