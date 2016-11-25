package com.cbecs.workflow.model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnInfo
{
    /**
    * 业务编号
    */
    @JsonProperty("BusinessCode")
    private String businessCode;
    /**
    * 返回信息序列化
    */
    @JsonProperty("Infos")
    private HashMap<String, String> infos;
    /**
    * 返回提示信息
    */
    @JsonProperty("Message")
    private String message;
    /**
    * 安全Code
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
    * 返回结果状态
    */
    @JsonProperty("Status")
    private String status;
    /**
    * 工作流ID
    */
    @JsonProperty("WorkFlowId")
    private int workFlowId;
    /**
    * 工作流状态
    */
    //空白 = 10, 草稿 = 20, 运行中 = 30, 退回 = 40, 挂起 = 50, 撤销 = 60, 转发 = 70, 已完成 = 80, 已删除 = 90
    @JsonProperty("WorkFlowState")
    private int workFlowState;
    
    public String getBusinessCode()
    {
        return businessCode;
    }
    public void setBusinessCode(String businessCode)
    {
        this.businessCode = businessCode;
    }
   
    public HashMap<String, String> getInfos()
    {
        return infos;
    }
    public void setInfos(HashMap<String, String> infos)
    {
        this.infos = infos;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
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
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public int getWorkFlowId()
    {
        return workFlowId;
    }
    public void setWorkFlowId(int workFlowId)
    {
        this.workFlowId = workFlowId;
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
