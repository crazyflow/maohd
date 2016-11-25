package com.cbecs.smc.cfpclient.bank.jsb.message;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200142RequestBody
{
    public JSB200142RequestBody()
    {
        Date now = new Date();
        systemId = "4";
        cmsCorpNo = "2011776388"; // 2000000038 2011776388
        beginDate = DateFormatUtils.format(DateUtils.addDays(now, -7), "yyyy-MM-dd");
        endDate = DateFormatUtils.format(now, "yyyy-MM-dd");
        acno = "90260188000090840"; // 31020188000003346 //90260188000090840
        curCode = "";
        amtFrom = "";
        amtTo = "";
    }

    @XmlElement(name = "system_id")
    private String systemId;

    @XmlElement(name = "cms_corp_no")
    private String cmsCorpNo;

    @XmlElement(name = "begin_date")
    private String beginDate;

    @XmlElement(name = "end_date")
    private String endDate;

    @XmlElement(name = "acno")
    private String acno;

    @XmlElement(name = "cur_code")
    private String curCode;

    @XmlElement(name = "amt_from")
    private String amtFrom;

    @XmlElement(name = "amt_to")
    private String amtTo;

    public String getSystemId()
    {
        return systemId;
    }

    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    public String getCmsCorpNo()
    {
        return cmsCorpNo;
    }

    public void setCmsCorpNo(String cmsCorpNo)
    {
        this.cmsCorpNo = cmsCorpNo;
    }

    public String getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(String beginDate)
    {
        this.beginDate = beginDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

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

    public String getAmtFrom()
    {
        return amtFrom;
    }

    public void setAmtFrom(String amtFrom)
    {
        this.amtFrom = amtFrom;
    }

    public String getAmtTo()
    {
        return amtTo;
    }

    public void setAmtTo(String amtTo)
    {
        this.amtTo = amtTo;
    }
}