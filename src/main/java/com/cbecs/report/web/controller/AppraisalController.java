package com.cbecs.report.web.controller;

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
import com.cbecs.report.model.DeptPerfPageableReport;
import com.cbecs.report.model.DeptPerfQueryReport;
import com.cbecs.report.model.DeptPerfReport;
import com.cbecs.report.service.inf.AppraisalService;

@Controller
@RequestMapping(value = "/appraisal")
public class AppraisalController
{
    @Autowired
    private AppraisalService appraisalService;

    /**
     * 业务部门考核页面
     * 
     * @return
     */
    @GetMapping(value = "/dept-perf")
    public ModelAndView getDeptPerf()
    {
        ModelAndView view = new ModelAndView("/report/appraisal/dept_perf_list");
        return view;
    }

    /**
     * 业务部门数据展示
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/dept-perf", params = "format=table")
    public ModelAndView getDeptPerfInTable(DeptPerfPageableReport model)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("Code");
        model.setPageSize(10);
        listSort.add(sort);
        model.setSort(listSort);
        List<DeptPerfReport> list = appraisalService.getDeptPerfByPage(model);
        ModelAndView view = new ModelAndView("/report/appraisal/dept_perf_table");
        view.addObject("deptPerf", list);
        view.addObject("page", model);
        return view;
    }

    /**
     * 业务部分报表导出
     * 
     * @param modelMap
     * @param request
     * @param response
     * @param model
     * @return
     */
    @GetMapping(value = "/dept-perf/excel")
    public ModelAndView getDeptPerfInExcel(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
            DeptPerfQueryReport model)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<DeptPerfReport> list = appraisalService.getDeptPerf(model);

        String exportTitle = "业务部门考核报表";
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map;

        String colNamesN[] =
        { "code", "companyName", "customerServiceName", "managerUser", "exportDate", "orderFlag", "totalForeignAmount",
                "planReceiptDate", "declarationDate", "clearanceForeignAmount", "IsRefundService", "refundTexAmount",
                "clearanceFinished", "uncompleteAmount", "retTaxServiceAmount" };

        map = new HashMap<String, Object>();
        map.put("code", "订单编号");
        map.put("companyName", "委托客户");
        map.put("customerServiceName", "客服");
        map.put("managerUser", "首单外贸顾问");
        map.put("exportDate", "出口日期");
        map.put("orderFlag", "首单返单");
        map.put("totalForeignAmount", "出口金额");
        map.put("planReceiptDate", "销售上报日期");
        map.put("declarationDate", "报关日期");
        map.put("clearanceForeignAmount", "收汇金额");
        map.put("IsRefundService", "退税垫付");
        map.put("refundTexAmount", "退税垫付金额");
        map.put("clearanceFinished", "货款收齐");
        map.put("uncompleteAmount", "未收齐金额");
        map.put("retTaxServiceAmount", "退税垫付服务费");

        list_export.add(map);
        NumberFormat nf = new DecimalFormat("0.00");
        for (DeptPerfReport deptPerf : list)
        {
            map = new HashMap<String, Object>();
            map.put("code", deptPerf.getCode());
            map.put("companyName", deptPerf.getCompanyName());
            map.put("customerServiceName", deptPerf.getCustomerServiceName());
            map.put("managerUser", deptPerf.getManagerUser());
            map.put("exportDate", deptPerf.getExportDate() == null ? "" : sdf.format(deptPerf.getExportDate()));
            if (deptPerf.getOrderFlag() == 1)
            {
                map.put("orderFlag", "首单");
            }
            else if (deptPerf.getOrderFlag() == 2)
            {
                map.put("orderFlag", "--");
            }
            else
            {
                map.put("orderFlag", "返单");
            }
            map.put("totalForeignAmount", nf.format(deptPerf.getTotalForeignAmount()));
            map.put("planReceiptDate",
                    deptPerf.getPlanReceiptDate() == null ? "" : sdf.format(deptPerf.getPlanReceiptDate()));
            map.put("declarationDate",
                    deptPerf.getDeclarationDate() == null ? "" : sdf.format(deptPerf.getDeclarationDate()));
            map.put("clearanceForeignAmount", nf.format(deptPerf.getClearanceForeignAmount()));
            if (deptPerf.getIsRefundService() == 1)
            {
                map.put("IsRefundService", "√");
            }
            else
            {
                map.put("IsRefundService", "");
            }
            map.put("refundTexAmount", nf.format(deptPerf.getRefundTexAmount()));
            if (deptPerf.getClearanceFinished() == 1)
            {
                map.put("clearanceFinished", "√");
            }
            else
            {
                map.put("clearanceFinished", "");
            }
            if (deptPerf.getTotalForeignAmount() - deptPerf.getClearanceForeignAmount() != 0)
            {
                map.put("uncompleteAmount",
                        nf.format(deptPerf.getTotalForeignAmount() - deptPerf.getClearanceForeignAmount()));
            }
            else
            {
                map.put("uncompleteAmount", "");
            }
            map.put("retTaxServiceAmount", nf.format(deptPerf.getRetTaxServiceAmount()));
            list_export.add(map);
        }
        ExcelView ExcelView = new ExcelView(exportTitle, list_export, colNamesN);
        return new ModelAndView(ExcelView, modelMap);
    }

}
