package com.cbecs.report.web.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.cbecs.framework.web.view.ExcelView;
import com.cbecs.report.model.ConsignerActiveQueryReport;
import com.cbecs.report.model.ConsignerActiveReport;
import com.cbecs.report.model.ConsignerExport;
import com.cbecs.report.model.ConsignerExportPageable;
import com.cbecs.report.model.ConsignerExportQuery;
import com.cbecs.report.model.ConsignerReorderQueryReport;
import com.cbecs.report.model.ConsignerReorderReport;
import com.cbecs.report.model.OrderDetailAnalysisQueryReport;
import com.cbecs.report.model.OrderDetailAnalysisQueryReportPageable;
import com.cbecs.report.model.OrderDetailAnalysisReport;
import com.cbecs.report.service.inf.AnalysisService;
import com.cbecs.report.service.inf.OperationService;

@Controller
@RequestMapping(value = "/analysis")
public class AnalysisController
{
    @Autowired
    private AnalysisService analysisService;

    @Autowired
    OperationService operationService;

    /**
     * 委托上活跃度页面
     * 
     * @return
     */
    @GetMapping(value = "/consigner-active")
    public ModelAndView getConsignerActive()
    {
        ModelAndView view = new ModelAndView("/report/analysis/consigner_active_list");
        return view;
    }

    /**
     * 委托商活跃度数据
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/consigner-active", produces =
    { "application/json" })
    @ResponseBody
    public List<ConsignerActiveReport> getconsignerActiveData(ConsignerActiveQueryReport model)
    {
        return analysisService.getConsignerActive(model);
    }

    /**
     * 委托商返单率页面
     * 
     * @return
     */
    @GetMapping(value = "/consigner-reorder")
    public ModelAndView getconsignerReorder()
    {
        return new ModelAndView("/report/analysis/consigner_reorder_list");
    }

    /**
     * 委托商返单率数据
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/consigner-reorder", produces =
    { "application/json" })
    @ResponseBody
    public List<ConsignerReorderReport> getconsignerReorderData(ConsignerReorderQueryReport model)
    {
        return analysisService.getConsignerReorder(model);
    }

    /**
     * 订单明细分析页面
     * 
     * @return
     */
    @GetMapping(value = "/order-detail")
    public ModelAndView getOrderDetail()
    {
        ModelAndView view = new ModelAndView("/report/analysis/order_details_analysis_list");
        String customerServices = analysisService.getExportReport();
        String managerUsers = analysisService.getFirstOrderManagerUser();
        view.addObject("customerServices", customerServices);
        view.addObject("managerUsers", managerUsers);
        return view;
    }

    /**
     * 订单明细分析数据
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/order-detail", params = "format=table")
    public ModelAndView getOrderDetailInTable(OrderDetailAnalysisQueryReportPageable model)
    {
        model.setPageSize(10);
        List<OrderDetailAnalysisReport> list = analysisService.getOrderDetailAnalysisByPage(model);
        ModelAndView view = new ModelAndView("report/analysis/order_details_analysis_table");
        view.addObject("orderDetails", list);
        view.addObject("page", model);
        return view;
    }

    /**
     * 导出订单明细分析数据
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/order-detail/excel")
    public ModelAndView exportOrderDetailInExcel(ModelMap modelMap, HttpServletRequest request,
            HttpServletResponse response, OrderDetailAnalysisQueryReport model)
    {
        List<OrderDetailAnalysisReport> list = analysisService.getOrderDetailAnalysis(model);

        String export_title = "订单明细分析报表";
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();

        String colNamesN[] =
        { "code", "declarationNumber", "ourCompanyName", "department", "customerServiceName", "managerUserName",
                "orderFlag", "companyName", "areaInfo", "orderDate", "orderWay", "exportDate", "declarationDate",
                "packingLeaveDate", "declareType", "destinationPort", "destinationCountryName", "logisticsService",
                "hasEnsureMoney", "paymentTerm", "isSellCreditService", "currencyName", "totalForeignAmount",
                "orderRate", "orderAmountRMB", "status", "isDeleted", "tradeTerm", "isRefundService", "refundService",
                "sellCreditAmountRMB", "drawers" };
        HashMap<String, Object> header = new HashMap<String, Object>();
        header.put("code", "订单号");
        header.put("declarationNumber", "报关单号");
        header.put("ourCompanyName", "业务主体");
        header.put("department", "所属部门");
        header.put("customerServiceName", "当前客服");
        header.put("managerUserName", "外贸顾问");
        header.put("orderFlag", "首单/返单");
        header.put("companyName", "委托方");
        header.put("areaInfo", "省市信息");
        header.put("orderDate", "下单日期");
        header.put("orderWay", "下单方式");
        header.put("exportDate", "出口日期");
        header.put("declarationDate", "申报日期");
        header.put("packingLeaveDate", "离境日期");
        header.put("declareType", "报关方式");
        header.put("destinationPort", "指运港");
        header.put("destinationCountryName", "目的国");
        header.put("logisticsService", "物流方式");
        header.put("hasEnsureMoney", "保证金");
        header.put("paymentTerm", "结算方式");
        header.put("isSellCreditService", "赊销服务");
        header.put("currencyName", "报关币种");
        header.put("totalForeignAmount", "报关金额(外币)");
        header.put("orderRate", "汇率");
        header.put("orderAmountRMB", "报关金额(RMB)");
        header.put("status", "订单状态");
        header.put("isDeleted", "是否删除");
        header.put("tradeTerm", "价格条款");
        header.put("isRefundService", "退税服务类型");
        header.put("refundService", "退税服务费率");
        header.put("sellCreditAmountRMB", "赊销金额(RMB)");
        header.put("drawers", "开票人");
        list_export.add(header);
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        NumberFormat nf = new DecimalFormat("0.00");
        for (OrderDetailAnalysisReport orderDetailAnalysis : list)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("code", orderDetailAnalysis.getCode());
            map.put("declarationNumber",
                    "".equals(orderDetailAnalysis.getDeclarationNumber())
                            || null == orderDetailAnalysis.getDeclarationNumber() ? ""
                                    : "'" + orderDetailAnalysis.getDeclarationNumber());
            map.put("ourCompanyName", orderDetailAnalysis.getOurCompanyName());
            map.put("department", orderDetailAnalysis.getDepartment());
            map.put("customerServiceName", orderDetailAnalysis.getCustomerServiceName());
            map.put("managerUserName", orderDetailAnalysis.getManagerUserName());
            if (orderDetailAnalysis.getOrderFlag() == 1)
            {
                map.put("orderFlag", "首单");
            }
            else if (orderDetailAnalysis.getOrderFlag() == 0)
            {
                map.put("orderFlag", "返单");
            }
            else
            {
                map.put("orderFlag", "--");
            }
            map.put("companyName", orderDetailAnalysis.getCompanyName());
            map.put("areaInfo", orderDetailAnalysis.getAreaInfo());
            map.put("orderDate", orderDetailAnalysis.getOrderDate() == null ? ""
                    : dateFormater.format(orderDetailAnalysis.getOrderDate()));
            if (orderDetailAnalysis.getOrderWay() == 1)
            {
                map.put("orderWay", "自助下单");
            }
            else if (orderDetailAnalysis.getOrderWay() == 2)
            {
                map.put("orderWay", "委托下单");
            }
            else
            {
                map.put("orderWay", "");
            }
            map.put("exportDate", orderDetailAnalysis.getExportDate() == null ? ""
                    : dateFormater.format(orderDetailAnalysis.getExportDate()));
            map.put("declarationDate", orderDetailAnalysis.getDeclarationDate() == null ? ""
                    : dateFormater.format(orderDetailAnalysis.getDeclarationDate()));
            map.put("packingLeaveDate", orderDetailAnalysis.getPackingLeaveDate() == null ? ""
                    : dateFormater.format(orderDetailAnalysis.getPackingLeaveDate()));
            if (orderDetailAnalysis.getDeclareType() == 1)
            {
                map.put("declareType", "贸互达报关");
            }
            else if (orderDetailAnalysis.getDeclareType() == 2)
            {
                map.put("declareType", "自行报关");
            }
            else
            {
                map.put("declareType", "");
            }
            map.put("destinationPort", orderDetailAnalysis.getDestinationPort());
            map.put("destinationCountryName", orderDetailAnalysis.getDestinationCountryName());
            if (orderDetailAnalysis.getLogisticsService() == 1)
            {
                map.put("logisticsService", "委托物流");
            }
            else if (orderDetailAnalysis.getLogisticsService() == 2)
            {
                map.put("logisticsService", "自行安排");
            }
            else if (orderDetailAnalysis.getLogisticsService() == 3)
            {
                map.put("logisticsService", "待定");
            }
            else
            {
                map.put("logisticsService", "");
            }
            if (orderDetailAnalysis.getHasEnsureMoney() == 1)
            {
                map.put("hasEnsureMoney", "含");
            }
            else
            {
                map.put("hasEnsureMoney", "不含");
            }
            map.put("paymentTerm", orderDetailAnalysis.getPaymentTerm());
            if (orderDetailAnalysis.getIsSellCreditService() == 1)
            {
                map.put("isSellCreditService", "是");
            }
            else
            {
                map.put("isSellCreditService", "否");
            }
            map.put("currencyName", orderDetailAnalysis.getCurrencyName());
            map.put("totalForeignAmount", nf.format(orderDetailAnalysis.getTotalForeignAmount()));
            map.put("orderRate", orderDetailAnalysis.getOrderRate());
            map.put("orderAmountRMB", nf.format(orderDetailAnalysis.getOrderAmountRMB()));
            if (orderDetailAnalysis.getStatus() == 1)
            {
                map.put("status", "草稿");
            }
            else if (orderDetailAnalysis.getStatus() == 10)
            {
                map.put("status", "完善中");
            }
            else if (orderDetailAnalysis.getStatus() == 20)
            {
                map.put("status", "审核中");
            }
            else if (orderDetailAnalysis.getStatus() == 30)
            {
                map.put("status", "待确认");
            }
            else if (orderDetailAnalysis.getStatus() == 40)
            {
                map.put("status", "待修改");
            }
            else if (orderDetailAnalysis.getStatus() == 50)
            {
                map.put("status", "执行中");
            }
            else if (orderDetailAnalysis.getStatus() == 52)
            {
                map.put("status", "已取消");
            }
            else if (orderDetailAnalysis.getStatus() == 90)
            {
                map.put("status", "订单完成");
            }
            else if (orderDetailAnalysis.getStatus() == 92)
            {
                map.put("status", "订单关闭");
            }
            else
            {
                map.put("status", "");
            }
            if (orderDetailAnalysis.getIsDeleted() == 1)
            {
                map.put("isDeleted", "是");
            }
            else
            {
                map.put("isDeleted", "否");
            }
            if (orderDetailAnalysis.getTradeTerm() == 1)
            {
                map.put("tradeTerm", "FOB");
            }
            else if (orderDetailAnalysis.getTradeTerm() == 2)
            {
                map.put("tradeTerm", "CIF");
            }
            else if (orderDetailAnalysis.getTradeTerm() == 3)
            {
                map.put("tradeTerm", "C&F");
            }
            else
            {
                map.put("tradeTerm", "其他");
            }
            if (orderDetailAnalysis.getIsRefundService() == 1)
            {
                map.put("isRefundService", "垫付退税");
            }
            else
            {
                map.put("isRefundService", "正常退税");
            }
            map.put("refundService", orderDetailAnalysis.getRefundService());
            map.put("sellCreditAmountRMB", nf.format(orderDetailAnalysis.getSellCreditAmountRMB()));
            map.put("drawers", orderDetailAnalysis.getDrawers());
            list_export.add(map);
        }
        ExcelView ExcelView = new ExcelView(export_title, list_export, colNamesN);
        return new ModelAndView(ExcelView, modelMap);
    }

    /**
     * 委托商出口分析报表页面
     * 
     * @return
     */
    @GetMapping(value = "/consigner-export")
    public ModelAndView getConsignerExport()
    {
        ModelAndView view = new ModelAndView("/report/analysis/consigner_export_list");
        String customerServices = analysisService.getExportReport();
        String productSourceType = analysisService.getproductSourceType();
        String industryType = analysisService.getIndustryType();
        String provinceName = analysisService.getProvince();
        String firstOrderManagerUser = analysisService.getFirstOrderManagerUser();
        view.addObject("customerServices", customerServices);
        view.addObject("productSourceType", productSourceType);
        view.addObject("industryType", industryType);
        view.addObject("provinceName", provinceName);
        view.addObject("firstOrderManagerUser", firstOrderManagerUser);
        return view;
    }

    /**
     * 省市级联参数查询
     * 
     * @param provinceid
     * @return
     */
    @GetMapping(value = "/getCity/{provinceid}", produces =
    { "application/json" })
    @ResponseBody
    public Map<String, Object> getCity(@PathVariable(value = "provinceid") int provinceid)
    {

        List<ConsignerExport> cityName = analysisService.getCity(provinceid);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cityName", cityName);
        return map;
    }

    /**
     * 展示委托方出口统计分析表的table
     * 
     * @param p
     * @return
     */
    @GetMapping(value = "/consigner-export", params = "format=table")
    public ModelAndView getExportTable(ConsignerExportPageable p)
    {
        p.setPageSize(10);
        List<ConsignerExport> t = analysisService.getExportTable(p);
        ModelAndView view = new ModelAndView("/report/analysis/consigner_export_table");
        view.addObject("exportlist", t);
        view.addObject("page", p);
        return view;
    }

    /**
     * 导出委托方出口分析表excel
     * 
     * @param model
     * @param request
     * @param response
     * @param p
     * @return
     */
    @GetMapping(value = "/consigner-export/excel")
    @ResponseBody
    public ModelAndView getExportReportExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response,
            ConsignerExportQuery p)
    {
        List<ConsignerExport> t = analysisService.getExportReport(p);
        String export_title = "委托方出口分析表";
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        String[] colNamesN = null;
        HashMap<String, Object> map;
        String colNames[] =
        { "companyName", "areaInfo", "industryTypeName", "productSourceTypeName", "signCode", "signTime",
                "firstExportDate", "orderCount", "orderTotalMoney", "firstOrderManagerUserName", "customerServiceName",
                "lastExportDate" };
        colNamesN = colNames;
        map = new HashMap<String, Object>();
        map.put("companyName", "委托方名");
        map.put("areaInfo", "省市信息");
        map.put("industryTypeName", "所属行业");
        map.put("productSourceTypeName", "企业类型");
        map.put("signCode", "协议编号");
        map.put("signTime", "签约时间");
        map.put("firstExportDate", "首单出口日期");
        map.put("orderCount", "累计订单数");
        map.put("orderTotalMoney", "累计订单金额");
        map.put("firstOrderManagerUserName", "首单外贸顾问");
        map.put("customerServiceName", "当前客服");
        map.put("lastExportDate", "最近出口日期");
        NumberFormat nf = new DecimalFormat("0.00");
        list_export.add(map);

        for (ConsignerExport consignerExport : t)
        {
            map = new HashMap<String, Object>();
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
            map.put("companyName", consignerExport.getCompanyName());
            map.put("areaInfo", consignerExport.getAreaInfo());
            map.put("industryTypeName", consignerExport.getIndustryTypeName());
            map.put("productSourceTypeName", consignerExport.getProductSourceTypeName());
            map.put("signCode", consignerExport.getSignCode());
            map.put("signTime",
                    consignerExport.getSignTime() == null ? "" : dateFormater.format(consignerExport.getSignTime()));
            map.put("firstExportDate", consignerExport.getFirstExportDate() == null ? ""
                    : dateFormater.format(consignerExport.getFirstExportDate()));
            map.put("orderCount", consignerExport.getOrderCount());
            map.put("orderTotalMoney", nf.format(consignerExport.getOrderTotalMoney()));
            map.put("firstOrderManagerUserName", consignerExport.getFirstOrderManagerUserName());
            map.put("customerServiceName", consignerExport.getCustomerServiceName());
            map.put("lastExportDate", consignerExport.getLastExportDate() == null ? ""
                    : dateFormater.format(consignerExport.getLastExportDate()));
            list_export.add(map);
        }

        ExcelView ExcelView = new ExcelView(export_title, list_export, colNamesN);
        return new ModelAndView(ExcelView, model);
    }

    public static void main(String[] args)
    {
        System.out.println(UUID.randomUUID().toString().toUpperCase());
    }
}
