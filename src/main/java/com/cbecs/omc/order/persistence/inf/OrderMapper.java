package com.cbecs.omc.order.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.cbecs.omc.order.model.OrderInfoBaseForInvoiceAddIndeCate;
import com.cbecs.omc.order.model.OrderInfoCommission;
import com.cbecs.omc.order.model.OrderInfoCommissionQueryByPage;
import com.cbecs.omc.order.model.OrderInfoForInvoiceCollection;
import com.cbecs.omc.order.model.OrderInfoForInvoiceCollectionQueryByPage;
import com.cbecs.omc.order.persistence.SelectSqlProvider;
import com.cbecs.smc.commision.model.SettlementDetail;
import com.cbecs.smc.invoice.model.InvoiceSupplier;

public interface OrderMapper
{
    /**
     * 查询渠道商的订单信息
     * 
     * @param id
     * @return
     */
    @SelectProvider(method = "selectOrderInfoByChannelByPage", type = SelectSqlProvider.class)
    List<OrderInfoCommission> selectOrderInfoByChannelId(OrderInfoCommissionQueryByPage model);

    /**
     * 根据订单code查询退税垫付的服务信息
     * 
     * @param orderCode
     * @return
     */
    @Select("  SELECT o.[ID] 'orderId', o.[Code] 'orderCode',  o.[CompanyID] 'entrustingId', "
            + " o.[CompanyName] 'entrustingName', 1 'financialProduct' , "
            + " od.[TaxRefundMoney]-od.[AdvanceRefundCommission] 'costAmount' ,od.[TaxRefundMoney] 'incomeAmount' ,od.[AdvanceRefundCommission] 'commisionAmount' "
            + "  FROM [OrderInfo] o " + "  LEFT JOIN [AdvanceRefundApplyOrderDetail] od ON o.[Code] = od.[OrderCode] "
            + "  LEFT JOIN [AdvanceRefundApply] ra  ON od.ApplyID = ra.ID " + "  WHERE   "
            + "  o.[IsRefundService] = 1 "
            + "  AND ra.Status = 30 AND od.[TaxRefundMoney] IS NOT NULL AND od.[AdvanceRefundCommission] IS NOT NULL "
            + "  AND o.[status] in (90,92) " + "  AND o.[Code] = #{value}")
    SettlementDetail selectSettlementRefundTaxByOrderCode(String orderCode);

    /**
     * 根据订单code查询订单赊销易服务信息
     * 
     * @param orderCode
     * @return
     */
    @Select("  SELECT o.[ID] 'orderId', o.[Code] 'orderCode',  o.[CompanyID] 'entrustingId', "
            + " o.[CompanyName] 'entrustingName', 2 'financialProduct' , "
            + " rad.[FinancingServiceMoney]-rad.[FinancingServiceCommission] 'costAmount' ,rad.[FinancingServiceMoney] 'incomeAmount' ,rad.[FinancingServiceCommission] 'commisionAmount' "
            + " FROM [OrderInfo] o  " + " LEFT JOIN [FinancingRepayApplyDetail] rad ON o.[Code] = rad.[OrderCode]   "
            + " LEFT JOIN FinancingRepayApply fra ON fra.id = rad.FinancingRepayApplyID   "
            + " WHERE o.[IsSellCreditService] = 1   "
            + " AND rad.[FinancingServiceMoney] IS NOT NULL AND rad.[FinancingServiceCommission] IS NOT NULL  "
            + " AND o.[Status] in (90,92)  " + " AND fra.AuditFlag = 2 "
            + " AND rad.IsFinished = 1 AND rad.orderCode = #{value}")
    SettlementDetail selectSettlementSellByOrderCode(String orderCode);

    /**
     * 根据订单code查询贸易服务信息
     * 
     * @param orderCode
     * @return
     */
    @Select("  SELECT o.[ID] 'orderId', o.[Code] 'orderCode',  o.[CompanyID] 'entrustingId', "
            + " o.[CompanyName] 'entrustingName', 3 'financialProduct' , "
            + " o.[TradeServiceMoney]-o.[TradeServiceCommission] 'costAmount' ,o.[TradeServiceMoney] 'incomeAmount' ,o.[TradeServiceCommission] 'commisionAmount' "
            + " FROM [OrderInfo] o WHERE o.[TradeServiceMoney] IS NOT NULL AND o.[TradeServiceCommission] IS NOT NULL "
            + " AND o.[Status] in (90,92) " + " AND o.[Code] = #{value} ")
    SettlementDetail selectSettlementTradeByOrderCode(String orderCode);

    /**
     * 根据订单查询渠道代理的代理费
     * 
     * @param orderCode
     * @return
     */
    @Select(" SELECT o.[ID] 'orderId', o.[Code] 'orderCode',  o.[CompanyID] 'entrustingId', "
            + " o.[CompanyName] 'entrustingName', 4 'financialProduct' , "
            + " 0.00 'costAmount' ,0.00 'incomeAmount' ,od.[RefundTaxAmount] 'commisionAmount'  "
            + " FROM [OrderInfo] o LEFT JOIN [AdvanceRefundApplyOrderDetail] od ON o.[Code] = od.[OrderCode] AND od.[RefundTaxAmount] IS NOT NULL  "
            + " LEFT JOIN [AdvanceRefundApply] ra  ON od.ApplyID = ra.ID AND ra.Status = 30  "
            + " WHERE o.[Code] = #{value}")
    SettlementDetail selectAgencyFeeAmountByOrderCodeForAgency(String orderCode);

    /**
     * 根据订单查询渠道拍档的代理费
     * 
     * @param orderCode
     * @return
     */
    @Select("  SELECT o.[ID] 'orderId', o.[Code] 'orderCode',  o.[CompanyID] 'entrustingId', "
            + " o.[CompanyName] 'entrustingName', 4 'financialProduct',"
            + " 0.00 'costAmount' ,0.00 'incomeAmount' ,0.00 'commisionAmount' "
            + " FROM [OrderInfo] o WHERE o.[Code] = #{value}")
    SettlementDetail selectAgencyFeeAmountByOrderCodeForPartner(String orderCode);

    /**
     * 查询订单信息--发票采集
     * 
     * @param id
     * @return
     */
    @SelectProvider(method = "selectOrderInfoForInvoiceCollectionByPage", type = SelectSqlProvider.class)
    List<OrderInfoForInvoiceCollection> selectOrderInfoForInvoiceCollection(
            OrderInfoForInvoiceCollectionQueryByPage model);

    /**
     * 查询订单信息
     * 
     * @param id
     * @return
     */
    @Select(" select o.ID 'orderId',o.Code 'orderCode',o.companyId,o.companyName, "
            + " case when exists(select i.* from invoice i where i.order_id = o.ID and invoice_status = 6 and deleted = 0) then 1 else 0 end as redInvoice "
            + " from orderInfo o " + " where o.Status not in (52,90,92) AND o.isdeleted = 0 and o.ID = #{value} ")
    OrderInfoBaseForInvoiceAddIndeCate selectOrderInfoByOrderId(String orderId);

    /**
     * 查询订单开票人采购合同信息
     * 
     * @param id
     * @return
     */
    @Select("  select ci.SupplierID,  s.Name supplierName, s.TaxpayerIdentityNumber 'taxpayerIdentityNumber',    ci.TotalLowerCNY 'contractAmount', "
            + " ( select sum(cipi.UnitCount) from ContractInvoiceProductInfo cipi where cipi.ContractInvoiceID = ci.ID) as declarequantity "
            + " from ContractInvoice ci " + " inner join Supplier s on ci.SupplierID = s.ID "
            + " where ci.OrderID = #{value} ")
    List<InvoiceSupplier> selectContractByOrderId(String orderId);

}