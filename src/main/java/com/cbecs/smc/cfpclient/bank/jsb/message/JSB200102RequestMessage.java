package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cbecs.smc.cfpclient.RequestMessage;

@XmlRootElement(name = "ap")
@XmlAccessorType(XmlAccessType.NONE)
public class JSB200102RequestMessage implements RequestMessage
{
    public JSB200102RequestMessage()
    {
        head = new JSBRequestHead();
        body = new JSB200102RequestBody();

        head.setTrCode("200102");
    }

    @XmlElement(name = "head")
    private JSBRequestHead head;

    @XmlElement(name = "body")
    private JSB200102RequestBody body;

    public JSBRequestHead getHead()
    {
        return head;
    }

    public void setHead(JSBRequestHead head)
    {
        this.head = head;
    }

    public JSB200102RequestBody getBody()
    {
        return body;
    }

    public void setBody(JSB200102RequestBody body)
    {
        this.body = body;
    }
}
