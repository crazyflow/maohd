package com.cbecs.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnSimpleInfo<T>
{
    /**
    * 主要信息
    */
    @JsonProperty("Infos")
    private T infos;
    /**
    * 返回提示信息
    */
    @JsonProperty("Message")
    private String message;
    /**
    * 返回结果状态
    */
    @JsonProperty("Status")
    private String status;
    
    public T getInfos()
    {
        return infos;
    }
    public void setInfos(T infos)
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
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }   
    
}
