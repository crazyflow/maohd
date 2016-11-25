package com.cbecs.smc.commision.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;


@Table(name = "commision_settlement_detail")
public class SettlementDetail implements java.io.Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 5710198575855562125L;

    /**
     * 结算信息id
     */
    @Id(name = "detail_id")
    private String detailId;

    /**
     * 结算申请单id，对应结算申请表
     */
    @Column(name = "application_id")
    private String applicationId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 订单code
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 委托人id
     */
    @Column(name = "entrusting_id")
    private String entrustingId;

    /**
     * 委托人名称
     */
    @Column(name = "entrusting_name")
    private String entrustingName;

    /**
     * 金融产品
     */
    private String financialProduct;

    /**
     * 成本
     */
    @Column(name = "cost_amount")
    @NumberFormat(pattern = "#.##")
    private double costAmount;

    /**
     * 收入
     */
    @Column(name = "income_amount")
    @NumberFormat(pattern = "#.##")
    private double incomeAmount;

    /**
     * 佣金
     */
    @Column(name = "commision_amount")
    @NumberFormat(pattern = "#.##")
    private double commisionAmount;

    /**
     * 订单完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 获取 detail_id 的值
     * 
     * @return String
     */
    public String getDetailId()
    {
        return detailId;
    }

    /**
     * 设置detail_id 的值
     * 
     * @param String
     *            detailId
     */
    public void setDetailId(String detailId)
    {
        this.detailId = detailId;
    }

    /**
     * 获取 application_id 的值
     * 
     * @return String
     */
    public String getApplicationId()
    {
        return applicationId;
    }

    /**
     * 设置application_id 的值
     * 
     * @param String
     *            applicationId
     */
    public void setApplicationId(String applicationId)
    {
        this.applicationId = applicationId;
    }

    /**
     * 获取 order_id 的值
     * 
     * @return String
     */
    public String getOrderId()
    {
        return orderId;
    }

    /**
     * 设置order_id 的值
     * 
     * @param String
     *            orderId
     */
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    /**
     * 获取 order_code 的值
     * 
     * @return String
     */
    public String getOrderCode()
    {
        return orderCode;
    }

    /**
     * 设置order_code 的值
     * 
     * @param String
     *            orderCode
     */
    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    /**
     * 获取 entrusting_id 的值
     * 
     * @return String
     */
    public String getEntrustingId()
    {
        return entrustingId;
    }

    /**
     * 设置entrusting_id 的值
     * 
     * @param String
     *            entrustingId
     */
    public void setEntrustingId(String entrustingId)
    {
        this.entrustingId = entrustingId;
    }

    /**
     * 获取 entrusting_name 的值
     * 
     * @return String
     */
    public String getEntrustingName()
    {
        return entrustingName;
    }

    /**
     * 设置entrusting_name 的值
     * 
     * @param String
     *            entrustingName
     */
    public void setEntrustingName(String entrustingName)
    {
        this.entrustingName = entrustingName;
    }

    public String getFinancialProduct()
    {
        return financialProduct;
    }

    public void setFinancialProduct(String financialProduct)
    {
        this.financialProduct = financialProduct;
    }

    /**
     * 获取 cost_amount 的值
     * 
     * @return double
     */
    public double getCostAmount()
    {
        return costAmount;
    }

    /**
     * 设置cost_amount 的值
     * 
     * @param double
     *            costAmount
     */
    public void setCostAmount(double costAmount)
    {
        this.costAmount = costAmount;
    }

    /**
     * 获取 income_amount 的值
     * 
     * @return double
     */
    public double getIncomeAmount()
    {
        return incomeAmount;
    }

    /**
     * 设置income_amount 的值
     * 
     * @param double
     *            incomeAmount
     */
    public void setIncomeAmount(double incomeAmount)
    {
        this.incomeAmount = incomeAmount;
    }

    /**
     * 获取 commision_amount 的值
     * 
     * @return double
     */
    public double getCommisionAmount()
    {
        return commisionAmount;
    }

    /**
     * 设置commision_amount 的值
     * 
     * @param double
     *            commisionAmount
     */
    public void setCommisionAmount(double commisionAmount)
    {
        this.commisionAmount = commisionAmount;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    @Override
    public String toString()
    {
        return "CommisionSettlementDetail [" + "detail_id=" + detailId + ", application_id=" + applicationId
                + ", order_id=" + orderId + ", order_code=" + orderCode + ", entrusting_id=" + entrustingId
                + ", entrusting_name=" + entrustingName + ", financial_product=" + financialProduct + ", cost_amount="
                + costAmount + ", income_amount=" + incomeAmount + ", commision_amount=" + commisionAmount + "]";
    }
}