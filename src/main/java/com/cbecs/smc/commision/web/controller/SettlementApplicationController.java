package com.cbecs.smc.commision.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.cbecs.report.service.inf.AnalysisService;
import com.cbecs.scrm.channel.model.ChannelBusiness;
import com.cbecs.scrm.channel.service.inf.ChannelService;
import com.cbecs.smc.commision.model.SettlementApplication;
import com.cbecs.smc.commision.model.SettlementApplicationQueryPageable;
import com.cbecs.smc.commision.service.inf.SettlementApplicationService;
import com.cbecs.workflow.model.EmployeeTrackInfo;
import com.cbecs.workflow.model.TrackLineEmployeeInfo;

@Controller
@RequestMapping(value = "/commission-settlement-applications")
public class SettlementApplicationController
{

    private static final String MENU_UUID = "86879D66-8007-4237-93D6-960C52D82A6D";

    @Autowired
    private SettlementApplicationService settlementApplicationService;

    @Autowired
    private ChannelService channelService;
    
    @Autowired
    private AnalysisService analysisService;

    /**
     * 佣金结算申请
     * 
     * @return
     */
    @GetMapping(value = "")
    public ModelAndView getSettlementApplication()
    {
        ModelAndView view = new ModelAndView("/smc/commission/settlement_application_list");
        return view;
    }

    /**
     * 佣金结算申请数据
     * 
     * @return
     */
    @GetMapping(value = "", params = "format=table")
    public ModelAndView getSettlementApplicationInTable(SettlementApplicationQueryPageable model)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("updated_date");
        listSort.add(sort);
        model.setSort(listSort);
        model.setPageSize(10);
        ModelAndView view = new ModelAndView("/smc/commission/settlement_application_table");
        List<SettlementApplication> list = settlementApplicationService.getSettlementApplication(model);
        // 获取当前我待办的工作流id
        String workflowIds = settlementApplicationService.getWorkFlowIds();
        Map<String, Object> map = settlementApplicationService.getWorkFlowRecord();
        view.addObject("settlementApplications", list);
        view.addObject("workflowIds", workflowIds);
        view.addObject("page", model);
        view.addObject("workflowSummary", map);
        return view;
    }

    /**
     * 佣金结算申请单新增页面
     * 
     * @return
     */
    @GetMapping(value = "/new")
    public ModelAndView getSettlementDetailBychannel(ChannelBusiness channelBuness)
    {
        ModelAndView view = new ModelAndView("/smc/commission/settlement_application_new");
        List<Map<String,Object>> businessMainBodys = analysisService.getBusinessMainBody();
        view.addObject("channel", channelBuness);
        view.addObject("businessMainBodys", businessMainBodys);
        return view;
    }

    /**
     * 佣金结算详情页面
     * 
     * @return
     */
    @GetMapping(value = "/{applicationId}/detail")
    public ModelAndView getSettlementApplicationDetail(@PathVariable(value = "applicationId") String applicationId)
    {
        ModelAndView view = new ModelAndView("/smc/commission/settlement_application_detail");
        SettlementApplication settlementApplication = settlementApplicationService
                .getSettlementApplicationByApplicationId(applicationId);
        List<TrackLineEmployeeInfo> trackLineEmployeeInfo = settlementApplicationService
                .getSettlementWorkflowLine(settlementApplication.getWorkflowId());
        List<EmployeeTrackInfo> employeeTrackInfo = settlementApplicationService
                .getSettlementWorkflowLines(settlementApplication.getWorkflowId());
        List<Map<String,Object>> businessMainBodys = analysisService.getBusinessMainBody();
        view.addObject("settlementApplication", settlementApplication);
        view.addObject("trackLineEmployeeInfo", trackLineEmployeeInfo);
        view.addObject("employeeTrackInfo", employeeTrackInfo);
        view.addObject("businessMainBodys", businessMainBodys);
        // 详情
        view.addObject("auditAuth", false);
        return view;
    }

    /**
     * 佣金结算单审核页面
     * 
     * @return
     */
    @GetMapping(value = "/{applicationId}/audit")
    public ModelAndView auditSettlementApplication(@PathVariable(value = "applicationId") String applicationId)
    {
        ModelAndView view = new ModelAndView("/smc/commission/settlement_application_detail");
        SettlementApplication settlementApplication = settlementApplicationService
                .getSettlementApplicationByApplicationId(applicationId);

        List<TrackLineEmployeeInfo> trackLineEmployeeInfo = settlementApplicationService
                .getSettlementWorkflowLine(settlementApplication.getWorkflowId());
        List<EmployeeTrackInfo> employeeTrackInfo = settlementApplicationService
                .getSettlementWorkflowLines(settlementApplication.getWorkflowId());
        List<Map<String,Object>> businessMainBodys = analysisService.getBusinessMainBody();
        view.addObject("settlementApplication", settlementApplication);
        view.addObject("trackLineEmployeeInfo", trackLineEmployeeInfo);
        view.addObject("employeeTrackInfo", employeeTrackInfo);
        view.addObject("auditAuth", true);
        view.addObject("businessMainBodys", businessMainBodys);
        return view;
    }

    /**
     * 佣金结算单编辑页面
     * 
     * @return
     */
    @GetMapping(value = "/{applicationId}/edit")
    public ModelAndView editSettlementApplication(@PathVariable(value = "applicationId") String applicationId)
    {
        SettlementApplication settlementApplication = settlementApplicationService
                .getSettlementApplicationByApplicationId(applicationId);
        List<Map<String,Object>> businessMainBodys = analysisService.getBusinessMainBody();
        ModelAndView view = new ModelAndView("/smc/commission/settlement_application_edit");
        view.addObject("settlementApplication", settlementApplication);
        view.addObject("businessMainBodys", businessMainBodys);
        return view;
    }

    /**
     * 审核
     * 
     * @return
     */
    @PutMapping(value = "/{applicationId}/audit")
    @ResponseBody
    public Map<String, Object> auditSettlementApplication(@PathVariable(value = "applicationId") String applicationId,
            boolean flag, String content)
    {
        // true表示审核成功 false表示审核不通过
        return settlementApplicationService.auditWorkFlow(applicationId, flag, content);
    }

    /**
     * 删除
     * 
     * @return
     */
    @DeleteMapping(value = "/{applicationId}")
    @ResponseBody
    public Map<String, Object> removeSettlementApplication(@PathVariable(value = "applicationId") String applicationId)
    {
        return settlementApplicationService.removeSettlementApplication(applicationId);
    }

    /**
     * 新增
     * 
     * @return
     */
    @PostMapping(value = "", produces = "application/json")
    public String newSettlementApplication(SettlementApplication model, RedirectAttributes attr)
    {
        Map<String, Object> map = settlementApplicationService.createSettlementApplication(model);
        String result = "";
        if (null != map.get("success"))
        {
            result = "redirect:/submit/success";
            // attr.addAttribute("message", map.get("success"));
            attr.addAttribute("tabId", MENU_UUID);
            attr.addAttribute("tabName", "佣金结算申请");
            // 成功跳转列表界面
            attr.addAttribute("tabUrl", "/commission-settlement-applications");
        }
        else
        {
            result = "redirect:/submit/info";
            attr.addAttribute("message", map.get("error"));
            attr.addAttribute("tabName", "新增佣金结算");
            // 失败跳转新增界面
            // 根据渠道商id获取渠道商信息
            ChannelBusiness channelBusiness = channelService.getChannelBuinessByChannelId(model.getChannelId());
            attr.addAttribute("tabUrl",
                    "/commission-settlement-applications/new?channelId=" + channelBusiness.getChannelId()
                            + "&channelName=" + channelBusiness.getChannelName() + "&contactName="
                            + channelBusiness.getContactName() + "&businessMainBody="
                            + channelBusiness.getBusinessMainBody() + "&cooperationMode="
                            + channelBusiness.getCooperationMode());
        }
        return result;
    }

    /**
     * 编辑
     * 
     * @return
     */
    @PutMapping(value = "", produces = "application/json")
    public String editSettlementApplication(SettlementApplication model, RedirectAttributes attr)
    {
        Map<String, Object> map = settlementApplicationService.editSettlementApplication(model);
        String result = "";
        if (null != map.get("success"))
        {
            result = "redirect:/submit/success";
            // attr.addAttribute("message", map.get("success"));
            attr.addAttribute("tabName", "佣金结算编辑");
            attr.addAttribute("tabId", MENU_UUID);
            // 成功跳转列表界面
            attr.addAttribute("tabUrl", "/commission-settlement-applications");
        }
        else
        {
            result = "redirect:/submit/info";
            attr.addAttribute("message", map.get("error"));
            attr.addAttribute("tabName", "佣金结算编辑");
            // attr.addAttribute("tabId",
            // "47400915-01D0-4440-A406-068E1E05C0AA");
            // 失败跳转新增界面
            // 根据渠道商id获取渠道商信息
            attr.addAttribute("tabUrl", "/commission-settlement-applications/" + model.getApplicationId() + "/edit");
        }
        return result;
    }

    /* 选择佣金结算申请 */
    @GetMapping(value = "", params = "format=dialog")
    public ModelAndView chooseSttlement(SettlementApplicationQueryPageable model)
    {
        List<Sort> listSort = new ArrayList<Sort>();
        Sort sort = new Sort();
        sort.setDirection("desc");
        sort.setProperty("updated_date");
        model.setPageSize(5);
        listSort.add(sort);
        model.setSort(listSort);
        List<SettlementApplication> list = settlementApplicationService.getSettlementApplicationByChannelId(model);

        ModelAndView view = new ModelAndView("/smc/commission/withdraw_settlement_application_table");
        view.addObject("sttlementList", list);
        view.addObject("page", model);
        return view;
    }

    public static void main(String[] args)
    {
        System.out.println(UUID.randomUUID().toString().toUpperCase());
    }
}