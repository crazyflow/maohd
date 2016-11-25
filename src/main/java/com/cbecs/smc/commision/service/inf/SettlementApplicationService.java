package com.cbecs.smc.commision.service.inf;

import java.util.List;
import java.util.Map;

import com.cbecs.smc.commision.model.SettlementApplication;
import com.cbecs.smc.commision.model.SettlementApplicationQueryPageable;
import com.cbecs.smc.commision.model.WithdrawDetail;
import com.cbecs.workflow.model.EmployeeTrackInfo;
import com.cbecs.workflow.model.ReturnSimpleInfo;
import com.cbecs.workflow.model.TrackLineEmployeeInfo;

public interface SettlementApplicationService
{

    /**
     * 新增结算信息
     * 
     * @param orderCode
     * @return
     */
    Map<String, Object> createSettlementApplication(SettlementApplication model);

    /**
     * 修改结算信息数据
     * 
     * @param orderCode
     * @return
     */
    Map<String, Object> editSettlementApplication(SettlementApplication model);

    /**
     * 查询结算信息列表
     * 
     * @param orderCode
     * @return
     */
    List<SettlementApplication> getSettlementApplication(SettlementApplicationQueryPageable model);

    /**
     * 根据结算applicationId查询结算单数据
     * 
     * @param appliction
     * @return
     */
    SettlementApplication getSettlementApplicationByApplicationId(String applictionId);

    /**
     * 根据结算applicationId删除其草稿记录
     * 
     * @param appliction
     * @return
     */
    Map<String, Object> removeSettlementApplication(String applictionId);

    List<SettlementApplication> getSettlementApplicationByChannelId(SettlementApplicationQueryPageable model);

    // -----------------工作流接口--------------------
    /**
     * 获取当前我workflow统计记录
     * 
     * @param appliction
     * @return
     */
    Map<String, Object> getWorkFlowRecord();

    /**
     * 获取我待办的工作流 --- 6066,6089,6077
     * 
     * @param model
     * @return
     */
    String getWorkFlowIds();

    /**
     * 获取工作流当前节点的代办人
     * 
     * @param model
     * @return
     */
    ReturnSimpleInfo<List<TrackLineEmployeeInfo>> getWorkFlowCurrentEmployees(int workflowId);

    /**
     * 佣金结算申请单审核
     * 
     * @param model
     * @return
     */
    Map<String, Object> auditWorkFlow(String applicationId, boolean flag, String content);

    /**
     * 获取结算申请单一般的轨迹线信息
     * 
     * @return
     */
    List<TrackLineEmployeeInfo> getSettlementWorkflowLine(int workflowId);

    /**
     * 获取结算申请单工作流轨迹信息
     * 
     * @return
     */
    List<EmployeeTrackInfo> getSettlementWorkflowLines(int workflowId);

    void updateSettleApplicationByAuditNo(WithdrawDetail withdrawDetail);

}
