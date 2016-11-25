package com.cbecs.scrm.channel.persistence;

import com.cbecs.scrm.channel.model.ChannelBusinessQueryByPage;
import com.cbecs.scrm.channel.model.ConsignerAccountSettlement;
import com.cbecs.scrm.channel.model.ConsignerAccountSettlementPageable;
import com.cbecs.scrm.channel.model.ConsignerCashPoolPageable;
import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.AccountStatementPageable;
import com.cbecs.smc.commision.model.CashPoolPageable;

public class SelectSqlProvider
{
    /**
     * 查询渠道商信息_分页
     * 
     * @return @model 渠道商签约的限制
     */
    public static String selectChannelBusinessByPage(ChannelBusinessQueryByPage model)
    {
        // 获取当前登陆人为渠道专员的id
        // SessionRepertory sessionRepertory = SpringContextHolder.getBean(SessionRepertory.class);
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( " + " select distinct" + " c.id 'ChannelId',c.Name 'ChannelName',"
                + " (SELECT TOP 1 cc.Name FROM CompanyContact cc where cc.CompanyID = c.ID) 'ContactName',"
                + " CASE WHEN c.CooperationMode = 4001 THEN 2 ELSE 1 END AS 'CooperationMode' ,"
                + " cc.SignType 'businessMainBody'  " + " from" + " orderinfo a"
                + " inner join company b on b.id = a.CompanyID"
                + " inner join company c on c.id= b.ChannelCompanyID "
                + " inner join CompanyContract cc on cc.CompanyID = c.ID and cc.SignFlag = 1 and cc.Isdeleted = 0"
                + " where a.Status in (90,92) and a.IsDeleted = 0 and "
                + " a.ID not in (select csd.order_id from commision_settlement_detail csd left join commision_settlement_application csa on csa.application_id = csd.application_id where csa.status != 1)"
                + " ) t where 1=1");
        /*sql.append(" select * from ( " + " select distinct" + " c.id 'ChannelId',c.Name 'ChannelName',"
                + " (SELECT TOP 1 cc.Name FROM CompanyContact cc where cc.CompanyID = c.ID) 'ContactName',"
                + " CASE WHEN c.CooperationMode = 4001 THEN 2 ELSE 1 END AS 'CooperationMode' ,"
                + " cc.SignType 'businessMainBody'  " + " from" + " orderinfo a"
                + " inner join company b on b.id = a.CompanyID"
                + " inner join company c on c.id= b.ChannelCompanyID and " + " c.Commissioner = '"
                + sessionRepertory.get().getId() + "' " + " inner join CompanyContract cc on cc.CompanyID = c.ID "
                + " where a.Status in (90,92) and a.IsDeleted = 0 and "
                + " a.ID not in (select csd.order_id from commision_settlement_detail csd left join commision_settlement_application csa on csa.applicant_id = csd.application_id where csa.status != 1)"
                + " ) t where 1=1");*/
        // 业务主体
        if (0 != model.getBusinessMainBody())
        {
            sql.append(" And t.[businessMainBody] = #{businessMainBody}");
        }
        // 渠道商
        if (null != model.getChannelName() && !"".equals(model.getChannelName()))
        {
            sql.append(" And CHARINDEX(#{channelName},t.[ChannelName])>0");
        }
        // 联系人
        if (null != model.getContactName() && !"".equals(model.getContactName()))
        {
            sql.append(" And CHARINDEX(#{contactName},t.[ContactName])>0");
        }
        return sql.toString();
    }

    /* 渠道商客户台帐 */
    public static String getConsignerCashPoolsByPageSql(ConsignerCashPoolPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT " + "companyName=c.Name ," + "companyID=c.ID," + "cashBalance=ca.CashBalance ,"
                + "depositBalance=ca.DepositBalance ," + "creditBalance=ca.CreditBalance ,"
                + "cashGatherAmount=ca.CashGatherAmount ," + "cashPayAmount=ca.CashPayAmount ,"
                + "cashFreezeAmount=ca.CashFreezeAmount ," + "depositGatherAmount=ca.DepositGatherAmount ,"
                + "depositPayAmount=ca.DepositPayAmount ," + "depositFreezeAmount=ca.DepositFreezeAmount ,"
                + "creditGatherAmount=ca.CreditGatherAmount ," + "creditPayAmount=ca.CreditPayAmount ,"
                + "creditFreezeAmount=ca.CreditFreezeAmount ," + "depositNeedPayAmount=ca.DepositNeedPayAmount ,"
                + "consultantName=m.Name," + "consultantId=m.ID," + "signFlag=c.SignFlag,"
                + "companyType=ca.OurCompanyType " + "FROM Company c "
                + "LEFT JOIN CompanyAsset ca ON ca.CompanyID = c.ID "
                + "LEFT JOIN ManagerUser m ON c.ManagerUser = m.ID " + "WHERE c.SignFlag = 1 ");
        if (model.getCompanyName() != null && model.getCompanyName() != "")
        {
            sql.append("And CHARINDEX(#{companyName, jdbcType=VARCHAR},c.Name) ");
        }
        if (model.getConsultantId() != null && model.getConsultantId() != "")
        {
            sql.append("And m.ID = #{consultantId, jdbcType=VARCHAR} ");
        }
        if (model.getCompanyType() > 0)
        {
            sql.append("And ca.OurCompanyType = #{companyType, jdbcType=INTEGER} ");
        }
        return sql.toString();
    }

    public static String getConsignerCashPoolsByCompanyId(String companyId)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT " + "companyName=c.Name ," + "companyID=c.ID," + "cashBalance=ca.CashBalance ,"
                + "depositBalance=ca.DepositBalance ," + "creditBalance=ca.CreditBalance ,"
                + "cashGatherAmount=ca.CashGatherAmount ," + "cashPayAmount=ca.CashPayAmount ,"
                + "cashFreezeAmount=ca.CashFreezeAmount ," + "depositGatherAmount=ca.DepositGatherAmount ,"
                + "depositPayAmount=ca.DepositPayAmount ," + "depositFreezeAmount=ca.DepositFreezeAmount ,"
                + "creditGatherAmount=ca.CreditGatherAmount ," + "creditPayAmount=ca.CreditPayAmount ,"
                + "creditFreezeAmount=ca.CreditFreezeAmount ," + "depositNeedPayAmount=ca.DepositNeedPayAmount ,"
                + "consultantName=m.Name," + "consultantId=m.ID," + "signFlag=c.SignFlag,"
                + "companyType=ca.OurCompanyType " + "FROM Company c "
                + "LEFT JOIN CompanyAsset ca ON ca.CompanyID = c.ID "
                + "LEFT JOIN ManagerUser m ON c.ManagerUser = m.ID "
                + "WHERE c.SignFlag = 1 AND c.ID=#{companyId, jdbcType=VARCHAR}");
        return sql.toString();
    }

    /* 渠道商客户台帐详情 */
    public static String getConsignerAccountSettlementsByPageSql(ConsignerAccountSettlementPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT " + "createTime=a.createTime," + "exacctName=c.ExacctName," + "billNo=a.billNo,"
                + "billId=a.billId," + "orderCode=a.orderCode," + "orderId=a.orderId,"
                + "foreignAmount=a.foreignAmount," + "currencyName=d.Name," + "rate=a.ExchangeRate," + "raeType=e.Text,"
                + "amount=a.amount," + "accountBalance=a.accountBalance," + "companyName=corp.name,"
                + "companyId=corp.id," + "accountType=b.Text," + "sortNum=a.SortNum " + "FROM CompanyAccountRecord a "
                + "LEFT JOIN DictionaryCommon b ON a.AccountType = b.ID "
                + "LEFT JOIN Exacct c ON c.ID = a.CapitalType2 "
                + "LEFT JOIN DictionaryCurrency d ON d.ID = a.CurrencyID "
                + "LEFT JOIN DictionaryCommon e ON e.ID = a.AccountChangeType "
                + "LEFT JOIN Company corp ON corp.ID = a.CompanyID WHERE a.CompanyID=#{companyId,jdbcType=VARCHAR} ");

        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And a.createTime between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR} ");
        }
        if (model.getBillNo() != null && model.getBillNo() != "")
        {
            sql.append("And CHARINDEX(#{billNo, jdbcType=VARCHAR},a.billNo)>0 ");
        }
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append("And CHARINDEX(#{orderCode, jdbcType=VARCHAR},a.orderCode)>0 ");
        }
        if (model.getCapitalType2() != null && model.getCapitalType2() != "")
        {
            sql.append("And a.capitalType2 = #{capitalType2, jdbcType=VARCHAR} ");
        }
        if (model.getAccountChangeType() > 0)
        {
            sql.append("And a.AccountChangeType = #{accountChangeType, jdbcType=INTEGER} ");
        }
        if (model.getMinAmount() != 0)
        {
            sql.append("And a.amount >= #{minAmount, jdbcType=DECIMAL} ");
        }
        if (model.getMaxAmount() != 0)
        {
            sql.append("And a.amount <= #{maxAmount, jdbcType=DECIMAL} ");
        }
        return sql.toString();
    }

    public static String getConsignerAccountSettlementsSql(ConsignerAccountSettlement model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT " + "createTime=a.createTime," + "exacctName=c.ExacctName," + "billNo=a.billNo,"
                + "billId=a.billId," + "orderCode=a.orderCode," + "orderId=a.orderId,"
                + "foreignAmount=a.foreignAmount," + "currencyName=d.Name," + "rate=a.ExchangeRate," + "raeType=e.Text,"
                + "amount=a.amount," + "accountBalance=a.accountBalance," + "companyName=corp.name,"
                + "companyId=corp.id," + "accountType=b.Text," + "sortNum=a.SortNum " + "FROM CompanyAccountRecord a "
                + "LEFT JOIN DictionaryCommon b ON a.AccountType = b.ID "
                + "LEFT JOIN Exacct c ON c.ID = a.CapitalType2 "
                + "LEFT JOIN DictionaryCurrency d ON d.ID = a.CurrencyID "
                + "LEFT JOIN DictionaryCommon e ON e.ID = a.AccountChangeType "
                + "LEFT JOIN Company corp ON corp.ID = a.CompanyID WHERE a.CompanyID=#{companyId,jdbcType=VARCHAR} ");

        if (model.getBeginDate() != null && model.getBeginDate() != "" && model.getEndDate() != null
                && model.getEndDate() != "")
        {
            sql.append(" And a.createTime between #{beginDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR} ");
        }
        if (model.getBillNo() != null && model.getBillNo() != "")
        {
            sql.append("And CHARINDEX(#{billNo, jdbcType=VARCHAR},a.billNo)>0 ");
        }
        if (model.getOrderCode() != null && model.getOrderCode() != "")
        {
            sql.append("And CHARINDEX(#{orderCode, jdbcType=VARCHAR},a.orderCode)>0 ");
        }
        if (model.getCapitalType2() != null && model.getCapitalType2() != "")
        {
            sql.append("And a.capitalType2 = #{capitalType2, jdbcType=VARCHAR} ");
        }
        if (model.getAccountChangeType() > 0)
        {
            sql.append("And a.AccountChangeType = #{accountChangeType, jdbcType=INTEGER} ");
        }
        if (model.getMinAmount() != 0)
        {
            sql.append("And a.amount >= #{minAmount, jdbcType=DECIMAL} ");
        }
        if (model.getMaxAmount() != 0)
        {
            sql.append("And a.amount <= #{maxAmount, jdbcType=DECIMAL} ");
        }

        return sql.toString();
    }

    /* 渠道商台帐 */
    public static String getCommisionCashPoolByPageSql(CashPoolPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT [channel_id],[channel_name],[free_amount],[freeze_amount],[total_amount],[created_by],[created_by_name],[created_date],[updated_by],[updated_by_name],[updated_date] ")
                .append(" FROM [channel_cash_pool] WHERE 0=0");
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
            // sql.append(" And
            // CHARINDEX(#{createdByName,jdbcType=VARCHAR},[created_by_name]) >
            // 0");
        }
        return sql.toString();
    }

    /* 佣金台帐详情 */
    public static String getCommisionAccountStatementsByPageSql(AccountStatementPageable model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT [statement_id],[channel_id],[channel_name],[capacity1_id],[capacity1_name],[capacity2_id],[capacity2_name],[document_id],[document_code],[rae_type],[currency_id],[currency_code],[rate],[amount],[foreign_amount],[order_id],[order_code],[created_by],[created_by_name],[created_date],[business_main_body] ")
                .append(" FROM  [channel_account_statement] WHERE 0=0");
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
        if (model.getChannelId() != null && model.getChannelId() != "")
        {
            sql.append(" And [channel_id] = #{channelId,jdbcType=VARCHAR}");
        }
        return sql.toString();
    }

    public static String getCommisionAccountStatementsSql(AccountStatement model)
    {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT [statement_id],[channel_id],[channel_name],[capacity1_id],[capacity1_name],[capacity2_id],[capacity2_name],[document_id],[document_code],[rae_type],[currency_id],[currency_code],[rate],[amount],[foreign_amount],[order_id],[order_code],[created_by],[created_by_name],[created_date],[business_main_body] ")
                .append(" FROM  [channel_account_statement] WHERE 0=0");
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
        if (model.getChannelId() != null && model.getChannelId() != "")
        {
            sql.append(" And [channel_id] = #{channelId,jdbcType=VARCHAR}");
        }
        return sql.toString();
    }
}
