package com.cbecs.scrm.channel.service.inf;

import java.util.List;

import com.cbecs.scrm.channel.model.ConsignerAccountSettlement;
import com.cbecs.scrm.channel.model.ConsignerAccountSettlementPageable;
import com.cbecs.scrm.channel.model.ConsignerCashPool;
import com.cbecs.scrm.channel.model.ConsignerCashPoolPageable;
import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.AccountStatementPageable;
import com.cbecs.smc.commision.model.CashPool;

public interface SettlementManagermentService{

    List<ConsignerCashPool> getConsignerCashPoolsByPage(ConsignerCashPoolPageable p);

    ConsignerCashPool getConsignerCashPoolByCompanyId(String companyId);

    List<ConsignerAccountSettlement> getConsignerAccountSettlementsByPage(ConsignerAccountSettlementPageable p);

    List<ConsignerAccountSettlement> getConsignerAccountSettlements(ConsignerAccountSettlement p);

    CashPool getCommisionCashPoolById(String channelId);

    List<AccountStatementPageable> getCommisionAccountStatementsByPage(AccountStatementPageable p);

    List<AccountStatement> getCommisionAccountStatements(AccountStatement p);
    
} 