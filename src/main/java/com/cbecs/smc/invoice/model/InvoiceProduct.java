package com.cbecs.smc.invoice.model;

import java.io.Serializable;

public class InvoiceProduct implements Serializable {

	private static final long serialVersionUID = -4525985269161752515L;
	
	/**
	 * 主键Id
	 */
	public String productId;
	/**
	 * 发票Id
	 */
	public String invoiceId;
	/**
	 * 申报品名
	 */
	public String productDeclareName;
	/**
	 * 申报规格
	 */
	public String productSpecification;
	/**
	 * 单位
	 */
	public int productUnit;
	/**
	 * 单位中文名称
	 */
	public String productUnitName;
	/**
	 * 数量
	 */
	public double productQuanity;
	/**
	 * 单价
	 */
	public double productPrice;
	/**
	 * 未税金额
	 */
	public double productTotalAmount;
	/**
	 * 税率
	 */
	public double productTaxRate;
	/**
	 * 税额
	 */
	public double productTaxAmount;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getProductDeclareName() {
		return productDeclareName;
	}
	public void setProductDeclareName(String productDeclareName) {
		this.productDeclareName = productDeclareName;
	}
	public String getProductSpecification() {
		return productSpecification;
	}
	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}
	public int getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(int productUnit) {
		this.productUnit = productUnit;
	}
	public String getProductUnitName() {
		return productUnitName;
	}
	public void setProductUnitName(String productUnitName) {
		this.productUnitName = productUnitName;
	}
	public double getProductQuanity() {
		return productQuanity;
	}
	public void setProductQuanity(double productQuanity) {
		this.productQuanity = productQuanity;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public double getProductTotalAmount() {
		return productTotalAmount;
	}
	public void setProductTotalAmount(double productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
	}
	public double getProductTaxRate() {
		return productTaxRate;
	}
	public void setProductTaxRate(double productTaxRate) {
		this.productTaxRate = productTaxRate;
	}
	public double getProductTaxAmount() {
		return productTaxAmount;
	}
	public void setProductTaxAmount(double productTaxAmount) {
		this.productTaxAmount = productTaxAmount;
	}
}
