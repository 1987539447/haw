package com.bigknow.system.controller;

import com.bigknow.frame.controller.BaseRestController;
import com.bigknow.frame.util.GlobalStatic;
import com.bigknow.system.entity.Node;
import com.bigknow.system.service.INodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/19.
 */
@RestController//采用RestController避免的在每个方法上门用 responseBody
@RequestMapping(value = "/system/node/")
public class NodeController extends BaseRestController<Node> {

    @Autowired
    public void setService(INodeService nodeService) {
        this.service = nodeService;
        this.listUrl = "/system/node/nodeList";
    }

    @RequestMapping(value = "/downloadKeyStore/{id}", method = RequestMethod.GET)
    public String downloadKeyStore(HttpServletResponse response, @PathVariable String id) {
//        Node node = ((INodeService)this.service).findById4KeyStore(id);
//        this.downloadBytes(response,node.getKeyStore(),node.getCode()+".jks");
        return GlobalStatic.NON_JSON_CONTENT;
    }

    @RequestMapping(value = "/rest/fuzzyCity/{name}", method = RequestMethod.GET)
    public List<CityData> fuzzyCity(@PathVariable String name){
        List<CityData> list = new ArrayList<>();
        list.add(new CityData("a","b"));
        list.add(new CityData("aaa", "bbb"));

        return list;
    }

    class CityData{
        private String value;
        private String data;

        public CityData(String value, String data) {
            this.value = value;
            this.data = data;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
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
