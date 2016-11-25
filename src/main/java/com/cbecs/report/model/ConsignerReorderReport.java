package com.cbecs.report.model;

import java.io.Serializable;

/**
 * 委托方返单率
 * 
 * @author sunqy
 */
public class ConsignerReorderReport implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -7600693992508003995L;

    /**
     * 日期范围
     */
    private String dateByMonth;

    /**
     * 返单比数
     */
    private int reorderNumber;

    /**
     * 返单客户数
     */
    private int reorderCustomer;

    /**
     * 返单率
     */
    private double reorderPercent;

    public String getDateByMonth()
    {
        return dateByMonth;
    }

    public void setDateByMonth(String dateByMonth)
    {
        this.dateByMonth = dateByMonth;
    }

    public int getReorderNumber()
    {
        return reorderNumber;
    }

    public void setReorderNumber(int reorderNumber)
    {
        this.reorderNumber = reorderNumber;
    }

    public int getReorderCustomer()
    {
        return reorderCustomer;
    }

    public void setReorderCustomer(int reorderCustomer)
    {
        this.reorderCustomer = reorderCustomer;
    }

    public double getReorderPercent()
    {
        return reorderPercent;
    }

    public void setReorderPercent(double reorderPercent)
    {
        this.reorderPercent = reorderPercent;
    }
    
}
