package com.cbecs.report.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.cbecs.report.model.DrawerReport;
import com.cbecs.report.model.DrawerReportPageable;
import com.cbecs.report.model.ConsignerReport;
import com.cbecs.report.model.ConsignerReportPageable;
import com.cbecs.report.persistence.SelectSqlProvider;

public interface RiskControlMapper
{
    @SelectProvider(method = "getDrawerReportByPageSql", type = SelectSqlProvider.class)
    List<DrawerReport> getDrawerReportByPage(DrawerReportPageable p);
    
    @SelectProvider(method = "getDrawerReportSql", type = SelectSqlProvider.class)
    List<DrawerReport> getDrawerReport(DrawerReport p);

    @SelectProvider(method = "getConsignerReportByPageSql", type = SelectSqlProvider.class)
    List<ConsignerReport> getConsignerReportByPage(ConsignerReportPageable p);
    
    @SelectProvider(method = "getConsignerReportSql", type = SelectSqlProvider.class)
    List<ConsignerReport> getConsignerReport(ConsignerReport p);

}