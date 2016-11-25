package com.cbecs.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkFlowInfo
{
    /**
    * 工作流ID
    */
    @JsonProperty("WorkFlowId")
    private int workFlowId;
    /**
    * 流程ID
    */
    @JsonProperty("FlowId")
    private int flowId;
    /**
    * 当前节点ID
    */
    @JsonProperty("CurrentNodeId")
    private int currentNodeId;
    /**
    * 流程开始节点
    */
    @JsonProperty("StartNodeId")
    private int startNodeId;
    /**
    * 流程结束节点ID
    */
    @JsonProperty("EndNodeId")
    private int endNodeId;
    /**
    * 父工作流ID
    */
    @JsonProperty("ParentWorkFlowId")
    private int parentWorkFlowId;
    /**
    * 父流程节点ID
    */
    @JsonProperty("ParentFlowNodeId")
    //用于标识当前工作流程是从父工作流哪个节点触发的（解决不同节点下存在相同的子流程触发，无法区分）
    private int parentFlowNodeId;
    /**
    * 业务编号
    */
    @JsonProperty("BusinessCode")
    private String businessCode;
    /**
    * 来源主要标记
    */
    @JsonProperty("SourceMainMark")
    private String sourceMainMark;
    /**
    * 来源次要标记
    */
    @JsonProperty("SourceSubMark")
    private String sourceSubMark;
    /**
    * 发起人
    */
    @JsonProperty("Starter")
    private String starter;
    /**
    * 发起人名称
    */
    @JsonProperty("StarterName")
    private String starterName;
    /**
    * 状态
    */
    @JsonProperty("State")
    private int state;
    /**
    * 标题
    */
    @JsonProperty("Title")
    private String title;
    /**
    * 待办人员数量
    */
    @JsonProperty("ToDoEmployeesNumber")
    private int toDoEmployeesNumber;
    
    public int getWorkFlowId()
    {
        return workFlowId;
    }
    public void setWorkFlowId(int workFlowId)
    {
        this.workFlowId = workFlowId;
    }
    public int getFlowId()
    {
        return flowId;
    }
    public void setFlowId(int flowId)
    {
        this.flowId = flowId;
    }
    public int getCurrentNodeId()
    {
        return currentNodeId;
    }
    public void setCurrentNodeId(int currentNodeId)
    {
        this.currentNodeId = currentNodeId;
    }
    public int getStartNodeId()
    {
        return startNodeId;
    }
    public void setStartNodeId(int startNodeId)
    {
        this.startNodeId = startNodeId;
    }
    public int getEndNodeId()
    {
        return endNodeId;
    }
    public void setEndNodeId(int endNodeId)
    {
        this.endNodeId = endNodeId;
    }
    public int getParentWorkFlowId()
    {
        return parentWorkFlowId;
    }
    public void setParentWorkFlowId(int parentWorkFlowId)
    {
        this.parentWorkFlowId = parentWorkFlowId;
    }
    public int getParentFlowNodeId()
    {
        return parentFlowNodeId;
    }
    public void setParentFlowNodeId(int parentFlowNodeId)
    {
        this.parentFlowNodeId = parentFlowNodeId;
    }
    public String getBusinessCode()
    {
        return businessCode;
    }
    public void setBusinessCode(String businessCode)
    {
        this.businessCode = businessCode;
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
    public String getStarter()
    {
        return starter;
    }
    public void setStarter(String starter)
    {
        this.starter = starter;
    }
    public String getStarterName()
    {
        return starterName;
    }
    public void setStarterName(String starterName)
    {
        this.starterName = starterName;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public int getToDoEmployeesNumber()
    {
        return toDoEmployeesNumber;
    }
    public void setToDoEmployeesNumber(int toDoEmployeesNumber)
    {
        this.toDoEmployeesNumber = toDoEmployeesNumber;
    }
    public int getState()
    {
        return state;
    }
    public void setState(int state)
    {
        this.state = state;
    }
    
    
}
