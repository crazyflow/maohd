package com.cbecs.smc.invoice.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 接口模型发票
 * 
 * @author Administrator
 */
@Table(name = "invoice")
public class ApiInvoice implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 发票id
     */
    @Id(name = "invoice_id")
    private String invoiceId;
    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;
    /**
     * 订单code
     */
    @Column(name = "order_code")
    private String orderCode;
    /**
     * 申请单id
     */
    @Column(name = "application_id")
    private String applicationId;
    /**
     * 申请单code
     */
    @Column(name = "invoice_code")
    private String invoiceCode;
    /**
     * 发票号码
     */
    @Column(name = "invoice_number")
    private String invoiceNumber;
    /**
     * 发票日期
     */
    @Column(name = "invoice_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    /**
     * 卖方名称
     */
    @Column(name = "invoice_seller_name")
    private String invoiceSellerName;
    /**
     * 卖方税号
     */
    @Column(name = "invoice_seller_tax_number")
    private String invoiceSellerTaxNumber;
    /**
     * 卖方地址
     */
    @Column(name = "invoice_seller_address")
    private String invoiceSellerAddress;
    /**
     * 买方名称
     */
    @Column(name = "invoice_buyer_name")
    private String invoiceBuyerName;
    /**
     * 买方税号
     */
    @Column(name = "invoice_buyer_tax_number")
    private String invoiceBuyerTaxNumber;
    /**
     * 买方地址
     */
    @Column(name = "invoice_buyer_address")
    private String invoiceBuyerAddress;
    /**
     * 发票总金额
     */
    @Column(name = "invoice_total_amount")
    private double invoiceTotalAmount;
    /**
     * 发票税前金额
     */
    @Column(name = "invoice_befortax_amount")
    private double invoiceBefortaxAmount;
    /**
     * 发票税后金额
     */
    @Column(name = "invoice_tax_amount")
    private double invoiceTaxAmount;
    /**
     * 1:验证通过 2:验证未通过 3:比对不通过 4:比对通过 5:扫描退票 6:红冲
     */
    @Column(name = "invoice_status")
    private int invoiceStatus;
    /**
     * 0:正常票 (黑票) 1:红票
     */
    @Column(name = "invoice_red")
    private boolean invoiceRed;
    @Column(name = "created_by_id")
    private String createdById;
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "update_by_id")
    private String updateById;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "update_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateAt;
    @Column(name = "deleted")
    private boolean deleted;
    /**
     * 开票人名称--销方名称
     */
    @Column(name = "invoice_seller_id")
    private String invoiceSellerId;

    /**
     * 发票的订单信息
     */
    private List<ApiInvoiceProduct> products;

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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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

    public String getInvoiceSellerId()
    {
        return invoiceSellerId;
    }

    public void setInvoiceSellerId(String invoiceSellerId)
    {
        this.invoiceSellerId = invoiceSellerId;
    }

    public List<ApiInvoiceProduct> getProducts()
    {
        return products;
    }

    public void setProducts(List<ApiInvoiceProduct> products)
    {
        this.products = products;
    }

}