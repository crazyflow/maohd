package com.cbecs.smc.commision.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.AccountStatementPageable;
import com.cbecs.smc.commision.persistence.inf.AccountStatementMapper;
import com.cbecs.smc.commision.service.inf.AccountStatementService;


@Service
public class AccountStatementServiceImpl implements AccountStatementService
{   
    @Autowired
    private AccountStatementMapper accountStatementMapper;
    
    @Override
    public void create(AccountStatement model)
    {        
        accountStatementMapper.insert(model);
    }

    @Override
    public void edit(AccountStatement model)
    {
        accountStatementMapper.update(model);
    }

    @Override
    public List<AccountStatement> getListByPage(AccountStatementPageable p)
    {
        return accountStatementMapper.getListByPage(p);
    }

    @Override
    public List<AccountStatement> getList(AccountStatement p)
    {
        return accountStatementMapper.getList(p);
    }
    
}
