package com.cbecs.workflow.model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessInteractiveInfo
{
    /**
    * 业务编号
    */
    @JsonProperty("BusinessCode")
    private String businessCode;
    /**
    * 业务操作
    */
    // 发起 = 1，保存草稿 = 2，执行 = 3，回退 = 4，挂起 = 5，取消挂起 = 6，撤销 = 7，转发 = 8，删除 = 9，结束 = 10
    @JsonProperty("BusinessOperate")
    private int businessOperate;
    /**
    * 员工ID
    */
    @JsonProperty("EmployeeId")
    private String employeeId;
    /**
    * 员工姓名
    */
    @JsonProperty("EmployeeName")
    private String employeeName;
    /**
    * 流程编号
    */
    @JsonProperty("FlowCode")
    private String flowCode;
    /**
    * 如果启用验证了，不能为空
    */
    @JsonProperty("SignCode")
    private String signCode;
    /**
    * 来源主标识
    */
    @JsonProperty("SourceMainMark")
    private String sourceMainMark;
    /**
    * 来源次标识
    */
    @JsonProperty("SourceSubMark")
    private String sourceSubMark;
    /**
    * 工作流ID
    */
    @JsonProperty("WorkFlowId")
    private int workFlowId;
    /**
    * 工作内容
    */
    @JsonProperty("WorkContext")
    private String workContext;
    /**
    * 工作主题
    */
    @JsonProperty("WorkSubject")
    private String workSubject;
    /**
    * 额外信息
    */
    @JsonProperty("Infos")
    private HashMap<String, String> infos;
    
    public String getBusinessCode()
    {
        return businessCode;
    }
    public void setBusinessCode(String businessCode)
    {
        this.businessCode = businessCode;
    }
    public int getBusinessOperate()
    {
        return businessOperate;
    }
    public void setBusinessOperate(int businessOperate)
    {
        this.businessOperate = businessOperate;
    }
    public String getEmployeeId()
    {
        return employeeId;
    }
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }
    public String getEmployeeName()
    {
        return employeeName;
    }
    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }
    public String getFlowCode()
    {
        return flowCode;
    }
    public void setFlowCode(String flowCode)
    {
        this.flowCode = flowCode;
    }
    public String getSignCode()
    {
        return signCode;
    }
    public void setSignCode(String signCode)
    {
        this.signCode = signCode;
    }
    public String getSourceMainMark()
    {
        return sourceMainMark;
    }
    public void setSourceMainMark(String sourceMainMark)
    {
        this.sourceMainMark = sourceMainMark;
    }
    public String getSourceSubMark()
    {
        return sourceSubMark;
    }
    public void setSourceSubMark(String sourceSubMark)
    {
        this.sourceSubMark = sourceSubMark;
    }
    public int getWorkFlowId()
    {
        return workFlowId;
    }
    public void setWorkFlowId(int workFlowId)
    {
        this.workFlowId = workFlowId;
    }
    public String getWorkContext()
    {
        return workContext;
    }
    public void setWorkContext(String workContext)
    {
        this.workContext = workContext;
    }
    public String getWorkSubject()
    {
        return workSubject;
    }
    public void setWorkSubject(String workSubject)
    {
        this.workSubject = workSubject;
    }
    public HashMap<String, String> getInfos()
    {
        return infos;
    }
    public void setInfos(HashMap<String, String> infos)
    {
        this.infos = infos;
    }   
    
}
