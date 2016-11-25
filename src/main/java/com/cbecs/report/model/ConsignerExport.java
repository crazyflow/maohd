package com.cbecs.report.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ConsignerExport implements java.io.Serializable
{

    private static final long serialVersionUID = -3995780189270519554L;
    /**
     * 委托商id
     */
    private String companyId;
    /**
     * 委托商名称
     */
    private String companyName;
    /**
     * 省id
     */
    private int provinceId;
    /**
     * 省name
     */
    private String provinceName;
    /**
     * 城市id
     */
    private int cityId;
    /**
     * 城市name
     */
    private String cityName;
    /**
     * 省市信息
     */
    private String areaInfo;
    /**
     * 行业类型
     */
    private int industryType;
    /**
     * 行业类型名称
     */
    private String industryTypeName;
    /**
     * 工厂类型
     */
    private int productSourceType;
    /**
     * 工厂类型名称
     */
    private String productSourceTypeName;
    /**
     * 签约标志
     */
    private int signFlag;
    /**
     * 签约code
     */
    private String signCode;
    /**
     * 签约时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signTime;
    /**
     * 首次出口日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstExportDate;
    /**
     * 累计订单数
     */
    private int orderCount;
    /**
     * 累计订单金额
     */
    private double orderTotalMoney;
    /**
     * 首单外贸顾问
     */
    private String firstOrderManagerUser;
    /**
     * 首单外贸顾问
     */
    private String firstOrderManagerUserName;
    /**
     * 客服
     */
    private String customerService;
    /**
     * 客服
     */
    private String customerServiceName;
    /**
     * 最后出口日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastExportDate;
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 标志
     */
    private int stopFlag;

    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(String companyId)
    {
        this.companyId = companyId;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public int getProvinceId()
    {
        return provinceId;
    }

    public void setProvinceId(int provinceId)
    {
        this.provinceId = provinceId;
    }

    public String getProvinceName()
    {
        return provinceName;
    }

    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }

    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getAreaInfo()
    {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo)
    {
        this.areaInfo = areaInfo;
    }

    public int getIndustryType()
    {
        return industryType;
    }

    public void setIndustryType(int industryType)
    {
        this.industryType = industryType;
    }

    public String getIndustryTypeName()
    {
        return industryTypeName;
    }

    public void setIndustryTypeName(String industryTypeName)
    {
        this.industryTypeName = industryTypeName;
    }

    public int getProductSourceType()
    {
        return productSourceType;
    }

    public void setProductSourceType(int productSourceType)
    {
        this.productSourceType = productSourceType;
    }

    public String getProductSourceTypeName()
    {
        return productSourceTypeName;
    }

    public void setProductSourceTypeName(String productSourceTypeName)
    {
        this.productSourceTypeName = productSourceTypeName;
    }

    public int getSignFlag()
    {
        return signFlag;
    }

    public void setSignFlag(int signFlag)
    {
        this.signFlag = signFlag;
    }

    public String getSignCode()
    {
        return signCode;
    }

    public void setSignCode(String signCode)
    {
        this.signCode = signCode;
    }

    public Date getSignTime()
    {
        return signTime;
    }

    public void setSignTime(Date signTime)
    {
        this.signTime = signTime;
    }

    public Date getFirstExportDate()
    {
        return firstExportDate;
    }

    public void setFirstExportDate(Date firstExportDate)
    {
        this.firstExportDate = firstExportDate;
    }

    public int getOrderCount()
    {
        return orderCount;
    }

    public void setOrderCount(int orderCount)
    {
        this.orderCount = orderCount;
    }

    public double getOrderTotalMoney()
    {
        return orderTotalMoney;
    }

    public void setOrderTotalMoney(double orderTotalMoney)
    {
        this.orderTotalMoney = orderTotalMoney;
    }

    public String getFirstOrderManagerUser()
    {
        return firstOrderManagerUser;
    }

    public void setFirstOrderManagerUser(String firstOrderManagerUser)
    {
        this.firstOrderManagerUser = firstOrderManagerUser;
    }

    public String getFirstOrderManagerUserName()
    {
        return firstOrderManagerUserName;
    }

    public void setFirstOrderManagerUserName(String firstOrderManagerUserName)
    {
        this.firstOrderManagerUserName = firstOrderManagerUserName;
    }

    public String getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(String customerService)
    {
        this.customerService = customerService;
    }

    public String getCustomerServiceName()
    {
        return customerServiceName;
    }

    public void setCustomerServiceName(String customerServiceName)
    {
        this.customerServiceName = customerServiceName;
    }

    public Date getLastExportDate()
    {
        return lastExportDate;
    }

    public void setLastExportDate(Date lastExportDate)
    {
        this.lastExportDate = lastExportDate;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public int getStopFlag()
    {
        return stopFlag;
    }

    public void setStopFlag(int stopFlag)
    {
        this.stopFlag = stopFlag;
    }

}
