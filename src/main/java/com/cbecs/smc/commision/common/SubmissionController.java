package com.cbecs.smc.commision.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbecs.smc.commision.common.model.Submission;


@Controller
@RequestMapping("/submit")
public class SubmissionController
{
    @GetMapping(value = "/success")
    public ModelAndView getSuccessPage(Submission model)
    {
        ModelAndView view = new ModelAndView("/shared/success");
        view.addObject("submission", model);
        return view;
    }

    @GetMapping(value = "/info")
    public ModelAndView getInfoPage(Submission model)
    {
        ModelAndView view = new ModelAndView("/shared/info");
        view.addObject("submission", model);
        return view;
    }

}
