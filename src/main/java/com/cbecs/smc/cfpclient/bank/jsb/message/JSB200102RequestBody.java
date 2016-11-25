package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200102RequestBody
{
    public JSB200102RequestBody()
    {
        acno = "";
        curCode = "";
        asAcno = "";
        asAcname = "";
    }

    @XmlElement(name = "acno")
    private String acno;

    @XmlElement(name = "cur_code")
    private String curCode;

    @XmlElement(name = "as_acno")
    private String asAcno;

    @XmlElement(name = "as_acname")
    private String asAcname;

    public String getAcno()
    {
        return acno;
    }

    public void setAcno(String acno)
    {
        this.acno = acno;
    }

    public String getCurCode()
    {
        return curCode;
    }

    public void setCurCode(String curCode)
    {
        this.curCode = curCode;
    }

    public String getAsAcno()
    {
        return asAcno;
    }

    public void setAsAcno(String asAcno)
    {
        this.asAcno = asAcno;
    }

    public String getAsAcname()
    {
        return asAcname;
    }

    public void setAsAcname(String asAcname)
    {
        this.asAcname = asAcname;
    }

}