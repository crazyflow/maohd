package com.cbecs.scrm.channel.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.report.service.inf.AnalysisService;
import com.cbecs.scrm.channel.model.ChannelBusiness;
import com.cbecs.scrm.channel.model.ChannelBusinessQueryByPage;
import com.cbecs.scrm.channel.model.SysMenu;
import com.cbecs.scrm.channel.service.inf.ChannelService;

@Controller
@RequestMapping(value = "/channels")
public class ChannelController
{

    @Autowired
    private ChannelService channelService;

    @Autowired
    private AnalysisService analysisService;

    @GetMapping(value = "/index")
    public ModelAndView getChannels()
    {
        ModelAndView view = new ModelAndView("/scrm/channel/index");
        List<SysMenu> sysMenus = channelService.getSysMenus();
        view.addObject("sysMenus", sysMenus);
        return view;
    }

    /**
     * 渠道商对话框
     * 
     * @return
     */
    @GetMapping(value = "/commission-settlement")
    public ModelAndView getChannelsInDialog()
    {
        ModelAndView view = new ModelAndView("/scrm/channel/channel_commission_settlement_list");
        String businessMainBodys = analysisService.getBusinessMainBodyInHtml();
        view.addObject("businessMainBodys", businessMainBodys);
        return view;
    }

    /**
     * 渠道商对话框列表数据
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/commission-settlement", params = "format=table")
    public ModelAndView getChannelsInDialogTable(ChannelBusinessQueryByPage model)
    {
        model.setPageSize(5);
        List<ChannelBusiness> list = channelService.getChannelBusinessByPage(model);
        ModelAndView view = new ModelAndView("/scrm/channel/channel_commission_settlement_table");
        view.addObject("channels", list);
        view.addObject("page", model);
        return view;
    }

}
