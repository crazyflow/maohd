package com.cbecs.scrm.channel.model;

public class ChannelBusiness implements java.io.Serializable
{
    private static final long serialVersionUID = -4228893059043755141L;
    /**
     * 渠道商id
     */
    private String channelId;
    /**
     * 渠道商名称
     */
    private String channelName;

    /**
     * 主联系人
     */
    private String contactName;

    /**
     * 签约主体
     */
    private int businessMainBody;

    /**
     * 合作模式
     */
    private int cooperationMode;

    public String getChannelId()
    {
        return channelId;
    }

    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public int getBusinessMainBody()
    {
        return businessMainBody;
    }

    public void setBusinessMainBody(int businessMainBody)
    {
        this.businessMainBody = businessMainBody;
    }

    public int getCooperationMode()
    {
        return cooperationMode;
    }

    public void setCooperationMode(int cooperationMode)
    {
        this.cooperationMode = cooperationMode;
    }

}
