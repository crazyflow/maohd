package com.cbecs.smc.invoice.model;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;

/**
 * 发票扫描申请-修改model
 * 
 * @author Administrator
 */
@Table(name = "invoice_scanning")
public class ScanningModification implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 申请单号
     */
    @Id(name = "application_id")
    private String applicationId;

    /**
     * 常规发票张数
     */
    @Column(name = "regular_invoice_count")
    private int regularInvoiceCount;
    /**
     * 红票张数
     */
    @Column(name = "red_invoice_count")
    private int redInvoiceCount;

    /**
     * 发票状态
     */
    @Column(name = "application_status")
    private int applicationStatus;

    public String getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId(String applicationId)
    {
        this.applicationId = applicationId;
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

    public int getApplicationStatus()
    {
        return applicationStatus;
    }

    public void setApplicationStatus(int applicationStatus)
    {
        this.applicationStatus = applicationStatus;
    }

}