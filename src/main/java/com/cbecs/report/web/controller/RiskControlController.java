package com.cbecs.report.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.framework.web.view.ExcelView;
import com.cbecs.report.model.ConsignerReport;
import com.cbecs.report.model.ConsignerReportPageable;
import com.cbecs.report.model.DrawerReport;
import com.cbecs.report.model.DrawerReportPageable;
import com.cbecs.report.service.inf.RiskControlService;

@Controller
@RequestMapping(value = "/risk-control")
public class RiskControlController
{
    @Autowired
    private RiskControlService riskControlService;
    
    /*开票人报表*/
    @GetMapping(value = "/drawer")
    public ModelAndView getDrawerReport(){
        return  new ModelAndView("/report/risk_control/drawer_list");
    }
    

    @GetMapping(value = "/drawer", params="format=table")
    public ModelAndView getDrawerReportTable(DrawerReportPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("asc");
        sort.setProperty("name");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<DrawerReport> t = riskControlService.getDrawerReportByPage(p);

        ModelAndView view = new ModelAndView("/report/risk_control/drawer_table");
        view.addObject("drawerList", t);
        view.addObject("page", p);
        return view;
    }
    
    @GetMapping(value = "/drawer/excel")
    @ResponseBody
    public ModelAndView getDrawerReportExcel(ModelMap model, HttpServletRequest request,
            HttpServletResponse response, DrawerReport p)
    {
        List<DrawerReport> t = riskControlService.getDrawerReport(p);

        String exportTitle = "开票人报表";
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        String[] colNamesN = null;
        HashMap<String, Object> map;
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); 

        String colNames[] =
        { "name", "companyName", "foundDate", "isCertificate", "registerAddress",
          "businessAddress", "supplierEnterpriseType", "vATInvoiceUpper", "grantedDate", "ticketCount", 
          "sheetCount", "pictureCount", "doorplateCount", "exportProducts", "isSupplier","amount"};
        colNamesN = colNames;
      
        map = new HashMap<String, Object>();
        map.put("name", "开票人名称");
        map.put("companyName", "对应委托人");
        map.put("foundDate", "成立日期");
        map.put("isCertificate", "三证合一");
        map.put("registerAddress", "注册地址");
        map.put("businessAddress", "经营地址");
        map.put("supplierEnterpriseType", "公司属性");
        map.put("vATInvoiceUpper", "税票种类");
        map.put("grantedDate", "一般纳税人年限");
        map.put("ticketCount", "增值税票");
        map.put("sheetCount", "纳税人报表");
        map.put("pictureCount", "生产线照片");
        map.put("doorplateCount", "公司门头");
        map.put("exportProducts", "出口商品");
        map.put("isSupplier", "已开票");
        map.put("amount", "已开票金额");
        

        list_export.add(map);
        for (DrawerReport drawerReport : t)
        {
            map = new HashMap<String, Object>();
            map.put("name", drawerReport.getName());
            map.put("companyName", drawerReport.getCompanyName());
            map.put("foundDate", drawerReport.getFoundDate()!=null?date.format(drawerReport.getFoundDate()):"");
            map.put("isCertificate", drawerReport.getIsCertificate());
            map.put("registerAddress", drawerReport.getRegisterAddress());
            map.put("businessAddress", drawerReport.getBusinessAddress());
            map.put("supplierEnterpriseType", drawerReport.getSupplierEnterpriseType());
            map.put("vATInvoiceUpper", drawerReport.getvATInvoiceUpper());
            map.put("grantedDate", drawerReport.getGrantedDate()!=null?date.format(drawerReport.getGrantedDate()):"");
            map.put("ticketCount", drawerReport.getTicketCount());
            map.put("sheetCount", drawerReport.getSheetCount());
            map.put("pictureCount", drawerReport.getPictureCount());
            map.put("doorplateCount", drawerReport.getDoorplateCount());
            map.put("exportProducts", drawerReport.getExportProducts());
            map.put("isSupplier", drawerReport.getIsSupplier());
            map.put("amount", drawerReport.getAmount());
            list_export.add(map);
        }

        ExcelView ExcelView = new ExcelView(exportTitle, list_export, colNamesN);
        return new ModelAndView(ExcelView, model);
    }
    
    
    
    /*委托方报表*/
    @GetMapping(value = "/consigner")
    public ModelAndView getConsignerReport(){
        return  new ModelAndView("/report/risk_control/consigner_list");
    }
    

    @GetMapping(value = "/consigner", params="format=table")
    public ModelAndView getConsignerReportTable(ConsignerReportPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("asc");
        sort.setProperty("name");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<ConsignerReport> t = riskControlService.getConsignerReportByPage(p);

        ModelAndView view = new ModelAndView("/report/risk_control/consigner_table");
        view.addObject("consignerList", t);
        view.addObject("page", p);
        return view;
    }
    
    @GetMapping(value = "/consigner/excel")
    @ResponseBody
    public ModelAndView getConsignerReportExcel(ModelMap model, HttpServletRequest request,
            HttpServletResponse response, ConsignerReport p)
    {
        List<ConsignerReport> t = riskControlService.getConsignerReport(p);

        String exportTitle = "委托方报表";
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        String[] colNamesN = null;
        HashMap<String, Object> map;

        String colNames[] =
        { "name", "industrytype", "isCertificate", "registerAddress", "businessAddress",
          "idCardCount", "personalReporCount"};
        colNamesN = colNames;
      
        map = new HashMap<String, Object>();
        map.put("name", "委托方名称");
        map.put("industrytype", "企业类型");
        map.put("isCertificate", "三证合一");
        map.put("registerAddress", "注册地址");
        map.put("businessAddress", "经营地址");
        map.put("idCardCount", "个人身份证");
        map.put("personalReporCount", "个人征信报告");
               

        list_export.add(map);
        for (ConsignerReport consignerReport : t)
        {
            map = new HashMap<String, Object>();
            map.put("name", consignerReport.getName());
            map.put("industrytype", consignerReport.getIndustrytype());
            map.put("isCertificate", consignerReport.getIsCertificate());
            map.put("registerAddress", consignerReport.getRegisterAddress());
            map.put("businessAddress", consignerReport.getBusinessAddress());
            map.put("idCardCount", consignerReport.getIdCardCount());
            map.put("personalReporCount", consignerReport.getPersonalReporCount());
            list_export.add(map);
        }

        ExcelView ExcelView = new ExcelView(exportTitle, list_export, colNamesN);
        return new ModelAndView(ExcelView, model);
    }
    
}