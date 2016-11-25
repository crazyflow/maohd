package com.cbecs.omc.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.omc.order.model.ApiSimpleOrder;
import com.cbecs.omc.order.persistence.inf.ApiOrderMapper;
import com.cbecs.omc.order.service.inf.ApiOrderService;

@Service
public class ApiOrderServiceImpl implements ApiOrderService
{
    private static final Logger logger = LoggerFactory.getLogger(ApiOrderServiceImpl.class);

    private static final String RESPONSE_CODE = "code";
    private static final String RESPONSE_MESSAGE = "message";
    private static final String RESPONSE_DATA = "data";
    private static final int RESPONSE_SUCCESS_CODE = 0;
    private static final int RESPONSE_ERROE_CODE = 1;

    @Autowired
    private ApiOrderService apiOrderService;

    @Autowired
    private ApiOrderMapper apiOrderMapper;

    /**
     * 接口数据
     */
    @Override
    public Map<String, Object> getOrderInfoByOrderId(String orderId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        Map<String, Object> infos = new HashMap<String, Object>();
        infos.put("orderId", orderId);
        // 订单开票人信息
        List<Map<String,Object>> orderSuppliers = apiOrderService.getOrderSupplierByOrderId(orderId);
        if (orderSuppliers == null || orderSuppliers.size() == 0)
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "无开票人信息");
            icsResponseCode.put(RESPONSE_DATA, "");
            logger.debug("该订单" + orderId + "无开票人信息");
            return icsResponseCode;
        }
        infos.put("orderSuppliers", orderSuppliers);
        // 订单业务主体信息
        Map<String,Object> orderBusinessMainBody = apiOrderService.getOrderBusinessMainBodyByOrderId(orderId);
        if (null == orderBusinessMainBody)
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "无购方信息");
            icsResponseCode.put(RESPONSE_DATA, "");
            logger.debug("该订单" + orderId + "无购方信息");
            return icsResponseCode;
        }
        infos.put("orderBusinessMainBody", orderBusinessMainBody);
        // 订单商品信息
        List<Map<String,Object>> orderProducts = apiOrderService.getOrderProductByOrderId(orderId);
        if (orderProducts == null || orderProducts.size() == 0)
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "无订单商品");
            icsResponseCode.put(RESPONSE_DATA, "");
            logger.debug("该订单" + orderId + "无订单商品");
            return icsResponseCode;
        }
        infos.put("orderProducts", orderProducts);
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_ERROE_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, infos);
        return icsResponseCode;
    }

    /**
     * 获取订单的开票人
     */
    @Override
    public List<Map<String,Object>> getOrderSupplierByOrderId(String orderId)
    {
        return apiOrderMapper.selectOrderSupplierByOrderId(orderId);
    }

    /**
     * 获取订单的业务主体
     */
    @Override
    public Map<String,Object> getOrderBusinessMainBodyByOrderId(String orderId)
    {
        return apiOrderMapper.selectOrderBusinessMainBodyByOrderId(orderId);
    }

    /**
     * 获取订单商品
     */
    @Override
    public List<Map<String,Object>> getOrderProductByOrderId(String orderId)
    {
        return apiOrderMapper.selectOrderProductByOrderId(orderId);
    }

    /**
     * 修改订单的发票是否收齐状态
     */
    @Override
    public void editOrderInvoiceStatusByOrderId(ApiSimpleOrder model)
    {
        apiOrderMapper.updateOrderInvoiceStatusByOrderId(model);
    }

}
