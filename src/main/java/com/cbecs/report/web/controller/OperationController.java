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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.framework.web.view.ExcelView;
import com.cbecs.report.model.CustomerServiceReport;
import com.cbecs.report.model.CustomerServiceReportPageable;
import com.cbecs.report.model.LogisticsReport;
import com.cbecs.report.model.LogisticsReportPageable;
import com.cbecs.report.model.OrderReport;
import com.cbecs.report.model.OrderReportDetails;
import com.cbecs.report.model.OrderReportDetailsPageable;
import com.cbecs.report.model.OrderReportPageable;
import com.cbecs.report.service.inf.OperationService;

@Controller
@RequestMapping(value = "/operation")
public class OperationController
{
    @Autowired
    private OperationService operationService;

    /* 订单统计报表 */
    @GetMapping(value = "/order")
    public ModelAndView getOrderReport()
    {
        ModelAndView view = new ModelAndView("/report/operation/order_list");
        String customerServices = operationService.getCustomerServices("");
        view.addObject("customerServices", customerServices);
        return view;
    }

    @GetMapping(value = "/order", params = "format=table")
    public ModelAndView getOrderReportTable(OrderReportPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("orderCount");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<OrderReport> t = operationService.getOrderReportByPage(p);

        ModelAndView view = new ModelAndView("/report/operation/order_table");
        view.addObject("orderList", t);
        view.addObject("page", p);
        return view;
    }

    @GetMapping(value = "/order/excel")
    @ResponseBody
    public ModelAndView getOrderReportExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response, OrderReport p)
    {
        List<OrderReport> t = operationService.getOrderReport(p);

        String exportTitle = "订单统计报表";
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        String[] colNamesN = null;
        HashMap<String, Object> map;

        String colNames[] =
        { "customerServiceName", "orderCount", "orderAmount" };
        colNamesN = colNames;

        map = new HashMap<String, Object>();
        map.put("customerServiceName", "客服");
        map.put("orderCount", "订单数");
        map.put("orderAmount", "订单金额(CNY)");
        NumberFormat nf = new DecimalFormat("#,###.00");
        list_export.add(map);
        for (OrderReport orderReport : t)
        {
            map = new HashMap<String, Object>();
            map.put("customerServiceName", orderReport.getCustomerServiceName());
            map.put("orderCount", orderReport.getOrderCount());
            map.put("orderAmount", orderReport.getOrderAmount() != 0 ? nf.format(orderReport.getOrderAmount()) : "0.00");
            list_export.add(map);
        }
        ExcelView ExcelView = new ExcelView(exportTitle, list_export, colNamesN);
        return new ModelAndView(ExcelView, model);
    }

    /* 订单统计报表明细 */
    @GetMapping(value = "/order-details")
    public ModelAndView getOrderReportDetails(String customerServiceId)
    {
        ModelAndView view = new ModelAndView("/report/operation/order_details_list");
        String customerServices = operationService.getCustomerServices(customerServiceId);
        view.addObject("customerServices", customerServices);
        return view;
    }

    @GetMapping(value = "/order-details", params = "format=table")
    public ModelAndView getOrderReportDetailsTable(OrderReportDetailsPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("ordercode");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<OrderReportDetails> t = operationService.getOrderReportDetailsByPage(p);

        ModelAndView view = new ModelAndView("/report/operation/order_details_table");
        view.addObject("orderDetailsList", t);
        view.addObject("page", p);
        return view;
    }

    @GetMapping(value = "/order-details/excel")
    @ResponseBody
    public ModelAndView getOrderReportExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response, OrderReportDetails p)
    {
        List<OrderReportDetails> t = operationService.getOrderReportDetails(p);

        String exportTitle = "订单统计报表(明细)";
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        String[] colNamesN = null;
        HashMap<String, Object> map;

        String colNames[] =
        { "orderDate", "orderCode", "companyName", "customerServiceName", "supplierName", "invoiceAmount", "declarationAmount", "currencySymbol", "rate", "amount" };
        colNamesN = colNames;

        map = new HashMap<String, Object>();
        map.put("orderDate", "订单日期");
        map.put("orderCode", "订单号");
        map.put("companyName", "委托方");
        map.put("customerServiceName", "客服");
        map.put("supplierName", "开票人");

        map.put("invoiceAmount", "开票金额");
        map.put("declarationAmount", "报关金额(外币)");
        map.put("currencySymbol", "报关币种");
        map.put("rate", "汇率");
        map.put("amount", "报关金额(人民币)");
        NumberFormat nf = new DecimalFormat("#,###.00");
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        list_export.add(map);
        for (OrderReportDetails orderReportDetails : t)
        {
            map = new HashMap<String, Object>();

            map.put("orderDate", orderReportDetails.getOrderDate() != null ? date.format(orderReportDetails.getOrderDate()) : "");
            map.put("orderCode", orderReportDetails.getOrderCode());
            map.put("companyName", orderReportDetails.getCompanyName());
            map.put("customerServiceName", orderReportDetails.getCustomerServiceName());
            map.put("supplierName", orderReportDetails.getSupplierName());

            map.put("invoiceAmount", orderReportDetails.getInvoiceAmount() != 0 ? nf.format(orderReportDetails.getInvoiceAmount()) : "0.00");
            map.put("declarationAmount", orderReportDetails.getDeclarationAmount() != 0 ? nf.format(orderReportDetails.getDeclarationAmount()) : "0.00");
            map.put("currencySymbol", orderReportDetails.getCurrencySymbol());
            map.put("rate", orderReportDetails.getRate());
            map.put("amount", orderReportDetails.getAmount() != 0 ? nf.format(orderReportDetails.getAmount()) : "0.00");

            list_export.add(map);
        }
        ExcelView ExcelView = new ExcelView(exportTitle, list_export, colNamesN);
        return new ModelAndView(ExcelView, model);
    }

    /* 客服统计报表 */
    @GetMapping(value = "/customer-service")
    public ModelAndView getCustomerServiceReport()
    {
        ModelAndView view = new ModelAndView("/report/operation/customer_service_list");
        String customerServices = operationService.getCustomerServices("");
        view.addObject("customerServices", customerServices);
        return view;
    }

    @GetMapping(value = "/customer-service", params = "format=table")
    public ModelAndView getCustomerServiceReportTable(CustomerServiceReportPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("orderCode");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<CustomerServiceReport> t = operationService.getCustomerServiceReportByPage(p);

        ModelAndView view = new ModelAndView("/report/operation/customer_service_table");
        view.addObject("customerServiceList", t);
        view.addObject("page", p);
        return view;
    }

    @GetMapping(value = "/customer-service/excel")
    @ResponseBody
    public ModelAndView getCustomerServiceReportExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response, CustomerServiceReport p)
    {
        List<CustomerServiceReport> t = operationService.getCustomerServiceReport(p);

        String exportTitle = "客服统计报表";
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        String[] colNamesN = null;
        HashMap<String, Object> map;

        String colNames[] =
        { "orderCode", "companyName", "customerServiceName", "paidAmount", "declarationAmount", "currencySymbol", "gatherDate", "gatherSymbol", "gatherAmountForeign", "gatherRate", "gatherAmountRmb" };
        colNamesN = colNames;

        map = new HashMap<String, Object>();
        map.put("orderCode", "订单号");
        map.put("companyName", "委托方");
        map.put("customerServiceName", "客服");
        map.put("paidAmount", "实付货款");
        map.put("declarationAmount", "报关金额");
        map.put("currencySymbol", "报关币种");

        map.put("gatherDate", "收汇日期");
        map.put("gatherSymbol", "收汇币种");
        map.put("gatherAmountForeign", "收汇金额(外币)");
        map.put("gatherRate", "汇率");
        map.put("gatherAmountRmb", "收汇金额(人民币)");
        NumberFormat nf = new DecimalFormat("#,###.00");
        NumberFormat nf2 = new DecimalFormat("#,###.0000");
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        list_export.add(map);
        for (CustomerServiceReport customerService : t)
        {
            map = new HashMap<String, Object>();
            map.put("orderCode", customerService.getOrderCode());
            map.put("companyName", customerService.getCompanyName());
            map.put("customerServiceName", customerService.getCustomerServiceName());
            map.put("paidAmount",  customerService.getPaidAmount() != 0 ? nf.format(customerService.getPaidAmount()) : "0.00");
            map.put("declarationAmount", customerService.getDeclarationAmount()  != 0 ? nf.format(customerService.getDeclarationAmount() ) : "0.00");
            map.put("currencySymbol", customerService.getCurrencySymbol());

            map.put("gatherDate",  customerService.getGatherDate() != null ? date.format(customerService.getGatherDate()) : "");
            map.put("gatherSymbol", customerService.getGatherSymbol());
            map.put("gatherAmountForeign", customerService.getGatherAmountForeign() != 0 ? nf.format(customerService.getGatherAmountForeign()) : "0.00");
            map.put("gatherRate", customerService.getGatherRate() != 0 ? nf2.format(customerService.getGatherRate()) : "0.0000");
            map.put("gatherAmountRmb",  customerService.getGatherAmountRmb() != 0 ? nf.format( customerService.getGatherAmountRmb()) : "0.00");
            list_export.add(map);
        }

        ExcelView ExcelView = new ExcelView(exportTitle, list_export, colNamesN);
        return new ModelAndView(ExcelView, model);
    }

    /* 物流账单 */
    @GetMapping(value = "/logistics")
    public ModelAndView getLogisticsReport()
    {
        return new ModelAndView("/report/operation/logistics_list");
    }

    @GetMapping(value = "/logistics", params = "format=table")
    public ModelAndView getLogisticsReportTable(LogisticsReportPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("orderCode");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);
        List<LogisticsReport> t = operationService.getLogisticsReportByPage(p);

        ModelAndView view = new ModelAndView("/report/operation/logistics_table");
        view.addObject("logisticsList", t);
        view.addObject("page", p);
        return view;
    }

    @GetMapping(value = "/logistics/excel")
    @ResponseBody
    public ModelAndView getLogisticsReportExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response, LogisticsReport p)
    {
        List<LogisticsReport> t = operationService.getLogisticsReport(p);

        String exportTitle = "物流账单";
        ArrayList<HashMap<String, Object>> list_export = new ArrayList<HashMap<String, Object>>();
        String[] colNamesN = null;
        HashMap<String, Object> map;
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String colNames[] =
        { "orderCode", "orderDate", "transportMode", "transportChildMode", "logisticsCostTypeName", "logisticsBusName", "costPrice", "customerPrice", "profit" };
        colNamesN = colNames;

        map = new HashMap<String, Object>();
        map.put("orderCode", "订单号");
        map.put("orderDate", "业务日期");
        map.put("transportMode", "物流方式");
        map.put("transportChildMode", "业务操作");
        map.put("logisticsCostTypeName", "费用类型");
        map.put("logisticsBusName", "物流供应商");

        map.put("costPrice", "成本价");
        map.put("customerPrice", "客户报价");
        map.put("profit", "利润");

        list_export.add(map);
        for (LogisticsReport logisticsReport : t)
        {
            map = new HashMap<String, Object>();
            map.put("orderCode", logisticsReport.getOrderCode());
            map.put("orderDate", logisticsReport.getOrderDate() != null ? date.format(logisticsReport.getOrderDate()) : "");
            map.put("transportMode", logisticsReport.getTransportMode());
            map.put("transportChildMode", logisticsReport.getTransportChildMode());
            map.put("logisticsCostTypeName", logisticsReport.getLogisticsCostTypeName());
            map.put("logisticsBusName", logisticsReport.getLogisticsBusName());

            map.put("costPrice", logisticsReport.getCostPriceByCurrency());
            map.put("customerPrice", logisticsReport.getCustomerPriceByCurrency());
            map.put("profit", logisticsReport.getProfit());
            list_export.add(map);
        }
        ExcelView ExcelView = new ExcelView(exportTitle, list_export, colNamesN);
        return new ModelAndView(ExcelView, model);
    }

}
