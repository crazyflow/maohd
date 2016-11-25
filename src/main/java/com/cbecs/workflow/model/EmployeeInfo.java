package com.cbecs.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeInfo
{
    /**
    * 职工ID
    */
    @JsonProperty("Id")
    private String id;
    /**
    * 姓名
    */
    @JsonProperty("Name")
    private String name;
    /**
    * 头像
    */
    @JsonProperty("Photo")
    private String photo;
    /**
    * 部门
    */
    @JsonProperty("Department")
    private String department;
    /**
    * 邮箱
    */
    @JsonProperty("Email")
    private String email;
    /**
    * 手机号
    */
    @JsonProperty("Mobile")
    private String mobile;
    /**
    * 操作内容
    */
    @JsonProperty("OperateContext")
    private String operateContext;
    /**
    * 操作主题
    */
    @JsonProperty("OperateSubject")
    private String operateSubject;
    /**
    * 操作时间
    */
    @JsonProperty("OperateTime")
    private String operateTime;
    /**
    * 职位
    */
    @JsonProperty("Position")
    private String position;
    /**
    * 用于排序
    */
    @JsonProperty("Ticks")
    private long ticks;
    
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
    public String getPhoto()
    {
        return photo;
    }
    public void setPhoto(String photo)
    {
        this.photo = photo;
    }
    public String getDepartment()
    {
        return department;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public String getOperateContext()
    {
        return operateContext;
    }
    public void setOperateContext(String operateContext)
    {
        this.operateContext = operateContext;
    }
    public String getOperateSubject()
    {
        return operateSubject;
    }
    public void setOperateSubject(String operateSubject)
    {
        this.operateSubject = operateSubject;
    }
    public String getOperateTime()
    {
        return operateTime;
    }
    public void setOperateTime(String operateTime)
    {
        this.operateTime = operateTime;
    }
    public String getPosition()
    {
        return position;
    }
    public void setPosition(String position)
    {
        this.position = position;
    }
    public long getTicks()
    {
        return ticks;
    }
    public void setTicks(long ticks)
    {
        this.ticks = ticks;
    }    
}
