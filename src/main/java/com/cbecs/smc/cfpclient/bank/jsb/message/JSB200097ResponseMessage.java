package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cbecs.smc.cfpclient.ResponseMessage;

@XmlRootElement(name = "ap")
@XmlAccessorType(XmlAccessType.NONE)
public class JSB200097ResponseMessage implements ResponseMessage
{
    public JSB200097ResponseMessage()
    {
        head = new JSBResponseHead();
        body = new JSB200097ResponseBody();
    }

    @XmlElement(name = "head")
    private JSBResponseHead head;

    @XmlElement(name = "body")
    private JSB200097ResponseBody body;

    public JSBResponseHead getHead()
    {
        return head;
    }

    public void setHead(JSBResponseHead head)
    {
        this.head = head;
    }

    public JSB200097ResponseBody getBody()
    {
        return body;
    }

    public void setBody(JSB200097ResponseBody body)
    {
        this.body = body;
    }
}
