package com.cbecs.report.model;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;
import com.cbecs.framework.mybatis.pagination.Pageable;

/**
 * 委托方
 */
@Table(name = "Company")
public class ConsignerReportPageable extends Pageable implements java.io.Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -276109640355405057L;

    /**
     * ID委托方ID。PK。系统自动生成随机型的Guid。
     */
    @Id(name = "ID")
    private String id;

    /**
     * Name委托方名称。必有且唯一。
     */
    @Column(name = "Name")
    private String name;
    
    /**
     * 企业类型
     */
    private String industrytype;
    
    /**
     * IsCertificate是否三证合一。1:是；0:否；
     */
    @Column(name = "IsCertificate")
    private String isCertificate;

    /**
     * 委托方注册地址
     */
    private String registerAddress;

    /**
     * 委托方经营地址
     */
    private String businessAddress;

    /**
     * 身份证照总数
     */
    private int idCardCount;

    /**
     * 个人征信报告总数
     */
    private int personalReporCount;
    
    /**
     * 签约标识。0未签约；1已签约。
     */
    private int signFlag;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIndustrytype()
    {
        return industrytype;
    }

    public void setIndustrytype(String industrytype)
    {
        this.industrytype = industrytype;
    }

    public String getIsCertificate()
    {
        return isCertificate;
    }

    public void setIsCertificate(String isCertificate)
    {
        this.isCertificate = isCertificate;
    }

    public String getRegisterAddress()
    {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress)
    {
        this.registerAddress = registerAddress;
    }

    public String getBusinessAddress()
    {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress)
    {
        this.businessAddress = businessAddress;
    }

    public int getIdCardCount()
    {
        return idCardCount;
    }

    public void setIdCardCount(int idCardCount)
    {
        this.idCardCount = idCardCount;
    }

    public int getPersonalReporCount()
    {
        return personalReporCount;
    }

    public void setPersonalReporCount(int personalReporCount)
    {
        this.personalReporCount = personalReporCount;
    }

    public int getSignFlag()
    {
        return signFlag;
    }

    public void setSignFlag(int signFlag)
    {
        this.signFlag = signFlag;
    }
   
}