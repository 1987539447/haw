package com.bigknow.frame.controller;

import com.bigknow.frame.entity.IEntity;
import com.bigknow.frame.service.ICRUDService;
import com.bigknow.frame.vo.Page;
import com.bigknow.frame.vo.ResultInfo;
import com.bigknow.frame.vo.VoFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bigknow.frame.util.GlobalStatic.*;

/**
 * rest json必须要有返回值
 * Created by Administrator on 2015/3/2.
 */
public class BaseRestController<T extends IEntity> extends BaseController {

    protected String listUrl;

    protected ICRUDService<T> service;

    // @RequestMapping(value = "/rest/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public Page<T> getAll(@PathVariable int pageNum, @PathVariable int pageSize, T t) {
        return service.findAll(t, pageNum, pageSize);
    }

    @RequestMapping(value = "/rest/listAll", method = RequestMethod.GET)
    public List<T> getAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET)
     public IEntity get(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/rest/", method = RequestMethod.POST)
    public ResultInfo addOrUpdate(T t) {
        if(!StringUtils.isEmpty(t.getId())){
            //当id不为空，并且id存在的时候再做update，否则执行insert
            //先执行update，如果没有update任何数据再做insert，就不执行return
            ResultInfo info = update(t);
            if(!springUtils.getMessage(UPDATE_NON).equals(info.getInfo())){
                return info;
            }
        }
        try {
            service.add(t);
        } catch (Exception e) {
            e.printStackTrace();
            return VoFactory.newResultInfo(ResultInfo.ERROR, e.getMessage());
        }
        return VoFactory.newResultInfo(ResultInfo.SUCCESS, springUtils.getMessage(ADD_OK));
    }

/*
  //由于部分情况下put方法不好用，值传不过来，改用post方法，这样就与add方法重合了
  //add方法改为 addOrUpdate
  @RequestMapping(value = "/rest/{id}", method = RequestMethod.PUT)
    public ResultInfo update(@PathVariable String id, T t) {
        try {
            if (service.update(t) == 0)
                return VoFactory.newResultInfo(ResultInfo.ERROR, springUtils.getMessage(UPDATE_NON));
        } catch (Exception e) {
            e.printStackTrace();
            return VoFactory.newResultInfo(ResultInfo.ERROR, e.getMessage());
        }
        return VoFactory.newResultInfo(ResultInfo.SUCCESS, springUtils.getMessage(UPDATE_OK));
    }*/

    private ResultInfo update(T t){
        try {
            if (service.update(t) == 0)
                return VoFactory.newResultInfo(ResultInfo.ERROR, springUtils.getMessage(UPDATE_NON));
        } catch (Exception e) {
            e.printStackTrace();
            return VoFactory.newResultInfo(ResultInfo.ERROR, e.getMessage());
        }
        return VoFactory.newResultInfo(ResultInfo.SUCCESS, springUtils.getMessage(UPDATE_OK));
    }

    @RequestMapping(value = "/rest/{id}", method = RequestMethod.DELETE)
    public ResultInfo delete(@PathVariable String id) {
        try {
            if (service.delete(id) == 0)
                return VoFactory.newResultInfo(ResultInfo.ERROR, springUtils.getMessage(DELETE_NON));
        } catch (Exception e) {
            return VoFactory.newResultInfo(ResultInfo.ERROR, e.getMessage());
        }
        return VoFactory.newResultInfo(ResultInfo.SUCCESS, springUtils.getMessage(DELETE_OK));
    }

    /**
     * 非rest方法,用于返回list页面,若果采用json方式
     *
     * @param t
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView list(T t, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", this.service.findAll(t, pageNum == null ? DEFAULT_PAGE_NUMBER : pageNum, pageSize == null ? DEFAULT_PAGE_SIZE : pageSize));
        map.put("fuzzyField", t.getFuzzyField());
        return new ModelAndView(listUrl, map);
    }
}
