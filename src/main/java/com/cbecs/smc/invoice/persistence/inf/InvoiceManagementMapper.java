package com.cbecs.smc.invoice.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.cbecs.smc.invoice.model.CustomerService;
import com.cbecs.smc.invoice.model.Invoice;
import com.cbecs.smc.invoice.model.InvoiceList;
import com.cbecs.smc.invoice.model.InvoiceProduct;
import com.cbecs.smc.invoice.model.ManagementList;
import com.cbecs.smc.invoice.model.ManagementQueryPageable;
import com.cbecs.smc.invoice.model.OrderInvoiceSummary;
import com.cbecs.smc.invoice.persistence.SelectSqlProvider;

public interface InvoiceManagementMapper
{
    /**
     * 查询发票管理列表
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectManagementList", type = SelectSqlProvider.class)
    List<ManagementList> selectList(ManagementQueryPageable model);
    
    /**
     * 查询用户列表
     * @param type 1-客服 2-外贸顾问
     * @return
     */
    @SelectProvider(method = "selectCustomerServiceList", type = SelectSqlProvider.class)
    List<CustomerService> selectCustomerServiceList(String typeName);
    
    /**
     * 查询红冲数量
     * @return
     */
    @SelectProvider(method = "selectRedStatusCount", type = SelectSqlProvider.class)
    int selectRedStatusCount();
    
    /**
     * 查询订单发票概况
     * @param orderId
     * @return
     */
    @SelectProvider(method = "selectOrderInvoiceSummary", type = SelectSqlProvider.class)
    OrderInvoiceSummary selectOrderInvoiceSummary(String orderId);
    
    /**
     * 查询订单发票列表
     * @param orderId
     * @param invoiceNumber
     * @param supplierId
     * @return
     */
    @SelectProvider(method = "selectInvoiceList", type = SelectSqlProvider.class)
    List<InvoiceList> selectInvoiceList(String orderId, String invoiceNumber, String supplierId);
    
    /**
     * 查询订单下的开票人
     * @param orderId
     * @return
     */
    @SelectProvider(method = "selectSupplierList", type = SelectSqlProvider.class)
    List<CustomerService> selectSupplierList(String orderId);
    
    /**
     * 设置订单为红冲状态
     * @param orderId
     * @return
     */
    @SelectProvider(method = "updateOrderInvoiceStatus", type = SelectSqlProvider.class)
    void setOrderRed(String orderId);
    
    /**
     * 设置发票为红冲状态
     * @param invoiceId
     * @return
     */
    @SelectProvider(method = "updateInvoiceStatus", type = SelectSqlProvider.class)
    void setInvoiceRed(String invoiceId);
    
    /**
     * 查询订单是否做过退税垫付申请
     * @param orderId
     * @return
     */
    @SelectProvider(method = "selectAdvanceRefundApplyCount", type = SelectSqlProvider.class)
    int selectAdvanceRefundApplyCount(String orderId);
    
    /**
     * 查询订单是否做过退税
     * @param orderId
     * @return
     */
    @SelectProvider(method = "selectRefundTaxCount", type = SelectSqlProvider.class)
    int selectRefundTaxCount(String orderId);
    
    /**
     * 查询单张发票信息
     * @param invoiceId
     * @return
     */
    @SelectProvider(method = "selectInvoice", type = SelectSqlProvider.class)
    Invoice selectInvoice(String invoiceId);
    
    /**
     * 查询发票内的商品信息
     * @param invoiceId
     * @return
     */
    @SelectProvider(method = "selectInvoiceProductList", type = SelectSqlProvider.class)
    List<InvoiceProduct> selectInvoiceProductList(String invoiceId);
}