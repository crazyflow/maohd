package com.cbecs.report.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.cbecs.report.model.CustomerServiceReport;
import com.cbecs.report.model.CustomerServiceReportPageable;
import com.cbecs.report.model.LogisticsReport;
import com.cbecs.report.model.LogisticsReportPageable;
import com.cbecs.report.model.OrderReport;
import com.cbecs.report.model.OrderReportDetails;
import com.cbecs.report.model.OrderReportDetailsPageable;
import com.cbecs.report.model.OrderReportPageable;
import com.cbecs.report.persistence.SelectSqlProvider;

public interface OperationMapper
{    
    @SelectProvider(method = "getCustomerServiceReportByPageSql", type = SelectSqlProvider.class)
    List<CustomerServiceReport> getCustomerServiceReportByPage(CustomerServiceReportPageable p);
    
    @SelectProvider(method = "getCustomerServiceReportSql", type = SelectSqlProvider.class)
    List<CustomerServiceReport> getCustomerServiceReport(CustomerServiceReport p);
    
    @SelectProvider(method = "getLogisticsReportByPageSql", type = SelectSqlProvider.class)
    List<LogisticsReport> getLogisticsReportByPage(LogisticsReportPageable p);
    
    @SelectProvider(method = "getLogisticsReportSql", type = SelectSqlProvider.class)
    List<LogisticsReport> getLogisticsReport(LogisticsReport p);

    
    @SelectProvider(method = "getOrderReportByPageSql", type = SelectSqlProvider.class)
    List<OrderReport> getOrderReportByPage(OrderReportPageable p);

    @SelectProvider(method = "getOrderReportSql", type = SelectSqlProvider.class)
    List<OrderReport> getOrderReport(OrderReport p);

    @Select("select customerServiceId=id,customerServiceName=name from manageruser where id in (select CustomerService from orderinfo) order by name")
    List<OrderReport> getCustomerServices();

    @SelectProvider(method = "getOrderReportDetailsByPageSql", type = SelectSqlProvider.class)
    List<OrderReportDetails> getOrderReportDetailsByPage(OrderReportDetailsPageable p);
    
    @SelectProvider(method = "getOrderReportDetailsSql", type = SelectSqlProvider.class)
    List<OrderReportDetails> getOrderReportDetails(OrderReportDetails p);

}