package com.cbecs.smc.invoice.service.inf;

import java.util.Map;

import com.cbecs.omc.order.model.ApiOrderInfoForInvoiceQueryByPage;
import com.cbecs.omc.order.model.ApiSimpleOrder;
import com.cbecs.smc.invoice.model.ApiInvoice;
import com.cbecs.smc.invoice.model.ApiInvoiceApplicationQueryPageable;
import com.cbecs.smc.invoice.model.ApiInvoiceScanning;
import com.cbecs.smc.invoice.model.ApiSimpleInvoice;

public interface ApiInvoiceService
{

    /**
     * 查询订单的所有未比对的发票 --钱伟
     * 
     * @param orderId
     * @return
     */
    Map<String, Object> getNoComparisonInvoicesByOrderId(String orderId);

    /**
     * 查询所有的待扫描和待收票的申请单数据 --钱伟
     * 
     * @param model
     * @return
     */
    Map<String, Object> getNoCompletedApplications(ApiInvoiceApplicationQueryPageable model);

    /**
     * 获取发票申请单待收票的状态
     * 
     * @param model
     * @return
     */
    Map<String, Object> getInvoiceScanningCountByNoReceipt();

    /**
     * 获取发票申请单待扫描的状态
     * 
     * @param model
     * @return
     */
    Map<String, Object> getInvoiceScanningCountByNoScanning();

    /**
     * 查询发票详情--吴凡
     * 
     * @param model
     * @return
     */
    Map<String, Object> getInvoicesByCodeAndSupplierAndOrderId(String orderId, String invoiceCode, String supplierId);

    /**
     * 根据发票id获取发票详情--吴凡
     * 
     * @param invoiceId
     * @return
     */
    Map<String, Object> getInvoiceByInvoiceId(String invoiceId);

    /**
     * 获取所有客服--钱伟
     * 
     * @return
     */
    Map<String, Object> getCustomers();

    /**
     * 根据订单id获取订单发票的收取情况--钱伟
     * 
     * @param orderId
     * @return
     */
    Map<String, Object> getOrderReceiptInvoiceStatusByOrderId(String orderId);

    /**
     * 获取登录账户信息--潘明星
     * 
     * @param accountNumber
     *            账号
     * @param password
     *            密码
     * @return
     */
    Map<String, Object> getManagerUser(String accountNumber, String password);

    /**
     * 查询比对待收票和待比对的订单-钱伟
     * 
     * @param map
     * @return
     */
    Map<String, Object> getReceivingAndCheckingOrders(ApiOrderInfoForInvoiceQueryByPage model);

    /**
     * 查询比对待收票订单的数目-钱伟
     * 
     * @return
     */
    Map<String, Object> getReceivingOrdersForInvoiceCheckCount();

    /**
     * 查询待比对订单的数目-钱伟
     * 
     * @return
     */
    Map<String, Object> getCheckingOrdersForInvoiceCheckCount();

    /**
     * 查询订单下所有的开票人--钱伟
     * 
     * @param orderId
     * @return
     */
    Map<String, Object> getSuppliersFromOrderByOrderId(String orderId);

    /**
     * 查询所有比对不通过的发票--钱伟
     * 
     * @return
     */
    Map<String, Object> getInvoicesOfCheckingFail(String orderCode, String orderId, String invoiceCode,
            String supplier);

    /**
     * 发票新增--吴凡
     * 
     * @param model
     * @return
     */
    Map<String, Object> verificationInvoices(ApiInvoice model);

    /**
     * 新增发票--吴凡
     * 
     * @param model
     * @return
     */
    Map<String, Object> addInvoices(ApiInvoice model);

    /**
     * 修改比对不通过发票和订单状态
     * 
     * @param model
     * @return
     */
    Map<String, Object> editOrderAndInvoiceStatus(ApiSimpleOrder model);

    /**
     * 更改发票状态
     * 
     * @param model
     * @return
     */
    void editInvoiceStatusByCode(ApiSimpleInvoice model);

    /**
     * 更改发票扫描申请状态
     * 
     * @return
     */
    Map<String, Object> editInvoiceScanningApplicationStatusById(ApiInvoiceScanning model);

    /**
     * 更改发票扫描申请状态及添加原因
     * 
     * @return
     */
    Map<String, Object> editInvoiceScanningApplicationStatusAndReasonById(ApiInvoiceScanning model);

    /**
     * 更改订单发票比对状态之比对收票--钱伟
     * 
     * @param orderId
     * @return
     */
    Map<String, Object> editOrderInvoiceCheckStatusByOrderId(String orderId);

    /**
     * 更改订单发票比对状态之比对拒收票--钱伟
     * 
     * @param orderId
     * @param reason
     * @return
     */
    Map<String, Object> editOrderInvoiceCheckStatusAndReasonByOrderId(String orderId, String reason);

    /**
     * 计算订单享有发票的总金额--钱伟
     * 
     * @param orderId
     * @param invoiceCode
     * @param supplierId
     * @return
     */
    Map<String, Object> getTotalInvoiceAmountByOrderId(String orderId, String invoiceCode, String supplierId);

    /**
     * 统计待比对的订单--钱伟
     * 
     * @return
     */
    Map<String, Object> getNoComparationOrders();

    /**
     * 查询比对不通过订单的所有开票人--钱伟
     * 
     * @return
     */
    Map<String, Object> getAllSupplierByComparationFail();

    /**
     * 更改申请单状态为待扫描
     * 
     * @param model
     * @return
     */
    void editInvoiceScanningStatusByOrderId(ApiInvoiceScanning model);

    /**
     * 编辑发票信息
     * 
     * @param model
     * @return
     */
    Map<String, Object> editInvoice(ApiInvoice model);

    /**
     * 根据订单id获取报关预录单的名称
     * 
     * @param orderId
     * @return
     */
    Map<String, Object> getDeclareName(String orderId);

    /**
     * 申请单存在退票
     * 
     * @param model
     * @return
     */
    Map<String, Object> updateSupplierInvoicesByApplicationId(String invoiceId);

    /**
     * 获取当前用户的菜单
     * 
     * @param userId
     * @return
     */
    Map<String, Object> getAuthByUserId(String userId);

    /**
     * 获取用户角色
     * 
     * @param userId
     * @return
     */
    Map<String, Object> getRoleByUserId(String userId);

    /**
     * 验证,验证采购合同上的信息是否与发票扫描出来的结果一致
     * 
     * @param orderId
     * @return
     */
    Map<String, Object> verificationInvoices(String orderId);

}
