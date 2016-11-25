package com.cbecs.scrm.channel.persistence.inf;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.cbecs.scrm.channel.model.ChannelBusiness;
import com.cbecs.scrm.channel.model.ChannelBusinessQueryByPage;
import com.cbecs.scrm.channel.model.SysMenu;
import com.cbecs.scrm.channel.persistence.SelectSqlProvider;

public interface ChannelMapper
{
    /**
     * 查询渠道商信息分页
     * 
     * @param model
     * @return
     */
    @SelectProvider(method = "selectChannelBusinessByPage", type = SelectSqlProvider.class)
    List<ChannelBusiness> selectChannelBusinessByPage(ChannelBusinessQueryByPage model);

    /**
     * 根据渠道商id查询渠道商信息
     * 
     * @param channelId
     * @return
     */
    @Select("SELECT distinct c.[ID] 'channelId',c.[Name] 'channelName',cc.[SignType] 'businessMainBody',c.[CooperationMode],(select top 1 name from companycontact where CompanyId = c.ID) 'contactName' "+
               " FROM [Company] c inner join [companycontract] cc  on c.[ID] = cc.[CompanyID]  "+
               " AND c.id = #{value} ")
    ChannelBusiness selectChannelBuinessByChannelId(String channelId);

    @ResultMap(value = { "sysMenusMap" })
    @Select(value = { "SELECT * FROM Sys_Menu d WHERE d.MenuLevel = 1 AND d.MenuType = 'scrm' order by d.sort" })
    List<SysMenu> getSysMenus();
    
}