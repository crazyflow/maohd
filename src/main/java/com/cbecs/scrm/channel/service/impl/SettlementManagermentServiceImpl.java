package com.cbecs.scrm.channel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.scrm.channel.model.ConsignerAccountSettlement;
import com.cbecs.scrm.channel.model.ConsignerAccountSettlementPageable;
import com.cbecs.scrm.channel.model.ConsignerCashPool;
import com.cbecs.scrm.channel.model.ConsignerCashPoolPageable;
import com.cbecs.scrm.channel.persistence.inf.SettlementManagermentMapper;
import com.cbecs.scrm.channel.service.inf.SettlementManagermentService;
import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.AccountStatementPageable;
import com.cbecs.smc.commision.model.CashPool;

@Service
public class SettlementManagermentServiceImpl implements SettlementManagermentService
{
    @Autowired
    private SettlementManagermentMapper settlementManagermentMapper;

    @Override
    public List<ConsignerCashPool> getConsignerCashPoolsByPage(ConsignerCashPoolPageable p)
    {
        return settlementManagermentMapper.getConsignerCashPoolsByPage(p);
    }

    @Override
    public List<ConsignerAccountSettlement> getConsignerAccountSettlementsByPage(ConsignerAccountSettlementPageable p)
    {
        return settlementManagermentMapper.getConsignerAccountSettlementsByPage(p);
    }

    @Override
    public List<ConsignerAccountSettlement> getConsignerAccountSettlements(ConsignerAccountSettlement p)
    {
        return settlementManagermentMapper.getConsignerAccountSettlements(p);
    }

    @Override
    public ConsignerCashPool getConsignerCashPoolByCompanyId(String companyId)
    {
        return settlementManagermentMapper.getConsignerCashPoolByCompanyId(companyId);
    }

    @Override
    public CashPool getCommisionCashPoolById(String channelId)
    {
        return settlementManagermentMapper.getCommisionCashPoolById(channelId);
    }

    @Override
    public List<AccountStatementPageable> getCommisionAccountStatementsByPage(AccountStatementPageable p)
    {
        return settlementManagermentMapper.getCommisionAccountStatementsByPage(p);
    }

    @Override
    public List<AccountStatement> getCommisionAccountStatements(AccountStatement p)
    {
        return settlementManagermentMapper.getCommisionAccountStatements(p);
    }
    
}
