package com.cbecs.report.service.inf;

import java.util.List;

import com.cbecs.report.model.DeptPerfPageableReport;
import com.cbecs.report.model.DeptPerfQueryReport;
import com.cbecs.report.model.DeptPerfReport;

public interface AppraisalService
{
    /**
     * 业务部门考核信息
     * 
     * @param model
     * @return
     */
    List<DeptPerfReport> getDeptPerfByPage(DeptPerfPageableReport model);
    
    /**
     * 业务部门考核信息所有数据
     * 
     * @param model
     * @return
     */
    List<DeptPerfReport> getDeptPerf(DeptPerfQueryReport model);

}
