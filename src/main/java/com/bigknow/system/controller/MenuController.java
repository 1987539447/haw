package com.bigknow.system.controller;

import com.bigknow.frame.controller.BaseRestController;
import com.bigknow.system.entity.Menu;
import com.bigknow.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/8/19.
 */
@RestController//采用RestController避免的在每个方法上门用 responseBody
@RequestMapping(value = "/system/menu/")
public class MenuController extends BaseRestController<Menu> {

    @Autowired
    public void setService(IMenuService menuService){
        this.service = menuService;
        this.listUrl = "/system/menu/menuList";
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
         public ModelAndView treePage() {
        return new ModelAndView("/system/menu/tree");
    }

    @RequestMapping(value = "/tree2", method = RequestMethod.GET)
    public ModelAndView tree2Page() {
        return new ModelAndView("/system/menu/tree2");
    }
}
