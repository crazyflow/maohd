package com.cbecs.smc.commision.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.AccountStatementPageable;
import com.cbecs.smc.commision.persistence.SelectSqlProvider;


public interface AccountStatementMapper {
    
    @Insert("INSERT INTO [channel_account_statement] ([statement_id],[channel_id],[channel_name],[business_main_body],[capacity1_id],[capacity1_name],[capacity2_id],[capacity2_name],[document_id],[document_code],[rae_type],[currency_id],[currency_code],[rate],[amount],[foreign_amount],[order_id],[order_code],[created_by],[created_by_name],[created_date]) VALUES (#{statementId,jdbcType=CHAR}, #{channelId,jdbcType=CHAR}, #{channelName,jdbcType=VARCHAR}, #{businessMainBody,jdbcType=INTEGER},#{capacity1Id,jdbcType=CHAR}, #{capacity1Name,jdbcType=VARCHAR}, #{capacity2Id,jdbcType=CHAR}, #{capacity2Name,jdbcType=VARCHAR}, #{documentId,jdbcType=CHAR}, #{documentCode,jdbcType=VARCHAR}, #{raeType,jdbcType=INTEGER}, #{currencyId,jdbcType=INTEGER}, #{currencyCode,jdbcType=CHAR}, #{rate,jdbcType=DECIMAL}, #{amount,jdbcType=DECIMAL}, #{foreignAmount,jdbcType=DECIMAL}, #{orderId,jdbcType=CHAR}, #{orderCode,jdbcType=VARCHAR}, #{createdBy,jdbcType=CHAR}, #{createdByName,jdbcType=VARCHAR}, #{createdDate,jdbcType=TIMESTAMP})")
    void insert(AccountStatement model);
    
    @Update("UPDATE [channel_account_statement] SET [channel_id]=#{channelId,jdbcType=CHAR}, [channel_name]=#{channelName,jdbcType=VARCHAR}, [capacity1_id]=#{capacity1Id,jdbcType=CHAR}, [capacity1_name]=#{capacity1Name,jdbcType=VARCHAR}, [capacity2_id]=#{capacity2Id,jdbcType=CHAR}, [capacity2_name]=#{capacity2Name,jdbcType=VARCHAR}, [document_id]=#{documentId,jdbcType=CHAR}, [document_code]=#{documentCode,jdbcType=VARCHAR}, [rae_type]=#{raeType,jdbcType=INTEGER}, [currency_id]=#{currencyId,jdbcType=INTEGER}, [currency_code]=#{currencyCode,jdbcType=CHAR}, [rate]=#{rate,jdbcType=DECIMAL}, [amount]=#{amount,jdbcType=DECIMAL}, [foreign_amount]=#{foreignAmount,jdbcType=DECIMAL}, [order_id]=#{orderId,jdbcType=CHAR}, [order_code]=#{orderCode,jdbcType=VARCHAR}, [created_by]=#{createdBy,jdbcType=CHAR}, [created_by_name]=#{createdByName,jdbcType=VARCHAR}, [created_date]=#{createdDate,jdbcType=TIMESTAMP} WHERE [statement_id]=#{statementId,jdbcType=CHAR}")
    void update(AccountStatement model);

    @SelectProvider(method = "getAccountStatementListByPageSql", type = SelectSqlProvider.class)
    List<AccountStatement> getListByPage(AccountStatementPageable p);

    @SelectProvider(method = "getAccountStatementListSql", type = SelectSqlProvider.class)
    List<AccountStatement> getList(AccountStatement p);
    
    /**
     * 根据documentId删除[channel_account_statement]表信息
     * @param documentId
     */
    @Delete("DELETE [channel_account_statement] WHERE [document_id] = #{documentId}")
    void deleteAccountStatement(String documentId);

    @Update("UPDATE [channel_account_statement] SET [rae_type]=#{raeType,jdbcType=INTEGER} WHERE [document_id]=#{documentId,jdbcType=CHAR}")
    void updateAccountStatementByAudit(AccountStatement accountStatement);
    
}