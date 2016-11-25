package com.cbecs.smc.commision.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.smc.commision.model.WithdrawApplication;
import com.cbecs.smc.commision.model.WithdrawDetail;
import com.cbecs.smc.commision.persistence.inf.SettlementApplicationMapper;
import com.cbecs.smc.commision.persistence.inf.WithdrawDetailMapper;
import com.cbecs.smc.commision.service.inf.WithdrawDetailService;


@Service
public class WithdrawDetailServiceImpl implements WithdrawDetailService
{
    @Autowired
    private WithdrawDetailMapper withdrawDetailMapper;
    
    @Autowired
    private SettlementApplicationMapper settlementApplicationMapper;
    
    @Override
    public String insertWithdrawDetail(WithdrawApplication model)
    {
        for (WithdrawDetail withdrawDetail : model.getWithDrawdetails())
        {
            withdrawDetail.setDetailId(UUID.randomUUID().toString());
            withdrawDetail.setApplicationId(model.getApplicationId());
            withdrawDetailMapper.insertWithdrawDetail(withdrawDetail);
        }
        if(model.getStatus() != 1){
            for (WithdrawDetail w : model.getWithDrawdetails())
            {
                settlementApplicationMapper.updateSettlementApplication(w);
            }
        }
        return "";
    }

    @Override
    public List<WithdrawDetail> getList(WithdrawDetail model)
    {
        return withdrawDetailMapper.selectList(model);
    }

    @Override
    public List<WithdrawDetail> selectWithdrawDetailById(String applicationId)
    {
        return withdrawDetailMapper.selectWithdrawDetailById(applicationId);
    }

    @Override
    public String deleteWithdrawDetail(String applicationId)
    {
        withdrawDetailMapper.deleteWithdrawDetail(applicationId);
        return "";
    }

    @Override
    public String updateWithdrawDetail(List<WithdrawDetail> details)
    {
        for (WithdrawDetail withdrawDetail : details)
        {
            withdrawDetailMapper.deleteWithdrawDetail(withdrawDetail.getApplicationId());
        }
        return "";
    }
}
