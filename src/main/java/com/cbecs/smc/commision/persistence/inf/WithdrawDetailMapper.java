package com.cbecs.smc.commision.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cbecs.smc.commision.model.WithdrawDetail;


public interface WithdrawDetailMapper {
    
    @Insert("INSERT INTO [commision_withdraw_detail] ([detail_id],[application_id],[settlement_application_id],[settlement_application_code],[commision_amount],[withdrawn_amount],[currency_id],[currency_code],[amount],[foreign_amount]) VALUES (#{detailId,jdbcType=CHAR}, #{applicationId,jdbcType=CHAR}, #{settlementApplicationId,jdbcType=CHAR}, #{settlementApplicationCode,jdbcType=VARCHAR}, #{commisionAmount,jdbcType=DECIMAL}, #{withdrawnAmount,jdbcType=DECIMAL}, #{currencyId,jdbcType=INTEGER}, #{currencyCode,jdbcType=CHAR}, #{amount,jdbcType=DECIMAL}, #{foreignAmount,jdbcType=DECIMAL})")
    void insertWithdrawDetail(WithdrawDetail withdrawDetail);
    
    @Update("UPDATE [commision_withdraw_detail] SET [detail_id]=#{detailId,jdbcType=CHAR}, [application_id]=#{applicationId,jdbcType=CHAR}, [settlement_application_id]=#{settlementApplicationId,jdbcType=CHAR}, [settlement_application_code]=#{settlementApplicationCode,jdbcType=VARCHAR}, [commision_amount]=#{commisionAmount,jdbcType=DECIMAL}, [withdrawn_amount]=#{withdrawnAmount,jdbcType=DECIMAL}, [currency]=#{currency,jdbcType=CHAR}, [amount]=#{amount,jdbcType=DECIMAL}, [foreign_amount]=#{foreignAmount,jdbcType=DECIMAL} ")
    void update(WithdrawDetail model);

    @Select("SELECT [detail_id],[application_id],[settlement_application_id],[settlement_application_code],[commision_amount],[withdrawn_amount],[currency],[amount],[foreign_amount] FROM [commision_withdraw_detail] ")
    WithdrawDetail select();
    
    @Select("SELECT [detail_id],[application_id],[settlement_application_id],[settlement_application_code],[commision_amount],[withdrawn_amount],[currency],[amount],[foreign_amount] FROM [commision_withdraw_detail]")
    List<WithdrawDetail> selectList(WithdrawDetail model);

    @Update("UPDATE [commision_withdraw_detail] SET [deleted] = #{deleted,jdbcType=BIT}, [last_updated_date] = #{lastUpdatedDate,jdbcType=TIMESTAMP}")
    void delete(WithdrawDetail model);

    @Select("SELECT [detail_id],[application_id],[settlement_application_id],[settlement_application_code],[commision_amount],[withdrawn_amount],[currency_id],[currency_code],[amount],[foreign_amount] FROM [commision_withdraw_detail] WHERE [application_id]=#{applicationId,jdbcType=CHAR}")
    List<WithdrawDetail> selectWithdrawDetailById(String applicationId);

    @Update("UPDATE [commision_withdraw_detail] SET [settlement_application_id]=#{settlementApplicationId,jdbcType=CHAR}, [settlement_application_code]=#{settlementApplicationCode,jdbcType=VARCHAR}, [commision_amount]=#{commisionAmount,jdbcType=DECIMAL}, [withdrawn_amount]=#{withdrawnAmount,jdbcType=DECIMAL}, [currency_id]=#{currencyId,jdbcType=INTEGER}, [currency_code]=#{currencyCode,jdbcType=CHAR}, [amount]=#{amount,jdbcType=DECIMAL}, [foreign_amount]=#{foreignAmount,jdbcType=DECIMAL} WHERE [detail_id]=#{detailId,jdbcType=CHAR}")
    void updateWithdrawDetail(WithdrawDetail withdrawDetail);

    @Delete("DELETE FROM [commision_withdraw_detail] WHERE [application_id]=#{applicationId,jdbcType=CHAR} ")
    void deleteWithdrawDetail(String applicationId);
    
}