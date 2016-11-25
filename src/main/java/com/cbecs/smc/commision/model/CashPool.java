package com.cbecs.smc.commision.model;
import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;

import java.util.Date;


@Table(name="channel_cash_pool")
public class CashPool implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6372541301044409604L;

    /** 
     *channel_id
     */
    @Id(name="channel_id")
    private String channelId;

    /** 
     *channel_name
     */
    @Column(name="channel_name")
    private String channelName;

    /** 
     *free_amount
     */
    @Column(name="free_amount")
    private double freeAmount;

    /** 
     *freeze_amount
     */
    @Column(name="freeze_amount")
    private double freezeAmount;

    /** 
     *total_amount
     */
    @Column(name="total_amount")
    private double totalAmount;

    @Column(name="business_main_body")
    private int businessMainBody;
    /** 
     *created_by
     */
    @Column(name="created_by")
    private String createdBy;

    /** 
     *created_by_name
     */
    @Column(name="created_by_name")
    private String createdByName;

    /** 
     *created_date
     */
    @Column(name="created_date")
    private Date createdDate;

    /** 
     *updated_by
     */
    @Column(name="updated_by")
    private String updatedBy;

    /** 
     *updated_by_name
     */
    @Column(name="updated_by_name")
    private String updatedByName;

    /** 
     *updated_date
     */
    @Column(name="updated_date")
    private Date updatedDate;



    /**
     * 获取 channel_id 的值
     * @return String 
     */
    public String getChannelId() {
        return channelId;
    }
    
    /**
     * 设置channel_id 的值
     * @param String channelId
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取 channel_name 的值
     * @return String 
     */
    public String getChannelName() {
        return channelName;
    }
    
    /**
     * 设置channel_name 的值
     * @param String channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 获取 free_amount 的值
     * @return double 
     */
    public double getFreeAmount() {
        return freeAmount;
    }
    
    /**
     * 设置free_amount 的值
     * @param double freeAmount
     */
    public void setFreeAmount(double freeAmount) {
        this.freeAmount = freeAmount;
    }

    /**
     * 获取 freeze_amount 的值
     * @return double 
     */
    public double getFreezeAmount() {
        return freezeAmount;
    }
    
    /**
     * 设置freeze_amount 的值
     * @param double freezeAmount
     */
    public void setFreezeAmount(double freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    /**
     * 获取 total_amount 的值
     * @return double 
     */
    public double getTotalAmount() {
        return totalAmount;
    }
    
    /**
     * 设置total_amount 的值
     * @param double totalAmount
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    
    public int getBusinessMainBody()
    {
        return businessMainBody;
    }

    public void setBusinessMainBody(int businessMainBody)
    {
        this.businessMainBody = businessMainBody;
    }

    /**
     * 获取 created_by 的值
     * @return String 
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * 设置created_by 的值
     * @param String createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取 created_by_name 的值
     * @return String 
     */
    public String getCreatedByName() {
        return createdByName;
    }
    
    /**
     * 设置created_by_name 的值
     * @param String createdByName
     */
    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    /**
     * 获取 created_date 的值
     * @return Date 
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    
    /**
     * 设置created_date 的值
     * @param Date createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 获取 updated_by 的值
     * @return String 
     */
    public String getUpdatedBy() {
        return updatedBy;
    }
    
    /**
     * 设置updated_by 的值
     * @param String updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取 updated_by_name 的值
     * @return String 
     */
    public String getUpdatedByName() {
        return updatedByName;
    }
    
    /**
     * 设置updated_by_name 的值
     * @param String updatedByName
     */
    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    /**
     * 获取 updated_date 的值
     * @return Date 
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }
    
    /**
     * 设置updated_date 的值
     * @param Date updatedDate
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


    
    @Override
	public String toString() {
		return "ChannelCashPool [" + "channel_id=" + channelId +", channel_name=" + channelName +", free_amount=" + freeAmount +", freeze_amount=" + freezeAmount +", total_amount=" + totalAmount +", created_by=" + createdBy +", created_by_name=" + createdByName +", created_date=" + createdDate +", updated_by=" + updatedBy +", updated_by_name=" + updatedByName +", updated_date=" + updatedDate +"]";
	}
}