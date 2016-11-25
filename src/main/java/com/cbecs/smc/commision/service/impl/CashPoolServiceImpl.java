package com.cbecs.smc.commision.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.framework.web.session.CurrentUser;
import com.cbecs.framework.web.session.SessionRepertory;
import com.cbecs.smc.commision.model.CashPool;
import com.cbecs.smc.commision.model.CashPoolPageable;
import com.cbecs.smc.commision.model.WithdrawApplication;
import com.cbecs.smc.commision.persistence.inf.CashPoolMapper;
import com.cbecs.smc.commision.service.inf.CashPoolService;

@Service
public class CashPoolServiceImpl implements CashPoolService
{
    @Autowired
    private CashPoolMapper cashPoolMapper;

    @Autowired
    private SessionRepertory sessionRepertory;

    @Override
    public List<CashPoolPageable> getListByPage(CashPoolPageable p)
    {
        return cashPoolMapper.selectListByPage(p);
    }

    @Override
    public void updateCashPoolBywithDraw(WithdrawApplication model)
    {
        CurrentUser user = sessionRepertory.get();
        model.setUpdatedDate(new Date());
        model.setUpdatedBy(user.getId());
        model.setUpdatedByName(user.getName());
        cashPoolMapper.updateCashPoolBywithDraw(model);
    }

    @Override
    public void updateCashPoolByAudit(WithdrawApplication withdrawApplication)
    {
        CurrentUser user = sessionRepertory.get();
        withdrawApplication.setUpdatedDate(new Date());
        withdrawApplication.setUpdatedBy(user.getId());
        withdrawApplication.setUpdatedByName(user.getName());
        cashPoolMapper.updateCashPoolByAudit(withdrawApplication);
    }

    @Override
    public void updateCashPoolByAuditNo(WithdrawApplication withdrawApplication)
    {
        CurrentUser user = sessionRepertory.get();
        withdrawApplication.setUpdatedDate(new Date());
        withdrawApplication.setUpdatedBy(user.getId());
        withdrawApplication.setUpdatedByName(user.getName());
        cashPoolMapper.updateCashPoolByAuditNo(withdrawApplication);
    }

    @Override
    public CashPool getCashPoolById(String channelId)
    {
        return cashPoolMapper.getCashPoolById(channelId);
    }

    @Override
    public int editCashPoolFree(CashPool model)
    {

        return cashPoolMapper.updateCashPoolFree(model);
    }

    @Override
    public void createCashPool(CashPool model)
    {
        cashPoolMapper.insert(model);

    }
}
