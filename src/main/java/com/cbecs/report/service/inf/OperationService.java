package com.cbecs.report.service.inf;
import java.util.List;

import com.cbecs.report.model.CustomerServiceReport;
import com.cbecs.report.model.CustomerServiceReportPageable;
import com.cbecs.report.model.LogisticsReport;
import com.cbecs.report.model.LogisticsReportPageable;
import com.cbecs.report.model.OrderReport;
import com.cbecs.report.model.OrderReportDetails;
import com.cbecs.report.model.OrderReportDetailsPageable;
import com.cbecs.report.model.OrderReportPageable;

public interface OperationService
{
    List<CustomerServiceReport> getCustomerServiceReportByPage(CustomerServiceReportPageable model);
    
    List<CustomerServiceReport> getCustomerServiceReport(CustomerServiceReport model);
    
    List<LogisticsReport> getLogisticsReportByPage(LogisticsReportPageable model);
    
    List<LogisticsReport> getLogisticsReport(LogisticsReport model);
    
    List<OrderReport> getOrderReportByPage(OrderReportPageable model);
    
    List<OrderReport> getOrderReport(OrderReport model);
    
    String getCustomerServices(String customerServiceId);

    List<OrderReportDetails> getOrderReportDetailsByPage(OrderReportDetailsPageable p);

    List<OrderReportDetails> getOrderReportDetails(OrderReportDetails p);
}
