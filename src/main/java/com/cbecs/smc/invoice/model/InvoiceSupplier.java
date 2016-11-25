package com.cbecs.smc.invoice.model;

/**
 * 扫描申请--新增和编辑时
 */
public class InvoiceSupplier implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 开票人id
     */
    private String supplierId;
    /**
     * 开票人name
     */
    private String supplierName;
    /**
     * 纳税识别证号
     */
    private String taxpayerIdentityNumber;
    /**
     * 总申报数量
     */
    private double declarequantity;
    /**
     * 合同金额
     */
    private String contractAmount;

    public String getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(String supplierId)
    {
        this.supplierId = supplierId;
    }

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public String getTaxpayerIdentityNumber()
    {
        return taxpayerIdentityNumber;
    }

    public void setTaxpayerIdentityNumber(String taxpayerIdentityNumber)
    {
        this.taxpayerIdentityNumber = taxpayerIdentityNumber;
    }

    public String getContractAmount()
    {
        return contractAmount;
    }

    public void setContractAmount(String contractAmount)
    {
        this.contractAmount = contractAmount;
    }

    public double getDeclarequantity()
    {
        return declarequantity;
    }

    public void setDeclarequantity(double declarequantity)
    {
        this.declarequantity = declarequantity;
    }

}