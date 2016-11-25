package com.cbecs.report.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ConsignerExportQuery implements java.io.Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 委托方名
     */
    private String companyName;

    /**
     * 委托方行业
     */
    private int IndustryType;
    /**
     * 企业类型
     */
    private int ProductSourceType;

    /**
     * 日期范围类型
     */
    private int rangeDateType;

    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    /**
     * 结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    /**
     * 省id
     */
    private int provinceId;
    /**
     * 城市id
     */
    private int cityId;
    /**
     * 订单数
     */
    private String orderCount;
    /**
     * 外贸顾问
     */
    private String firstOrderManagerUser;
    /**
     * 客服
     */
    private String customerService;

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public int getIndustryType()
    {
        return IndustryType;
    }

    public void setIndustryType(int industryType)
    {
        IndustryType = industryType;
    }

    public int getProductSourceType()
    {
        return ProductSourceType;
    }

    public void setProductSourceType(int productSourceType)
    {
        ProductSourceType = productSourceType;
    }

    public int getRangeDateType()
    {
        return rangeDateType;
    }

    public void setRangeDateType(int rangeDateType)
    {
        this.rangeDateType = rangeDateType;
    }

    public Date getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public int getProvinceId()
    {
        return provinceId;
    }

    public void setProvinceId(int provinceId)
    {
        this.provinceId = provinceId;
    }

    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }

    public String getOrderCount()
    {
        return orderCount;
    }

    public void setOrderCount(String orderCount)
    {
        this.orderCount = orderCount;
    }

    public String getFirstOrderManagerUser()
    {
        return firstOrderManagerUser;
    }

    public void setFirstOrderManagerUser(String firstOrderManagerUser)
    {
        this.firstOrderManagerUser = firstOrderManagerUser;
    }

    public String getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(String customerService)
    {
        this.customerService = customerService;
    }

}
