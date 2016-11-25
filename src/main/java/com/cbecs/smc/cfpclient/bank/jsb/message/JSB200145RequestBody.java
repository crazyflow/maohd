package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200145RequestBody
{
    public JSB200145RequestBody()
    {
        curPair = "";
    }

    @XmlElement(name = "cur_pair")
    private String curPair;

    public String getCurPair()
    {
        return curPair;
    }

    public void setCurPair(String curPair)
    {
        this.curPair = curPair;
    }

}