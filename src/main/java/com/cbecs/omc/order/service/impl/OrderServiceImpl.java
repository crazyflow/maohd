package com.cbecs.omc.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbecs.framework.web.session.SessionRepertory;
import com.cbecs.omc.order.model.OrderInfoBaseForInvoiceAddIndeCate;
import com.cbecs.omc.order.model.OrderInfoCommission;
import com.cbecs.omc.order.model.OrderInfoCommissionQueryByPage;
import com.cbecs.omc.order.model.OrderInfoForInvoiceCollection;
import com.cbecs.omc.order.model.OrderInfoForInvoiceCollectionQueryByPage;
import com.cbecs.omc.order.persistence.inf.OrderMapper;
import com.cbecs.omc.order.service.inf.OrderService;
import com.cbecs.smc.commision.model.SettlementApplication;
import com.cbecs.smc.commision.model.SettlementDetail;
import com.cbecs.smc.invoice.model.InvoiceSupplier;

@Service
public class OrderServiceImpl implements OrderService
{

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private @Value("${agency.fee}") double agencyFee;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SessionRepertory sessionRepertory;
    
    @Override
    public List<OrderInfoCommission> getOrderInfoByChannelId(OrderInfoCommissionQueryByPage model)
    {
        return orderMapper.selectOrderInfoByChannelId(model);
    }

    @Override
    @Transactional
    public SettlementApplication getSettlementApplicationByOrderCode(String codes, int cooperationMode)
    {
        // code,code,code
        SettlementApplication settlementApplication = new SettlementApplication();
        settlementApplication.setCooperationMode(cooperationMode);
        String[] codeArr = codes.substring(0, codes.length() - 1).split(",");
        List<SettlementDetail> list = new ArrayList<SettlementDetail>();
        for (String orderCode : codeArr)
        {
            SettlementDetail refundTax = orderMapper.selectSettlementRefundTaxByOrderCode(orderCode);
            // 退税垫付信息 -1
            if (null != refundTax)
            {
                list.add(refundTax);
            }
            // 佘销易信息 -2
            SettlementDetail sell = orderMapper.selectSettlementSellByOrderCode(orderCode);
            if (null != sell)
            {
                list.add(sell);
            }
            // 贸易服务代理费 -3
            SettlementDetail trade = orderMapper.selectSettlementTradeByOrderCode(orderCode);
            if (null != trade)
            {
                list.add(trade);
            }
            // 如果三个服务费都不存在，无需计算下面
            if (refundTax == null && sell == null && refundTax == trade)
            {
                // 生成一条数据用于前端提示，该已经完成的订单不具备佣金提取的条件,金融服务financialProduct设置为0
                SettlementDetail common = orderMapper.selectAgencyFeeAmountByOrderCodeForPartner(orderCode);
                common.setFinancialProduct("0");
                list.add(common);
                continue;
            }
            // 代理费 -4 取决于退税垫付，如果存在退税垫付，则渠道代理可以拿到代理费
            // 获取订单id
            if (1 == cooperationMode)
            {
                // 渠道拍档
                SettlementDetail partner = orderMapper.selectAgencyFeeAmountByOrderCodeForPartner(orderCode);
                list.add(partner);
            }
            else if (2 == cooperationMode)
            {
                SettlementDetail agent = null;
                // 渠道代理
                if (null == refundTax)
                {
                    agent = orderMapper.selectAgencyFeeAmountByOrderCodeForPartner(orderCode);

                }
                else
                {
                    // 查询代理费用结算后的佣金，退税额*0.5%
                    agent = orderMapper.selectAgencyFeeAmountByOrderCodeForAgency(orderCode);
                    if (null != agent)
                    {
                        agent.setCommisionAmount(agent.getCommisionAmount() * agencyFee);
                    }
                }
                list.add(agent);
            }
            else
            {
                logger.info("other channelAgencyMode");
            }
        }
        settlementApplication.setSettlementDetails(list);
        return settlementApplication;
    }

    @Override
    public List<OrderInfoForInvoiceCollection> getOrderInfoForInvoiceCollection(
            OrderInfoForInvoiceCollectionQueryByPage model)
    {
        model.setCustomerService(sessionRepertory.get().getId());
        return orderMapper.selectOrderInfoForInvoiceCollection(model);
    }

    @Override
    public List<OrderInfoBaseForInvoiceAddIndeCate> getInvoiceScanningInfoByOrderIds(String orderIds)
    {
        String[] orderIdsArr = orderIds.substring(0, orderIds.length() - 1).split(",");
        List<OrderInfoBaseForInvoiceAddIndeCate> list = new ArrayList<OrderInfoBaseForInvoiceAddIndeCate>();
        for (String orderId : orderIdsArr)
        {
            // 查询订单的扫描申请要展示的信息
            OrderInfoBaseForInvoiceAddIndeCate orderInfoBaseForInvoice = orderMapper.selectOrderInfoByOrderId(orderId);
            // 获取订单的开票人信息
            orderInfoBaseForInvoice.setSuppliers(getContractByOrderId(orderId));
            list.add(orderInfoBaseForInvoice);
        }
        return list;
    }

    @Override
    public List<InvoiceSupplier> getContractByOrderId(String orderId)
    {
        return orderMapper.selectContractByOrderId(orderId);
    }

}
