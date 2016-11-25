package com.cbecs.smc.invoice.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 列表model
 * 
 * @author chenlong
 */
public class ManagementList implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;

    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 发票张数
     */
    private int invoiceCount;
    /**
     * 发票含税总金额
     */
    private double orderInvoiceTotalAmount;
    /**
     * 发票金额合计
     */
    private double orderInvoiceBeforeTaxAmount;
    /**
     * 发票税额合计
     */
    private double orderInvoiceTaxAmount;
    /**
     * 申请日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderTransferDate;
    /**
     * 状态 1-收齐 2-红冲
     */
    private int status;
    /**
     * 客服
     */
    private String customerServiceName;
    
    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public int getInvoiceCount()
    {
        return invoiceCount;
    }

    public void setInvoiceCount(int invoiceCount)
    {
        this.invoiceCount = invoiceCount;
    }

    public double getOrderInvoiceTotalAmount()
    {
        return orderInvoiceTotalAmount;
    }

    public void setOrderInvoiceTotalAmount(double orderInvoiceTotalAmount)
    {
        this.orderInvoiceTotalAmount = orderInvoiceTotalAmount;
    }

    public double getOrderInvoiceBeforeTaxAmount()
    {
        return orderInvoiceBeforeTaxAmount;
    }

    public void setOrderInvoiceBeforeTaxAmount(double orderInvoiceBeforeTaxAmount)
    {
        this.orderInvoiceBeforeTaxAmount = orderInvoiceBeforeTaxAmount;
    }

    public double getOrderInvoiceTaxAmount()
    {
        return orderInvoiceTaxAmount;
    }

    public void setOrderInvoiceTaxAmount(double orderInvoiceTaxAmount)
    {
        this.orderInvoiceTaxAmount = orderInvoiceTaxAmount;
    }
    
    public Date getOrderTransferDate()
    {
        return orderTransferDate;
    }

    public void setOrderTransferDate(Date orderTransferDate)
    {
        this.orderTransferDate = orderTransferDate;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getCustomerServiceName()
    {
        return customerServiceName;
    }

    public void setCustomerServiceName(String customerServiceName)
    {
        this.customerServiceName = customerServiceName;
    }

}