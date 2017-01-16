package com.bigknow.system.service.impl;

import com.bigknow.frame.service.BaseCRUDService;
import com.bigknow.system.entity.Node;
import com.bigknow.system.mapper.CityMapper;
import com.bigknow.system.mapper.NodeMapper;
import com.bigknow.system.service.INodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/3/23.
 */
@Service("nodeService")
public class NodeServiceImpl extends BaseCRUDService<Node, NodeMapper> implements INodeService {

    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private CityMapper cityMapper;

    public Node findById4KeyStore(String id) {
        return nodeMapper.findById4KeyStore(id);
    }

//    @Override
//    public void add(Node node) {
//        List<City> cities = cityMapper.selectByCityName(node.getCityName());
//        if(cities != null && !cities.isEmpty()){
//            node.setCityId(cities.get(0).getId());// 只取查询出来的第一个城市Id
//            generateKeyStore(node);
//            super.add(node);
//        } else {
//            throw new RuntimeException("City name is error.");
//        }
//    }
//
//    @Override
//    public int update(Node node) {
//        List<City> cities = cityMapper.selectByCityName(node.getCityName());
//        if(cities != null && !cities.isEmpty()){
//            node.setCityId(cities.get(0).getId());// 只取查询出来的第一个城市Id
//            generateKeyStore(node);
//            int result =super.update(node);
//            return result;
//        } else {
//            throw new RuntimeException("City name is error.");
//        }
//
//    }

//    private void generateKeyStore(Node node){
//        ByteArrayOutputStream bas = new ByteArrayOutputStream();
//        try {
//            CAManager.getInstance().generateKeyStore("C=CN,ST=北京,L=北京,O=商务部,OU=商务部,CN=商务部,E=bb@bigknow.com.cn", node.getCode(),
//                    bas);
//            System.out.println("node key store size = " + bas.toByteArray().length);
//            node.setKeyStore(bas.toByteArray());
//        } catch (Exception e) {
//            throw new RuntimeException("Cannot generate key store", e);
//        }
//    }

    public static void main(String[] args) {

    }
}
