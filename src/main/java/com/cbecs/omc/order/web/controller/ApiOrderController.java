package com.cbecs.omc.order.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbecs.framework.web.JsonResult;
import com.cbecs.omc.order.service.inf.ApiOrderService;

@Controller
@RequestMapping(value = "/api/orders")
public class ApiOrderController
{

    @Autowired
    private ApiOrderService apiOrderService;
    
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody JsonResult runtimeExceptionHandler(RuntimeException runtimeException) 
    {  
        return JsonResult.Json(500, runtimeException.getMessage());
    }
    
    /**
     * 获取订单比对数据开票人，商品，购方数据信息 --吴凡
     * 
     * @return
     */
    @GetMapping(value = "/suppliers_products_company", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult getOrderInfoWithSupplierAndProduct(@RequestParam String orderId)
    {
        return JsonResult.Json(apiOrderService.getOrderInfoByOrderId(orderId));
    }

}