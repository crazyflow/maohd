package com.cbecs.omc.order.persistence.inf;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cbecs.omc.order.model.ApiSimpleOrder;

public interface ApiOrderMapper
{
    /**
     * 查询订单的开票人信息
     * 
     * @param model
     * @return
     */
    @Select(" SELECT s.ID 'id',s.name 'name',s.TaxpayerIdentityNumber 'taxpayerIdentityNumber',(s.SupplierProvinceName+s.SupplierCityName+s.SupplierCountyName+s.SupplierAddress) 'supplierAddress' FROM[dbo].[Supplier] "
            + " AS s WHERE s.ID IN (SELECT oip.SupplierID FROM [dbo].[OrderInfoProduct] AS oip "
            + " WHERE oip.OrderID = #{value}) ")
    List<Map<String, Object>> selectOrderSupplierByOrderId(String orderId);

    /**
     * 购方信息
     * 
     * @param model
     * @return
     */
    @Select("   select bb.body_id 'bodyId',bb.body_name 'bodyName',bb.body_taxnumber 'bodyTaxnumber',bb.body_address 'bodyAddress' from business_body bb "
            + " left join OrderInfo o on o.OurCompanyType = bb.body_id where o.ID = #{value} ")
    Map<String, Object> selectOrderBusinessMainBodyByOrderId(String orderId);

    /**
     * 商品信息
     * 
     * @param model
     * @return
     */
    @Select("SELECT " + " oip.ID, " + " oip.DeclareName, " + " oip.SupplierID, " + " oip.OrderID, " + " (SELECT "
            + " opde.DeclareElementValue " + " FROM " + " OrderProductDeclareElement AS opde " + " WHERE "
            + " opde.CustomersProductID =oip.ProductID " + " AND opde.OrderProductID =oip.ID "
            + " AND opde.DictionaryDeclareElementID = '2135F007-047D-45C0-951D-8CE3E8E7978A' "
            + " ) AS DeclareElementValue, " + " dcu.ChineseName AS UnitName, " + " dcu.ID AS UnitID " + " FROM "
            + " [dbo].[OrderInfoProduct] AS oip "
            + " left JOIN ContractInvoiceProductInfo AS cipi ON cipi.OrderInfoProductID = oip.id "
            + " left JOIN [dbo].[DictionaryCommonUnit] AS dcu ON cipi.UnitID = dcu.ID " + " WHERE "
            + " oip.OrderID = #{value} ")
    List<Map<String, Object>> selectOrderProductByOrderId(String orderId);

    /**
     * 更改订单的发票是否收齐状态
     * 
     * @param model
     */
    @Update(" update orderinfo set IsPurchaseInvoiceFinished = #{isPurchaseInvoiceFinished},orderInvoiceCheckStatus = #{orderInvoiceCheckStatus} where id = #{orderId}")
    void updateOrderInvoiceStatusByOrderId(ApiSimpleOrder model);
}