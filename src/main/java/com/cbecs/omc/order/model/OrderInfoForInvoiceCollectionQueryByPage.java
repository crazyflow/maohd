package com.cbecs.omc.order.model;

import com.cbecs.framework.mybatis.pagination.Pageable;

/**
 * 发票扫描列表订单选择列表的查询条件
 * 
 * @author Administrator
 */
public class OrderInfoForInvoiceCollectionQueryByPage extends Pageable implements java.io.Serializable
{

    private static final long serialVersionUID = 1366442964398020285L;

    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 开票人
     */
    private String suppliers;
    /**
     * 已经排除了的页面的订单号
     */
    private String exceptOrder;
    /**
     * 订单客服
     */
    private String customerService;

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getSuppliers()
    {
        return suppliers;
    }

    public void setSuppliers(String suppliers)
    {
        this.suppliers = suppliers;
    }

    public String getExceptOrder()
    {
        return exceptOrder;
    }

    public void setExceptOrder(String exceptOrder)
    {
        this.exceptOrder = exceptOrder;
    }

    public String getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(String customerService)
    {
        this.customerService = customerService;
    }

}
