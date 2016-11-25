package com.cbecs.smc.commision.model;
import org.springframework.format.annotation.NumberFormat;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;


@Table(name="commision_withdraw_detail")
public class WithdrawDetail implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5706656613736051621L;

    /** 
     *detail_id
     */
    @Id(name="detail_id")
    private String detailId;

    /** 
     *application_id
     */
    @Column(name="application_id")
    private String applicationId;

    /** 
     *settlement_application_id
     */
    @Column(name="settlement_application_id")
    private String settlementApplicationId;

    /** 
     *settlement_application_code
     */
    @Column(name="settlement_application_code")
    private String settlementApplicationCode;

    /** 
     *commision_amount
     */
    @Column(name="commision_amount")
    @NumberFormat(pattern="#,###,###.##")
    private double commisionAmount;

    /** 
     *withdrawn_amount
     */
    @Column(name="withdrawn_amount")
    @NumberFormat(pattern="#,###,###.##")
    private double withdrawnAmount;

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
     *amount
     */
    @Column(name="amount")
    @NumberFormat(pattern="#,###,###.##")
    private double amount;

    /** 
     *foreign_amount
     */
    @Column(name="foreign_amount")
    @NumberFormat(pattern="#,###,###.##")
    private double foreignAmount;



    /**
     * 获取 detail_id 的值
     * @return String 
     */
    public String getDetailId() {
        return detailId;
    }
    
    /**
     * 设置detail_id 的值
     * @param String detailId
     */
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

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
     * 获取 settlement_application_id 的值
     * @return String 
     */
    public String getSettlementApplicationId() {
        return settlementApplicationId;
    }
    
    /**
     * 设置settlement_application_id 的值
     * @param String settlementApplicationId
     */
    public void setSettlementApplicationId(String settlementApplicationId) {
        this.settlementApplicationId = settlementApplicationId;
    }

    /**
     * 获取 settlement_application_code 的值
     * @return String 
     */
    public String getSettlementApplicationCode() {
        return settlementApplicationCode;
    }
    
    /**
     * 设置settlement_application_code 的值
     * @param String settlementApplicationCode
     */
    public void setSettlementApplicationCode(String settlementApplicationCode) {
        this.settlementApplicationCode = settlementApplicationCode;
    }

    /**
     * 获取 commision_amount 的值
     * @return double 
     */
    public double getCommisionAmount() {
        return commisionAmount;
    }
    
    /**
     * 设置commision_amount 的值
     * @param double commisionAmount
     */
    public void setCommisionAmount(double commisionAmount) {
        this.commisionAmount = commisionAmount;
    }

    /**
     * 获取 withdrawn_amount 的值
     * @return double 
     */
    public double getWithdrawnAmount() {
        return withdrawnAmount;
    }
    
    /**
     * 设置withdrawn_amount 的值
     * @param double withdrawnAmount
     */
    public void setWithdrawnAmount(double withdrawnAmount) {
        this.withdrawnAmount = withdrawnAmount;
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


    
    @Override
	public String toString() {
		return "CommisionWithdrawDetail [" + "detail_id=" + detailId +", application_id=" + applicationId +", settlement_application_id=" + settlementApplicationId +", settlement_application_code=" + settlementApplicationCode +", commision_amount=" + commisionAmount +", withdrawn_amount=" + withdrawnAmount +", currency_id=" + currencyId +", currency_code=" + currencyCode +", amount=" + amount +", foreign_amount=" + foreignAmount +"]";
	}
}