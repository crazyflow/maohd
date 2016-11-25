package com.cbecs.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.report.model.DeptPerfPageableReport;
import com.cbecs.report.model.DeptPerfQueryReport;
import com.cbecs.report.model.DeptPerfReport;
import com.cbecs.report.persistence.inf.AppraisalMapper;
import com.cbecs.report.service.inf.AppraisalService;

@Service
public class AppraisalServiceImpl implements AppraisalService
{

    @Autowired
    private AppraisalMapper appraisalMapper;

    @Override
    public List<DeptPerfReport> getDeptPerfByPage(DeptPerfPageableReport model)
    {
        return appraisalMapper.selectDeptPerfByPage(model);
    }

    @Override
    public List<DeptPerfReport> getDeptPerf(DeptPerfQueryReport model)
    {
        return appraisalMapper.selectDeptPerf(model);
    }

}