package com.cbecs.smc.invoice.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 发票扫描申请详情
 * 
 * @author Administrator
 */
public class ScanningEdition implements java.io.Serializable
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
     * 委托商id
     */
    private String companyId;
    /**
     * 委托商name
     */
    private String companyName;
    /**
     * 开票人信息
     */
    private List<InvoiceSupplier> suppliers;
    /**
     * 常规发票张数
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
     * 拒票理由
     */
    private String rejectTicketReason;

    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 是否存在红票
     */
    private int redInvoice;

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

    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(String companyId)
    {
        this.companyId = companyId;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public List<InvoiceSupplier> getSuppliers()
    {
        return suppliers;
    }

    public void setSuppliers(List<InvoiceSupplier> suppliers)
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

    public String getRejectTicketReason()
    {
        return rejectTicketReason;
    }

    public void setRejectTicketReason(String rejectTicketReason)
    {
        this.rejectTicketReason = rejectTicketReason;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public int getRedInvoice()
    {
        return redInvoice;
    }

    public void setRedInvoice(int redInvoice)
    {
        this.redInvoice = redInvoice;
    }

}