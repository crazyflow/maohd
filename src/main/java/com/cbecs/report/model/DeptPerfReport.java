package com.cbecs.report.model;

import java.io.Serializable;
import java.util.Date;

public class DeptPerfReport implements Serializable
{

    private static final long serialVersionUID = 3167523586462765872L;
    /**
     * 订单id
     */
    private String id;
    /**
     * 订单编号
     */
    private String code;
    /**
     * 委托客户id
     */
    private String companyId;
    /**
     * 委托客户名称
     */
    private String companyName;
    /**
     * 客服
     */
    private String customerServiceId;
    /**
     * 客服id
     */
    private String customerServiceName;
    /**
     * 外贸顾问id
     */
    private String managerUser;
    /**
     * 出口日期
     */
    private Date exportDate;
    /**
     * 首单返单
     */
    private int orderFlag;
    /**
     * 出口金额
     */
    private double totalForeignAmount;
    /**
     * 销售上报日期
     */
    private Date planReceiptDate;
    /**
     * 报关日期
     */
    private Date declarationDate;
    /**
     * 收汇金额
     */
    private double clearanceForeignAmount;
    /**
     * 退税垫付
     */
    private int IsRefundService;
    /**
     * 退税垫付金额
     */
    private double refundTexAmount;
    /**
     * 货款交齐
     */
    private int clearanceFinished;
    /**
     * 退税垫付服务费
     */
    private double retTaxServiceAmount;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

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

    public String getCustomerServiceId()
    {
        return customerServiceId;
    }

    public void setCustomerServiceId(String customerServiceId)
    {
        this.customerServiceId = customerServiceId;
    }

    public String getCustomerServiceName()
    {
        return customerServiceName;
    }

    public void setCustomerServiceName(String customerServiceName)
    {
        this.customerServiceName = customerServiceName;
    }

    public String getManagerUser()
    {
        return managerUser;
    }

    public void setManagerUser(String managerUser)
    {
        this.managerUser = managerUser;
    }

    public Date getExportDate()
    {
        return exportDate;
    }

    public void setExportDate(Date exportDate)
    {
        this.exportDate = exportDate;
    }

    public int getOrderFlag()
    {
        return orderFlag;
    }

    public void setOrderFlag(int orderFlag)
    {
        this.orderFlag = orderFlag;
    }

    public double getTotalForeignAmount()
    {
        return totalForeignAmount;
    }

    public void setTotalForeignAmount(double totalForeignAmount)
    {
        this.totalForeignAmount = totalForeignAmount;
    }

    public Date getPlanReceiptDate()
    {
        return planReceiptDate;
    }

    public void setPlanReceiptDate(Date planReceiptDate)
    {
        this.planReceiptDate = planReceiptDate;
    }

    public Date getDeclarationDate()
    {
        return declarationDate;
    }

    public void setDeclarationDate(Date declarationDate)
    {
        this.declarationDate = declarationDate;
    }

    public double getClearanceForeignAmount()
    {
        return clearanceForeignAmount;
    }

    public void setClearanceForeignAmount(double clearanceForeignAmount)
    {
        this.clearanceForeignAmount = clearanceForeignAmount;
    }

    public int getIsRefundService()
    {
        return IsRefundService;
    }

    public void setIsRefundService(int isRefundService)
    {
        IsRefundService = isRefundService;
    }

    public double getRefundTexAmount()
    {
        return refundTexAmount;
    }

    public void setRefundTexAmount(double refundTexAmount)
    {
        this.refundTexAmount = refundTexAmount;
    }

    public int getClearanceFinished()
    {
        return clearanceFinished;
    }

    public void setClearanceFinished(int clearanceFinished)
    {
        this.clearanceFinished = clearanceFinished;
    }

    public double getRetTaxServiceAmount()
    {
        return retTaxServiceAmount;
    }

    public void setRetTaxServiceAmount(double retTaxServiceAmount)
    {
        this.retTaxServiceAmount = retTaxServiceAmount;
    }

}
