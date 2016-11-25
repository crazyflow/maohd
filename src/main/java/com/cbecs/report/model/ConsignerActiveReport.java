package com.cbecs.report.model;

import java.io.Serializable;

public class ConsignerActiveReport implements Serializable
{

    private static final long serialVersionUID = 2471204151058687184L;

    /**
     * 区间
     */
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateByMonth;

    /**
     * 返单客户数
     */
    private int reorderCustomer;

    /**
     * 累计客户数
     */
    private int cumulativeCustomer;

    /**
     * 活跃度
     */
    private double activePersent;

    public String getDateByMonth()
    {
        return dateByMonth;
    }

    public void setDateByMonth(String dateByMonth)
    {
        this.dateByMonth = dateByMonth;
    }

    public int getReorderCustomer()
    {
        return reorderCustomer;
    }

    public void setReorderCustomer(int reorderCustomer)
    {
        this.reorderCustomer = reorderCustomer;
    }

    public int getCumulativeCustomer()
    {
        return cumulativeCustomer;
    }

    public void setCumulativeCustomer(int cumulativeCustomer)
    {
        this.cumulativeCustomer = cumulativeCustomer;
    }

    public double getActivePersent()
    {
        return activePersent;
    }

    public void setActivePersent(double activePersent)
    {
        this.activePersent = activePersent;
    }

}
