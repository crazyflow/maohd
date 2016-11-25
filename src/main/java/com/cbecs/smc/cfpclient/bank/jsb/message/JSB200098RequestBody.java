package com.cbecs.smc.cfpclient.bank.jsb.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200098RequestBody
{
    public JSB200098RequestBody()
    {
        acno = "";
        curCode = "";
        fieldNum = "";
        recordNum = "";
    }

    @XmlElement(name = "acno")
    private String acno;

    @XmlElement(name = "cur_code")
    private String curCode;

    @XmlElement(name = "file_name")
    private String fileName;

    @XmlElement(name = "field_num")
    private String fieldNum;

    @XmlElement(name = "record_num")
    private String recordNum;

    @XmlElement(name = "serial_record")
    private String serialRecord;

    @XmlTransient
    private List<JSB200098ReqItem> respItems;

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

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFieldNum()
    {
        return fieldNum;
    }

    public void setFieldNum(String fieldNum)
    {
        this.fieldNum = fieldNum;
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

    public List<JSB200098ReqItem> getRespItems()
    {
        return respItems;
    }

    public void setRespItems(List<JSB200098ReqItem> respItems)
    {
        this.respItems = respItems;
    }

    public static final class JSB200098ReqItem
    {
        public JSB200098ReqItem()
        {
            AccrualMode = "0";
            DrRate = "";
            CrRate = "";
            PayMode = 1;
            ExceedLimit = 0;
            AccrualFlag = "0";
            AssignFlag = "0";
            NoticeFlag = "0";
            PresFlag = "0";
            Inherit = "1";
        }

        private String OperFlag;
        private String Acno;
        private String CurCode;
        private String AsAcno;
        private String AsAcname;
        private String SupAsAcno;
        private String AccrualMode;
        private String DrRate;
        private String CrRate;
        private int PayMode;
        private double ExceedLimit;
        private String AccrualFlag;
        private String AssignFlag;
        private String NoticeFlag;
        private String PresFlag;
        private String Inherit;

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

        public String getSupAsAcno()
        {
            return SupAsAcno;
        }

        public void setSupAsAcno(String supAsAcno)
        {
            SupAsAcno = supAsAcno;
        }

        public String getAccrualMode()
        {
            return AccrualMode;
        }

        public void setAccrualMode(String accrualMode)
        {
            AccrualMode = accrualMode;
        }

        public String getDrRate()
        {
            return DrRate;
        }

        public void setDrRate(String drRate)
        {
            DrRate = drRate;
        }

        public String getCrRate()
        {
            return CrRate;
        }

        public void setCrRate(String crRate)
        {
            CrRate = crRate;
        }

        public int getPayMode()
        {
            return PayMode;
        }

        public void setPayMode(int payMode)
        {
            PayMode = payMode;
        }

        public double getExceedLimit()
        {
            return ExceedLimit;
        }

        public void setExceedLimit(double exceedLimit)
        {
            ExceedLimit = exceedLimit;
        }

        public String getAccrualFlag()
        {
            return AccrualFlag;
        }

        public void setAccrualFlag(String accrualFlag)
        {
            AccrualFlag = accrualFlag;
        }

        public String getAssignFlag()
        {
            return AssignFlag;
        }

        public void setAssignFlag(String assignFlag)
        {
            AssignFlag = assignFlag;
        }

        public String getNoticeFlag()
        {
            return NoticeFlag;
        }

        public void setNoticeFlag(String noticeFlag)
        {
            NoticeFlag = noticeFlag;
        }

        public String getPresFlag()
        {
            return PresFlag;
        }

        public void setPresFlag(String presFlag)
        {
            PresFlag = presFlag;
        }

        public String getInherit()
        {
            return Inherit;
        }

        public void setInherit(String inherit)
        {
            Inherit = inherit;
        }

    }
}