package com.cbecs.smc.commision.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.SettlementDetail;
import com.cbecs.smc.commision.persistence.inf.SettlementDetailMapper;
import com.cbecs.smc.commision.service.inf.SettlementDetailService;

@Service
public class SettlementDetailServiceImpl implements SettlementDetailService
{
    @Autowired
    private SettlementDetailMapper settlementDetailMapper;

    @Override
    public void removeSettlementDetailsByApplication(String applicationId)
    {
        settlementDetailMapper.deleteSettlementDetails(applicationId);
    }

    @Override
    public void createSettlementDetails(SettlementDetail model)
    {
        settlementDetailMapper.insertSettlementDetails(model);
    }

    @Override
    public List<SettlementDetail> getSettlementDetailByApplicationId(String applicationId)
    {
        return settlementDetailMapper.selectSettlementDetailByApplicationId(applicationId);
    }

    @Override
    public int getCountByOrderCode(String orderCode,String applicationId)
    {
        return settlementDetailMapper.selectCountByOrderCode(orderCode,applicationId);
    }

    @Override
    public List<AccountStatement> getStatementByApplicationId(String applicationId)
    {
        return settlementDetailMapper.selectStatementByApplicationId(applicationId);
    }

}
