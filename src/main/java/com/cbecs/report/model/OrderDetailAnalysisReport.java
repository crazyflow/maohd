package com.cbecs.report.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderDetailAnalysisReport implements Serializable
{

    private static final long serialVersionUID = -2042590540531556574L;
    /**
     * 订单id
     */
    private String id;
    /**
     * 订单号
     */
    private String code;
    /**
     * 报关单号
     */
    private String declarationNumber;
    /**
     * 业务主体
     */
    private int ourCompanytype;
    /**
     * 业务主体
     */
    private String ourCompanyName;
    /**
     * 客服id
     */
    private String customerService;
    /**
     * 客服名字
     */
    private String customerServiceName;
    /**
     * 客服所在部门
     */
    private String department;
    /**
     * 外贸顾问
     */
    private String managerUserId;
    /**
     * 外贸顾问
     */
    private String managerUserName;
    /**
     * 首单返单 1首单 0返单
     */
    private int orderFlag;
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
    private int ProvinceID;
    /**
     * 省name
     */
    private String ProvinceName;
    /**
     * 城市id
     */
    private int cityID;
    /**
     * 城市name
     */
    private String cityName;
    /**
     * 省市结合信息
     */
    private String areaInfo;
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 下单日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    /**
     * 下单方式
     */
    private int orderWay;
    /**
     * 出口日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date exportDate;
    /**
     * 申报日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date declarationDate;
    /**
     * 离境日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date packingLeaveDate;
    /**
     * 报关方式
     */
    private int declareType;
    /**
     * 指运港
     */
    private String destinationPort;
    /**
     * 最终目的国ID
     */
    private int destinationCountryID;
    /**
     * 最终目的国
     */
    private String destinationCountryName;
    /**
     * 物流服务
     */
    private int logisticsService;
    /**
     * 保证金
     */
    private int hasEnsureMoney;
    /**
     * 结算方式
     */
    private String paymentTerm;
    /**
     * 赊销服务
     */
    private int isSellCreditService;
    /**
     * 币种
     */
    private int currencyID;
    /**
     * 币种名称
     */
    private String currencyName;
    /**
     * 报关金额
     */
    private double totalForeignAmount;
    /**
     * 汇率
     */
    private double orderRate;
    /**
     * 报关金额人民币
     */
    private double orderAmountRMB;
    /**
     * 订单状态
     */
    private int status;
    /**
     * 是否删除
     */
    private int isDeleted;
    /**
     * 价格条款
     */
    private int tradeTerm;
    /**
     * 退税服务费类型
     */
    private int isRefundService;
    /**
     * 退税服务信息
     */
    private String refundService;
    /**
     * 赊销金额
     */
    private double sellCreditAmountRMB;

    /**
     * 开票人
     */
    private String drawers;

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

    public String getDeclarationNumber()
    {
        return declarationNumber;
    }

    public void setDeclarationNumber(String declarationNumber)
    {
        this.declarationNumber = declarationNumber;
    }

    public int getOurCompanytype()
    {
        return ourCompanytype;
    }

    public void setOurCompanytype(int ourCompanytype)
    {
        this.ourCompanytype = ourCompanytype;
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

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getManagerUserId()
    {
        return managerUserId;
    }

    public void setManagerUserId(String managerUserId)
    {
        this.managerUserId = managerUserId;
    }

    public String getManagerUserName()
    {
        return managerUserName;
    }

    public void setManagerUserName(String managerUserName)
    {
        this.managerUserName = managerUserName;
    }

    public int getOrderFlag()
    {
        return orderFlag;
    }

    public void setOrderFlag(int orderFlag)
    {
        this.orderFlag = orderFlag;
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

    public int getProvinceID()
    {
        return ProvinceID;
    }

    public void setProvinceID(int provinceID)
    {
        ProvinceID = provinceID;
    }

    public String getProvinceName()
    {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName)
    {
        ProvinceName = provinceName;
    }

    public int getCityID()
    {
        return cityID;
    }

    public void setCityID(int cityID)
    {
        this.cityID = cityID;
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

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public int getOrderWay()
    {
        return orderWay;
    }

    public void setOrderWay(int orderWay)
    {
        this.orderWay = orderWay;
    }

    public Date getExportDate()
    {
        return exportDate;
    }

    public void setExportDate(Date exportDate)
    {
        this.exportDate = exportDate;
    }

    public Date getDeclarationDate()
    {
        return declarationDate;
    }

    public void setDeclarationDate(Date declarationDate)
    {
        this.declarationDate = declarationDate;
    }

    public Date getPackingLeaveDate()
    {
        return packingLeaveDate;
    }

    public void setPackingLeaveDate(Date packingLeaveDate)
    {
        this.packingLeaveDate = packingLeaveDate;
    }

    public int getDeclareType()
    {
        return declareType;
    }

    public void setDeclareType(int declareType)
    {
        this.declareType = declareType;
    }

    public String getDestinationPort()
    {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort)
    {
        this.destinationPort = destinationPort;
    }

    public int getDestinationCountryID()
    {
        return destinationCountryID;
    }

    public void setDestinationCountryID(int destinationCountryID)
    {
        this.destinationCountryID = destinationCountryID;
    }

    public String getDestinationCountryName()
    {
        return destinationCountryName;
    }

    public void setDestinationCountryName(String destinationCountryName)
    {
        this.destinationCountryName = destinationCountryName;
    }

    public int getLogisticsService()
    {
        return logisticsService;
    }

    public void setLogisticsService(int logisticsService)
    {
        this.logisticsService = logisticsService;
    }

    public int getHasEnsureMoney()
    {
        return hasEnsureMoney;
    }

    public void setHasEnsureMoney(int hasEnsureMoney)
    {
        this.hasEnsureMoney = hasEnsureMoney;
    }

    public String getPaymentTerm()
    {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm)
    {
        this.paymentTerm = paymentTerm;
    }

    public int getIsSellCreditService()
    {
        return isSellCreditService;
    }

    public void setIsSellCreditService(int isSellCreditService)
    {
        this.isSellCreditService = isSellCreditService;
    }

    public int getCurrencyID()
    {
        return currencyID;
    }

    public void setCurrencyID(int currencyID)
    {
        this.currencyID = currencyID;
    }

    public String getCurrencyName()
    {
        return currencyName;
    }

    public void setCurrencyName(String currencyName)
    {
        this.currencyName = currencyName;
    }

    public double getTotalForeignAmount()
    {
        return totalForeignAmount;
    }

    public void setTotalForeignAmount(double totalForeignAmount)
    {
        this.totalForeignAmount = totalForeignAmount;
    }

    public double getOrderRate()
    {
        return orderRate;
    }

    public void setOrderRate(double orderRate)
    {
        this.orderRate = orderRate;
    }

    public double getOrderAmountRMB()
    {
        return orderAmountRMB;
    }

    public void setOrderAmountRMB(double orderAmountRMB)
    {
        this.orderAmountRMB = orderAmountRMB;
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

    public int getTradeTerm()
    {
        return tradeTerm;
    }

    public void setTradeTerm(int tradeTerm)
    {
        this.tradeTerm = tradeTerm;
    }

    public int getIsRefundService()
    {
        return isRefundService;
    }

    public void setIsRefundService(int isRefundService)
    {
        this.isRefundService = isRefundService;
    }

    public String getRefundService()
    {
        return refundService;
    }

    public void setRefundService(String refundService)
    {
        this.refundService = refundService;
    }

    public double getSellCreditAmountRMB()
    {
        return sellCreditAmountRMB;
    }

    public void setSellCreditAmountRMB(double sellCreditAmountRMB)
    {
        this.sellCreditAmountRMB = sellCreditAmountRMB;
    }

    public String getDrawers()
    {
        return drawers;
    }

    public void setDrawers(String drawers)
    {
        this.drawers = drawers;
    }

    public String getOurCompanyName()
    {
        return ourCompanyName;
    }

    public void setOurCompanyName(String ourCompanyName)
    {
        this.ourCompanyName = ourCompanyName;
    }

}
