package com.cbecs.smc.invoice.model;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;

@Table(name = "invoice")
public class ApiSimpleInvoice
{
    /**
     * 发票代码
     */
    @Id(name = "invoice_id")
    private String invoiceId;
    /**
     * 发票代码
     */
    @Column(name = "invoice_code")
    private String invoiceCode;
    /**
     * 发票号码
     */
    @Column(name = "invoice_number")
    private String invoiceNumber;
    /**
     * 发票状态
     */
    @Column(name = "invoice_status")
    private int invoiceStatus;
    @Column(name = "invoice_check_fail")
    private int invoiceCheckFail;

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

    public int getInvoiceStatus()
    {
        return invoiceStatus;
    }

    public void setInvoiceStatus(int invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }

    public int getInvoiceCheckFail()
    {
        return invoiceCheckFail;
    }

    public void setInvoiceCheckFail(int invoiceCheckFail)
    {
        this.invoiceCheckFail = invoiceCheckFail;
    }

    public String getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId;
    }

}
