package com.cbecs.smc.commision.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.cbecs.smc.commision.model.SettlementApplication;
import com.cbecs.smc.commision.model.SettlementApplicationQueryPageable;
import com.cbecs.smc.commision.model.WithdrawDetail;
import com.cbecs.smc.commision.persistence.SelectSqlProvider;

public interface SettlementApplicationMapper
{

    /**
     * 插入结算申请表数据
     * 
     * @param orderCode
     * @return
     */
    @Select(" SELECT COUNT(applicationId) FROM [commision_settlement_application] WHERE [application_id] = #{value}")
    int selectCountByApplicationId(String applicationId);

    /**
     * 插入结算申请表数据
     * 
     * @param orderCode
     * @return
     */
    @Insert(" INSERT INTO [commision_settlement_application] "
            + " ([application_id],[application_code],[application_date],[status],[workflow_id],[channel_id],[channel_name],[cooperation_mode], "
            + " [total_cost_amount],[total_income_amount],[total_commission_amount],[withdrawn_amount], "
            + " [business_main_body], " + " [applicant_id] ,[applicant_name], " + " [remark],[deleted], "
            + " [created_by],[created_by_name],[created_date], " + " [updated_by],[updated_by_name],[updated_date])  "
            + " VALUES (#{applicationId}, #{applicationCode},#{applicationDate},#{status},#{workflowId},#{channelId},#{channelName},#{cooperationMode}, "
            + " #{totalCostAmount},#{totalIncomeAmount},#{totalCommissionAmount}, #{withdrawnAmount}, "
            + " #{businessMainBody}, " + " #{applicantId},#{applicantName}, " + " #{remark},#{deleted}, "
            + " #{createdBy},#{createdByName},#{createdDate}, " + " #{updatedBy},#{updatedByName},#{updatedDate}) ")
    void insertSettlementApplication(SettlementApplication model);

    /**
     * 查询结算信息数据
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectSettlementApplicaiton", type = SelectSqlProvider.class)
    List<SettlementApplication> selectSettlementApplication(SettlementApplicationQueryPageable model);

    /**
     * 根据applicaitonId查询结算申请单信息
     * 
     * @param model
     * @return
     */
    @Select("SELECT [application_id],[application_code],[application_date], "
            + " [status],[workflow_id],[channel_id] ,[channel_name],[cooperation_mode], "
            + " [total_cost_amount],[total_income_amount],[total_commission_amount],[withdrawn_amount], "
            + " [business_main_body],[applicant_id],[applicant_name],[remark],[deleted], "
            + " [created_by],[created_by_name],[created_date],[updated_by],[updated_by_name],[updated_date] "
            + " FROM [commision_settlement_application] WHERE [deleted] = 0 AND [application_id] = #{value}")
    SettlementApplication selectSettlementApplicationByApplicationId(String applicationId);

    /**
     * 根据applicaitonId修改结算单信息
     * 
     * @param model
     * @return
     */
    @Update("UPDATE [commision_settlement_application] " + " SET [status] = #{status}, "
            + " [updated_by] = #{updatedBy,jdbcType=TIMESTAMP}, "
            + " [updated_by_name] = #{updatedByName,jdbcType=TIMESTAMP}, "
            + " [updated_date] = #{updatedDate,jdbcType=TIMESTAMP} " + " WHERE [application_id] = #{applicationId}")
    int updateSettlementApplicationStatus(SettlementApplication model);

    /**
     * 根据applicaitonId删除该结算单的为草稿状态的信息
     * 
     * @param model
     * @return
     */
    @Delete("DELETE [commision_settlement_application] " + " WHERE [application_id] = #{value}")
    int deleteSettlementApplicaiton(String applicationId);

    /**
     * 根据applicaitonId修改settlementApplication信息
     * 
     * @param model
     * @return
     */
    @Update("UPDATE [commision_settlement_application] "
            + " SET [status] = #{status}, [workflow_id] = #{workflowId}, [total_cost_amount] = #{totalCostAmount}, "
            + " [total_income_amount] = #{totalIncomeAmount}, [total_commission_amount] = #{totalCommissionAmount}, "
            + " [remark] = #{remark}, " + " [updated_by] = #{updatedBy,jdbcType=TIMESTAMP}, "
            + " [updated_by_name] = #{updatedByName,jdbcType=TIMESTAMP}, "
            + " [updated_date] = #{updatedDate,jdbcType=TIMESTAMP} " + " WHERE [application_id] = #{applicationId}")
    int updateSettlementApplicaiton(SettlementApplication settlementApplication);

    /**
     * 根据applicaitonId获取workflow_id
     * 
     * @param model
     * @return
     */
    @Select("SELECT [workflow_id] FROM [commision_settlement_application] WHERE [application_id] = #{value}")
    int selectWorkflowIdByApplicationId(String applicationId);

    /**
     * 获取待我修改待我审核记录
     * 
     * @param workFlowIds
     * @return
     */
    @SelectProvider(method = "getAuditsRecord", type = SelectSqlProvider.class)
    String getAuditsRecord(String workFlowIds);

    @SelectProvider(method = "getSettlementApplicationByChannelId", type = SelectSqlProvider.class)
    List<SettlementApplication> getSettlementApplicationByChannelId(SettlementApplicationQueryPageable model);

    @Update("update [commision_settlement_application] set [withdrawn_amount] = [withdrawn_amount] + #{amount,jdbcType=DECIMAL} where [application_id] = #{settlementApplicationId,jdbcType=CHAR}")
    void updateSettlementApplication(WithdrawDetail w);

    @Update("update [commision_settlement_application] set [withdrawn_amount] = [withdrawn_amount] - #{withdrawnAmount,jdbcType=DECIMAL}, [updated_by] = #{updatedBy,jdbcType=TIMESTAMP}, [updated_by_name] = #{updatedByName,jdbcType=TIMESTAMP}, [updated_date] = #{updatedDate,jdbcType=TIMESTAMP} where [application_id] = #{applicationId,jdbcType=CHAR}")
    void updateSettleApplicationByAuditNo(SettlementApplication settlementApplication);
}