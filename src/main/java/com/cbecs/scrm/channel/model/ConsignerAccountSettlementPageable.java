package com.cbecs.scrm.channel.model;

import java.util.Date;

import com.cbecs.framework.mybatis.pagination.Pageable;


public class ConsignerAccountSettlementPageable extends Pageable implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 8298249847004619450L;
    
    private Date createTime;
    private String exacctName;
    private String billNo;
    private String orderCode;
    private double foreignAmount;
    private String currencyName;
    private double rate;
    private String raeType;
    private double amount;
    private double accountBalance;
    private String companyName;

    private String billId;
    private String orderId;
    private String companyId;
    private String accountType;
    
    private String capitalType2;
    private int accountChangeType;
    private double minAmount;
    private double maxAmount;
    private String beginDate;
    private String endDate;

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getExacctName()
    {
        return exacctName;
    }

    public void setExacctName(String exacctName)
    {
        this.exacctName = exacctName;
    }

    public String getBillNo()
    {
        return billNo;
    }

    public void setBillNo(String billNo)
    {
        this.billNo = billNo;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public double getForeignAmount()
    {
        return foreignAmount;
    }

    public void setForeignAmount(double foreignAmount)
    {
        this.foreignAmount = foreignAmount;
    }

    public String getCurrencyName()
    {
        return currencyName;
    }

    public void setCurrencyName(String currencyName)
    {
        this.currencyName = currencyName;
    }

    public double getRate()
    {
        return rate;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    public String getRaeType()
    {
        return raeType;
    }

    public void setRaeType(String raeType)
    {
        this.raeType = raeType;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public double getAccountBalance()
    {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance)
    {
        this.accountBalance = accountBalance;
    }

    public int getAccountChangeType()
    {
        return accountChangeType;
    }

    public void setAccountChangeType(int accountChangeType)
    {
        this.accountChangeType = accountChangeType;
    }

    public double getMinAmount()
    {
        return minAmount;
    }

    public void setMinAmount(double minAmount)
    {
        this.minAmount = minAmount;
    }

    public double getMaxAmount()
    {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount)
    {
        this.maxAmount = maxAmount;
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

    public String getBillId()
    {
        return billId;
    }

    public void setBillId(String billId)
    {
        this.billId = billId;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
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

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public String getCapitalType2()
    {
        return capitalType2;
    }

    public void setCapitalType2(String capitalType2)
    {
        this.capitalType2 = capitalType2;
    }

}
