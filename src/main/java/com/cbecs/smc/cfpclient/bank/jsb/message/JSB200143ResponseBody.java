package com.cbecs.smc.cfpclient.bank.jsb.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200143ResponseBody
{
    public JSB200143ResponseBody()
    {
        systemId = "";
        batchNo = "";
        returnCode = "";
    }

    @XmlElement(name = "host_serial_no")
    private String hostSerialNo;

    @XmlElement(name = "stat")
    private String stat;

    @XmlElement(name = "system_id")
    private String systemId;

    @XmlElement(name = "batch_no")
    private String batchNo;

    @XmlElement(name = "resp_list")
    private List<JSB200143RespItem> respItems;

    @XmlElement(name = "return_code")
    private String returnCode;

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

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public List<JSB200143RespItem> getRespItems()
    {
        return respItems;
    }

    public void setRespItems(List<JSB200143RespItem> respItems)
    {
        this.respItems = respItems;
    }

    public String getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(String returnCode)
    {
        this.returnCode = returnCode;
    }

    public static final class JSB200143RespItem
    {
        @XmlElement(name = "tpp_no")
        private String tppNo;

        @XmlElement(name = "proc_status")
        private String procStatus;

        @XmlElement(name = "fail_result")
        private String failResult;

        public String getTppNo()
        {
            return tppNo;
        }

        public void setTppNo(String tppNo)
        {
            this.tppNo = tppNo;
        }

        public String getProcStatus()
        {
            return procStatus;
        }

        public void setProcStatus(String procStatus)
        {
            this.procStatus = procStatus;
        }

        public String getFailResult()
        {
            return failResult;
        }

        public void setFailResult(String failResult)
        {
            this.failResult = failResult;
        }

    }
}