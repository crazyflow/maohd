package com.cbecs.workflow.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkFlowTrackItem
{
    /**
    * 从节点
    */
    @JsonProperty("NodeId")
    public int nodeId;
    /**
    * 到节点
    */
    @JsonProperty("ToNodeId")
    public int toNodeId;
    /**
    * 耗时
    */
    @JsonProperty("CostTimeFullInfo")
    public String costTimeFullInfo;
    /**
    * 执行人员信息
    */
    @JsonProperty("ExcuteEmployeeInfos")
    public List<EmployeeInfo> excuteEmployeeInfos;
    /**
    * 流程名称
    */
    @JsonProperty("FlowName")
    public String flowName;
    /**
    * 匹配的条件
    */
    @JsonProperty("MatchConditions")
    public String matchConditions;
    /**
    * 节点名称
    */
    @JsonProperty("NodeName")
    public String nodeName;
    /**
    * 工作流标题
    */
    @JsonProperty("WorkFlowTitle")
    public String workFlowTitle;
    public int getNodeId()
    {
        return nodeId;
    }
    public void setNodeId(int nodeId)
    {
        this.nodeId = nodeId;
    }
    public int getToNodeId()
    {
        return toNodeId;
    }
    public void setToNodeId(int toNodeId)
    {
        this.toNodeId = toNodeId;
    }
    public String getCostTimeFullInfo()
    {
        return costTimeFullInfo;
    }
    public void setCostTimeFullInfo(String costTimeFullInfo)
    {
        this.costTimeFullInfo = costTimeFullInfo;
    }
    public List<EmployeeInfo> getExcuteEmployeeInfos()
    {
        return excuteEmployeeInfos;
    }
    public void setExcuteEmployeeInfos(List<EmployeeInfo> excuteEmployeeInfos)
    {
        this.excuteEmployeeInfos = excuteEmployeeInfos;
    }
    public String getFlowName()
    {
        return flowName;
    }
    public void setFlowName(String flowName)
    {
        this.flowName = flowName;
    }
    public String getMatchConditions()
    {
        return matchConditions;
    }
    public void setMatchConditions(String matchConditions)
    {
        this.matchConditions = matchConditions;
    }
    public String getNodeName()
    {
        return nodeName;
    }
    public void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }
    public String getWorkFlowTitle()
    {
        return workFlowTitle;
    }
    public void setWorkFlowTitle(String workFlowTitle)
    {
        this.workFlowTitle = workFlowTitle;
    }    
    
}
