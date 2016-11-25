package com.cbecs.omc.order.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.omc.order.model.OrderInfoBaseForInvoiceAddIndeCate;
import com.cbecs.omc.order.model.OrderInfoCommission;
import com.cbecs.omc.order.model.OrderInfoCommissionQueryByPage;
import com.cbecs.omc.order.model.OrderInfoForInvoiceCollection;
import com.cbecs.omc.order.model.OrderInfoForInvoiceCollectionQueryByPage;
import com.cbecs.omc.order.service.inf.OrderService;
import com.cbecs.report.service.inf.AnalysisService;
import com.cbecs.smc.commision.model.SettlementApplication;

@Controller
@RequestMapping(value = "/orders")
public class OrderController
{

    @Autowired
    private OrderService orderService;

    @Autowired
    private AnalysisService analysisService;

    /**
     * 选择的订单信息
     * 
     * @return
     */
    @GetMapping(value = "/commission-settlement")
    public ModelAndView getOrderDialog()
    {
        ModelAndView view = new ModelAndView("/omc/order/order_commission_settlement_list");
        String businessMainBodys = analysisService.getBusinessMainBodyInHtml();
        view.addObject("businessMainBodys", businessMainBodys);
        return view;
    }

    /**
     * 选择的订单列表信息dialog
     * 
     * @return
     */
    @GetMapping(value = "/commission-settlement", params = "format=table")
    public ModelAndView getOrderListByPage(OrderInfoCommissionQueryByPage model)
    {
        model.setPageSize(5);
        List<OrderInfoCommission> list = orderService.getOrderInfoByChannelId(model);
        ModelAndView view = new ModelAndView("/omc/order/order_commission_settlement_table");
        view.addObject("OrderInfos", list);
        view.addObject("page", model);
        return view;
    }

    /**
     * 根据订单号code查询订单的服务信息返回结算对象
     * 
     * @return
     */
    @GetMapping(value = "/fiancials")
    public ModelAndView getFiancialByOrderCode(String orders, int cooperationMode)
    {
        SettlementApplication settlementApplication = orderService.getSettlementApplicationByOrderCode(orders,
                cooperationMode);
        ModelAndView view = new ModelAndView("/smc/commission/settlement_detail_list");
        view.addObject("settlementApplication", settlementApplication);
        return view;
    }

    /**
     * 选择的订单信息，发票采集dialog
     * 
     * @return
     */
    @GetMapping(value = "/invoice-collection")
    public ModelAndView getOrderDialogForInvoiceCollection()
    {
        ModelAndView view = new ModelAndView("/omc/order/order_invoice_collection_index");
        return view;
    }

    /**
     * 选择的订单列表信息,发票采集
     * 
     * @return
     */
    @GetMapping(value = "/invoice-collection", params = "format=table")
    public ModelAndView getOrderListByPageFroInvoiceCollection(OrderInfoForInvoiceCollectionQueryByPage model)
    {
        model.setPageSize(5);
        List<OrderInfoForInvoiceCollection> invoiceCollection = orderService.getOrderInfoForInvoiceCollection(model);
        ModelAndView view = new ModelAndView("/omc/order/order_invoice_collection_list");
        view.addObject("invoiceCollection", invoiceCollection);
        view.addObject("page", model);
        return view;
    }

    /**
     * 根据订单号id查询订单的采购合同信息
     * 
     * @return
     */
    @GetMapping(value = "/order-contract-invoice", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<OrderInfoBaseForInvoiceAddIndeCate> getOrderInvoiceScanningInfo(String orderIds)
    {
        return orderService.getInvoiceScanningInfoByOrderIds(orderIds);
    }

}
