package com.cbecs.smc.invoice.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class InvoiceList implements Serializable {

	private static final long serialVersionUID = 1655351516597802078L;

	/**
     * 发票id
     */
    public String invoiceId;
	/**
     * 订单id
     */
    public String orderId;
    /**
     * 订单code
     */
    public String orderCode;
    /**
     * 发票代码
     */
    public String invoiceCode;
    /**
     * 发票号码
     */
    public String invoiceNumber;
    /**
     * 开票日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    /**
     * 销方名称
     */
    public String invoiceSellerName;
    /**
     * 销方税号
     */
    public String invoiceSellerTaxNumber;
    /**
     * 金额合计
     */
    public double invoiceBefortaxAmount;
    /**
     * 税额合计
     */
    public double invoiceTaxAmount;
    /**
     * 1:验证通过 2:验证未通过 3:比对不通过 4:比对通过 5:扫描退票 6:红冲
     */
    public int invoiceStatus;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceSellerName() {
		return invoiceSellerName;
	}
	public void setInvoiceSellerName(String invoiceSellerName) {
		this.invoiceSellerName = invoiceSellerName;
	}
	public String getInvoiceSellerTaxNumber() {
		return invoiceSellerTaxNumber;
	}
	public void setInvoiceSellerTaxNumber(String invoiceSellerTaxNumber) {
		this.invoiceSellerTaxNumber = invoiceSellerTaxNumber;
	}
	public double getInvoiceBefortaxAmount() {
		return invoiceBefortaxAmount;
	}
	public void setInvoiceBefortaxAmount(double invoiceBefortaxAmount) {
		this.invoiceBefortaxAmount = invoiceBefortaxAmount;
	}
	public double getInvoiceTaxAmount() {
		return invoiceTaxAmount;
	}
	public void setInvoiceTaxAmount(double invoiceTaxAmount) {
		this.invoiceTaxAmount = invoiceTaxAmount;
	}
	public int getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(int invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
}
