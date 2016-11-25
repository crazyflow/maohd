package com.cbecs.smc.commision.service.inf;
import java.util.List;

import com.cbecs.smc.commision.model.WithdrawApplication;
import com.cbecs.smc.commision.model.WithdrawDetail;


public interface WithdrawDetailService
{
    String insertWithdrawDetail(WithdrawApplication model);
    
    List<WithdrawDetail> getList(WithdrawDetail model);

    List<WithdrawDetail> selectWithdrawDetailById(String applicationId);

    String deleteWithdrawDetail(String applicationId);

    String updateWithdrawDetail(List<WithdrawDetail> details);
}
