package com.cbecs.omc.order.service.inf;

import java.util.List;
import java.util.Map;

import com.cbecs.omc.order.model.ApiSimpleOrder;

public interface ApiOrderService
{
    /**
     * 获取验证信息
     * 
     * @param orderId
     * @return
     */
    Map<String, Object> getOrderInfoByOrderId(String orderId);

    /**
     * 获取开票人信息
     * 
     * @param orderCode
     * @return
     */
    List<Map<String,Object>> getOrderSupplierByOrderId(String orderId);

    /**
     * 获取购方信息
     * 
     * @param orderCode
     * @return
     */
    Map<String,Object> getOrderBusinessMainBodyByOrderId(String orderId);

    /**
     * 获取商品信息
     * 
     * @param orderCode
     * @return
     */
    List<Map<String,Object>> getOrderProductByOrderId(String orderId);

    /**
     * 更改订单的发票收齐等状态
     * 
     * @param model
     * @return
     */
    void editOrderInvoiceStatusByOrderId(ApiSimpleOrder model);
}
