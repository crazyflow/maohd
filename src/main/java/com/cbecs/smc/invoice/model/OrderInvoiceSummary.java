package com.cbecs.smc.invoice.model;

import java.io.Serializable;

public class OrderInvoiceSummary implements Serializable {

	private static final long serialVersionUID = -8912407387005239105L;
	
	/**
	 * 订单Id
	 */
	public String orderId;
	
	/**
	 * 订单code
	 */
	public String orderCode;
	
	/**
	 * 公司Id
	 */
	public String companyId;
	
	/**
	 * 公司名称
	 */
	public String companyName;
	
	/**
	 * 发票数量
	 */
	public int invoiceCount;
	
	/**
	 * 发票比对通过数量
	 */
	public int invoiceComparePastCount;
	
	/**
	 * 金额合计
	 */
	public double orderInvoiceBeforeTaxAmount;
	
	/**
	 * 税额合计
	 */
	public double orderInvoiceTaxAmount;
	
	/**
	 * 订单发票状态
	 */
	public int status;

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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getInvoiceCount() {
		return invoiceCount;
	}

	public void setInvoiceCount(int invoiceCount) {
		this.invoiceCount = invoiceCount;
	}

	public int getInvoiceComparePastCount() {
		return invoiceComparePastCount;
	}

	public void setInvoiceComparePastCount(int invoiceComparePastCount) {
		this.invoiceComparePastCount = invoiceComparePastCount;
	}

	public double getOrderInvoiceBeforeTaxAmount() {
		return orderInvoiceBeforeTaxAmount;
	}

	public void setOrderInvoiceBeforeTaxAmount(double orderInvoiceBeforeTaxAmount) {
		this.orderInvoiceBeforeTaxAmount = orderInvoiceBeforeTaxAmount;
	}

	public double getOrderInvoiceTaxAmount() {
		return orderInvoiceTaxAmount;
	}

	public void setOrderInvoiceTaxAmount(double orderInvoiceTaxAmount) {
		this.orderInvoiceTaxAmount = orderInvoiceTaxAmount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
