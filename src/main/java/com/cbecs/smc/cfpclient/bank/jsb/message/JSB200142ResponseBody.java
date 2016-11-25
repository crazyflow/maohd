package com.cbecs.smc.cfpclient.bank.jsb.message;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200142ResponseBody
{
    public static final class JSB200142RespItem
    {
        private String systemId;
        private String cmsCorpNo;
        private String corpCnName;
        private Date trAcdt;
        private String serialNo;
        private String curCode;
        private double amt;
        private String remtCurCode;
        private double remtAmt;
        private String payAcname;
        private String remtBk;
        private String details;
        private String acno;
        private String asAcno;
        private String asAcname;
        private Date valueDt;
        private String addies;

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

        public String getCorpCnName()
        {
            return corpCnName;
        }

        public void setCorpCnName(String corpCnName)
        {
            this.corpCnName = corpCnName;
        }

        public Date getTrAcdt()
        {
            return trAcdt;
        }

        public void setTrAcdt(Date trAcdt)
        {
            this.trAcdt = trAcdt;
        }

        public String getSerialNo()
        {
            return serialNo;
        }

        public void setSerialNo(String serialNo)
        {
            this.serialNo = serialNo;
        }

        public String getCurCode()
        {
            return curCode;
        }

        public void setCurCode(String curCode)
        {
            this.curCode = curCode;
        }

        public double getAmt()
        {
            return amt;
        }

        public void setAmt(double amt)
        {
            this.amt = amt;
        }

        public String getRemtCurCode()
        {
            return remtCurCode;
        }

        public void setRemtCurCode(String remtCurCode)
        {
            this.remtCurCode = remtCurCode;
        }

        public double getRemtAmt()
        {
            return remtAmt;
        }

        public void setRemtAmt(double remtAmt)
        {
            this.remtAmt = remtAmt;
        }

        public String getPayAcname()
        {
            return payAcname;
        }

        public void setPayAcname(String payAcname)
        {
            this.payAcname = payAcname;
        }

        public String getRemtBk()
        {
            return remtBk;
        }

        public void setRemtBk(String remtBk)
        {
            this.remtBk = remtBk;
        }

        public String getDetails()
        {
            return details;
        }

        public void setDetails(String details)
        {
            this.details = details;
        }

        public String getAcno()
        {
            return acno;
        }

        public void setAcno(String acno)
        {
            this.acno = acno;
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

        public Date getValueDt()
        {
            return valueDt;
        }

        public void setValueDt(Date valueDt)
        {
            this.valueDt = valueDt;
        }

        public String getAddies()
        {
            return addies;
        }

        public void setAddies(String addies)
        {
            this.addies = addies;
        }
    }

    public JSB200142ResponseBody()
    {
        systemId = "";
        cmsCorpNo = "";
        totalCount = "";
        fileName = "";
        returnCode = "";
    }

    @XmlElement(name = "host_serial_no")
    private String hostSerialNo;

    @XmlElement(name = "stat")
    private String stat;

    @XmlElement(name = "system_id")
    private String systemId;

    @XmlElement(name = "cms_corp_no")
    private String cmsCorpNo;

    @XmlElement(name = "total_count")
    private String totalCount;

    @XmlElement(name = "file_name")
    private String fileName;

    @XmlElement(name = "return_code")
    private String returnCode;

    @XmlTransient
    private List<JSB200142RespItem> respItems;

    public String getHostSerialNo()
    {
        return hostSerialNo;
    }

    public void setHostSerialNo(String hostSerialNo)
    {
        this.hostSerialNo = hostSerialNo;
    }

    public String getStat()
    {
        return stat;
    }

    public void setStat(String stat)
    {
        this.stat = stat;
    }

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

    public String getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(String totalCount)
    {
        this.totalCount = totalCount;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(String returnCode)
    {
        this.returnCode = returnCode;
    }

    public List<JSB200142RespItem> getRespItems()
    {
        return respItems;
    }

    public void setRespItems(List<JSB200142RespItem> respItems)
    {
        this.respItems = respItems;
    }
}