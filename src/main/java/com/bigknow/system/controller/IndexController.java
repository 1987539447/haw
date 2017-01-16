package com.bigknow.system.controller;

import com.bigknow.frame.controller.BaseController;
import com.bigknow.frame.util.AuthenticateUserUtil;
import com.bigknow.system.entity.Menu;
import com.bigknow.system.service.IDelegatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2015/5/6.
 */
@RestController
public class IndexController extends BaseController {

    @Autowired
    private IDelegatingService delegatingService;

    @RequestMapping("index/menu")
    public List<Menu> getMenu(){
        return delegatingService.getMenuByUserId(AuthenticateUserUtil.getUserId());
    }
}
