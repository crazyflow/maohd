package com.cbecs.smc.invoice.model;

import java.util.Date;

/**
 * 发票表
 * 
 * @author Administrator
 */
public class Invoice implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;

    /**
     * 发票id
     */
    public String invoiceId;
    /**
     * 订单id
     */
    public String orderId;
    /**
     * 订单code
     */
    public String orderCode;
    /**
     * 申请单id
     */
    public String applicationId;
    /**
     * 发票代码
     */
    public String invoiceCode;
    /**
     * 发票号码
     */
    public String invoiceNumber;
    /**
     * 发票日期
     */
    public Date invoiceDate;
    /**
     * 销方名称
     */
    public String invoiceSellerName;
    /**
     * 销方税号
     */
    public String invoiceSellerTaxNumber;
    /**
     * 销方地址
     */
    public String invoiceSellerAddress;
    /**
     * 买方名称
     */
    public String invoiceBuyerName;
    /**
     * 买方税号
     */
    public String invoiceBuyerTaxNumber;
    /**
     * 买方地址
     */
    public String invoiceBuyerAddress;
    /**
     * 发票总金额
     */
    public double invoiceTotalAmount;
    /**
     * 发票税前金额
     */
    public double invoiceBefortaxAmount;
    /**
     * 发票税后金额
     */
    public double invoiceTaxAmount;
    /**
     * 1:验证通过 2:验证未通过 3:比对不通过 4:比对通过 5:扫描退票 6:红冲
     */
    public int invoiceStatus;
    /**
     * 0:正常票 (黑票) 1:红票
     */
    public boolean invoiceRed;
    public String createdById;
    public Date createdAt;
    public String createdBy;
    public String updateById;
    public String updateBy;
    public Date updateAt;
    public boolean deleted;

    public String getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId;
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

    public String getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId(String applicationId)
    {
        this.applicationId = applicationId;
    }

    public String getInvoiceCode()
    {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode)
    {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate()
    {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate)
    {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceSellerName()
    {
        return invoiceSellerName;
    }

    public void setInvoiceSellerName(String invoiceSellerName)
    {
        this.invoiceSellerName = invoiceSellerName;
    }

    public String getInvoiceSellerTaxNumber()
    {
        return invoiceSellerTaxNumber;
    }

    public void setInvoiceSellerTaxNumber(String invoiceSellerTaxNumber)
    {
        this.invoiceSellerTaxNumber = invoiceSellerTaxNumber;
    }

    public String getInvoiceSellerAddress()
    {
        return invoiceSellerAddress;
    }

    public void setInvoiceSellerAddress(String invoiceSellerAddress)
    {
        this.invoiceSellerAddress = invoiceSellerAddress;
    }

    public String getInvoiceBuyerName()
    {
        return invoiceBuyerName;
    }

    public void setInvoiceBuyerName(String invoiceBuyerName)
    {
        this.invoiceBuyerName = invoiceBuyerName;
    }

    public String getInvoiceBuyerTaxNumber()
    {
        return invoiceBuyerTaxNumber;
    }

    public void setInvoiceBuyerTaxNumber(String invoiceBuyerTaxNumber)
    {
        this.invoiceBuyerTaxNumber = invoiceBuyerTaxNumber;
    }

    public String getInvoiceBuyerAddress()
    {
        return invoiceBuyerAddress;
    }

    public void setInvoiceBuyerAddress(String invoiceBuyerAddress)
    {
        this.invoiceBuyerAddress = invoiceBuyerAddress;
    }

    public double getInvoiceTotalAmount()
    {
        return invoiceTotalAmount;
    }

    public void setInvoiceTotalAmount(double invoiceTotalAmount)
    {
        this.invoiceTotalAmount = invoiceTotalAmount;
    }

    public double getInvoiceBefortaxAmount()
    {
        return invoiceBefortaxAmount;
    }

    public void setInvoiceBefortaxAmount(double invoiceBefortaxAmount)
    {
        this.invoiceBefortaxAmount = invoiceBefortaxAmount;
    }

    public double getInvoiceTaxAmount()
    {
        return invoiceTaxAmount;
    }

    public void setInvoiceTaxAmount(double invoiceTaxAmount)
    {
        this.invoiceTaxAmount = invoiceTaxAmount;
    }

    public int getInvoiceStatus()
    {
        return invoiceStatus;
    }

    public void setInvoiceStatus(int invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }

    public boolean isInvoiceRed()
    {
        return invoiceRed;
    }

    public void setInvoiceRed(boolean invoiceRed)
    {
        this.invoiceRed = invoiceRed;
    }

    public String getCreatedById()
    {
        return createdById;
    }

    public void setCreatedById(String createdById)
    {
        this.createdById = createdById;
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

    public String getUpdateById()
    {
        return updateById;
    }

    public void setUpdateById(String updateById)
    {
        this.updateById = updateById;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    public Date getUpdateAt()
    {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt)
    {
        this.updateAt = updateAt;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

}