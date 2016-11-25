package com.cbecs.omc.order.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cbecs.framework.mybatis.pagination.Pageable;

/**
 * 接口模型--订单扫描状态
 * 
 * @author Administrator
 */
public class ApiOrderInfoForInvoiceQueryByPage extends Pageable implements java.io.Serializable {
	private static final long serialVersionUID = -4339080747057122495L;
	private String orderCode;
	private String customerName;
	private String supplierName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private int orderInvoiceCheckStatus ;
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getOrderInvoiceCheckStatus() {
		return orderInvoiceCheckStatus;
	}

	public void setOrderInvoiceCheckStatus(int orderInvoiceCheckStatus) {
		this.orderInvoiceCheckStatus = orderInvoiceCheckStatus;
	}

}
