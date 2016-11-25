package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cbecs.smc.cfpclient.RequestMessage;

@XmlRootElement(name = "ap")
@XmlAccessorType(XmlAccessType.NONE)
public class JSB200097RequestMessage implements RequestMessage
{
    public JSB200097RequestMessage()
    {
        head = new JSBRequestHead();
        body = new JSB200097RequestBody();

        head.setTrCode("200097");
    }

    @XmlElement(name = "head")
    private JSBRequestHead head;

    @XmlElement(name = "body")
    private JSB200097RequestBody body;

    public JSBRequestHead getHead()
    {
        return head;
    }

    public void setHead(JSBRequestHead head)
    {
        this.head = head;
    }

    public JSB200097RequestBody getBody()
    {
        return body;
    }

    public void setBody(JSB200097RequestBody body)
    {
        this.body = body;
    }
}
