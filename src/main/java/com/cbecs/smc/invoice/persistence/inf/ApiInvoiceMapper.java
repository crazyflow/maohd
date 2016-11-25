package com.cbecs.smc.invoice.persistence.inf;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.cbecs.omc.order.model.ApiOrderInfoForInvoiceQueryByPage;
import com.cbecs.smc.invoice.model.ApiInvoice;
import com.cbecs.smc.invoice.model.ApiInvoiceApplicationQueryPageable;
import com.cbecs.smc.invoice.model.ApiInvoiceProduct;
import com.cbecs.smc.invoice.model.ApiInvoiceScanning;
import com.cbecs.smc.invoice.model.ApiInvoiceSupplier;
import com.cbecs.smc.invoice.model.ApiOrder;
import com.cbecs.smc.invoice.model.ApiOrderSupplier;
import com.cbecs.smc.invoice.model.ApiOrderSupplierProduct;
import com.cbecs.smc.invoice.model.ApiSimpleInvoice;
import com.cbecs.smc.invoice.persistence.SelectSqlProvider;

public interface ApiInvoiceMapper
{
    /**
     * 查询订单的所有未比对的发票 --钱伟
     * 
     * @param orderId
     * @return
     */
    @Select(" select [invoice_id] 'invoiceId' " + " ,[order_id] 'orderId' " + " ,[order_code] 'orderCode' "
            + " ,[application_id] 'applicationId' " + " ,[invoice_code] 'invoiceCode' "
            + " ,[invoice_number] 'invoiceNumber' " + " ,[invoice_date] 'invoiceDate' "
            + " ,[invoice_seller_name] 'invoiceSellerName' " + " ,[invoice_seller_tax_number] 'invoiceSellerTaxNumber' "
            + " ,[invoice_seller_address] 'invoiceSellerAddress' " + " ,[invoice_buyer_name] 'invoiceBuyerName' "
            + " ,[invoice_buyer_tax_number] 'invoiceBuyerTaxNumber' "
            + " ,[invoice_buyer_address] 'invoiceBuyerAddress' " + " ,[invoice_total_Amount] 'invoiceTotalAmount' "
            + " ,[invoice_befortax_amount] 'invoiceBefortaxAmount' " + " ,[invoice_tax_amount] 'invoiceTaxAmount' "
            + " ,[invoice_status] 'invoiceStatus' " + " ,[invoice_red] 'invoiceRed' "
            + " ,[created_by_id] 'createdById' " + " ,[created_at] 'createdAt' " + " ,[created_by] 'createdBy' "
            + " ,[update_by_id] 'updateById' " + " ,[update_by] 'updateBy' " + " ,[update_at] 'updateAt' "
            + " ,[deleted] 'deleted' " + " ,[invoice_check_fail] 'invoiceCheckFail' "
            + " ,[invoice_seller_id]  'invoiceSellerId' " + " from invoice "
            + " inner join OrderInfo on invoice.order_id = OrderInfo.ID and OrderInfo.IsDeleted = 0 "
            + " where OrderInfo.orderInvoiceCheckStatus  = 30 and invoice.deleted = 0 and invoice.invoice_status in(1,3) and invoice.invoice_red = 0  and OrderInfo.id = #{value}")
    List<Map<String, Object>> selectNoComparisonInvoicesByOrderId(String orderId);

    /**
     * 查询所有的待扫描和待收票的申请单数据 --钱伟
     * 
     * @param model
     */
    @SelectProvider(method = "selectNoCompletedApplications", type = SelectSqlProvider.class)
    @Results(value =
    { @Result(id = true, property = "applicationId", column = "application_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "applicationCode", column = "application_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderCode", column = "order_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderId", column = "order_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "companyName", column = "company_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "companyId", column = "company_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "applicationDate", column = "application_date", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "applicationStatus", column = "application_status", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "scannedNum", column = "scannedNum", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "sumTotalAmount", column = "sumTotalAmount", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "sumBeforeTaxAmount", column = "sumBeforeTaxAmount", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "sumAggregateAmount", column = "sumAggregateAmount", javaType = Double.class, jdbcType = JdbcType.DOUBLE),
            @Result(property = "orderInvoiceCheckStatus", column = "orderInvoiceCheckStatus", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "totalInvoicenum", column = "totalinvoicenum", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    List<Map<String, Object>> selectNoCompletedApplicationsByPageable(ApiInvoiceApplicationQueryPageable model);

    /**
     * 获取发票申请单待收票的数量 --钱伟
     * 
     * @param model
     */
    @Select(" select count(*) from invoice_scanning where application_status  = 1 and deleted = 0 ")
    int getInvoiceApplicationStatusForGetTicket();

    /**
     * 获取发票申请单待扫描的数量 --钱伟
     * 
     * @param model
     */
    @Select(" select count(*) from invoice_scanning where application_status  = 2  and deleted = 0 ")
    int getInvoiceApplicationStatusForScanning();

    /**
     * 根据发票号码,开票人id,订单id 获取发票list-- 吴凡
     * 
     * @param model
     */
    @SelectProvider(method = "selectInvoicesByCodeAndSupplierAndOrderId", type = SelectSqlProvider.class)
    List<Map<String, Object>> selectInvoicesByCodeAndSupplierAndOrderId(String orderId, String invoiceCode,
            String supplierId);

    /**
     * 根据发票id获取发票信息-- 吴凡
     * 
     * @param model
     */
    @Select(" SELECT i.[invoice_id] 'invoiceId',i.[order_id] 'orderId',i.[order_code] 'orderCode' "
            + " ,i.[application_id] 'applicationId' " + " ,i.[invoice_code] 'invoiceCode' "
            + " ,i.[invoice_number] 'invoiceNumber' " + " ,i.[invoice_date] 'invoiceDate' "
            + " ,i.[invoice_seller_name] 'invoiceSellerName' "
            + " ,i.[invoice_seller_tax_number] 'invoiceSellerTaxNumber' "
            + " ,i.[invoice_seller_address] 'invoiceSellerAddress' " + " ,i.[invoice_buyer_name] 'invoiceBuyerName' "
            + " ,i.[invoice_buyer_tax_number] 'invoiceBuyerTaxNumber' "
            + " ,i.[invoice_buyer_address] 'invoiceBuyerAddress' " + " ,i.[invoice_total_Amount] 'invoiceTotalAmount' "
            + " ,i.[invoice_befortax_amount] 'invoiceBefortaxAmount' " + " ,i.[invoice_tax_amount] 'invoiceTaxAmount' "
            + " ,i.[invoice_status] 'invoiceStatus' " + " ,i.[invoice_red] 'invoiceRed' "
            + " ,i.[created_by_id] 'createdById' " + " ,i.[created_at] 'createdAt' " + " ,i.[created_by] 'createdBy' "
            + " ,i.[invoice_check_fail] 'invoiceCheckFail' " + " ,i.[invoice_seller_id] 'invoiceSellerId' "
            + "  FROM invoice AS i where i.invoice_id = #{value} ")
    ApiInvoice selectInvoicesByInvoiceId(String invoiceId);

    /**
     * 根据发票id获取商品信息-- 吴凡
     * 
     * @param model
     */
    @Select(" select ip.[product_id] 'productId' ,ip.[invoice_id] 'invoiceId' ,ip.[prodcut_declare_name] 'prodcutDeclareName' "
            + " ,ip.[product_specification] 'productSpecification'   ,ip.[product_unit] 'productUnit', dcu.ChineseName 'unitName' "
            + " ,ip.[product_quanity] 'productQuanity'   ,ip.[product_price] 'productPrice' "
            + " ,ip.[product_total_amount] 'productTotalAmount'   ,ip.[product_tax_rate] 'productTaxRate' "
            + " ,ip.[product_tax_amount] 'productTaxAmount' from invoice_product ip inner join DictionaryCommonUnit dcu on ip.product_unit = dcu.ID where ip.invoice_id =#{value} ")
    List<ApiInvoiceProduct> selectInvoiceProductByInvoiceId(String invoiceId);

    /**
     * 查询当前客服--钱伟
     * 
     * @param model
     */
    @Select(" select c.ID 'id',c.Name 'name' from Sys_Role a,Sys_RoleOfUser b,ManagerUser c where a.RoleID = b.RoleID and a.RoleID = '8bc51d64-168f-474b-ad00-e54ff5dc6158' and c.ID = b.UserGuid and c.activeflag=1 and c.stopflag=0  Order by c.Name asc  ")
    List<Map<String, Object>> selectCustomers();

    /**
     * 获取订单的收票情况--钱伟
     * 
     * @param orderId
     * @return
     */
    @Select(" select " + " oip.ID, " + " ord.CompanyName companyName, " + " ord.CompanyID companyId, "
            + " ord.Code as orderCode, " + " isnull(cip.UnitPrice,0) unitPrice, "
            + " isnull(cip.UnitCount,0) as unitCount, " + " isnull(dicu.ChineseName,'--') as chineseName, "
            + " oip.SupplierID supplierId, " + " isnull(invoice1.invoiceTotalNum,0) as invoiceTotalNum, "
            + " isnull(invoice2.invoiceSupplierNum,0)as invoiceSupplierNum, "
            + " isnull(cip.AggregateAmount,0) aggregateAmount, " + " sup.Name as supplierName, "
            + " csp.DeclareName declareName, " + " csp.Specification specification" + " from  "
            + " OrderInfoProduct oip " + " left join ContractInvoiceProductInfo cip on cip.OrderInfoProductID = oip.ID "
            + " left join CustomersProduct csp on csp.ID = oip.ProductID "
            + " left join DictionaryCommonUnit dicu on dicu.ID = cip.UnitID "
            + " left join OrderInfo  ord on ord.ID = oip.OrderID "
            + " left join Supplier sup on sup.ID = oip.SupplierID " + " left join ( "
            + " select order_id, count(1) as  invoiceTotalNum " + " from invoice  where deleted = 0   "
            + " group by order_id  " + " )   invoice1 on invoice1.order_id = oip.OrderID " + " left join ( "
            + " select order_id,invoice_seller_id, count(1) as  invoiceSupplierNum "
            + " from invoice where deleted = 0   " + " group by order_id, invoice_seller_id "
            + " )   invoice2 on invoice2.order_id = oip.OrderID and invoice2.invoice_seller_id = oip.SupplierID "
            + " where oip.OrderID = #{value} ")
    List<Map<String, Object>> selectOrderReceiptInvoiceStatusByOrderId(String orderId);

    /**
     * 获取登录账户信息--潘明星
     * 
     * @param accountNumber
     *            账号
     * @param password
     *            密码
     * @return
     */
    @Select("  select ID,Account,Name,Department,Position from ManagerUser "
            + " where Account=#{0} and [Password] = #{1} and ActiveFlag=1 and StopFlag=0 ")
    Map<String, Object> getManagerUser(String accountNumber, String password);

    /**
     * 查询比对待收票和待比对的订单-钱伟
     * 
     * @param map
     * @return
     */
    @SelectProvider(method = "getReceivingAndCheckingOrders", type = SelectSqlProvider.class)
    @Results(value =
    { @Result(id = true, property = "orderId", column = "orderid", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderCode", column = "ordercode", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderTransferDate", column = "ordertransferdate", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderInvoiceCheckStatus", column = "orderinvoicecheckstatus", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "customerService", column = "customerservice", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "customerServiceName", column = "customerservicename", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderInvoiceBeforeTaxAmount", column = "orderinvoicebeforetaxamount", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderInvoiceTaxAmount", column = "orderinvoicetaxamount", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderInvoiceTotalAmount", column = "orderinvoicetotalamount", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "supplierId", column = "supplierid", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "suplierName", column = "supliername", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "orderInvoiceTotalNum", column = "orderinvoicetotalnum", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    List<Map<String, Object>> getReceivingAndCheckingOrders(ApiOrderInfoForInvoiceQueryByPage model);

    /**
     * 查询比对待收票订单的数目--钱伟
     * 
     * @return
     */
    @Select("select count(1) as toCollectNum from OrderInfo where orderInvoiceCheckStatus = 10 and IsDeleted = 0")
    Map<String, Object> getReceivingOrdersForInvoiceCheckCount();

    /**
     * 查询待比对订单的数目--钱伟
     * 
     * @return
     */
    @Select("select count(1) as toCheckNum from OrderInfo where orderInvoiceCheckStatus = 30 and IsDeleted = 0")
    Map<String, Object> getCheckingOrdersForInvoiceCheckCount();

    /**
     * 查询订单下所有的开票人--钱伟
     * 
     * @param orderId
     * @return
     */
    @Select(" select  oip.SupplierID, sup.Name from OrderInfoProduct oip "
            + " inner join Supplier sup on oip.SupplierID = sup.ID "
            + " where oip.OrderID = #{0} group by oip.SupplierID,sup.Name")
    List<Map<String, Object>> getSuppliersFromOrderByOrderId(String orderId);

    /**
     * 查询所有比对不通过的发票--钱伟
     * 
     * @return
     */
    @SelectProvider(method = "getInvoicesOfCheckingFail", type = SelectSqlProvider.class)
    List<Map<String, Object>> getInvoicesOfCheckingFail(String orderCode, String orderId, String invoiceCode,
            String supplier);

    /**
     * 新增或编辑是判断是否满足条件，不满足条件，返回发票信息--吴凡
     * 
     * @param model
     */
    @SelectProvider(method = "selectSameInvoiceNumber", type = SelectSqlProvider.class)
    ApiInvoice selectSameInvoiceNumber(ApiInvoice model);

    /**
     * 新增发票--吴凡
     * 
     * @param model
     */
    @Insert(" INSERT INTO [dbo].[invoice] ([invoice_id],[order_id],[order_code],[application_id],[invoice_code],[invoice_number],[invoice_date] "
            + " ,[invoice_seller_name],[invoice_seller_tax_number],[invoice_seller_address],[invoice_buyer_name],[invoice_buyer_tax_number] "
            + " ,[invoice_buyer_address],[invoice_total_Amount],[invoice_befortax_amount],[invoice_tax_amount] "
            + " ,[invoice_status],[invoice_red],[created_by_id],[created_at],[created_by],[deleted],[invoice_seller_id]) "
            + " VALUES (#{invoiceId},#{orderId},#{orderCode},#{applicationId},#{invoiceCode},#{invoiceNumber},#{invoiceDate}"
            + " ,#{invoiceSellerName},#{invoiceSellerTaxNumber},#{invoiceSellerAddress},#{invoiceBuyerName}"
            + " ,#{invoiceBuyerTaxNumber},#{invoiceBuyerAddress},#{invoiceTotalAmount},#{invoiceBefortaxAmount}"
            + " ,#{invoiceTaxAmount},#{invoiceStatus},#{invoiceRed},#{createdById}"
            + " ,#{createdAt},#{createdBy},#{deleted},#{invoiceSellerId} )")
    int insertInvoice(ApiInvoice model);

    /**
     * 更改申请单已扫张数--吴凡
     * 
     * @param model
     */
    @Update(" update invoice_scanning set scanned_ticket_count = scanned_ticket_count+1 where application_id = #{applicationId} ")
    int updateInvoiceScanningScannedTicketCounts(ApiInvoiceScanning model);

    /**
     * 更改发票--吴凡
     * 
     * @param model
     */
    @Update(" update invoice set invoice_code =#{invoiceCode},invoice_number =#{invoiceNumber},invoice_date =#{invoiceDate}, "
            + " invoice_seller_name =#{invoiceSellerName},invoice_seller_tax_number =#{invoiceSellerTaxNumber},invoice_seller_address =#{invoiceSellerAddress}, "
            + " invoice_buyer_name =#{invoiceBuyerName},invoice_buyer_tax_number =#{invoiceBuyerTaxNumber},invoice_buyer_address =#{invoiceBuyerAddress}, "
            + " invoice_total_amount =#{invoiceTotalAmount},invoice_befortax_amount =#{invoiceBefortaxAmount},invoice_tax_amount =#{invoiceTaxAmount},invoice_status = #{invoiceStatus}, "
            + " update_by_id =#{updateById},update_by =#{updateBy},update_at =#{updateAt},invoice_seller_id=#{invoiceSellerId} where invoice_id=#{invoiceId} ")
    int updateInvoice(ApiInvoice model);
    
    /**
     * 更改申请单的退票数量和已扫数量--吴凡
     * 
     * @param model
     */
    @Update(" update invoice_scanning set scanned_ticket_count = scanned_ticket_count + #{scannedTicketCount},"
            + " refund_ticket_count = refund_ticket_count - #{refundTicketCount}"
            + " where application_id = #{applicationId} ")
    int updateInvoiceScanningCount(ApiInvoiceScanning model);

    /**
     * 删除发票商品--吴凡
     * 
     * @param model
     */
    @Delete(" delete invoice_product where invoice_id = #{invoiceId} ")
    int deleteInvoiceProduct(ApiInvoiceProduct model);

    /**
     * 新增发票商品--吴凡
     * 
     * @param model
     */
    @Insert(" INSERT INTO [dbo].[invoice_product] ([product_id] ,[invoice_id],[prodcut_declare_name],[product_specification],[product_unit],[product_quanity],[product_price] ,[product_total_amount],[product_tax_rate],[product_tax_amount]) "
            + " VALUES  (#{productId},#{invoiceId},#{prodcutDeclareName},#{productSpecification},#{productUnit},#{productQuanity} ,#{productPrice},#{productTotalAmount},#{productTaxRate},#{productTaxAmount}) ")
    int insertInvoiceProduct(ApiInvoiceProduct model);

    /**
     * 更改发票状态 --钱伟
     * 
     * @param model
     */
    @Update(" update invoice set invoice_status = #{invoiceStatus},invoice_check_fail = #{invoiceCheckFail} where invoice_code = #{invoiceCode} and  invoice_number = #{invoiceNumber} and invoice_status != 5 ")
    void updateInvoiceStatusByCode(ApiSimpleInvoice model);

    /**
     * 更改申请单状态为待扫描
     * 
     * @param model
     */
    @Update(" update invoice_scanning set application_status = #{applicationStatus} where order_id = #{orderId}")
    void updateInvoiceScanningStatusByOrderId(ApiInvoiceScanning model);

    /**
     * 更改发票扫描申请单状态--钱伟
     * 
     * @param applicationId
     * @return
     */
    @Update(" update invoice_scanning set application_status = 2 ,updated_at = #{updatedAt},updated_by_id = #{updatedById},updated_by=#{updatedBy}   where  application_id = #{applicationId}")
    int updateInvoiceScanningApplicationStatusById(ApiInvoiceScanning model);

    /**
     * 更改发票扫描申请单状态及原因--钱伟
     * 
     * @param applicationId
     * @param reason
     * @return
     */
    @Update(" update invoice_scanning set application_status = 3, reject_ticket_reason = #{rejectTicketReason} ,updated_at = #{updatedAt},updated_by_id = #{updatedById},updated_by=#{updatedBy} where  application_id = #{applicationId}")
    int updateInvoiceScanningApplicationStatusAndReasonById(ApiInvoiceScanning model);

    /**
     * 更改订单发票比对状态之比对收票--钱伟
     * 
     * @param orderId
     * @return
     */
    @Update(" update OrderInfo set orderInvoiceCheckStatus = 30 where ID =#{orderId} ")
    int updateOrderInvoiceCheckStatusByOrderId(ApiOrder model);

    /**
     * 更改订单发票比对状态之比对拒收票--钱伟
     * 
     * @param orderId
     * @param reason
     * @return
     */
    @Update(" update OrderInfo set orderInvoiceCheckStatus = 20, orderRefuseCollectInvoiceReason = #{orderRefuseCollectInvoiceReason} where ID =#{orderId} ")
    int updateOrderInvoiceCheckStatusAndReasonByOrderId(ApiOrder apiOrder);

    /**
     * 计算订单享有发票的总金额--钱伟
     * 
     * @param orderId
     * @return
     */
    @SelectProvider(method = "selectTotalInvoiceAmountByOrderId", type = SelectSqlProvider.class)
    Map<String, Object> selectTotalInvoiceAmountByOrderId(String orderId, String invoiceCode, String supplierId);

    /**
     * 统计待比对的订单--钱伟
     * 
     * @return
     */
    @Select(" select id as orderId,code as orderCode from OrderInfo where orderInvoiceCheckStatus =30 and IsDeleted = 0 ")
    List<Map<String, Object>> selectNoComparationOrders();

    /**
     * 查询比对不通过订单的所有开票人--钱伟
     * 
     * @return
     */
    @Select(" select op.SupplierID,su.Name from " + " OrderInfo ord "
            + " inner join  OrderInfoProduct op on  ord.ID = op.OrderID "
            + " inner join Supplier su on su.ID = op.SupplierID "
            + " where ord.orderInvoiceCheckStatus =50 and ord.IsDeleted = 0 " + " group by  op.SupplierID,su.Name ")
    List<Map<String, Object>> selectAllSupplierByComparationFail();

    /**
     * 获取该申请单填写的发票的数量 常规+红票-退票
     * 
     * @return
     */
    @Select("  select sum(isn.regular_invoice_count + isn.red_invoice_count - isn.refund_ticket_count) from invoice_scanning isn "
            + " where isn.order_id = #{value} and isn.deleted = 0 ")
    int selectInvoiceCountByOrderId(String orderId);

    /**
     * 获取该订单的数量 去除扫描退票的状态的发票
     * 
     * @return
     */
    @Select(" select count(0) from invoice i where i.order_id = #{orderId} and i.deleted = 0 and i.invoice_status != 5 ")
    int selectScannedInvoiceCount(String orderId);

    /**
     * 申请单状态改为扫描退票--吴凡
     * 
     * @return
     */
    @Update(" update invoice_scanning set application_status = #{applicationStatus} ,"
            + " scanned_ticket_count = scanned_ticket_count-#{refundTicketCount}, "
            + " refund_ticket_count = refund_ticket_count+#{refundTicketCount} where application_id = #{applicationId}")
    void updateScanningApplicationStatusForRefundTickets(ApiInvoiceScanning model);

    /**
     * 退票
     * 
     * @param model
     */
    @Update(" update invoice set invoice_status = #{invoiceStatus} where invoice_seller_id = #{invoiceSellerId}  ")
    void updateInvoiceStatusVerificationFailBySupplierId(ApiInvoice model);

    /**
     * 根据申请单id和开票人id获取开票人张数--吴凡
     * 
     * @return
     */
    @Select(" select count(*) from invoice where application_id = #{0} and invoice_seller_id =#{1}  ")
    int selectCountForSupplierInvoices(String applicationId, String supplierId);

    /**
     * 查询当前用户的权限--钱伟
     * 
     * @return
     */
    @Select(" select MenuCode menuCode,MenuName menuName,LinkURL linkUrl from VUserMenu where ID  = #{value} ")
    List<Map<String, Object>> selectAuthByUserId(String userId);

    /**
     * 查询当前用户角色--钱伟
     * 
     * @return
     */
    @Select(" select r.RoleName as roleName,ru.RoleID as roleId from Sys_RoleOfUser ru,Sys_Role r "
            + " where ru.RoleID = r.RoleID and ru.UserGuid = #{value} ")
    List<Map<String, Object>> selectRoleByUserId(String userId);

    /**
     * 查询报关单预录单的名称--钱伟
     * 
     * @return
     */
    @Select(" select CONVERT(varchar(100), do.Createtime, 112)+'/'+ LOWER(REPLACE(LTRIM(do.ID),'-','')) +'.'+do.FileType as declarationName from "
            + " OrderSpecialDocument osd, Document do "
            + " where osd.DocumentID = do.ID and  osd.Type =1 and OrderID = #{value} ")
    Map<String, Object> selectDeclareName(String orderId);

    // -------------------------------------------------sunquanya
    /**
     * 获取采购合同里面的订单开票人
     * 
     * @return
     */
    @Select(" select ci.supplierId,s.TaxpayerIdentityNumber,ci.TotalLowerCNY 'totalAmount' from ContractInvoice ci "
            + " inner join Supplier s on s.id = ci.SupplierId where ci.OrderID = #{value}")
    List<ApiOrderSupplier> selectSupplierFromContarctByOrderId(String orderId);

    /**
     * 根据orderId，supplierId获取采购合同里面的商品信息
     * 
     * @return
     */
    @Select(" select oip.DeclareName 'productName',cipi.UnitID,cipi.UnitCount, (SELECT opde.DeclareElementValue "
            + "  FROM OrderProductDeclareElement AS opde  WHERE opde.OrderProductID =oip.ID "
            + " AND opde.DictionaryDeclareElementID = '2135F007-047D-45C0-951D-8CE3E8E7978A' "
            + " ) AS DeclareElementValue from ContractInvoiceProductInfo cipi "
            + " left join ContractInvoice ci on cipi.ContractInvoiceID = ci.id "
            + " inner join OrderInfoProduct oip ON oip.ID = cipi.OrderInfoProductID "
            + " inner join CustomersProduct cp on cp.ID = oip.ProductID "
            + " where ci.OrderID = #{0} and ci.SupplierID = #{1}")
    List<ApiOrderSupplierProduct> selectProductFromContarctByOrderId(String orderId, String supplierId);

    /**
     * 获取提交的发票中所包含的开票人的数量
     * 
     * @return
     */
    @Select(" select count(distinct invoice_seller_id) from invoice where order_id = #{value} and invoice_status != 5 ")
    int selectCountSupplierByOrderId(String orderId);

    /**
     * 获取发票里面该订单下该开票人的商品信息
     * 
     * @return
     */
    @Select(" select ip.invoice_id,ip.prodcut_declare_name,ip.product_specification,ip.product_unit,ip.product_quanity,ip.product_total_amount "
            + " from invoice i,invoice_product ip " + " where i.invoice_id = ip.invoice_id "
            + " and i.order_id = #{0} and i.invoice_seller_id = #{1} and invoice_status != 5")
    List<ApiInvoiceProduct> selectInvoiceProductByOrderIdBySupplierId(String orderId, String supplierId);

    /**
     * 获取发票里面该订单下所有存在的开票人--去除退票
     * 
     * @return
     */
    @Select(" select distinct i.invoice_seller_id,s.name 'invoiceSellerName',i.invoice_seller_tax_number "
            + " from invoice i " + " inner join Supplier s on i.invoice_seller_id = s.ID "
            + " where order_id = #{value} and invoice_status != 5 ")
    List<ApiInvoiceSupplier> selectInvoiceSupplierByOrderId(String orderId);

    /**
     * 获取采购合同里面该订单下的该开票人的单位及对应的数量
     * 
     * @return
     */
    @Select(" select cipi.UnitID unitId ,convert(float,UnitCount) count ,oip.DeclareName name "
            + " from ContractInvoice ci  "
            + " inner join ContractInvoiceProductInfo cipi on cipi.ContractInvoiceID = ci.ID  "
            + " inner join [dbo].[OrderInfoProduct] oip On oip.ID = cipi.OrderInfoProductID "
            + " where ci.OrderID = #{0} and ci.SupplierID = #{1} ")
    List<Map<String, Object>> selectProductCountFromContractByOrderIdBySupplier(String orderId, String supplierId);

    /**
     * 获取采购合同里面该订单下该开票人的总金额
     * 
     * @return
     */
    @Select(" select sum(convert(float,ci.totalLowerCNY)) from ContractInvoice ci where ci.orderId = #{0} and supplierId = #{1} ")
    double selectSupplierAmountFromContractByOrderIdBySupplier(String orderId, String supplierId);

    /**
     * 获取发票里面该订单下该开票人的未税金额
     * 
     * @return
     */
    @Select("select sum(i.invoice_befortax_amount) from invoice i where i.order_id = #{0} and i.invoice_seller_id = #{1} and deleted = 0 and invoice_status!=5 ")
    double selectSupplierAmountFromInoviceByOrderIdBySupplier(String orderId, String supplierId);

    /**
     * 更改单张发状态
     * 
     * @return
     */
    @Update(" update invoice set invoice_status = #{invoiceStatus} where invoice_id = #{invoiceId} and invoice_status != 6")
    void updateInvoiceStatusVerificationFailByInvoiceId(ApiInvoice model);

    /**
     * 设置该订单下该开票人的所有发票为验证未通过
     * 
     * @return
     */
    @Update(" update invoice set invoice_status = #{invoiceStatus} where order_id = #{orderId} and invoice_seller_id = #{invoiceSellerId} and invoice_status != 5 and invoice_status != 6 ")
    void updateAllInvoiceStatusVerificationFailBySupplierId(ApiInvoice model);

    /**
     * 发票验证通过，更改订单状态10-比对待收票
     * 
     * @return
     */
    @Update(" update orderInfo set orderTransferDate=#{orderTransferDate} , orderInvoiceCheckStatus = #{orderInvoiceCheckStatus}"
            + " , orderInvoiceTotalAmount = #{orderInvoiceTotalAmount}"
            + " , orderInvoiceBeforeTaxAmount = #{orderInvoiceBeforeTaxAmount}"
            + " , orderInvoiceTaxAmount = #{orderInvoiceTaxAmount}"
            + " , orderInvoiceTotalNum = #{orderInvoiceTotalNum}  where id = #{orderId} ")
    void updateOrderInvoiceStatusByOrderId(ApiOrder model);

    /**
     * 更改该订单的所有发票扫描申请单的状态为扫描通过
     * 
     * @return
     */
    @Update(" update invoice_scanning set application_status = #{applicationStatus} where order_id = #{orderId}  ")
    void updateScanningStatusByOrderId(ApiInvoiceScanning model);

    /**
     * 获取该订单验证的总张数和三个金额
     * 
     * @return
     */
    @Select("  select order_id,sum(invoice_total_Amount) orderInvoiceTotalAmount, sum(invoice_befortax_amount) orderInvoiceBeforeTaxAmount, "
            + "  sum(invoice_tax_amount) orderInvoiceTaxAmount,count(*) orderInvoiceTotalNum"
            + "  from invoice where order_id = #{value} and deleted = 0 and invoice_status != 5 group by order_id")
    ApiOrder selectAmountCount(String orderId);

}