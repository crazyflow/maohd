package com.cbecs.smc.cfpclient.bank.jsb.message;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.time.DateFormatUtils;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200143RequestBody
{
    public JSB200143RequestBody()
    {
        Date now = new Date();
        systemId = "4";
        batchNo = UUID.randomUUID().toString();
        trAcdt = DateFormatUtils.format(now, "yyyy-MM-dd");
    }

    @XmlElement(name = "system_id")
    private String systemId;

    @XmlElement(name = "batch_no")
    private String batchNo;

    @XmlElement(name = "tr_acdt")
    private String trAcdt;

    @XmlElement(name = "req_list")
    private List<JSB200143ReqItem> reqItems;

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

    public String getTrAcdt()
    {
        return trAcdt;
    }

    public void setTrAcdt(String trAcdt)
    {
        this.trAcdt = trAcdt;
    }

    public List<JSB200143ReqItem> getReqItems()
    {
        return reqItems;
    }

    public void setReqItems(List<JSB200143ReqItem> reqItems)
    {
        this.reqItems = reqItems;
    }

    public static final class JSB200143ReqItem
    {
        @XmlElement(name = "tpp_no")
        private String tppNo;

        @XmlElement(name = "settle_instrument")
        private String settleInstrument;

        @XmlElement(name = "country_code")
        private String countryCode;

        public String getTppNo()
        {
            return tppNo;
        }

        public void setTppNo(String tppNo)
        {
            this.tppNo = tppNo;
        }

        public String getSettleInstrument()
        {
            return settleInstrument;
        }

        public void setSettleInstrument(String settleInstrument)
        {
            this.settleInstrument = settleInstrument;
        }

        public String getCountryCode()
        {
            return countryCode;
        }

        public void setCountryCode(String countryCode)
        {
            this.countryCode = countryCode;
        }

    }
}