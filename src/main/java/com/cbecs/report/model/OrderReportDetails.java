package com.cbecs.report.model;

import java.util.Date;

public class OrderReportDetails implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 2124941536279215875L;
    
    private Date orderDate;
    
    private String orderCode;
    
    private String companyName;
    
    private String customerServiceName;
    
    private String customerServiceId;
    
    private String supplierName;
    
    private double invoiceAmount;
    
    private double declarationAmount;
    
    private String currencySymbol;
    
    private double rate;
    
    private double amount;
    
    private String beginDate;
    
    private String endDate;
    
    private String orderId;
    
    private String companyId;
    
    private String supplierId;

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCustomerServiceName()
    {
        return customerServiceName;
    }

    public void setCustomerServiceName(String customerServiceName)
    {
        this.customerServiceName = customerServiceName;
    }

    public String getCustomerServiceId()
    {
        return customerServiceId;
    }

    public void setCustomerServiceId(String customerServiceId)
    {
        this.customerServiceId = customerServiceId;
    }

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public double getInvoiceAmount()
    {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount)
    {
        this.invoiceAmount = invoiceAmount;
    }

    public double getDeclarationAmount()
    {
        return declarationAmount;
    }

    public void setDeclarationAmount(double declarationAmount)
    {
        this.declarationAmount = declarationAmount;
    }

    public String getCurrencySymbol()
    {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol)
    {
        this.currencySymbol = currencySymbol;
    }

    public double getRate()
    {
        return rate;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(String beginDate)
    {
        this.beginDate = beginDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(String companyId)
    {
        this.companyId = companyId;
    }

    public String getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(String supplierId)
    {
        this.supplierId = supplierId;
    }
    
}