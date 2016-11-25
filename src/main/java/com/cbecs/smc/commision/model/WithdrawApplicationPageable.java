package com.cbecs.smc.commision.model;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;
import com.cbecs.framework.mybatis.pagination.Pageable;


@Table(name="commision_withdraw_application")
public class WithdrawApplicationPageable extends Pageable implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8350291327053517030L;

    /** 
     *application_id
     */
    @Id(name="application_id")
    private String applicationId;

    /** 
     *application_code
     */
    @Column(name="application_code")
    private String applicationCode;

    /** 
     *application_date
     */
    @Column(name="application_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applicationDate;

    /** 
     *status
     */
    @Column(name="status")
    private int status;

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
     *business_main_body
     */
    @Column(name="business_main_body")
    private int businessMainBody;

    /** 
     *beneficiary_name
     */
    @Column(name="beneficiary_name")
    private String beneficiaryName;

    /** 
     *beneficiary_bank
     */
    @Column(name="beneficiary_bank")
    private String beneficiaryBank;

    /** 
     *beneficiary_account_no
     */
    @Column(name="beneficiary_account_no")
    private String beneficiaryAccountNo;

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
     *selling_rate
     */
    @Column(name="selling_rate")
    @NumberFormat(pattern="#.##")
    private double sellingRate;

    /** 
     *total_amount
     */
    @Column(name="total_amount")
    @NumberFormat(pattern="#,###,###.##")
    private double totalAmount;

    /** 
     *total_foreign_amount
     */
    @Column(name="total_foreign_amount")
    @NumberFormat(pattern="#,###,###.##")
    private double totalForeignAmount;

    /** 
     *remark
     */
    @Column(name="remark")
    private String remark;

    /** 
     *deleted
     */
    @Column(name="deleted")
    private boolean deleted;

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
     *workflow_id
     */
    @Column(name="workflow_id")
    private int workFlowId;
    
    private String beginDate;
    
    private String endDate;
    
    private boolean byStatus;
    
    private String workFlowIds;
    
    private int currentFlowStatus;

    /**
     * 获取 application_id 的值
     * @return String 
     */
    public String getApplicationId() {
        return applicationId;
    }
    
    /**
     * 设置application_id 的值
     * @param String applicationId
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * 获取 application_code 的值
     * @return String 
     */
    public String getApplicationCode() {
        return applicationCode;
    }
    
    /**
     * 设置application_code 的值
     * @param String applicationCode
     */
    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    /**
     * 获取 application_date 的值
     * @return Date 
     */
    public Date getApplicationDate() {
        return applicationDate;
    }
    
    /**
     * 设置application_date 的值
     * @param Date applicationDate
     */
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     * 获取 status 的值
     * @return int 
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * 设置status 的值
     * @param int status
     */
    public void setStatus(int status) {
        this.status = status;
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
     * 获取 business_main_body 的值
     * @return int 
     */
    public int getBusinessMainBody() {
        return businessMainBody;
    }
    
    /**
     * 设置business_main_body 的值
     * @param int businessMainBody
     */
    public void setBusinessMainBody(int businessMainBody) {
        this.businessMainBody = businessMainBody;
    }

    /**
     * 获取 beneficiary_name 的值
     * @return String 
     */
    public String getBeneficiaryName() {
        return beneficiaryName;
    }
    
    /**
     * 设置beneficiary_name 的值
     * @param String beneficiaryName
     */
    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    /**
     * 获取 beneficiary_bank 的值
     * @return String 
     */
    public String getBeneficiaryBank() {
        return beneficiaryBank;
    }
    
    /**
     * 设置beneficiary_bank 的值
     * @param String beneficiaryBank
     */
    public void setBeneficiaryBank(String beneficiaryBank) {
        this.beneficiaryBank = beneficiaryBank;
    }

    /**
     * 获取 beneficiary_account_no 的值
     * @return String 
     */
    public String getBeneficiaryAccountNo() {
        return beneficiaryAccountNo;
    }
    
    /**
     * 设置beneficiary_account_no 的值
     * @param String beneficiaryAccountNo
     */
    public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
        this.beneficiaryAccountNo = beneficiaryAccountNo;
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
     * 获取 selling_rate 的值
     * @return double 
     */
    public double getSellingRate() {
        return sellingRate;
    }
    
    /**
     * 设置selling_rate 的值
     * @param double sellingRate
     */
    public void setSellingRate(double sellingRate) {
        this.sellingRate = sellingRate;
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

    /**
     * 获取 total_foreign_amount 的值
     * @return double 
     */
    public double getTotalForeignAmount() {
        return totalForeignAmount;
    }
    
    /**
     * 设置total_foreign_amount 的值
     * @param double totalForeignAmount
     */
    public void setTotalForeignAmount(double totalForeignAmount) {
        this.totalForeignAmount = totalForeignAmount;
    }

    /**
     * 获取 remark 的值
     * @return String 
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置remark 的值
     * @param String remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 deleted 的值
     * @return boolean 
     */
    public boolean getDeleted() {
        return deleted;
    }
    
    /**
     * 设置deleted 的值
     * @param boolean deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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
     * @return Date 
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

    public boolean isByStatus()
    {
        return byStatus;
    }

    public void setByStatus(boolean byStatus)
    {
        this.byStatus = byStatus;
    }

    public String getWorkFlowIds()
    {
        return workFlowIds;
    }

    public void setWorkFlowIds(String workFlowIds)
    {
        this.workFlowIds = workFlowIds;
    }

    public int getWorkFlowId()
    {
        return workFlowId;
    }

    public void setWorkFlowId(int workFlowId)
    {
        this.workFlowId = workFlowId;
    }

    public int getCurrentFlowStatus()
    {
        return currentFlowStatus;
    }

    public void setCurrentFlowStatus(int currentFlowStatus)
    {
        this.currentFlowStatus = currentFlowStatus;
    }   
    
    
    
}