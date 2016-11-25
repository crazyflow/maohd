package com.cbecs.report.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import com.cbecs.framework.mybatis.pagination.Pageable;

/**
 * 客服统计
 */
public class LogisticsReportPageable extends Pageable implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -5109617790861203146L;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 业务日期
     */
    private Date orderDate;

    /**
     * 物流方式
     */
    private String transportMode;

    /**
     * 物流方式ID
     */
    private int transportModeId;

    /**
     * 业务操作
     */
    private String transportChildMode;

    /**
     * 业务操作ID
     */
    private int transportChildModeId;

    /**
     * 费用类型
     */
    private String logisticsCostTypeName;

    /**
     * 费用类型ID
     */
    private int logisticsCostTypeNameId;

    /**
     * 物流供应商
     */
    private String logisticsBusName;

    /**
     * 成本价
     */
    private double costPrice;
    /**
     * 客户报价
     */
    private double customerPrice;
    /**
     * 利润
     */
    private String profit;

    private int costCurrencyId;
    
    private String currencyCode;

    private int customerCurrencyId;
    
    private String costPriceByCurrency;
    
    private String customerPriceByCurrency;

    /**
     * 委托商
     */
    private String companyName;

    private String beginDate;

    private String endDate;
    
    private NumberFormat nf = new DecimalFormat("#,###.##");

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

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getTransportMode()
    {
        return transportMode;
    }

    public void setTransportMode(String transportMode)
    {
        this.transportMode = transportMode;
    }

    public String getTransportChildMode()
    {
        return transportChildMode;
    }

    public void setTransportChildMode(String transportChildMode)
    {
        this.transportChildMode = transportChildMode;
    }

    public String getLogisticsCostTypeName()
    {
        return logisticsCostTypeName;
    }

    public void setLogisticsCostTypeName(String logisticsCostTypeName)
    {
        this.logisticsCostTypeName = logisticsCostTypeName;
    }

    public String getLogisticsBusName()
    {
        return logisticsBusName;
    }

    public void setLogisticsBusName(String logisticsBusName)
    {
        this.logisticsBusName = logisticsBusName;
    }

    public double getCostPrice()
    {
        return costPrice;
    }

    public void setCostPrice(double costPrice)
    {
        this.costPrice = costPrice;
    }

    public double getCustomerPrice()
    {
        return customerPrice;
    }

    public void setCustomerPrice(double customerPrice)
    {
        this.customerPrice = customerPrice;
    }

    public String getCostPriceByCurrency()
    {
        this.costPriceByCurrency=this.currencyCode + nf.format(this.costPrice);
        return costPriceByCurrency;
    }

    public String getCustomerPriceByCurrency()
    {
        this.customerPriceByCurrency=this.currencyCode + nf.format(this.customerPrice);
        return customerPriceByCurrency;
    }

    public String getProfit()
    {
        if (this.costCurrencyId == this.customerCurrencyId)
        {
            this.profit = this.currencyCode + nf.format(this.customerPrice - this.costPrice);
        }
        else if (this.costCurrencyId != this.customerCurrencyId)
        {
            this.profit = (this.currencyCode + nf.format(this.customerPrice)) + "-"
                    + (this.currencyCode + nf.format(this.costPrice));
        }
        return profit;
    }

    public int getCostCurrencyId()
    {
        return costCurrencyId;
    }

    public void setCostCurrencyId(int costCurrencyId)
    {
        this.costCurrencyId = costCurrencyId;
    }

    public int getCustomerCurrencyId()
    {
        return customerCurrencyId;
    }

    public void setCustomerCurrencyId(int customerCurrencyId)
    {
        this.customerCurrencyId = customerCurrencyId;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public int getTransportModeId()
    {
        return transportModeId;
    }

    public void setTransportModeId(int transportModeId)
    {
        this.transportModeId = transportModeId;
    }

    public int getTransportChildModeId()
    {
        return transportChildModeId;
    }

    public void setTransportChildModeId(int transportChildModeId)
    {
        this.transportChildModeId = transportChildModeId;
    }

    public int getLogisticsCostTypeNameId()
    {
        return logisticsCostTypeNameId;
    }

    public void setLogisticsCostTypeNameId(int logisticsCostTypeNameId)
    {
        this.logisticsCostTypeNameId = logisticsCostTypeNameId;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

}