package com.cbecs.smc.invoice.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.framework.web.JsonResult;
import com.cbecs.smc.invoice.model.InvoiceCheckDetail;
import com.cbecs.smc.invoice.model.ScanningApplication;
import com.cbecs.smc.invoice.model.ScanningCheckDetail;
import com.cbecs.smc.invoice.model.ScanningDetail;
import com.cbecs.smc.invoice.model.ScanningDetailRelation;
import com.cbecs.smc.invoice.model.ScanningEdition;
import com.cbecs.smc.invoice.model.ScanningList;
import com.cbecs.smc.invoice.model.ScanningModification;
import com.cbecs.smc.invoice.model.ScanningQueryPageable;
import com.cbecs.smc.invoice.service.inf.InvoiceScanningService;

@Controller
@RequestMapping(value = "/invoice-scanning-applications")
public class InvoiceScanningController
{

    @Autowired
    private InvoiceScanningService invoiceScanningService;

    /**
     * 发票扫描申请列表
     * 
     * @return
     */
    @GetMapping(value = "")
    public ModelAndView index()
    {
        ModelAndView view = new ModelAndView("/smc/invoice/scanning/index");
        return view;
    }

    /**
     * 发票扫描申请数据
     * 
     * @return
     */
    @GetMapping(value = "", params = "format=table")
    public ModelAndView list(ScanningQueryPageable model)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("created_at");
        listSort.add(sort);
        model.setSort(listSort);
        model.setPageSize(10);
        ModelAndView view = new ModelAndView("/smc/invoice/scanning/list");
        List<ScanningList> scanningList = invoiceScanningService.list(model);
        view.addObject("scanningList", scanningList);
        view.addObject("page", model);
        return view;
    }

    /**
     * 新增发票扫描页面
     * 
     * @return
     */
    @GetMapping(value = "/add")
    public ModelAndView add()
    {
        ModelAndView view = new ModelAndView("/smc/invoice/scanning/add");
        return view;
    }

    /**
     * 新增
     * 
     * @return
     */
    @PostMapping(value = "", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult add(@RequestBody List<ScanningApplication> scanningApplication)
    {
        return JsonResult.Json(invoiceScanningService.add(scanningApplication));
    }

    /**
     * 编辑发票扫描页面
     * 
     * @return
     */
    @GetMapping(value = "/{applicationId}/edit")
    public ModelAndView edit(@PathVariable(value = "applicationId") String applicationId)
    {
        ModelAndView view = new ModelAndView("/smc/invoice/scanning/edit");
        ScanningEdition scanningApplication = invoiceScanningService
                .getScanningAppLicationByApplicationId(applicationId);
        view.addObject("scanningApplication", scanningApplication);
        return view;
    }

    /**
     * 编辑
     * 
     * @return
     */
    @PutMapping(value = "", produces = "application/json")
    @ResponseBody
    public JsonResult edit(@RequestBody ScanningModification model)
    {
        return JsonResult.Json(invoiceScanningService.edit(model));
    }

    /**
     * 发票扫描详情页面
     * 
     * @return
     */

    @GetMapping(value = "/{applicationId}/{orderId}/detail")
    public ModelAndView detail(@PathVariable(value = "applicationId") String applicationId,
            @PathVariable(value = "orderId") String orderId)
    {
        ModelAndView view = new ModelAndView("/smc/invoice/scanning/detail");
        // 详情页面上面信息
        ScanningDetail scanningDetail = invoiceScanningService.getScanningDetailByApplicationId(applicationId);
        // 详情页面下面信息
        List<ScanningDetailRelation> scanningDetailRelations = invoiceScanningService
                .getScanningDetailByOrderCode(applicationId, orderId);
        view.addObject("scanningDetail", scanningDetail);
        view.addObject("scanningDetailRelations", scanningDetailRelations);
        return view;
    }

    /**
     * 删除
     * 
     * @return
     */
    @DeleteMapping(value = "/{applicationId}")
    @ResponseBody
    public JsonResult removeInvoiceCollection(@PathVariable(value = "applicationId") String applicationId)
    {
        return JsonResult.Json(invoiceScanningService.remove(applicationId));
    }

    /**
     * 获取发票比对详情页面
     * 
     * @return
     */
    @GetMapping(value = "/{orderId}/check-detail")
    public ModelAndView getInvoiceCheckDetail(@PathVariable(value = "orderId") String orderId)
    {
        ModelAndView view = new ModelAndView("/smc/invoice/scanning/check_detail");
        ScanningCheckDetail scanningCheckDetail = invoiceScanningService.getOrderAndInvoceByOrderId(orderId);
        List<Map<String, Object>> suppliers = invoiceScanningService.getOrderSuppliersByOrderId(orderId);
        view.addObject("scanningCheckDetail", scanningCheckDetail);
        view.addObject("suppliers", suppliers);
        return view;
    }

    /**
     * 获取订单的发票比对信息
     * 
     * @return
     */
    @GetMapping(value = "/invoice-comparation-detail", params = "format=table")
    public ModelAndView get(String orderId, String invoiceNumber, String invoiceSellerId)
    {
        ModelAndView view = new ModelAndView("/smc/invoice/scanning/check_detail_list");
        List<InvoiceCheckDetail> invoiceCheckDetail = invoiceScanningService.getOrderInvoiceDetail(orderId, invoiceNumber,
                invoiceSellerId);
        view.addObject("invoiceCheckDetail", invoiceCheckDetail);
        return view;
    }

}