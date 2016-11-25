package com.cbecs.smc.commision.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbecs.framework.web.session.CurrentUser;
import com.cbecs.framework.web.session.SessionRepertory;
import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.CashPool;
import com.cbecs.smc.commision.model.SettlementApplication;
import com.cbecs.smc.commision.model.SettlementApplicationQueryPageable;
import com.cbecs.smc.commision.model.SettlementDetail;
import com.cbecs.smc.commision.model.WithdrawDetail;
import com.cbecs.smc.commision.persistence.inf.SettlementApplicationMapper;
import com.cbecs.smc.commision.persistence.inf.WithdrawApplicationMapper;
import com.cbecs.smc.commision.service.inf.AccountStatementService;
import com.cbecs.smc.commision.service.inf.CashPoolService;
import com.cbecs.smc.commision.service.inf.SettlementApplicationService;
import com.cbecs.smc.commision.service.inf.SettlementDetailService;
import com.cbecs.workflow.model.BusinessInteractiveInfo;
import com.cbecs.workflow.model.EmployeeTrackInfo;
import com.cbecs.workflow.model.ReturnInfo;
import com.cbecs.workflow.model.ReturnSimpleInfo;
import com.cbecs.workflow.model.TrackLineEmployeeInfo;
import com.cbecs.workflow.model.WorkFlowCommonBody;
import com.cbecs.workflow.service.inf.WorkFlowService;

@Service
public class SettlementApplicationServiceImpl implements SettlementApplicationService
{

    private static final Logger logger = LoggerFactory.getLogger(SettlementApplicationServiceImpl.class);

    // 工作流标志
    private static final String WORKFLOW_CODE = "mhdYJJS_test";
    private static final String WORKFLOW_SOURCEMAINMARK = "smc";
    private static final String WORKFLOW_SOURCESUBMARK = "YJJS";

    /**
     * 生成序列标识
     */
    private static final String SETTLEMENT_SEQ = "YJJS";

    @Autowired
    private SettlementApplicationMapper settlementApplicationMapper;

    @Autowired
    private SettlementDetailService settlementDetailService;

    @Autowired
    private CashPoolService cashPoolService;

    @Autowired
    private WithdrawApplicationMapper withdrawApplicationMapper;

    @Autowired
    private AccountStatementService accountStatementService;

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private SessionRepertory sessionRepertory;

    @Override
    public List<SettlementApplication> getSettlementApplication(SettlementApplicationQueryPageable model)
    {
        return settlementApplicationMapper.selectSettlementApplication(model);
    }

    @Override
    @Transactional
    public SettlementApplication getSettlementApplicationByApplicationId(String applictionId)
    {
        SettlementApplication settlementApplication = settlementApplicationMapper
                .selectSettlementApplicationByApplicationId(applictionId);
        // 根据applicationid查询出对应的明细信息
        List<SettlementDetail> list = settlementDetailService.getSettlementDetailByApplicationId(applictionId);
        settlementApplication.setSettlementDetails(list);
        return settlementApplication;
    }

    @Override
    @Transactional
    public Map<String, Object> createSettlementApplication(SettlementApplication model)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        if(model.getStatus() == 2){
            // 验证是否提交错误的订单数据
            if (model.getSettlementDetails() == null)
            {
                map.put("error", "选择的结算订单出错!");
                return map;
            }
        }
        // 初始化settlementApplicaiton数据并添加
        String applicationId = UUID.randomUUID().toString();
        BusinessInteractiveInfo businessInteractiveInfo = new BusinessInteractiveInfo();
        // 草稿不发起工作流
        if (model.getStatus() == 2)
        {
            // 发起工作流
            businessInteractiveInfo.setBusinessCode(applicationId);
            businessInteractiveInfo.setBusinessOperate(1);
            businessInteractiveInfo.setEmployeeId(sessionRepertory.get().getId());
            businessInteractiveInfo.setEmployeeName(sessionRepertory.get().getName());
            businessInteractiveInfo.setFlowCode(WORKFLOW_CODE);
            businessInteractiveInfo.setSignCode("");
            businessInteractiveInfo.setSourceMainMark(WORKFLOW_SOURCEMAINMARK);
            businessInteractiveInfo.setSourceSubMark(WORKFLOW_SOURCESUBMARK);
            businessInteractiveInfo.setWorkFlowId(0);
            businessInteractiveInfo.setWorkSubject("发起审核");
            businessInteractiveInfo.setInfos(null);
            ReturnInfo returnInfo = workFlowService.BusinessInteractive(businessInteractiveInfo);
            if (null == returnInfo || !"success".equals(returnInfo.getStatus()))
            {
                logger.info(" 发起工作流失败:");
                map.put("error", "工作流发起失败!");
                return map;
            }
            // 提交申请，
            businessInteractiveInfo.setWorkFlowId(returnInfo.getWorkFlowId());
            businessInteractiveInfo.setBusinessOperate(3);
            ReturnInfo returnInfo2 = workFlowService.BusinessInteractive(businessInteractiveInfo);
            if (null == returnInfo2 || !"success".equals(returnInfo2.getStatus()))
            {
                logger.info(" 提交工作流失败:");
                map.put("error", "工作流提交失败!");
                return map;
            }
            // 设置workflowId值
            model.setWorkflowId(returnInfo.getWorkFlowId());
        }
        Date now = new Date();
        CurrentUser user = sessionRepertory.get();
        model.setApplicationId(applicationId);
        String applicationCode = withdrawApplicationMapper.getApplicationCode(SETTLEMENT_SEQ);
        model.setApplicationCode(applicationCode);
        model.setWithdrawnAmount(0f);
        model.setApplicantId(user.getId());
        model.setApplicantName(user.getName());
        model.setDeleted(false);
        model.setCreatedBy(user.getId());
        model.setCreatedByName(sessionRepertory.get().getName());
        model.setCreatedDate(now);
        model.setUpdatedBy(user.getId());
        model.setUpdatedByName(sessionRepertory.get().getName());
        model.setUpdatedDate(now);
        settlementApplicationMapper.insertSettlementApplication(model);
        if (null != model.getSettlementDetails())
        {
            for (SettlementDetail settlementDetail : model.getSettlementDetails())
            {
                if ("".equals(settlementDetail.getOrderId()) || "".equals(settlementDetail.getOrderCode())
                        || null == settlementDetail.getOrderId() || null == settlementDetail.getOrderCode())
                {
                    continue;
                }
                // 佣金结算详情
                String detailId = UUID.randomUUID().toString();
                settlementDetail.setDetailId(detailId);
                settlementDetail.setApplicationId(applicationId);
                settlementDetailService.createSettlementDetails(settlementDetail);
            }
        }
        map.put("success", "新增结算申请单成功");
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> editSettlementApplication(SettlementApplication model)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        if(model.getStatus() == 2){
            if (model.getSettlementDetails() == null)
            {
                map.put("error", "选择的结算订单出错!");
                return map;
            }
        }
        if(model.getSettlementDetails() != null){
         // 判断是否满足条件，订单已经存在非草稿状态中的结算单
            for (SettlementDetail settlementDetail : model.getSettlementDetails())
            {
                // 遍历所有的订单
                if ("".equals(settlementDetail.getOrderId()) || "".equals(settlementDetail.getOrderCode())
                        || null == settlementDetail.getOrderId() || null == settlementDetail.getOrderCode())
                {
                    continue;
                }
                // 查询当前所有的，并去除自身的
                int count = settlementDetailService.getCountByOrderCode(settlementDetail.getOrderCode(),
                        model.getApplicationId());
                // 说明该订单已经存在在非草稿状态的结算单
                if (count > 0)
                {
                    logger.info("失败,存在订单已经发起结算!");
                    map.put("error", "失败,存在订单已经发起结算!");
                    return map;
                }
            }
        }
        Date now = new Date();
        CurrentUser user = sessionRepertory.get();
        model.setUpdatedBy(user.getId());
        model.setUpdatedByName(user.getName());
        model.setUpdatedDate(now);
        // 提交
        if (model.getStatus() != 1)
        {
            // 判断是否已经发起工作流
            int workflowId = settlementApplicationMapper.selectWorkflowIdByApplicationId(model.getApplicationId());
            if (workflowId == 0)
            {
                BusinessInteractiveInfo businessInteractiveInfo = new BusinessInteractiveInfo();
                // 发起工作流
                businessInteractiveInfo.setBusinessCode(model.getApplicationId());
                businessInteractiveInfo.setBusinessOperate(1);
                businessInteractiveInfo.setEmployeeId(sessionRepertory.get().getId());
                businessInteractiveInfo.setEmployeeName(sessionRepertory.get().getName());
                businessInteractiveInfo.setFlowCode(WORKFLOW_CODE);
                businessInteractiveInfo.setSignCode("");
                businessInteractiveInfo.setSourceMainMark(WORKFLOW_SOURCEMAINMARK);
                businessInteractiveInfo.setSourceSubMark(WORKFLOW_SOURCESUBMARK);
                businessInteractiveInfo.setWorkFlowId(0);
                businessInteractiveInfo.setWorkSubject("发起审核");
                businessInteractiveInfo.setInfos(null);
                ReturnInfo returnInfo = workFlowService.BusinessInteractive(businessInteractiveInfo);
                if (null == returnInfo || !"success".equals(returnInfo.getStatus()))
                {
                    logger.info("工作流发起失败");
                    map.put("error", "工作流发起失败");
                    return map;
                }
                // 提交申请
                businessInteractiveInfo.setWorkFlowId(returnInfo.getWorkFlowId());
                businessInteractiveInfo.setBusinessOperate(3);
                ReturnInfo returnInfo2 = workFlowService.BusinessInteractive(businessInteractiveInfo);
                if (null == returnInfo2 || !"success".equals(returnInfo2.getStatus()))
                {
                    logger.info("工作流发起失败");
                    map.put("error", "工作流提交失败");
                    return map;
                }
                // 设置workflowId值
                model.setWorkflowId(returnInfo.getWorkFlowId());
            }
            else
            {
                // 已经存在了工作流
                BusinessInteractiveInfo businessInteractiveInfo = new BusinessInteractiveInfo();
                businessInteractiveInfo.setBusinessCode(model.getApplicationId());
                businessInteractiveInfo.setBusinessOperate(3);
                businessInteractiveInfo.setEmployeeId(sessionRepertory.get().getId());
                businessInteractiveInfo.setEmployeeName(sessionRepertory.get().getName());
                businessInteractiveInfo.setFlowCode(WORKFLOW_CODE);
                businessInteractiveInfo.setSignCode("");
                businessInteractiveInfo.setSourceMainMark(WORKFLOW_SOURCEMAINMARK);
                businessInteractiveInfo.setSourceSubMark(WORKFLOW_SOURCESUBMARK);
                businessInteractiveInfo.setWorkFlowId(workflowId);
                businessInteractiveInfo.setWorkSubject("提交成功");
                businessInteractiveInfo.setInfos(null);
                // 再次发起提交
                ReturnInfo returnInfo = workFlowService.BusinessInteractive(businessInteractiveInfo);
                if (null == returnInfo || !"success".equals(returnInfo.getStatus()))
                {
                    logger.info("工作流发起失败" + returnInfo.getMessage());
                    map.put("error", "工作流提交失败" + returnInfo.getMessage());
                    return map;
                }
                // 修改application状态为 2-待审核
                model.setWorkflowId(workflowId);
                model.setStatus(2);
            }
        }
        // 更改settlementAppliction信息
        settlementApplicationMapper.updateSettlementApplicaiton(model);
        // 删除明细信息
        settlementDetailService.removeSettlementDetailsByApplication(model.getApplicationId());
        if (null != model.getSettlementDetails())
        {
            // 新增明细信息
            for (SettlementDetail settlementDetail : model.getSettlementDetails())
            {
                if ("".equals(settlementDetail.getOrderId()) || "".equals(settlementDetail.getOrderCode())
                        || null == settlementDetail.getOrderId() || null == settlementDetail.getOrderCode())
                {
                    continue;
                }
                // 佣金结算详情
                String detailId = UUID.randomUUID().toString();
                settlementDetail.setDetailId(detailId);
                settlementDetail.setApplicationId(model.getApplicationId());
                settlementDetailService.createSettlementDetails(settlementDetail);
            }
        }
        map.put("success", "编辑结算申请单成功");
        return map;
    }

    @Override
    public List<SettlementApplication> getSettlementApplicationByChannelId(SettlementApplicationQueryPageable model)
    {
        List<SettlementApplication> list = settlementApplicationMapper.getSettlementApplicationByChannelId(model);
        return list;
    }

    @Override
    @Transactional
    public Map<String, Object> removeSettlementApplication(String applictionId)
    {
        // 删除明细信息
        settlementDetailService.removeSettlementDetailsByApplication(applictionId);
        // 更改settlementApplication表信息
        settlementApplicationMapper.deleteSettlementApplicaiton(applictionId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("success", "删除成功");
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> getWorkFlowRecord()
    {
        WorkFlowCommonBody wfCommonBody = new WorkFlowCommonBody();
        CurrentUser user = sessionRepertory.get();
        wfCommonBody.setEmployeeId(user.getId());
        wfCommonBody.setSourceMainMark(WORKFLOW_SOURCEMAINMARK);
        wfCommonBody.setSourceSubMark(WORKFLOW_SOURCESUBMARK);
        Map<String, Object> map = new HashMap<String, Object>();
        String workFlowIds = workFlowService.getWorkFlowIds(wfCommonBody);
        if ("".equals(workFlowIds))
        {
            map.put("reFix", 0);
            map.put("reAudit", 0);
            return map;
        }
        String record = settlementApplicationMapper.getAuditsRecord(workFlowIds);
        if (null == record)
        {
            map.put("reFix", 0);
            map.put("reAudit", 0);
        }
        else
        {
            map.put("reAudit", record.split("#")[0]);
            map.put("reFix", record.split("#")[1]);
        }
        return map;
    }

    @Override
    public String getWorkFlowIds()
    {
        WorkFlowCommonBody wfCommonBody = new WorkFlowCommonBody();
        CurrentUser user = sessionRepertory.get();
        wfCommonBody.setEmployeeId(user.getId());
        wfCommonBody.setSourceMainMark(WORKFLOW_SOURCEMAINMARK);
        wfCommonBody.setSourceSubMark(WORKFLOW_SOURCESUBMARK);
        String workFlowIds = workFlowService.getWorkFlowIds(wfCommonBody);
        return workFlowIds;
    }

    @Override
    public ReturnSimpleInfo<List<TrackLineEmployeeInfo>> getWorkFlowCurrentEmployees(int workflowId)
    {
        WorkFlowCommonBody wfCommonBody = new WorkFlowCommonBody();
        CurrentUser user = sessionRepertory.get();
        wfCommonBody.setEmployeeId(user.getId());
        wfCommonBody.setSourceMainMark(WORKFLOW_SOURCEMAINMARK);
        wfCommonBody.setSourceSubMark(WORKFLOW_SOURCESUBMARK);
        wfCommonBody.setWorkFlowId(workflowId);
        ReturnSimpleInfo<List<TrackLineEmployeeInfo>> simpleInfo = workFlowService
                .GetWorkFlowCurrentEmployees(wfCommonBody);
        return simpleInfo;
    }

    @Override
    @Transactional
    public Map<String, Object> auditWorkFlow(String applicationId, boolean flag, String content)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        Date now = new Date();
        CurrentUser user = sessionRepertory.get();
        BusinessInteractiveInfo businessInteractiveInfo = new BusinessInteractiveInfo();
        businessInteractiveInfo.setBusinessCode(applicationId);
        businessInteractiveInfo.setBusinessOperate(1);
        businessInteractiveInfo.setEmployeeId(sessionRepertory.get().getId());
        businessInteractiveInfo.setEmployeeName(sessionRepertory.get().getName());
        businessInteractiveInfo.setFlowCode(WORKFLOW_CODE);
        businessInteractiveInfo.setSignCode("");
        businessInteractiveInfo.setSourceMainMark(WORKFLOW_SOURCEMAINMARK);
        businessInteractiveInfo.setSourceSubMark(WORKFLOW_SOURCESUBMARK);
        businessInteractiveInfo.setWorkFlowId(0);
        businessInteractiveInfo.setInfos(null);
        businessInteractiveInfo.setWorkFlowId(getSettlementApplicationByApplicationId(applicationId).getWorkflowId());
        SettlementApplication settlement = new SettlementApplication();
        settlement.setApplicationId(applicationId);
        settlement.setUpdatedBy(user.getId());
        settlement.setUpdatedByName(user.getName());
        settlement.setUpdatedDate(new Date());
        if (flag)
        {
            // 审核通过
            businessInteractiveInfo.setWorkSubject("审核通过");
            businessInteractiveInfo.setBusinessOperate(3);
            settlement.setStatus(3);
        }
        else
        {
            businessInteractiveInfo.setWorkSubject("审核不通过");
            businessInteractiveInfo.setWorkContext(content);
            settlement.setStatus(4);
            businessInteractiveInfo.setBusinessOperate(4);
        }
        ReturnInfo returnInfo = workFlowService.BusinessInteractive(businessInteractiveInfo);
        // 审核通过80
        if ("success".equals(returnInfo.getStatus()))
        {
            if (returnInfo.getWorkFlowState() == 80)
            {
                logger.info("工作流审核成功,结果为通过!状态码80");
                // 更改结算单状态为审核通过
                settlementApplicationMapper.updateSettlementApplicationStatus(settlement);
                // 根据applicationId获取application数据
                SettlementApplication settlementApplication = getSettlementApplicationByApplicationId(applicationId);
                // 按照订单的金额流水插入
                List<AccountStatement> list = settlementDetailService.getStatementByApplicationId(applicationId);
                for (AccountStatement accountStatement : list)
                {
                    // 佣金结算台账详情
                    accountStatement.setStatementId(UUID.randomUUID().toString());
                    accountStatement.setChannelId(settlementApplication.getChannelId());
                    accountStatement.setChannelName(settlementApplication.getChannelName());
                    accountStatement.setBusinessMainBody(settlementApplication.getBusinessMainBody());
                    String capacityId = UUID.randomUUID().toString();
                    accountStatement.setCapacity1Id(capacityId);
                    accountStatement.setCapacity1Name("佣金");
                    accountStatement.setCapacity2Id(capacityId);
                    accountStatement.setCapacity2Name("佣金");
                    accountStatement.setDocumentId(applicationId);
                    accountStatement.setDocumentCode(settlementApplication.getApplicationCode());
                    accountStatement.setRaeType(1);
                    accountStatement.setCurrencyId(151);
                    accountStatement.setCurrencyCode("CNY");
                    accountStatement.setRate(1.0000f);
                    accountStatement.setAmount(accountStatement.getAmount());
                    accountStatement.setForeignAmount(accountStatement.getForeignAmount());
                    accountStatement.setOrderCode(accountStatement.getOrderCode());
                    accountStatement.setOrderId(accountStatement.getOrderId());
                    accountStatement.setCreatedBy(user.getId());
                    accountStatement.setCreatedByName(user.getName());
                    accountStatement.setCreatedDate(now);
                    accountStatementService.create(accountStatement);
                }
                // 插入台账汇总信息
                CashPool cashPool = new CashPool();
                cashPool.setChannelId(settlementApplication.getChannelId());
                cashPool.setFreeAmount(settlementApplication.getTotalCommissionAmount());
                cashPool.setTotalAmount(settlementApplication.getTotalCommissionAmount());
                cashPool.setUpdatedBy(user.getId());
                cashPool.setUpdatedByName(user.getName());
                cashPool.setUpdatedDate(now);
                int count = cashPoolService.editCashPoolFree(cashPool);
                if (count == 0)
                {
                    cashPool.setChannelId(settlementApplication.getChannelId());
                    cashPool.setChannelName(settlementApplication.getChannelName());
                    cashPool.setFreeAmount(settlementApplication.getTotalCommissionAmount());
                    cashPool.setFreezeAmount(0f);
                    cashPool.setTotalAmount(settlementApplication.getTotalCommissionAmount());
                    cashPool.setBusinessMainBody(settlementApplication.getBusinessMainBody());
                    cashPool.setCreatedBy(user.getId());
                    cashPool.setCreatedByName(user.getName());
                    cashPool.setCreatedDate(now);
                    cashPool.setUpdatedBy(user.getId());
                    cashPool.setUpdatedByName(user.getName());
                    cashPool.setUpdatedDate(now);
                    cashPoolService.createCashPool(cashPool);
                }
                map.put("success", "审核成功");
            }
            else if (returnInfo.getWorkFlowState() == 40)
            {
                // 更改结算单状态为待审核
                logger.info("工作流审核成功,结果为不通过!状态吗40");
                settlementApplicationMapper.updateSettlementApplicationStatus(settlement);
                map.put("success", "该节点审核成功");
            }
            else if (returnInfo.getWorkFlowState() == 30)
            {
                logger.info("该节点审核成功,状态码为" + returnInfo.getWorkFlowState());
                map.put("success", "该节点审核成功");
            }
            else
            {
                logger.info("工作流审核失败,结果为其他,状态码为" + returnInfo.getWorkFlowState());
                map.put("error", "审核结果异常");
            }
        }
        else
        {
            logger.info("工作流审核失败:");
            map.put("error", "操作失败");
        }
        return map;
    }

    @Override
    public List<TrackLineEmployeeInfo> getSettlementWorkflowLine(int workflowId)
    {
        // 若该草稿没有发起审核
        if (0 == workflowId)
        {
            return null;
        }
        List<TrackLineEmployeeInfo> trackLineEmployeeInfo = new ArrayList<TrackLineEmployeeInfo>();
        WorkFlowCommonBody wfCommonBody = new WorkFlowCommonBody();
        CurrentUser user = sessionRepertory.get();
        wfCommonBody.setEmployeeId(user.getId());
        wfCommonBody.setSourceMainMark(WORKFLOW_SOURCEMAINMARK);
        wfCommonBody.setSourceSubMark(WORKFLOW_SOURCESUBMARK);
        wfCommonBody.setWorkFlowId(workflowId);
        trackLineEmployeeInfo = workFlowService.GetSimpleTrackLine(wfCommonBody).getInfos();
        return trackLineEmployeeInfo;
    }

    @Override
    public List<EmployeeTrackInfo> getSettlementWorkflowLines(int workflowId)
    {
        // 若该草稿没有发起审核
        if (0 == workflowId)
        {
            return null;
        }
        WorkFlowCommonBody wfCommonBody = new WorkFlowCommonBody();
        CurrentUser user = sessionRepertory.get();
        wfCommonBody.setEmployeeId(user.getId());
        wfCommonBody.setSourceMainMark(WORKFLOW_SOURCEMAINMARK);
        wfCommonBody.setSourceSubMark(WORKFLOW_SOURCESUBMARK);
        wfCommonBody.setWorkFlowId(workflowId);
        return workFlowService.SortEmployeeInfos(wfCommonBody);
    }

    @Override
    public void updateSettleApplicationByAuditNo(WithdrawDetail withdrawDetail)
    {
        CurrentUser user = sessionRepertory.get();
        SettlementApplication settlementApplication = new SettlementApplication();
        settlementApplication.setApplicationId(withdrawDetail.getSettlementApplicationId());
        settlementApplication.setWithdrawnAmount(withdrawDetail.getAmount());
        settlementApplication.setUpdatedBy(user.getId());
        settlementApplication.setUpdatedByName(user.getName());
        settlementApplication.setUpdatedDate(new Date());
        settlementApplicationMapper.updateSettleApplicationByAuditNo(settlementApplication);
    }

    public static void main(String[] args)
    {
        System.out.println(UUID.randomUUID().toString().toUpperCase());
    }
}
