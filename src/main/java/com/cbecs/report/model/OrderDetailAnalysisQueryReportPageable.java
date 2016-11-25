package com.cbecs.report.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cbecs.framework.mybatis.pagination.Pageable;

public class OrderDetailAnalysisQueryReportPageable extends Pageable implements java.io.Serializable
{

    private static final long serialVersionUID = 1460480990078133215L;
    /**
     * 搜索内容
     */
    private String searchVal;
    /**
     * 日期范围标志
     */
    private int rangeType;
    /**
     * 日期范围标志
     */
    private String rangeDate;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 赊销服务
     */
    private int isSellCreditService;
    /**
     * 物流服务 1--委托物流 2-自行安排物流 3-待定
     */
    private int logisticsService;
    /**
     * 报关方式
     */
    private int declareType;
    /**
     * 是否首单 1首单 0返单
     */
    private int orderFlag;
    /**
     * 当前客服
     */
    private String customerService;
    /**
     * 当前客服id
     */
    private String customerServiceId;
    /**
     * 外贸顾问
     */
    private String managerUserId;
    /**
     * 下单方式
     */
    private int orderWay;
    /**
     * 部门
     */
    private String department;
    /**
     * 状态
     */
    private int status;
    /**
     * 是否删除
     */
    private int isDeleted;

    public String getSearchVal()
    {
        return searchVal;
    }

    public void setSearchVal(String searchVal)
    {
        this.searchVal = searchVal;
    }

    public int getRangeType()
    {
        return rangeType;
    }

    public void setRangeType(int rangeType)
    {
        this.rangeType = rangeType;
    }

    public String getRangeDate()
    {
        return rangeDate;
    }

    public void setRangeDate(String rangeDate)
    {
        this.rangeDate = rangeDate;
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

    public int getIsSellCreditService()
    {
        return isSellCreditService;
    }

    public void setIsSellCreditService(int isSellCreditService)
    {
        this.isSellCreditService = isSellCreditService;
    }

    public int getLogisticsService()
    {
        return logisticsService;
    }

    public void setLogisticsService(int logisticsService)
    {
        this.logisticsService = logisticsService;
    }

    public int getDeclareType()
    {
        return declareType;
    }

    public void setDeclareType(int declareType)
    {
        this.declareType = declareType;
    }

    public int getOrderFlag()
    {
        return orderFlag;
    }

    public void setOrderFlag(int orderFlag)
    {
        this.orderFlag = orderFlag;
    }

    public String getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(String customerService)
    {
        this.customerService = customerService;
    }

    public String getCustomerServiceId()
    {
        return customerServiceId;
    }

    public void setCustomerServiceId(String customerServiceId)
    {
        this.customerServiceId = customerServiceId;
    }

    public String getManagerUserId()
    {
        return managerUserId;
    }

    public void setManagerUserId(String managerUserId)
    {
        this.managerUserId = managerUserId;
    }

    public int getOrderWay()
    {
        return orderWay;
    }

    public void setOrderWay(int orderWay)
    {
        this.orderWay = orderWay;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted)
    {
        this.isDeleted = isDeleted;
    }

}
