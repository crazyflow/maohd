package com.cbecs.scrm.consigner.persistence;

import com.cbecs.scrm.consigner.model.BankAccountPageable;

public class SelectSqlProvider {
	 
	 public static String getallbysql(BankAccountPageable model){
		 StringBuilder sql = new StringBuilder();
		 sql.append("select ba.*,M.name as customer,bb.body_name as bodyName from bank_account ba left join Company cm ON cm.ID=ba.consigner_id left join ManagerUser M ON M.ID=cm.CustomerService left join business_body bb on bb.body_id=ba.business_main_body where ba.deleted=0  ");
		 if(model.getConsignerName() != null && model.getConsignerName() != ""){
			 sql.append("AND CHARINDEX(#{consignerName,jdbcType=VARCHAR},ba.consigner_name) > 0 ");
		 };
		 if(model.getBankName()!=null && model.getBankName()!=""){
			 sql.append("and ba.bank_name = #{bankName,jdbcType=VARCHAR} ");
		 }
		 if(model.getAccountName()!=null && model.getAccountName()!=""){
			 sql.append("and ba.account_name like '%'+#{accountName,jdbcType=VARCHAR}+'%' ");
		 }
		 if(model.getBusinessMainBody()!=0){
			 sql.append("and ba.business_main_body=#{businessMainBody,jdbcType=INTEGER} ");
		 }
		 if(model.getCustomer()!=null && model.getCustomer()!=""){
			 sql.append("and M.name=#{customer,jdbcType=VARCHAR} ");
		 }
		 if(model.getCreatedByName()!=null && model.getCreatedByName()!=""){
			 sql.append("and ba.created_by_name=#{createdByName,jdbcType=VARCHAR} ");
		 }
		 if(model.getShenQingDate()!=null && model.getShenQingDate()!=""){
			 sql.append("and Convert(varchar(10),ba.created_date,120) like '%'+#{shenQingDate}+'%' ");
		 }
		 return sql.toString();
		 
		 
	 }
}
