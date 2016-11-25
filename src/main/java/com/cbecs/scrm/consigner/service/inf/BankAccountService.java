package com.cbecs.scrm.consigner.service.inf;

import java.util.List;

import com.cbecs.scrm.consigner.model.Bank;
import com.cbecs.scrm.consigner.model.BankAccount;
import com.cbecs.scrm.consigner.model.BankAccountPageable;
import com.cbecs.scrm.consigner.model.BusinessBody;
import com.cbecs.scrm.consigner.model.BusinessBodyAccount;

public interface BankAccountService {
	String getConsignerId();
	String getBank();
	String getyeuzhuti();
	BankAccount getEnname(String consignerId);
	BusinessBodyAccount getenabbr(int bodyId,String accountNo);
	List<BankAccount> getCg(int bodyId,String userId);
	String getcustomer();
	String getcreatedByName();
	String getAccountName();
	List<BankAccount> validate(String validate);
	List<Bank> getbanklist();
	List<BusinessBody> getbussinessbody(int bankId);
	List<BusinessBodyAccount> getaccountno(int bankId,int bodyId);
	List<BankAccountPageable> getConsignerName(String accountId);
	List<BankAccountPageable> tabel(BankAccountPageable model);
	BankAccount getall(BankAccount model);
	List<BankAccount> consignerexists(String consignerId,int bankId,String accountNo);
	List<BankAccount> getConsignerlist(String congsignerName);
	void savedata(BankAccount model);
	
	
}
