package com.cbecs.smc.commision.service.inf;
import java.util.List;

import com.cbecs.smc.commision.model.WithdrawApplication;
import com.cbecs.smc.commision.model.WithdrawApplicationPageable;


public interface WithdrawApplicationService
{    
    List<WithdrawApplication> getList(WithdrawApplication model);
    
    List<WithdrawApplication> getListByPage(WithdrawApplicationPageable model);
    
    double getCurrencyDailyRate(int currencyId);

    String insertWithdrawApplication(WithdrawApplication model);

    WithdrawApplication selectwithdrawById(String applicationId);

    String deleteWithdrawApplication(String applicationId);

    String updateWithdrawApplication(WithdrawApplication model);

    String getApplicationCode(String string);

    String getStatusInfo(String workFlowIds);

    String updateWithdrawApplicationWithStatus(WithdrawApplication model);

     List<WithdrawApplication> getHistoryAccounts(WithdrawApplication w);
    
}
