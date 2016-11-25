package com.cbecs.smc.invoice.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;

/**
 * 发票扫描申请--新增model
 * 
 * @author Administrator
 */
@Table(name = "invoice_scanning")
public class ApiInvoiceScanning implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 申请单号
     */
    @Id(name = "application_id")
    private String applicationId;
    /**
     * 申请单号
     */
    @Column(name = "application_code")
    private String applicationCode;
    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;
    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;
    /**
     * 委托商id
     */
    @Column(name = "company_id")
    private String companyId;
    /**
     * 委托商name
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 开票人信息
     */
    private List<InvoiceSupplier> suppliers;
    
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
     * 已扫张数
     */
    @Column(name = "scanned_ticket_count")
    private int scannedTicketCount;
    /**
     * 退票张数
     */
    @Column(name = "refund_ticket_count")
    private int refundTicketCount;
    /**
     * 退票理由
     */
    @Column(name = "reject_ticket_reason")
    private String rejectTicketReason;
    /**
     * 申请日期
     */
    @Column(name = "application_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applicationDate;
    /**
     * 申请单状态 1-扫描待收票 2-待扫描 3-扫描拒收票 4-扫描通过 9-扫描退票
     */
    @Column(name = "application_status")
    private int applicationStatus;
    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;
    /**
     * 创建人id
     */
    @Column(name = "created_by_id")
    private String createdById;
    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;
    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;
    /**
     * 更新人id
     */
    @Column(name = "updated_by_id")
    private String updatedById;
    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private String updatedBy;
    /**
     * 是否删除
     */
    @Column(name = "deleted")
    private int deleted;

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

    public int getScannedTicketCount()
    {
        return scannedTicketCount;
    }

    public void setScannedTicketCount(int scannedTicketCount)
    {
        this.scannedTicketCount = scannedTicketCount;
    }

    public int getRefundTicketCount()
    {
        return refundTicketCount;
    }

    public void setRefundTicketCount(int refundTicketCount)
    {
        this.refundTicketCount = refundTicketCount;
    }

    public String getRejectTicketReason()
    {
        return rejectTicketReason;
    }

    public void setRejectTicketReason(String rejectTicketReason)
    {
        this.rejectTicketReason = rejectTicketReason;
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

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getCreatedById()
    {
        return createdById;
    }

    public void setCreatedById(String createdById)
    {
        this.createdById = createdById;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedById()
    {
        return updatedById;
    }

    public void setUpdatedById(String updatedById)
    {
        this.updatedById = updatedById;
    }

    public String getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public int getDeleted()
    {
        return deleted;
    }

    public void setDeleted(int deleted)
    {
        this.deleted = deleted;
    }

}