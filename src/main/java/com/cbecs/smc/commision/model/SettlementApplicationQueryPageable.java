package com.cbecs.smc.commision.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cbecs.framework.mybatis.pagination.Pageable;


public class SettlementApplicationQueryPageable extends Pageable implements java.io.Serializable
{

    private static final long serialVersionUID = -165890694623536598L;
    /**
     * 结算id
     */
    private String applicationId;
    /**
     * 结算单号
     */
    private String applicationCode;
    /**
     * 渠道商id
     */
    private String channelId;
    /**
     * 渠道商名称
     */
    private String channelName;
    /**
     * 状态
     */
    private int status;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 申请人
     */
    private String proposerName;
    /**
     * 申请人id
     */
    private String proposerId;
    /**
     * 申请时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applicationDate;
    /**
     * 业务主体
     */
    private int businessMainBody;
    /**
     * 待我审核，待我修改判断
     */
    private int workflowStatus;

    private String ids;

    public String getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId(String applicationId)
    {
        this.applicationId = applicationId;
    }

    public String getApplicationCode()
    {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode)
    {
        this.applicationCode = applicationCode;
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

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getProposerName()
    {
        return proposerName;
    }

    public void setProposerName(String proposerName)
    {
        this.proposerName = proposerName;
    }

    public String getProposerId()
    {
        return proposerId;
    }

    public void setProposerId(String proposerId)
    {
        this.proposerId = proposerId;
    }

    public Date getApplicationDate()
    {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate)
    {
        this.applicationDate = applicationDate;
    }

    public int getBusinessMainBody()
    {
        return businessMainBody;
    }

    public void setBusinessMainBody(int businessMainBody)
    {
        this.businessMainBody = businessMainBody;
    }

    public String getIds()
    {
        return ids;
    }

    public void setIds(String strings)
    {
        this.ids = strings;
    }

    public int getWorkflowStatus()
    {
        return workflowStatus;
    }

    public void setWorkflowStatus(int workflowStatus)
    {
        this.workflowStatus = workflowStatus;
    }

}