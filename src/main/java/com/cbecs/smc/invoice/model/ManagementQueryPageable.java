package com.cbecs.smc.invoice.model;

import com.cbecs.framework.mybatis.pagination.Pageable;

/**
 * 列表展示数据
 * @author chenlong
 *
 */
public class ManagementQueryPageable extends Pageable implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 发票号码
     */
    private String invoiceNumber;
    
    /**
     * 销方名称
     */
    private String invoiceSellerName;
    
	/**
     * 客服ID
     */
    private String customerService;
    
    /**
     * 订单code
     */
    private String orderCode;
    
    /**
     * 状态 1-收齐 2-红冲
     */
    private int status;

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }
    
    public String getInvoiceSellerName() {
		return invoiceSellerName;
	}

	public void setInvoiceSellerName(String invoiceSellerName) {
		this.invoiceSellerName = invoiceSellerName;
	}

	public String getCustomerService() {
		return customerService;
	}

	public void setCustomerService(String customerService) {
		this.customerService = customerService;
	}
	
    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}