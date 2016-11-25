package com.cbecs.scrm.consigner.model;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Table;

@Table(name="bank")
public class Bank implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 351390023609071337L;
	
	@Column(name="bank_id")
	private int bankId;
	
	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="bank_abbr")
	private String bankAbbr;
	
	@Column(name="swift_code")
	private String swiftCode;
	
	@Column(name="address")
	private String address;
	
	@Column(name="enaddress")
	private String enaddress;

	public String getBankAbbr() {
		return bankAbbr;
	}

	public void setBankAbbr(String bankAbbr) {
		this.bankAbbr = bankAbbr;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnaddress() {
		return enaddress;
	}

	public void setEnaddress(String enaddress) {
		this.enaddress = enaddress;
	}
	
}
