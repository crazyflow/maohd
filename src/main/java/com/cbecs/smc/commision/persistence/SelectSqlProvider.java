package com.cbecs.smc.commision.persistence;

import com.cbecs.framework.spring.SpringContextHolder;
import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.AccountStatementPageable;
import com.cbecs.smc.commision.model.CashPoolPageable;
import com.cbecs.smc.commision.model.SettlementApplicationQueryPageable;
import com.cbecs.smc.commision.model.WithdrawApplication;
import com.cbecs.smc.commision.model.WithdrawApplicationPageable;
import com.cbecs.smc.commision.service.inf.SettlementApplicationService;

public class SelectSqlProvider
{
    /* 佣金提取申请 */
    public static String getSelectCommisionWithdrawListByPageSql(WithdrawApplicationPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT [application_id],[application_code],[channel_name],[channel_id],[total_amount],[selling_rate],[total_foreign_amount],[status],[created_by_name],[application_date],[updated_date],[workflow_id]")
                .append(" FROM [commision_withdraw_application] where deleted=0");
        if (model.getApplicationCode() != null && model.getApplicationCode() != "")
        {
            sql.append(" And CHARINDEX(#{applicationCode,jdbcType=VARCHAR},[application_code]) > 0");
        }
        if (model.getChannelName() != null && model.getChannelName() != "")
        {
            sql.append(" And CHARINDEX(#{channelName,jdbcType=VARCHAR},[channel_name]) > 0");
        }
        if (model.getStatus() > 0)
        {
            sql.append(" And [status] = #{status,jdbcType=INTEGER}");
        }
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And [application_date] between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        if (model.getCreatedByName() != null && model.getCreatedByName() != "")
        {
            sql.append(" And CHARINDEX(#{createdByName,jdbcType=VARCHAR},[created_by_name]) > 0");
        }
        if (model.getCurrencyCode() != null && model.getCurrencyCode() != "")
        {
            sql.append(" And CHARINDEX(#{currencyCode,jdbcType=VARCHAR},[currency_code]) > 0");
        }
        if (model.getWorkFlowIds() != null && model.getWorkFlowIds() != "")
        {
            sql.append(" And [workflow_id] in (" + model.getWorkFlowIds() + ")");
        }
        return sql.toString();

    }

    /* 佣金台帐 */
    public static String getCashPoolListByPageSql(CashPoolPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT [channel_id],[channel_name],[free_amount],[freeze_amount],[total_amount],[created_by],[created_by_name],[created_date],[updated_by],[updated_by_name],[updated_date] ")
                .append(" FROM [channel_cash_pool] a left join company b on b.ID = a.channel_id left join ManagerUser c on c.ID = b.Commissioner WHERE 0=0");
        if (model.getChannelName() != null && model.getChannelName() != "")
        {
            sql.append(" And CHARINDEX(#{channelName,jdbcType=VARCHAR},[channel_name]) > 0");
        }
        if (model.getBusinessMainBody() > 0)
        {
            sql.append(" And [business_main_body] = #{businessMainBody,jdbcType=INTEGER}");
        }
        if (model.getChannelServiceName() != null && model.getChannelServiceName() != "")
        {
             sql.append(" And CHARINDEX(#{channelServiceName,jdbcType=VARCHAR},c.[Name]) > 0");
        }
        return sql.toString();
    }

    /* 佣金台帐详情 */
    public static String getAccountStatementListByPageSql(AccountStatementPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT [statement_id],[channel_id],[channel_name],[capacity1_id],[capacity1_name],[capacity2_id],[capacity2_name],[document_id],[document_code],[rae_type],[currency_id],[currency_code],[rate],[amount],[foreign_amount],[order_id],[order_code],[created_by],[created_by_name],[created_date],[business_main_body] ")
                .append(" FROM  [channel_account_statement] WHERE [channel_id] = #{channelId,jdbcType=VARCHAR}");
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And [created_date] between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        if (model.getCapacity1Name() != null && model.getCapacity1Name() != "")
        {
            sql.append(" And [capacity1_name] = #{capacity1Name}");
        }
        if (model.getRaeType() > 0)
        {
            sql.append(" And [rae_type] = #{raeType,jdbcType=INTEGER}");
        }
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},[order_code]) > 0");
        }
        if (model.getDocumentCode() != null && model.getDocumentCode() != "")
        {
            sql.append(" And CHARINDEX(#{documentCode,jdbcType=VARCHAR},[document_code]) > 0");
        }
        return sql.toString();
    }

    public static String getAccountStatementListSql(AccountStatement model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT [statement_id],[channel_id],[channel_name],[capacity1_id],[capacity1_name],[capacity2_id],[capacity2_name],[document_id],[document_code],[rae_type],[currency_id],[currency_code],[rate],[amount],[foreign_amount],[order_id],[order_code],[created_by],[created_by_name],[created_date],[business_main_body] ")
                .append(" FROM  [channel_account_statement] WHERE [channel_id] = #{channelId,jdbcType=VARCHAR}");
        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And [created_date] between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}");
        }
        if (model.getCapacity1Name() != null && model.getCapacity1Name() != "")
        {
            sql.append(" And CHARINDEX(#{capacity1Name,jdbcType=VARCHAR},[capacity1_name]) > 0");
        }
        if (model.getRaeType() > 0)
        {
            sql.append(" And [rae_type] = #{raeType,jdbcType=INTEGER}");
        }
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append(" And CHARINDEX(#{orderCode,jdbcType=VARCHAR},[order_code]) > 0");
        }
        if (model.getDocumentCode() != null && model.getDocumentCode() != "")
        {
            sql.append(" And CHARINDEX(#{documentCode,jdbcType=VARCHAR},[document_code]) > 0");
        }
        return sql.toString();
    }

    public static String getSequenceNo(String preFix)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "IF NOT EXISTS ( SELECT 1 FROM dbo.Sys_Sequence ss WHERE ss.PreFixString = #{preFix} AND CurrDate >= CONVERT(VARCHAR(10), GETDATE(), 20))")
                .append(" BEGIN").append(" IF EXISTS(SELECT 1").append(" FROM dbo.Sys_Sequence ss")
                .append(" WHERE ss.PreFixString = #{preFix})").append(" BEGIN").append(" UPDATE Sys_Sequence")
                .append(" SET CurrentValue = 0,").append(" CurrDate = GETDATE()")
                .append(" WHERE PreFixString = #{preFix};").append(" END").append(" ELSE BEGIN")
                .append(" INSERT INTO dbo.Sys_Sequence")
                .append(" (SequenceID, SequenceName, CurrentValue, StepValue, PreFixString, PostFixString, Remark, DateFormatStr, SeqFormatStr, CurrDate, IsCanDelete)")
                .append(" VALUES (NEWID(), N'', 0, 1, #{preFix}, N'', N'', N'', N'', GETDATE(), 0);").append(" END")
                .append(" END").append(" UPDATE Sys_Sequence SET CurrentValue+= 1")
                .append(" OUTPUT #{preFix} + CONVERT(VARCHAR(6), GETDATE(), 12) + REPLICATE('0', 4- LEN(Inserted.CurrentValue)) + CONVERT(VARCHAR(4), Inserted.CurrentValue)")
                .append(" WHERE PreFixString = #{preFix}");
        return sql.toString();
    }

    /**
     * 根据订单code数组查询佣金结算信息
     * 
     * @param orderCode
     * @return
     */
    public static String selectSettlementDetailByOrderCode(String orderCode)
    {
        StringBuilder sql = new StringBuilder();
        String[] codes = orderCode.split(",");
        sql.append("SELECT o.[ID], o.[Code], o.[CompanyID] , o.[CompanyName], "
                + "(SELECT SUM(ap.RefundTaxAmount)*0.04 FROM  [AdvanceRefundApplyProductDetail] ap,  [AdvanceRefundApplyOrderDetail] ao WHERE ap.[AdvanceRefundApplyOrderID] = ao.[ID] AND ao.[OrderCode] = o.[Code]) 'cost_amount' "
                + "FROM [OrderInfo] o  "
                + "WHERE o.[Status] in(90,92) AND (o.[IsSellCreditService] = 1 OR o.[IsRefundService] = 1)  "
                + "AND o.[Code] in ");
        String temp = "(";
        for (int i = 0; i < codes.length; i++)
        {
            temp += "'" + codes[i] + "'" + ",";
        }
        temp = temp.substring(0, temp.length() - 1) + ")";
        sql.append(temp);
        return sql.toString();
    }

    /**
     * 查询结算申请列表数据
     * 
     * @param model
     * @return
     */
    public static String selectSettlementApplicaiton(SettlementApplicationQueryPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "  SELECT [application_id], [application_code], [application_date], [status], [workflow_id], [channel_id],[channel_name], "
                        + " [total_cost_amount],[total_income_amount],[total_commission_amount],[withdrawn_amount], "
                        + " [business_main_body] ,[applicant_id],[applicant_name],[remark],[deleted],[created_by],[created_by_name], "
                        + " [created_date],[updated_by],[updated_by_name],[updated_date] "
                        + " FROM [commision_settlement_application] " + " WHERE 1=1");
        // 结算单单号
        if (null != model.getApplicationCode() && !"".equals(model.getApplicationCode()))
        {
            sql.append(" And CHARINDEX(#{applicationCode},[application_code]) > 0 ");
        }
        // 渠道商
        if (null != model.getChannelName() && !"".equals(model.getChannelName()))
        {
            sql.append(" And CHARINDEX(#{channelName},[channel_name]) > 0 ");
        }
        // 状态
        if (0 != model.getStatus())
        {
            sql.append(" And [status] = #{status}");
        }
        // 订单号 特别处理
        if (null != model.getOrderCode() && !"".equals(model.getOrderCode()))
        {
            sql.append(
                    " And [application_id] IN (SELECT [application_id] FROM [commision_settlement_detail] sd WHERE #{orderCode} = sd.[order_code])");
        }
        // 申请人
        if (null != model.getProposerName() && !"".equals(model.getProposerName()))
        {
            sql.append(" And CHARINDEX(#{proposerName},[applicant_name]) > 0 ");
        }
        // 申请时间
        if (null != model.getApplicationDate() && !"".equals(model.getApplicationDate()))
        {
            sql.append(" And [application_date] = #{applicationDate}");
        }
        // 工作流状态,1--待我修改,2--待我审核,0--无工作流状态查询
        if (1 == model.getWorkflowStatus())
        {
            SettlementApplicationService service = SpringContextHolder.getBean(SettlementApplicationService.class);
            String workflowIds = service.getWorkFlowIds();
            // 获取当前属于我的工作流id，获取其状态为待修改状态
            if (!"".equals(workflowIds))
            {
                sql.append(" AND [workflow_id] IN (" + workflowIds + ") AND [status] = 4 ");
            }
            else
            {
                sql.append(" AND 1 != 1 ");
            }
        }
        else if (2 == model.getWorkflowStatus())
        {
            SettlementApplicationService service = SpringContextHolder.getBean(SettlementApplicationService.class);
            String workflowIds = service.getWorkFlowIds();
            // 获取当前属于我的工作流id，获取其状态为待修改状态
            if (!"".equals(workflowIds))
            {
                sql.append(" AND [workflow_id] IN (" + workflowIds + ") AND [status] = 2 ");
            }
            else
            {
                sql.append(" AND 1 != 1 ");
            }
        }
        return sql.toString();
    }

    public static String getSettlementApplicationByChannelId(SettlementApplicationQueryPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "  SELECT [application_id], [application_code], [application_date], [status], [channel_id],[channel_name], "
                        + " [total_cost_amount],[total_income_amount],[total_commission_amount],[withdrawn_amount], "
                        + " [business_main_body] ,[applicant_id],[applicant_name],[remark],[deleted],[created_by],[created_by_name], "
                        + " [created_date],[updated_by],[updated_by_name],[updated_date] "
                        + " FROM [commision_settlement_application] "
                        + " WHERE [status] = 3 AND [total_commission_amount]-[withdrawn_amount] > 0");
        if (model.getIds().length() > 0)
        {
            String settleMentIds = model.getIds().substring(0, model.getIds().length() - 1);
            sql.append(" AND [application_id] not in (" + settleMentIds + ")");
        }
        // 结算单单号
        if (null != model.getChannelId() && model.getChannelId() != "")
        {
            sql.append(" And [channel_id] = #{channelId}");
        }
        // 结算单单号
        if (null != model.getApplicationCode() && model.getApplicationCode() != "")
        {
            sql.append(" And CHARINDEX(#{applicationCode,jdbcType=VARCHAR},[application_code]) > 0");
        }
        // 渠道商
        if (null != model.getChannelName() && model.getChannelName() != "")
        {
            sql.append(" And CHARINDEX(#{channelName,jdbcType=VARCHAR},[channel_name]) > 0");
        }
        // 订单号 特别处理
        if (null != model.getOrderCode() && model.getOrderCode() != "")
        {
             sql.append(" AND [application_id] in (select application_id from commision_settlement_detail where CHARINDEX(#{orderCode,jdbcType=VARCHAR},[order_code]) > 0) ");
        }
        if (model.getBusinessMainBody() > 0)
        {
            sql.append(" And [business_main_body] = #{businessMainBody,jdbcType=INTEGER}");
        }
        return sql.toString();
    }

    /**
     * 获取佣金提取statusInfo
     */
    public static String getStatusInfo(String workFlowIds)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "select cast(sum(iif(t.status=2,1,0)) as varchar(20))+'#'+ cast(sum(iif(t.status=4,1,0)) as varchar(20)) from commision_withdraw_application t where workflow_id in ("
                        + workFlowIds + ")");
        return sql.toString();
    }

    /**
     * 获取佣金结算待我审核及修改的工作流记录
     * 
     * @param workFlowIds
     * @return
     */
    public static String getAuditsRecord(String workFlowIds)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CAST( " + " SUM( " + " IIF(t.[Status]=2,1,0)) as varchar(20)) " + " +'#'+  " + " CAST( "
                + " SUM( " + " IIF(t.[Status]=4,1,0)) as varchar(20))  "
                + " FROM [commision_settlement_application] t  " + " WHERE 1=1 ");
        if (!"".equals(workFlowIds))
        {
            sql.append(" and workflow_id in (" + workFlowIds + ")");
        }

        return sql.toString();
    }
    
    public static String getHistoryAccounts(WithdrawApplication w)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("select top 10 * from commision_withdraw_application where 0=0 ");
        if (w.getChannelId() != null && w.getChannelId() != "")
        {
            sql.append(" and channel_id= #{channelId,jdbcType=CHAR}");
        }
        if(w.getBeneficiaryName() != null && w.getBeneficiaryName() != ""){
            sql.append(" and CHARINDEX(#{beneficiaryName,jdbcType=VARCHAR},beneficiary_name)>0 ");
        }
        sql.append(" order by created_date desc");
        return sql.toString();
    }
}
