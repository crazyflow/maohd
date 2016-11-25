package com.cbecs.scrm.consigner.model;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Table;

@Table(name="business_body_account")
public class BusinessBodyAccount implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6188733081018034558L;
	
	
	@Column(name="body_id")
	private int bodyId;
	
	@Column(name="bank_id")
	private int bankId;
	
	@Column(name="account_no")
	private String accountNo;
	
	@Column(name="account_name")
	private String accountName;
	
	@Column(name="account_abbr")
	private String accountAbbr;
	
	@Column(name="account_enname")
	private String accountEnname;
	
	@Column(name="account_enabbr")
	private String accountEnabbr;
	
	@Column(name="curreny")
	private String curreny;
	
	@Column(name="sub_account_opened")
	private boolean subAccountOpened;
	
	public int getBodyId() {
		return bodyId;
	}

	public void setBodyId(int bodyId) {
		this.bodyId = bodyId;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountAbbr() {
		return accountAbbr;
	}

	public void setAccountAbbr(String accountAbbr) {
		this.accountAbbr = accountAbbr;
	}

	public String getAccountEnname() {
		return accountEnname;
	}

	public void setAccountEnname(String accountEnname) {
		this.accountEnname = accountEnname;
	}

	public String getAccountEnabbr() {
		return accountEnabbr;
	}

	public void setAccountEnabbr(String accountEnabbr) {
		this.accountEnabbr = accountEnabbr;
	}

	public String getCurreny() {
		return curreny;
	}

	public void setCurreny(String curreny) {
		this.curreny = curreny;
	}

	public boolean isSubAccountOpened() {
		return subAccountOpened;
	}

	public void setSubAccountOpened(boolean subAccountOpened) {
		this.subAccountOpened = subAccountOpened;
	}

	public int getSubAccountLength() {
		return subAccountLength;
	}

	public void setSubAccountLength(int subAccountLength) {
		this.subAccountLength = subAccountLength;
	}

	public int getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(int appreciation) {
		this.appreciation = appreciation;
	}

	@Column(name="sub_account_length")
	private int subAccountLength;
	
	@Column(name="appreciation")
	private int appreciation;

}
