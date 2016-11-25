package com.cbecs.smc.commision.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;


@Table(name = "commision_settlement_application")
public class SettlementApplication implements java.io.Serializable
{

    private static final long serialVersionUID = 424458957565646401L;

    /**
     * 结算申请id
     */
    @Id(name = "application_id")
    private String applicationId;

    /**
     * 结算申请编号
     */
    @Column(name = "application_code")
    private String applicationCode;

    /**
     * 结算申请日期
     */
    @Column(name = "application_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applicationDate;

    /**
     * 结算状态
     */
    @Column(name = "status")
    private int status;

    /**
     * 工作流id
     */
    @Column(name = "workflow_id")
    private int workflowId;

    /**
     * 渠道商id
     */
    @Column(name = "channel_id")
    private String channelId;

    /**
     * 渠道商名称
     */
    @Column(name = "channel_name")
    private String channelName;

    /**
     * 结算总成本
     */
    @Column(name = "total_cost_amount")
    @NumberFormat(pattern = "#.##")
    private double totalCostAmount;

    /**
     * 结算总收入
     */
    @Column(name = "total_income_amount")
    @NumberFormat(pattern = "#.##")
    private double totalIncomeAmount;

    /**
     * 结算总佣金
     */
    @Column(name = "total_commission_amount")
    @NumberFormat(pattern = "#.##")
    private double totalCommissionAmount;

    /**
     * 提取金额
     */
    @Column(name = "withdrawn_amount")
    @NumberFormat(pattern = "#.##")
    private double withdrawnAmount;

    /**
     * 业务主体
     */
    @Column(name = "business_main_body")
    private int businessMainBody;
    
    /**
     * 合作模式
     */
    @Column(name = "cooperation_mode")
    private int cooperationMode;

    /**
     * 申请人id
     */
    @Column(name = "applicant_id")
    private String applicantId;

    /**
     * 申请人name
     */
    @Column(name = "applicant_name")
    private String applicantName;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 是否删除
     */
    @Column(name = "deleted")
    private boolean deleted;

    /**
     * 明细信息
     */
    private List<SettlementDetail> settlementDetails;

    /**
     * created_by
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 创建人
     */
    @Column(name = "created_by_name")
    private String createdByName;

    /**
     * 创建日期
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * updated_by
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 更改人
     */
    @Column(name = "updated_by_name")
    private String updatedByName;

    /**
     * 更改日期
     */
    @Column(name = "updated_date")
    private Date updatedDate;

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

    public Date getApplicationDate()
    {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate)
    {
        this.applicationDate = applicationDate;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
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

    public double getTotalCostAmount()
    {
        return totalCostAmount;
    }

    public void setTotalCostAmount(double totalCostAmount)
    {
        this.totalCostAmount = totalCostAmount;
    }

    public double getTotalIncomeAmount()
    {
        return totalIncomeAmount;
    }

    public void setTotalIncomeAmount(double totalIncomeAmount)
    {
        this.totalIncomeAmount = totalIncomeAmount;
    }

    public double getTotalCommissionAmount()
    {
        return totalCommissionAmount;
    }

    public void setTotalCommissionAmount(double totalCommissionAmount)
    {
        this.totalCommissionAmount = totalCommissionAmount;
    }

    public double getWithdrawnAmount()
    {
        return withdrawnAmount;
    }

    public void setWithdrawnAmount(double withdrawnAmount)
    {
        this.withdrawnAmount = withdrawnAmount;
    }

    public int getBusinessMainBody()
    {
        return businessMainBody;
    }

    public void setBusinessMainBody(int businessMainBody)
    {
        this.businessMainBody = businessMainBody;
    }

    
    public String getApplicantId()
    {
        return applicantId;
    }

    public void setApplicantId(String applicantId)
    {
        this.applicantId = applicantId;
    }

    public String getApplicantName()
    {
        return applicantName;
    }

    public void setApplicantName(String applicantName)
    {
        this.applicantName = applicantName;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    public List<SettlementDetail> getSettlementDetails()
    {
        return settlementDetails;
    }

    public void setSettlementDetails(List<SettlementDetail> settlementDetails)
    {
        this.settlementDetails = settlementDetails;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getCreatedByName()
    {
        return createdByName;
    }

    public void setCreatedByName(String createdByName)
    {
        this.createdByName = createdByName;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedByName()
    {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName)
    {
        this.updatedByName = updatedByName;
    }

    public Date getUpdatedDate()
    {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate)
    {
        this.updatedDate = updatedDate;
    }

    public int getWorkflowId()
    {
        return workflowId;
    }

    public void setWorkflowId(int workflowId)
    {
        this.workflowId = workflowId;
    }

    public int getCooperationMode()
    {
        return cooperationMode;
    }

    public void setCooperationMode(int cooperationMode)
    {
        this.cooperationMode = cooperationMode;
    }

}