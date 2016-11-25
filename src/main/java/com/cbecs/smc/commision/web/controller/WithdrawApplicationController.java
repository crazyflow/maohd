package com.cbecs.smc.commision.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbecs.framework.mybatis.pagination.Sort;
import com.cbecs.framework.web.session.CurrentUser;
import com.cbecs.framework.web.session.SessionRepertory;
import com.cbecs.smc.commision.model.AccountStatement;
import com.cbecs.smc.commision.model.WithdrawApplication;
import com.cbecs.smc.commision.model.WithdrawApplicationPageable;
import com.cbecs.smc.commision.model.WithdrawDetail;
import com.cbecs.smc.commision.service.inf.AccountStatementService;
import com.cbecs.smc.commision.service.inf.CashPoolService;
import com.cbecs.smc.commision.service.inf.SettlementApplicationService;
import com.cbecs.smc.commision.service.inf.WithdrawApplicationService;
import com.cbecs.smc.commision.service.inf.WithdrawDetailService;
import com.cbecs.workflow.model.BusinessInteractiveInfo;
import com.cbecs.workflow.model.EmployeeTrackInfo;
import com.cbecs.workflow.model.ReturnInfo;
import com.cbecs.workflow.model.TrackLineEmployeeInfo;
import com.cbecs.workflow.model.WorkFlowCommonBody;
import com.cbecs.workflow.service.inf.WorkFlowService;

@Controller
@RequestMapping("/commission-withdraw-applications")
public class WithdrawApplicationController
{
    @Autowired
    private WithdrawApplicationService withdrawApplicationService;

    @Autowired
    private WithdrawDetailService withdrawDetailService;

    @Autowired
    private CashPoolService cashPoolService;

    @Autowired
    private AccountStatementService accountStatementService;

    @Autowired
    private SettlementApplicationService settlementApplicationService;

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private SessionRepertory sessionRepertory;

    private static String Flow_Code = "mhdYJTQ_test";
    private static String Source_MainMark = "smc";
    private static String Source_SubMark = "YJTQ";

    /* 佣金提取 */
    @GetMapping(value = "")
    public ModelAndView getcommissionWithdraw()
    {       
        ModelAndView view = new ModelAndView("/smc/commission/withdraw_application_list");
        return view;
    }

    @GetMapping(value = "", params = "format=table")
    public ModelAndView getCommissionWithdrawTable(WithdrawApplicationPageable p)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("updated_date");
        p.setPageSize(10);
        listSort.add(sort);
        p.setSort(listSort);

        WorkFlowCommonBody wfCommonBody = new WorkFlowCommonBody();
        CurrentUser user = sessionRepertory.get();
        wfCommonBody.setEmployeeId(user.getId());
        wfCommonBody.setSourceMainMark(Source_MainMark);
        wfCommonBody.setSourceSubMark(Source_SubMark);
        String workFlowIds = workFlowService.getWorkFlowIds(wfCommonBody);

        if (p.isByStatus())
        {
            p.setWorkFlowIds(workFlowIds);
        }

        List<WithdrawApplication> t = withdrawApplicationService.getListByPage(p);
        String[] res = { "0", "0" };
        if (workFlowIds != "" && workFlowIds != null)
        {
            for (WithdrawApplication w : t)
            {
                if (workFlowIds.indexOf(String.valueOf(w.getWorkFlowId())) >= 0)
                {
                    w.setCurrentFlowStatus(w.getStatus() == 2 ? 2 : 4);
                }
                else
                {
                    w.setCurrentFlowStatus(0);
                }
            }
            String statusInfo = withdrawApplicationService.getStatusInfo(workFlowIds);
            if (statusInfo != null)
            {
                res = statusInfo.split("#");
            }
        }
        ModelAndView view = new ModelAndView("/smc/commission/withdraw_application_table");
        view.addObject("withdrawList", t);
        view.addObject("page", p);
        view.addObject("statusInfo", res);
        return view;
    }
    
    @GetMapping(value = "/history", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getHistoryAccounts(WithdrawApplication withdrawApplication)
    {
        List<WithdrawApplication> t = withdrawApplicationService.getHistoryAccounts(withdrawApplication);
        String res = "";
        for(WithdrawApplication w : t){
            res += "<li onclick=\"setOtherValues('"+w.getBeneficiaryName()+"','"+w.getBeneficiaryBank()+"','"+w.getBeneficiaryAccountNo()+"');\">"+w.getBeneficiaryName()+"  "+ w.getBeneficiaryBank() +"</li>";
        } 
        return res;
    }

    /* 新增佣金提取 */
    @GetMapping(value = "/new")
    public ModelAndView newWithdrawApplication()
    {
        return new ModelAndView("/smc/commission/withdraw_application_new");
    }

    /* 保存新增佣金提取 */
    @PostMapping(value = "")
    public String savewithdrawApplication(WithdrawApplication model,RedirectAttributes attr)
    {
        CurrentUser user = sessionRepertory.get();
        model.setApplicationId(UUID.randomUUID().toString());
        ReturnInfo returnifo = new ReturnInfo();
        
        BusinessInteractiveInfo interactiveInfo = new BusinessInteractiveInfo();
        interactiveInfo.setBusinessCode(model.getApplicationId());
        interactiveInfo.setBusinessOperate(1);
        interactiveInfo.setEmployeeId(user.getId());
        interactiveInfo.setEmployeeName(user.getName());
        interactiveInfo.setFlowCode(Flow_Code);
        interactiveInfo.setSourceMainMark(Source_MainMark);
        interactiveInfo.setSourceSubMark(Source_SubMark);

        if (model.getStatus() != 1)
        {
            returnifo = workFlowService.BusinessInteractive(interactiveInfo);
            if (returnifo.getStatus().equals("success"))
            {
                model.setWorkFlowId(returnifo.getWorkFlowId());
                interactiveInfo.setWorkFlowId(returnifo.getWorkFlowId());
                interactiveInfo.setWorkSubject("提交成功");
            }
            else
            {
                attr.addAttribute("message", "工作流发起失败！");
                attr.addAttribute("tabName", "佣金提取新增");
                attr.addAttribute("tabUrl", "/commission-withdraw-applications/new");
                return "redirect:/submit/info";
            }
        }

        model.setApplicationCode(withdrawApplicationService.getApplicationCode(Source_SubMark));
        withdrawApplicationService.insertWithdrawApplication(model);
        withdrawDetailService.insertWithdrawDetail(model);
        if (model.getStatus() != 1)
        {
            insertWithDrawAccout(model, 3);
            cashPoolService.updateCashPoolBywithDraw(model);
            interactiveInfo.setBusinessOperate(3);
            workFlowService.BusinessInteractive(interactiveInfo);
        }
        
        attr.addAttribute("tabName", "佣金提取申请");
        attr.addAttribute("tabUrl", "/commission-withdraw-applications");
        attr.addAttribute("tabId", "47400915-01D0-4440-A406-068E1E05C0AA");
        return "redirect:/submit/success";
    }

    /* 获取当日美元汇率 */
    @GetMapping(value = "/currency-rate")
    @ResponseBody
    public double getCurrencyDailyRate()
    {
        int currencyId = 318;
        double d = withdrawApplicationService.getCurrencyDailyRate(currencyId);
        return d;
    }

    /* 佣金提取详情 */
    @GetMapping(value = "/{id}")
    public ModelAndView withdrawDetail(@PathVariable(value = "id") String applicationId)
    {
        boolean audit = false;
        WithdrawApplication model = withdrawApplicationService.selectwithdrawById(applicationId);
        List<WithdrawDetail> details = withdrawDetailService.selectWithdrawDetailById(applicationId);
        model.setWithDrawdetails(details);

        List<EmployeeTrackInfo> employeeTrackInfos = new ArrayList<EmployeeTrackInfo>();
        List<TrackLineEmployeeInfo> trackLineEmployeeInfo = new ArrayList<TrackLineEmployeeInfo>();
        if (model.getStatus() != 1)
        {
            WorkFlowCommonBody wfCommonBody = new WorkFlowCommonBody();
            CurrentUser user = sessionRepertory.get();
            wfCommonBody.setEmployeeId(user.getId());
            wfCommonBody.setSourceMainMark(Source_MainMark);
            wfCommonBody.setSourceSubMark(Source_SubMark);
            String workFlowIds = workFlowService.getWorkFlowIds(wfCommonBody);
            wfCommonBody.setWorkFlowId(model.getWorkFlowId());
            employeeTrackInfos = workFlowService.SortEmployeeInfos(wfCommonBody);
            trackLineEmployeeInfo = workFlowService.GetSimpleTrackLine(wfCommonBody).getInfos();

            if (workFlowIds.indexOf(String.valueOf(model.getWorkFlowId())) >= 0 && model.getStatus() == 2)
            {
                audit = true;
            }
        }
        ModelAndView view = new ModelAndView("/smc/commission/withdraw_application_detail");
        view.addObject("withdraw", model);
        view.addObject("employeeTrackInfos", employeeTrackInfos);
        view.addObject("trackLineEmployeeInfo", trackLineEmployeeInfo);
        view.addObject("audit", audit);
        return view;
    }

    /* 删除佣金提取 */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public HashMap<String, Object> deleteWithdrawApplication(@PathVariable(value = "id") String applicationId)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String a = withdrawApplicationService.deleteWithdrawApplication(applicationId);
        String b = withdrawDetailService.deleteWithdrawDetail(applicationId);
        if (a.isEmpty() && b.isEmpty())
        {
            map.put("success", true);
            map.put("message", "删除佣金提取成功!");
        }
        else
        {
            map.put("success", false);
            map.put("message", a + b);
        }
        return map;
    }

    /* 佣金提取编辑 */
    @GetMapping(value = "/{id}/edit")
    public ModelAndView editWithdrawApplicationPage(@PathVariable(value = "id") String applicationId)
    {
        WithdrawApplication model = withdrawApplicationService.selectwithdrawById(applicationId);
        List<WithdrawDetail> details = withdrawDetailService.selectWithdrawDetailById(applicationId);
        String arr = "";
        for (WithdrawDetail d : details)
        {
            arr += d.getSettlementApplicationId() + "#" + d.getSettlementApplicationCode() + "#" + d.getCommisionAmount() + "#" + d.getWithdrawnAmount() + "#" + d.getAmount() + "#" + d.getForeignAmount() + "#" + d.getDetailId() + ",";
        }
        ModelAndView view = new ModelAndView("/smc/commission/withdraw_application_edit");
        view.addObject("withdraw", model);
        view.addObject("withdrawDetail", arr.substring(0, arr.length() - 1));
        return view;
    }

    @PutMapping(value = "")
    public String updateWithdrawApplication(WithdrawApplication model, RedirectAttributes attr)
    {
        CurrentUser user = sessionRepertory.get();
        BusinessInteractiveInfo interactiveInfo = new BusinessInteractiveInfo();
        interactiveInfo.setBusinessCode(model.getApplicationId());
        interactiveInfo.setEmployeeId(user.getId());
        interactiveInfo.setEmployeeName(user.getName());
        interactiveInfo.setFlowCode(Flow_Code);
        interactiveInfo.setSourceMainMark(Source_MainMark);
        interactiveInfo.setSourceSubMark(Source_SubMark);

        if (model.getWorkFlowId() == 0)
        {
            interactiveInfo.setBusinessOperate(1);
            ReturnInfo returnifo = workFlowService.BusinessInteractive(interactiveInfo);
            if (returnifo.getStatus().equals("success"))
            {
                model.setWorkFlowId(returnifo.getWorkFlowId());
                interactiveInfo.setWorkFlowId(returnifo.getWorkFlowId());
                model.setWorkFlowId(returnifo.getWorkFlowId());
            }
            else
            {
                attr.addAttribute("message", "工作流发起失败！");
                attr.addAttribute("tabName", "佣金提取编辑");
                attr.addAttribute("tabUrl", "/commission-withdraw-applications/" + model.getApplicationId() + "/edit");
                return "redirect:/submit/info";
            }
        }
        else
        {
            interactiveInfo.setWorkFlowId(model.getWorkFlowId());
        }

        if (model.getStatus() != 1)
        {
            interactiveInfo.setBusinessOperate(3);
            interactiveInfo.setWorkSubject("提交成功");
            workFlowService.BusinessInteractive(interactiveInfo);
        }

        withdrawApplicationService.updateWithdrawApplication(model);
        withdrawDetailService.deleteWithdrawDetail(model.getApplicationId());
        withdrawDetailService.insertWithdrawDetail(model);
        if (model.getStatus() != 1)
        {
            insertWithDrawAccout(model, 3);
            cashPoolService.updateCashPoolBywithDraw(model);
        }

        attr.addAttribute("tabName", "佣金提取申请");
        attr.addAttribute("tabUrl", "/commission-withdraw-applications");
        attr.addAttribute("tabId", "47400915-01D0-4440-A406-068E1E05C0AA");
        return "redirect:/submit/success";
    }

    @PostMapping(value = "/{id}/audit", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String auditWithdrawApplication(@PathVariable(value = "id") String applicationId, boolean auditValue, String comment)
    {
        CurrentUser user = sessionRepertory.get();
        WithdrawApplication withdrawApplication = withdrawApplicationService.selectwithdrawById(applicationId);
        BusinessInteractiveInfo interactiveInfo = new BusinessInteractiveInfo();
        interactiveInfo.setBusinessCode(applicationId);
        interactiveInfo.setEmployeeId(user.getId());
        interactiveInfo.setEmployeeName(user.getName());
        interactiveInfo.setFlowCode(Flow_Code);
        interactiveInfo.setSourceMainMark(Source_MainMark);
        interactiveInfo.setSourceSubMark(Source_SubMark);
        interactiveInfo.setWorkFlowId(withdrawApplication.getWorkFlowId());
        if (auditValue)
        {
            interactiveInfo.setWorkSubject("审核通过");
            interactiveInfo.setBusinessOperate(3);
        }
        else
        {
            interactiveInfo.setBusinessOperate(4);
            interactiveInfo.setWorkSubject("审核不通过");
            interactiveInfo.setWorkContext(comment);
        }
        ReturnInfo returnInfo = workFlowService.BusinessInteractive(interactiveInfo);

        List<WithdrawDetail> withDrawDetails = withdrawDetailService.selectWithdrawDetailById(applicationId);

        if (returnInfo.getWorkFlowState() == 80)
        {
            withdrawApplication.setStatus(3);
            withdrawApplicationService.updateWithdrawApplicationWithStatus(withdrawApplication);
            cashPoolService.updateCashPoolByAudit(withdrawApplication);
            insertWithDrawAccout(withdrawApplication, 4);
            insertWithDrawAccout(withdrawApplication, 2);
        }
        else if (returnInfo.getWorkFlowState() == 40)
        {
            withdrawApplication.setStatus(4);
            withdrawApplicationService.updateWithdrawApplicationWithStatus(withdrawApplication);
            for (WithdrawDetail withdrawDetail : withDrawDetails)
            {
                settlementApplicationService.updateSettleApplicationByAuditNo(withdrawDetail);
            }
            insertWithDrawAccout(withdrawApplication, 4);
            cashPoolService.updateCashPoolByAuditNo(withdrawApplication);
        }
        else if (returnInfo.getWorkFlowState() == 30)
        {

        }
        else
        {
            return "审核提交失败！";
        }
        return "";
    }
    
    public void insertWithDrawAccout(WithdrawApplication withdrawApplication,int raeType){
        CurrentUser user = sessionRepertory.get();
        AccountStatement accountStatement = new AccountStatement();
        accountStatement.setStatementId(UUID.randomUUID().toString());
        accountStatement.setChannelId(withdrawApplication.getChannelId());
        accountStatement.setChannelName(withdrawApplication.getChannelName());
        accountStatement.setBusinessMainBody(withdrawApplication.getBusinessMainBody());
        accountStatement.setCapacity1Id("00000000-0000-0000-0000-000000000000");
        accountStatement.setCapacity1Name("提佣金");
        accountStatement.setCapacity2Id("00000000-0000-0000-0000-000000000000");
        accountStatement.setCapacity2Name("提佣金");
        accountStatement.setDocumentId(withdrawApplication.getApplicationId());
        accountStatement.setDocumentCode(withdrawApplication.getApplicationCode());
        accountStatement.setRaeType(raeType);
        accountStatement.setCurrencyId(withdrawApplication.getCurrencyId());
        accountStatement.setCurrencyCode(withdrawApplication.getCurrencyCode());
        accountStatement.setRate(withdrawApplication.getSellingRate());
        accountStatement.setAmount(withdrawApplication.getTotalAmount());
        accountStatement.setForeignAmount(withdrawApplication.getCurrencyId()==151?withdrawApplication.getTotalAmount():withdrawApplication.getTotalForeignAmount());
        accountStatement.setCreatedBy(user.getId());
        accountStatement.setCreatedByName(user.getName());
        accountStatement.setCreatedDate(new Date());
        accountStatementService.create(accountStatement);
    }

}