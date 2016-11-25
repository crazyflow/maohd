package com.cbecs.smc.cfpclient.bank.jsb.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200097RequestBody
{

    public JSB200097RequestBody()
    {

    }

    @XmlElement(name = "file_name")
    private String fileName;

    @XmlElement(name = "record_num")
    private String recordNum;

    @XmlElement(name = "serial_record")
    private String serialRecord;

    @XmlTransient
    private List<JSB200097ReqItem> respItems;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
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

    public List<JSB200097ReqItem> getRespItems()
    {
        return respItems;
    }

    public void setRespItems(List<JSB200097ReqItem> respItems)
    {
        this.respItems = respItems;
    }

    public static final class JSB200097ReqItem
    {
        public JSB200097ReqItem()
        {
            stat = "";
            purpose = "";
        }

        private String stat;
        private String acno;
        private String curCode;
        private String serialNo;
        private String inAsacno;
        private String outAsacno;
        private double amt;
        private String transDate;
        private String purpose;
        private String reserved1;
        private String reserved2;
        private String reserved3;
        private String reserved4;

        public String getStat()
        {
            return stat;
        }

        public void setStat(String stat)
        {
            this.stat = stat;
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

        public String getSerialNo()
        {
            return serialNo;
        }

        public void setSerialNo(String serialNo)
        {
            this.serialNo = serialNo;
        }

        public String getInAsacno()
        {
            return inAsacno;
        }

        public void setInAsacno(String inAsacno)
        {
            this.inAsacno = inAsacno;
        }

        public String getOutAsacno()
        {
            return outAsacno;
        }

        public void setOutAsacno(String outAsacno)
        {
            this.outAsacno = outAsacno;
        }

        public double getAmt()
        {
            return amt;
        }

        public void setAmt(double amt)
        {
            this.amt = amt;
        }

        public String getTransDate()
        {
            return transDate;
        }

        public void setTransDate(String transDate)
        {
            this.transDate = transDate;
        }

        public String getPurpose()
        {
            return purpose;
        }

        public void setPurpose(String purpose)
        {
            this.purpose = purpose;
        }

        public String getReserved1()
        {
            return reserved1;
        }

        public void setReserved1(String reserved1)
        {
            this.reserved1 = reserved1;
        }

        public String getReserved2()
        {
            return reserved2;
        }

        public void setReserved2(String reserved2)
        {
            this.reserved2 = reserved2;
        }

        public String getReserved3()
        {
            return reserved3;
        }

        public void setReserved3(String reserved3)
        {
            this.reserved3 = reserved3;
        }

        public String getReserved4()
        {
            return reserved4;
        }

        public void setReserved4(String reserved4)
        {
            this.reserved4 = reserved4;
        }

    }
}