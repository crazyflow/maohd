package com.cbecs.smc.commision.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.framework.web.session.CurrentUser;
import com.cbecs.framework.web.session.SessionRepertory;
import com.cbecs.smc.commision.model.WithdrawApplication;
import com.cbecs.smc.commision.model.WithdrawApplicationPageable;
import com.cbecs.smc.commision.persistence.inf.WithdrawApplicationMapper;
import com.cbecs.smc.commision.service.inf.WithdrawApplicationService;

@Service
public class WithdrawApplicationServiceImpl implements WithdrawApplicationService
{
    @Autowired
    private WithdrawApplicationMapper withdrawApplicationMapper;
    
    @Autowired
    private SessionRepertory sessionRepertory;

    @Override
    public List<WithdrawApplication> getList(WithdrawApplication model)
    {
        return withdrawApplicationMapper.selectList(model);
    }

    @Override
    public List<WithdrawApplication> getListByPage(WithdrawApplicationPageable model)
    {
        return withdrawApplicationMapper.selectListByPage(model);
    }

    @Override
    public double getCurrencyDailyRate(int currencyId)
    {
        return withdrawApplicationMapper.getCurrencyDailyRate(currencyId);
    }

    @Override
    public String insertWithdrawApplication(WithdrawApplication model)
    {
        CurrentUser user = sessionRepertory.get();
        model.setDeleted(false);
        model.setCreatedBy(user.getId());
        model.setCreatedByName(user.getName());
        model.setCreatedDate(new Date());
        model.setUpdatedBy(user.getId());
        model.setUpdatedByName(user.getName());
        model.setUpdatedDate(new Date());
        withdrawApplicationMapper.insertWithdrawApplication(model);
        return "";
    }

    @Override
    public WithdrawApplication selectwithdrawById(String applicationId)
    {
        return withdrawApplicationMapper.selectwithdrawById(applicationId);
    }

    @Override
    public String deleteWithdrawApplication(String applicationId)
    {
        WithdrawApplication model = new WithdrawApplication();
        model.setApplicationId(applicationId);
        model.setDeleted(true);
        withdrawApplicationMapper.deleteWithdrawApplication(model);
        return "";
    }

    @Override
    public String updateWithdrawApplication(WithdrawApplication model)
    {
        CurrentUser user = sessionRepertory.get();
        model.setDeleted(false);
        model.setUpdatedBy(user.getId());
        model.setUpdatedByName(user.getName());
        model.setUpdatedDate(new Date());
        withdrawApplicationMapper.updateWithdrawApplication(model);
        return "";
    }
    
    @Override
    public String updateWithdrawApplicationWithStatus(WithdrawApplication model)
    {
        CurrentUser user = sessionRepertory.get();
        model.setUpdatedBy(user.getId());
        model.setUpdatedByName(user.getName());
        model.setUpdatedDate(new Date());
        withdrawApplicationMapper.updateWithdrawApplicationWithStatus(model);
        return "";
    }

    @Override
    public String getApplicationCode(String string)
    {
        return withdrawApplicationMapper.getApplicationCode("YJTQ");
    }

    @Override
    public String getStatusInfo(String workFlowIds)
    {
        return withdrawApplicationMapper.getStatusInfo(workFlowIds);
    }

    @Override
    public List<WithdrawApplication> getHistoryAccounts(WithdrawApplication w)
    {
        return withdrawApplicationMapper.getHistoryAccounts(w);
    }
  
}
