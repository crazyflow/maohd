package com.cbecs.smc.invoice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbecs.framework.web.session.CurrentUser;
import com.cbecs.framework.web.session.SessionRepertory;
import com.cbecs.omc.order.service.inf.OrderService;
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
import com.cbecs.smc.invoice.persistence.inf.InvoiceScanningMapper;
import com.cbecs.smc.invoice.service.inf.InvoiceScanningService;

@Service
public class InvoiceScanningServiceImpl implements InvoiceScanningService
{

    @Autowired
    private InvoiceScanningMapper invoiceScanningMapper;

    @Autowired
    private SessionRepertory sessionRepertory;

    @Autowired
    private OrderService orderService;
    /**
     * 生成序列标识
     */
    private static final String INVOICE_SEQ = "FPSM";

    @Override
    public List<ScanningList> list(ScanningQueryPageable model)
    {
        model.setCustomerService(sessionRepertory.get().getId());
        return invoiceScanningMapper.selectList(model);
    }

    @Override
    @Transactional
    public Map<String, Object> add(List<ScanningApplication> model)
    {
        CurrentUser user = sessionRepertory.get();
        Date now = new Date();
        for (ScanningApplication scanningApplication : model)
        {
            scanningApplication.setApplicationId(UUID.randomUUID().toString());
            scanningApplication.setApplicationCode(invoiceScanningMapper.getApplicationCode(INVOICE_SEQ));
            scanningApplication.setCreatedAt(now);
            scanningApplication.setCreatedBy(user.getName());
            scanningApplication.setCreatedById(user.getId());
            scanningApplication.setDeleted(0);
            invoiceScanningMapper.insert(scanningApplication);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", "成功");
        return map;
    }

    @Override
    public Map<String, Object> edit(ScanningModification model)
    {
        // 判断当前申请单的状态，如果为扫描待收票可以更改
        ScanningApplication scanningApplication = invoiceScanningMapper
                .selectScanningApplicationStatusByApplicationId(model.getApplicationId());
        Map<String, Object> map = new HashMap<String, Object>();
        if (scanningApplication == null)
        {
            map.put("error", "扫描单不存在!");
        }
        else if (scanningApplication.getApplicationStatus() == 1 || scanningApplication.getApplicationStatus() == 3)
        {
            invoiceScanningMapper.update(model);
            map.put("success", "成功");
        }
        else
        {
            map.put("error", "该扫描单正在处理，不可修改，请刷新列表!");
        }
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> remove(String applicationId)
    {
        // 判断当前扫描单的状态
        ScanningApplication scanningApplication = invoiceScanningMapper
                .selectScanningApplicationStatusByApplicationId(applicationId);
        Map<String, Object> map = new HashMap<String, Object>();
        if (scanningApplication == null)
        {
            map.put("error", "扫描单不存在!");
        }
        else if (scanningApplication.getApplicationStatus() != 1)
        {
            map.put("error", "该扫描单正在处理，不可删除，请刷新列表!");
        }
        else
        {
            scanningApplication.setDeleted(1);
            invoiceScanningMapper.delete(scanningApplication);
            map.put("success", "成功!");
        }
        return map;
    }

    /**
     * 编辑页面数据
     */
    @Override
    @Transactional
    public ScanningEdition getScanningAppLicationByApplicationId(String applicationId)
    {
        ScanningEdition orderInfoBaseForInvoiceAddIndeCate = invoiceScanningMapper
                .selectScanningApplicationByApplicationId(applicationId);
        orderInfoBaseForInvoiceAddIndeCate
                .setSuppliers(orderService.getContractByOrderId(orderInfoBaseForInvoiceAddIndeCate.getOrderId()));
        return orderInfoBaseForInvoiceAddIndeCate;
    }

    /**
     * 申请详情信息
     */
    @Override
    @Transactional
    public ScanningDetail getScanningDetailByApplicationId(String applicationId)
    {
        ScanningDetail scanningDetail = invoiceScanningMapper.selectScanningDetailByApplicationId(applicationId);
        scanningDetail.setSuppliers(orderService.getContractByOrderId(scanningDetail.getOrderId()));
        return scanningDetail;
    }

    /**
     * 申请详情订单关联信息
     */
    @Override
    @Transactional
    public List<ScanningDetailRelation> getScanningDetailByOrderCode(String applicationId, String orderId)
    {
        // 获取订单号为xx的所有申请单信息
        List<ScanningDetailRelation> list = invoiceScanningMapper.selectScanningDetailByOrderId(applicationId, orderId);
        if (list != null)
        {
            for (ScanningDetailRelation scanningDetailRelation : list)
            {
                List<InvoiceDetailSupplier> invoiceSuppliers = new ArrayList<InvoiceDetailSupplier>();
                // 根据applicationId获取这个申请单的所有开票人
                List<String> suppliers = invoiceScanningMapper
                        .selectScanningDetailSuppliersByApplicationId(scanningDetailRelation.getApplicationId());
                for (String supplierId : suppliers)
                {
                    // 查询开票人信息
                    InvoiceDetailSupplier invoiceDetailSupplier = invoiceScanningMapper
                            .selectInvoiceWithSupplierByOrderId(scanningDetailRelation.getApplicationId(), supplierId);
                    // 查询该该申请单下该开票人的发票的数量
                    invoiceDetailSupplier
                            .setInvoiceCount(invoiceScanningMapper.selectInvoiceCountByApplicationIdAndSupplierId(
                                    scanningDetailRelation.getApplicationId(), supplierId));
                    int refundCount = invoiceScanningMapper.selectInvoiceStatusByApplicationIdAndSupplierId(
                            scanningDetailRelation.getApplicationId(), supplierId);
                    if (refundCount > 0)
                    {
                        invoiceDetailSupplier.setSupplierStatus("退票");
                    }
                    else
                    {
                        invoiceDetailSupplier.setSupplierStatus("");
                    }
                    invoiceSuppliers.add(invoiceDetailSupplier);
                }
                scanningDetailRelation.setSuppliers(invoiceSuppliers);
            }
        }
        return list;
    }

    /**
     * 比对详情页面数据
     */
    @Override
    public ScanningCheckDetail getOrderAndInvoceByOrderId(String orderId)
    {
        return invoiceScanningMapper.selectCheckDetailInvoiceByOrderId(orderId);
    }

    /**
     * 比对详情页面--发票详情信息
     */
    @Override
    public List<InvoiceCheckDetail> getOrderInvoiceDetail(String orderId, String invoiceNumber, String invoiceSellerId)
    {
        return invoiceScanningMapper.selectCheckDetailInvoice(orderId, invoiceNumber, invoiceSellerId);
    }

    /**
     * 获取该订单所有的开票人
     */
    @Override
    public List<Map<String, Object>> getOrderSuppliersByOrderId(String orderId)
    {
        return invoiceScanningMapper.selectOrderSuppliersByOrderId(orderId);
    }

}
