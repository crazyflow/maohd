package com.cbecs.report.model;

import java.io.Serializable;

/**
 * 委托方返单率
 * 
 * @author sunqy
 */
public class ConsignerReorderQueryReport implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -688849223361950444L;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
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

    
}
