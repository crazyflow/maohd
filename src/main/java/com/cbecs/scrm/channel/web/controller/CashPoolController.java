package com.cbecs.scrm.channel.web.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.framework.web.view.ExcelView;
import com.cbecs.scrm.channel.model.ConsignerAccountSettlement;
import com.cbecs.scrm.channel.model.ConsignerAccountSettlementPageable;
import com.cbecs.scrm.channel.model.ConsignerCashPool;
import com.cbecs.scrm.channel.model.ConsignerCashPoolPageable;
import com.cbecs.scrm.channel.service.inf.SettlementManagermentService;

@Controller(value="channelsCashPool")
@RequestMapping(value = "/channels")
public class CashPoolController
{

    @Autowired
    private SettlementManagermentService settlementManagermentService;
        
    @GetMapping(value = "/consigner-cash-pools")
    public ModelAndView getConsignerCashPools()
    {
        return new ModelAndView("/scrm/channel/consigner_cash_pool_list");
    }

    @GetMapping(value = "/consigner-cash-pools", params = "format=table")
    public ModelAndView getConsignerCashPoolsTable(ConsignerCashPoolPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("asc");
        sort.setProperty("companyName");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<ConsignerCashPool> t = settlementManagermentService.getConsignerCashPoolsByPage(p);

        ModelAndView view = new ModelAndView("/scrm/channel/consigner_cash_pool_table");
        view.addObject("consignerCashPoolsList", t);
        view.addObject("page", p);
        
        return view;
    }
    
    /*客户台帐详情*/
    @GetMapping(value = "/consigner-account-settlements/{companyId}")
    public ModelAndView getConsignerAccountSettlements(@PathVariable(value="companyId") String companyId)
    {
        ConsignerCashPool c = settlementManagermentService.getConsignerCashPoolByCompanyId(companyId);
        ModelAndView view = new ModelAndView("/scrm/channel/consigner_account_settlement_list");
        view.addObject("consignerCashPool", c);
        return view;
    }

    @GetMapping(value = "/consigner-account-settlements", params = "format=table")
    public ModelAndView getConsignerAccountSettlementsTable(ConsignerAccountSettlementPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("sortNum");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<ConsignerAccountSettlement> t = settlementManagermentService.getConsignerAccountSettlementsByPage(p);

        ModelAndView view = new ModelAndView("/scrm/channel/consigner_account_settlement_table");
        view.addObject("consignerAccountSettlementList", t);
        view.addObject("page", p);
        
        return view;
    }
    
    @GetMapping(value = "/consigner-account-settlements/excel")
    @ResponseBody
    public ModelAndView getConsignerCashPoolsExcel(ModelMap model, HttpServletRequest request,
            HttpServletResponse response, ConsignerAccountSettlement p)
    {
        List<ConsignerAccountSettlement> t = settlementManagermentService.getConsignerAccountSettlements(p);

        String exportTitle = "客户资金台账--" + p.getCompanyName();
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        String[] colNamesN = null;
        HashMap<String, Object> map;
        NumberFormat nf = new DecimalFormat("#,##0.00");
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

        String colNames[] =
        { "createTime", "exacctName", "billNo", "orderCode", "foreignAmount", "currencyName",
                "rate", "raeType", "amount", "accountBalance"};
        colNamesN = colNames;

        map = new HashMap<String, Object>();
        map.put("createTime", "发生日期");
        map.put("exacctName", "费用科目");
        map.put("billNo", "业务单据号");
        map.put("orderCode", "订单号");
        map.put("foreignAmount", "金额");
        map.put("currencyName", "币种");
        
        map.put("rate", "汇率");
        map.put("raeType", "收支类型");
        map.put("amount", "金额(CNY)");
        map.put("accountBalance", "可用余额(CNY)");

        list_export.add(map);
        for (ConsignerAccountSettlement consignerCashPool : t)
        {
            map = new HashMap<String, Object>();
            map.put("createTime", consignerCashPool.getCreateTime()!=null?date.format(consignerCashPool.getCreateTime()):"");
            map.put("exacctName", consignerCashPool.getExacctName());
            map.put("billNo", consignerCashPool.getBillNo());
            map.put("orderCode", consignerCashPool.getOrderCode());
            map.put("foreignAmount", consignerCashPool.getForeignAmount()!=0?nf.format(consignerCashPool.getForeignAmount()):"");
            map.put("currencyName", consignerCashPool.getCurrencyName());
            
            map.put("rate", consignerCashPool.getRate()!=0?nf.format(consignerCashPool.getRate()):"");
            map.put("raeType", consignerCashPool.getRaeType());
            map.put("amount", consignerCashPool.getAmount()!=0?nf.format(consignerCashPool.getAmount()):"");
            map.put("accountBalance", consignerCashPool.getAccountBalance()!=0?nf.format(consignerCashPool.getAccountBalance()):"");
            list_export.add(map);
        }

        ExcelView ExcelView = new ExcelView(exportTitle, list_export, colNamesN);
        return new ModelAndView(ExcelView, model);
    }
    
}
