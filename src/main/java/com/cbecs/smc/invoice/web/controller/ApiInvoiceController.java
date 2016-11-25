package com.cbecs.smc.invoice.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbecs.framework.web.JsonResult;
import com.cbecs.omc.order.model.ApiOrderInfoForInvoiceQueryByPage;
import com.cbecs.omc.order.model.ApiSimpleOrder;
import com.cbecs.smc.invoice.model.ApiInvoice;
import com.cbecs.smc.invoice.model.ApiInvoiceApplicationQueryPageable;
import com.cbecs.smc.invoice.model.ApiInvoiceScanning;
import com.cbecs.smc.invoice.service.inf.ApiInvoiceService;

@Controller
@RequestMapping(value = "/api/invoices")
public class ApiInvoiceController
{
    @Autowired
    private ApiInvoiceService invoiceScanningApiService;

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody JsonResult runtimeExceptionHandler(RuntimeException runtimeException)
    {
        return JsonResult.Json(500, runtimeException.getMessage());
    }

    /**
     * 查询订单的所有未比对的发票 --钱伟
     * 
     * @return
     */
    @GetMapping(value = "/no-comparison-invoices", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getNoComparisionInvoices(@RequestParam String orderId)
    {
        return JsonResult.Json(invoiceScanningApiService.getNoComparisonInvoicesByOrderId(orderId));
    }

    /**
     * 查询所有的待扫描和待收票的申请单数据 --钱伟
     * 
     * @return
     */
    @GetMapping(value = "/no-completed-applications", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getNoCompletedApplications(ApiInvoiceApplicationQueryPageable model)
    {
        return JsonResult.Json(invoiceScanningApiService.getNoCompletedApplications(model));
    }

    /**
     * 比对不通过修改订单及发票状态-- 钱伟
     * 
     * @return
     */
    @PutMapping(value = "/order-invoice-status", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult editOrderAndInvoiceStatus(@RequestBody ApiSimpleOrder model)
    {
        return JsonResult.Json(invoiceScanningApiService.editOrderAndInvoiceStatus(model));
    }

    /**
     * 判断新增发票是否已经存在--吴凡
     * 
     * @return
     */
    @GetMapping(value = "/verification-invoices", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult verificationInvoices(ApiInvoice model)
    {
        return JsonResult.Json(invoiceScanningApiService.verificationInvoices(model));
    }

    /**
     * 新增发票--吴凡
     * 
     * @return
     */
    @PostMapping(value = "/add-invoices", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult addInvoices(@RequestBody ApiInvoice model)
    {
        return JsonResult.Json(invoiceScanningApiService.addInvoices(model));
    }

    /**
     * 编辑发票--钱伟
     * 
     * @return
     */
    @PutMapping(value = "/edit-invoices", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult editInvoice(@RequestBody ApiInvoice model)
    {
        return JsonResult.Json(invoiceScanningApiService.editInvoice(model));
    }

    /**
     * 根据发票号码,开票人id,订单id 获取发票list--吴凡
     * 
     * @return
     */
    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getInvoicesByCodeAndSupplierAndOrderId(String orderId, String invoiceCode, String supplierId)
    {
        return JsonResult.Json(
                invoiceScanningApiService.getInvoicesByCodeAndSupplierAndOrderId(orderId, invoiceCode, supplierId));
    }

    /**
     * 获取发票申请单待收票数量-- 钱伟
     * 
     * @return
     */
    @GetMapping(value = "/no-receipt-application-counts", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getInvoiceScanningCountByNoReceipt()
    {
        return JsonResult.Json(invoiceScanningApiService.getInvoiceScanningCountByNoReceipt());
    }

    /**
     * 获取发票申请单待扫描的数量-- 钱伟
     * 
     * @return
     */
    @GetMapping(value = "/no-scanning-application-counts", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getInvoiceScanningCountByNoScanning()
    {
        return JsonResult.Json(invoiceScanningApiService.getInvoiceScanningCountByNoScanning());
    }

    /**
     * 获取所有客服-- 钱伟
     * 
     * @return
     */
    @GetMapping(value = "/customers", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getCustomers()
    {
        return JsonResult.Json(invoiceScanningApiService.getCustomers());
    }

    /**
     * 根据发票id获取发票信息/商品信息-- 吴凡
     * 
     * @return
     */
    @GetMapping(value = "/detail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getInvoiceByInvoiceId(String invoiceId)
    {
        return JsonResult.Json(invoiceScanningApiService.getInvoiceByInvoiceId(invoiceId));
    }

    /**
     * 根据订单id获取订单的收票情况-- 钱伟
     * 
     * @return
     */
    @GetMapping(value = "/receiving-condition", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getOrderReceiptInvoiceStatusByOrderId(String orderId)
    {
        return JsonResult.Json(invoiceScanningApiService.getOrderReceiptInvoiceStatusByOrderId(orderId));
    }

    /**
     * 获取登录账户信息--潘明星
     * 
     * @param account
     *            accountNumber 账号 password 密码
     * @return
     */
    @GetMapping(value = "/signin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getManagerUser(String accountNumber, String password)
    {
        // 用户认证，从数据库验证用户名密码，如果成功,获取用户信息，返回json给客户端
        // 调用service
        return JsonResult.Json(invoiceScanningApiService.getManagerUser(accountNumber, password));
    }

    /**
     * 查询比对待收票和待比对的订单-钱伟 orderCode 订单编号 customerName 客服名称 supplierName 开票人名称
     * startDate 开始时间 endDate 结束时间
     * 
     * @return
     */
    @GetMapping(value = "/receiving-checking-orders", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getReceivingOrdersForInvoiceCheckCount(ApiOrderInfoForInvoiceQueryByPage model)
    {
        return JsonResult.Json(invoiceScanningApiService.getReceivingAndCheckingOrders(model));
    }

    /**
     * 查询比对待收票订单的数目-钱伟
     * 
     * @return
     */
    @GetMapping(value = "/invoiceCheck-receiving-orders-count", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getReceivingOrdersForInvoiceCheckCount()
    {
        return JsonResult.Json(invoiceScanningApiService.getReceivingOrdersForInvoiceCheckCount());
    }

    /**
     * 查询待比对订单的数目-钱伟
     * 
     * @return
     */
    @GetMapping(value = "/invoiceCheck-checking-orders-count", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getCheckingOrdersForInvoiceCheckCount()
    {
        return JsonResult.Json(invoiceScanningApiService.getCheckingOrdersForInvoiceCheckCount());
    }

    /**
     * 查询订单下所有的开票人--钱伟
     * 
     * @param orderId
     * @return
     */
    @GetMapping(value = "/order-suppliers", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getSuppliersFromOrderByOrderId(String orderId)
    {
        return JsonResult.Json(invoiceScanningApiService.getSuppliersFromOrderByOrderId(orderId));
    }

    /**
     * 查询所有比对不通过的发票--钱伟
     * 
     * @return
     */
    @GetMapping(value = "/invoices-checking-fail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getInvoicesOfCheckingFail(String orderCode, String orderId, String invoiceCode, String supplier)
    {
        return JsonResult
                .Json(invoiceScanningApiService.getInvoicesOfCheckingFail(orderCode, orderId, invoiceCode, supplier));
    }

    /**
     * 更改发票扫描申请单状态--钱伟
     * 
     * @param map
     *            applicationId 扫描申请单ID
     * @return
     */
    @PutMapping(value = "/scanning-application-status", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult editInvoiceScanningApplicationStatusById(@RequestBody ApiInvoiceScanning model)
    {
        return JsonResult.Json(invoiceScanningApiService.editInvoiceScanningApplicationStatusById(model));
    }

    /**
     * 扫描拒收票原因--钱伟
     * 
     * @param map
     *            applicationId 扫描申请单ID reason 收票有误原因
     * @return
     */
    @PutMapping(value = "/scanning-application-reason", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult editInvoiceScanningApplicationReason(@RequestBody ApiInvoiceScanning model)
    {
        return JsonResult.Json(invoiceScanningApiService.editInvoiceScanningApplicationStatusAndReasonById(model));
    }

    /**
     * 更改订单发票比对状态
     * 
     * @param map
     *            orderId 订单ID
     * @return
     */
    @PutMapping(value = "/order-invoice-check-status", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult editOrderInvoiceCheckStatusByOrderId(@RequestBody Map<String, String> map)
    {
        return JsonResult.Json(invoiceScanningApiService.editOrderInvoiceCheckStatusByOrderId(map.get("orderId")));
    }

    /**
     * 比对拒收票原因
     * 
     * @param map
     *            orderId 订单ID reason 拒收原因
     * @return
     */
    @PutMapping(value = "/order-invoice-check-reason", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult editOrderInvoiceCheckStatusReason(@RequestBody Map<String, String> map)
    {
        return JsonResult.Json(invoiceScanningApiService
                .editOrderInvoiceCheckStatusAndReasonByOrderId(map.get("orderId"), map.get("reason")));
    }

    /**
     * 计算订单享有发票的总金额--钱伟
     * 
     * @return
     */
    @GetMapping(value = "/order-invoce-amount", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getTotalInvoiceAmountByOrderId(String orderId, String invoiceCode, String supplierId)
    {
        return JsonResult
                .Json(invoiceScanningApiService.getTotalInvoiceAmountByOrderId(orderId, invoiceCode, supplierId));
    }

    /**
     * 统计待比对的订单--钱伟
     * 
     * @return
     */
    @GetMapping(value = "/order-no-comparation", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getNoComparationOrders()
    {
        return JsonResult.Json(invoiceScanningApiService.getNoComparationOrders());
    }

    /**
     * 查询比对不通过订单的所有开票人--钱伟
     * 
     * @return
     */
    @GetMapping(value = "/supplier-comparation-fail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getAllSupplierByComparationFail()
    {
        return JsonResult.Json(invoiceScanningApiService.getAllSupplierByComparationFail());
    }

    /**
     * 获取报关预录单的名称--钱伟
     * 
     * @return
     */
    @GetMapping(value = "/declaration-name", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getDeclarationNameByOrderId(String orderId)
    {
        return JsonResult.Json(invoiceScanningApiService.getDeclareName(orderId));
    }

    /**
     * 查询当前用户的权限--钱伟
     * 
     * @return
     */
    @GetMapping(value = "/menu-user", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getAuthByUserId(String userId)
    {
        return JsonResult.Json(invoiceScanningApiService.getAuthByUserId(userId));
    }

    /**
     * 查询当前用户的角色--钱伟
     * 
     * @return
     */
    @GetMapping(value = "/role-user", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getRoleByUserId(String userId)
    {
        return JsonResult.Json(invoiceScanningApiService.getRoleByUserId(userId));
    }

    /**
     * 退票--吴凡
     * 
     * @return
     */
    @GetMapping(value = "/refund-ticket", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult refundTicket(String invoiceId)
    {
        return JsonResult.Json(invoiceScanningApiService.updateSupplierInvoicesByApplicationId(invoiceId));
    }

    /**
     * 验证--孙全亚
     * 
     * @return
     */
    @GetMapping(value = "/verification", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult verificationInvoices(String orderId)
    {
        return JsonResult.Json(invoiceScanningApiService.verificationInvoices(orderId));
    }

}