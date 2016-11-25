package com.cbecs.smc.commision.service.inf;

import java.util.List;

import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.SettlementDetail;

public interface SettlementDetailService
{

    /**
     * 根据applicationId删除明细信息
     * 
     * @param applicationId
     */
    void removeSettlementDetailsByApplication(String applicationId);
    
    /**
     * 新增结算明细
     * @param model
     */
    void createSettlementDetails(SettlementDetail model);
    
    /**
     * 根据applicationId查询结算明细信息
     * @param applicationId
     * @return
     */
    List<SettlementDetail> getSettlementDetailByApplicationId(String applicationId);
    
    /**
     * 查询该订单是否存在SettlementDetail表
     * @param orderCode
     * @return
     */
    int getCountByOrderCode(String orderCode,String applicationId);
    
    /**
     * 根据applicationId 查询订单对应的台账流水信息
     * @param applicationId
     * @return
     */
    List<AccountStatement> getStatementByApplicationId(String applicationId);
    
}
