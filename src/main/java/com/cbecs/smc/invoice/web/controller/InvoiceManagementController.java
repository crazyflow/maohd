package com.cbecs.smc.invoice.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.framework.web.JsonResult;
import com.cbecs.smc.invoice.model.CustomerService;
import com.cbecs.smc.invoice.model.Invoice;
import com.cbecs.smc.invoice.model.InvoiceList;
import com.cbecs.smc.invoice.model.InvoiceProduct;
import com.cbecs.smc.invoice.model.ManagementList;
import com.cbecs.smc.invoice.model.ManagementQueryPageable;
import com.cbecs.smc.invoice.model.OrderInvoiceSummary;
import com.cbecs.smc.invoice.service.inf.InvoiceManagementService;

@Controller
@RequestMapping(value = "/invoice-management-applications")
public class InvoiceManagementController {

	@Autowired
    private InvoiceManagementService invoiceManagementService;
	
	private static final String RESPONSE_CODE = "code";
    private static final String RESPONSE_MESSAGE = "message";
	
	/**
	 * 发票管理默认页
	 * @return
	 */
	@GetMapping(value = "")
    public ModelAndView index()
    {
        ModelAndView view = new ModelAndView("/smc/invoice/management/index");
        List<CustomerService> customerServiceList = invoiceManagementService.customers(1);
        view.addObject("customerServiceList", customerServiceList);
        return view;
    }
	
	/**
	 * 发票管理数据
	 * @param model
	 * @return
	 */
    @GetMapping(value = "", params = "format=table")
    public ModelAndView list(ManagementQueryPageable model)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("orderTransferDate");
        listSort.add(sort);
        model.setSort(listSort);
        model.setPageSize(10);
        ModelAndView view = new ModelAndView("/smc/invoice/management/list");
        List<ManagementList> managementList = invoiceManagementService.list(model);
        view.addObject("managementList", managementList);
        view.addObject("page", model);
        return view;
    }
    
    /**
     * 红冲数量
     * @return
     */
    @GetMapping(value = "/status")
    public ModelAndView statusDetail(){
    	ModelAndView view = new ModelAndView("/smc/invoice/management/status_detail");
    	int redCount = invoiceManagementService.getOrderInvoiceRedCount();
    	view.addObject("redCount", redCount);
    	return view;
    }
    
    /**
     * 发票详情
     * @param orderId
     * @return
     */
    @GetMapping(value = "/{orderId}/detail")
    public ModelAndView detail(@PathVariable(value = "orderId") String orderId)
    {
        ModelAndView view = new ModelAndView("/smc/invoice/management/detail");
        OrderInvoiceSummary summary = invoiceManagementService.getOrderInvoiceSummary(orderId);
        List<CustomerService> supplierList = invoiceManagementService.suppliers(orderId);
        view.addObject("summary", summary);
        view.addObject("supplierList", supplierList);
        return view;
    }
    
    /**
     * 订单发票数据
     * @param orderId
     * @param invoiceNumber
     * @param supplierId
     * @return
     */
	@GetMapping(value = "/invoice-list")
	public ModelAndView invoiceList(String orderId, String invoiceNumber, String supplierId)
    {
        ModelAndView view = new ModelAndView("/smc/invoice/management/invoice_list");
        List<InvoiceList> invoiceList = invoiceManagementService.getInvoiceList(orderId, invoiceNumber, supplierId);
        view.addObject("invoiceList", invoiceList);
        return view;
    }
    
    /**
     * 设置红冲
     * @param invoiceId
     * @param orderId
     * @return
     */
	@GetMapping(value = "/red")
	@ResponseBody
	public JsonResult updateStatus(String invoiceId, String orderId) {
		int flag = invoiceManagementService.isInvoiceCanSetRed(orderId);
		Map<String, Object> icsResponseCode = new HashMap<String, Object>();
		try{
			if(flag == 0){
				invoiceManagementService.setInvoiceRed(invoiceId, orderId);
			}
			
			icsResponseCode.put(RESPONSE_CODE, String.valueOf(flag));
			icsResponseCode.put(RESPONSE_MESSAGE, flag == 0 ? "成功" : (flag == 1 ? "已做退税垫付" : (flag == 2 ? "已导入退税" : "已做退税垫付和导入退税")));
		}catch (Exception e) {
			icsResponseCode.put(RESPONSE_CODE, "-1");
			icsResponseCode.put("-1", "红冲出错，请重试");
		}
		
		return JsonResult.Json(icsResponseCode);
	}
	
	/**
	 * 单张发票商品详情
	 * @param invoiceId
	 * @return
	 */
	@GetMapping(value = "/invoice-detail")
	public ModelAndView invoiceDetail(String invoiceId)
    {
        ModelAndView view = new ModelAndView("/smc/invoice/management/invoice_detail");
        Invoice invoice = invoiceManagementService.getInvoiceDetail(invoiceId);
        List<InvoiceProduct> invoiceProductList = invoiceManagementService.getInvoiceProductList(invoiceId);
        view.addObject("invoice", invoice);
        view.addObject("invoiceProductList", invoiceProductList);
        return view;
    }
}
