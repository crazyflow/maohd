package com.cbecs.omc.order.model;

/**
 * 发票扫描选择订单列表展示
 * @author Administrator
 *
 */
public class OrderInfoForInvoiceCollection implements java.io.Serializable
{

    private static final long serialVersionUID = 4275379096951643123L;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单code
     */
    private String orderCode;
    /**
     * 委托商id
     */
    private String companyId;
    /**
     * 委托商名称
     */
    private String companyName;
    /**
     * 开票人
     */
    private String suppliers;
    /**
     * 报关金额
     */
    private double totalForeignAmount;
    /**
     * 币种
     */
    private int currencyId;
    /**
     * 币种名称
     */
    private String currencyName;
    /**
     * 发票金额
     */
    private double invoiceAmount;
    /**
     * 是否存在采购合同
     */
    private int purchaseContract;
    /**
     * 是否存在报关预录单
     */
    private int delaration;

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
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

    public String getSuppliers()
    {
        return suppliers;
    }

    public void setSuppliers(String suppliers)
    {
        this.suppliers = suppliers;
    }

    public double getTotalForeignAmount()
    {
        return totalForeignAmount;
    }

    public void setTotalForeignAmount(double totalForeignAmount)
    {
        this.totalForeignAmount = totalForeignAmount;
    }

    public int getCurrencyId()
    {
        return currencyId;
    }

    public void setCurrencyId(int currencyId)
    {
        this.currencyId = currencyId;
    }

    public String getCurrencyName()
    {
        return currencyName;
    }

    public void setCurrencyName(String currencyName)
    {
        this.currencyName = currencyName;
    }

    public double getInvoiceAmount()
    {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount)
    {
        this.invoiceAmount = invoiceAmount;
    }

    public int getPurchaseContract()
    {
        return purchaseContract;
    }

    public void setPurchaseContract(int purchaseContract)
    {
        this.purchaseContract = purchaseContract;
    }

    public int getDelaration()
    {
        return delaration;
    }

    public void setDelaration(int delaration)
    {
        this.delaration = delaration;
    }

}
