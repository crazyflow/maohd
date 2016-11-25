package com.cbecs.scrm.consigner.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.cbecs.scrm.consigner.model.Bank;
import com.cbecs.scrm.consigner.model.BankAccount;
import com.cbecs.scrm.consigner.model.BankAccountPageable;
import com.cbecs.scrm.consigner.model.BusinessBody;
import com.cbecs.scrm.consigner.model.BusinessBodyAccount;
import com.cbecs.scrm.consigner.persistence.SelectSqlProvider;





public interface BankAccountMapper {
	 @Select("select name as consignerName,id as consignerId from Company where SignFlag='1' and ProductSourceType!='203'  ")
	 List<BankAccount> getConsigner();
	 
	 
	 
	 @Insert("insert into bank_account([account_id]"
		      +",[account_no]"
		      +",[sub_account_no]"
		      +",[consigner_id]"
		      +",[consigner_name]"
		      +",[status]"
		      +",[business_main_body]"
		      +",[bank_id]"
		      +",[bank_name]"
		      +",[remark]"
		      +",[deleted]"
		      +",[created_by]"
		      +",[created_by_name]"
		      +",[created_date]"
		      +",[updated_by]"
		      +",[updated_by_name]"
		      +",[updated_date],[account_name]) values(#{accountId},#{accountNo},#{subAccountNo},#{consignerId},#{consignerName},#{status},#{businessMainBody},#{bankId},#{bankName},#{remark},'0',#{createdBy},#{createdByName},GETDATE(),#{createdBy},#{createdByName},GETDATE(),#{accountName})")
	 void savedata(BankAccount model);
	 
	 
	 @SelectProvider(method="getallbysql",type=SelectSqlProvider.class)
	 List<BankAccountPageable> table(BankAccountPageable model);

	@Select("select ba.*,bba.bank_enname,bba.swift_code,bba.enaddress,bb.body_name as bodyName from bank_account ba left join bank bba on ba.bank_id=bba.bank_id left join business_body bb on bb.body_id=ba.business_main_body where account_id=#{accountId}")
	List<BankAccountPageable> getconsignername(String accountId);
	
	
	
	@Select("select RIGHT(REPLICATE('0', 6) + cast(max(sub_account_no) + 1 as varchar(10)), 6) as sub_account_no from bank_account  ")
	BankAccount getall(BankAccount model);
	
	@Select("select consigner_name,consigner_id from bank_account where consigner_name like '%'+#{consignerName,jdbcType=VARCHAR}+'%' ")
	List<BankAccount> getconsignerlist(String consignerName);

	@Select("select bank_id,bank_name from bank")
	List<Bank> getbanklist();

	@Select("select distinct bb.body_id,bb.body_name from business_body bb left join business_body_account ba ON ba.body_id=bb.body_id where ba.bank_id=#{bankId}")
	List<BusinessBody> getbusiness(int bankId);
	
	@Select("select * from business_body_account where bank_id=#{0} and body_id=#{1} ")
	List<BusinessBodyAccount> getaccountno(int bankId,int bodyId);
	
	@Select("select * from bank")
	List<Bank> getBank();
	
	@Select("select account_name from bank_account")
	List<BankAccount> getAccountName();
	
	@Select("select cm.name as consignerName,cm.ID as consignerId from Company cm left join CompanyContract cc on cm.ID=cc.CompanyID where cm.SignFlag='1' and  cc.SignType=#{0} and cm.ProductSourceType!='203' and cm.CustomerService=#{1} ")
	List<BankAccount> getCg(int bodyId,String userId);
	
	@Select("select EnglishName from Company where id=#{consignerId}")
	BankAccount getEnname(String consignerId);
	
	@Select("select distinct M.name as customer from bank_account ba left join Company cm ON cm.ID=ba.consigner_id left join ManagerUser M ON M.ID=cm.CustomerService where ba.deleted=0")
	List<BankAccount> getcustomer();
	
	@Select("select distinct created_by_name from bank_account")
	List<BankAccount> getcreatedByName();
	
	@Select("select account_enabbr from business_body_account where body_id=#{0} and account_no=#{1}")
	BusinessBodyAccount getenabbr(int bodyId,String accountNo);
	
	@Select(" select * from bank_account where REPLACE(account_name,' ','')=#{validate}")
	List<BankAccount> validate(String validate);


	@Select("select * from bank_account where consigner_id=#{0} and bank_id=#{1} and account_no=#{2}")
	List<BankAccount> consignerexists(String consignerId, int bankId, String accountNo);

	@Select("select * from business_body")
	List<BusinessBody> getyewuzhuti();
}
