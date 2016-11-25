package com.cbecs.smc.invoice.model;

/**
 * 申请详情--开票人信息
 */
public class InvoiceDetailSupplier implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 开票人id
     */
    private String supplierId;
    /**
     * 开票人name
     */
    private String supplierName;
    /**
     * 来票张数
     */
    private int invoiceCount;
    /**
     * 发票含税总金额
     */
    private double invoiceBefoetaxAmount;
    /**
     * 发票金额合计
     */
    private double invoiceTotalAmount;
    /**
     * 发票税额合计
     */
    private double invoiceTaxAmount;

    /**
     * 状态
     */
    private String supplierStatus;

    public String getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(String supplierId)
    {
        this.supplierId = supplierId;
    }

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public double getInvoiceBefoetaxAmount()
    {
        return invoiceBefoetaxAmount;
    }

    public void setInvoiceBefoetaxAmount(double invoiceBefoetaxAmount)
    {
        this.invoiceBefoetaxAmount = invoiceBefoetaxAmount;
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

    public String getSupplierStatus()
    {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus)
    {
        this.supplierStatus = supplierStatus;
    }

    public int getInvoiceCount()
    {
        return invoiceCount;
    }

    public void setInvoiceCount(int invoiceCount)
    {
        this.invoiceCount = invoiceCount;
    }

}