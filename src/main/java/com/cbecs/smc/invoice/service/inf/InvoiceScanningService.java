package com.cbecs.smc.invoice.service.inf;

import java.util.List;
import java.util.Map;

import com.cbecs.smc.invoice.model.InvoiceCheckDetail;
import com.cbecs.smc.invoice.model.ScanningApplication;
import com.cbecs.smc.invoice.model.ScanningCheckDetail;
import com.cbecs.smc.invoice.model.ScanningDetail;
import com.cbecs.smc.invoice.model.ScanningDetailRelation;
import com.cbecs.smc.invoice.model.ScanningEdition;
import com.cbecs.smc.invoice.model.ScanningList;
import com.cbecs.smc.invoice.model.ScanningModification;
import com.cbecs.smc.invoice.model.ScanningQueryPageable;

public interface InvoiceScanningService
{
    /**
     * 查询发票扫描申请的信息
     * 
     * @param orderCode
     * @return
     */
    List<ScanningList> list(ScanningQueryPageable model);

    /**
     * 新增发票扫描申请
     * 
     * @param orderCode
     * @return
     */
    Map<String, Object> add(List<ScanningApplication> model);

    /**
     * 编辑发票扫描申请
     * 
     * @param orderCode
     * @return
     */
    Map<String, Object> edit(ScanningModification model);

    /**
     * 删除发票扫描申请
     * 
     * @param applicationId
     * @return
     */
    Map<String, Object> remove(String applicationId);

    /**
     * 根据申请单号查询申请单数据信息
     * 
     * @param applicationId
     * @return
     */
    ScanningEdition getScanningAppLicationByApplicationId(String applicationId);

    /**
     * 申请详情，根据applicationId查询申请单的信息
     * 
     * @param applicationId
     * @return
     */
    ScanningDetail getScanningDetailByApplicationId(String applicationId);

    /**
     * 申请详情，关联订单对应的信息，除去自身
     * 
     * @param applicationId
     * @param orderId
     * @return
     */
    List<ScanningDetailRelation> getScanningDetailByOrderCode(String applicationId, String orderId);

    /**
     * 根据orderId获取订单的发票申请信息--比对详情页面数据
     * 
     * @param orderId
     * @return
     */
    ScanningCheckDetail getOrderAndInvoceByOrderId(String orderId);

    /**
     * 发票比对详情
     * 
     * @param invoiceCode
     * @param invoiceSellerId
     * @return
     */
    List<InvoiceCheckDetail> getOrderInvoiceDetail(String orderId, String invoiceNumber, String invoiceSellerId);

    /**
     * 根据订单id获取开票人信息
     * 
     * @param orderId
     * @return
     */
    List<Map<String, Object>> getOrderSuppliersByOrderId(String orderId);
}
