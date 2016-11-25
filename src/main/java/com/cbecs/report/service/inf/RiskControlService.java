package com.cbecs.report.service.inf;
import java.util.List;

import com.cbecs.report.model.DrawerReport;
import com.cbecs.report.model.DrawerReportPageable;
import com.cbecs.report.model.ConsignerReport;
import com.cbecs.report.model.ConsignerReportPageable;

public interface RiskControlService
{
    List<DrawerReport>getDrawerReportByPage(DrawerReportPageable model);
    
    List<DrawerReport> getDrawerReport(DrawerReport model);
    
    List<ConsignerReport> getConsignerReportByPage(ConsignerReportPageable model);
    
    List<ConsignerReport> getConsignerReport(ConsignerReport model);
}
