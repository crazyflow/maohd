package com.cbecs.smc.invoice.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 申请单list
 * 
 * @author Administrator
 */
public class ScanningList implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 申请单号
     */
    private String applicationId;

    /**
     * 申请单号
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
    private String suppliers;
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
    private int applicationStatus;
    /**
     * 拒收票理由
     */
    private String rejectTicketReason;
    /**
     * 订单比对状态 10-比对待收票 20-比对拒收票 30-待认证 40-比对通过 50-比对不通过
     */
    private int orderInvoiceCheckStatus;

    /**
     * 比对拒收票原因
     */
    private String orderRefuseCollectInvoiceReason;

    public String getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId(String applicationId)
    {
        this.applicationId = applicationId;
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

    public String getSuppliers()
    {
        return suppliers;
    }

    public void setSuppliers(String suppliers)
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

    public int getApplicationStatus()
    {
        return applicationStatus;
    }

    public void setApplicationStatus(int applicationStatus)
    {
        this.applicationStatus = applicationStatus;
    }

    public String getRejectTicketReason()
    {
        return rejectTicketReason;
    }

    public void setRejectTicketReason(String rejectTicketReason)
    {
        this.rejectTicketReason = rejectTicketReason;
    }

    public int getOrderInvoiceCheckStatus()
    {
        return orderInvoiceCheckStatus;
    }

    public void setOrderInvoiceCheckStatus(int orderInvoiceCheckStatus)
    {
        this.orderInvoiceCheckStatus = orderInvoiceCheckStatus;
    }

    public String getApplicationCode()
    {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode)
    {
        this.applicationCode = applicationCode;
    }

    public String getOrderRefuseCollectInvoiceReason()
    {
        return orderRefuseCollectInvoiceReason;
    }

    public void setOrderRefuseCollectInvoiceReason(String orderRefuseCollectInvoiceReason)
    {
        this.orderRefuseCollectInvoiceReason = orderRefuseCollectInvoiceReason;
    }

}