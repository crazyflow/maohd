package com.cbecs.report.model;

import java.io.Serializable;

public class DeptPerfQueryReport implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 7496303827525846339L;

    private String beginExportDate;

    private String endExportDate;

    private String beginDeclarationDate;

    private String endDeclarationDate;

    public String getBeginExportDate()
    {
        return beginExportDate;
    }

    public void setBeginExportDate(String beginExportDate)
    {
        this.beginExportDate = beginExportDate;
    }

    public String getEndExportDate()
    {
        return endExportDate;
    }

    public void setEndExportDate(String endExportDate)
    {
        this.endExportDate = endExportDate;
    }

    public String getBeginDeclarationDate()
    {
        return beginDeclarationDate;
    }

    public void setBeginDeclarationDate(String beginDeclarationDate)
    {
        this.beginDeclarationDate = beginDeclarationDate;
    }

    public String getEndDeclarationDate()
    {
        return endDeclarationDate;
    }

    public void setEndDeclarationDate(String endDeclarationDate)
    {
        this.endDeclarationDate = endDeclarationDate;
    }

   
}
