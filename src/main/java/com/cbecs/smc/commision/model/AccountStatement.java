package com.cbecs.smc.commision.model;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;


import java.util.Date;


@Table(name="channel_account_statement")
public class AccountStatement implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -655686390616942732L;

    /** 
     *statement_id
     */
    @Id(name="statement_id")
    private String statementId;

    /** 
     *channel_id
     */
    @Column(name="channel_id")
    private String channelId;

    /** 
     *channel_name
     */
    @Column(name="channel_name")
    private String channelName;

    /** 
     *capacity1_id
     */
    @Column(name="capacity1_id")
    private String capacity1Id;

    /** 
     *capacity1_name
     */
    @Column(name="capacity1_name")
    private String capacity1Name;

    /** 
     *capacity2_id
     */
    @Column(name="capacity2_id")
    private String capacity2Id;

    /** 
     *capacity2_name
     */
    @Column(name="capacity2_name")
    private String capacity2Name;

    /** 
     *document_id
     */
    @Column(name="document_id")
    private String documentId;

    /** 
     *document_code
     */
    @Column(name="document_code")
    private String documentCode;

    /** 
     *rae_type
     */
    @Column(name="rae_type")
    private int raeType;

    /** 
     *currency_id
     */
    @Column(name="currency_id")
    private int currencyId;

    /** 
     *currency_code
     */
    @Column(name="currency_code")
    private String currencyCode;

    /** 
     *rate
     */
    @Column(name="rate")
    private double rate;

    /** 
     *amount
     */
    @Column(name="amount")
    private double amount;

    /** 
     *foreign_amount
     */
    @Column(name="foreign_amount")
    private double foreignAmount;

    /** 
     *order_id
     */
    @Column(name="order_id")
    private String orderId;

    /** 
     *order_code
     */
    @Column(name="order_code")
    private String orderCode;

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
     *business_main_body
     */
    @Column(name="business_main_body")
    private int businessMainBody;
    
    private String beginDate;
    private String endDate;



    /**
     * 获取 statement_id 的值
     * @return String 
     */
    public String getStatementId() {
        return statementId;
    }
    
    /**
     * 设置statement_id 的值
     * @param String statementId
     */
    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

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
     * 获取 capacity1_id 的值
     * @return String 
     */
    public String getCapacity1Id() {
        return capacity1Id;
    }
    
    /**
     * 设置capacity1_id 的值
     * @param String capacity1Id
     */
    public void setCapacity1Id(String capacity1Id) {
        this.capacity1Id = capacity1Id;
    }

    /**
     * 获取 capacity1_name 的值
     * @return String 
     */
    public String getCapacity1Name() {
        return capacity1Name;
    }
    
    /**
     * 设置capacity1_name 的值
     * @param String capacity1Name
     */
    public void setCapacity1Name(String capacity1Name) {
        this.capacity1Name = capacity1Name;
    }

    /**
     * 获取 capacity2_id 的值
     * @return String 
     */
    public String getCapacity2Id() {
        return capacity2Id;
    }
    
    /**
     * 设置capacity2_id 的值
     * @param String capacity2Id
     */
    public void setCapacity2Id(String capacity2Id) {
        this.capacity2Id = capacity2Id;
    }

    /**
     * 获取 capacity2_name 的值
     * @return String 
     */
    public String getCapacity2Name() {
        return capacity2Name;
    }
    
    /**
     * 设置capacity2_name 的值
     * @param String capacity2Name
     */
    public void setCapacity2Name(String capacity2Name) {
        this.capacity2Name = capacity2Name;
    }

    /**
     * 获取 document_id 的值
     * @return String 
     */
    public String getDocumentId() {
        return documentId;
    }
    
    /**
     * 设置document_id 的值
     * @param String documentId
     */
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    /**
     * 获取 document_code 的值
     * @return String 
     */
    public String getDocumentCode() {
        return documentCode;
    }
    
    /**
     * 设置document_code 的值
     * @param String documentCode
     */
    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    /**
     * 获取 rae_type 的值
     * @return int 
     */
    public int getRaeType() {
        return raeType;
    }
    
    /**
     * 设置rae_type 的值
     * @param int raeType
     */
    public void setRaeType(int raeType) {
        this.raeType = raeType;
    }

    /**
     * 获取 currency_id 的值
     * @return int 
     */
    public int getCurrencyId() {
        return currencyId;
    }
    
    /**
     * 设置currency_id 的值
     * @param int currencyId
     */
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * 获取 currency_code 的值
     * @return String 
     */
    public String getCurrencyCode() {
        return currencyCode;
    }
    
    /**
     * 设置currency_code 的值
     * @param String currencyCode
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * 获取 rate 的值
     * @return double 
     */
    public double getRate() {
        return rate;
    }
    
    /**
     * 设置rate 的值
     * @param double rate
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * 获取 amount 的值
     * @return double 
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * 设置amount 的值
     * @param double amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * 获取 foreign_amount 的值
     * @return double 
     */
    public double getForeignAmount() {
        return foreignAmount;
    }
    
    /**
     * 设置foreign_amount 的值
     * @param double foreignAmount
     */
    public void setForeignAmount(double foreignAmount) {
        this.foreignAmount = foreignAmount;
    }

    /**
     * 获取 order_id 的值
     * @return String 
     */
    public String getOrderId() {
        return orderId;
    }
    
    /**
     * 设置order_id 的值
     * @param String orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取 order_code 的值
     * @return String 
     */
    public String getOrderCode() {
        return orderCode;
    }
    
    /**
     * 设置order_code 的值
     * @param String orderCode
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
    
    public int getBusinessMainBody()
    {
        return businessMainBody;
    }

    public void setBusinessMainBody(int businessMainBody)
    {
        this.businessMainBody = businessMainBody;
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

    @Override
	public String toString() {
		return "ChannelAccountStatement [" + "statement_id=" + statementId +", channel_id=" + channelId +", channel_name=" + channelName +", capacity1_id=" + capacity1Id +", capacity1_name=" + capacity1Name +", capacity2_id=" + capacity2Id +", capacity2_name=" + capacity2Name +", document_id=" + documentId +", document_code=" + documentCode +", rae_type=" + raeType +", currency_id=" + currencyId +", currency_code=" + currencyCode +", rate=" + rate +", amount=" + amount +", foreign_amount=" + foreignAmount +", order_id=" + orderId +", order_code=" + orderCode +", created_by=" + createdBy +", created_by_name=" + createdByName +", created_date=" + createdDate +"]";
	}
}