package com.cbecs.smc.commision.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.cbecs.smc.commision.model.CashPool;
import com.cbecs.smc.commision.model.CashPoolPageable;
import com.cbecs.smc.commision.model.SettlementApplication;
import com.cbecs.smc.commision.model.WithdrawApplication;
import com.cbecs.smc.commision.persistence.SelectSqlProvider;

public interface CashPoolMapper
{

    @Insert("INSERT INTO [channel_cash_pool] ([channel_id],[channel_name],[free_amount],[freeze_amount],[total_amount],[business_main_body],[created_by],[created_by_name],[created_date],[updated_by],[updated_by_name],[updated_date]) VALUES (#{channelId,jdbcType=CHAR}, #{channelName,jdbcType=VARCHAR}, #{freeAmount,jdbcType=DECIMAL}, #{freezeAmount,jdbcType=DECIMAL}, #{totalAmount,jdbcType=DECIMAL}, #{businessMainBody},#{createdBy,jdbcType=CHAR}, #{createdByName,jdbcType=VARCHAR}, #{createdDate,jdbcType=TIMESTAMP}, #{updatedBy,jdbcType=CHAR}, #{updatedByName,jdbcType=VARCHAR}, #{updatedDate,jdbcType=TIMESTAMP})")
    void insert(CashPool model);

    @SelectProvider(method = "getCashPoolListByPageSql", type = SelectSqlProvider.class)
    List<CashPoolPageable> selectListByPage(CashPoolPageable p);

    @Update("UPDATE [channel_cash_pool] SET [free_amount]=[free_amount]-#{totalAmount,jdbcType=DECIMAL}, [freeze_amount]=[freeze_amount]+#{totalAmount,jdbcType=DECIMAL}, [updated_date]=#{updatedDate,jdbcType=TIMESTAMP} WHERE [channel_id]=#{channelId,jdbcType=CHAR}")
    void updateCashPoolBywithDraw(WithdrawApplication model);

    /**
     * 更改佣金台账自由佣金池及总佣金池
     * 
     * @return
     */
    @Update("UPDATE [channel_cash_pool] " + " SET "
            + " [free_amount]= (#{freeAmount}+(SELECT [free_amount] FROM [channel_cash_pool] WHERE [channel_id]=#{channelId,jdbcType=CHAR})), "
            + " [total_amount]= (#{totalAmount}+(SELECT [total_amount] FROM [channel_cash_pool] WHERE [channel_id]=#{channelId,jdbcType=CHAR})), "
            + " [updated_by]=#{updatedBy,jdbcType=CHAR}, " + " [updated_by_name]=#{updatedByName,jdbcType=VARCHAR}, "
            + " [updated_date]=#{updatedDate,jdbcType=TIMESTAMP} " + " WHERE [channel_id]=#{channelId,jdbcType=CHAR}")
    int updateCashPoolFree(CashPool model);

    /**
     * 根据channel获取当前渠道商的佣金池信息
     * 
     * @return
     */
    @Update("UPDATE [channel_cash_pool] " + " SET " + "[free_amount]= ([free_amount] - #{totalCommissionAmount}), "
            + "[total_amount]= ([total_amount] - #{totalCommissionAmount}), " + "[updated_by], " + "[updated_by_name], "
            + "[updated_date] " + "FROM [channel_cash_pool] WHERE [channel_id] = #{channelId}")
    void UpdateCashPoolBySettlementApplication(SettlementApplication settlementApplication);

    @Update("UPDATE [channel_cash_pool] SET [freeze_amount]=[freeze_amount]-#{totalAmount,jdbcType=DECIMAL}, [total_amount]=[total_amount]-#{totalAmount,jdbcType=DECIMAL}, [updated_date]=#{updatedDate,jdbcType=TIMESTAMP} WHERE [channel_id]=#{channelId,jdbcType=CHAR}")
    void updateCashPoolByAudit(WithdrawApplication withdrawApplication);

    @Update("UPDATE [channel_cash_pool] SET [free_amount]=[free_amount]+#{totalAmount,jdbcType=DECIMAL}, [freeze_amount]=[freeze_amount]-#{totalAmount,jdbcType=DECIMAL}, [updated_date]=#{updatedDate,jdbcType=TIMESTAMP} WHERE [channel_id]=#{channelId,jdbcType=CHAR}")
    void updateCashPoolByAuditNo(WithdrawApplication withdrawApplication);

    @Select("SELECT * FROM [channel_cash_pool] WHERE [channel_id] = #{channelId}")
    CashPool getCashPoolById(String channelId);

}