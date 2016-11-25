package com.cbecs.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeTrackInfo extends EmployeeInfo
{
    /**
    * 耗时
    */
    @JsonProperty("CostTimeFullInfo")
    private String costTimeFullInfo;
    /**
    * 匹配的条件
    */
    @JsonProperty("MatchConditions")
    private String matchConditions;
    /**
    * 节点名称
    */
    @JsonProperty("NodeName")
    private String nodeName;
    
    public String getCostTimeFullInfo()
    {
        return costTimeFullInfo;
    }
    public void setCostTimeFullInfo(String costTimeFullInfo)
    {
        this.costTimeFullInfo = costTimeFullInfo;
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
    
}
