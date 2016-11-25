package com.cbecs.smc.invoice.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 比对详情列表
 * 
 * @author Administrator
 */
public class InvoiceCheckDetail implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;

    /**
     * 发票id
     */
    private String invoiceId;
    /**
     * 发票code
     */
    private String invoiceCode;
    /**
     * 发票号码
     */
    private String invoiceNumber;
    /**
     * 发票日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    /**
     * 卖方名称
     */
    private String invoiceSellerName;
    /**
     * 卖方税号
     */
    private String invoiceSellerTaxNumber;
    /**
     * 发票总金额
     */
    private double invoiceTotalAmount;
    /**
     * 发票税后金额
     */
    private double invoiceTaxAmount;
    /**
     * 1:验证通过 2:验证未通过 3:比对不通过 4:比对通过 5:扫描退票 6:红冲
     */
    private int invoiceStatus;

    public String getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId;
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

    public double getInvoiceTotalAmount()
    {
        return invoiceTotalAmount;
    }

    public void setInvoiceTotalAmount(double invoiceTotalAmount)
    {
        this.invoiceTotalAmount = invoiceTotalAmount;
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

}