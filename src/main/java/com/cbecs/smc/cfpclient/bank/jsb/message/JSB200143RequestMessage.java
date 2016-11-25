package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cbecs.smc.cfpclient.RequestMessage;

@XmlRootElement(name = "ap")
@XmlAccessorType(XmlAccessType.NONE)
public class JSB200143RequestMessage implements RequestMessage
{
    public JSB200143RequestMessage()
    {
        head = new JSBRequestHead();
        body = new JSB200143RequestBody();

        head.setTrCode("200143");
    }

    @XmlElement(name = "head")
    private JSBRequestHead head;

    @XmlElement(name = "body")
    private JSB200143RequestBody body;

    public JSBRequestHead getHead()
    {
        return head;
    }

    public void setHead(JSBRequestHead head)
    {
        this.head = head;
    }

    public JSB200143RequestBody getBody()
    {
        return body;
    }

    public void setBody(JSB200143RequestBody body)
    {
        this.body = body;
    }
}
