package com.cbecs.report.persistence;

import com.cbecs.report.model.ConsignerActiveQueryReport;
import com.cbecs.report.model.ConsignerExportPageable;
import com.cbecs.report.model.ConsignerExportQuery;
import com.cbecs.report.model.ConsignerReorderQueryReport;
import com.cbecs.report.model.ConsignerReport;
import com.cbecs.report.model.ConsignerReportPageable;
import com.cbecs.report.model.CustomerServiceReport;
import com.cbecs.report.model.CustomerServiceReportPageable;
import com.cbecs.report.model.DeptPerfPageableReport;
import com.cbecs.report.model.DeptPerfQueryReport;
import com.cbecs.report.model.DrawerReport;
import com.cbecs.report.model.DrawerReportPageable;
import com.cbecs.report.model.LogisticsReport;
import com.cbecs.report.model.LogisticsReportPageable;
import com.cbecs.report.model.OrderDetailAnalysisQueryReport;
import com.cbecs.report.model.OrderDetailAnalysisQueryReportPageable;
import com.cbecs.report.model.OrderReport;
import com.cbecs.report.model.OrderReportDetails;
import com.cbecs.report.model.OrderReportDetailsPageable;
import com.cbecs.report.model.OrderReportPageable;

public class SelectSqlProvider
{
    /* 开票人报表 */
    public static String getDrawerReportByPageSql(DrawerReportPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" id=s.Id, name = s.Name,").append(" companyId=c.Id, companyName=c.Name,")
                .append(" foundDate=s.FoundDate,")
                .append(" isCertificate=case when s.IsCertificate=1 then '√' else '' end,")
                .append(" registerAddress=(select a.name+b.name+c.name from supplierAddress saddr1 left join DictionaryArea a on a.id=saddr1.ProvinceID left join DictionaryArea b on b.id=saddr1.CityID left join DictionaryArea c on c.id=saddr1.DistrictID where saddr1.SupplierID=s.ID and saddr1.AddressType=1),")
                .append(" businessAddress=(select a.name+b.name+c.name from supplierAddress saddr2 left join DictionaryArea a on a.id=saddr2.ProvinceID left join DictionaryArea b on b.id=saddr2.CityID left join DictionaryArea c on c.id=saddr2.DistrictID where saddr2.SupplierID=s.ID and saddr2.AddressType=2),")
                .append(" supplierEnterpriseType=case when s.supplierenterprisetype=1 then '工厂' else '贸易公司' end,")
                .append(" vATInvoiceUpper=d.text,").append(" grantedDate=s.GrantedDate,")
                .append(" ticketCount=(select count(1) from HistoricalInvoice h where h.SupplierID=s.ID and h.Type=6),")
                .append(" sheetCount=(select count(1) from HistoricalInvoice h where h.SupplierID=s.ID and h.Type=7),")
                .append("  pictureCount=(select count(1) from HistoricalInvoice h where h.SupplierID=s.ID and h.Type=8),")
                .append(" doorplateCount=(select count(1) from HistoricalInvoice h where h.SupplierID=s.ID and h.Type=9),")
                .append(" exportProducts=(d1.Text+','+d2.Text+','+d3.Text),")
                .append(" isSupplier=case when exists(select InvoiceAmount from PurchaseInvoice p,OrderInfo o where s.id=p.supplierid and p.orderid=o.id and o.status not in(1,52) and o.isdeleted=0) then '√' else '' end,")
                .append(" amount=case when exists(select InvoiceAmount from PurchaseInvoice p,OrderInfo o where s.id=p.supplierid and p.orderid=o.id and o.status not in(1,52) and o.isdeleted=0 ) then (select sum(InvoiceAmount) from PurchaseInvoice p,OrderInfo o where s.id=p.supplierid and p.orderid=o.id and o.status not in(1,52) and o.isdeleted=0) else 0 end,")
                .append(" status=s.Status")
                .append(" from supplier s left join companysupplierrelation r on s.id = r.supplierid left join company c on c.id = r.companyid left join dictionarycommon d on d.id=s.vatinvoiceupper")
                .append(" left join CustomsProduct cp1 on cp1.ID=(select CustomsProductID from (select op.CustomsProductID,rn=ROW_NUMBER() OVER(ORDER BY COUNT(1) desc) from OrderInfoProduct op left join OrderInfo o on o.ID=op.OrderID and o.Status not in (1,52) where op.SupplierID = s.ID group by op.CustomsProductID) a where rn=1)")
                .append(" left join CustomsProduct cp2 on cp2.ID=(select CustomsProductID from (select op.CustomsProductID,rn=ROW_NUMBER() OVER(ORDER BY COUNT(1) desc) from OrderInfoProduct op left join OrderInfo o on o.ID=op.OrderID and o.Status not in (1,52) where op.SupplierID = s.ID group by op.CustomsProductID) a where rn=2)")
                .append(" left join CustomsProduct cp3 on cp3.ID=(select CustomsProductID from (select op.CustomsProductID,rn=ROW_NUMBER() OVER(ORDER BY COUNT(1) desc) from OrderInfoProduct op left join OrderInfo o on o.ID=op.OrderID and o.Status not in (1,52) where op.SupplierID = s.ID group by op.CustomsProductID) a where rn=3)")
                .append(" left join dictionarycommon d1 on d1.id=cp1.ProductFirstType")
                .append(" left join dictionarycommon d2 on d2.id=cp2.ProductFirstType")
                .append(" left join dictionarycommon d3 on d3.id=cp3.ProductFirstType where 0=0");
        if (model.getName() != null && model.getName() != "")
        {
            sql.append(" And CHARINDEX(#{name,jdbcType=VARCHAR},s.NAME) > 0");
        }
        if (model.getOrderStatus() != 0)
        {
            sql.append(" And s.STATUS = #{orderStatus,jdbcType=INTEGER}");
        }

        return sql.toString();

    }

    public static String getDrawerReportSql(DrawerReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" id=s.Id, name = s.Name,").append(" companyId=c.Id, companyName=c.Name,")
                .append(" foundDate=s.FoundDate,")
                .append(" isCertificate=case when s.IsCertificate=1 then '√' else '' end,")
                .append(" registerAddress=(select a.name+b.name+c.name from supplierAddress saddr1 left join DictionaryArea a on a.id=saddr1.ProvinceID left join DictionaryArea b on b.id=saddr1.CityID left join DictionaryArea c on c.id=saddr1.DistrictID where saddr1.SupplierID=s.ID and saddr1.AddressType=1),")
                .append(" businessAddress=(select a.name+b.name+c.name from supplierAddress saddr2 left join DictionaryArea a on a.id=saddr2.ProvinceID left join DictionaryArea b on b.id=saddr2.CityID left join DictionaryArea c on c.id=saddr2.DistrictID where saddr2.SupplierID=s.ID and saddr2.AddressType=2),")
                .append(" supplierEnterpriseType=case when s.supplierenterprisetype=1 then '工厂' else '贸易公司' end,")
                .append(" vATInvoiceUpper=d.text,").append(" grantedDate=s.GrantedDate,")
                .append(" ticketCount=(select count(1) from HistoricalInvoice h where h.SupplierID=s.ID and h.Type=6),")
                .append(" sheetCount=(select count(1) from HistoricalInvoice h where h.SupplierID=s.ID and h.Type=7),")
                .append("  pictureCount=(select count(1) from HistoricalInvoice h where h.SupplierID=s.ID and h.Type=8),")
                .append(" doorplateCount=(select count(1) from HistoricalInvoice h where h.SupplierID=s.ID and h.Type=9),")
                .append(" exportProducts=(d1.Text+','+d2.Text+','+d3.Text),")
                .append(" isSupplier=case when exists(select InvoiceAmount from PurchaseInvoice p,OrderInfo o where s.id=p.supplierid and p.orderid=o.id and o.status not in(1,52) and o.isdeleted=0) then '√' else '' end,")
                .append(" amount=case when exists(select InvoiceAmount from PurchaseInvoice p,OrderInfo o where s.id=p.supplierid and p.orderid=o.id and o.status not in(1,52) and o.isdeleted=0 ) then (select sum(InvoiceAmount) from PurchaseInvoice p,OrderInfo o where s.id=p.supplierid and p.orderid=o.id and o.status not in(1,52) and o.isdeleted=0) else 0 end,")
                .append(" status=s.Status")
                .append(" from supplier s left join companysupplierrelation r on s.id = r.supplierid left join company c on c.id = r.companyid left join dictionarycommon d on d.id=s.vatinvoiceupper")
                .append(" left join CustomsProduct cp1 on cp1.ID=(select CustomsProductID from (select op.CustomsProductID,rn=ROW_NUMBER() OVER(ORDER BY COUNT(1) desc) from OrderInfoProduct op left join OrderInfo o on o.ID=op.OrderID and o.Status not in (1,52) where op.SupplierID = s.ID group by op.CustomsProductID) a where rn=1)")
                .append(" left join CustomsProduct cp2 on cp2.ID=(select CustomsProductID from (select op.CustomsProductID,rn=ROW_NUMBER() OVER(ORDER BY COUNT(1) desc) from OrderInfoProduct op left join OrderInfo o on o.ID=op.OrderID and o.Status not in (1,52) where op.SupplierID = s.ID group by op.CustomsProductID) a where rn=2)")
                .append(" left join CustomsProduct cp3 on cp3.ID=(select CustomsProductID from (select op.CustomsProductID,rn=ROW_NUMBER() OVER(ORDER BY COUNT(1) desc) from OrderInfoProduct op left join OrderInfo o on o.ID=op.OrderID and o.Status not in (1,52) where op.SupplierID = s.ID group by op.CustomsProductID) a where rn=3)")
                .append(" left join dictionarycommon d1 on d1.id=cp1.ProductFirstType")
                .append(" left join dictionarycommon d2 on d2.id=cp2.ProductFirstType")
                .append(" left join dictionarycommon d3 on d3.id=cp3.ProductFirstType where 0=0");
        if (model.getName() != null && model.getName() != "")
        {
            sql.append(" And CHARINDEX(#{name,jdbcType=VARCHAR},s.NAME) > 0");
        }
        if (model.getOrderStatus() != 0)
        {
            sql.append(" And s.STATUS = #{orderStatus,jdbcType=INTEGER}");
        }

        return sql.toString();
    }

    /* 委托方报表 */
    public static String getConsignerReportByPageSql(ConsignerReportPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" id=a.id, name= a.name,").append(" industrytype=b.Text,")
                .append(" isCertificate='',").append(" registerAddress=c.Name+d.Name+e.Name,")
                .append(" businessAddress=f.Name+g.Name+h.Name,").append(" idCardCount=0,")
                .append(" personalReporCount=0").append(" from Company a ")
                .append(" left join companyAddress caddr1 on caddr1.CompanyID=a.ID and caddr1.AddressType='注册地址'")
                .append(" left join companyAddress caddr2 on caddr2.CompanyID=a.ID and caddr2.AddressType='经营地址'")
                .append(" left join dictionarycommon b on a.productsourcetype = b.id")
                .append(" LEFT JOIN DictionaryArea AS c ON caddr1.ProvinceID = c.Code ")
                .append(" LEFT JOIN DictionaryArea AS d ON caddr1.CityID = d.Code")
                .append(" LEFT JOIN DictionaryArea AS e ON caddr1.DistrictID = e.Code")
                .append(" LEFT JOIN DictionaryArea AS f ON caddr2.ProvinceID = f.Code")
                .append(" LEFT JOIN DictionaryArea AS g ON caddr2.CityID = g.Code")
                .append(" LEFT JOIN DictionaryArea AS h ON caddr2.DistrictID = h.Code where 0=0");
        if (model.getName() != null && model.getName() != "")
        {
            sql.append(" And CHARINDEX(#{name,jdbcType=VARCHAR},a.NAME) > 0");
        }
        if (model.getSignFlag() >= 0)
        {
            sql.append(" And a.signflag = #{signFlag,jdbcType=INTEGER}");
        }
        return sql.toString();

    }

    public static String getConsignerReportSql(ConsignerReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" name= a.name,").append(" industrytype=b.Text,").append(" isCertificate='',")
                .append(" registerAddress=c.Name+d.Name+e.Name,").append(" businessAddress=f.Name+g.Name+h.Name,")
                .append(" idCardCount=0,").append(" personalReporCount=0").append(" from Company a ")
                .append(" left join companyAddress caddr1 on caddr1.CompanyID=a.ID and caddr1.AddressType='注册地址'")
                .append(" left join companyAddress caddr2 on caddr2.CompanyID=a.ID and caddr2.AddressType='经营地址'")
                .append(" left join dictionarycommon b on a.productsourcetype = b.id")
                .append(" LEFT JOIN DictionaryArea AS c ON caddr1.ProvinceID = c.Code ")
                .append(" LEFT JOIN DictionaryArea AS d ON caddr1.CityID = d.Code")
                .append(" LEFT JOIN DictionaryArea AS e ON caddr1.DistrictID = e.Code")
                .append(" LEFT JOIN DictionaryArea AS f ON caddr2.ProvinceID = f.Code")
                .append(" LEFT JOIN DictionaryArea AS g ON caddr2.CityID = g.Code")
                .append(" LEFT JOIN DictionaryArea AS h ON caddr2.DistrictID = h.Code where 0=0");
        if (model.getName() != null && model.getName() != "")
        {
            sql.append(" And CHARINDEX(#{name,jdbcType=VARCHAR},a.NAME) > 0");
        }
        if (model.getSignFlag() >= 0)
        {
            sql.append(" And a.signflag = #{signFlag,jdbcType=INTEGER}");
        }
        return sql.toString();
    }

    /* 订单统计报表 */
    public static String getOrderReportByPageSql(OrderReportPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" customerServiceName, customerServiceId,").append(" orderCount=count(1),")
                .append(" orderAmount=sum(orderAmount) ").append(" from (select ")
                .append(" customerServiceName= m.Name,").append(" supplierName=s.Name,")
                .append(" companyName=o.CompanyName,")
                .append(" orderAmount=o.TotalForeignAmount*(case when not exists(select * from CompanyRates where CompanyID=o.companyId and ShowType in (2301,2302) and isdeleted = 0) then (select a.rate from DailyRates a where a.isdelete = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and a.RateType=2301) else (select a.rate from DailyRates a, CompanyRates b where a.isdelete = 0 and b.isdeleted = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and b.CompanyID=o.CompanyID and a.RateType=b.ShowType) end), ")
                .append(" orderDate=o.OrderDate, ").append(" orderCode=o.Code, ")
                .append(" customerServiceId=o.CustomerService ")
                .append(" from orderinfo o left join supplier s on o.supplierid=s.id")
                .append(" left join manageruser m on m.id = o.customerservice")
                .append(" where  o.isdeleted = 0 and o.status not in(1, 52) ) orderTmp where 0=0 ");

        if (model.getCustomerServiceId() != null && model.getCustomerServiceId() != "")
        {
            sql.append(" And orderTmp.customerServiceId = #{customerServiceId,jdbcType=VARCHAR}");
        }
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},orderTmp.orderCode) > 0");
        }
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And CHARINDEX(#{companyName,jdbcType=VARCHAR},orderTmp.companyName) > 0");
        }
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And orderTmp.OrderDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        if (model.getSupplierName() != null && model.getSupplierName() != "")
        {
            sql.append(" And CHARINDEX(#{supplierName,jdbcType=VARCHAR},orderTmp.supplierName) > 0");
        }
        sql.append(" group by customerServiceName, customerServiceId");
        return sql.toString();

    }

    public static String getOrderReportSql(OrderReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" customerServiceName, customerServiceId,").append(" orderCount=count(1),")
                .append(" orderAmount=sum(orderAmount) ").append(" from (select ")
                .append(" customerServiceName= m.Name,").append(" supplierName=s.Name,")
                .append(" companyName=o.CompanyName,")
                .append(" orderAmount=o.TotalForeignAmount*(case when not exists(select * from CompanyRates where CompanyID=o.companyId and ShowType in (2301,2302) and isdeleted = 0) then (select a.rate from DailyRates a where a.isdelete = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and a.RateType=2301) else (select a.rate from DailyRates a, CompanyRates b where a.isdelete = 0 and b.isdeleted = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and b.CompanyID=o.CompanyID and a.RateType=b.ShowType) end), ")
                .append(" orderDate=o.OrderDate, ").append(" orderCode=o.Code, ")
                .append(" customerServiceId=o.CustomerService ")
                .append(" from orderinfo o left join supplier s on o.supplierid=s.id")
                .append(" left join manageruser m on m.id = o.customerservice")
                .append(" where  o.isdeleted = 0 and o.status not in(1, 52) ) orderTmp where 0=0 ");

        if (model.getCustomerServiceId() != null && model.getCustomerServiceId() != "")
        {
            sql.append(" And orderTmp.customerServiceId = #{customerServiceId,jdbcType=VARCHAR}");
        }
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},orderTmp.orderCode) > 0");
        }
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And CHARINDEX(#{companyName,jdbcType=VARCHAR},orderTmp.companyName) > 0");
        }
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And orderTmp.OrderDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        if (model.getSupplierName() != null && model.getSupplierName() != "")
        {
            sql.append(" And CHARINDEX(#{supplierName,jdbcType=VARCHAR},orderTmp.supplierName) > 0");
        }
        sql.append(" group by customerServiceName, customerServiceId ORDER BY ordercount desc");
        return sql.toString();
    }

    /* 客服统计报表 */
    public static String getCustomerServiceReportByPageSql(CustomerServiceReportPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" orderId=o.id, orderCode=o.Code,")
                .append(" companyId=o.companyId, companyName=o.CompanyName,").append(" customerServiceName=m.Name,")
                .append(" paidAmount=case when EXISTS(select * from PAYAPPLYDETAIL pd,PAYAPPLY p")
                .append(" where p.ApplyID=pd.ApplyID and pd.OrderID=o.ID")
                .append(" and p.STATUS='30' and p.CapitalType2='40BB14F8-6470-4051-A50F-904663D79364') then (select sum(pd.Money) from PAYAPPLYDETAIL pd,PAYAPPLY p")
                .append(" where p.ApplyID=pd.ApplyID and pd.OrderID=o.ID")
                .append(" and p.STATUS='30' and p.CapitalType2='40BB14F8-6470-4051-A50F-904663D79364') else 0 end,")
                .append(" declarationAmount=o.TotalForeignAmount,").append(" currencySymbol=d1.symbol,")
                .append(" gatherDate=gor.CreateTime,").append(" gatherSymbol=d2.symbol,")
                .append(" gatherAmountForeign=gord.GatherForeignAmount,").append(" gatherRate=gord.GatherExchangeRate,")
                .append(" gatherAmountRmb=gord.GatherForeignAmount* gord.GatherExchangeRate")
                .append(" from manageruser m left join orderinfo o on m.id=o.customerservice left join GatherOrderRelevanceDetial gord on gord.OrderId=o.ID left join GatherOrderRelevance gor on gor.ID=gord.GatherOrderRelevanceId ")
                .append(" left join dictionarycurrency d1 on d1.id=o.currencyid left join dictionarycurrency d2 on d2.id=gord.GatherCurrencyID  ")
                .append(" where o.IsDeleted=0 and o.Status not in(1,52)");
        if (model.getCustomerServiceId() != null && model.getCustomerServiceId() != "")
        {
            sql.append(" And o.CustomerService = #{customerServiceId,jdbcType=VARCHAR}");
        }
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And CHARINDEX(#{companyName,jdbcType=VARCHAR},o.CompanyName) > 0");
        }
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},o.Code) > 0");
        }
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And gor.CreateTime between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        return sql.toString();

    }

    public static String getCustomerServiceReportSql(CustomerServiceReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" orderId=o.id, orderCode=o.Code,")
                .append(" companyId=o.companyId, companyName=o.CompanyName,").append(" customerServiceName=m.Name,")
                .append(" paidAmount=case when EXISTS(select * from PAYAPPLYDETAIL pd,PAYAPPLY p")
                .append(" where p.ApplyID=pd.ApplyID and pd.OrderID=o.ID")
                .append(" and p.STATUS='30' and p.CapitalType2='40BB14F8-6470-4051-A50F-904663D79364') then (select sum(pd.Money) from PAYAPPLYDETAIL pd,PAYAPPLY p")
                .append(" where p.ApplyID=pd.ApplyID and pd.OrderID=o.ID")
                .append(" and p.STATUS='30' and p.CapitalType2='40BB14F8-6470-4051-A50F-904663D79364') else 0 end,")
                .append(" declarationAmount=o.TotalForeignAmount,").append(" currencySymbol=d1.symbol,")
                .append(" gatherDate=gor.CreateTime,").append(" gatherSymbol=d2.symbol,")
                .append(" gatherAmountForeign=gord.GatherForeignAmount,").append(" gatherRate=gord.GatherExchangeRate,")
                .append(" gatherAmountRmb=gord.GatherForeignAmount* gord.GatherExchangeRate")
                .append(" from manageruser m left join orderinfo o on m.id=o.customerservice left join GatherOrderRelevanceDetial gord on gord.OrderId=o.ID left join GatherOrderRelevance gor on gor.ID=gord.GatherOrderRelevanceId ")
                .append(" left join dictionarycurrency d1 on d1.id=o.currencyid left join dictionarycurrency d2 on d2.id=gord.GatherCurrencyID  ")
                .append(" where o.IsDeleted=0 and o.Status not in(1,52)");
        if (model.getCustomerServiceId() != null && model.getCustomerServiceId() != "")
        {
            sql.append(" And o.CustomerService = #{customerServiceId,jdbcType=VARCHAR}");
        }
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And CHARINDEX(#{companyName,jdbcType=VARCHAR},o.CompanyName) > 0");
        }
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},o.Code) > 0");
        }
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And gor.CreateTime between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        sql.append(" order by o.code");
        return sql.toString();
    }

    /* 物流账单 */
    public static String getLogisticsReportByPageSql(LogisticsReportPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" orderCode=o.Code, orderId=o.Id,").append(" orderDate=o.OrderDate,")
                .append(" transportmode=case when olm.transportmode=0 then '空运' when olm.transportmode=1 then '海运' when olm.transportmode=2 then '快递' else '中港快运' end,")
                .append(" transportChildMode=case when olm.transportchildmode=1 then '订舱（空运）' when olm.transportchildmode=2 then '拖车+订舱（空运）' when olm.transportchildmode=3 then '拖车（海运）' when olm.transportchildmode=4 then '订舱（海运）' when olm.transportchildmode=5 then '拖车+订舱（海运）' else '中港陆运' end,")
                .append(" logisticscosttypename=case when lo.LogisticsCostTypeId in(2804,2805,2813,2814,2815,2816) then lo.logisticscosttypename else '包干费' end,")
                .append(" logisticsBusName=lo.logisticsbusname,logisticsBusId=lo.logisticsbusId,")
                .append(" costPrice=lo.costprice,").append(" customerPrice=lo.customerprice,")
                .append(" costCurrencyId=lo.costcurrencyid, customerCurrencyId=lo.customercurrencyid,currencyCode = d.code,costCurrencyCode = d1.Code ")
                .append(" from LogisticsOffer lo left join OrderLogisticsMergeDetail olmd on olmd.OrderLogisticsID=lo.LogisticsOrderId")
                .append(" left join OrderInfo o on olmd.OrderID=o.ID")
                .append(" left join OrderLogisticsMerge olm on olm.OrderLogisticsMergeID=olmd.OrderLogisticsMergeID left join DictionaryCurrency d on d.id = lo.CustomerCurrencyId left join dictionarycurrency d1 on d1.id = lo.costcurrencyid where lo.IsDeleted=0 and lo.OrderLogisticsMergeID is null");
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},o.Code) > 0");
        }
        if (model.getTransportModeId() >= 0)
        {
            sql.append(" And olm.TransportMode = #{transportModeId,jdbcType=INTEGER}");
        }
        if (model.getTransportChildModeId() > 0)
        {
            sql.append(" And olm.TransportChildMode = #{transportChildModeId,jdbcType=INTEGER}");
        }
        if (model.getLogisticsCostTypeNameId() > 0)
        {
            sql.append(model.getLogisticsCostTypeNameId() < 99999
                    ? " And lo.LogisticsCostTypeId = #{logisticsCostTypeNameId,jdbcType=INTEGER}"
                    : " And lo.LogisticsCostTypeId not in(2804,2805,2813,2814,2815,2816)");
        }
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And  o.OrderDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        if (model.getLogisticsBusName() != null && model.getLogisticsBusName() != "")
        {
            sql.append(" And CHARINDEX(#{logisticsBusName,jdbcType=VARCHAR},lo.LogisticsBusName) > 0");
        }
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And CHARINDEX(#{companyName,jdbcType=VARCHAR},o.CompanyName) > 0");
        }
        return sql.toString();

    }

    public static String getLogisticsReportSql(LogisticsReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select").append(" orderCode=o.Code,").append(" orderDate=o.OrderDate,")
                .append(" transportmode=case when olm.transportmode=0 then '空运' when olm.transportmode=1 then '海运' when olm.transportmode=2 then '快递' else '中港快运' end,")
                .append(" transportChildMode=case when olm.transportchildmode=1 then '订舱（空运）' when olm.transportchildmode=2 then '拖车+订舱（空运）' when olm.transportchildmode=3 then '拖车（海运）' when olm.transportchildmode=4 then '订舱（海运）' when olm.transportchildmode=5 then '拖车+订舱（海运）' else '中港陆运' end,")
                .append(" logisticscosttypename=case when lo.LogisticsCostTypeId in(2804,2805,2813,2814,2815,2816) then lo.logisticscosttypename else '包干费' end,")
                .append(" logisticsBusName=lo.logisticsbusname,").append(" costPrice=lo.costprice,")
                .append(" customerPrice=lo.customerprice,")
                .append(" costCurrencyId=lo.costcurrencyid, customerCurrencyId=lo.customercurrencyid,currencyCode = d.code,costCurrencyCode = d1.Code ")
                .append(" from LogisticsOffer lo left join OrderLogisticsMergeDetail olmd on olmd.OrderLogisticsID=lo.LogisticsOrderId")
                .append(" left join OrderInfo o on olmd.OrderID=o.ID")
                .append(" left join OrderLogisticsMerge olm on olm.OrderLogisticsMergeID=olmd.OrderLogisticsMergeID left join DictionaryCurrency d on d.id = lo.CustomerCurrencyId left join dictionarycurrency d1 on d1.id = lo.costcurrencyid  where lo.IsDeleted=0 and lo.OrderLogisticsMergeID is null");
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},o.Code) > 0");
        }
        if (model.getTransportModeId() >= 0)
        {
            sql.append(" And olm.TransportMode = #{transportModeId,jdbcType=INTEGER}");
        }
        if (model.getTransportChildModeId() > 0)
        {
            sql.append(" And olm.TransportChildMode = #{transportChildModeId,jdbcType=INTEGER}");
        }
        if (model.getLogisticsCostTypeNameId() > 0)
        {
            sql.append(model.getLogisticsCostTypeNameId() < 99999
                    ? " And lo.LogisticsCostTypeId = #{logisticsCostTypeNameId,jdbcType=INTEGER}"
                    : "And lo.LogisticsCostTypeId not in(2804,2805,2813,2814,2815,2816)");
        }
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And  o.OrderDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        if (model.getLogisticsBusName() != null && model.getLogisticsBusName() != "")
        {
            sql.append(" And CHARINDEX(#{logisticsBusName,jdbcType=VARCHAR},lo.LogisticsBusName) > 0");
        }
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And CHARINDEX(#{companyName,jdbcType=VARCHAR},o.CompanyName) > 0");
        }
        sql.append(" order by o.code desc");
        return sql.toString();
    }

    /* 订单明细报表 */
    public static String getOrderReportDetailsByPageSql(OrderReportDetailsPageable model)
    {
        StringBuilder sql = new StringBuilder();

        sql.append(" select").append(" orderDate=o.OrderDate, ").append(" orderCode=o.Code, orderId=o.Id,")
                .append(" companyName=o.CompanyName, companyId=o.CompanyId,").append(" customerServiceName= m.Name,")
                .append(" supplierName=s.Name, supplierId=s.id,")
                .append(" invoiceAmount=(select sum(p.invoiceAmount) from purchaseInvoice p where o.ID = p.OrderID and s.ID = p.SupplierID),")
                .append(" declarationAmount=op.foriegnAmount,").append(" currencySymbol=d1.symbol,")
                .append(" rate=(case when not exists(select * from CompanyRates where CompanyID=o.companyId and ShowType in (2301,2302) and isdeleted = 0) then (select a.rate from DailyRates a where a.isdelete = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and a.RateType=2301) else (select a.rate from DailyRates a, CompanyRates b where a.isdelete = 0 and b.isdeleted = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and b.CompanyID=o.CompanyID and a.RateType=b.ShowType) end), ")
                .append(" amount=op.foriegnAmount*(case when not exists(select * from CompanyRates where CompanyID=o.companyId and ShowType in (2301,2302) and isdeleted = 0) then (select a.rate from DailyRates a where a.isdelete = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and a.RateType=2301) else (select a.rate from DailyRates a, CompanyRates b where a.isdelete = 0 and b.isdeleted = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and b.CompanyID=o.CompanyID and a.RateType=b.ShowType) end) ")
                .append(" from orderinfo o ").append(" left join orderinfoproduct op on o.id=op.orderid ")
                .append(" left join supplier s on op.supplierid=s.id")
                .append(" left join manageruser m on m.id = o.customerservice")
                .append(" left join dictionarycurrency d1 on d1.id=o.currencyid")
                .append(" where o.isdeleted = 0 and o.status not in(1, 52)");
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},o.Code) > 0");
        }
        if (model.getCustomerServiceId() != null && model.getCustomerServiceId() != "")
        {
            sql.append(" And o.customerservice = #{customerServiceId,jdbcType=VARCHAR}");
        }
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And  o.OrderDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And CHARINDEX(#{companyName,jdbcType=VARCHAR},o.CompanyName) > 0");
        }
        if (model.getSupplierName() != null && model.getSupplierName() != "")
        {
            sql.append(" And CHARINDEX(#{supplierName,jdbcType=VARCHAR},s.Name) > 0");
        }
        return sql.toString();
    }

    /* 订单明细报表导出 */
    public static String getOrderReportDetailsSql(OrderReportDetails model)
    {
        StringBuilder sql = new StringBuilder();

        sql.append(" select").append(" orderDate=o.OrderDate, ").append(" orderCode=o.Code, orderId=o.Id,")
                .append(" companyName=o.CompanyName, companyId=o.CompanyId,").append(" customerServiceName= m.Name,")
                .append(" supplierName=s.Name, supplierId=s.id,")
                .append(" invoiceAmount=(select sum(p.invoiceAmount) from purchaseInvoice p where o.ID = p.OrderID and s.ID = p.SupplierID),")
                .append(" declarationAmount=op.foriegnAmount,").append(" currencySymbol=d1.symbol,")
                .append(" rate=(case when not exists(select * from CompanyRates where CompanyID=o.companyId and ShowType in (2301,2302) and isdeleted = 0) then (select a.rate from DailyRates a where a.isdelete = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and a.RateType=2301) else (select a.rate from DailyRates a, CompanyRates b where a.isdelete = 0 and b.isdeleted = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and b.CompanyID=o.CompanyID and a.RateType=b.ShowType) end), ")
                .append(" amount=op.foriegnAmount*(case when not exists(select * from CompanyRates where CompanyID=o.companyId and ShowType in (2301,2302) and isdeleted = 0) then (select a.rate from DailyRates a where a.isdelete = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and a.RateType=2301) else (select a.rate from DailyRates a, CompanyRates b where a.isdelete = 0 and b.isdeleted = 0 and a.RateDate=convert(char(10),o.OrderDate,120) and a.CurrencyId=o.currencyId and b.CompanyID=o.CompanyID and a.RateType=b.ShowType) end) ")
                .append(" from orderinfo o ").append(" left join orderinfoproduct op on o.id=op.orderid ")
                .append(" left join supplier s on op.supplierid=s.id")
                .append(" left join manageruser m on m.id = o.customerservice")
                .append(" left join dictionarycurrency d1 on d1.id=o.currencyid")
                .append(" where o.isdeleted = 0 and o.status not in(1, 52)");
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},o.Code) > 0");
        }
        if (model.getCustomerServiceId() != null && model.getCustomerServiceId() != "")
        {
            sql.append(" And o.customerservice = #{customerServiceId,jdbcType=VARCHAR}");
        }
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And  o.OrderDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And CHARINDEX(#{companyName,jdbcType=VARCHAR},o.CompanyName) > 0");
        }
        if (model.getSupplierName() != null && model.getSupplierName() != "")
        {
            sql.append(" And CHARINDEX(#{supplierName,jdbcType=VARCHAR},s.Name) > 0");
        }
        sql.append(" order by o.code desc");
        return sql.toString();
    }

    /* 委托方出口分析表 */
    public static String getExportByPageSql(ConsignerExportPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM ( " + " SELECT distinct b.ID as CompanyId,   b.Name as CompanyName, "
                + " ca.ProvinceID,    da1.Name as ProvinceName,   ca.CityID,   da2.Name as CityName,  "
                + " da1.Name+da2.Name as AreaInfo,   b.IndustryType ,   da3.Text as IndustryTypeName, "
                + " b.ProductSourceType ,   da4.Text as ProductSourceTypeName,  " + " cc.SignFlag,    "
                + " cc.SignCode,  " + " cc.SignTime,  "
                + " (select top 1 o.ExportDate from orderInfo o where ((o.status != 52 and o.isdeleted = 0 and o.OrderWay = 2) or (o.status not in (1,52) and o.isdeleted = 0 and o.OrderWay = 1)) and o.companyId = b.id  order by orderdate asc, createtime asc ) as FirstExportDate ,  "
                + " ( select count(c.ID) from [OrderInfo] c where c.CompanyID=b.ID and c.DeclarationDate is not null and c.[Status] != 52 and c.IsDeleted = 0) OrderCount,  "
                + " ( select sum(TotalForeignAmount* (case c.CurrencyID when 151 then 1.0000 else ISNULL(r.Rate,0)end)) from [OrderInfo] c left join MonthlyRates r on r.CurrencyId=c.CurrencyID and r.RateDate=convert(char(7),c.DeclarationDate,120) where c.CompanyID=b.ID and c.DeclarationDate is not null and c.[Status] != 52 and c.IsDeleted = 0) OrderToTalMoney, "
                + " (select top 1 o.ManagerUser from orderInfo o where ((o.status != 52 and o.isdeleted = 0 and o.OrderWay = 2) or (o.status not in (1,52) and o.isdeleted = 0 and o.OrderWay = 1)) and o.companyId = b.id  order by orderdate asc, createtime asc ) as FirstOrderManagerUser ,  "
                + " (select top 1 mu.name from orderInfo o left join managerUser mu on o.managerUser = mu.ID where ((o.status != 52 and o.isdeleted = 0 and o.OrderWay = 2) or (o.status not in (1,52) and o.isdeleted = 0 and o.OrderWay = 1)) and o.companyId = b.id  order by o.orderdate asc, o.CreateTime asc ) as FirstOrderManagerUserName ,  "
                + " b.CustomerService as CustomerService,  "
                + " ( select name from ManagerUser where ManagerUser.ID=b.CustomerService ) as CustomerServiceName,   "
                + " (select max(t.ExportDate) from OrderInfo t where CompanyID = b.ID) as 'LastExportDate',  "
                + " b.CreateTime,   b.StopFlag  "
                + " FROM dbo.Company b  LEFT JOIN dbo.[OrderInfo] o on b.ID=o.CompanyID   "
                + " LEFT JOIN CompanyContract cc on cc.CompanyID = b.ID and cc.SignFlag = 1 "
                + " LEFT JOIN dbo.CompanyAddress ca on ca.CompanyID = b.ID and ca.AddressType='经营地址'  "
                + " LEFT JOIN dbo.DictionaryArea da1 ON da1.Code = ca.ProvinceID   "
                + " LEFT JOIN dbo.DictionaryArea da2 ON da2.Code = ca.CityID   "
                + " LEFT JOIN dbo.DictionaryCommon da3 ON b.IndustryType = da3.ID   "
                + " LEFT JOIN dbo.DictionaryCommon da4 ON b.ProductSourceType = da4.ID  "
                + " where b.StopFlag = 0 and b.SignFlag=1 ) at  where 1=1");
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And at.companyName like '%'+#{companyName,jdbcType=VARCHAR}+'%' ");
        }
        if (model.getCustomerService() != "" && model.getCustomerService() != null)
        {
            sql.append(" And at.CustomerService=#{customerService,jdbcType=VARCHAR} ");
        }
        if (model.getIndustryType() != 0)
        {
            sql.append(" And  at.IndustryType= #{industryType,jdbcType=INTEGER} ");
        }
        if (model.getProductSourceType() != 0)
        {
            sql.append(" And at.ProductSourceType = #{productSourceType,jdbcType=INTEGER} ");
        }
        if (model.getProvinceId() != 0)
        {
            sql.append(" And at.ProvinceID = #{provinceId,jdbcType=INTEGER} ");
        }
        if (model.getCityId() != 0)
        {
            sql.append(" And at.CityID = #{cityId,jdbcType=INTEGER} ");
        }
        if (model.getBeginDate() != null && model.getEndDate() != null)
        {
            if (model.getRangeDateType() == 0) // 不限
            {
                sql.append(
                        " And (at.SignTime between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR} or at.FirstExportDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}) ");
            }
            else if (model.getRangeDateType() == 1)
            {
                sql.append(" And at.SignTime between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR} ");
            }
            else if (model.getRangeDateType() == 2)
            {
                sql.append(
                        " And at.FirstExportDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR} ");
            }
        }
        if (null != model.getOrderCount() && !"".equals(model.getOrderCount()))
        {
            sql.append(" And at.OrderCount = #{orderCount} ");
        }
        if (model.getFirstOrderManagerUser() != null && model.getFirstOrderManagerUser() != "")
        {
            sql.append(" And at.FirstOrderManagerUser = #{firstOrderManagerUser,jdbcType=VARCHAR} ");
        }
        return sql.toString();
    }

    /* 委托方出口分析表导出 */
    public static String getExportReportSql(ConsignerExportQuery model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM ( " + " SELECT distinct b.ID as CompanyId,   b.Name as CompanyName, "
                + " ca.ProvinceID,    da1.Name as ProvinceName,   ca.CityID,   da2.Name as CityName,  "
                + " da1.Name+da2.Name as AreaInfo,   b.IndustryType ,   da3.Text as IndustryTypeName, "
                + " b.ProductSourceType ,   da4.Text as ProductSourceTypeName,  " + " cc.SignFlag,    "
                + " cc.SignCode,  " + " cc.SignTime,  "
                + " (select top 1 o.ExportDate from orderInfo o where ((o.status != 52 and o.isdeleted = 0 and o.OrderWay = 2) or (o.status not in (1,52) and o.isdeleted = 0 and o.OrderWay = 1)) and o.companyId = b.id  order by orderdate asc, createtime asc ) as FirstExportDate ,  "
                + " ( select count(c.ID) from [OrderInfo] c where c.CompanyID=b.ID and c.DeclarationDate is not null and c.[Status] != 52 and c.IsDeleted = 0) OrderCount,  "
                + " ( select sum(TotalForeignAmount* (case c.CurrencyID when 151 then 1.0000 else ISNULL(r.Rate,0)end)) from [OrderInfo] c left join MonthlyRates r on r.CurrencyId=c.CurrencyID and r.RateDate=convert(char(7),c.DeclarationDate,120) where c.CompanyID=b.ID and c.DeclarationDate is not null and c.[Status] != 52 and c.IsDeleted = 0) OrderToTalMoney, "
                + " (select top 1 o.ManagerUser from orderInfo o where ((o.status != 52 and o.isdeleted = 0 and o.OrderWay = 2) or (o.status not in (1,52) and o.isdeleted = 0 and o.OrderWay = 1)) and o.companyId = b.id  order by orderdate asc, createtime asc ) as FirstOrderManagerUser ,  "
                + " (select top 1 mu.name from orderInfo o left join managerUser mu on o.managerUser = mu.ID where ((o.status != 52 and o.isdeleted = 0 and o.OrderWay = 2) or (o.status not in (1,52) and o.isdeleted = 0 and o.OrderWay = 1)) and o.companyId = b.id  order by o.orderdate asc, o.CreateTime asc ) as FirstOrderManagerUserName ,  "
                + " b.CustomerService as CustomerService,  "
                + " ( select name from ManagerUser where ManagerUser.ID=b.CustomerService ) as CustomerServiceName,   "
                + " (select max(t.ExportDate) from OrderInfo t where CompanyID = b.ID) as 'LastExportDate',  "
                + " b.CreateTime,   b.StopFlag  "
                + " FROM dbo.Company b  LEFT JOIN dbo.[OrderInfo] o on b.ID=o.CompanyID   "
                + " LEFT JOIN CompanyContract cc on cc.CompanyID = b.ID and cc.SignFlag = 1 "
                + " LEFT JOIN dbo.CompanyAddress ca on ca.CompanyID = b.ID and ca.AddressType='经营地址'  "
                + " LEFT JOIN dbo.DictionaryArea da1 ON da1.Code = ca.ProvinceID   "
                + " LEFT JOIN dbo.DictionaryArea da2 ON da2.Code = ca.CityID   "
                + " LEFT JOIN dbo.DictionaryCommon da3 ON b.IndustryType = da3.ID   "
                + " LEFT JOIN dbo.DictionaryCommon da4 ON b.ProductSourceType = da4.ID  "
                + " where b.StopFlag = 0 and b.SignFlag=1 ) at  where 1=1");
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append(" And at.companyName like '%'+#{companyName,jdbcType=VARCHAR}+'%' ");
        }
        if (model.getCustomerService() != "" && model.getCustomerService() != null)
        {
            sql.append(" And at.CustomerService=#{customerService,jdbcType=VARCHAR} ");
        }
        if (model.getIndustryType() != 0)
        {
            sql.append(" And  at.IndustryType= #{industryType,jdbcType=INTEGER} ");
        }
        if (model.getProductSourceType() != 0)
        {
            sql.append(" And at.ProductSourceType = #{productSourceType,jdbcType=INTEGER} ");
        }
        if (model.getProvinceId() != 0)
        {
            sql.append(" And at.ProvinceID = #{provinceId,jdbcType=INTEGER} ");
        }
        if (model.getCityId() != 0)
        {
            sql.append(" And at.CityID = #{cityId,jdbcType=INTEGER} ");
        }
        if (model.getBeginDate() != null && model.getEndDate() != null)
        {
            if (model.getRangeDateType() == 0) // 不限
            {
                sql.append(
                        " And (at.SignTime between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR} or at.FirstExportDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}) ");
            }
            else if (model.getRangeDateType() == 1)
            {
                sql.append(" And at.SignTime between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR} ");
            }
            else if (model.getRangeDateType() == 2)
            {
                sql.append(
                        " And at.FirstExportDate between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR} ");
            }
        }
        if (null != model.getOrderCount() && !"".equals(model.getOrderCount()))
        {
            sql.append(" And at.OrderCount = #{orderCount} ");
        }
        if (model.getFirstOrderManagerUser() != null && model.getFirstOrderManagerUser() != "")
        {
            sql.append(" And at.FirstOrderManagerUser = #{firstOrderManagerUser,jdbcType=VARCHAR} ");
        }
        return sql.toString();
    }

    /**
     * 业务部门考核报表
     * 
     * @param model
     * @return
     */
    public static String selectDeptPerfByPage(DeptPerfPageableReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select o.id, "+
                      " o.code, "+
                      " o.companyid, "+
                      " o.companyname, "+
                      " o.customerservice as customerserviceid, "+
                      " m.name as customerservicename, "+
                      " case when ot.OrderFlag = 1 then m2.name else '' end as manageruser, "+
                      " o.exportdate, "+
                      " ot.orderFlag, "+
                      " totalforeignamount = case when o.currencyid = 318 then o.totalforeignamount when  o.currencyid = 151 then o.totalforeignamount/mr2.rate else o.totalforeignamount*mr.rate/mr2.rate end, "+
                      " planreceiptdate = case when m2.name is not null then case when t2.mingatherdate < o.exportdate then t2.mingatherdate else o.exportdate end else null end,o.declarationdate, "+
                      " clearanceforeignamount = o.ClearanceAmount/(case when not exists(select * from companyrates where companyid=o.companyid and showtype in (2301,2302) and isdeleted = 0) then (select a.rate from dailyrates a where a.isdelete = 0 and a.ratedate=convert(char(10),getdate(),120) and a.currencyid=318 and a.ratetype=2301) else (select a.rate from dailyrates a, companyrates b where a.isdelete = 0 and b.isdeleted = 0 and a.ratedate=convert(char(10),getdate(),120) and a.currencyid=318 and b.companyid=o.companyid and a.ratetype=b.showtype) end), "+
                      " o.isrefundservice,refundtexamount = a.taxrefund,o.clearancefinished ,rettaxserviceamount = a.taxrefundmoney  "+
                      " from orderinfo o  "+
                      " left join (select o.ID ,case when o.status = 52 or o.isdeleted = 1 then 2 when exists(select * from orderinfo where status != 52 and isdeleted = 0 and OrderWay = 2 and companyid = o.companyid) or exists(select * from orderinfo where status not in (1,52) and IsDeleted = 0 and OrderWay = 1) then (case o.orderdate + o.createtime when (select top 1 orderdate + createtime from orderinfo where companyid = o.companyid  and ((status != 52 and isdeleted = 0 and OrderWay = 2) or (status not in (1,52) and IsDeleted =0 and OrderWay = 1)) order by orderdate asc, createtime asc) then 1 else 0 end) END AS OrderFlag from orderinfo o) ot on ot.ID = o.ID "+
                      " left join manageruser m on m.id = o.customerservice and m.position='客服'  "+
                      " left join ManagerUser AS m2 ON m2.ID = o.ManagerUser "+
                      " left join monthlyrates mr on mr.currencyid = o.currencyid and mr.ratedate = convert(char(7), o.orderdate,120)  "+
                      " left join monthlyrates mr2 on mr2.currencyid = 318 and mr2.ratedate = convert(char(7), o.orderdate,120)  "+
                      " left join (select orderid,mingatherdate = min(gatherdate) from ( select orderid = o2.id,gatherdate = gor.UpdateTime from orderinfo o2 left join gatherorderrelevancedetial gord on gord.orderid = o2.id  "+
                      " left join gatherorderrelevance gor on gor.id = gord.gatherorderrelevanceid and gor.status =30 where o2.clearancefinished = 1 ) tmp group by orderid) t2 on t2.orderid = o.id  "+
                      " left join advancerefundapplyorderdetail ad  on ad.orderid = o.id left join advancerefundapply a on a.id = ad.applyid and a.status =30 "+ 
                " where o.status not in (1,52) and o.isdeleted = 0 ");
        if (model.getBeginExportDate() != null && model.getBeginExportDate() != "" && model.getEndExportDate() != null
                && model.getEndExportDate() != "")
        {
            sql.append(" AND o.[ExportDate] BETWEEN #{beginExportDate} AND #{endExportDate} ");
        }
        if (model.getBeginDeclarationDate() != null && model.getEndDeclarationDate() != null
                && model.getBeginDeclarationDate() != "" && model.getEndDeclarationDate() != "")
        {
            sql.append(" AND o.[DeclarationDate] BETWEEN #{beginDeclarationDate} AND #{endDeclarationDate} ");
        }
        return sql.toString();
    }

    /**
     * 业务部门导出报表
     * 
     * @param model
     * @return
     */
    public static String selectDeptPerf(DeptPerfQueryReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select o.id, "+
                " o.code, "+
                " o.companyid, "+
                " o.companyname, "+
                " o.customerservice as customerserviceid, "+
                " m.name as customerservicename, "+
                " case when ot.OrderFlag = 1 then m2.name else '' end as manageruser, "+
                " o.exportdate, "+
                " ot.orderFlag, "+
                " totalforeignamount = case when o.currencyid = 318 then o.totalforeignamount when  o.currencyid = 151 then o.totalforeignamount/mr2.rate else o.totalforeignamount*mr.rate/mr2.rate end, "+
                " planreceiptdate = case when m2.name is not null then case when t2.mingatherdate < o.exportdate then t2.mingatherdate else o.exportdate end else null end,o.declarationdate, "+
                " clearanceforeignamount = o.ClearanceAmount/(case when not exists(select * from companyrates where companyid=o.companyid and showtype in (2301,2302) and isdeleted = 0) then (select a.rate from dailyrates a where a.isdelete = 0 and a.ratedate=convert(char(10),getdate(),120) and a.currencyid=318 and a.ratetype=2301) else (select a.rate from dailyrates a, companyrates b where a.isdelete = 0 and b.isdeleted = 0 and a.ratedate=convert(char(10),getdate(),120) and a.currencyid=318 and b.companyid=o.companyid and a.ratetype=b.showtype) end), "+
                " o.isrefundservice,refundtexamount = a.taxrefund,o.clearancefinished ,rettaxserviceamount = a.taxrefundmoney  "+
                " from orderinfo o  "+
                " left join (select o.ID ,case when o.status = 52 or o.isdeleted = 1 then 2 when exists(select * from orderinfo where status != 52 and isdeleted = 0 and OrderWay = 2 and companyid = o.companyid) or exists(select * from orderinfo where status not in (1,52) and IsDeleted = 0 and OrderWay = 1) then (case o.orderdate + o.createtime when (select top 1 orderdate + createtime from orderinfo where companyid = o.companyid  and ((status != 52 and isdeleted = 0 and OrderWay = 2) or (status not in (1,52) and IsDeleted =0 and OrderWay = 1)) order by orderdate asc, createtime asc) then 1 else 0 end) END AS OrderFlag from orderinfo o) ot on ot.ID = o.ID "+
                " left join manageruser m on m.id = o.customerservice and m.position='客服'  "+
                " left join ManagerUser AS m2 ON m2.ID = o.ManagerUser "+
                " left join monthlyrates mr on mr.currencyid = o.currencyid and mr.ratedate = convert(char(7), o.orderdate,120)  "+
                " left join monthlyrates mr2 on mr2.currencyid = 318 and mr2.ratedate = convert(char(7), o.orderdate,120)  "+
                " left join (select orderid,mingatherdate = min(gatherdate) from ( select orderid = o2.id,gatherdate = gor.UpdateTime from orderinfo o2 left join gatherorderrelevancedetial gord on gord.orderid = o2.id  "+
                " left join gatherorderrelevance gor on gor.id = gord.gatherorderrelevanceid and gor.status =30 where o2.clearancefinished = 1 ) tmp group by orderid) t2 on t2.orderid = o.id  "+
                " left join advancerefundapplyorderdetail ad  on ad.orderid = o.id left join advancerefundapply a on a.id = ad.applyid and a.status =30 "+ 
          " where o.status not in (1,52) and o.isdeleted = 0 ");
        if (model.getBeginExportDate() != null && model.getBeginExportDate() != "" && model.getEndExportDate() != null
                && model.getEndExportDate() != "")
        {
            sql.append(" AND o.[ExportDate] BETWEEN #{beginExportDate} AND #{endExportDate} ");
        }
        if (model.getBeginDeclarationDate() != null && model.getEndDeclarationDate() != null
                && model.getBeginDeclarationDate() != "" && model.getEndDeclarationDate() != "")
        {
            sql.append(" AND o.[DeclarationDate] BETWEEN #{beginDeclarationDate} AND #{endDeclarationDate} ");
        }
        sql.append(" order by o.code desc ");
        return sql.toString();
    }

    /**
     * 活跃度报表分析
     * 
     * @param model
     * @return
     */
    public static String selectConsignerActive(ConsignerActiveQueryReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" with temp as   ( " + " select o.ID,  o.Code,o.CompanyID,o.CompanyName, "
                + " case when o.status = 52 or o.isdeleted = 1 " + " then 2 "
                + " when exists(select * from orderinfo where status != 52 and isdeleted = 0 and CompanyId = o.CompanyId) "
                + " then (case o.orderdate + o.createtime  "
                + " when (select top 1 orderdate + createtime from orderinfo where companyid = o.companyid and [status] != 52 and isdeleted = 0 order by orderdate asc, createtime asc) "
                + " then 1 " + " else 0 " + " end) " + " end as OrderFlag, " + " o.Status,o.IsDeleted, "
                + " o.OrderDate, o.CreateTime  " + " from OrderInfo o  " + " )  " + " select dateByMonth,  "
                + " (select count(0)   from(  " + " select companyId  " + " from temp "
                + " where orderFlag = 0 and datediff(month,OrderDate,dateByMonth)=0 " + " except  "
                + " select companyId from temp "
                + " where orderFlag = 1 and datediff(month,OrderDate,dateByMonth)=0 )t " + " ) reorderCustomer, "
                + " (select count(0)   from(  " + " select distinct companyId from temp "
                + " where orderFlag = 0 and datediff(month,OrderDate,dateByMonth)>=0 )t    ) cumulativeCustomer  "
                + " from(   select convert(date,dateadd(month,number,#{beginDate}),120) as dateByMonth  "
                + " from master..spt_values "
                + " where number<=datediff(month,#{beginDate},#{endDate}) and type='p') months ");
        return sql.toString();
    }

    /**
     * 返单率报表分析
     * 
     * @param model
     * @return
     */
    public static String selectConsignerReorder(ConsignerReorderQueryReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" with temp as   ( " + " select o.ID,  o.Code,o.CompanyID,o.CompanyName, "
                + " case when o.status = 52 or o.isdeleted = 1 " + " then 2 "
                + " when exists(select * from orderinfo where status != 52 and isdeleted = 0 and CompanyId = o.CompanyId) "
                + " then (case o.orderdate + o.createtime  "
                + " when (select top 1 orderdate + createtime from orderinfo where companyid = o.companyid and [status] != 52 and isdeleted = 0 order by orderdate asc, createtime asc) "
                + " then 1 " + " else 0 " + " end) " + " end as OrderFlag, " + " o.Status,o.IsDeleted, "
                + " o.OrderDate, o.CreateTime " + " from OrderInfo o  " + " )  " + " select dateByMonth,  "
                + " (select count(0)   from(  " + " select companyId  " + " from temp  "
                + " where orderFlag = 0 and datediff(month,OrderDate,dateByMonth)=0 )t  " + " ) reorderNumber, "
                + " (select count(0)   from(  " + " select distinct companyId from temp  "
                + " where orderFlag = 0 and datediff(month,OrderDate,dateByMonth)>=0 )t  " + " ) reorderCustomer  "
                + " from(   select convert(date,dateadd(month,number,#{beginDate}),120) as dateByMonth  "
                + " from master..spt_values   "
                + " where number<=datediff(month,#{beginDate},#{endDate}) and type='p'  " + " ) months ");
        return sql.toString();
    }

    /**
     * 订单明细分析报表
     * 
     * @param model
     * @return
     */
    public static String selectOrderDetailAnalysisByPage(OrderDetailAnalysisQueryReportPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM ( " + " SELECT   o.ID, " + " o.Code ,  " + " o.DeclarationNumber, "
                + " o.OurCompanytype,bb.body_name AS ourCompanyName, " + " mu1.id AS CustomerService, " + " mu1.Name AS CustomerServiceName, "
                + " CASE WHEN mu2.Department = '渠道中心' AND mu2.Name IS NOT NULL  THEN '渠道中心' WHEN mu2.Department != '渠道中心' AND mu2.Name IS NOT NULL THEN '直销中心' ELSE '' END AS Department , "
                + " mu2.ID AS managerUserId,mu2.Name AS managerUserName, "
                + " case when o.status = 52 or o.isdeleted = 1 then 2 when exists(select * from orderinfo where status != 52 and isdeleted = 0 and OrderWay = 2 and companyid = o.companyid) or  exists(select * from orderinfo where status not in (1,52) and IsDeleted = 0 and OrderWay = 1) then (case o.orderdate + o.createtime when (select top 1 orderdate + createtime from orderinfo where companyid = o.companyid  and ((status != 52 and isdeleted = 0 and OrderWay = 2) or (status not in (1,52) and IsDeleted =0 and OrderWay = 1)) order by orderdate asc, createtime asc) then 1 else 0 end) END AS OrderFlag, "
                + " o.CompanyID , " + " o.CompanyName, " + " ca.ProvinceID,  " + " da1.Name as ProvinceName,  "
                + " ca.CityID,  " + " da2.Name as CityName, " + " da1.Name+da2.Name as AreaInfo,  " + " o.CreateTime, "
                + " o.OrderDate , " + " o.OrderWay, " + " o.ExportDate , " + " o.DeclarationDate, "
                + " CASE WHEN o.LogisticsService != 1 THEN o.StartDate  ELSE (select le.LeaveTime from LogisticsExcute le left join OrderLogisticsMerge olm on olm.OrderLogisticsMergeID = le.OrderLogisticsMergeID inner join Orderlogisticsmergedetail od on le.Orderlogisticsmergeid=od.Orderlogisticsmergeid where od.OrderID = o.ID )END AS PackingLeaveDate,  "
                + " o.DeclareType, " + " o.DestinationPort,  " + " o.DestinationCountryID, "
                + " dc3.Name AS DestinationCountryName, " + " o.LogisticsService ,  "
                + " CASE WHEN EXISTS ( SELECT * FROM OrderAccountLog oal WHERE oal.OrderID = o.ID AND oal.CapitalType1 = '54F0BE44-9FA1-462A-B540-C43882C9934D' AND oal.CapitalType2 = '5B6FDEE0-4AC7-4C4B-9E75-361840D8020A' AND oal.IsDeleted = 0 ) THEN 1 ELSE 0 END AS HasEnsureMoney , "
                + " dc2.Text AS PaymentTerm , " + " o.IsSellCreditService , " + " o.CurrencyID , "
                + " dc1.Name AS CurrencyName , " + " o.TotalForeignAmount , "
                + " CASE o.CurrencyID WHEN 151 THEN 1.0000 ELSE ISNULL(( SELECT TOP 1 mr.rate FROM    MonthlyRates mr WHERE mr.RateDate = CONVERT(VARCHAR(7), o.DeclarationDate, 23) AND mr.CurrencyId = o.CurrencyID AND mr.IsDelete = 0 ), 0) END AS OrderRate , "
                + " CASE o.CurrencyID WHEN 151 THEN o.TotalForeignAmount ELSE ISNULL(( SELECT TOP 1 mr.rate FROM    MonthlyRates mr WHERE   mr.RateDate = CONVERT(VARCHAR(7), o.DeclarationDate, 23) AND mr.CurrencyId = o.CurrencyID AND mr.IsDelete = 0 ), 0) * o.TotalForeignAmount END AS OrderAmountRMB , "
                + " o.Status, " + " o.IsDeleted, " + " o.TradeTerm, " + " o.IsRefundService, "
                + " CASE o.IsRefundService WHEN 0 THEN NULL ELSE (SELECT top 1 CASE WHEN cr.ShowType=2303 THEN CONCAT('退税额*',cr.RateValue,'%') WHEN cr.ShowType=2304 THEN CONCAT('订单货款*',cr.RateValue*100,'%') WHEN cr.ShowType=2305 THEN CONCAT('订单货款*',cr.RateValue*100,'%') WHEN cr.ShowType=2306 THEN CONCAT('固定收取*',cr.RateValue*100,'%') ELSE '' END FROM CompanyRates cr WHERE cr.CompanyID = o.CompanyID AND cr.ShowType > 2302 AND cr.ShowType < 2307 AND cr.IsDeleted = 0) END AS 'RefundService', "
                + " CASE o.IsSellCreditService WHEN 1 THEN osc.Amount ELSE 0 END AS 'SellCreditAmountRMB',  "
                + "(select t.drawers from (select tb.OrderID,stuff((select ','+ Name from (select distinct op.OrderID,s.Name from OrderInfoProduct op left join Supplier s on op.SupplierID = s.ID where op.OrderID = o.ID) t where t.orderID=tb.orderId for xml path('')), 1, 1, '') as drawers from (select distinct op.OrderID,s.Name from OrderInfoProduct op left join Supplier s on op.SupplierID = s.ID where op.OrderID = o.ID) tb GROUP BY tb.OrderID) t) as drawers  "
                + " FROM OrderInfo AS o " 
                + " LEFT JOIN business_body bb on bb.body_id = o.OurCompanytype "
                + " LEFT JOIN ManagerUser AS mu1 ON mu1.ID = o.CustomerService  "
                + " LEFT JOIN ManagerUser AS mu2 ON mu2.ID = o.ManagerUser  "
                + " LEFT JOIN Company AS c ON c.ID = o.CompanyID "
                + " LEFT JOIN CompanyAddress AS ca on ca.CompanyID = c.ID and ca.AddressType='经营地址' "
                + " LEFT JOIN DictionaryArea AS da1 ON da1.Code = ca.ProvinceID and da1.StopFlag=0 "
                + " LEFT JOIN DictionaryArea AS da2 ON da2.Code = ca.CityID and da2.StopFlag=0  "
                + " LEFT JOIN DictionaryCurrency AS dc1 ON dc1.ID = o.CurrencyID and dc1.StopFlag=0  "
                + " LEFT JOIN DictionaryCommon AS dc2 ON dc2.ID = o.StatementType and dc2.StopFlag=0 "
                + " LEFT JOIN DictionaryCountry AS dc3 on dc3.ID=o.DestinationCountryID and dc3.StopFlag=0  "
                + " LEFT JOIN OrderSellCredit osc ON osc.OrderID = o.ID AND osc.IsDeleted = 0 ) t ");
        sql.append(" WHERE ( t.OrderWay=2 or (t.OrderWay = 1 AND t.Status != 1)) ");
        if (!"".equals(model.getSearchVal()) && null != model.getSearchVal())
        {
            sql.append(" AND (CHARINDEX(#{searchVal},t.[Code]) > 0 OR CHARINDEX(#{searchVal},t.[CompanyName]) > 0)");
        }
        if (!"".equals(model.getBeginDate()) && null != model.getBeginDate() && !"".equals(model.getEndDate())
                && null != model.getEndDate())
        {
            if (model.getRangeType() == 1)
            {
                sql.append(" AND t.[OrderDate] BETWEEN #{beginDate} AND #{endDate} ");
            }
            else if (model.getRangeType() == 2)
            {
                sql.append(" AND t.[ExportDate] BETWEEN #{beginDate} AND #{endDate} ");
            }
            else if (model.getRangeType() == 3)
            {
                sql.append(" AND t.[DeclarationDate] BETWEEN #{beginDate} AND #{endDate} ");
            }
            else
            {
                sql.append(
                        " AND (t.[OrderDate] BETWEEN #{beginDate} AND #{endDate} OR t.[ExportDate] BETWEEN #{beginDate} AND #{endDate} OR t.[OrderDate] BETWEEN #{beginDate} AND #{endDate}) ");
            }
        }
        // 佘销易服务
        if (model.getIsSellCreditService() != 2)
        {
            sql.append(" AND t.[IsSellCreditService] = #{isSellCreditService} ");
        }
        // 物流方式
        if (model.getLogisticsService() != 0)
        {
            sql.append(" AND t.[LogisticsService] = #{logisticsService} ");
        }
        // 报关方式
        if (model.getDeclareType() != 0)
        {
            sql.append(" AND t.[DeclareType] = #{declareType} ");
        }
        // 首单返单
        if (model.getOrderFlag() != 2)
        {
            sql.append(" AND t.[OrderFlag] = #{orderFlag} ");
        }
        // 客服人员
        if (model.getCustomerServiceId() != null && !"".equals(model.getCustomerServiceId()))
        {
            sql.append("AND t.[CustomerService] = #{customerServiceId} ");
        }
        // 外贸顾问
        if (model.getManagerUserId() != null && !"".equals(model.getManagerUserId()))
        {
            sql.append("AND t.[managerUserId] = #{managerUserId} ");
        }
        // 下单方式
        if (model.getOrderWay() != 0)
        {
            sql.append("AND t.[OrderWay] = #{orderWay} ");
        }
        // 所属部门
        if (model.getDepartment() != null && !"".equals(model.getDepartment()))
        {
            sql.append("AND t.[Department] = #{department} ");
        }
        // 状态
        if (model.getStatus() != 0)
        {
            sql.append("AND t.[Status] = #{status} ");
        }
        // 是否删除
        if (model.getIsDeleted() != 2)
        {
            sql.append("AND t.[IsDeleted] = #{isDeleted} ");
        }
        return sql.toString();
    }

    /**
     * 订单明细分析报表导出
     * 
     * @param model
     * @return
     */
    public static String selectOrderDetailAnalysis(OrderDetailAnalysisQueryReport model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM ( " + " SELECT   o.ID, " + " o.Code ,  " + " o.DeclarationNumber, "
                + " o.OurCompanytype,bb.body_name AS ourCompanyName, " + " mu1.id AS CustomerService, " + " mu1.Name AS CustomerServiceName, "
                + " CASE WHEN mu2.Department = '渠道中心' AND mu2.Name IS NOT NULL  THEN '渠道中心' WHEN mu2.Department != '渠道中心' AND mu2.Name IS NOT NULL THEN '直销中心' ELSE '' END AS Department , "
                + " mu2.ID AS managerUserId,mu2.Name AS managerUserName, "
                + " case when o.status = 52 or o.isdeleted = 1 then 2 when exists(select * from orderinfo where status != 52 and isdeleted = 0 and OrderWay = 2 and companyid = o.companyid) or  exists(select * from orderinfo where status not in (1,52) and IsDeleted = 0 and OrderWay = 1) then (case o.orderdate + o.createtime when (select top 1 orderdate + createtime from orderinfo where companyid = o.companyid  and ((status != 52 and isdeleted = 0 and OrderWay = 2) or (status not in (1,52) and IsDeleted =0 and OrderWay = 1)) order by orderdate asc, createtime asc) then 1 else 0 end) END AS OrderFlag, "
                + " o.CompanyID , " + " o.CompanyName, " + " ca.ProvinceID,  " + " da1.Name as ProvinceName,  "
                + " ca.CityID,  " + " da2.Name as CityName, " + " da1.Name+da2.Name as AreaInfo,  " + " o.CreateTime, "
                + " o.OrderDate , " + " o.OrderWay, " + " o.ExportDate , " + " o.DeclarationDate, "
                + " CASE WHEN o.LogisticsService != 1 THEN o.StartDate  ELSE (select le.LeaveTime from LogisticsExcute le left join OrderLogisticsMerge olm on olm.OrderLogisticsMergeID = le.OrderLogisticsMergeID inner join Orderlogisticsmergedetail od on le.Orderlogisticsmergeid=od.Orderlogisticsmergeid where od.OrderID = o.ID )END AS PackingLeaveDate,  "
                + " o.DeclareType, " + " o.DestinationPort,  " + " o.DestinationCountryID, "
                + " dc3.Name AS DestinationCountryName, " + " o.LogisticsService ,  "
                + " CASE WHEN EXISTS ( SELECT * FROM OrderAccountLog oal WHERE oal.OrderID = o.ID AND oal.CapitalType1 = '54F0BE44-9FA1-462A-B540-C43882C9934D' AND oal.CapitalType2 = '5B6FDEE0-4AC7-4C4B-9E75-361840D8020A' AND oal.IsDeleted = 0 ) THEN 1 ELSE 0 END AS HasEnsureMoney , "
                + " dc2.Text AS PaymentTerm , " + " o.IsSellCreditService , " + " o.CurrencyID , "
                + " dc1.Name AS CurrencyName , " + " o.TotalForeignAmount , "
                + " CASE o.CurrencyID WHEN 151 THEN 1.0000 ELSE ISNULL(( SELECT TOP 1 mr.rate FROM    MonthlyRates mr WHERE mr.RateDate = CONVERT(VARCHAR(7), o.DeclarationDate, 23) AND mr.CurrencyId = o.CurrencyID AND mr.IsDelete = 0 ), 0) END AS OrderRate , "
                + " CASE o.CurrencyID WHEN 151 THEN o.TotalForeignAmount ELSE ISNULL(( SELECT TOP 1 mr.rate FROM    MonthlyRates mr WHERE   mr.RateDate = CONVERT(VARCHAR(7), o.DeclarationDate, 23) AND mr.CurrencyId = o.CurrencyID AND mr.IsDelete = 0 ), 0) * o.TotalForeignAmount END AS OrderAmountRMB , "
                + " o.Status, " + " o.IsDeleted, " + " o.TradeTerm, " + " o.IsRefundService, "
                + " CASE o.IsRefundService WHEN 0 THEN NULL ELSE (SELECT top 1 CASE WHEN cr.ShowType=2303 THEN CONCAT('退税额*',cr.RateValue,'%') WHEN cr.ShowType=2304 THEN CONCAT('订单货款*',cr.RateValue*100,'%') WHEN cr.ShowType=2305 THEN CONCAT('订单货款*',cr.RateValue*100,'%') WHEN cr.ShowType=2306 THEN CONCAT('固定收取*',cr.RateValue*100,'%') ELSE '' END FROM CompanyRates cr WHERE cr.CompanyID = o.CompanyID AND cr.ShowType > 2302 AND cr.ShowType < 2307 AND cr.IsDeleted = 0) END AS 'RefundService', "
                + " CASE o.IsSellCreditService WHEN 1 THEN osc.Amount ELSE 0 END AS 'SellCreditAmountRMB',  "
                + "(select t.drawers from (select tb.OrderID,stuff((select ','+ Name from (select distinct op.OrderID,s.Name from OrderInfoProduct op left join Supplier s on op.SupplierID = s.ID where op.OrderID = o.ID) t where t.orderID=tb.orderId for xml path('')), 1, 1, '') as drawers from (select distinct op.OrderID,s.Name from OrderInfoProduct op left join Supplier s on op.SupplierID = s.ID where op.OrderID = o.ID) tb GROUP BY tb.OrderID) t) as drawers  "
                + " FROM OrderInfo AS o " 
                + " LEFT JOIN business_body bb on bb.body_id = o.OurCompanytype "
                + " LEFT JOIN ManagerUser AS mu1 ON mu1.ID = o.CustomerService  "
                + " LEFT JOIN ManagerUser AS mu2 ON mu2.ID = o.ManagerUser  "
                + " LEFT JOIN Company AS c ON c.ID = o.CompanyID "
                + " LEFT JOIN CompanyAddress AS ca on ca.CompanyID = c.ID and ca.AddressType='经营地址' "
                + " LEFT JOIN DictionaryArea AS da1 ON da1.Code = ca.ProvinceID and da1.StopFlag=0 "
                + " LEFT JOIN DictionaryArea AS da2 ON da2.Code = ca.CityID and da2.StopFlag=0  "
                + " LEFT JOIN DictionaryCurrency AS dc1 ON dc1.ID = o.CurrencyID and dc1.StopFlag=0  "
                + " LEFT JOIN DictionaryCommon AS dc2 ON dc2.ID = o.StatementType and dc2.StopFlag=0 "
                + " LEFT JOIN DictionaryCountry AS dc3 on dc3.ID=o.DestinationCountryID and dc3.StopFlag=0  "
                + " LEFT JOIN OrderSellCredit osc ON osc.OrderID = o.ID AND osc.IsDeleted = 0 ) t ");
        sql.append(" WHERE ( t.OrderWay=2 or (t.OrderWay = 1 AND t.Status != 1)) ");
        if (!"".equals(model.getSearchVal()) && null != model.getSearchVal())
        {
            sql.append(" AND (CHARINDEX(#{searchVal},t.[Code]) > 0 OR CHARINDEX(#{searchVal},t.[CompanyName]) > 0)");
        }
        if (!"".equals(model.getBeginDate()) && null != model.getBeginDate() && !"".equals(model.getEndDate())
                && null != model.getEndDate())
        {
            if (model.getRangeType() == 1)
            {
                sql.append(" AND t.[OrderDate] BETWEEN #{beginDate} AND #{endDate} ");
            }
            else if (model.getRangeType() == 2)
            {
                sql.append(" AND t.[ExportDate] BETWEEN #{beginDate} AND #{endDate} ");
            }
            else if (model.getRangeType() == 3)
            {
                sql.append(" AND t.[DeclarationDate] BETWEEN #{beginDate} AND #{endDate} ");
            }
            else
            {
                sql.append(
                        " AND (t.[OrderDate] BETWEEN #{beginDate} AND #{endDate} OR t.[ExportDate] BETWEEN #{beginDate} AND #{endDate} OR t.[OrderDate] BETWEEN #{beginDate} AND #{endDate}) ");
            }
        }
        // 佘销易服务
        if (model.getIsSellCreditService() != 2)
        {
            sql.append(" AND t.[IsSellCreditService] = #{isSellCreditService} ");
        }
        // 物流方式
        if (model.getLogisticsService() != 0)
        {
            sql.append(" AND t.[LogisticsService] = #{logisticsService} ");
        }
        // 报关方式
        if (model.getDeclareType() != 0)
        {
            sql.append(" AND t.[DeclareType] = #{declareType} ");
        }
        // 首单返单
        if (model.getOrderFlag() != 2)
        {
            sql.append(" AND t.[OrderFlag] = #{orderFlag} ");
        }
        // 客服人员
        if (model.getCustomerServiceId() != null && !"".equals(model.getCustomerServiceId()))
        {
            sql.append("AND t.[CustomerService] = #{customerServiceId} ");
        }
        // 外贸顾问
        if (model.getManagerUserId() != null && !"".equals(model.getManagerUserId()))
        {
            sql.append("AND t.[managerUserId] = #{managerUserId} ");
        }
        // 下单方式
        if (model.getOrderWay() != 0)
        {
            sql.append("AND t.[OrderWay] = #{orderWay} ");
        }
        // 所属部门
        if (model.getDepartment() != null && !"".equals(model.getDepartment()))
        {
            sql.append("AND t.[Department] = #{department} ");
        }
        // 状态
        if (model.getStatus() != 0)
        {
            sql.append("AND t.[Status] = #{status} ");
        }
        // 是否删除
        if (model.getIsDeleted() != 2)
        {
            sql.append("AND t.[IsDeleted] = #{isDeleted} ");
        }
        return sql.toString();
    }

}
