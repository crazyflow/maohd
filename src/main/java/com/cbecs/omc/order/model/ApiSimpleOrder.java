package com.cbecs.omc.order.model;

import java.util.List;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;
import com.cbecs.smc.invoice.model.ApiSimpleInvoice;

/**
 * 接口模型--订单扫描状态
 * 
 * @author Administrator
 */
@Table(name = "OrderInfo")
public class ApiSimpleOrder implements java.io.Serializable
{
    private static final long serialVersionUID = -4339080747057122495L;
    /**
     * 订单id
     */
    @Id(name = "ID")
    private String orderId;
    /**
     * 订单的扫描单信息
     */
    private List<ApiSimpleInvoice> simpleInvoices;
    /**
     * 是否发票收齐
     */
    @Column(name = "isPurchaseInvoiceFinished")
    private int isPurchaseInvoiceFinished;
    /**
     * 订单发票比对状态
     */
    @Column(name = "orderInvoiceCheckStatus")
    private int orderInvoiceCheckStatus;

    /**
     * 订单发票比对状态
     */
    private int applicationStatus;


    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public List<ApiSimpleInvoice> getSimpleInvoices()
    {
        return simpleInvoices;
    }

    public void setSimpleInvoices(List<ApiSimpleInvoice> simpleInvoices)
    {
        this.simpleInvoices = simpleInvoices;
    }

    public int getIsPurchaseInvoiceFinished()
    {
        return isPurchaseInvoiceFinished;
    }

    public void setIsPurchaseInvoiceFinished(int isPurchaseInvoiceFinished)
    {
        this.isPurchaseInvoiceFinished = isPurchaseInvoiceFinished;
    }

    public int getOrderInvoiceCheckStatus()
    {
        return orderInvoiceCheckStatus;
    }

    public void setOrderInvoiceCheckStatus(int orderInvoiceCheckStatus)
    {
        this.orderInvoiceCheckStatus = orderInvoiceCheckStatus;
    }

    public int getApplicationStatus()
    {
        return applicationStatus;
    }

    public void setApplicationStatus(int applicationStatus)
    {
        this.applicationStatus = applicationStatus;
    }


}
