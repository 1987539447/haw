package com.bigknow.system.controller;

import com.bigknow.frame.controller.BaseRestController;
import com.bigknow.system.entity.User;
import com.bigknow.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/3/2.
 */
@RestController//采用RestController避免的在每个方法上门用 responseBody
@RequestMapping(value = "/system/user/")
public class UserController extends BaseRestController<User> {

    @Autowired
    public void setService(IUserService userService){
        this.service = userService;
        this.listUrl = "/system/user/userList";
    }

}
