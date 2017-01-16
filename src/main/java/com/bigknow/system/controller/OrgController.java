package com.bigknow.system.controller;

import com.bigknow.frame.controller.BaseRestController;
import com.bigknow.system.entity.Org;
import com.bigknow.system.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/3/16.
 */
@RestController
@RequestMapping("/system/org/")
public class OrgController extends BaseRestController<Org> {

    @Autowired
    public void setService(IOrgService orgService){
        this.service = orgService;
    }

    @RequestMapping("/tree2")
    public ModelAndView tree(){
        return new ModelAndView("system/org/tree2");
    }
}
