package com.cbecs.scrm.consigner.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.framework.web.session.CurrentUser;
import com.cbecs.framework.web.session.SessionRepertory;
import com.cbecs.scrm.consigner.model.Bank;
import com.cbecs.scrm.consigner.model.BankAccount;
import com.cbecs.scrm.consigner.model.BankAccountPageable;
import com.cbecs.scrm.consigner.model.BusinessBody;
import com.cbecs.scrm.consigner.model.BusinessBodyAccount;
import com.cbecs.scrm.consigner.service.inf.BankAccountService;
import com.cbecs.smc.cfpclient.bank.BankService;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098RequestBody.JSB200098ReqItem;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098RequestMessage;
import com.cbecs.smc.cfpclient.bank.jsb.message.JSB200098ResponseMessage;

@Controller
@RequestMapping(value = "/bank-account")
public class BankAccountController {
	private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);
	@Autowired
	private SessionRepertory sessionRepertory;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private BankService bankService;

	@Autowired
	private @Value("${cfpclient.jsb.acno}") String acno;

	@GetMapping(value = "")
	public ModelAndView getbankaccount() {

		ModelAndView view = new ModelAndView("/scrm/consigner/bank_account_list");
		String bank = bankAccountService.getBank();
		String customer = bankAccountService.getcustomer();

		String consigner = bankAccountService.getConsignerId();
		String accountName = bankAccountService.getAccountName();
		String createdBy = bankAccountService.getcreatedByName();
		String businessBody = bankAccountService.getyeuzhuti();
		view.addObject("accountName", accountName);
		view.addObject("bank", bank);
		view.addObject("consigner", consigner);
		view.addObject("customer", customer);
		view.addObject("createdBy", createdBy);
		view.addObject("businessBody", businessBody);
		return view;
	}

	@GetMapping(value = "", params = "format=table")
	public ModelAndView getbankTable(BankAccountPageable p) {
	    List<Sort> listSort = new ArrayList<Sort>();
		Sort sort = new Sort();
		sort.setDirection("desc");
		sort.setProperty("updated_date");
		p.setPageSize(10);
		listSort.add(sort);
		p.setSort(listSort);

		ModelAndView view = new ModelAndView("/scrm/consigner/bank_account_table");
		List<BankAccountPageable> tablelist = bankAccountService.tabel(p);

		view.addObject("tablelist", tablelist);
		view.addObject("page", p);

		return view;
	}

	@GetMapping(value = "/new")
	public ModelAndView newWithdrawApplication() {
		ModelAndView view = new ModelAndView("/scrm/consigner/bank_account_new");

		CurrentUser user = sessionRepertory.get();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());

		view.addObject("user", user);
		view.addObject("time", time);

		return view;

	}

	@PostMapping(value = "")
	public String saveBankaccountinfo(BankAccount model, RedirectAttributes attr)
			throws JAXBException, IOException{
		String result = "";
		
		BankAccount mm = bankAccountService.getall(model);
		String subaccount = "";

		if (null == mm) {
			subaccount = "000001";
		} else {
			subaccount = mm.getSubAccountNo();
		}
		
		if (StringUtils.isBlank(model.getAccountName())
				|| StringUtils.isBlank(model.getAccountNo())
				|| StringUtils.isBlank(subaccount))
		{
			attr.addAttribute("message","errorInfo：户名,账户,子账号可能为空");
			attr.addAttribute("tabName", "新增虚拟子账户管理");
			attr.addAttribute("tabUrl", "/bank-account/new");
			
			result="redirect:/submit/info";
			return result;
		}
		List<BankAccount> newbank = bankAccountService.validate(StringUtils.deleteWhitespace(model.getAccountName()));
		if(newbank.size() != 0){
			attr.addAttribute("message","errorInfo：账户名已存在");
			attr.addAttribute("tabName", "新增虚拟子账户管理");
			attr.addAttribute("tabUrl", "/bank-account/new");
			result="redirect:/submit/info";
			return result;
		}
		JSB200098RequestMessage request = new JSB200098RequestMessage();
		request.getBody().setAcno(model.getAccountNo());
		request.getBody().setCurCode("01");
		// 新增
		List<JSB200098ReqItem> list = new ArrayList<JSB200098ReqItem>();
		JSB200098ReqItem item1 = new JSB200098ReqItem();
		// 操作类型 0-新增，1-删除，2-修改
		item1.setOperFlag("0");
		// 银行账号
		item1.setAcno(model.getAccountNo());
		// 人民币账户
		item1.setCurCode("01");
		item1.setAsAcno(subaccount);
		item1.setAsAcname(model.getAccountName());
		// 上级账簿
		item1.setSupAsAcno("000000");
		list.add(item1);

		request.getBody().setRespItems(list);

		JSB200098ResponseMessage response = (JSB200098ResponseMessage) bankService.updateAccountBook(request);

		if ("0".equals(response.getBody().getStat()) && "0".equals(response.getHead().getSuccFlag())
				&& "0000".equals(response.getHead().getRetCode())) {
			logger.info("success,param{}：" + response.getBody().getSerialRecord());
			String accountId = UUID.randomUUID().toString();
			model.setAccountId(accountId);

			CurrentUser user = sessionRepertory.get();
			String userName = user.getName();
			String userId = user.getId();

			model.setSubAccountNo(subaccount);
			model.setCreatedBy(userId);
			model.setCreatedByName(userName);
			model.setUpdatedDate(new Date());
			bankAccountService.savedata(model);

			attr.addAttribute("tabName", "虚拟子账户管理");
			attr.addAttribute("tabUrl", "/bank-account");
			attr.addAttribute("tabId", "AC70F35C-C9C6-4BF7-8835-56598608491F");
			result ="redirect:/submit/success";
		} else {
			logger.info("error,param{}：" + response.getBody().getSerialRecord());
			logger.info("error,errorCode：" + response.getHead().getSuccFlag() + ",errorInfo："
					+ response.getHead().getRetInfo() + ",subErrorCode：" + response.getHead().getRetCode()
					+ ",subErrorInfo：" + response.getHead().getExtInfo());

			attr.addAttribute("message",
					response.getHead().getSuccFlag() + ",errorInfo：" + response.getHead().getRetInfo()
							+ ",subErrorCode：" + response.getHead().getRetCode() + ",subErrorInfo："
							+ response.getHead().getExtInfo());
			attr.addAttribute("tabName", "新增虚拟子账户");
			attr.addAttribute("tabUrl", "/bank-account/new");
			result="redirect:/submit/info";
		}
		
		return result;

	}

	@GetMapping(value = "/{id}/view")
	public ModelAndView editbankPage(@PathVariable(value = "id") String accountId) {
		ModelAndView view = new ModelAndView("/scrm/consigner/bank_account_view");

		List<BankAccountPageable> consigner = bankAccountService.getConsignerName(accountId);

		view.addObject("consigner", consigner);
		return view;
	}

	@GetMapping(value = "/getbank", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> getbank() {
		List<Bank> banklist = bankAccountService.getbanklist();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("banklist", banklist);
		return map;
	}

	@GetMapping(value = "/getbusinessbody/{bankId}", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> getbusinessbody(@PathVariable(value = "bankId") int bankId) {
		List<BusinessBody> list = bankAccountService.getbussinessbody(bankId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

	@GetMapping(value = "/getaccountno/{bankId}/{bodyId}", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> getaccountno(@PathVariable(value = "bankId") int bankId,
			@PathVariable(value = "bodyId") int bodyId) {
		List<BusinessBodyAccount> list = bankAccountService.getaccountno(bankId, bodyId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;

	}

	@GetMapping(value = "/getConsigner/{bodyId}", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> getbussinessbody(@PathVariable(value = "bodyId") int bodyId) {
		CurrentUser user = sessionRepertory.get();
		String userId = user.getId();
		List<BankAccount> list = bankAccountService.getCg(bodyId,userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

	@GetMapping(value = "/getEnname/{consignerId}", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> getEnname(@PathVariable(value = "consignerId") String consignerId) {

		BankAccount bankAccount = bankAccountService.getEnname(consignerId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bankAccount", bankAccount);
		return map;
	}

	@GetMapping(value = "/getjianchen/{bodyId}/{accountNo}", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> getjianchen(@PathVariable(value = "bodyId") int bodyId,@PathVariable(value = "accountNo") String accountNo) {
		BusinessBodyAccount bba = bankAccountService.getenabbr(bodyId,accountNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bba", bba);
		return map;
	}
	@GetMapping(value = "/consignerexists/{consignerId}/{bankId}/{accountNo}", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> consignerexists(@PathVariable(value = "consignerId") String consignerId,@PathVariable(value = "bankId") int bankId,@PathVariable(value = "accountNo") String accountNo){
		List<BankAccount> list = bankAccountService.consignerexists(consignerId,bankId,accountNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	@GetMapping(value = "/accountNameexist/{accountName}", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> accountNameexist(@PathVariable(value = "accountName") String accountName){
		List<BankAccount> newbank = bankAccountService.validate(accountName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", newbank.size());
		return map;
	}
}
