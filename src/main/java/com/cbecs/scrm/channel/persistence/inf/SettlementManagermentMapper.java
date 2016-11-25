package com.cbecs.scrm.channel.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.cbecs.scrm.channel.model.ConsignerAccountSettlement;
import com.cbecs.scrm.channel.model.ConsignerAccountSettlementPageable;
import com.cbecs.scrm.channel.model.ConsignerCashPool;
import com.cbecs.scrm.channel.model.ConsignerCashPoolPageable;
import com.cbecs.scrm.channel.persistence.SelectSqlProvider;
import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.AccountStatementPageable;
import com.cbecs.smc.commision.model.CashPool;

public interface SettlementManagermentMapper
{
    @SelectProvider(method = "getConsignerCashPoolsByPageSql", type = SelectSqlProvider.class)
    List<ConsignerCashPool> getConsignerCashPoolsByPage(ConsignerCashPoolPageable model);

    @SelectProvider(method = "getConsignerAccountSettlementsByPageSql", type = SelectSqlProvider.class)
    List<ConsignerAccountSettlement> getConsignerAccountSettlementsByPage(ConsignerAccountSettlementPageable p);
    
    @SelectProvider(method = "getConsignerAccountSettlementsSql", type = SelectSqlProvider.class)
    List<ConsignerAccountSettlement> getConsignerAccountSettlements(ConsignerAccountSettlement p);
    
    @SelectProvider(method = "getConsignerCashPoolsByCompanyId", type = SelectSqlProvider.class)
    ConsignerCashPool getConsignerCashPoolByCompanyId(String companyId);

    @Select("SELECT * FROM [channel_cash_pool] WHERE [channel_id] = #{channelId}")
    CashPool getCommisionCashPoolById(String channelId);

    @SelectProvider(method = "getCommisionAccountStatementsByPageSql", type = SelectSqlProvider.class)
    List<AccountStatementPageable> getCommisionAccountStatementsByPage(AccountStatementPageable p);
    
    @SelectProvider(method = "getCommisionAccountStatementsSql", type = SelectSqlProvider.class)
    List<AccountStatement> getCommisionAccountStatements(AccountStatement p);
    
}