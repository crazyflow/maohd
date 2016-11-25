package com.cbecs.smc.invoice.service.inf;

import java.util.List;

import com.cbecs.smc.invoice.model.CustomerService;
import com.cbecs.smc.invoice.model.Invoice;
import com.cbecs.smc.invoice.model.InvoiceList;
import com.cbecs.smc.invoice.model.InvoiceProduct;
import com.cbecs.smc.invoice.model.ManagementList;
import com.cbecs.smc.invoice.model.ManagementQueryPageable;
import com.cbecs.smc.invoice.model.OrderInvoiceSummary;
 
public interface InvoiceManagementService
{
    /**
     * 查询发票管理的信息
     * @param model
     * @return
     */
    List<ManagementList> list(ManagementQueryPageable model);

    /**
     * 查询所有客服
     * @param type 1-客服 2-外贸顾问
     * @return
     */
    List<CustomerService> customers(int type);
    
    /**
     * 获取发票管理下红冲发票的数量
     * @return
     */
    int getOrderInvoiceRedCount();
    
    /**
     * 获取订单发票概况
     * @param orderId
     * @return
     */
    OrderInvoiceSummary getOrderInvoiceSummary(String orderId);
    
    /**
     * 查询订单发票列表
     * @param orderId
     * @param invoiceNumber
     * @param supplierId
     * @return
     */
    List<InvoiceList> getInvoiceList(String orderId, String invoiceNumber, String supplierId);
    
    /**
     * 查询订单下的开票人
     * @param orderId
     * @return
     */
    List<CustomerService> suppliers(String orderId);
    
    /**
     * 设置订单和发票红冲状态
     * @param invoiceId
     * @param orderId
     * @return true-执行成功；false-执行失败
     */
    void setInvoiceRed(String invoiceId, String orderId);
    
    /**
     * 判断发票是否能红冲
     * @param orderId
     * @return 0-可以红冲；1-已做退税垫付；2-已导入退税；3-已做退税垫付和导入退税
     */
    int isInvoiceCanSetRed(String orderId);
    
    /**
     * 查询单张发票详细信息
     * @param invoiceId
     * @return
     */
    Invoice getInvoiceDetail(String invoiceId);
    
    /**
     * 查询发票对应的商品发票信息
     * @param invoiceId
     * @return
     */
    List<InvoiceProduct> getInvoiceProductList(String invoiceId);
}
