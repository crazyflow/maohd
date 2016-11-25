package com.cbecs.smc.cfpclient.bank.jsb.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200098ResponseBody
{
    @XmlElement(name = "stat")
    private String stat;

    @XmlElement(name = "record_num")
    private String recordNum;

    @XmlElement(name = "serial_record")
    private String serialRecord;

    @XmlTransient
    private List<JSB200098RespItem> respItems;

    public String getStat()
    {
        return stat;
    }

    public void setStat(String stat)
    {
        this.stat = stat;
    }

    public String getRecordNum()
    {
        return recordNum;
    }

    public void setRecordNum(String recordNum)
    {
        this.recordNum = recordNum;
    }

    public String getSerialRecord()
    {
        return serialRecord;
    }

    public void setSerialRecord(String serialRecord)
    {
        this.serialRecord = serialRecord;
    }

    public List<JSB200098RespItem> getRespItems()
    {
        return respItems;
    }

    public void setRespItems(List<JSB200098RespItem> respItems)
    {
        this.respItems = respItems;
    }

    public static final class JSB200098RespItem
    {
        private String OperFlag;
        private String Acno;
        private String CurCode;
        private String AsAcno;
        private String AsAcname;
        private String ErrInfo;

        public String getOperFlag()
        {
            return OperFlag;
        }

        public void setOperFlag(String operFlag)
        {
            OperFlag = operFlag;
        }

        public String getAcno()
        {
            return Acno;
        }

        public void setAcno(String acno)
        {
            Acno = acno;
        }

        public String getCurCode()
        {
            return CurCode;
        }

        public void setCurCode(String curCode)
        {
            CurCode = curCode;
        }

        public String getAsAcno()
        {
            return AsAcno;
        }

        public void setAsAcno(String asAcno)
        {
            AsAcno = asAcno;
        }

        public String getAsAcname()
        {
            return AsAcname;
        }

        public void setAsAcname(String asAcname)
        {
            AsAcname = asAcname;
        }

        public String getErrInfo()
        {
            return ErrInfo;
        }

        public void setErrInfo(String errInfo)
        {
            ErrInfo = errInfo;
        }
    }
}