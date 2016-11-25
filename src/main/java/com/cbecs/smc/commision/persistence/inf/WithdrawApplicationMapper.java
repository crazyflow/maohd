package com.cbecs.smc.commision.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.cbecs.smc.commision.model.WithdrawApplication;
import com.cbecs.smc.commision.model.WithdrawApplicationPageable;
import com.cbecs.smc.commision.persistence.SelectSqlProvider;


public interface WithdrawApplicationMapper
{
    @Update("UPDATE [commision_withdraw_application] SET [application_id]=#{applicationId,jdbcType=CHAR}, [application_code]=#{applicationCode,jdbcType=VARCHAR}, [application_date]=#{applicationDate,jdbcType=TIMESTAMP}, [status]=#{status,jdbcType=INTEGER}, [channel_id]=#{channelId,jdbcType=CHAR}, [channel_name]=#{channelName,jdbcType=VARCHAR}, [business_main_body]=#{businessMainBody,jdbcType=INTEGER}, [beneficiary_name]=#{beneficiaryName,jdbcType=VARCHAR}, [beneficiary_bank]=#{beneficiaryBank,jdbcType=VARCHAR}, [beneficiary_account_no]=#{beneficiaryAccountNo,jdbcType=VARCHAR}, [currency]=#{currency,jdbcType=CHAR}, [selling_rate]=#{sellingRate,jdbcType=DECIMAL}, [total_amount]=#{totalAmount,jdbcType=DECIMAL}, [total_foreign_amount]=#{totalForeignAmount,jdbcType=DECIMAL}, [remark]=#{remark,jdbcType=VARCHAR}, [deleted]=#{deleted,jdbcType=INTEGER}, [created_by]=#{createdBy,jdbcType=CHAR}, [created_by_name]=#{createdByName,jdbcType=VARCHAR}, [created_date]=#{createdDate,jdbcType=TIMESTAMP}, [updated_by]=#{updatedBy,jdbcType=CHAR}, [updated_by_name]=#{updatedByName,jdbcType=TIMESTAMP}, [updated_date]=#{updatedDate,jdbcType=TIMESTAMP} ")
    void update(WithdrawApplication model);

    @Select("SELECT [application_id],[application_code],[application_date],[status],[workflow_id],[channel_id],[channel_name],[business_main_body],[beneficiary_name],[beneficiary_bank],[beneficiary_account_no],[currency],[selling_rate],[total_amount],[total_foreign_amount],[remark],[deleted],[created_by],[created_by_name],[created_date],[updated_by],[updated_by_name],[updated_date] FROM [commision_withdraw_application] ")
    WithdrawApplication select();

    @Select("SELECT [application_id],[application_code],[application_date],[status],[workflow_id],[channel_id],[channel_name],[business_main_body],[beneficiary_name],[beneficiary_bank],[beneficiary_account_no],[currency],[selling_rate],[total_amount],[total_foreign_amount],[remark],[deleted],[created_by],[created_by_name],[created_date],[updated_by],[updated_by_name],[updated_date] FROM [commision_withdraw_application]")
    List<WithdrawApplication> selectList(WithdrawApplication model);

    @Update("UPDATE [commision_withdraw_application] SET [deleted] = #{deleted,jdbcType=BIT}, [last_updated_date] = #{lastUpdatedDate,jdbcType=TIMESTAMP}")
    void delete(WithdrawApplication model);

    @SelectProvider(method = "getSelectCommisionWithdrawListByPageSql", type = SelectSqlProvider.class)
    List<WithdrawApplication> selectListByPage(WithdrawApplicationPageable model);

    @Select("SELECT [RateBuyOut] FROM [DailyRates]  WHERE IsDelete=0 AND [CurrencyId] = #{currencyId,jdbcType=INTEGER} AND [RateDate]= convert(varchar(10),getdate(),120) AND [RateType] = 2301")
    double getCurrencyDailyRate(int currencyId);

    @Insert("INSERT INTO [commision_withdraw_application] ([application_id],[application_code],[application_date],[status],[workflow_id],[channel_id],[channel_name],[business_main_body],[beneficiary_name],[beneficiary_bank],[beneficiary_account_no],[currency_id],[currency_code],[selling_rate],[total_amount],[total_foreign_amount],[remark],[deleted],[created_by],[created_by_name],[created_date],[updated_by],[updated_by_name],[updated_date]) VALUES (#{applicationId,jdbcType=CHAR}, #{applicationCode,jdbcType=VARCHAR}, #{applicationDate,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER},#{workFlowId,jdbcType=INTEGER}, #{channelId,jdbcType=CHAR}, #{channelName,jdbcType=VARCHAR}, #{businessMainBody,jdbcType=INTEGER}, #{beneficiaryName,jdbcType=VARCHAR}, #{beneficiaryBank,jdbcType=VARCHAR}, #{beneficiaryAccountNo,jdbcType=VARCHAR}, #{currencyId,jdbcType=INTEGER}, #{currencyCode,jdbcType=CHAR}, #{sellingRate,jdbcType=DECIMAL}, #{totalAmount,jdbcType=DECIMAL}, #{totalForeignAmount,jdbcType=DECIMAL}, #{remark,jdbcType=VARCHAR}, #{deleted,jdbcType=INTEGER}, #{createdBy,jdbcType=CHAR}, #{createdByName,jdbcType=VARCHAR}, #{createdDate,jdbcType=TIMESTAMP}, #{updatedBy,jdbcType=CHAR}, #{updatedByName,jdbcType=VARCHAR}, #{updatedDate,jdbcType=TIMESTAMP})")
    void insertWithdrawApplication(WithdrawApplication model);

    @Select("SELECT [application_id],[application_code],[application_date],[status],[workflow_id],[channel_id],[channel_name],[business_main_body],[beneficiary_name],[beneficiary_bank],[beneficiary_account_no],[currency_id],[currency_code],[selling_rate],[total_amount],[total_foreign_amount],[remark],[deleted],[created_by],[created_by_name],[created_date],[updated_by],[updated_by_name],[updated_date] FROM [commision_withdraw_application] WHERE [application_id]=#{applicationId,jdbcType=CHAR}")
    WithdrawApplication selectwithdrawById(String applicationId);

    @Update("UPDATE [commision_withdraw_application] SET [deleted] = #{deleted,jdbcType=BIT}  WHERE [application_id]=#{applicationId,jdbcType=CHAR}")
    void deleteWithdrawApplication(WithdrawApplication model);

    @Update("UPDATE [commision_withdraw_application] SET [application_date]=#{applicationDate,jdbcType=TIMESTAMP}, [status]=#{status,jdbcType=INTEGER}, [workflow_id]=#{workFlowId,jdbcType=INTEGER}, [channel_id]=#{channelId,jdbcType=CHAR}, [channel_name]=#{channelName,jdbcType=VARCHAR}, [business_main_body]=#{businessMainBody,jdbcType=INTEGER}, [beneficiary_name]=#{beneficiaryName,jdbcType=VARCHAR}, [beneficiary_bank]=#{beneficiaryBank,jdbcType=VARCHAR}, [beneficiary_account_no]=#{beneficiaryAccountNo,jdbcType=VARCHAR}, [currency_id]=#{currencyId,jdbcType=INTEGER}, [currency_code]=#{currencyCode,jdbcType=CHAR}, [selling_rate]=#{sellingRate,jdbcType=DECIMAL}, [total_amount]=#{totalAmount,jdbcType=DECIMAL}, [total_foreign_amount]=#{totalForeignAmount,jdbcType=DECIMAL}, [remark]=#{remark,jdbcType=VARCHAR}, [deleted]=#{deleted,jdbcType=INTEGER},[updated_by]=#{updatedBy,jdbcType=CHAR}, [updated_by_name]=#{updatedByName,jdbcType=VARCHAR}, [updated_date]=#{updatedDate,jdbcType=TIMESTAMP} WHERE [application_id]=#{applicationId,jdbcType=CHAR}")
    void updateWithdrawApplication(WithdrawApplication model);
    
    @Update("UPDATE [commision_withdraw_application] SET [status]=#{status,jdbcType=INTEGER},[updated_by]=#{updatedBy,jdbcType=CHAR}, [updated_by_name]=#{updatedByName,jdbcType=VARCHAR}, [updated_date]=#{updatedDate,jdbcType=TIMESTAMP} WHERE [application_id]=#{applicationId,jdbcType=CHAR}")
    void updateWithdrawApplicationWithStatus(WithdrawApplication model);

    @SelectProvider(method = "getSequenceNo", type = SelectSqlProvider.class)
    String getApplicationCode(String preFix);

    @SelectProvider(method = "getStatusInfo", type = SelectSqlProvider.class)
    String getStatusInfo(String workFlowIds);

    @SelectProvider(method = "getHistoryAccounts", type = SelectSqlProvider.class)
    List<WithdrawApplication> getHistoryAccounts(WithdrawApplication w);
    
}