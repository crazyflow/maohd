package com.cbecs.workflow.service.inf;

import java.util.List;

import com.cbecs.workflow.model.BusinessInteractiveInfo;
import com.cbecs.workflow.model.EmployeeTrackInfo;
import com.cbecs.workflow.model.ItemListBody;
import com.cbecs.workflow.model.ReturnInfo;
import com.cbecs.workflow.model.ReturnSimpleInfo;
import com.cbecs.workflow.model.TrackLineEmployeeInfo;
import com.cbecs.workflow.model.WorkFlowCommonBody;
import com.cbecs.workflow.model.WorkFlowInfo;
import com.cbecs.workflow.model.WorkFlowTrackItem;

public interface WorkFlowService
{
    /**
    * 获取我发起的工作流
    */
    ReturnSimpleInfo<List<ItemListBody>> GetMyLunchItems(WorkFlowCommonBody wfCommonBody);
    
    /**
    * 获取我待办的工作流
    */
    ReturnSimpleInfo<List<ItemListBody>> GetMyToDoItems(WorkFlowCommonBody wfCommonBody);
    
    /**
    * 获取我业务系统待办的工作流
    */
    ReturnSimpleInfo<List<ItemListBody>> GetMyAllToDoItems(WorkFlowCommonBody wfCommonBody);
   
    /**
    * 获取工作流轨迹信息
    */
    ReturnSimpleInfo<List<WorkFlowTrackItem>> GetWorkFlowTracks(WorkFlowCommonBody wfCommonBody);

    /**
    * 获取一般的轨迹线信息
    */
    ReturnSimpleInfo<List<TrackLineEmployeeInfo>> GetSimpleTrackLine(WorkFlowCommonBody wfCommonBody);

    /**
    * 获取工作流当前节点的待办人
    */
    ReturnSimpleInfo<List<TrackLineEmployeeInfo>> GetWorkFlowCurrentEmployees(WorkFlowCommonBody wfCommonBody);

    /**
    * 获取工作流信息
    */
    ReturnSimpleInfo<WorkFlowInfo> GetWorkFlowInfo(WorkFlowCommonBody wfCommonBody);

    /**
    * 业务交互
    */
    ReturnInfo BusinessInteractive(BusinessInteractiveInfo interactiveInfo);
    
    //非api，获取workFlowIds
    String getWorkFlowIds(WorkFlowCommonBody wfCommonBody);
    
    //对轨迹信息操作人信息根据操作时间逆序排序
    List<EmployeeTrackInfo> SortEmployeeInfos(WorkFlowCommonBody wfCommonBody);
    
}
