package com.bigknow.system.controller;

import com.bigknow.frame.controller.BaseRestController;
import com.bigknow.system.entity.City;
import com.bigknow.system.service.ICityService;
import com.bigknow.system.vo.CityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/24.
 */
@RestController//采用RestController避免的在每个方法上门用 responseBody
@RequestMapping(value = "/system/city/")
public class CityController extends BaseRestController<City> {

    @Autowired
    public void setService(ICityService cityService){
        this.service = cityService;
        this.listUrl = "/system/city/cityList";
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ModelAndView treePage() {
        return new ModelAndView("/system/city/tree");
    }

    @RequestMapping(value = "/rest/listAllVo", method = RequestMethod.GET)
    public List<CityVo> getAllVo() {
        List<CityVo> list = new ArrayList<CityVo>();
        List<City> cities = service.findAll();
        for(City city:cities){
            CityVo cv = new CityVo();
            cv.setId(city.getCityId());
            cv.setPid(city.getParentid());
            cv.setName(city.getAreaname());
            list.add(cv);
        }
        return list;
    }
}
