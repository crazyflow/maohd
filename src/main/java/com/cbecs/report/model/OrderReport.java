package com.cbecs.report.model;

import com.cbecs.framework.mybatis.annotations.Table;
/**
 * 委托方
 */
@Table(name = "OrderInfo")
public class OrderReport implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 2124941536279215875L;

    /**
     * 客服名称
     */
    private String customerServiceName;
    
    /**
     * 订单总数。
     */
    private int orderCount;
    
    /**
     * 订单金额
     */
    private double orderAmount;
    
    /**
     * 客服ID
     */
    private String customerServiceId;
    
    /**
     * 委托人名称
     */
    private String companyName;
    
    /**
     * 物流供应商名称
     */
    private String orderCode;
    
    /**
     * 订单日期开始日期和结束日期
     */
    private String beginDate;
    private String endDate;
    
    /**
     * 开票人名称
     */
    private String supplierName;

    public String getCustomerServiceName()
    {
        return customerServiceName;
    }

    public void setCustomerServiceName(String customerServiceName)
    {
        this.customerServiceName = customerServiceName;
    }

    public int getOrderCount()
    {
        return orderCount;
    }

    public void setOrderCount(int orderCount)
    {
        this.orderCount = orderCount;
    }

    public double getOrderAmount()
    {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount)
    {
        this.orderAmount = orderAmount;
    }

    public String getCustomerServiceId()
    {
        return customerServiceId;
    }

    public void setCustomerServiceId(String customerServiceId)
    {
        this.customerServiceId = customerServiceId;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
  
    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
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

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }
}