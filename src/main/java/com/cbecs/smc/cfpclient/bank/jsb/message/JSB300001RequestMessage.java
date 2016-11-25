package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cbecs.smc.cfpclient.RequestMessage;

@XmlRootElement(name = "ap")
@XmlAccessorType(XmlAccessType.NONE)
public class JSB300001RequestMessage implements RequestMessage
{
    public JSB300001RequestMessage()
    {
        head = new JSBRequestHead();
        body = new JSB300001RequestBody();

        head.setTrCode("300001");
    }

    @XmlElement(name = "head")
    private JSBRequestHead head;

    @XmlElement(name = "body")
    private JSB300001RequestBody body;

    public JSBRequestHead getHead()
    {
        return head;
    }

    public void setHead(JSBRequestHead head)
    {
        this.head = head;
    }

    public JSB300001RequestBody getBody()
    {
        return body;
    }

    public void setBody(JSB300001RequestBody body)
    {
        this.body = body;
    }
}
