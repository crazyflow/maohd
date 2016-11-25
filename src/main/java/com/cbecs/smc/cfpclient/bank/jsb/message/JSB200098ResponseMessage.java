package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cbecs.smc.cfpclient.ResponseMessage;

@XmlRootElement(name = "ap")
@XmlAccessorType(XmlAccessType.NONE)
public class JSB200098ResponseMessage implements ResponseMessage
{
    public JSB200098ResponseMessage()
    {
        head = new JSBResponseHead();
        body = new JSB200098ResponseBody();
    }

    @XmlElement(name = "head")
    private JSBResponseHead head;

    @XmlElement(name = "body")
    private JSB200098ResponseBody body;

    public JSBResponseHead getHead()
    {
        return head;
    }

    public void setHead(JSBResponseHead head)
    {
        this.head = head;
    }

    public JSB200098ResponseBody getBody()
    {
        return body;
    }

    public void setBody(JSB200098ResponseBody body)
    {
        this.body = body;
    }
}
