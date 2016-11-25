package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cbecs.smc.cfpclient.RequestMessage;

@XmlRootElement(name = "ap")
@XmlAccessorType(XmlAccessType.NONE)
public class JSB200098RequestMessage implements RequestMessage
{
    public JSB200098RequestMessage()
    {
        head = new JSBRequestHead();
        body = new JSB200098RequestBody();

        head.setTrCode("200098");
    }

    @XmlElement(name = "head")
    private JSBRequestHead head;

    @XmlElement(name = "body")
    private JSB200098RequestBody body;

    public JSBRequestHead getHead()
    {
        return head;
    }

    public void setHead(JSBRequestHead head)
    {
        this.head = head;
    }

    public JSB200098RequestBody getBody()
    {
        return body;
    }

    public void setBody(JSB200098RequestBody body)
    {
        this.body = body;
    }
}
