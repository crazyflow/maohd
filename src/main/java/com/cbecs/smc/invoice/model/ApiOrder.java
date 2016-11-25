package com.cbecs.smc.invoice.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;

/**
 * 接口模型-发票验证
 * 
 * @author Administrator
 */
@Table(name = "OrderInfo")
public class ApiOrder implements java.io.Serializable
{

    private static final long serialVersionUID = -655686390616942732L;
    /**
     * 订单id
     */
    @Id(name = "ID")
    private String orderId;
    /**
     * 开票人
     */
    private List<ApiOrderSupplier> suppliers;

    /**
     * 订单发票比对状态
     */
    @Column(name = "orderTransferDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderTransferDate;

    /**
     * 订单发票比对状态
     */
    @Column(name = "orderInvoiceCheckStatus")
    private int orderInvoiceCheckStatus;

    /**
     * 比对拒收票原因
     */
    @Column(name = "orderRefuseCollectInvoiceReason")
    private String orderRefuseCollectInvoiceReason;

    /**
     * 发票总金额
     */
    @Column(name = "orderInvoiceTotalAmount")
    private String orderInvoiceTotalAmount;

    /**
     * 金额
     */
    @Column(name = "orderInvoiceBeforeTaxAmount")
    private String orderInvoiceBeforeTaxAmount;

    /**
     * 金额
     */
    @Column(name = "orderInvoiceTaxAmount")
    private String orderInvoiceTaxAmount;

    /**
     * 发票个数
     */
    @Column(name = "orderInvoiceTotalNum")
    private String orderInvoiceTotalNum;

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public List<ApiOrderSupplier> getSuppliers()
    {
        return suppliers;
    }

    public void setSuppliers(List<ApiOrderSupplier> suppliers)
    {
        this.suppliers = suppliers;
    }

    public int getOrderInvoiceCheckStatus()
    {
        return orderInvoiceCheckStatus;
    }

    public void setOrderInvoiceCheckStatus(int orderInvoiceCheckStatus)
    {
        this.orderInvoiceCheckStatus = orderInvoiceCheckStatus;
    }

    public Date getOrderTransferDate()
    {
        return orderTransferDate;
    }

    public void setOrderTransferDate(Date orderTransferDate)
    {
        this.orderTransferDate = orderTransferDate;
    }

    public String getOrderRefuseCollectInvoiceReason()
    {
        return orderRefuseCollectInvoiceReason;
    }

    public void setOrderRefuseCollectInvoiceReason(String orderRefuseCollectInvoiceReason)
    {
        this.orderRefuseCollectInvoiceReason = orderRefuseCollectInvoiceReason;
    }

    public String getOrderInvoiceTotalAmount()
    {
        return orderInvoiceTotalAmount;
    }

    public void setOrderInvoiceTotalAmount(String orderInvoiceTotalAmount)
    {
        this.orderInvoiceTotalAmount = orderInvoiceTotalAmount;
    }

    public String getOrderInvoiceBeforeTaxAmount()
    {
        return orderInvoiceBeforeTaxAmount;
    }

    public void setOrderInvoiceBeforeTaxAmount(String orderInvoiceBeforeTaxAmount)
    {
        this.orderInvoiceBeforeTaxAmount = orderInvoiceBeforeTaxAmount;
    }

    public String getOrderInvoiceTaxAmount()
    {
        return orderInvoiceTaxAmount;
    }

    public void setOrderInvoiceTaxAmount(String orderInvoiceTaxAmount)
    {
        this.orderInvoiceTaxAmount = orderInvoiceTaxAmount;
    }

    public String getOrderInvoiceTotalNum()
    {
        return orderInvoiceTotalNum;
    }

    public void setOrderInvoiceTotalNum(String orderInvoiceTotalNum)
    {
        this.orderInvoiceTotalNum = orderInvoiceTotalNum;
    }

}