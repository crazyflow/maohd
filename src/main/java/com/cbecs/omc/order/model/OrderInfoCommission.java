package com.cbecs.omc.order.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderInfoCommission implements java.io.Serializable
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
     * 订单完成日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 委托客户
     */
    private String companyName;

    /**
     * 委托客户id
     */
    private String companyId;

    /**
     * 业务主体
     */
    private String businessMainBody;

    /**
     * 渠道商
     */
    private String channelId;

    /**
     * 渠道商
     */
    private String channelName;

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

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(String companyId)
    {
        this.companyId = companyId;
    }

    public String getBusinessMainBody()
    {
        return businessMainBody;
    }

    public void setBusinessMainBody(String businessMainBody)
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

    public String getChannelName()
    {
        return channelName;
    }

    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }

}
