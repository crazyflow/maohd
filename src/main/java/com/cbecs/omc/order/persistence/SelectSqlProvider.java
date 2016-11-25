package com.cbecs.omc.order.persistence;

import com.cbecs.omc.order.model.OrderInfoCommissionQueryByPage;
import com.cbecs.omc.order.model.OrderInfoForInvoiceCollectionQueryByPage;

public class SelectSqlProvider
{
    /**
     * 查询渠道商对应委托商的订单信息
     * 
     * @param model
     * @return
     */
    public static String selectOrderInfoByChannelByPage(OrderInfoCommissionQueryByPage model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT o.[ID] AS 'OrderId',  o.[Code] AS 'OrderCode', "
                + " o.[CompanyID],  o.[CompanyName],  o.[UpdateTime], " + " o.[OurCompanyType]  FROM [OrderInfo] o "
                + " WHERE  o.[ChannelCompanyID] = #{channelId}  AND o.[Status] IN (90,92) AND o.isdeleted = 0 ");
        // 新增
        if (model.getExceptOrder().length() != 0)
        {
            String[] exceptOrder = model.getExceptOrder().split(",");
            String temp = "(";
            for (int i = 0; i < exceptOrder.length; i++)
            {
                temp += "'" + exceptOrder[i] + "'" + ",";
            }
            temp = temp.substring(0, temp.length() - 1) + ")";
            sql.append(" AND o.[Code] NOT IN " + temp);
        }
        if (null == model.getApplicationId())
        {
            // 新增时查询
            sql.append(
                    " AND o.[ID] NOT IN (select csd.order_id from commision_settlement_detail csd left join commision_settlement_application csa on csa.application_id = csd.application_id where csa.status != 1) ");
        }
        else
        {
            // 编辑时查询
            sql.append(
                    " AND o.[ID] NOT IN (select csd.order_id from commision_settlement_detail csd left join commision_settlement_application csa on csa.application_id = csd.application_id where csa.status != 1 and csa.application_id != '"
                            + model.getApplicationId() + "') ");
        }
        // 订单完成时间
        if (null != model.getBeginDate() && !"".equals(model.getBeginDate()) && null != model.getEndDate()
                && !"".equals(model.getEndDate()))
        {
            sql.append(" AND o.[UpdateTime] BETWEEN #{beginDate} AND #{endDate} ");
        }
        // 订单号
        if (null != model.getOrderCode() && !"".equals(model.getOrderCode()))
        {
            sql.append(" AND CHARINDEX(#{orderCode},o.[Code]) > 0 ");
        }
        // 委托客户
        if (null != model.getCompanyName() && !"".equals(model.getCompanyName()))
        {
            sql.append(" AND CHARINDEX(#{companyName},o.[CompanyName]) > 0");
        }
        // 业务主体
        if (0 != model.getBusinessMainBody())
        {
            sql.append(" AND o.[OurCompanyType] = #{businessMainBody}");
        }
        return sql.toString();
    }

    /**
     * 查询订单啊信息--发票扫描
     * 
     * @param model
     * @return
     */
    public static String selectOrderInfoForInvoiceCollectionByPage(OrderInfoForInvoiceCollectionQueryByPage model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from (select o.id as orderId,o.code as orderCode,o.companyId,o.companyName, o.CustomerService,"
                + " (select t.suppliers from (select tb.OrderID,stuff((select ','+ Name+'|'+convert(varchar(38),ID) from (select distinct op.OrderID,s.id,s.Name from OrderInfoProduct op left join Supplier s on op.SupplierID = s.ID where op.OrderID = o.ID) t where t.orderID=tb.orderId for xml path('')), 1, 1, '') as suppliers from (select distinct op.OrderID,s.Name from OrderInfoProduct op left join Supplier s on op.SupplierID = s.ID where op.OrderID = o.ID) tb GROUP BY tb.OrderID) t) as suppliers , "
                + " o.TotalForeignAmount, o.CurrencyID, "
                + " (select dc.Name from DictionaryCurrency dc where dc.ID = o.CurrencyID and dc.StopFlag=0) as CurrencyName, "
                + " (select sum(convert(float,ci.TotalLowerCNY)) from ContractInvoice ci where ci.OrderID = o.id) as invoiceAmount, "
                + " case when (select count(*) from (select distinct op.OrderID,s.Name from OrderInfoProduct op left join Supplier s on op.SupplierID = s.ID where op.OrderID = o.id and s.name is not null) t) = (select count(osd.OrderID) from OrderSpecialDocument osd inner join Document d on osd.DocumentID = d.ID where osd.OrderID = o.ID and osd.Type = 3 and d.Source = 2) then 1 else 0 end as 'purchaseContract', "
                + " case when exists(select * from OrderSpecialDocument osd where osd.OrderID = o.id and osd.type=1)  then 1 else 0 end as 'delaration' "
                + " from OrderInfo o " + " where o.Status not in (52,90,92) " + " and IsDeleted = 0 "
                + " and ((o.IsPurchaseInvoiceFinished = 0 or o.IsPurchaseInvoiceFinished is null) and not exists(select isn.* from invoice_scanning isn where isn.order_id = o.ID and isn.application_status in (1,2,3)  and isn.deleted = 0) or o.IsPurchaseInvoiceFinished = 2)  )  t where 1=1 ");
        sql.append(" and t.customerService = #{customerService}");
        if (null != model.getExceptOrder() && !"".equals(model.getExceptOrder()))
        {
            String[] exceptOrder = model.getExceptOrder().split(",");
            String temp = "(";
            for (int i = 0; i < exceptOrder.length; i++)
            {
                temp += "'" + exceptOrder[i] + "'" + ",";
            }
            temp = temp.substring(0, temp.length() - 1) + ")";
            sql.append(" and t.orderId NOT IN " + temp);
        }
        if (null != model.getOrderCode() && !"".equals(model.getOrderCode()))
        {
            sql.append(" and CHARINDEX(#{orderCode,jdbcType=VARCHAR},t.orderCode) > 0 ");
        }
        if (null != model.getSuppliers() && !"".equals(model.getSuppliers()))
        {
            sql.append(" and CHARINDEX(#{suppliers,jdbcType=VARCHAR},t.suppliers) > 0 ");
        }
        return sql.toString();
    }

}
