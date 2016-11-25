package com.cbecs.report.service.inf;

import java.util.List;
import java.util.Map;

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

public interface AnalysisService
{
    /**
     * 查询委托方活跃度报表信息
     * 
     * @param model
     * @return
     */
    List<ConsignerActiveReport> getConsignerActive(ConsignerActiveQueryReport model);
    
    /**
     * 查询委托方返单率报表信息
     * 
     * @param model
     * @return
     */
    List<ConsignerReorderReport> getConsignerReorder(ConsignerReorderQueryReport model);
    
    /**
     * 订单明细分析报表分页
     * 
     * @param model
     * @return
     */
    List<OrderDetailAnalysisReport> getOrderDetailAnalysisByPage(OrderDetailAnalysisQueryReportPageable model);
    
    /**
     * 订单明细分析所有数据
     * 
     * @param model
     * @return
     */
    List<OrderDetailAnalysisReport> getOrderDetailAnalysis(OrderDetailAnalysisQueryReport model);
    
    
    /**
     * 获取客服信息
     * 
     * @param
     * @return
     */
    String getExportReport();
    /**
     * 获取企业类型信息
     * 
     * @param model
     * @return
     */
	String getproductSourceType();
	/**
     * 获取行业信息
     * 
     * @param
     * @return
     */
	String getIndustryType();
	/**
     * 获取省份信息
     * 
     * @param
     * @return
     */
	String getProvince();
	/**
     * 获取首单接待信息信息
     * 
     * @param 
     * @return
     */
	String getFirstOrderManagerUser();
	/**
	 * 获取城市信息
	 * @param provinceid
	 * @return
	 */
	List<ConsignerExport> getCity(int provinceid);
	/**
	 * 委托方出口分析统计信息
	 * @param model
	 * @return
	 */
	List<ConsignerExport> getExportTable(ConsignerExportPageable model);
	/**
	 * 委托方出口分析统计信息导出数据
	 * @param model
	 * @return
	 */
	List<ConsignerExport> getExportReport(ConsignerExportQuery model);
	
	/**
	 * 获取业务主体html
	 * @return
	 */
	String getBusinessMainBodyInHtml();
	
	/**
	 * 获取业务主体list
	 * @return
	 */
	List<Map<String,Object>> getBusinessMainBody();
}
