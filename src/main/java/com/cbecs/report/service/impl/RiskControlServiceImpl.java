package com.cbecs.report.service.impl;

import org.springframework.stereotype.Service;

import com.cbecs.report.model.DrawerReport;
import com.cbecs.report.model.DrawerReportPageable;
import com.cbecs.report.model.ConsignerReport;
import com.cbecs.report.model.ConsignerReportPageable;
import com.cbecs.report.persistence.inf.RiskControlMapper;
import com.cbecs.report.service.inf.RiskControlService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RiskControlServiceImpl implements RiskControlService
{
    @Autowired
    private RiskControlMapper riskControlMapper;

    @Override
    public List<DrawerReport> getDrawerReportByPage(DrawerReportPageable model)
    {
        return riskControlMapper.getDrawerReportByPage(model);
    }
    
    @Override
    public List<DrawerReport> getDrawerReport(DrawerReport model)
    {
        return riskControlMapper.getDrawerReport(model);
    }
    
    @Override
    public List<ConsignerReport> getConsignerReportByPage(ConsignerReportPageable model)
    {
        return riskControlMapper.getConsignerReportByPage(model);
    }
    
    @Override
    public List<ConsignerReport> getConsignerReport(ConsignerReport model)
    {
        return riskControlMapper.getConsignerReport(model);
    }
}