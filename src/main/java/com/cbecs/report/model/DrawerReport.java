package com.cbecs.report.model;

import java.util.Date;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;

/**
 * 开票人
 */
@Table(name = "Supplier")
public class DrawerReport implements java.io.Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 3143669915605552036L;

    /**
     * ID开票人ID。PK。系统自动生成随机型的Guid。
     */
    @Id(name = "ID")
    private String id;

    /**
     * Code开票人编码
     */
    @Column(name = "Code")
    private String code;

    /**
     * Name开票人名称。必有且唯一。
     */
    @Column(name = "Name")
    private String name;

    /**
     * 委托人名称
     */
    private String companyName;

    /**
     * FoundDate成立时间。
     */
    @Column(name = "FoundDate")
    private Date foundDate;

    /**
     * IsCertificate是否三证合一。1:是；0:否；
     */
    @Column(name = "IsCertificate")
    private String isCertificate;

    /**
     * 开票人注册地址
     */
    private String registerAddress;

    /**
     * 开票人经营地址
     */
    private String businessAddress;

    /**
     * SupplierEnterpriseType开票人企业类型(1:工厂,2:贸易公司)
     */
    @Column(name = "SupplierEnterpriseType")
    private String supplierEnterpriseType;

    /**
     * VATInvoiceUpper增值税发票限额。单位：人民币元。千元票；万元票；十万元票；百万元票。通用数据字典，数据字典的DictionaryType与字段同名。
     */
    @Column(name = "VATInvoiceUpper")
    private String vATInvoiceUpper;

    /**
     * GrantedDate一般纳税人认定时间。
     */
    @Column(name = "GrantedDate")
    private Date grantedDate;

    /**
     * 增值税票总数
     */
    private int ticketCount;

    /**
     * 纳税人报表总数
     */
    private int sheetCount;

    /**
     * 照片总数
     */
    private int pictureCount;

    /**
     * 门牌头总数
     */
    private int doorplateCount;

    /**
     * 出口商品
     */
    private String exportProducts;

    /**
     * 是否开票
     */
    private String isSupplier;

    /**
     * 已开票总额
     */
    private double amount;
    
    /**
     * 审核状态
     */
    private int orderStatus;
    
    private String companyId;

    public String getIsSupplier()
    {
        return isSupplier;
    }

    public void setIsSupplier(String isSupplier)
    {
        this.isSupplier = isSupplier;
    }

    public int getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
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

   
    public int getTicketCount()
    {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount)
    {
        this.ticketCount = ticketCount;
    }

    public int getSheetCount()
    {
        return sheetCount;
    }

    public void setSheetCount(int sheetCount)
    {
        this.sheetCount = sheetCount;
    }

    public int getPictureCount()
    {
        return pictureCount;
    }

    public void setPictureCount(int pictureCount)
    {
        this.pictureCount = pictureCount;
    }

    public int getDoorplateCount()
    {
        return doorplateCount;
    }

    public void setDoorplateCount(int doorplateCount)
    {
        this.doorplateCount = doorplateCount;
    }

    public String getExportProducts()
    {
        return exportProducts;
    }

    public void setExportProducts(String exportProducts)
    {
        this.exportProducts = exportProducts;
    }

    public String getSupplierEnterpriseType()
    {
        return supplierEnterpriseType;
    }

    public void setSupplierEnterpriseType(String supplierEnterpriseType)
    {
        this.supplierEnterpriseType = supplierEnterpriseType;
    }

    public String getvATInvoiceUpper()
    {
        return vATInvoiceUpper;
    }

    public void setvATInvoiceUpper(String vATInvoiceUpper)
    {
        this.vATInvoiceUpper = vATInvoiceUpper;
    }  

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public Date getFoundDate()
    {
        return foundDate;
    }

    public void setFoundDate(Date foundDate)
    {
        this.foundDate = foundDate;
    }

    public Date getGrantedDate()
    {
        return grantedDate;
    }

    public void setGrantedDate(Date grantedDate)
    {
        this.grantedDate = grantedDate;
    }

    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(String companyId)
    {
        this.companyId = companyId;
    }   
        
}