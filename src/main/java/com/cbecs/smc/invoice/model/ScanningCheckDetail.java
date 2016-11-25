package com.cbecs.smc.invoice.model;

/**
 * 比对详情
 * 
 * @author Administrator
 */
public class ScanningCheckDetail implements java.io.Serializable
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
     * 订单编号
     */
    private String companyId;

    /**
     * 订单编号
     */
    private String companyName;
    /**
     * 常规发票总张数
     */
    private int totalApplicationCount;

    /**
     * 通过认证发票张数
     */
    private int totalInvoiceCount;

    /**
     * 通过认证的发票总金额
     */
    private int totalInvoiceAmount;

    /**
     * 通过认证的发票总税额
     */
    private int totalInvoiceTaxAmount;
    /**
     * 发票是否收齐
     */
    private int isPurchaseInvoiceFinished;

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

    public int getTotalApplicationCount()
    {
        return totalApplicationCount;
    }

    public void setTotalApplicationCount(int totalApplicationCount)
    {
        this.totalApplicationCount = totalApplicationCount;
    }

    public int getTotalInvoiceCount()
    {
        return totalInvoiceCount;
    }

    public void setTotalInvoiceCount(int totalInvoiceCount)
    {
        this.totalInvoiceCount = totalInvoiceCount;
    }

    public int getTotalInvoiceAmount()
    {
        return totalInvoiceAmount;
    }

    public void setTotalInvoiceAmount(int totalInvoiceAmount)
    {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    public int getTotalInvoiceTaxAmount()
    {
        return totalInvoiceTaxAmount;
    }

    public void setTotalInvoiceTaxAmount(int totalInvoiceTaxAmount)
    {
        this.totalInvoiceTaxAmount = totalInvoiceTaxAmount;
    }

    public int getIsPurchaseInvoiceFinished()
    {
        return isPurchaseInvoiceFinished;
    }

    public void setIsPurchaseInvoiceFinished(int isPurchaseInvoiceFinished)
    {
        this.isPurchaseInvoiceFinished = isPurchaseInvoiceFinished;
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

}