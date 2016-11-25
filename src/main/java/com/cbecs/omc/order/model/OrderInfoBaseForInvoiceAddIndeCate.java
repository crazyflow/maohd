package com.cbecs.omc.order.model;

import java.util.List;

import com.cbecs.smc.invoice.model.InvoiceSupplier;

/**
 * 发票扫描选择订单列表展示
 * 
 * @author Administrator
 */
public class OrderInfoBaseForInvoiceAddIndeCate implements java.io.Serializable
{

    private static final long serialVersionUID = 4275379096951643123L;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单code
     */
    private String orderCode;
    /**
     * 委托商id
     */
    private String companyId;
    /**
     * 委托商名称
     */
    private String companyName;

    private List<InvoiceSupplier> suppliers;
    /**
     * 是否存在红票,在选择订单的时候已经过滤掉订单发票完成的情况 1-存在 0-不存在
     * 
     * @return
     */
    private int redInvoice;

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

    public int getRedInvoice()
    {
        return redInvoice;
    }

    public void setRedInvoice(int redInvoice)
    {
        this.redInvoice = redInvoice;
    }

    public List<InvoiceSupplier> getSuppliers()
    {
        return suppliers;
    }

    public void setSuppliers(List<InvoiceSupplier> suppliers)
    {
        this.suppliers = suppliers;
    }

}
