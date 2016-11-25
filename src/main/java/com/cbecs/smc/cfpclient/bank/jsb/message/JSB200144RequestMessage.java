package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cbecs.smc.cfpclient.RequestMessage;

@XmlRootElement(name = "ap")
@XmlAccessorType(XmlAccessType.NONE)
public class JSB200144RequestMessage implements RequestMessage
{
    public JSB200144RequestMessage()
    {
        head = new JSBRequestHead();
        body = new JSB200144RequestBody();

        head.setTrCode("200144");
    }

    @XmlElement(name = "head")
    private JSBRequestHead head;

    @XmlElement(name = "body")
    private JSB200144RequestBody body;

    public JSBRequestHead getHead()
    {
        return head;
    }

    public void setHead(JSBRequestHead head)
    {
        this.head = head;
    }

    public JSB200144RequestBody getBody()
    {
        return body;
    }

    public void setBody(JSB200144RequestBody body)
    {
        this.body = body;
    }
}
