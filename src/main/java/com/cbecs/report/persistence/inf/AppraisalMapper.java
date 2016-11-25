package com.cbecs.report.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.cbecs.report.model.DeptPerfPageableReport;
import com.cbecs.report.model.DeptPerfQueryReport;
import com.cbecs.report.model.DeptPerfReport;
import com.cbecs.report.persistence.SelectSqlProvider;

public interface AppraisalMapper
{
    /**
     * 业务报表分页查询
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectDeptPerfByPage", type = SelectSqlProvider.class)
    List<DeptPerfReport> selectDeptPerfByPage(DeptPerfPageableReport model);

    /**
     * 报表导出
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectDeptPerf", type = SelectSqlProvider.class)
    List<DeptPerfReport> selectDeptPerf(DeptPerfQueryReport model);
}