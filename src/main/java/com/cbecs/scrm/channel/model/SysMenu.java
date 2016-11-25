package com.cbecs.scrm.channel.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cbecs.framework.mybatis.annotations.Column;
import com.cbecs.framework.mybatis.annotations.Id;
import com.cbecs.framework.mybatis.annotations.Table;



@Table(name="Sys_Menu")
public class SysMenu implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7771213517630228176L;

    /** 
     *MenuID菜单ID
     */
    @Id(name="MenuID")
    private String menuID;

    /** 
     *MenuTypeOP,ISV,VAR,CSP,EC
     */
    @Column(name="MenuType")
    private String menuType;

    /** 
     *MenuName菜单名称
     */
    @Column(name="MenuName")
    private String menuName;

    /** 
     *MenuCode菜单Code:一般与权限判断
     */
    @Column(name="MenuCode")
    private String menuCode;

    /** 
     *RightValue
     */
    @Column(name="RightValue")
    private int rightValue;

    /** 
     *ParentMenuID为Null的表示不同的类型菜单根
     */
    @Column(name="ParentMenuID")
    private String parentMenuID;

    /** 
     *ParentMenuPath父菜单路径
     */
    @Column(name="ParentMenuPath")
    private String parentMenuPath;

    /** 
     *ParentMenuCode父菜单Code
     */
    @Column(name="ParentMenuCode")
    private String parentMenuCode;

    /** 
     *MenuLevel菜单级别
     */
    @Column(name="MenuLevel")
    private int menuLevel;

    /** 
     *Sort排列顺序
     */
    @Column(name="Sort")
    private int sort;

    /** 
     *ImageURL图片地址
     */
    @Column(name="ImageURL")
    private String imageURL;

    /** 
     *LinkURL连接地址
     */
    @Column(name="LinkURL")
    private String linkURL;

    /** 
     *IsVisible是否显示:0--否;1--是
     */
    @Column(name="IsVisible")
    private int isVisible;

    /** 
     *IsCanDelete是否可以删除:0--不可以;1--可以
     */
    @Column(name="IsCanDelete")
    private int isCanDelete;

    /** 
     *Remark备注
     */
    @Column(name="Remark")
    private String remark;

    /** 
     *CreateTime创建时间
     */
    @Column(name="CreateTime")
    private Date createTime;

    /** 
     *ModifyTime修改时间
     */
    @Column(name="ModifyTime")
    private Date modifyTime;

    /** 
     *IsMenu 菜单和按钮区分 1：菜单 0：按钮
     */
    @Column(name="IsMenu")
    private int isMenu;

    private List<SysMenu> children = new ArrayList<SysMenu>();

    /**
     * 获取 MenuID 的值
     * @return String 
     */
    public String getMenuID() {
        return menuID;
    }
    
    /**
     * 设置MenuID 的值
     * @param String menuID
     */
    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    /**
     * 获取 MenuType 的值
     * @return String 
     */
    public String getMenuType() {
        return menuType;
    }
    
    /**
     * 设置MenuType 的值
     * @param String menuType
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取 MenuName 的值
     * @return String 
     */
    public String getMenuName() {
        return menuName;
    }
    
    /**
     * 设置MenuName 的值
     * @param String menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取 MenuCode 的值
     * @return String 
     */
    public String getMenuCode() {
        return menuCode;
    }
    
    /**
     * 设置MenuCode 的值
     * @param String menuCode
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 获取 RightValue 的值
     * @return int 
     */
    public int getRightValue() {
        return rightValue;
    }
    
    /**
     * 设置RightValue 的值
     * @param int rightValue
     */
    public void setRightValue(int rightValue) {
        this.rightValue = rightValue;
    }

    /**
     * 获取 ParentMenuID 的值
     * @return String 
     */
    public String getParentMenuID() {
        return parentMenuID;
    }
    
    /**
     * 设置ParentMenuID 的值
     * @param String parentMenuID
     */
    public void setParentMenuID(String parentMenuID) {
        this.parentMenuID = parentMenuID;
    }

    /**
     * 获取 ParentMenuPath 的值
     * @return String 
     */
    public String getParentMenuPath() {
        return parentMenuPath;
    }
    
    /**
     * 设置ParentMenuPath 的值
     * @param String parentMenuPath
     */
    public void setParentMenuPath(String parentMenuPath) {
        this.parentMenuPath = parentMenuPath;
    }

    /**
     * 获取 ParentMenuCode 的值
     * @return String 
     */
    public String getParentMenuCode() {
        return parentMenuCode;
    }
    
    /**
     * 设置ParentMenuCode 的值
     * @param String parentMenuCode
     */
    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    /**
     * 获取 MenuLevel 的值
     * @return int 
     */
    public int getMenuLevel() {
        return menuLevel;
    }
    
    /**
     * 设置MenuLevel 的值
     * @param int menuLevel
     */
    public void setMenuLevel(int menuLevel) {
        this.menuLevel = menuLevel;
    }

    /**
     * 获取 Sort 的值
     * @return int 
     */
    public int getSort() {
        return sort;
    }
    
    /**
     * 设置Sort 的值
     * @param int sort
     */
    public void setSort(int sort) {
        this.sort = sort;
    }

    /**
     * 获取 ImageURL 的值
     * @return String 
     */
    public String getImageURL() {
        return imageURL;
    }
    
    /**
     * 设置ImageURL 的值
     * @param String imageURL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * 获取 LinkURL 的值
     * @return String 
     */
    public String getLinkURL() {
        return linkURL;
    }
    
    /**
     * 设置LinkURL 的值
     * @param String linkURL
     */
    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    /**
     * 获取 IsVisible 的值
     * @return int 
     */
    public int getIsVisible() {
        return isVisible;
    }
    
    /**
     * 设置IsVisible 的值
     * @param int isVisible
     */
    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * 获取 IsCanDelete 的值
     * @return int 
     */
    public int getIsCanDelete() {
        return isCanDelete;
    }
    
    /**
     * 设置IsCanDelete 的值
     * @param int isCanDelete
     */
    public void setIsCanDelete(int isCanDelete) {
        this.isCanDelete = isCanDelete;
    }

    /**
     * 获取 Remark 的值
     * @return String 
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置Remark 的值
     * @param String remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 CreateTime 的值
     * @return Date 
     */
    public Date getCreateTime() {
        return createTime;
    }
    
    /**
     * 设置CreateTime 的值
     * @param Date createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 ModifyTime 的值
     * @return Date 
     */
    public Date getModifyTime() {
        return modifyTime;
    }
    
    /**
     * 设置ModifyTime 的值
     * @param Date modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取 IsMenu 的值
     * @return int 
     */
    public int getIsMenu() {
        return isMenu;
    }
    
    /**
     * 设置IsMenu 的值
     * @param int isMenu
     */
    public void setIsMenu(int isMenu) {
        this.isMenu = isMenu;
    }


    
    @Override
    public String toString() {
        return "SysMenu [" + "MenuID=" + menuID +", MenuType=" + menuType +", MenuName=" + menuName +", MenuCode=" + menuCode +", RightValue=" + rightValue +", ParentMenuID=" + parentMenuID +", ParentMenuPath=" + parentMenuPath +", ParentMenuCode=" + parentMenuCode +", MenuLevel=" + menuLevel +", Sort=" + sort +", ImageURL=" + imageURL +", LinkURL=" + linkURL +", IsVisible=" + isVisible +", IsCanDelete=" + isCanDelete +", Remark=" + remark +", CreateTime=" + createTime +", ModifyTime=" + modifyTime +", IsMenu=" + isMenu +"]";
    }

    public List<SysMenu> getChildren()
    {
        return children;
    }

    public void setChildren(List<SysMenu> children)
    {
        this.children = children;
    }
}
