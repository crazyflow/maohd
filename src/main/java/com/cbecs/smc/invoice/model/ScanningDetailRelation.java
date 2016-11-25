package com.cbecs.smc.invoice.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 详情，关联其他申请单
 * 
 * @author Administrator
 */
public class ScanningDetailRelation implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 申请id
     */
    private String applicationId;
    /**
     * 申请code
     */
    private String applicationCode;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 开票人信息
     */
    private List<InvoiceDetailSupplier> suppliers;
    /**
     * 常规发票
     */
    private int regularInvoiceCount;
    /**
     * 红票张数
     */
    private int redInvoiceCount;

    /**
     * 申请日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applicationDate;
    /**
     * 申请单状态 1-扫描待收票 2-待扫描 3-扫描拒收票 4-扫描通过 9-扫描退票
     */
    private int invoiceStatus;

    public String getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId(String applicationId)
    {
        this.applicationId = applicationId;
    }

    public String getApplicationCode()
    {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode)
    {
        this.applicationCode = applicationCode;
    }

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

    public List<InvoiceDetailSupplier> getSuppliers()
    {
        return suppliers;
    }

    public void setSuppliers(List<InvoiceDetailSupplier> suppliers)
    {
        this.suppliers = suppliers;
    }

    public int getRegularInvoiceCount()
    {
        return regularInvoiceCount;
    }

    public void setRegularInvoiceCount(int regularInvoiceCount)
    {
        this.regularInvoiceCount = regularInvoiceCount;
    }

    public int getRedInvoiceCount()
    {
        return redInvoiceCount;
    }

    public void setRedInvoiceCount(int redInvoiceCount)
    {
        this.redInvoiceCount = redInvoiceCount;
    }

    public Date getApplicationDate()
    {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate)
    {
        this.applicationDate = applicationDate;
    }

    public int getInvoiceStatus()
    {
        return invoiceStatus;
    }

    public void setInvoiceStatus(int invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }

}