package com.cbecs.omc.order.service.inf;

import java.util.List;

import com.cbecs.omc.order.model.OrderInfoBaseForInvoiceAddIndeCate;
import com.cbecs.omc.order.model.OrderInfoCommission;
import com.cbecs.omc.order.model.OrderInfoCommissionQueryByPage;
import com.cbecs.omc.order.model.OrderInfoForInvoiceCollection;
import com.cbecs.omc.order.model.OrderInfoForInvoiceCollectionQueryByPage;
import com.cbecs.smc.commision.model.SettlementApplication;
import com.cbecs.smc.invoice.model.InvoiceSupplier;

public interface OrderService
{
    /**
     * 根据渠道商id查询所关联的订单的信息
     * 
     * @param id
     * @return
     */
    List<OrderInfoCommission> getOrderInfoByChannelId(OrderInfoCommissionQueryByPage model);

    /**
     * 根据订单号返回订单的服务信息数据
     * 
     * @param orderIndexInfos,cooperationMode
     * @return
     */
    SettlementApplication getSettlementApplicationByOrderCode(String orders, int cooperationMode);

    /**
     * 发票扫描查询订单
     * 
     * @return
     */
    List<OrderInfoForInvoiceCollection> getOrderInfoForInvoiceCollection(
            OrderInfoForInvoiceCollectionQueryByPage model);

    /**
     * 根据订单code查询采购合同信息
     */
    List<OrderInfoBaseForInvoiceAddIndeCate> getInvoiceScanningInfoByOrderIds(String orderIds);

    /**
     * 根据订单id获取发票开票人信息
     * 
     * @param orderId
     * @return
     */
    List<InvoiceSupplier> getContractByOrderId(String orderId);

}
