package com.cbecs.smc.commision.web.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.framework.web.view.ExcelView;
import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.AccountStatementPageable;
import com.cbecs.smc.commision.model.CashPool;
import com.cbecs.smc.commision.service.inf.AccountStatementService;
import com.cbecs.smc.commision.service.inf.CashPoolService;

@Controller
@RequestMapping("/account-statements")
public class AccountStatementController
{
    @Autowired
    private AccountStatementService accountStatementService;

    @Autowired
    private CashPoolService cashPoolService;

    @GetMapping(value = "")
    public ModelAndView getAccountStatement(String channelId)
    {
        CashPool c = cashPoolService.getCashPoolById(channelId);
        ModelAndView view = new ModelAndView("/smc/commission/account_statement_list");
        view.addObject("cashPool", c);
        return view;
    }

    @GetMapping(value = "", params = "format=table")
    public ModelAndView getAccountStatementTable(AccountStatementPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("created_date");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<AccountStatement> t = new ArrayList<AccountStatement>();
        if (p.getChannelId() != "")
        {
            t = accountStatementService.getListByPage(p);
        }

        ModelAndView view = new ModelAndView("/smc/commission/account_statement_table");
        view.addObject("accountDetailList", t);
        view.addObject("page", p);
        return view;
    }

    @GetMapping(value = "/excel")
    public ModelAndView getAccountStatementExport(ModelMap model, HttpServletRequest request, HttpServletResponse response, AccountStatement p, String totalAmount, String freeAmount)
    {
        List<AccountStatement> t = new ArrayList<AccountStatement>();
        if (p.getChannelId() != "")
        {
            t = accountStatementService.getList(p);
        }

        String exportTitle = p.getChannelName() + " 合计(CNY)" + totalAmount + " 自由佣金池(CNY)" + freeAmount;
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        String[] colNamesN = null;
        HashMap<String, Object> map;
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

        String colNames[] =
        { "createdDate", "capacity1Name", "documentCode", "orderCode", "foreignAmount", "currencyCode", "rate", "raeType", "amount" };
        colNamesN = colNames;

        map = new HashMap<String, Object>();
        map.put("createdDate", "发生日期");
        map.put("capacity1Name", "费用科目");
        map.put("documentCode", "业务单据号");
        map.put("orderCode", "订单号");
        map.put("foreignAmount", "金额");
        map.put("currencyCode", "币种");

        map.put("rate", "汇率");
        map.put("raeType", "收支类型");
        map.put("amount", "金额(CNY)");
        NumberFormat nf = new DecimalFormat("#,###.00");
        NumberFormat nf2 = new DecimalFormat("#,###.0000");
        list_export.add(map);
        for (AccountStatement accountStatement : t)
        {
            map = new HashMap<String, Object>();
            map.put("createdDate", accountStatement.getCreatedDate() != null ? date.format(accountStatement.getCreatedDate()) : "");
            map.put("capacity1Name", accountStatement.getCapacity1Name());
            map.put("documentCode", accountStatement.getDocumentCode());
            map.put("orderCode", accountStatement.getOrderCode());
            map.put("foreignAmount", accountStatement.getForeignAmount() != 0 ? nf.format(accountStatement.getForeignAmount()) : "");
            map.put("currencyCode", accountStatement.getCurrencyCode());

            map.put("rate",accountStatement.getRate() != 0 ? nf2.format(accountStatement.getRate()) : "");
            
            String res = "";
            if(accountStatement.getRaeType() == 1){
                res = "收入";
            }else if(accountStatement.getRaeType() == 2){
                res = "支出";
            }else if(accountStatement.getRaeType() == 3){
                res = "冻结";
            }else{
                res = "取消冻结";
            }
            map.put("raeType", res);
            map.put("amount", accountStatement.getAmount()  != 0 ? nf.format(accountStatement.getAmount() ) : "");
            list_export.add(map);
        }

        ExcelView ExcelView = new ExcelView(exportTitle, list_export, colNamesN);
        return new ModelAndView(ExcelView, model);
    }
}