package com.bigknow.system.controller;

import com.bigknow.frame.controller.BaseRestController;
import com.bigknow.system.entity.Role;
import com.bigknow.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/8/13.
 */
@RestController//采用RestController避免的在每个方法上门用 responseBody
@RequestMapping(value = "/system/role/")
public class roleController extends BaseRestController<Role> {

    @Autowired
    public void setService(IRoleService roleService){
        this.service = roleService;
        this.listUrl = "/system/role/roleList";
    }
}
