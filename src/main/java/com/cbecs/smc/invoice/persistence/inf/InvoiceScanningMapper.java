package com.cbecs.smc.invoice.persistence.inf;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.cbecs.smc.invoice.model.InvoiceCheckDetail;
import com.cbecs.smc.invoice.model.InvoiceDetailSupplier;
import com.cbecs.smc.invoice.model.ScanningApplication;
import com.cbecs.smc.invoice.model.ScanningCheckDetail;
import com.cbecs.smc.invoice.model.ScanningDetail;
import com.cbecs.smc.invoice.model.ScanningDetailRelation;
import com.cbecs.smc.invoice.model.ScanningEdition;
import com.cbecs.smc.invoice.model.ScanningList;
import com.cbecs.smc.invoice.model.ScanningModification;
import com.cbecs.smc.invoice.model.ScanningQueryPageable;
import com.cbecs.smc.invoice.persistence.SelectSqlProvider;

public interface InvoiceScanningMapper
{
    /**
     * 查询扫描申请列表
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectScannigList", type = SelectSqlProvider.class)
    List<ScanningList> selectList(ScanningQueryPageable model);

    /**
     * 新增扫描申请
     * 
     * @param model
     * @return
     */
    @Insert(" insert into invoice_scanning(application_id,application_code,order_id,order_code,company_id,company_name "
            + " ,regular_invoice_count,red_invoice_count,scanned_ticket_count,refund_ticket_count,reject_ticket_reason "
            + " ,application_date,application_status,created_at,created_by_id,created_by,deleted) "
            + " values(#{applicationId},#{applicationCode},#{orderId}, "
            + " #{orderCode},#{companyId},#{companyName},#{regularInvoiceCount},#{redInvoiceCount},#{scannedTicketCount}, "
            + " #{refundTicketCount},#{rejectTicketReason},#{applicationDate},#{applicationStatus} "
            + " ,#{createdAt},#{createdById},#{createdBy},#{deleted}) ")
    void insert(ScanningApplication model);

    /**
     * 编辑扫描申请
     * 
     * @param model
     * @return
     */
    @Update(" update invoice_scanning set application_status = #{applicationStatus},"
            + " regular_invoice_count = #{regularInvoiceCount}," + " red_invoice_count = #{redInvoiceCount}"
            + " where application_id = #{applicationId} ")
    void update(ScanningModification model);

    /**
     * 删除申请单
     * 
     * @param preFix
     * @return
     */
    @Update(" update invoice_scanning set deleted = 1 where application_id = #{applicationId} and deleted = 0 ")
    void delete(ScanningApplication model);

    /**
     * 根据申请单id查询申请单的状态
     */
    @Select(" select isn.application_id,isn.application_status 'applicationStatus' from invoice_scanning isn where isn.application_id = #{value} ")
    ScanningApplication selectScanningApplicationStatusByApplicationId(String applicationId);

    /**
     * 根据applicationId查询申请单基本信息--编辑
     */
    @Select(" select isn.application_id,isn.application_code,isn.order_id,isn.order_code,isn.company_id,isn.company_name,isn.application_date,regular_invoice_count,red_invoice_count,"
            + " case when exists(select i.* from invoice i where i.order_id = isn.order_id and i.invoice_red = 1) then 1 else 0 end as redInvoice "
            + " from invoice_scanning isn where isn.application_id = #{value} ")
    ScanningEdition selectScanningApplicationByApplicationId(String applicationId);

    /**
     * 申请详情-根据applicaitonId查询详情所要展示内容
     */
    @Select(" select application_id,application_code,order_id,order_code,"
            + " company_id,company_name,application_date,regular_invoice_count,red_invoice_count,reject_ticket_reason,created_at,created_by "
            + " from invoice_scanning where application_id = #{value} ")
    ScanningDetail selectScanningDetailByApplicationId(String applicationId);

    /**
     * 申请详情-订单关联详情--申请单信息,除去自身
     */
    @Select("  select isn.application_id,isn.application_code,isn.order_id,isn.order_code,isn.application_date, "
            + "  isn.regular_invoice_count,isn.red_invoice_count " + "  from invoice_scanning isn "
            + " where isn.order_id = #{1} and application_id != #{0} and isn.deleted = 0")
    List<ScanningDetailRelation> selectScanningDetailByOrderId(String applicationId, String orderId);

    /**
     * 申请详情-订单关联详情--申请单关联的开票人信息
     */
    @Select("  select distinct invoice_seller_id from invoice where application_id = #{value} and invoice_status != 5")
    List<String> selectScanningDetailSuppliersByApplicationId(String applicationId);

    /**
     * 申请详情-订单关联详情--发票信息
     */
    @Select("  select i.invoice_seller_id supplierId,i.invoice_seller_name supplierName, "
            + "  sum(i.invoice_befortax_amount) 'invoiceBefoetaxAmount', "
            + "  sum(i.invoice_total_Amount)  'invoiceTotalAmount', "
            + "  sum(i.invoice_tax_amount) 'invoiceTaxAmount'  " + "  from invoice i "
            + "  where i.application_id = #{0} and  " + "  i.invoice_seller_id = #{1} "
            + "  and deleted = 0 and invoice_status != 5 " + "  group by i.invoice_seller_id ,i.invoice_seller_name ")
    InvoiceDetailSupplier selectInvoiceWithSupplierByOrderId(String applicationId, String supplierId);

    /**
     * 查询该该申请单下该开票人的发票数量
     */
    @Select("  select count(*) " + "  from invoice i " + "  where i.application_id = #{0} and  "
            + "  i.invoice_seller_id = #{1} " + "  and deleted = 0 and invoice_status != 5  ")
    int selectInvoiceCountByApplicationIdAndSupplierId(String applicationId, String supplierId);

    /**
     * 获取该申请单下，该开票人状态--退票张数
     */
    @Select("  select count(*) from invoice i  where  i.application_id = #{0} "
            + " and i.invoice_seller_id = #{1} and deleted = 0 and i.invoice_status = 5 ")
    int selectInvoiceStatusByApplicationIdAndSupplierId(String applicationId, String supplierId);

    /**
     * 比对详情页面数据
     */
    @Select(" select o.ID orderId,o.code orderCode,o.CompanyID companyId,o.CompanyName companyName, "
            + " case when o.IsPurchaseInvoiceFinished is null or o.IsPurchaseInvoiceFinished = 0 then 0 else o.IsPurchaseInvoiceFinished end as 'isPurchaseInvoiceFinished', "
            + " (select sum(isn.regular_invoice_count-isn.refund_ticket_count) from invoice_scanning isn where isn.order_id = o.ID and isn.deleted = 0) totalApplicationCount, "
            + " (select count(*) from invoice i where i.order_id = o.ID and i.invoice_status = 4 and i.deleted = 0) totalInvoiceCount, "
            + " (select sum(i.invoice_total_amount) from invoice i where i.order_id = o.ID and i.deleted = 0) totalInvoiceAmount, "
            + " (select sum(i.invoice_tax_amount) from invoice i where i.order_id = o.ID and i.deleted = 0) totalInvoiceTaxAmount "
            + " from orderInfo o where o.id = #{value}")
    ScanningCheckDetail selectCheckDetailInvoiceByOrderId(String orderId);

    /**
     * 比对详情列表数据
     */
    @SelectProvider(method = "selectCheckDetailInvoice", type = SelectSqlProvider.class)
    List<InvoiceCheckDetail> selectCheckDetailInvoice(String orderId, String invoiceNumber, String invoiceSellerId);

    /**
     * 获取订单下的开票人信息
     * 
     * @param orderId
     * @return
     */
    @Select(" select oip.SupplierID AS supplierId, Supplier.Name AS supplierName from OrderInfoProduct oip "
            + " inner join Supplier on oip.SupplierID = Supplier.ID where oip.IsDeleted = 0 and "
            + " oip.OrderID = #{orderId} group by oip.SupplierID, Supplier.Name ")
    List<Map<String, Object>> selectOrderSuppliersByOrderId(String orderId);

    /**
     * 获取新增申请单的序号
     * 
     * @param preFix
     * @return
     */
    @SelectProvider(method = "getSequenceNo", type = SelectSqlProvider.class)
    String getApplicationCode(String preFix);

}