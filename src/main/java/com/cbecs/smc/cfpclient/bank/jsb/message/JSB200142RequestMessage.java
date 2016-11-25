package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cbecs.smc.cfpclient.RequestMessage;

@XmlRootElement(name = "ap")
@XmlAccessorType(XmlAccessType.NONE)
public class JSB200142RequestMessage implements RequestMessage
{
    public JSB200142RequestMessage()
    {
        head = new JSBRequestHead();
        body = new JSB200142RequestBody();

        head.setTrCode("200142");
    }

    @XmlElement(name = "head")
    private JSBRequestHead head;

    @XmlElement(name = "body")
    private JSB200142RequestBody body;

    public JSBRequestHead getHead()
    {
        return head;
    }

    public void setHead(JSBRequestHead head)
    {
        this.head = head;
    }

    public JSB200142RequestBody getBody()
    {
        return body;
    }

    public void setBody(JSB200142RequestBody body)
    {
        this.body = body;
    }
}
