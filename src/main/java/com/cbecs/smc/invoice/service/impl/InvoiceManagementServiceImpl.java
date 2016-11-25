package com.cbecs.smc.invoice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbecs.smc.invoice.model.CustomerService;
import com.cbecs.smc.invoice.model.Invoice;
import com.cbecs.smc.invoice.model.InvoiceList;
import com.cbecs.smc.invoice.model.InvoiceProduct;
import com.cbecs.smc.invoice.model.ManagementList;
import com.cbecs.smc.invoice.model.ManagementQueryPageable;
import com.cbecs.smc.invoice.model.OrderInvoiceSummary;
import com.cbecs.smc.invoice.persistence.inf.InvoiceManagementMapper;
import com.cbecs.smc.invoice.service.inf.InvoiceManagementService;

@Service
public class InvoiceManagementServiceImpl implements InvoiceManagementService
{

    @Autowired
    private InvoiceManagementMapper invoiceManagementMapper;
    
    @Override
    public List<ManagementList> list(ManagementQueryPageable model)
    {
        return invoiceManagementMapper.selectList(model);
    }

	@Override
	public List<CustomerService> customers(int type) {
		String typeName = "";
		if(type == 1){
			typeName = "Servicer";
		}else if(type == 2){
			typeName = "ManagerUser";
		}
		
		return invoiceManagementMapper.selectCustomerServiceList(typeName);
	}

	@Override
	public int getOrderInvoiceRedCount() {
		return invoiceManagementMapper.selectRedStatusCount();
	}

	@Override
	public OrderInvoiceSummary getOrderInvoiceSummary(String orderId) {
		return invoiceManagementMapper.selectOrderInvoiceSummary(orderId);
	}

	@Override
	public List<InvoiceList> getInvoiceList(String orderId, String invoiceNumber, String supplierId) {
		return invoiceManagementMapper.selectInvoiceList(orderId, invoiceNumber, supplierId);
	}

	@Override
	public List<CustomerService> suppliers(String orderId) {
		return invoiceManagementMapper.selectSupplierList(orderId);
	}

	@Override
	@Transactional
	public void setInvoiceRed(String invoiceId, String orderId) {
		invoiceManagementMapper.setOrderRed(orderId);
		invoiceManagementMapper.setInvoiceRed(invoiceId);
	}

	@Override
	public int isInvoiceCanSetRed(String orderId) {
		int advanceRefundCount = invoiceManagementMapper.selectAdvanceRefundApplyCount(orderId);
		int refundTaxCount = invoiceManagementMapper.selectRefundTaxCount(orderId);
		int flag = 0;
		if(advanceRefundCount > 0 && refundTaxCount == 0){
			flag = 1;
		}else if(advanceRefundCount == 0 && refundTaxCount > 0){
			flag = 2;
		}else if(advanceRefundCount > 0 && refundTaxCount > 0){
			flag = 3;
		}
		
		return flag;
	}

	@Override
	public Invoice getInvoiceDetail(String invoiceId) {
		return invoiceManagementMapper.selectInvoice(invoiceId);
	}

	@Override
	public List<InvoiceProduct> getInvoiceProductList(String invoiceId) {
		return invoiceManagementMapper.selectInvoiceProductList(invoiceId);
	}
}
