package com.cbecs.report.model;

import java.util.Date;

/**
 * 客服统计
 */
public class CustomerServiceReport implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 485825130816248238L;

    /**
     * 订单编号
     */
    private String orderCode;
    
    /**
     * 委托人名称
     */
    private String companyName;
    
    /**
     * 客服名称
     */
    private String customerServiceName;
    
    /**
     * 已付货款。
     */
    private double paidAmount;
    
    /**
     * 报关金额
     */
    private double declarationAmount;
    
    /**
     * 报关币种
     */
    private String currencySymbol;
    
    /**
     * 收汇日期
     */
    private Date gatherDate;
           
    /**
     * 收汇币种
     */
    private String gatherSymbol;
    
    /**
     * 收汇金额(外币)
     */
    private double gatherAmountForeign;
    
    /**
     * 收汇率
     */
    private double gatherRate;
    
    /**
     * 收汇金额(人民币)
     */
    private double gatherAmountRmb;
    
    /**
     * 客服ID
     */
    private String customerServiceId;
    
    private String orderId;
        
    private String companyId;
    
    private String beginDate;
    private String endDate;
      
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

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCustomerServiceName()
    {
        return customerServiceName;
    }

    public void setCustomerServiceName(String customerServiceName)
    {
        this.customerServiceName = customerServiceName;
    }

    public double getPaidAmount()
    {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount)
    {
        this.paidAmount = paidAmount;
    }

    public double getDeclarationAmount()
    {
        return declarationAmount;
    }

    public void setDeclarationAmount(double declarationAmount)
    {
        this.declarationAmount = declarationAmount;
    }

    public Date getGatherDate()
    {
        return gatherDate;
    }

    public void setGatherDate(Date gatherDate)
    {
        this.gatherDate = gatherDate;
    }

    public double getGatherAmountForeign()
    {
        return gatherAmountForeign;
    }

    public void setGatherAmountForeign(double gatherAmountForeign)
    {
        this.gatherAmountForeign = gatherAmountForeign;
    }

    public double getGatherRate()
    {
        return gatherRate;
    }

    public void setGatherRate(double gatherRate)
    {
        this.gatherRate = gatherRate;
    }

    public double getGatherAmountRmb()
    {
        return gatherAmountRmb;
    }

    public void setGatherAmountRmb(double gatherAmountRmb)
    {
        this.gatherAmountRmb = gatherAmountRmb;
    }

    public String getCustomerServiceId()
    {
        return customerServiceId;
    }

    public void setCustomerServiceId(String customerServiceId)
    {
        this.customerServiceId = customerServiceId;
    }

    public String getCurrencySymbol()
    {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol)
    {
        this.currencySymbol = currencySymbol;
    }

    public String getGatherSymbol()
    {
        return gatherSymbol;
    }

    public void setGatherSymbol(String gatherSymbol)
    {
        this.gatherSymbol = gatherSymbol;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(String companyId)
    {
        this.companyId = companyId;
    }
    
    
}