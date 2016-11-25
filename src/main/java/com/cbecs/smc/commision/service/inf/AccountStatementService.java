package com.cbecs.smc.commision.service.inf;
import java.util.List;

import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.AccountStatementPageable;

public interface AccountStatementService
{
    void create(AccountStatement model);
    
    void edit(AccountStatement model);

    List<AccountStatement> getListByPage(AccountStatementPageable p);

    List<AccountStatement> getList(AccountStatement p);
}
