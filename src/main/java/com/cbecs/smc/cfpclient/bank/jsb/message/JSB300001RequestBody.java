package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB300001RequestBody
{
    public JSB300001RequestBody()
    {

    }

    @XmlElement(name = "pay_acno")
    private String payAcno;

    @XmlElement(name = "pay_cur_code")
    private String payCurCode;

    @XmlElement(name = "pay_acname")
    private String payAcname;

    @XmlElement(name = "as_flag")
    private String asFlag;

    @XmlElement(name = "as_acno")
    private String asAcno;

    @XmlElement(name = "as_acname")
    private String asAcname;

    @XmlElement(name = "cert_type")
    private String certType;

    @XmlElement(name = "cert_no")
    private String certNo;

    @XmlElement(name = "rcv_acno")
    private String rcvAcno;

    @XmlElement(name = "rcv_cur_code")
    private String rcvCurCode;

    @XmlElement(name = "rcv_acname")
    private String rcvAcname;

    @XmlElement(name = "rcv_bank_no")
    private String rcvBankNo;

    @XmlElement(name = "rcv_bank_name")
    private String rcvBankName;

    @XmlElement(name = "amt")
    private double amt;

    @XmlElement(name = "bank_flag")
    private String bankFlag;

    @XmlElement(name = "urgency_flag")
    private String urgencyFlag;

    @XmlElement(name = "purpose")
    private String purpose;

    @XmlElement(name = "postscript")
    private String postscript;

    public String getPayAcno()
    {
        return payAcno;
    }

    public void setPayAcno(String payAcno)
    {
        this.payAcno = payAcno;
    }

    public String getPayCurCode()
    {
        return payCurCode;
    }

    public void setPayCurCode(String payCurCode)
    {
        this.payCurCode = payCurCode;
    }

    public String getPayAcname()
    {
        return payAcname;
    }

    public void setPayAcname(String payAcname)
    {
        this.payAcname = payAcname;
    }

    public String getAsFlag()
    {
        return asFlag;
    }

    public void setAsFlag(String asFlag)
    {
        this.asFlag = asFlag;
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

    public String getCertType()
    {
        return certType;
    }

    public void setCertType(String certType)
    {
        this.certType = certType;
    }

    public String getCertNo()
    {
        return certNo;
    }

    public void setCertNo(String certNo)
    {
        this.certNo = certNo;
    }

    public String getRcvAcno()
    {
        return rcvAcno;
    }

    public void setRcvAcno(String rcvAcno)
    {
        this.rcvAcno = rcvAcno;
    }

    public String getRcvCurCode()
    {
        return rcvCurCode;
    }

    public void setRcvCurCode(String rcvCurCode)
    {
        this.rcvCurCode = rcvCurCode;
    }

    public String getRcvAcname()
    {
        return rcvAcname;
    }

    public void setRcvAcname(String rcvAcname)
    {
        this.rcvAcname = rcvAcname;
    }

    public String getRcvBankNo()
    {
        return rcvBankNo;
    }

    public void setRcvBankNo(String rcvBankNo)
    {
        this.rcvBankNo = rcvBankNo;
    }

    public String getRcvBankName()
    {
        return rcvBankName;
    }

    public void setRcvBankName(String rcvBankName)
    {
        this.rcvBankName = rcvBankName;
    }

    public double getAmt()
    {
        return amt;
    }

    public void setAmt(double amt)
    {
        this.amt = amt;
    }

    public String getBankFlag()
    {
        return bankFlag;
    }

    public void setBankFlag(String bankFlag)
    {
        this.bankFlag = bankFlag;
    }

    public String getUrgencyFlag()
    {
        return urgencyFlag;
    }

    public void setUrgencyFlag(String urgencyFlag)
    {
        this.urgencyFlag = urgencyFlag;
    }

    public String getPurpose()
    {
        return purpose;
    }

    public void setPurpose(String purpose)
    {
        this.purpose = purpose;
    }

    public String getPostscript()
    {
        return postscript;
    }

    public void setPostscript(String postscript)
    {
        this.postscript = postscript;
    }

}