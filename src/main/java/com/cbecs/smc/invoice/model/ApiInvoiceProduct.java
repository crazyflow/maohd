package com.cbecs.smc.invoice.model;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;

/**
 * 接口模型-发票信息--钱伟
 * 
 * @author Administrator
 */
@Table(name = "invoice_product")
public class ApiInvoiceProduct implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 商品id
     */
    @Id(name = "product_id")
    private String productId;

    /**
     * 发票id
     */
    @Column(name = "invoice_id")
    private String invoiceId;

    /**
     * 商品名称
     */
    @Column(name = "prodcut_declare_name")
    private String prodcutDeclareName;

    /**
     * 商品规格
     */
    @Column(name = "product_specification")
    private String productSpecification;

    /**
     * 商品单位
     */
    @Column(name = "product_unit")
    private int productUnit;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 商品数量
     */
    @Column(name = "product_quanity")
    private double productQuanity;

    /**
     * 商品单价
     */
    @Column(name = "product_price")
    private double productPrice;

    /**
     * 商品总价
     */
    @Column(name = "product_total_amount")
    private double productTotalAmount;

    /**
     * 商品税率
     */
    @Column(name = "product_tax_rate")
    private double productTaxRate;

    /**
     * 商品税额
     */
    @Column(name = "product_tax_amount")
    private double productTaxAmount;

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId;
    }

    public String getProdcutDeclareName()
    {
        return prodcutDeclareName;
    }

    public void setProdcutDeclareName(String prodcutDeclareName)
    {
        this.prodcutDeclareName = prodcutDeclareName;
    }

    public String getProductSpecification()
    {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification)
    {
        this.productSpecification = productSpecification;
    }

    public int getProductUnit()
    {
        return productUnit;
    }

    public void setProductUnit(int productUnit)
    {
        this.productUnit = productUnit;
    }

    public double getProductQuanity()
    {
        return productQuanity;
    }

    public void setProductQuanity(double productQuanity)
    {
        this.productQuanity = productQuanity;
    }

    public double getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(double productPrice)
    {
        this.productPrice = productPrice;
    }

    public double getProductTotalAmount()
    {
        return productTotalAmount;
    }

    public void setProductTotalAmount(double productTotalAmount)
    {
        this.productTotalAmount = productTotalAmount;
    }

    public double getProductTaxRate()
    {
        return productTaxRate;
    }

    public void setProductTaxRate(double productTaxRate)
    {
        this.productTaxRate = productTaxRate;
    }

    public double getProductTaxAmount()
    {
        return productTaxAmount;
    }

    public void setProductTaxAmount(double productTaxAmount)
    {
        this.productTaxAmount = productTaxAmount;
    }

    public String getUnitName()
    {
        return unitName;
    }

    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }

}