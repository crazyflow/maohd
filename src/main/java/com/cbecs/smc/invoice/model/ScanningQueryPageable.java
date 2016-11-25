package com.cbecs.smc.invoice.model;

import com.cbecs.framework.mybatis.pagination.Pageable;

/**
 * 列表展示数据
 * 
 * @author Administrator
 */
public class ScanningQueryPageable extends Pageable implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 扫描单号
     */
    private String applicationCode;
    /**
     * 订单code
     */
    private String orderCode;

    /**
     * 扫描申请状态 1-扫描待收票 2-待扫描 3-扫描拒收票 4-扫描通过 9-扫描退票
     */
    private int applicationStatus;
    /**
     * 订单比对状态 10-比对待收票 20-比对拒收票 30-待认证 40-比对通过 50-比对不通过
     */
    private int orderInvoiceCheckStatus;

    /**
     * 客服
     */
    private String customerService;

    public String getApplicationCode()
    {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode)
    {
        this.applicationCode = applicationCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public int getApplicationStatus()
    {
        return applicationStatus;
    }

    public void setApplicationStatus(int applicationStatus)
    {
        this.applicationStatus = applicationStatus;
    }

    public int getOrderInvoiceCheckStatus()
    {
        return orderInvoiceCheckStatus;
    }

    public void setOrderInvoiceCheckStatus(int orderInvoiceCheckStatus)
    {
        this.orderInvoiceCheckStatus = orderInvoiceCheckStatus;
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