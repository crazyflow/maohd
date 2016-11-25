package com.cbecs.workflow.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cbecs.workflow.model.BusinessInteractiveInfo;
import com.cbecs.workflow.model.EmployeeInfo;
import com.cbecs.workflow.model.EmployeeTrackInfo;
import com.cbecs.workflow.model.ItemListBody;
import com.cbecs.workflow.model.ReturnInfo;
import com.cbecs.workflow.model.ReturnSimpleInfo;
import com.cbecs.workflow.model.TrackLineEmployeeInfo;
import com.cbecs.workflow.model.WorkFlowCommonBody;
import com.cbecs.workflow.model.WorkFlowInfo;
import com.cbecs.workflow.model.WorkFlowTrackItem;
import com.cbecs.workflow.service.inf.WorkFlowService;
import com.cbecs.workflow.utility.Utility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class WorkFlowServiceImpl implements WorkFlowService
{
    @Value("${workflow.url}")
    private String hostUrl;
    private static final Logger logger = LoggerFactory.getLogger(WorkFlowServiceImpl.class);
    private ObjectMapper mapper = new ObjectMapper();
    
    WorkFlowServiceImpl(){
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true); 
    }
    
    /**
     * 获取我发起的工作流
     * 
     * @throws IOException
     */
    @Override
    public ReturnSimpleInfo<List<ItemListBody>> GetMyLunchItems(WorkFlowCommonBody wfCommonBody)
    {
        ReturnSimpleInfo<List<ItemListBody>> res = null;
        try
        {
            String jsonObject = mapper.writeValueAsString(wfCommonBody);
            logger.info("获取我发起的工作流输入:"+jsonObject);
            StringEntity entity = new StringEntity(jsonObject, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            String url = hostUrl + "/api/MyLunchItems/";
            String retStr = Utility.HttpPost(url, entity);
            logger.info("获取我发起的工作流输出:"+retStr);
            res = mapper.readValue(retStr, new TypeReference<ReturnSimpleInfo<List<ItemListBody>>>()
            {
            });
        }
        catch (Exception e)
        {
            logger.error("获取我发起的工作流:", e);
        }
        return res;
    }

    /**
     * 获取我待办的工作流
     */
    @Override
    public ReturnSimpleInfo<List<ItemListBody>> GetMyToDoItems(WorkFlowCommonBody wfCommonBody)
    {
        ReturnSimpleInfo<List<ItemListBody>> res = null;
        try
        {
            String jsonObject = mapper.writeValueAsString(wfCommonBody);
            logger.info("获取我待办的工作流输入:"+jsonObject);
            StringEntity entity = new StringEntity(jsonObject, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");

            String url = hostUrl + "/api/MyToDoItems/";
            String retStr = Utility.HttpPost(url, entity);
            logger.info("获取我待办的工作流输出:"+retStr);
            res = mapper.readValue(retStr, new TypeReference<ReturnSimpleInfo<List<ItemListBody>>>()
            {
            });
        }
        catch (Exception e)
        {
            logger.error("获取我待办的工作流:", e);
        }
        return res;
    }

    /**
     * 获取我业务系统待办的工作流
     */
    @Override
    public ReturnSimpleInfo<List<ItemListBody>> GetMyAllToDoItems(WorkFlowCommonBody wfCommonBody)
    {
        ReturnSimpleInfo<List<ItemListBody>> res = null;
        try
        {
            String jsonObject = mapper.writeValueAsString(wfCommonBody);
            logger.info("获取我业务系统待办的工作流输入:"+jsonObject);
            StringEntity entity = new StringEntity(jsonObject, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            String url = hostUrl + "/api/MyAllToDoItems/";
            String retStr = Utility.HttpPost(url, entity);
            logger.info("获取我业务系统待办的工作流输出:"+retStr);
            res = mapper.readValue(retStr, new TypeReference<ReturnSimpleInfo<List<ItemListBody>>>()
            {
            });
        }
        catch (Exception e)
        {
            logger.error("获取我业务系统待办的工作流:", e);
        }
        return res;
    }

    /**
     * 获取工作流轨迹信息
     */
    @Override
    public ReturnSimpleInfo<List<WorkFlowTrackItem>> GetWorkFlowTracks(WorkFlowCommonBody wfCommonBody)
    {
        ReturnSimpleInfo<List<WorkFlowTrackItem>> res = null;
        try
        {
            String jsonObject = mapper.writeValueAsString(wfCommonBody);
            logger.info("获取工作流轨迹信息输入:"+jsonObject);
            StringEntity entity = new StringEntity(jsonObject, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");

            String url = hostUrl + "/api/WorkFlowTracks/";
            String retStr = Utility.HttpPost(url, entity);
            logger.info("获取工作流轨迹信息输出:"+retStr);
            res = mapper.readValue(retStr, new TypeReference<ReturnSimpleInfo<List<WorkFlowTrackItem>>>()
            {
            });
        }
        catch (Exception e)
        {
            logger.error("获取工作流轨迹信息:", e);
        }
        return res;
    }

    /**
     * 获取一般的轨迹线信息
     */
    @Override
    public ReturnSimpleInfo<List<TrackLineEmployeeInfo>> GetSimpleTrackLine(WorkFlowCommonBody wfCommonBody)
    {
        ReturnSimpleInfo<List<TrackLineEmployeeInfo>> res = null;
        try
        {
            String jsonObject = mapper.writeValueAsString(wfCommonBody);
            logger.info("获取一般的轨迹线信息输入:"+jsonObject);
            StringEntity entity = new StringEntity(jsonObject, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");

            String url = hostUrl + "/api/SimpleTrackLine/";
            String retStr = Utility.HttpPost(url, entity);
            logger.info("获取一般的轨迹线信息输出:"+retStr);
            res = mapper.readValue(retStr, new TypeReference<ReturnSimpleInfo<List<TrackLineEmployeeInfo>>>()
            {
            });
        }
        catch (Exception e)
        {
            logger.error("获取一般的轨迹线信息:", e);
        }
        return res;
    }

    /**
     * 获取工作流当前节点的待办人
     */
    @Override
    public ReturnSimpleInfo<List<TrackLineEmployeeInfo>> GetWorkFlowCurrentEmployees(WorkFlowCommonBody wfCommonBody)
    {
        ReturnSimpleInfo<List<TrackLineEmployeeInfo>> res = null;
        try
        {
            String jsonObject = mapper.writeValueAsString(wfCommonBody);
            logger.info("获取工作流当前节点的待办人输入:"+jsonObject);
            StringEntity entity = new StringEntity(jsonObject, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");

            String url = hostUrl + "/api/WorkFlowCurrentEmployees/";
            String retStr = Utility.HttpPost(url, entity);
            logger.info("获取工作流当前节点的待办人输出:"+retStr);
            res = mapper.readValue(retStr, new TypeReference<ReturnSimpleInfo<List<TrackLineEmployeeInfo>>>()
            {
            });
        }
        catch (Exception e)
        {
            logger.error("获取工作流当前节点的待办人:", e);
        }
        return res;
    }

    /**
     * 获取工作流信息
     */
    @Override
    public ReturnSimpleInfo<WorkFlowInfo> GetWorkFlowInfo(WorkFlowCommonBody wfCommonBody)
    {
        ReturnSimpleInfo<WorkFlowInfo> res = null;
        try
        {
            String jsonObject = mapper.writeValueAsString(wfCommonBody);
            logger.info("获取工作流信息输入:"+jsonObject);
            StringEntity entity = new StringEntity(jsonObject, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");

            String url = hostUrl + "/api/WorkFlowInfo/";
            String retStr = Utility.HttpPost(url, entity);
            logger.info("获取工作流信息输出:"+retStr);
            res = mapper.readValue(retStr, new TypeReference<ReturnSimpleInfo<WorkFlowInfo>>()
            {
            });
        }
        catch (Exception e)
        {
            logger.error("获取工作流信息:", e);
        }
        return res;
    }

    /**
     * 业务交互
     */
    @Override
    public ReturnInfo BusinessInteractive(BusinessInteractiveInfo interactiveInfo)
    {
        ReturnInfo res = null;
        try
        {
            String jsonObject = mapper.writeValueAsString(interactiveInfo);
            logger.info("业务交互输入:"+jsonObject);
            StringEntity entity = new StringEntity(jsonObject, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");

            String url = hostUrl + "/api/BusinessInteractive/";
            String retStr = Utility.HttpPost(url, entity);
            logger.info("业务交互输出:"+retStr);
            res = mapper.readValue(retStr, ReturnInfo.class);
        }
        catch (Exception e)
        {
            logger.error("业务交互:", e);
        }
        return res;
    }

    @Override
    public String getWorkFlowIds(WorkFlowCommonBody wfCommonBody)
    {
        String workFlowIds = "";
        ReturnSimpleInfo<List<ItemListBody>> res = GetMyToDoItems(wfCommonBody);
        if(null == res){
            return "";
        }
        for(ItemListBody item : res.getInfos()){
            workFlowIds += item.getWorkFlowId() + ",";
        }
        if(workFlowIds!=""){
            workFlowIds = workFlowIds.substring(0, workFlowIds.length()-1);
        }
        return workFlowIds;
    }

    // 非api,获取轨迹信息
    @Override
    public List<EmployeeTrackInfo> SortEmployeeInfos(WorkFlowCommonBody wfCommonBody)
    {
        ReturnSimpleInfo<List<WorkFlowTrackItem>> returnInfo = GetWorkFlowTracks(wfCommonBody);
        
        List<WorkFlowTrackItem> workFlowTrackItems = returnInfo.getInfos();
        List<EmployeeTrackInfo> retEmployeeTrackInfos = new ArrayList<EmployeeTrackInfo>();
        if (workFlowTrackItems == null || workFlowTrackItems.size() == 0)
        {
            return null;
        }else{
            for(WorkFlowTrackItem item : workFlowTrackItems)
            {
                if (item.getExcuteEmployeeInfos() != null){
                    for (EmployeeInfo employeeInfo : item.getExcuteEmployeeInfos())
                    {
                        if(item.getNodeName().equals("开始")){
                            continue;
                        }
                        EmployeeTrackInfo employeeTrackInfo = new EmployeeTrackInfo();
                        employeeTrackInfo.setId(employeeInfo.getId());
                        employeeTrackInfo.setName(employeeInfo.getName());
                        employeeTrackInfo.setPhoto(employeeInfo.getPhoto());
                        employeeTrackInfo.setDepartment(employeeInfo.getDepartment());
                        employeeTrackInfo.setEmail(employeeInfo.getEmail());
                        employeeTrackInfo.setMobile(employeeInfo.getMobile());
                        employeeTrackInfo.setOperateContext(employeeInfo.getOperateContext());
                        employeeTrackInfo.setOperateSubject(employeeInfo.getOperateSubject());
                        employeeTrackInfo.setOperateTime(employeeInfo.getOperateTime());
                        employeeTrackInfo.setPosition(employeeInfo.getPosition());
                        employeeTrackInfo.setTicks(employeeInfo.getTicks());
                        employeeTrackInfo.setCostTimeFullInfo(item.getCostTimeFullInfo());
                        employeeTrackInfo.setMatchConditions(item.getMatchConditions());
                        employeeTrackInfo.setNodeName(item.getNodeName());
                        retEmployeeTrackInfos.add(employeeTrackInfo);
                    }
                }
            }
            Collections.reverse(retEmployeeTrackInfos);
            return retEmployeeTrackInfos;
        }
    }

}
