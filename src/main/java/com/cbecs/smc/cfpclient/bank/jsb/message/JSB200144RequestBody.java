package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200144RequestBody
{
    public JSB200144RequestBody()
    {
        systemId = "4";
        batchNo = "";
        tppNo = "";
        beginDate = "";
        endData = "";
    }

    @XmlElement(name = "system_id")
    private String systemId;

    @XmlElement(name = "batch_no")
    private String batchNo;

    @XmlElement(name = "tpp_no")
    private String tppNo;

    @XmlElement(name = "begin_date")
    private String beginDate;

    @XmlElement(name = "end_data")
    private String endData;

    public String getSystemId()
    {
        return systemId;
    }

    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getTppNo()
    {
        return tppNo;
    }

    public void setTppNo(String tppNo)
    {
        this.tppNo = tppNo;
    }

    public String getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(String beginDate)
    {
        this.beginDate = beginDate;
    }

    public String getEndData()
    {
        return endData;
    }

    public void setEndData(String endData)
    {
        this.endData = endData;
    }

}