package com.cbecs.report.model;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 委托方活跃度
 * 
 * @author sunqy
 */
public class ConsignerActiveQueryReport implements Serializable
{
    private static final long serialVersionUID = -2123861930114913908L;

    /**
     * 开始日期
     */
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String beginDate;

    /**
     * 结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
