package com.cbecs.scrm.consigner.model;

import java.util.Date;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;
import com.cbecs.framework.mybatis.pagination.Pageable;

@Table(name="bank_account")
public class BankAccountPageable extends Pageable implements java.io.Serializable{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5859320885493193000L;

	@Id(name="account_id")
	private String accountId;
	
	@Column(name="account_no")
	private String accountNo;
	
	@Column(name="sub_account_no")
	private String subAccountNo;
	
	@Column(name="consigner_id")
	private String consignerId;
	
	@Column(name="consigner_name")
	private String consignerName;
	
	@Column(name="status")
	private int status;
	
	@Column(name="business_main_body")
	private int businessMainBody;
	
	@Column(name="bank_id")
	private int bankId;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="deleted")
	private boolean deleted;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_by_name")
	private String createdByName;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_by_name")
	private String updatedByName;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="enaddress")
	private String enaddress;
	
	@Column(name="swift_code")
	private String swiftCode;
	
	@Column(name="bank_enname")
	private String bankEnname;
	
	private String shenQingDate;
	
	private String bodyName;
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	private String creator;
	
	public String getEnaddress() {
		return enaddress;
	}

	public void setEnaddress(String enaddress) {
		this.enaddress = enaddress;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getBankEnname() {
		return bankEnname;
	}

	public void setBankEnname(String bankEnname) {
		this.bankEnname = bankEnname;
	}

	private String beginDate;
    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	private String endDate;
    private String customer;
    @Column(name="account_name")
    private String accountName;
    
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getSubAccountNo() {
		return subAccountNo;
	}

	public void setSubAccountNo(String subAccountNo) {
		this.subAccountNo = subAccountNo;
	}

	public String getConsignerId() {
		return consignerId;
	}

	public void setConsignerId(String consignerId) {
		this.consignerId = consignerId;
	}

	public String getConsignerName() {
		return consignerName;
	}

	public void setConsignerName(String consignerName) {
		this.consignerName = consignerName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getBusinessMainBody() {
		return businessMainBody;
	}

	public void setBusinessMainBody(int businessMainBody) {
		this.businessMainBody = businessMainBody;
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getShenQingDate() {
		return shenQingDate;
	}

	public void setShenQingDate(String shenQingDate) {
		this.shenQingDate = shenQingDate;
	}

	public String getBodyName() {
		return bodyName;
	}

	public void setBodyName(String bodyName) {
		this.bodyName = bodyName;
	}
}
