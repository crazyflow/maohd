package com.cbecs.scrm.consigner.model;

import com.cbecs.framework.mybatis.annotations.Column;

public class BusinessBody implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4808683013446664357L;
	
	public int getBodyId() {
		return bodyId;
	}

	public void setBodyId(int bodyId) {
		this.bodyId = bodyId;
	}

	public String getBodyName() {
		return bodyName;
	}

	public void setBodyName(String bodyName) {
		this.bodyName = bodyName;
	}

	@Column(name="body_id")
	private int bodyId;
	
	@Column(name="body_name")
	private String bodyName;
	
	@Column(name="account_no")
	private String accountNO;
	
	@Column(name="account_name")
	private String accountName;
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNO() {
		return accountNO;
	}

	public void setAccountNO(String accountNO) {
		this.accountNO = accountNO;
	}
}
