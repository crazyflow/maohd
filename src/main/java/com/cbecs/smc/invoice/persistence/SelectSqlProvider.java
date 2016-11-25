package com.cbecs.smc.invoice.persistence;

import com.cbecs.omc.order.model.ApiOrderInfoForInvoiceQueryByPage;
import com.cbecs.smc.invoice.model.ApiInvoice;
import com.cbecs.smc.invoice.model.ApiInvoiceApplicationQueryPageable;
import com.cbecs.smc.invoice.model.ManagementQueryPageable;
import com.cbecs.smc.invoice.model.ScanningQueryPageable;

public class SelectSqlProvider
{
    /**
     * 查询发票内的商品信息
     * 
     * @param invoiceId
     * @return
     */
    public static String selectInvoiceProductList(String invoiceId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select product_id AS productId, invoice_id AS invoiceId, prodcut_declare_name AS "
                + " productDeclareName, product_specification AS productSpecification, product_unit "
                + " AS productUnit, dcu.ChineseName AS productUnitName, product_quanity AS productQuanity, "
                + " product_price AS productPrice, product_total_amount AS productTotalAmount, "
                + " product_tax_rate AS productTaxRate, product_tax_amount AS productTaxAmount "
                + " from invoice_product left join DictionaryCommonUnit dcu ON dcu.ID = product_unit "
                + " where invoice_id=#{invoiceId}");

        return sql.toString();
    }

    /**
     * 查询单张发票信息
     * 
     * @param invoiceId
     * @return
     */
    public static String selectInvoice(String invoiceId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select i.invoice_id AS invoiceId, i.order_id AS orderId, i.order_code AS orderCode,"
                + " i.application_id AS applicationId, i.invoice_code AS invoiceCode, i.invoice_number AS "
                + " invoiceNumber, i.invoice_date AS invoiceDate, i.invoice_seller_name AS invoiceSellerName, "
                + " i.invoice_seller_tax_number AS invoiceSellerTaxNumber, i.invoice_seller_address AS "
                + " invoiceSellerAddress, i.invoice_buyer_name AS invoiceBuyerName, i.invoice_buyer_tax_number "
                + " AS invoiceBuyerTaxNumber, i.invoice_buyer_address AS invoiceBuyerAddress, "
                + " i.invoice_total_Amount AS invoiceTotalAmount, i.invoice_befortax_amount AS "
                + " invoiceBefortaxAmount, i.invoice_tax_amount AS invoiceTaxAmount, i.invoice_status "
                + " AS invoiceStatus from invoice i where i.invoice_id=#{invoiceId}");

        return sql.toString();
    }

    /**
     * 查询订单是否做过退税
     * 
     * @param orderId
     * @return
     */
    public static String selectRefundTaxCount(String orderId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(*) from RefundTax rt where rt.IsDeleted=0 and rt.OrderID=#{orderId}");

        return sql.toString();
    }

    /**
     * 查询订单是否做过退税垫付申请
     * 
     * @param orderId
     * @return
     */
    public static String selectAdvanceRefundApplyCount(String orderId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(*) from AdvanceRefundApply ara left join AdvanceRefundApplyOrderDetail araod "
                + " on ara.ID = araod.ApplyID where ara.IsDeleted=0 and ara.Status !=1 and araod.OrderID=#{orderId}");

        return sql.toString();
    }

    /**
     * 设置发票状态为红冲
     * 
     * @param invoiceId
     * @return
     */
    public static String updateInvoiceStatus(String invoiceId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" update invoice set invoice_status=6 where invoice_id=#{invoiceId}");

        return sql.toString();
    }

    /**
     * 设置订单发票状态为红冲
     * 
     * @param orderId
     * @return
     */
    public static String updateOrderInvoiceStatus(String orderId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" update orderinfo set IsPurchaseInvoiceFinished=2 where id=#{orderId}");

        return sql.toString();
    }

    /**
     * 查询订单下的开票人
     * 
     * @param orderId
     * @return
     */
    public static String selectSupplierList(String orderId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select oip.SupplierID AS userId, Supplier.Name AS userName from OrderInfoProduct oip "
                + " inner join Supplier on oip.SupplierID = Supplier.ID where oip.IsDeleted = 0 and "
                + " oip.OrderID = #{orderId} group by oip.SupplierID, Supplier.Name");

        return sql.toString();
    }

    /**
     * 查询订单发票列表
     * 
     * @param orderId
     * @param invoiceNumber
     * @param supplierId
     * @return
     */
    public static String selectInvoiceList(String orderId, String invoiceNumber, String supplierId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select i.invoice_id AS invoiceId, i.order_id AS orderId, i.order_code AS orderCode, "
                + " i.invoice_code AS invoiceCode, i.invoice_number AS invoiceNumber, i.invoice_date AS "
                + " invoiceDate, i.invoice_seller_name AS invoiceSellerName, i.invoice_seller_tax_number AS "
                + " invoiceSellerTaxNumber, i.invoice_befortax_amount AS invoiceBefortaxAmount, i.invoice_tax_amount "
                + " AS invoiceTaxAmount, i.invoice_status AS invoiceStatus from ( select * from invoice t where "
                + " t.deleted = 0 and t.invoice_red = 0 and (t.invoice_status = 4 OR t.invoice_status = 6) union all "
                + " select * from invoice t where t.deleted = 0 and t.invoice_red = 1 and t.invoice_status = 1) i "
                + " where i.order_id=#{0} ");
        if (!"".equals(invoiceNumber))
        {
            sql.append(" and CHARINDEX(#{1},i.invoice_number) > 0 ");
        }

        if (!"".equals(supplierId))
        {
            sql.append(" and i.invoice_seller_id = #{2} ");
        }
        
        sql.append(" order by i.invoice_date desc");

        return sql.toString();
    }

    /**
     * 查询订单发票概况
     * 
     * @param orderId
     * @return
     */
    public static String selectOrderInvoiceSummary(String orderId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                " select od.ID AS orderId, od.Code AS orderCode, Company.ID AS companyId, Company.Name AS companyName, "
                        + " (select count(*) from invoice where invoice.deleted = 0 and invoice.order_id = sc.order_id "
                        + " and ((invoice.invoice_red = 0 and (invoice.invoice_status = 4 or invoice.invoice_status = 6))"
                        + " or (invoice.invoice_red = 1 and invoice.invoice_status = 1))) AS invoiceCount, "
                        + " (select count(*) from invoice where invoice.invoice_red = 0 and invoice.order_id = sc.order_id "
                        + " and invoice.invoice_status = 4) AS invoiceComparePastCount, od.orderInvoiceBeforeTaxAmount, "
                        + " od.orderInvoiceTaxAmount, od.IsPurchaseInvoiceFinished AS status from (select scan.order_id "
                        + " from invoice_scanning scan where scan.application_status = 4 group by scan.order_id) sc "
                        + " INNER JOIN OrderInfo od ON sc.order_id = od.ID LEFT JOIN Company ON Company.ID = od.CompanyID "
                        + " where od.ID = #{orderId} ");

        return sql.toString();
    }

    /**
     * 查询红冲状态数量
     * 
     * @return
     */
    public static String selectRedStatusCount()
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(*) from (select scan.order_id from invoice_scanning scan where "
                + " scan.application_status = 4 group by scan.order_id) sc INNER JOIN OrderInfo od "
                + " ON sc.order_id = od.ID where od.IsPurchaseInvoiceFinished = 2");
        return sql.toString();
    }

    /**
     * 查询用户列表
     * 
     * @param type
     * @return
     */
    public static String selectCustomerServiceList(String typeName)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select Sys_RoleOfUser.UserGuid as userId, ManagerUser.Name as userName from Sys_Role "
                + "LEFT JOIN Sys_RoleOfUser ON Sys_Role.RoleID = Sys_RoleOfUser.RoleID "
                + "INNER JOIN ManagerUser ON ManagerUser.ID = Sys_RoleOfUser.UserGuid Where "
                + "ManagerUser.ActiveFlag=1 And ManagerUser.StopFlag=0 ");
        if (!"".equals(typeName))
        {
            sql.append(" And Sys_Role.RoleCode = #{status}");
        }

        return sql.toString();
    }

    /**
     * 查询发票管理列表
     * 
     * @param model
     * @return
     */
    public static String selectManagementList(ManagementQueryPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                " select sc.order_id AS orderId, od.Code AS orderCode, od.orderInvoiceTotalAmount, "
                        + "od.orderInvoiceBeforeTaxAmount, od.orderInvoiceTaxAmount, od.orderTransferDate, "
                        + "od.IsPurchaseInvoiceFinished AS status, od.CustomerService, mu.Name AS CustomerServiceName, "
                        + "( select count(*) from invoice where invoice.deleted = 0 and invoice.order_id = sc.order_id "
                        + " and ((invoice.invoice_red = 0 and (invoice.invoice_status = 4 or invoice.invoice_status = 6))"
                        + " or (invoice.invoice_red = 1 and invoice.invoice_status = 1))) AS invoiceCount, "
                        + "p.invoiceSellerNames, p.invoiceNumbers from (select scan.order_id from invoice_scanning scan "
                        + "where scan.application_status = 4 group by scan.order_id)sc inner join OrderInfo od on "
                        + "sc.order_id = od.ID left join ManagerUser mu on mu.ID = od.CustomerService left join ( "
                        + "select t.orderId, (select m.invoiceSellerName + '、' from (select invoice.order_id AS orderId, "
                        + "invoice.invoice_seller_name AS invoiceSellerName, invoice.invoice_number AS invoiceNumber from "
                        + "invoice where invoice.deleted = 0 and ((invoice.invoice_red = 0 and (invoice.invoice_status = 4 "
                        + " or invoice.invoice_status = 6)) or (invoice.invoice_red = 1 and invoice.invoice_status = 1)) "
                        + "group by invoice.order_id, invoice.invoice_seller_name, invoice.invoice_number)m where "
                        + "m.orderId = t.orderId for xml path ('') ) AS invoiceSellerNames, (select n.invoiceNumber + '、' "
                        + "from (select invoice.order_id AS orderId, invoice.invoice_seller_name AS invoiceSellerName, "
                        + "invoice.invoice_number AS invoiceNumber from invoice where invoice.deleted = 0 and (( " 
                        + "invoice.invoice_red = 0 and (invoice.invoice_status = 4 or invoice.invoice_status = 6)) or "
                        + "(invoice.invoice_red = 1 and invoice.invoice_status = 1)) group by invoice.order_id, "
                        + "invoice.invoice_seller_name, invoice.invoice_number) n where n.orderId = t.orderId for "
                        + "xml path ('')) AS invoiceNumbers from (select invoice.order_id AS orderId, invoice.invoice_seller_name "
                        + "AS invoiceSellerName, invoice.invoice_number AS invoiceNumber from invoice where invoice.deleted = 0 and (( " 
                        + "invoice.invoice_red = 0 and (invoice.invoice_status = 4 or invoice.invoice_status = 6)) or "
                        + "(invoice.invoice_red = 1 and invoice.invoice_status = 1)) group by invoice.order_id, "
                        + "invoice.invoice_seller_name, invoice.invoice_number) t group by t.orderId) p on p.orderId = sc.order_id "
                        + "where (od.IsPurchaseInvoiceFinished = 1 or od.IsPurchaseInvoiceFinished = 2)");
        if (null != model.getInvoiceNumber() && !"".equals(model.getInvoiceNumber()))
        {
            sql.append(" and CHARINDEX(#{invoiceNumber,jdbcType=VARCHAR},p.invoiceNumbers) > 0 ");
        }
        if (null != model.getInvoiceSellerName() && !"".equals(model.getInvoiceSellerName()))
        {
            sql.append(" and CHARINDEX(#{invoiceSellerName,jdbcType=VARCHAR},p.invoiceSellerNames) > 0 ");
        }
        if (null != model.getCustomerService() && !"".equals(model.getCustomerService()))
        {
            sql.append(" and od.CustomerService = #{customerService} ");
        }
        if (null != model.getOrderCode() && !"".equals(model.getOrderCode()))
        {
            sql.append(" and CHARINDEX(#{orderCode,jdbcType=VARCHAR},od.Code) > 0 ");
        }
        if (model.getStatus() != 0)
        {
            sql.append(" and od.IsPurchaseInvoiceFinished = #{status} ");
        }

        return sql.toString();
    }

    /**
     * 查询扫描申请列表
     * 
     * @param model
     * @return
     */
    public static String selectScannigList(ScanningQueryPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("  select ins.application_id, ins.application_code," + " ins.order_id, " + " ins.order_code, "
                + " ins.company_id, " + " ins.company_name, "
                + " (select t.suppliers from (select tb.OrderID,stuff((select ','+ Name from (select distinct op.OrderID,s.id,s.Name from OrderInfoProduct op left join Supplier s on op.SupplierID = s.ID where op.OrderID = o.ID) t where t.orderID=tb.orderId for xml path('')), 1, 1, '') as suppliers from (select distinct op.OrderID,s.Name from OrderInfoProduct op left join Supplier s on op.SupplierID = s.ID where op.OrderID = o.ID) tb GROUP BY tb.OrderID) t) as suppliers, "
                + " ins.regular_invoice_count,ins.red_invoice_count , " + " ins.application_date, "
                + " ins.application_status, " + " o.orderInvoiceCheckStatus, "
                + " ins.reject_ticket_reason,o.orderRefuseCollectInvoiceReason,ins.created_at "
                + " from invoice_scanning ins " + " left join orderinfo o on o.id = ins.order_id "
                + " where 1=1 and ins.deleted=0 ");
        sql.append(" and ins.created_by_id = #{customerService} ");
        if (null != model.getApplicationCode() && !"".equals(model.getApplicationCode()))
        {
            sql.append(" And CHARINDEX(#{applicationCode,jdbcType=VARCHAR},ins.application_code) > 0 ");
        }
        if (null != model.getOrderCode() && !"".equals(model.getOrderCode()))
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},ins.order_code) > 0 ");
        }
        if (model.getApplicationStatus() != 0)
        {
            sql.append(" And ins.application_status = #{applicationStatus} ");
        }
        if (model.getOrderInvoiceCheckStatus() != 0)
        {
            sql.append(" And o.OrderInvoiceCheckStatus = #{orderInvoiceCheckStatus} ");
        }
        return sql.toString();

    }

    /**
     * 获取申请单序号
     * 
     * @param preFix
     * @return
     */
    public static String getSequenceNo(String preFix)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "IF NOT EXISTS ( SELECT 1 FROM dbo.Sys_Sequence ss WHERE ss.PreFixString = #{preFix} AND CurrDate >= CONVERT(VARCHAR(10), GETDATE(), 20))")
                .append(" BEGIN").append(" IF EXISTS(SELECT 1").append(" FROM dbo.Sys_Sequence ss")
                .append(" WHERE ss.PreFixString = #{preFix})").append(" BEGIN").append(" UPDATE Sys_Sequence")
                .append(" SET CurrentValue = 0,").append(" CurrDate = GETDATE()")
                .append(" WHERE PreFixString = #{preFix};").append(" END").append(" ELSE BEGIN")
                .append(" INSERT INTO dbo.Sys_Sequence")
                .append(" (SequenceID, SequenceName, CurrentValue, StepValue, PreFixString, PostFixString, Remark, DateFormatStr, SeqFormatStr, CurrDate, IsCanDelete)")
                .append(" VALUES (NEWID(), N'', 0, 1, #{preFix}, N'', N'', N'', N'', GETDATE(), 0);").append(" END")
                .append(" END").append(" UPDATE Sys_Sequence SET CurrentValue+= 1")
                .append(" OUTPUT #{preFix} + CONVERT(VARCHAR(6), GETDATE(), 12) + REPLICATE('0', 4- LEN(Inserted.CurrentValue)) + CONVERT(VARCHAR(4), Inserted.CurrentValue)")
                .append(" WHERE PreFixString = #{preFix}");
        return sql.toString();
    }

    /**
     * 查询扫描申请列表
     * 
     * @param model
     * @return
     */
    public static String selectCheckDetailInvoice(String orderId, String invoiceNumber, String invoiceSellerId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "  select i.invoice_id,i.invoice_code,i.invoice_number,i.invoice_date,i.invoice_seller_id,i.invoice_seller_name,"
                + "i.invoice_seller_tax_number,i.invoice_total_Amount,i.invoice_tax_amount,i.invoice_status "
                        + " from invoice i " + " where i.order_id = #{0} ");
        if (null != invoiceNumber && !"".equals(invoiceNumber))
        {
            sql.append(" and i.invoice_number = #{1} ");
        }
        if (null != invoiceSellerId && !"".equals(invoiceSellerId))
        {
            sql.append(" and i.invoice_seller_id = #{2} ");
        }
        sql.append(" order by i.update_at desc ");
        return sql.toString();

    }

    // --------------发票api部分----------------
    /**
     * 查询所有的待扫描和待收票的申请单数据 --分页--钱伟
     * 
     * @param model
     * @return
     */
    public static String selectNoCompletedApplications(ApiInvoiceApplicationQueryPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select   ins.application_code , ins.order_code ,ins.order_id ,"
                + " ins.company_name , CONVERT(varchar(100), ins.application_date, 23) as application_date, ins.application_status , "
                + " ins.application_id ,ISNULL(ins.regular_invoice_count,0)+ISNULL(ins.red_invoice_count,0) as totalInvoiceNum, isnull(ins.scanned_ticket_count,0) as scannedNum,ce.sumBeforetaxAmount, "
                + "  (select sum(CONVERT(float,ci.TotalLowerCNY)) from ContractInvoice ci where ci.OrderID = ins.order_id ) as sumAggregateAmount,ord.orderInvoiceCheckStatus ,ManagerUser.Name     from   "
                + " dbo.invoice_scanning ins left join ( "
                + " select invoice.application_id,sum(invoice.invoice_total_Amount) as sumTotalAmount,sum(invoice.invoice_befortax_amount) as sumBeforeTaxAmount  "
                + " from invoice where invoice.deleted =0  and invoice_status <> 5 group by invoice.application_id  "
                + " )  ce on ins.application_id = ce.application_id    "
                + " inner join OrderInfo ord  on ord.ID =ins.order_id and ord.IsDeleted = 0   "
                + " left join ManagerUser on ManagerUser.ID = ord.CustomerService   where  ins.deleted = 0  ");
        if (null != model.getApplicationCode() && !"".equals(model.getApplicationCode()))
        {
            sql.append(" and CHARINDEX(#{applicationCode,jdbcType=VARCHAR}, ins.application_code) > 0 ");
        }
        if (null != model.getOrderCode() && !"".equals(model.getOrderCode()))
        {
            sql.append(" and CHARINDEX(#{orderCode,jdbcType=VARCHAR},ins.order_code) > 0 ");
        }
        if (null != model.getCustomer() && !"".equals(model.getCustomer()))
        {
            sql.append(" and ord.CustomerService = #{customer}");
        }
        if (null != model.getStartDate() && !"".equals(model.getStartDate()))
        {
            sql.append(" and ins.application_date > = #{startDate} ");
        }
        if (null != model.getEndDate() && !"".equals(model.getEndDate()))
        {
            sql.append(" and ins.application_date <= #{endDate} ");
        }
        if (model.getApplicationStatus() != -1)
        {
            sql.append(" And ins.application_status = #{applicationStatus} ");
        }
        if (model.getOrderInvoiceCheckStatus() != -1)
        {
            sql.append(" And ord.OrderInvoiceCheckStatus = #{orderInvoiceCheckStatus} ");
        }
        return sql.toString();

    }

    /**
     * 根据发票号码,开票人id,订单id 获取发票list-- 吴凡
     * 
     * @param model
     */
    public static String selectInvoicesByCodeAndSupplierAndOrderId(String orderId, String invoiceCode,
            String supplierId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT [invoice_id] 'invoiceId'   ,[order_id] 'orderId'   ,[order_code] 'orderCode' "
                + " ,[application_id] 'applicationId'   ,[invoice_code] 'invoiceCode'  "
                + " ,[invoice_number] 'invoiceNumber'   ,[invoice_date] 'invoiceDate'  "
                + " ,[invoice_seller_name] 'invoiceSellerName'   ,[invoice_seller_tax_number] 'invoiceSellerTaxNumber' "
                + " ,[invoice_seller_address] 'invoiceSellerAddress'   ,[invoice_buyer_name] 'invoiceBuyerName'  "
                + " ,[invoice_buyer_tax_number] 'invoiceBuyerTaxNumber'  "
                + " ,[invoice_buyer_address] 'invoiceBuyerAddress'   ,[invoice_total_Amount] 'invoiceTotalAmount'  "
                + " ,[invoice_befortax_amount] 'invoiceBefortaxAmount'   ,[invoice_tax_amount] 'invoiceTaxAmount' "
                + "  ,[invoice_status] 'invoiceStatus'   ,[invoice_red] 'invoiceRed'  "
                + " ,[created_by_id] 'createdById'   ,[created_at] 'createdAt'   ,[created_by] 'createdBy'  "
                + " ,[update_by_id] 'updateById'   ,[update_by] 'updateBy'   ,[update_at] 'updateAt'  "
                + " ,[deleted] 'deleted'   ,[invoice_check_fail] 'invoiceCheckFail'  "
                + " ,[invoice_seller_id]  'invoiceSellerId' FROM [invoice] where 1=1 ");
        if (null != orderId && !"".equals(orderId))
        {
            sql.append(" and [order_id] =#{0}  ");
        }
        if (null != invoiceCode && !"".equals(invoiceCode))
        {
            sql.append(" AND [invoice_number] LIKE '%' +#{1} + '%' ");
        }
        if (null != supplierId && !"".equals(supplierId))
        {
            sql.append(" And [invoice_seller_id] = #{2} ");
        }
        return sql.toString();

    }

    /**
     * 根据发票号码,开票人id,订单id 获取发票list-- 钱伟
     * 
     * @param model
     */
    public static String getReceivingAndCheckingOrders(ApiOrderInfoForInvoiceQueryByPage model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                " select OrderInfo.ID as orderId,orderInfo.Code as orderCode, CONVERT(varchar(100), orderinfo.orderTransferDate, 23)   orderTransferDate, orderinfo.orderInvoiceCheckStatus "
                        + " ,orderinfo.CustomerService, mu.Name as CustomerServiceName, orderinfo.orderInvoiceBeforeTaxAmount "
                        + " ,orderinfo.orderInvoiceTaxAmount,orderinfo.orderInvoiceTotalAmount,orderinfo.supplierID,su.Name as suplierName "
                        + " ,orderInfo.orderInvoiceTotalNum " + " from OrderInfo orderinfo"
                        + " left join ManagerUser mu on mu.ID = orderinfo.CustomerService "
                        + " left join Supplier su on su.ID = orderinfo.SupplierID "
                        + " where orderinfo.orderInvoiceCheckStatus in (10,30)  ");
        if (null != model.getOrderCode() && !"".equals(model.getOrderCode()))
        {
            sql.append(" and CHARINDEX(#{orderCode},orderinfo.code) > 0 ");
        }
        if (null != model.getCustomerName() && !"".equals(model.getCustomerName()))
        {
            sql.append(" and #{customerName} = orderinfo.CustomerService  ");
        }
        if (null != model.getSupplierName() && !"".equals(model.getSupplierName()))
        {
            sql.append(" and CHARINDEX(#{supplierName},su.Name) > 0 ");
        }
        if (null != model.getStartDate() && !"".equals(model.getStartDate()))
        {
            sql.append(" AND orderinfo.orderTransferDate >= #{startDate} ");
        }
        if (null != model.getEndDate() && !"".equals(model.getEndDate()))
        {
            sql.append(" and orderinfo.orderTransferDate < #{endDate} ");
        }
        if (model.getOrderInvoiceCheckStatus() != -1)
        {
            sql.append(" And orderinfo.OrderInvoiceCheckStatus = #{orderInvoiceCheckStatus} ");
        }
        return sql.toString();

    }

    /**
     * 查询所有比对不通过的发票--钱伟
     * 
     * @param model
     */
    public static String getInvoicesOfCheckingFail(String orderCode, String orderId, String invoiceCode,
            String supplier)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                " select inv.invoice_id invoiceId,inv.invoice_code invoiceCode,inv.invoice_number invoiceNumber,inv.invoice_date invoiceDate,inv.invoice_seller_name invoiceSellerName,inv.invoice_seller_tax_number invoiceSellerTaxNumber"
                        + " ,inv.invoice_befortax_amount invoiceBefortaxAmount,inv.invoice_tax_amount invoiceTaxAmount,inv.application_id applicationId,inv.order_id orderId,inv.order_code orderCode,mu.Name as custoumerService"
                        + " ,orderinfo.CustomerService  as customerId,inv1.sumBeforeTaxAmount,inv1.sumTaxAmount,inv.invoice_check_fail 'invoiceCheckFail' "
                        + " from invoice inv " + " inner join  OrderInfo orderinfo  on orderinfo.ID = inv.order_id "
                        + " inner join  ManagerUser mu on mu.ID = orderinfo.CustomerService "
                        + " left join (select invoice.order_id,sum(invoice.invoice_befortax_amount) as sumBeforeTaxAmount "
                        + "     ,sum(invoice.invoice_tax_amount) as sumTaxAmount from invoice "
                        + "      where invoice.invoice_status = 4  and invoice.deleted = 0 "
                        + "      group by invoice.order_id) inv1 on inv1.order_id = inv.order_id "
                        + " where inv.deleted = 0 and inv.invoice_status = 3 ");
        if (null != orderCode && !"".equals(orderCode))
        {
            sql.append(" and CHARINDEX(#{0},inv.order_code) > 0 ");
        }
        if (null != orderId && !"".equals(orderId))
        {
            sql.append(" and CHARINDEX(#{1},inv.order_id) > 0 ");
        }
        if (null != invoiceCode && !"".equals(invoiceCode))
        {
            sql.append(" and CHARINDEX(#{2}, inv.invoice_number) > 0 ");
        }
        if (null != supplier && !"".equals(supplier))
        {
            sql.append(" and #{3} = inv.invoice_seller_id ");
        }
        return sql.toString();

    }

    /**
     * 发票新增或编辑是判断是否满足条件--吴凡
     * 
     * @param model
     */
    public static String selectSameInvoiceNumber(ApiInvoice model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                " select * from invoice where invoice_code = #{invoiceCode} and  invoice_number = #{invoiceNumber} and deleted=0 and invoice_status != 5 ");
        if (null != model.getInvoiceId() && !"".equals(model.getInvoiceId()))
        {
            sql.append(" and invoice_id != #{invoiceId} ");
        }
        return sql.toString();

    }

    /**
     * 计算订单享有发票的总金额--钱伟
     * 
     * @param model
     */
    public static String selectTotalInvoiceAmountByOrderId(String orderId, String invoiceCode, String supplierId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select isnull(sum(invoice_tax_amount),0) as sumTaxAmount, "
                + " isnull(sum(invoice_total_Amount),0) as sumTotalAmount, "
                + " isnull(sum(invoice_befortax_amount),0) as sumBeforeTaxAmount "
                + " from invoice where invoice_status <> 5 and deleted = 0 ");
        if (null != orderId && !"".equals(orderId))
        {
            sql.append(" and order_id = #{0} ");
        }
        if (null != invoiceCode && !"".equals(invoiceCode))
        {
            sql.append(" and CHARINDEX(#{1}, invoice_number) > 0 ");
        }
        if (null != supplierId && !"".equals(supplierId))
        {
            sql.append(" and invoice_seller_id = #{2}  ");
        }
        return sql.toString();

    }

}
