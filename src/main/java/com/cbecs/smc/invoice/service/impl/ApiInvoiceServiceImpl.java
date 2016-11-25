package com.cbecs.smc.invoice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.framework.web.session.CurrentUser;
import com.cbecs.omc.order.model.ApiOrderInfoForInvoiceQueryByPage;
import com.cbecs.omc.order.model.ApiSimpleOrder;
import com.cbecs.omc.order.service.inf.ApiOrderService;
import com.cbecs.smc.invoice.model.ApiInvoice;
import com.cbecs.smc.invoice.model.ApiInvoiceApplicationQueryPageable;
import com.cbecs.smc.invoice.model.ApiInvoiceProduct;
import com.cbecs.smc.invoice.model.ApiInvoiceScanning;
import com.cbecs.smc.invoice.model.ApiInvoiceSupplier;
import com.cbecs.smc.invoice.model.ApiOrder;
import com.cbecs.smc.invoice.model.ApiOrderSupplier;
import com.cbecs.smc.invoice.model.ApiOrderSupplierProduct;
import com.cbecs.smc.invoice.model.ApiSimpleInvoice;
import com.cbecs.smc.invoice.persistence.inf.ApiInvoiceMapper;
import com.cbecs.smc.invoice.service.inf.ApiInvoiceService;

@Service
public class ApiInvoiceServiceImpl implements ApiInvoiceService
{
    private static final Logger logger = LoggerFactory.getLogger(ApiInvoiceServiceImpl.class);

    private static final String RESPONSE_CODE = "code";
    private static final String RESPONSE_MESSAGE = "message";
    private static final String RESPONSE_DATA = "data";
    private static final int RESPONSE_SUCCESS_CODE = 0;
    private static final int RESPONSE_ERROR_CODE = 1;

    @Autowired
    private ApiInvoiceMapper apiInvoiceMapper;

    @Autowired
    private ApiInvoiceService apiInvoiceService;

    @Autowired
    private ApiOrderService apiOrderService;

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    /**
     * 接口数据-查询订单所有未比对的发票
     */
    @Override
    public Map<String, Object> getNoComparisonInvoicesByOrderId(String orderId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        List<Map<String, Object>> invoice = apiInvoiceMapper.selectNoComparisonInvoicesByOrderId(orderId);
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, invoice);
        return icsResponseCode;
    }

    /**
     * 接口数据-查询所有的待扫描和待收票的申请单数据-分页
     */
    @Override
    public Map<String, Object> getNoCompletedApplications(ApiInvoiceApplicationQueryPageable model)
    {
        // 分页
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sortStatus = new Sort();
        sortStatus.setDirection("");
        sortStatus.setProperty("application_status");
        listSort.add(sortStatus);
        Sort sortDate = new Sort();
        sortDate.setDirection("desc");
        sortDate.setProperty("application_date");
        listSort.add(sortDate);
        model.setSort(listSort);
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        List<Map<String, Object>> list = apiInvoiceMapper.selectNoCompletedApplicationsByPageable(model);
        Map<String, Object> page = new HashMap<String, Object>();
        page.put("total", model.getTotal());
        page.put("pageNumber", model.getPageNumber());
        page.put("pageSize", model.getPageSize());
        page.put("applications", list);
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, page);
        return icsResponseCode;
    }

    /**
     * 接口数据，获取发票申请单待收票的数量
     */
    @Override
    public Map<String, Object> getInvoiceScanningCountByNoReceipt()
    {
        // 待收票
        int applicationStatusForGetTicket = apiInvoiceMapper.getInvoiceApplicationStatusForGetTicket();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("noReceiptCounts", applicationStatusForGetTicket);
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, data);
        return icsResponseCode;
    }

    /**
     * 接口数据，获取发票申请单待扫描的数量
     */
    @Override
    public Map<String, Object> getInvoiceScanningCountByNoScanning()
    {
        // 待收票
        int applicationStatusForScanning = apiInvoiceMapper.getInvoiceApplicationStatusForScanning();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("noScanningCounts", applicationStatusForScanning);
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, data);
        return icsResponseCode;
    }

    /**
     * 接口数据--根据发票号码,开票人id,订单id 获取发票list
     */
    @Override
    public Map<String, Object> getInvoicesByCodeAndSupplierAndOrderId(String orderId, String invoiceCode,
            String supplierId)
    {
        List<Map<String, Object>> data = apiInvoiceMapper.selectInvoicesByCodeAndSupplierAndOrderId(orderId,
                invoiceCode, supplierId);
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, data);
        return icsResponseCode;
    }

    /**
     * 接口数据--根据发票id获取发票详情信息
     */
    @Override
    @Transactional
    public Map<String, Object> getInvoiceByInvoiceId(String invoiceId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        ApiInvoice apiInvoice = apiInvoiceMapper.selectInvoicesByInvoiceId(invoiceId);
        apiInvoice.setProducts(apiInvoiceMapper.selectInvoiceProductByInvoiceId(invoiceId));
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, apiInvoice);
        return icsResponseCode;
    }

    /**
     * 接口数据--根据发票id获取发票详情信息
     */
    @Override
    public Map<String, Object> getCustomers()
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, apiInvoiceMapper.selectCustomers());
        return icsResponseCode;
    }

    @Override
    public Map<String, Object> getOrderReceiptInvoiceStatusByOrderId(String orderId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, apiInvoiceMapper.selectOrderReceiptInvoiceStatusByOrderId(orderId));
        return icsResponseCode;
    }

    /**
     * 接口数据-获取登录账户信息
     */
    @Override
    @Transactional
    public Map<String, Object> getManagerUser(String accountNumber, String password)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        Map<String, Object> userInfo = new HashMap<String, Object>();
        CurrentUser user = new CurrentUser();

        userInfo = apiInvoiceMapper.getManagerUser(accountNumber, password);

        if (userInfo != null)
        {
            String token = UUID.randomUUID().toString();

            user.setSessionId(token);
            user.setId(userInfo.get("ID").toString());
            user.setAccount(userInfo.get("Account").toString());
            user.setName(userInfo.get("Name").toString());
            user.setDepartment(userInfo.get("Department").toString());
            user.setPosition(userInfo.get("Position").toString());

            ehCacheCacheManager.getCache("tokenCache").put(token, user);

            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "成功");
            icsResponseCode.put(RESPONSE_DATA, user);
        }
        else
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_ERROR_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "登录失败");
            icsResponseCode.put(RESPONSE_DATA, user);
        }

        return icsResponseCode;
    }

    /**
     * 查询比对待收票和待比对的订单-钱伟
     */
    @Override
    public Map<String, Object> getReceivingAndCheckingOrders(ApiOrderInfoForInvoiceQueryByPage model)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sortStatus = new Sort();
        sortStatus.setDirection("");
        sortStatus.setProperty("orderInvoiceCheckStatus");
        listSort.add(sortStatus);
        Sort sortDate = new Sort();
        sortDate.setDirection("desc");
        sortDate.setProperty("orderTransferDate");
        listSort.add(sortDate);
        model.setSort(listSort);
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        List<Map<String, Object>> list = apiInvoiceMapper.getReceivingAndCheckingOrders(model);
        Map<String, Object> page = new HashMap<String, Object>();
        page.put("total", model.getTotal());
        page.put("pageNumber", model.getPageNumber());
        page.put("pageSize", model.getPageSize());
        page.put("orders", list);
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, page);

        return icsResponseCode;
    }

    /**
     * 查询比对待收票订单的数目-钱伟
     */
    @Override
    public Map<String, Object> getReceivingOrdersForInvoiceCheckCount()
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();

        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, apiInvoiceMapper.getReceivingOrdersForInvoiceCheckCount());

        return icsResponseCode;
    }

    /**
     * 查询待比对订单的数目-钱伟
     */
    @Override
    public Map<String, Object> getCheckingOrdersForInvoiceCheckCount()
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();

        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, apiInvoiceMapper.getCheckingOrdersForInvoiceCheckCount());

        return icsResponseCode;
    }

    /**
     * 查询订单下所有的开票人--钱伟
     */
    @Override
    public Map<String, Object> getSuppliersFromOrderByOrderId(String orderId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();

        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, apiInvoiceMapper.getSuppliersFromOrderByOrderId(orderId));

        return icsResponseCode;
    }

    /**
     * 查询所有比对不通过的发票--钱伟
     */
    @Override
    public Map<String, Object> getInvoicesOfCheckingFail(String orderCode, String orderId, String invoiceCode,
            String supplier)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA,
                apiInvoiceMapper.getInvoicesOfCheckingFail(orderCode, orderId, invoiceCode, supplier));

        return icsResponseCode;
    }

    /**
     * 发票新增前验证
     */
    @Override
    public Map<String, Object> verificationInvoices(ApiInvoice model)
    {
        ApiInvoice invoice = apiInvoiceMapper.selectSameInvoiceNumber(model);
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        if (invoice == null)
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "成功");
            icsResponseCode.put(RESPONSE_DATA, "");
        }
        else
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_ERROR_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "存在相同记录");
            icsResponseCode.put(RESPONSE_DATA, invoice);
        }
        return icsResponseCode;
    }

    /**
     * 接口数据--新增发票
     */
    @Override
    @Transactional
    public Map<String, Object> addInvoices(ApiInvoice model)
    {
        String invoiceId = UUID.randomUUID().toString().toUpperCase();
        model.setInvoiceId(invoiceId);
        // 新增发票信息及商品信息
        apiInvoiceMapper.insertInvoice(model);
        for (ApiInvoiceProduct invoiceProdcut : model.getProducts())
        {
            invoiceProdcut.setProductId(UUID.randomUUID().toString().toUpperCase());
            invoiceProdcut.setInvoiceId(invoiceId);
            if (null == invoiceProdcut.getProductSpecification())
            {
                invoiceProdcut.setProductSpecification("");
            }
            apiInvoiceMapper.insertInvoiceProduct(invoiceProdcut);
        }
        // 更改申请单中已扫张数+1
        ApiInvoiceScanning apiInvoiceScanning = new ApiInvoiceScanning();
        apiInvoiceScanning.setApplicationId(model.getApplicationId());
        apiInvoiceMapper.updateInvoiceScanningScannedTicketCounts(apiInvoiceScanning);
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, "");
        return icsResponseCode;
    }

    /**
     * 更改发票状态
     */
    @Override
    public void editInvoiceStatusByCode(ApiSimpleInvoice model)
    {
        apiInvoiceMapper.updateInvoiceStatusByCode(model);
    }

    /**
     * 接口数据--修改比对不通过发票和订单状态
     */
    @Override
    @Transactional
    public Map<String, Object> editOrderAndInvoiceStatus(ApiSimpleOrder model)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        apiOrderService.editOrderInvoiceStatusByOrderId(model);
        ApiInvoiceScanning apiInvoiceScanning = new ApiInvoiceScanning();
        apiInvoiceScanning.setApplicationStatus(model.getApplicationStatus());
        apiInvoiceScanning.setOrderId(model.getOrderId());
        apiInvoiceService.editInvoiceScanningStatusByOrderId(apiInvoiceScanning);
        for (ApiSimpleInvoice SimpleInvoice : model.getSimpleInvoices())
        {
            apiInvoiceService.editInvoiceStatusByCode(SimpleInvoice);
        }
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, "");
        return icsResponseCode;
    }

    /**
     * 接口-更改发票扫描申请状态
     */
    @Override
    public Map<String, Object> editInvoiceScanningApplicationStatusById(ApiInvoiceScanning model)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        int retValue = apiInvoiceMapper.updateInvoiceScanningApplicationStatusById(model);
        if (retValue > 0)
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "成功");
            icsResponseCode.put(RESPONSE_DATA, "");
        }
        else
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_ERROR_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "失败");
            icsResponseCode.put(RESPONSE_DATA, "");
        }

        return icsResponseCode;
    }

    /**
     * 接口-更改发票扫描申请状态及添加原因
     */
    @Override
    public Map<String, Object> editInvoiceScanningApplicationStatusAndReasonById(ApiInvoiceScanning model)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        int retValue = apiInvoiceMapper.updateInvoiceScanningApplicationStatusAndReasonById(model);

        if (retValue > 0)
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "成功");
            icsResponseCode.put(RESPONSE_DATA, "");
        }
        else
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_ERROR_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "失败");
            icsResponseCode.put(RESPONSE_DATA, "");
        }

        return icsResponseCode;
    }

    /**
     * 更改订单发票比对状态之比对收票--钱伟
     */
    @Override
    public Map<String, Object> editOrderInvoiceCheckStatusByOrderId(String orderId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        ApiOrder apiOrder = new ApiOrder();
        apiOrder.setOrderId(orderId);
        int retValue = apiInvoiceMapper.updateOrderInvoiceCheckStatusByOrderId(apiOrder);

        if (retValue > 0)
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "成功");
            icsResponseCode.put(RESPONSE_DATA, "");
        }
        else
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_ERROR_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "失败");
            icsResponseCode.put(RESPONSE_DATA, "");
        }

        return icsResponseCode;
    }

    /**
     * 更改订单发票比对状态之比对拒收票--钱伟
     */
    @Override
    public Map<String, Object> editOrderInvoiceCheckStatusAndReasonByOrderId(String orderId, String reason)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        ApiOrder apiOrder = new ApiOrder();
        apiOrder.setOrderId(orderId);
        apiOrder.setOrderRefuseCollectInvoiceReason(reason);
        int retValue = apiInvoiceMapper.updateOrderInvoiceCheckStatusAndReasonByOrderId(apiOrder);

        if (retValue > 0)
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "成功");
            icsResponseCode.put(RESPONSE_DATA, "");
        }
        else
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_ERROR_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "失败");
            icsResponseCode.put(RESPONSE_DATA, "");
        }

        return icsResponseCode;
    }

    /**
     * 计算订单享有发票的总金额--钱伟
     */
    @Override
    public Map<String, Object> getTotalInvoiceAmountByOrderId(String orderId, String invoiceCode, String supplierId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        Map<String, Object> map = apiInvoiceMapper.selectTotalInvoiceAmountByOrderId(orderId, invoiceCode, supplierId);
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, map);
        return icsResponseCode;
    }

    /**
     * 统计待比对的订单--钱伟
     */
    @Override
    public Map<String, Object> getNoComparationOrders()
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        List<Map<String, Object>> list = apiInvoiceMapper.selectNoComparationOrders();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, list);
        return icsResponseCode;
    }

    /**
     * 查询比对不通过订单的所有开票人--钱伟
     */
    @Override
    public Map<String, Object> getAllSupplierByComparationFail()
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        List<Map<String, Object>> list = apiInvoiceMapper.selectAllSupplierByComparationFail();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, list);
        return icsResponseCode;
    }

    /**
     * 根据订单id修改所有申请单的状态为待扫描
     */
    @Override
    public void editInvoiceScanningStatusByOrderId(ApiInvoiceScanning model)
    {
        apiInvoiceMapper.updateInvoiceScanningStatusByOrderId(model);
    }

    /**
     * 发票编辑
     */
    @Override
    @Transactional
    public Map<String, Object> editInvoice(ApiInvoice model)
    {
        // 判断发票状态是否为退票，若退票编辑则扫描单退票数量-1.已扫数量+1
        ApiInvoice apiInvoice = apiInvoiceMapper.selectInvoicesByInvoiceId(model.getInvoiceId());
        if (apiInvoice.getInvoiceStatus() == 5)
        {
            ApiInvoiceScanning apiInvoiceScanning = new ApiInvoiceScanning();
            apiInvoiceScanning.setApplicationId(apiInvoice.getApplicationId());
            apiInvoiceScanning.setRefundTicketCount(1);
            apiInvoiceScanning.setScannedTicketCount(1);
            // 更改发票申请单的退票数量和已扫数量
            apiInvoiceMapper.updateInvoiceScanningCount(apiInvoiceScanning);
            // 更改发票信息
            apiInvoiceMapper.updateInvoice(model);
        }
        else
        {
            apiInvoiceMapper.updateInvoice(model);
        }
        ApiInvoiceProduct apiInvoiceProduct = new ApiInvoiceProduct();
        apiInvoiceProduct.setInvoiceId(model.getInvoiceId());
        // 删除原发票中商品信息
        apiInvoiceMapper.deleteInvoiceProduct(apiInvoiceProduct);
        // 新增发票的商品
        for (ApiInvoiceProduct invoiceProduct : model.getProducts())
        {
            invoiceProduct.setInvoiceId(model.getInvoiceId());
            invoiceProduct.setProductId(UUID.randomUUID().toString().toUpperCase());
            apiInvoiceMapper.insertInvoiceProduct(invoiceProduct);
        }
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, "");
        return icsResponseCode;
    }

    /**
     * 获取报关预录单的名称
     */
    @Override
    public Map<String, Object> getDeclareName(String orderId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, apiInvoiceMapper.selectDeclareName(orderId));
        return icsResponseCode;
    }

    /**
     * 获取用户权限
     */
    @Override
    public Map<String, Object> getAuthByUserId(String userId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, apiInvoiceMapper.selectAuthByUserId(userId));
        return icsResponseCode;
    }

    /**
     * 获取用户角色
     */
    @Override
    public Map<String, Object> getRoleByUserId(String userId)
    {
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, apiInvoiceMapper.selectRoleByUserId(userId));
        return icsResponseCode;
    }

    /**
     * 扫描退票
     */
    @Override
    @Transactional
    public Map<String, Object> updateSupplierInvoicesByApplicationId(String invoiceId)
    {
        ApiInvoice apiInvoice = apiInvoiceMapper.selectInvoicesByInvoiceId(invoiceId);
        // 查询该订单要退票的开票人的张数
        int refundCount = apiInvoiceMapper.selectCountForSupplierInvoices(apiInvoice.getApplicationId(),
                apiInvoice.getInvoiceSellerId());
        ApiInvoiceScanning apiInvoiceScanning = new ApiInvoiceScanning();
        apiInvoiceScanning.setApplicationId(apiInvoice.getApplicationId());
        apiInvoiceScanning.setRefundTicketCount(refundCount);
        apiInvoiceScanning.setApplicationStatus(9);
        // 更改申请单的状态和退票张数
        apiInvoiceMapper.updateScanningApplicationStatusForRefundTickets(apiInvoiceScanning);
        // 开票人所有的发票改为退票状态
        apiInvoice.setInvoiceStatus(5);
        apiInvoiceMapper.updateInvoiceStatusVerificationFailBySupplierId(apiInvoice);
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
        icsResponseCode.put(RESPONSE_MESSAGE, "成功");
        icsResponseCode.put(RESPONSE_DATA, "");
        return icsResponseCode;
    }

    /**
     * 验证 1.判断当前发票数量是否与订单收票数量相等 2.开票人下商品比对
     * 1.品名/产品名称、2规格型号、3.计量单位、4.数量（同品名+规格型号的组合汇总）、5.总金额（此处数据根据发票总金额和订单所有采购合同的总金额比对，如差额大于100元不允许比对通过）
     */
    @Override
    @Transactional
    public Map<String, Object> verificationInvoices(String orderId)
    {
        String message = "";
        Map<String, Object> icsResponseCode = new HashMap<String, Object>();
        // 该订单的发票申请单数量--和发票扫描数量
        int applicationCount = apiInvoiceMapper.selectInvoiceCountByOrderId(orderId);
        int invoiceCount = apiInvoiceMapper.selectScannedInvoiceCount(orderId);
        if (applicationCount != invoiceCount)
        {
            // 扫描张数与收票张数不服
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_ERROR_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "扫描张数与收票张数不符!");
            icsResponseCode.put(RESPONSE_DATA, "");
            return icsResponseCode;
        }
        // 判断所选的订单时候已经提交了所有的开票人的发票--去除退票
        int invoiceSupplierCount = apiInvoiceMapper.selectCountSupplierByOrderId(orderId);
        // 采购合同中该订单的所有开票人信息
        List<ApiOrderSupplier> suppliers = apiInvoiceMapper.selectSupplierFromContarctByOrderId(orderId);
        // 判断提交的发票是否包含该订单的所有开票人,判断最后订单是否发票收齐
        boolean finished = false;
        if (invoiceSupplierCount == suppliers.size())
        {
            finished = true;
        }
        // 发票-获取该订单下存在的所有开票人--去除退票
        List<ApiInvoiceSupplier> invoiceSuppliersTemp = apiInvoiceMapper.selectInvoiceSupplierByOrderId(orderId);
        List<ApiInvoiceSupplier> invoiceSuppliers = new ArrayList<ApiInvoiceSupplier>();
        // 发票-获取每个开票人下的商品信息
        for (ApiInvoiceSupplier invoiceSupplierTemp : invoiceSuppliersTemp)
        {
            ApiInvoiceSupplier invoiceSupplier = new ApiInvoiceSupplier();
            invoiceSupplier.setInvoiceSellerId(invoiceSupplierTemp.getInvoiceSellerId());
            invoiceSupplier.setInvoiceSellerName(invoiceSupplierTemp.getInvoiceSellerName());
            invoiceSupplier.setInvoiceSellerTaxNumber(invoiceSupplierTemp.getInvoiceSellerTaxNumber());
            List<ApiInvoiceProduct> invoiceproducts = apiInvoiceMapper
                    .selectInvoiceProductByOrderIdBySupplierId(orderId, invoiceSupplierTemp.getInvoiceSellerId());
            invoiceSupplier.setProducts(invoiceproducts);
            invoiceSuppliers.add(invoiceSupplier);
        }
        // 判断该订单下该开票人的发票是否验证未通过
        boolean verification = true;
        for (ApiInvoiceSupplier apiInvoiceSupplier : invoiceSuppliers)
        {
            // 判断开票人信息是否验证成功
            boolean supplierVerification = true;
            // 单张发票比对--比对时记录每张发票状态
            // 单张比对完成，统计开票人金额是否正确，若正确，无操作，若不正确，把该订单下有错误开票人的发票状态改为验证不通过
            // 验证发票商品信息
            for (ApiInvoiceProduct invoiceProduct : apiInvoiceSupplier.getProducts())
            {
                // 获取采购合同里面该订单该开票人的商品信息集合
                List<ApiOrderSupplierProduct> orderProducts = apiInvoiceMapper
                        .selectProductFromContarctByOrderId(orderId, apiInvoiceSupplier.getInvoiceSellerId());
                // 商品id是否存在
                boolean productId = false;
                String productName = "";
                // 采购合同的产品规格
                String specification = "";
                // 采购合同的商品单位
                int unit = -1;
                // 比较发票里面的商品名称是否存在
                for (ApiOrderSupplierProduct orderProduct : orderProducts)
                {
                    // 发票上商品名称与采购合同上商品名称相等
                    if (invoiceProduct.getProdcutDeclareName().equals(orderProduct.getProductName()))
                    {
                        productName = invoiceProduct.getProdcutDeclareName();
                        specification = orderProduct.getDeclareElementValue() == null ? ""
                                : orderProduct.getDeclareElementValue();
                        unit = orderProduct.getUnitId();
                        productId = true;
                        break;
                    }
                }
                ApiInvoice invoice = new ApiInvoice();
                invoice.setInvoiceId(invoiceProduct.getInvoiceId());
                // 商品名称一致
                if (productId)
                {
                    String productSpecification = invoiceProduct.getProductSpecification() == null ? ""
                            : invoiceProduct.getProductSpecification();
                    // 获取发票上商品id存在采购合同上情况的采购合同的产品规格、产品单位是否一致
                    if (!productSpecification.equals(specification))
                    {
                        // 产品规格不一致
                        logger.info("订单id" + orderId + "下，开票人" + apiInvoiceSupplier.getInvoiceSellerId() + "下，商品"
                                + productName + "产品规格与采购合同不一致");
                        message += "<br/>开票人" + apiInvoiceSupplier.getInvoiceSellerName() + "下，商品" + productName
                                + "产品规格与采购合同不一致";
                        invoice.setInvoiceStatus(2);
                        apiInvoiceMapper.updateInvoiceStatusVerificationFailByInvoiceId(invoice);
                        supplierVerification = false;
                        continue;
                    }
                    if (unit != invoiceProduct.getProductUnit())
                    {
                        // 产品单位不一致
                        logger.info("订单id" + orderId + "下，开票人" + apiInvoiceSupplier.getInvoiceSellerId() + "下，商品"
                                + productName + "产品单位与采购合同不一致");
                        message += "<br/>开票人" + apiInvoiceSupplier.getInvoiceSellerName() + "下，商品" + productName
                                + "产品单位与采购合同不一致";
                        invoice.setInvoiceStatus(2);
                        apiInvoiceMapper.updateInvoiceStatusVerificationFailByInvoiceId(invoice);
                        supplierVerification = false;
                        continue;
                    }
                    invoice.setInvoiceStatus(1);// 单张发票验证通过
                    apiInvoiceMapper.updateInvoiceStatusVerificationFailByInvoiceId(invoice);
                    continue;
                }
                else
                {
                    logger.info("订单id" + orderId + "下，开票人" + apiInvoiceSupplier.getInvoiceSellerId() + "下，商品"
                            + productName + "在采购合同上不存在");
                    message += "<br/>开票人" + apiInvoiceSupplier.getInvoiceSellerName() + "下，商品" + productName
                            + "在采购合同上不存在!";
                    invoice.setInvoiceId(invoiceProduct.getInvoiceId());
                    invoice.setInvoiceStatus(2);// 验证未通过
                    // 该商品id不存在采购合同上所有开票人的商品id对应，该发票不为该订单的发票，该发票验证不通过
                    apiInvoiceMapper.updateInvoiceStatusVerificationFailByInvoiceId(invoice);
                    supplierVerification = false;
                    continue;
                }
            }
            // 数量验证
            // 发票--该开票人的商品名称/商品单位对应的商品总数量 name,unitId,count
            List<Map<String, Object>> invoicelList = new ArrayList<Map<String, Object>>();
            for (ApiInvoiceProduct invoiceProduct : apiInvoiceSupplier.getProducts())
            {
                boolean flag = false;
                Map<String, Object> tempMap = new HashMap<String, Object>();
                // 判断list中是否已经存在
                for (Map<String, Object> map : invoicelList)
                {
                    if (map.get("name").equals(invoiceProduct.getProdcutDeclareName())
                            && map.get("unitId").equals(invoiceProduct.getProductUnit()))
                    {
                        tempMap = map;
                        invoicelList.remove(map);
                        flag = true;
                        break;
                    }
                }
                // 不存在
                if (!flag)
                {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", invoiceProduct.getProdcutDeclareName());
                    map.put("unitId", invoiceProduct.getProductUnit());
                    map.put("count", invoiceProduct.getProductQuanity());
                    invoicelList.add(map);
                }
                else
                {
                    double count = (double) tempMap.get("count");
                    tempMap.put("count", count + invoiceProduct.getProductQuanity());
                    invoicelList.add(tempMap);
                }
            }
            // 采购合同-获取采购合同里面该开票人商品的单位和数量 name,unitId,count
            List<Map<String, Object>> contractProductUnits = apiInvoiceMapper
                    .selectProductCountFromContractByOrderIdBySupplier(orderId,
                            apiInvoiceSupplier.getInvoiceSellerId());
            List<Map<String, Object>> contractCountList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> contractProduct : contractProductUnits)
            {
                boolean flag = false;
                Map<String, Object> tempMap = new HashMap<String, Object>();
                // 判断list中是否已经存在
                for (Map<String, Object> map : contractCountList)
                {
                    if (map.get("name").equals(contractProduct.get("name"))
                            && map.get("unitId").equals(contractProduct.get("unitId")))
                    {
                        tempMap = map;
                        contractCountList.remove(map);
                        flag = true;
                        break;
                    }
                }
                // 不存在
                if (!flag)
                {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", contractProduct.get("name"));
                    map.put("unitId", contractProduct.get("unitId"));
                    map.put("count", contractProduct.get("count"));
                    contractCountList.add(map);
                }
                else
                {
                    double count = (double) tempMap.get("count");
                    tempMap.put("count", count + (double) contractProduct.get("count"));
                    contractCountList.add(tempMap);
                }
            }
            if (!(invoicelList.containsAll(contractCountList) && contractCountList.containsAll(invoicelList)))
            {
                logger.info("订单id" + orderId + "下，开票人" + apiInvoiceSupplier.getInvoiceSellerId()
                        + "下，根据商品名称、单位比对采购合同商品数量错误");
                message += "<br/>开票人" + apiInvoiceSupplier.getInvoiceSellerName() + "下，根据商品名称、单位比对采购合同商品数量错误";
                supplierVerification = false;
                // 该开票人商品数量不对应(根据单位比对),所有发票验证不通过
                ApiInvoice invoice = new ApiInvoice();
                invoice.setOrderId(orderId);
                invoice.setInvoiceSellerId(apiInvoiceSupplier.getInvoiceSellerId());
                invoice.setInvoiceStatus(2);
                apiInvoiceMapper.updateAllInvoiceStatusVerificationFailBySupplierId(invoice);
                // 判断整体验证是否成功
                verification = verification && supplierVerification;
                continue;
            }

            // 金额验证
            double contractSupplierAmount = apiInvoiceMapper.selectSupplierAmountFromContractByOrderIdBySupplier(
                    orderId, apiInvoiceSupplier.getInvoiceSellerId());
            double invoiceSupplierAmount = apiInvoiceMapper.selectSupplierAmountFromInoviceByOrderIdBySupplier(orderId,
                    apiInvoiceSupplier.getInvoiceSellerId());
            if (Math.abs(contractSupplierAmount - invoiceSupplierAmount) > 100)
            {
                logger.info("订单id" + orderId + "下，开票人" + apiInvoiceSupplier.getInvoiceSellerId() + "总金额验证不正确!");
                message += "<br/>开票人" + apiInvoiceSupplier.getInvoiceSellerName() + "总金额验证不正确!" + "采购合同金额:"
                        + contractSupplierAmount + ",发票金额:" + invoiceSupplierAmount;
                supplierVerification = false;
                // 该开票人商品数量不对应(根据单位比对),所有发票验证不通过
                ApiInvoice invoice = new ApiInvoice();
                invoice.setOrderId(orderId);
                invoice.setInvoiceSellerId(apiInvoiceSupplier.getInvoiceSellerId());
                invoice.setInvoiceStatus(2);
                apiInvoiceMapper.updateAllInvoiceStatusVerificationFailBySupplierId(invoice);
                // 判断整体验证是否成功
                verification = verification && supplierVerification;
                continue;
            }
        }
        // 所有的开票人发票已经提交成功，并且发票验证成功
        if (finished && verification)
        {
            // 所有开票人的票都已经提交，若全部正确可以设置订单状态10-比对待收票,所有的申请单状态扫描通过
            ApiOrder order = apiInvoiceMapper.selectAmountCount(orderId);
            order.setOrderInvoiceCheckStatus(10);
            order.setOrderTransferDate(new Date());
            // 订单金额
            apiInvoiceMapper.updateOrderInvoiceStatusByOrderId(order);
            ApiInvoiceScanning apiInvoiceScanning = new ApiInvoiceScanning();
            apiInvoiceScanning.setOrderId(orderId);
            apiInvoiceScanning.setApplicationStatus(4);
            apiInvoiceMapper.updateScanningStatusByOrderId(apiInvoiceScanning);
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_SUCCESS_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "验证成功" + message);
            icsResponseCode.put(RESPONSE_DATA, "");
        }
        else
        {
            icsResponseCode.put(RESPONSE_CODE, RESPONSE_ERROR_CODE);
            icsResponseCode.put(RESPONSE_MESSAGE, "验证失败" + message);
            icsResponseCode.put(RESPONSE_DATA, "");
        }
        return icsResponseCode;
    }

}
