package com.cbecs.smc.invoice.model;

import java.util.List;

/**
 * 接口模型发票
 * 
 * @author Administrator
 */
public class ApiInvoiceSupplier implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 开票人id
     */
    private String invoiceSellerId;
    /**
     * 开票人名称
     */
    private String invoiceSellerName;
    /**
     * 纳税识别证号
     */
    private String invoiceSellerTaxNumber;
    /**
     * 开票人商品
     */
    private List<ApiInvoiceProduct> products;

    public String getInvoiceSellerId()
    {
        return invoiceSellerId;
    }

    public void setInvoiceSellerId(String invoiceSellerId)
    {
        this.invoiceSellerId = invoiceSellerId;
    }

    public String getInvoiceSellerTaxNumber()
    {
        return invoiceSellerTaxNumber;
    }

    public void setInvoiceSellerTaxNumber(String invoiceSellerTaxNumber)
    {
        this.invoiceSellerTaxNumber = invoiceSellerTaxNumber;
    }

    public List<ApiInvoiceProduct> getProducts()
    {
        return products;
    }

    public void setProducts(List<ApiInvoiceProduct> products)
    {
        this.products = products;
    }

    public String getInvoiceSellerName()
    {
        return invoiceSellerName;
    }

    public void setInvoiceSellerName(String invoiceSellerName)
    {
        this.invoiceSellerName = invoiceSellerName;
    }

}