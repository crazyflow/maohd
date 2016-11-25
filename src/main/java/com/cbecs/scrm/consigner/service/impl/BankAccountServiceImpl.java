package com.cbecs.scrm.consigner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbecs.scrm.consigner.model.Bank;
import com.cbecs.scrm.consigner.model.BankAccount;
import com.cbecs.scrm.consigner.model.BankAccountPageable;
import com.cbecs.scrm.consigner.model.BusinessBody;
import com.cbecs.scrm.consigner.model.BusinessBodyAccount;
import com.cbecs.scrm.consigner.persistence.inf.BankAccountMapper;
import com.cbecs.scrm.consigner.service.inf.BankAccountService;

@Service
class BankAccountServiceImpl implements BankAccountService{
	@Autowired
	private BankAccountMapper bankAccountMapper;

	@Override
	public String getConsignerId() {
		StringBuilder res = new StringBuilder();
		List<BankAccount> Consigner = bankAccountMapper.getConsigner();
		for(BankAccount bankAccount:Consigner ){
			res.append("<option >"+bankAccount.getConsignerName()+"</option>"); 
		}
		return res.toString();
	}

	@Override
	public void savedata(BankAccount model) {
		
		 bankAccountMapper.savedata(model);
	}

	

	@Override
	public List<BankAccountPageable> tabel(BankAccountPageable model) {
		return bankAccountMapper.table(model);
	}

	@Override
	public List<BankAccountPageable> getConsignerName(String accountId) {
		
		return bankAccountMapper.getconsignername(accountId);
	}

	

	

	@Transactional
	@Override
	public BankAccount getall(BankAccount model) {
		
		return bankAccountMapper.getall(model);
	}

	@Override
	public List<BankAccount> getConsignerlist(String congsignerName) {
		
		return bankAccountMapper.getconsignerlist(congsignerName);
	}

	@Override
	public List<Bank> getbanklist() {
		
		return bankAccountMapper.getbanklist();
	}

	@Override
	public List<BusinessBody> getbussinessbody(int bankId) {
		
		return bankAccountMapper.getbusiness(bankId);
	}

	@Override
	public List<BusinessBodyAccount> getaccountno(int bankId, int bodyId) {
		
		return bankAccountMapper.getaccountno(bankId, bodyId);
	}

	@Override
	public String getBank() {
		StringBuilder res = new StringBuilder();
		List<Bank> banklist = bankAccountMapper.getBank();
		for(Bank bank:banklist ){
			res.append("<option value='"+bank.getBankName()+"'>"+bank.getBankName()+"</option>"); 
		}
		return res.toString();
	}

	@Override
	public String getAccountName() {
		StringBuilder res = new StringBuilder();
		List<BankAccount> accountName= bankAccountMapper.getAccountName();
		for(BankAccount bankAccount:accountName ){
			res.append("<option >"+bankAccount.getAccountName()+"</option>"); 
		}
		return res.toString();
		
	}

	

	

	@Override
	public List<BankAccount> getCg(int bodyId,String userId) {
		
		return bankAccountMapper.getCg(bodyId,userId);
	}

	@Override
	public BankAccount getEnname(String consignerId) {
		
		return bankAccountMapper.getEnname(consignerId);
	}

	@Override
	public String getcustomer() {
		StringBuilder res = new StringBuilder();
		List<BankAccount> customer = bankAccountMapper.getcustomer();
		for(BankAccount bankAccount : customer){
			res.append("<option value='"+bankAccount.getCustomer()+"'>"+bankAccount.getCustomer()+"</option>"); 
		}
		return res.toString();
	}

	@Override
	public String getcreatedByName() {
		StringBuilder res = new StringBuilder();
		List<BankAccount> created = bankAccountMapper.getcreatedByName();
		for(BankAccount bankAccount : created){
			res.append("<option value='"+bankAccount.getCreatedByName()+"'>"+bankAccount.getCreatedByName()+"</option>"); 
		}
		return res.toString();
	}

	@Override
	public BusinessBodyAccount getenabbr(int bodyId,String accountNo) {
		
		return bankAccountMapper.getenabbr(bodyId,accountNo);
	}

	@Override
	public List<BankAccount> validate(String validate) {
		
		return bankAccountMapper.validate(validate);
	}

	@Override
	public List<BankAccount> consignerexists(String consignerId, int bankId, String accountNo) {
		
		return bankAccountMapper.consignerexists(consignerId,bankId,accountNo);
	}

	@Override
	public String getyeuzhuti() {
		StringBuilder res = new StringBuilder();
		List<BusinessBody> yewuzhuti = bankAccountMapper.getyewuzhuti();
		for(BusinessBody businessBody : yewuzhuti){
			res.append("<option value='"+businessBody.getBodyId()+"'>"+businessBody.getBodyName()+"</option>"); 
		}
		return res.toString();
	}

	

	

	

	
	
	

}
