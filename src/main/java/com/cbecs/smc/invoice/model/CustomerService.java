package com.cbecs.smc.invoice.model;

import java.io.Serializable;

public class CustomerService implements Serializable {

	private static final long serialVersionUID = -5676680612336105978L;

	/**
	 * id
	 */
	public String userId;
	
	/**
	 * 名称
	 */
	public String userName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
