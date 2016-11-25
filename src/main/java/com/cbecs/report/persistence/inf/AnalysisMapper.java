package com.cbecs.report.persistence.inf;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

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
import com.cbecs.report.persistence.SelectSqlProvider;

public interface AnalysisMapper
{
    /**
     * 委托方活跃度报表分析
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectConsignerActive", type = SelectSqlProvider.class)
    List<ConsignerActiveReport> selectConsignerActive(ConsignerActiveQueryReport model);

    /**
     * 返单率报表分析
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectConsignerReorder", type = SelectSqlProvider.class)
    List<ConsignerReorderReport> selectConsignerReorder(ConsignerReorderQueryReport model);

    /**
     * 获取订单明细分析报表数据分页
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectOrderDetailAnalysisByPage", type = SelectSqlProvider.class)
    List<OrderDetailAnalysisReport> selectOrderDetailAnalysisByPage(OrderDetailAnalysisQueryReportPageable model);

    /**
     * 获取订单明细报表数据
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectOrderDetailAnalysis", type = SelectSqlProvider.class)
    List<OrderDetailAnalysisReport> selectOrderDetailAnalysis(OrderDetailAnalysisQueryReport model);

    @Select(" select mu.ID as CustomerService,mu.Name as CustomerServiceName from ManagerUser mu where mu.Position = '客服' and mu.StopFlag = 0 ")
    List<ConsignerExport> getExportCustomers();

    @Select("select distinct DictionaryCommon.Text as IndustryTypeName,Company.IndustryType   from Company left join DictionaryCommon on DictionaryCommon.id=Company.IndustryType where DictionaryCommon.DictionaryType='IndustryType'")
    List<ConsignerExport> getExportIndustryType();

    @Select("select distinct DictionaryCommon.Text as ProductSourceTypeName,Company.ProductSourceType from Company left join DictionaryCommon on DictionaryCommon.id=Company.ProductSourceType where DictionaryCommon.DictionaryType='ProductSourceType'")
    List<ConsignerExport> getProductSourceType();

    @Select("select distinct da1.Name as ProvinceName,ca.ProvinceID from dbo.Company b "
            + "LEFT JOIN dbo.[OrderInfo] o on b.ID=o.CompanyID "
            + "Left Join dbo.CompanyAddress ca on ca.CompanyID = b.ID and ca.AddressType='经营地址' "
            + "LEFT JOIN dbo.DictionaryArea da1 ON da1.Code = ca.ProvinceID where b.StopFlag = 0 and b.SignFlag=1 and o.[Status] NOT IN(1,10,40,52) AND o.ExportDate IS NOT NULL AND da1.Name IS NOT NULL")
    List<ConsignerExport> getProvince();

    @Select("select distinct da2.Name as CityName,ca.CityID from dbo.Company b LEFT JOIN dbo.[OrderInfo] o on b.ID=o.CompanyID Left Join dbo.CompanyAddress ca on ca.CompanyID = b.ID and ca.AddressType='经营地址'LEFT JOIN dbo.DictionaryArea da1 ON da1.Code = ca.ProvinceID LEFT JOIN dbo.DictionaryArea da2 ON da2.Code = ca.CityID where b.StopFlag = 0 and b.SignFlag=1 and o.[Status] NOT IN(1,10,40,52) AND o.ExportDate IS NOT NULL AND da1.Name IS NOT NULL AND ca.ProvinceID =#{value}")
    List<ConsignerExport> getCity(int provinceid);

    @SelectProvider(method = "getExportByPageSql", type = SelectSqlProvider.class)
    List<ConsignerExport> getExportTable(ConsignerExportPageable p);

    @SelectProvider(method = "getExportReportSql", type = SelectSqlProvider.class)
    List<ConsignerExport> getExportReport(ConsignerExportQuery p);

    @Select(" select mu.ID as FirstOrderManagerUser ,mu.Name as FirstOrderManagerUserName from ManagerUser mu where mu.Position = '外贸顾问' and mu.StopFlag = 0 ")
    List<ConsignerExport> getfirstOrderManagerUser();

    @Select(" select body_id as id,body_name as value from business_body ")
    List<Map<String, Object>> selectBusinessMainBody();
}