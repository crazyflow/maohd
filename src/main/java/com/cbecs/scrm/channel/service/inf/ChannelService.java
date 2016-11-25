package com.cbecs.scrm.channel.service.inf;

import java.util.List;

import com.cbecs.scrm.channel.model.ChannelBusiness;
import com.cbecs.scrm.channel.model.ChannelBusinessQueryByPage;
import com.cbecs.scrm.channel.model.SysMenu;

public interface ChannelService
{
    /**
     * 查询渠道商信息分页
     * 
     * @param model
     * @return
     */
    List<ChannelBusiness> getChannelBusinessByPage(ChannelBusinessQueryByPage model);

    /**
     * 根据渠道商Id查询渠道商信息
     * 
     * @param id
     * @return
     */
    ChannelBusiness getChannelBuinessByChannelId(String id);

    List<SysMenu> getSysMenus();
}
