package com.cbecs.smc.cfpclient.bank.jsb.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200102ResponseBody
{
    @XmlElement(name = "acname")
    private String acname;

    @XmlElement(name = "serial_record")
    private String serialRecord;

    @XmlElement(name = "record_num")
    private String recordNum;

    @XmlElement(name = "field_num")
    private String fieldNum;

    @XmlElement(name = "file_name")
    private String fileName;

    @XmlTransient
    private List<JSB200102RespItem> respItems;

    public String getAcname()
    {
        return acname;
    }

    public void setAcname(String acname)
    {
        this.acname = acname;
    }

    public String getSerialRecord()
    {
        return serialRecord;
    }

    public void setSerialRecord(String serialRecord)
    {
        this.serialRecord = serialRecord;
    }

    public String getRecordNum()
    {
        return recordNum;
    }

    public void setRecordNum(String recordNum)
    {
        this.recordNum = recordNum;
    }

    public String getFieldNum()
    {
        return fieldNum;
    }

    public void setFieldNum(String fieldNum)
    {
        this.fieldNum = fieldNum;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public List<JSB200102RespItem> getRespItems()
    {
        return respItems;
    }

    public void setRespItems(List<JSB200102RespItem> respItems)
    {
        this.respItems = respItems;
    }

    public static final class JSB200102RespItem
    {
        private String Stat;
        private String Acno;
        private String CurCode;
        private String AsAcno;
        private String AsAcname;
        private String SupAsAcno;
        private double ExceedLimit;
        private String Inherit;
        private String AssignFlag;
        private double SelfBal;
        private String AccrualFlag;
        private String AccrualMode;
        private String AccrualCyc;
        private double DrRate;
        private double CrRate;
        private String OpenDate;
        private String CloseDate;
        private String Acname;
        private int AsLevel;
        private String PayMode;
        private String CrRateType;
        private String CrRateBasetype;
        private double CrRatePrefer;
        private double CrAmountOne;
        private double CrRateOne;
        private double CrAmountTwo;
        private double CrRateTwo;
        private double CrAmountThree;
        private double CrRateThree;
        private double CrAmountFour;
        private double CrRateFour;
        private double CrAmountFive;
        private double CrRateFive;
        private String DrRateType;
        private String DrRateBasetype;
        private double DrRatePrefer;
        private double DrAmountOne;
        private double DrRateOne;
        private double DrAmountTwo;
        private double DrRateTwo;
        private double DrAmountThree;
        private double DrRateThree;
        private double DrAmountFour;
        private double DrRateFour;
        private double DrAmountFive;
        private double DrRateFive;
        private String SupAsAcname;

        public String getStat()
        {
            return Stat;
        }

        public void setStat(String stat)
        {
            Stat = stat;
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

        public double getExceedLimit()
        {
            return ExceedLimit;
        }

        public void setExceedLimit(double exceedLimit)
        {
            ExceedLimit = exceedLimit;
        }

        public String getInherit()
        {
            return Inherit;
        }

        public void setInherit(String inherit)
        {
            Inherit = inherit;
        }

        public String getAssignFlag()
        {
            return AssignFlag;
        }

        public void setAssignFlag(String assignFlag)
        {
            AssignFlag = assignFlag;
        }

        public double getSelfBal()
        {
            return SelfBal;
        }

        public void setSelfBal(double selfBal)
        {
            SelfBal = selfBal;
        }

        public String getAccrualFlag()
        {
            return AccrualFlag;
        }

        public void setAccrualFlag(String accrualFlag)
        {
            AccrualFlag = accrualFlag;
        }

        public String getAccrualMode()
        {
            return AccrualMode;
        }

        public void setAccrualMode(String accrualMode)
        {
            AccrualMode = accrualMode;
        }

        public String getAccrualCyc()
        {
            return AccrualCyc;
        }

        public void setAccrualCyc(String accrualCyc)
        {
            AccrualCyc = accrualCyc;
        }

        public double getDrRate()
        {
            return DrRate;
        }

        public void setDrRate(double drRate)
        {
            DrRate = drRate;
        }

        public double getCrRate()
        {
            return CrRate;
        }

        public void setCrRate(double crRate)
        {
            CrRate = crRate;
        }

        public String getOpenDate()
        {
            return OpenDate;
        }

        public void setOpenDate(String openDate)
        {
            OpenDate = openDate;
        }

        public String getCloseDate()
        {
            return CloseDate;
        }

        public void setCloseDate(String closeDate)
        {
            CloseDate = closeDate;
        }

        public String getAcname()
        {
            return Acname;
        }

        public void setAcname(String acname)
        {
            Acname = acname;
        }

        public int getAsLevel()
        {
            return AsLevel;
        }

        public void setAsLevel(int asLevel)
        {
            AsLevel = asLevel;
        }

        public String getPayMode()
        {
            return PayMode;
        }

        public void setPayMode(String payMode)
        {
            PayMode = payMode;
        }

        public String getCrRateType()
        {
            return CrRateType;
        }

        public void setCrRateType(String crRateType)
        {
            CrRateType = crRateType;
        }

        public String getCrRateBasetype()
        {
            return CrRateBasetype;
        }

        public void setCrRateBasetype(String crRateBasetype)
        {
            CrRateBasetype = crRateBasetype;
        }

        public double getCrRatePrefer()
        {
            return CrRatePrefer;
        }

        public void setCrRatePrefer(double crRatePrefer)
        {
            CrRatePrefer = crRatePrefer;
        }

        public double getCrAmountOne()
        {
            return CrAmountOne;
        }

        public void setCrAmountOne(double crAmountOne)
        {
            CrAmountOne = crAmountOne;
        }

        public double getCrRateOne()
        {
            return CrRateOne;
        }

        public void setCrRateOne(double crRateOne)
        {
            CrRateOne = crRateOne;
        }

        public double getCrAmountTwo()
        {
            return CrAmountTwo;
        }

        public void setCrAmountTwo(double crAmountTwo)
        {
            CrAmountTwo = crAmountTwo;
        }

        public double getCrRateTwo()
        {
            return CrRateTwo;
        }

        public void setCrRateTwo(double crRateTwo)
        {
            CrRateTwo = crRateTwo;
        }

        public double getCrAmountThree()
        {
            return CrAmountThree;
        }

        public void setCrAmountThree(double crAmountThree)
        {
            CrAmountThree = crAmountThree;
        }

        public double getCrRateThree()
        {
            return CrRateThree;
        }

        public void setCrRateThree(double crRateThree)
        {
            CrRateThree = crRateThree;
        }

        public double getCrAmountFour()
        {
            return CrAmountFour;
        }

        public void setCrAmountFour(double crAmountFour)
        {
            CrAmountFour = crAmountFour;
        }

        public double getCrRateFour()
        {
            return CrRateFour;
        }

        public void setCrRateFour(double crRateFour)
        {
            CrRateFour = crRateFour;
        }

        public double getCrAmountFive()
        {
            return CrAmountFive;
        }

        public void setCrAmountFive(double crAmountFive)
        {
            CrAmountFive = crAmountFive;
        }

        public double getCrRateFive()
        {
            return CrRateFive;
        }

        public void setCrRateFive(double crRateFive)
        {
            CrRateFive = crRateFive;
        }

        public String getDrRateType()
        {
            return DrRateType;
        }

        public void setDrRateType(String drRateType)
        {
            DrRateType = drRateType;
        }

        public String getDrRateBasetype()
        {
            return DrRateBasetype;
        }

        public void setDrRateBasetype(String drRateBasetype)
        {
            DrRateBasetype = drRateBasetype;
        }

        public double getDrRatePrefer()
        {
            return DrRatePrefer;
        }

        public void setDrRatePrefer(double drRatePrefer)
        {
            DrRatePrefer = drRatePrefer;
        }

        public double getDrAmountOne()
        {
            return DrAmountOne;
        }

        public void setDrAmountOne(double drAmountOne)
        {
            DrAmountOne = drAmountOne;
        }

        public double getDrRateOne()
        {
            return DrRateOne;
        }

        public void setDrRateOne(double drRateOne)
        {
            DrRateOne = drRateOne;
        }

        public double getDrAmountTwo()
        {
            return DrAmountTwo;
        }

        public void setDrAmountTwo(double drAmountTwo)
        {
            DrAmountTwo = drAmountTwo;
        }

        public double getDrRateTwo()
        {
            return DrRateTwo;
        }

        public void setDrRateTwo(double drRateTwo)
        {
            DrRateTwo = drRateTwo;
        }

        public double getDrAmountThree()
        {
            return DrAmountThree;
        }

        public void setDrAmountThree(double drAmountThree)
        {
            DrAmountThree = drAmountThree;
        }

        public double getDrRateThree()
        {
            return DrRateThree;
        }

        public void setDrRateThree(double drRateThree)
        {
            DrRateThree = drRateThree;
        }

        public double getDrAmountFour()
        {
            return DrAmountFour;
        }

        public void setDrAmountFour(double drAmountFour)
        {
            DrAmountFour = drAmountFour;
        }

        public double getDrRateFour()
        {
            return DrRateFour;
        }

        public void setDrRateFour(double drRateFour)
        {
            DrRateFour = drRateFour;
        }

        public double getDrAmountFive()
        {
            return DrAmountFive;
        }

        public void setDrAmountFive(double drAmountFive)
        {
            DrAmountFive = drAmountFive;
        }

        public double getDrRateFive()
        {
            return DrRateFive;
        }

        public void setDrRateFive(double drRateFive)
        {
            DrRateFive = drRateFive;
        }

        public String getSupAsAcname()
        {
            return SupAsAcname;
        }

        public void setSupAsAcname(String supAsAcname)
        {
            SupAsAcname = supAsAcname;
        }

    }
}