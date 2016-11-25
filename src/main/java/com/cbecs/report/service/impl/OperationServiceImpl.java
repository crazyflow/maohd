package com.cbecs.report.service.impl;

import org.springframework.stereotype.Service;

import com.cbecs.report.model.CustomerServiceReport;
import com.cbecs.report.model.CustomerServiceReportPageable;
import com.cbecs.report.model.LogisticsReport;
import com.cbecs.report.model.LogisticsReportPageable;
import com.cbecs.report.model.OrderReport;
import com.cbecs.report.model.OrderReportDetails;
import com.cbecs.report.model.OrderReportDetailsPageable;
import com.cbecs.report.model.OrderReportPageable;
import com.cbecs.report.persistence.inf.OperationMapper;
import com.cbecs.report.service.inf.OperationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OperationServiceImpl implements OperationService
{
    @Autowired
    private OperationMapper operationMapper;

    @Override
    public List<CustomerServiceReport> getCustomerServiceReportByPage(CustomerServiceReportPageable model)
    {
        return operationMapper.getCustomerServiceReportByPage(model);
    }
    
    @Override
    public List<CustomerServiceReport> getCustomerServiceReport(CustomerServiceReport model)
    {
        return operationMapper.getCustomerServiceReport(model);
    }
    
    @Override
    public List<LogisticsReport> getLogisticsReportByPage(LogisticsReportPageable model)
    {
        return operationMapper.getLogisticsReportByPage(model);
    }
    
    @Override
    public List<LogisticsReport> getLogisticsReport(LogisticsReport model)
    {
        return operationMapper.getLogisticsReport(model);
    }
    
    @Override
    public List<OrderReport> getOrderReportByPage(OrderReportPageable model)
    {
        return operationMapper.getOrderReportByPage(model);
    }
    
    @Override
    public List<OrderReport> getOrderReport(OrderReport model)
    {
        return operationMapper.getOrderReport(model);
    }
    
    @Override
    public String getCustomerServices(String customerServiceId)
    {
        StringBuilder res = new StringBuilder();
        List<OrderReport>  customerServices = operationMapper.getCustomerServices();
        for(OrderReport orderReport:customerServices){
            String tmp = "";
            if(orderReport.getCustomerServiceId().equals(customerServiceId)){
                tmp = "selected='selected'";
            }
            res.append("<option " + tmp + " value='"+orderReport.getCustomerServiceId()+"'>"+orderReport.getCustomerServiceName()+"</option>"); 
        }
        return res.toString();
    }

    @Override
    public List<OrderReportDetails> getOrderReportDetailsByPage(OrderReportDetailsPageable p)
    {
        return operationMapper.getOrderReportDetailsByPage(p);
    }

    @Override
    public List<OrderReportDetails> getOrderReportDetails(OrderReportDetails p)
    {
        return operationMapper.getOrderReportDetails(p);
    }
}