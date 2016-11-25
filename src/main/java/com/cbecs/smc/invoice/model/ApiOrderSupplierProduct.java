package com.cbecs.smc.invoice.model;

/**
 * 接口模型-商品信息
 * 
 * @author Administrator
 */
public class ApiOrderSupplierProduct implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 商品id
     */
    private String productName;
    /**
     * 单位id
     */
    private int unitId;
    /**
     * 数量
     */
    private Double unitCount;
    /**
     * 申报要素
     */
    private String declareElementValue;

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public int getUnitId()
    {
        return unitId;
    }

    public void setUnitId(int unitId)
    {
        this.unitId = unitId;
    }

    public Double getUnitCount()
    {
        return unitCount;
    }

    public void setUnitCount(Double unitCount)
    {
        this.unitCount = unitCount;
    }

    public String getDeclareElementValue()
    {
        return declareElementValue;
    }

    public void setDeclareElementValue(String declareElementValue)
    {
        this.declareElementValue = declareElementValue;
    }

}