package com.cbecs.smc.invoice.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cbecs.framework.mybatis.pagination.Pageable;

/**
 * 接口模型--钱伟 查询发票申请单信息
 * 
 * @author Administrator
 */
public class ApiInvoiceApplicationQueryPageable extends Pageable implements java.io.Serializable
{
    private static final long serialVersionUID = 1812033798262783211L;
    /**
     * 扫描申请单号
     */
    private String applicationCode;
    /**
     * 订单code
     */
    private String orderCode;
    /**
     * 客服
     */
    private String customer;
    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * 结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    /**
     * 申请单状态
     */
    private int applicationStatus;
    /**
     * 订单状态
     */
    private int orderInvoiceCheckStatus;

    public String getApplicationCode()
    {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode)
    {
        this.applicationCode = applicationCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getCustomer()
    {
        return customer;
    }

    public void setCustomer(String customer)
    {
        this.customer = customer;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public int getApplicationStatus()
    {
        return applicationStatus;
    }

    public void setApplicationStatus(int applicationStatus)
    {
        this.applicationStatus = applicationStatus;
    }

    public int getOrderInvoiceCheckStatus()
    {
        return orderInvoiceCheckStatus;
    }

    public void setOrderInvoiceCheckStatus(int orderInvoiceCheckStatus)
    {
        this.orderInvoiceCheckStatus = orderInvoiceCheckStatus;
    }

}