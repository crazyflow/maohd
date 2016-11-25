package com.cbecs.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkFlowCommonBody
{
    /**
    * 员工ID
    */
    @JsonProperty("EmployeeId")
    private String employeeId;
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
    
    public String getEmployeeId()
    {
        return employeeId;
    }
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
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
    
    
}
