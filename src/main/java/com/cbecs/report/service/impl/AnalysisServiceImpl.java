package com.cbecs.report.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.report.model.ConsignerActiveQueryReport;
import com.cbecs.report.model.ConsignerActiveReport;
import com.cbecs.report.model.ConsignerExport;
import com.cbecs.report.model.ConsignerExportPageable;
import com.cbecs.report.model.ConsignerExportQuery;
import com.cbecs.report.model.ConsignerReorderQueryReport;
import com.cbecs.report.model.ConsignerReorderReport;
import com.cbecs.report.model.OrderDetailAnalysisQueryReport;
import com.cbecs.report.model.OrderDetailAnalysisQueryReportPageable;
import com.cbecs.report.model.OrderDetailAnalysisReport;
import com.cbecs.report.persistence.inf.AnalysisMapper;
import com.cbecs.report.service.inf.AnalysisService;

@Service
public class AnalysisServiceImpl implements AnalysisService
{

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public List<ConsignerActiveReport> getConsignerActive(ConsignerActiveQueryReport model)
    {
        return analysisMapper.selectConsignerActive(model);
    }

    @Override
    public List<ConsignerReorderReport> getConsignerReorder(ConsignerReorderQueryReport model)
    {
        return analysisMapper.selectConsignerReorder(model);
    }

    @Override
    public List<OrderDetailAnalysisReport> getOrderDetailAnalysisByPage(OrderDetailAnalysisQueryReportPageable model)
    {
        return analysisMapper.selectOrderDetailAnalysisByPage(model);
    }

    @Override
    public List<OrderDetailAnalysisReport> getOrderDetailAnalysis(OrderDetailAnalysisQueryReport model)
    {
        return analysisMapper.selectOrderDetailAnalysis(model);
    }

    @Override
    public String getExportReport()
    {
        StringBuilder res = new StringBuilder();
        List<ConsignerExport> customerServices = analysisMapper.getExportCustomers();
        for (ConsignerExport consignerExport : customerServices)
        {
            res.append("<option value='" + consignerExport.getCustomerService() + "'>"
                    + consignerExport.getCustomerServiceName() + "</option>");
        }
        return res.toString();
    }

    @Override
    public String getproductSourceType()
    {
        StringBuilder res = new StringBuilder();
        List<ConsignerExport> productSourceType = analysisMapper.getProductSourceType();
        for (ConsignerExport consignerExport : productSourceType)
        {
            res.append("<option value='" + consignerExport.getProductSourceType() + "'>"
                    + consignerExport.getProductSourceTypeName() + "</option>");
        }
        return res.toString();
    }

    @Override
    public String getIndustryType()
    {
        StringBuilder res = new StringBuilder();
        List<ConsignerExport> industryType = analysisMapper.getExportIndustryType();
        for (ConsignerExport consignerExport : industryType)
        {
            res.append("<option value='" + consignerExport.getIndustryType() + "'>"
                    + consignerExport.getIndustryTypeName() + "</option>");
        }
        return res.toString();
    }

    @Override
    public String getProvince()
    {
        StringBuilder res = new StringBuilder();
        List<ConsignerExport> provinceName = analysisMapper.getProvince();
        for (ConsignerExport consignerExport : provinceName)
        {
            res.append("<option value='" + consignerExport.getProvinceId() + "'>" + consignerExport.getProvinceName()
                    + "</option>");
        }
        return res.toString();
    }

    @Override
    public List<ConsignerExport> getCity(int provinceid)
    {

        List<ConsignerExport> cityName = analysisMapper.getCity(provinceid);

        return cityName;
    }

    @Override
    public List<ConsignerExport> getExportTable(ConsignerExportPageable model)
    {
        return analysisMapper.getExportTable(model);
    }

    @Override
    public List<ConsignerExport> getExportReport(ConsignerExportQuery model)
    {
        return analysisMapper.getExportReport(model);
    }

    @Override
    public String getFirstOrderManagerUser()
    {
        StringBuilder res = new StringBuilder();
        List<ConsignerExport> firstOrderManagerUser = analysisMapper.getfirstOrderManagerUser();
        for (ConsignerExport consignerExport : firstOrderManagerUser)
        {
            res.append("<option value='" + consignerExport.getFirstOrderManagerUser() + "'>"
                    + consignerExport.getFirstOrderManagerUserName() + "</option>");
        }
        return res.toString();
    }

    @Override
    public String getBusinessMainBodyInHtml()
    {
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> list = analysisMapper.selectBusinessMainBody();
        for (Map<String, Object> map : list)
        {
            sb.append("<option value='" + map.get("id") + "'>" + map.get("value") + "</option>");
        }
        return sb.toString();
    }

    @Override
    public List<Map<String, Object>> getBusinessMainBody()
    {
        return analysisMapper.selectBusinessMainBody();
    }

}