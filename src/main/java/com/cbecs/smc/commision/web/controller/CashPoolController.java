package com.cbecs.smc.commision.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.smc.commision.model.CashPoolPageable;
import com.cbecs.smc.commision.service.inf.CashPoolService;

@Controller
@RequestMapping("/cash-pools")
public class CashPoolController
{
	@Autowired
	private CashPoolService cashPoolService;
	
	@GetMapping(value = "")
    public ModelAndView getCashPool()
    {
        return new ModelAndView("/smc/commission/cash_pool_list");
    }
    
    @GetMapping(value = "", params="format=table")
    public ModelAndView getCashPoolTable(CashPoolPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("updated_date");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<CashPoolPageable> t = cashPoolService.getListByPage(p);

        ModelAndView view = new ModelAndView("/smc/commission/cash_pool_table");
        view.addObject("accountList", t);
        view.addObject("page", p);
        return view;
    }
}