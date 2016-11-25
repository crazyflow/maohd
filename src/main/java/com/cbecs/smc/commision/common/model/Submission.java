package com.cbecs.smc.commision.common.model;

import java.io.Serializable;

public class Submission implements Serializable
{
    private static final long serialVersionUID = 368722460691438896L;
    
    private String tabName;//tab表头名
    private String tabUrl;//跳转地址
    private String tabId;//跳转tabId，若为null则新添加tab页
    private String message;//返回显示业务信息
    
    public String getTabName()
    {
        return tabName;
    }
    public void setTabName(String tabName)
    {
        this.tabName = tabName;
    }
    public String getTabUrl()
    {
        return tabUrl;
    }
    public void setTabUrl(String tabUrl)
    {
        this.tabUrl = tabUrl;
    }
    public String getTabId()
    {
        return tabId;
    }
    public void setTabId(String tabId)
    {
        this.tabId = tabId;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    } 
   
}