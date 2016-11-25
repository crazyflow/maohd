package com.cbecs.scrm.channel.model;

public class ConsignerCashPool implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 5441747660621950819L;
    
    private String companyName;
    private String companyId;
    private double cashBalance;
    private double depositBalance;
    private double creditBalance;
    
    private double cashGatherAmount;
    private double cashPayAmount;
    private double cashFreezeAmount;
    private double depositGatherAmount;
    private double depositPayAmount;
    private double depositFreezeAmount;
    
    private double creditGatherAmount;
    private double creditPayAmount;
    private double creditFreezeAmount;
    private double depositNeedPayAmount;
    
    private String consultantName;
    private String consultantId;
    
    private int signFlag;
    private int companyType;
    

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(String companyId)
    {
        this.companyId = companyId;
    }

    public double getCashBalance()
    {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance)
    {
        this.cashBalance = cashBalance;
    }

    public double getDepositBalance()
    {
        return depositBalance;
    }

    public void setDepositBalance(double depositBalance)
    {
        this.depositBalance = depositBalance;
    }

    public double getCreditBalance()
    {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance)
    {
        this.creditBalance = creditBalance;
    }

    public double getCashGatherAmount()
    {
        return cashGatherAmount;
    }

    public void setCashGatherAmount(double cashGatherAmount)
    {
        this.cashGatherAmount = cashGatherAmount;
    }

    public double getCashPayAmount()
    {
        return cashPayAmount;
    }

    public void setCashPayAmount(double cashPayAmount)
    {
        this.cashPayAmount = cashPayAmount;
    }

    public double getCashFreezeAmount()
    {
        return cashFreezeAmount;
    }

    public void setCashFreezeAmount(double cashFreezeAmount)
    {
        this.cashFreezeAmount = cashFreezeAmount;
    }

    public double getDepositGatherAmount()
    {
        return depositGatherAmount;
    }

    public void setDepositGatherAmount(double depositGatherAmount)
    {
        this.depositGatherAmount = depositGatherAmount;
    }

    public double getDepositPayAmount()
    {
        return depositPayAmount;
    }

    public void setDepositPayAmount(double depositPayAmount)
    {
        this.depositPayAmount = depositPayAmount;
    }

    public double getDepositFreezeAmount()
    {
        return depositFreezeAmount;
    }

    public void setDepositFreezeAmount(double depositFreezeAmount)
    {
        this.depositFreezeAmount = depositFreezeAmount;
    }

    public double getCreditGatherAmount()
    {
        return creditGatherAmount;
    }

    public void setCreditGatherAmount(double creditGatherAmount)
    {
        this.creditGatherAmount = creditGatherAmount;
    }

    public double getCreditPayAmount()
    {
        return creditPayAmount;
    }

    public void setCreditPayAmount(double creditPayAmount)
    {
        this.creditPayAmount = creditPayAmount;
    }

    public double getCreditFreezeAmount()
    {
        return creditFreezeAmount;
    }

    public void setCreditFreezeAmount(double creditFreezeAmount)
    {
        this.creditFreezeAmount = creditFreezeAmount;
    }

    public double getDepositNeedPayAmount()
    {
        return depositNeedPayAmount;
    }

    public void setDepositNeedPayAmount(double depositNeedPayAmount)
    {
        this.depositNeedPayAmount = depositNeedPayAmount;
    }

    public String getConsultantName()
    {
        return consultantName;
    }

    public void setConsultantName(String consultantName)
    {
        this.consultantName = consultantName;
    }

    public String getConsultantId()
    {
        return consultantId;
    }

    public void setConsultantId(String consultantId)
    {
        this.consultantId = consultantId;
    }

    public int getSignFlag()
    {
        return signFlag;
    }

    public void setSignFlag(int signFlag)
    {
        this.signFlag = signFlag;
    }

    public int getCompanyType()
    {
        return companyType;
    }

    public void setCompanyType(int companyType)
    {
        this.companyType = companyType;
    }
        
}
