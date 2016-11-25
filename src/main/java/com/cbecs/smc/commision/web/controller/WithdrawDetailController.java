package com.cbecs.smc.commision.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbecs.smc.commision.model.WithdrawDetail;
import com.cbecs.smc.commision.service.inf.WithdrawDetailService;


@Controller
public class WithdrawDetailController
{
	@Autowired
	private WithdrawDetailService withdrawDetailService;
    
    @GetMapping(value = "/withdrawdetailList")
    @ResponseBody
    public List<WithdrawDetail> getList(WithdrawDetail model)
    {
        return withdrawDetailService.getList(model);
    }
}