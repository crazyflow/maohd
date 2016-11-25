package com.cbecs.smc.commision.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.SettlementDetail;

public interface SettlementDetailMapper
{

    /**
     * 插入结算明细数据
     * 
     * @param model
     */
    @Insert(" INSERT INTO [commision_settlement_detail] " + " ([detail_id],[application_id],[order_id],[order_code], "
            + " [entrusting_id],[entrusting_name], " + " [financial_product], "
            + " [cost_amount],[income_amount],[commision_amount])  "
            + " VALUES (#{detailId}, #{applicationId},#{orderId},#{orderCode}, "
            + " #{entrustingId},#{entrustingName}, " + " #{financialProduct}, "
            + " #{costAmount},#{incomeAmount},#{commisionAmount}) ")
    void insertSettlementDetails(SettlementDetail model);

    /**
     * 根据applicaitonid删除该结算单明细信息
     * 
     * @param model
     * @return
     */
    @Delete("DELETE [commision_settlement_detail] WHERE [application_id] = #{value}")
    int deleteSettlementDetails(String applicationId);

    /**
     * 根据applicaitonId查询结算申请单详细信息
     * 
     * @param model
     * @return
     */
    @Select("SELECT [detail_id],[application_id], " + " [order_id],[order_code], "
            + " [entrusting_id],[entrusting_name],[financial_product], "
            + " [cost_amount],[income_amount],[commision_amount] " + " FROM [commision_settlement_detail] "
            + " WHERE [application_id] = #{applicationId} ORDER BY [order_code] desc,[financial_product]")
    List<SettlementDetail> selectSettlementDetailByApplicationId(String applicationId);

    /**
     * 根据orderCode查看SettlementDetail是否已经存在,且结算单的状态不为草稿
     * 
     * @param model
     * @return
     */
    @Select(" select count(0) from commision_settlement_detail csd "+
        " inner join commision_settlement_application csa on csa.application_id = csd.application_id  "+
        " where csa.status != 1 and csd.order_code = #{0} and csd.application_id != #{1} ")
    int selectCountByOrderCode(String orderCode,String applicationId);

    /**
     * 根据applicaitonId查询订单的台账流水信息
     * 
     * @param applicationId
     * @return
     */
    @Select("  SELECT order_id,order_code,sum(commision_amount)  'Amount',sum(commision_amount) 'ForeignAmount' "+
                 " FROM commision_settlement_detail "+ 
                 " WHERE application_id = #{value} "+
                 " GROUP BY order_id,order_code")
    List<AccountStatement> selectStatementByApplicationId(String applicationId);

}