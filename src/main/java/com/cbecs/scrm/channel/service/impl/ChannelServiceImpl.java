package com.cbecs.scrm.channel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbecs.scrm.channel.model.ChannelBusiness;
import com.cbecs.scrm.channel.model.ChannelBusinessQueryByPage;
import com.cbecs.scrm.channel.model.SysMenu;
import com.cbecs.scrm.channel.persistence.inf.ChannelMapper;
import com.cbecs.scrm.channel.service.inf.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService
{
    @Autowired
    private ChannelMapper channelMapper;
    
    @Override
    public List<ChannelBusiness> getChannelBusinessByPage(ChannelBusinessQueryByPage model)
    {
        return channelMapper.selectChannelBusinessByPage(model);
    }

    @Override
    public ChannelBusiness getChannelBuinessByChannelId(String id)
    {
        return channelMapper.selectChannelBuinessByChannelId(id);
    }

    @Override
    public List<SysMenu> getSysMenus()
    {
        return channelMapper.getSysMenus();
    }
}
