package com.cbecs.omc.order.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cbecs.framework.mybatis.pagination.Pageable;

public class OrderInfoCommissionQueryByPage extends Pageable implements java.io.Serializable
{

    private static final long serialVersionUID = 1366442964398020285L;

    private String orderId;
    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 订单完成日期开始
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /**
     * 订单完成日期结束
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 委托客户
     */
    private String companyName;

    /**
     * 业务主体
     */
    private int businessMainBody;

    /**
     * 渠道商
     */
    private String channelId;

    /**
     * 已经选择的订单
     */
    private String exceptOrder;

    /**
     * 结算单号--编辑时需要传过去
     */
    private String applicationId;

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

    public Date getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public int getBusinessMainBody()
    {
        return businessMainBody;
    }

    public void setBusinessMainBody(int businessMainBody)
    {
        this.businessMainBody = businessMainBody;
    }

    public String getChannelId()
    {
        return channelId;
    }

    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }

    public String getExceptOrder()
    {
        return exceptOrder;
    }

    public void setExceptOrder(String exceptOrder)
    {
        this.exceptOrder = exceptOrder;
    }

    public String getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId(String applicationId)
    {
        this.applicationId = applicationId;
    }

}
