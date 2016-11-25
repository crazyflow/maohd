package com.cbecs.smc.cfpclient.bank.jsb.message;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.time.DateFormatUtils;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSBRequestHead
{
    public JSBRequestHead()
    {
        trCode = "";
        cmsCorpNo = "";
        userNo = "";
        orgCode = "";
        serialNo = "";
        reqNo = System.currentTimeMillis() + "";
        Date now = new Date();
        trAcdt = DateFormatUtils.format(now, "yyyyMMdd");
        trTime = DateFormatUtils.format(now, "HHmmss");
        channel = "5";
        sign = "0";
        fileFlag = "0";
        reserved = "";
    }

    @XmlElement(name = "tr_code")
    private String trCode;

    @XmlElement(name = "cms_corp_no")
    private String cmsCorpNo;

    @XmlElement(name = "user_no")
    private String userNo;

    @XmlElement(name = "org_code")
    private String orgCode;

    @XmlElement(name = "serial_no")
    private String serialNo;

    @XmlElement(name = "req_no")
    private String reqNo;

    @XmlElement(name = "tr_acdt")
    private String trAcdt;

    @XmlElement(name = "tr_time")
    private String trTime;

    @XmlElement(name = "channel")
    private String channel;

    @XmlElement(name = "sign")
    private String sign;

    @XmlElement(name = "file_flag")
    private String fileFlag;

    @XmlElement(name = "reserved")
    private String reserved;

    public String getTrCode()
    {
        return trCode;
    }

    public void setTrCode(String trCode)
    {
        this.trCode = trCode;
    }

    public String getCmsCorpNo()
    {
        return cmsCorpNo;
    }

    public void setCmsCorpNo(String cmsCorpNo)
    {
        this.cmsCorpNo = cmsCorpNo;
    }

    public String getUserNo()
    {
        return userNo;
    }

    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getReqNo()
    {
        return reqNo;
    }

    public void setReqNo(String reqNo)
    {
        this.reqNo = reqNo;
    }

    public String getTrAcdt()
    {
        return trAcdt;
    }

    public void setTrAcdt(String trAcdt)
    {
        this.trAcdt = trAcdt;
    }

    public String getTrTime()
    {
        return trTime;
    }

    public void setTrTime(String trTime)
    {
        this.trTime = trTime;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public String getSign()
    {
        return sign;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }

    public String getFileFlag()
    {
        return fileFlag;
    }

    public void setFileFlag(String fileFlag)
    {
        this.fileFlag = fileFlag;
    }

    public String getReserved()
    {
        return reserved;
    }

    public void setReserved(String reserved)
    {
        this.reserved = reserved;
    }
}