package com.cbecs.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemListBody
{
    /**
    * 工作流ID
    */
    @JsonProperty("WorkFlowId")
    private int workFlowId;
    /**
    * 业务编号
    */
    @JsonProperty("BusinessCode")
    private String businessCode;
    /**
    * 来源次标识
    */
    @JsonProperty("SourceSubMark")
    private String sourceSubMark;
    /**
    * 发起人
    */
    @JsonProperty("Starter")
    private String starter;
    /**
    * 工作流状态
    */
    @JsonProperty("WorkFlowState")
    private int workFlowState;
    
    public int getWorkFlowId()
    {
        return workFlowId;
    }
    public void setWorkFlowId(int workFlowId)
    {
        this.workFlowId = workFlowId;
    }
    public String getBusinessCode()
    {
        return businessCode;
    }
    public void setBusinessCode(String businessCode)
    {
        this.businessCode = businessCode;
    }
    public String getSourceSubMark()
    {
        return sourceSubMark;
    }
    public void setSourceSubMark(String sourceSubMark)
    {
        this.sourceSubMark = sourceSubMark;
    }
    public String getStarter()
    {
        return starter;
    }
    public void setStarter(String starter)
    {
        this.starter = starter;
    }
    public int getWorkFlowState()
    {
        return workFlowState;
    }
    public void setWorkFlowState(int workFlowState)
    {
        this.workFlowState = workFlowState;
    }
    
  
}
