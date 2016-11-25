package com.cbecs.smc.cfpclient.bank.jsb.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public final class JSB200145ResponseBody
{
    @XmlElement(name = "tr_acdt")
    private String trAcdt;

    @XmlElement(name = "tr_time")
    private String trTime;

    @XmlElement(name = "buy_price")
    private double buyPrice;

    @XmlElement(name = "mid_price")
    private double midPrice;

    @XmlElement(name = "sell_price")
    private double sellPrice;

    public String getTrAcdt()
    {
        return trAcdt;
    }

    public void setTrAcdt(String trAcdt)
    {
        this.trAcdt = trAcdt;
    }

    public String getTrTime()
    {
        return trTime;
    }

    public void setTrTime(String trTime)
    {
        this.trTime = trTime;
    }

    public double getBuyPrice()
    {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice)
    {
        this.buyPrice = buyPrice;
    }

    public double getMidPrice()
    {
        return midPrice;
    }

    public void setMidPrice(double midPrice)
    {
        this.midPrice = midPrice;
    }

    public double getSellPrice()
    {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice)
    {
        this.sellPrice = sellPrice;
    }

}