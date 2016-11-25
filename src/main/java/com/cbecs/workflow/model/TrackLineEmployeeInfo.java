package com.cbecs.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackLineEmployeeInfo
{
    /**
    * 职工ID
    */
    @JsonProperty("Id")
    private String id;
    /**
    * 姓名
    */
    @JsonProperty("Name")
    private String name;
    /**
    * 节点名称
    */
    @JsonProperty("NodeName")
    private String nodeName;
    /**
    * 工作状态，100待办200已办
    */
    @JsonProperty("WorkState")
    private int workState;
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getNodeName()
    {
        return nodeName;
    }
    public void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }
    public int getWorkState()
    {
        return workState;
    }
    public void setWorkState(int workState)
    {
        this.workState = workState;
    }
    
    
}
