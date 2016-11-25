package com.cbecs.smc.invoice.model;

import java.util.List;

/**
 * 接口模型-开票人信息
 * 
 * @author Administrator
 */
public class ApiOrderSupplier implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 开票人id
     */
    private String supplierId;
    /**
     * 纳税识别证号
     */
    private String taxpayerIdentityNumber;
    /**
     * 开票人商品
     */
    private List<ApiOrderSupplierProduct> products;


    public String getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(String supplierId)
    {
        this.supplierId = supplierId;
    }

    public String getTaxpayerIdentityNumber()
    {
        return taxpayerIdentityNumber;
    }

    public void setTaxpayerIdentityNumber(String taxpayerIdentityNumber)
    {
        this.taxpayerIdentityNumber = taxpayerIdentityNumber;
    }

    public List<ApiOrderSupplierProduct> getProducts()
    {
        return products;
    }

    public void setProducts(List<ApiOrderSupplierProduct> products)
    {
        this.products = products;
    }


}