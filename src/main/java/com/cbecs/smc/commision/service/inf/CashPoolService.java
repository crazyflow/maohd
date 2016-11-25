package com.cbecs.smc.commision.service.inf;

import java.util.List;

import com.cbecs.smc.commision.model.CashPool;
import com.cbecs.smc.commision.model.CashPoolPageable;
import com.cbecs.smc.commision.model.WithdrawApplication;

public interface CashPoolService
{
    List<CashPoolPageable> getListByPage(CashPoolPageable p);

    void updateCashPoolBywithDraw(WithdrawApplication model);

    void updateCashPoolByAudit(WithdrawApplication withdrawApplication);

    void updateCashPoolByAuditNo(WithdrawApplication withdrawApplication);

    CashPool getCashPoolById(String channelId);

    /**
     * 更改佣金台账自由佣金池及总佣金池
     */
    int editCashPoolFree(CashPool model);

    /**
     * 新增佣金台账
     * 
     * @param model
     */
    void createCashPool(CashPool model);
}
